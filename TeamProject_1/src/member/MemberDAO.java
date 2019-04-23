package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAO {
	private DataSource dataFactory;

	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void basicFrame() {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeAll(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private MemberDTO getRSInfo(ResultSet rs) throws SQLException {
		MemberDTO dto = null;

		String id = rs.getString("id");
		String pw = rs.getString("pw");
		String name = rs.getString("name");
		String nick = rs.getString("nick");
		String birth = rs.getString("birth");
		String email = rs.getString("email");
		String phone = rs.getString("phone");
		int grade = rs.getInt("grade");
		int point = rs.getInt("point");
		String pfa = rs.getString("pfa");

		dto = new MemberDTO(id, pw, name, nick, birth, email, phone, grade, point, pfa);

		return dto;
	}

	public void insert(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO tbl_member (id,pw,name,nick,birth,email,phone) " + "VALUES (?,?,?,?,?,?,?)";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getNick());
			System.out.println(dto.getBirth());
			pstmt.setString(5, dto.getBirth());
			pstmt.setString(6, dto.getEmail());
			pstmt.setString(7, dto.getPhone());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public MemberDTO memberInfo(String id) {
		MemberDTO dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM tbl_member WHERE id = ?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = getRSInfo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

		return dto;
	}

	public void updateMember(MemberDTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql;
		if (dto.getPfa() == null) 
			sql = "UPDATE tbl_member SET nick = ?, email = ?, phone = ? WHERE id = ?";
		else 
			sql = "UPDATE tbl_member SET nick = ?, email = ?, phone = ?, pfa = ? WHERE id = ?";
		
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getNick());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getPhone());
			
			if (dto.getPfa() == null) {
				pstmt.setString(4, dto.getId());
			} else {
				pstmt.setString(4, dto.getPfa());
				pstmt.setString(5, dto.getId());
			}
			

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public MemberDTO login(String id, String pw) {
		MemberDTO dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT id,nick,grade from tbl_member where id = ? and pw = ?";

		try {
			conn = dataFactory.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				String nick = rs.getString("nick");
				int grade = rs.getInt("grade");

				dto = new MemberDTO(id, null, null, nick, null, null, null, grade, 0, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return dto;
	}

	public void incrGrade(String writer) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update tbl_member set point=point+1 where id=?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writer);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public int getNbrMembers() {
		int nbrMembers = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(id) FROM tbl_member";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				nbrMembers = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return nbrMembers;
	}

	
}
