package board2.domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CommentBoard2DAO {
	private DataSource dataFactory;

	public CommentBoard2DAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void closeAll(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private int getMaxCnum(Connection conn) {
		int cnum = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT max(cnum)+1 cnum FROM tbl_comment_final2";
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next())
				cnum = rs.getInt("cnum");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, rs);
		}
		return cnum;
	}

	private CommentBoard2DTO getRSInfo(ResultSet rs) {
		CommentBoard2DTO dto = null;

		try {
			int cnum = rs.getInt("cnum");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			String writeDate = rs.getString("writedate");
			int repRoot = rs.getInt("repRoot");
			int repStep = rs.getInt("repstep");
			int repIndent = rs.getInt("repIndent");
			int good = rs.getInt("good");
			int bad = rs.getInt("bad");
			int num = rs.getInt("num");

			dto = new CommentBoard2DTO(cnum, content, writer, writeDate, repRoot, repStep, repIndent, good, bad, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	public void write(CommentBoard2DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO tbl_comment_final2 " + "(cnum,content,writer,reproot,repstep,repindent,good,bad,num) "
				+ "VALUES (?,?,?,?,0,0,0,0,?)";
		try {
			conn = dataFactory.getConnection();

			int cnum = getMaxCnum(conn);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getWriter());
			pstmt.setInt(4, cnum);
			pstmt.setInt(5, dto.getNum());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public List<CommentBoard2DTO> list(int num) {
		List<CommentBoard2DTO> list = new ArrayList<CommentBoard2DTO>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM tbl_comment_final2 WHERE num = ?";
		try {
			conn = dataFactory.getConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(getRSInfo(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

	public void delete(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "DELETE tbl_comment_final2 WHERE num = ?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public void good(int cnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE tbl_comment_final2 SET good = good+1 WHERE cnum = ?";
		try {
			conn =dataFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public void bad(int cnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "UPDATE tbl_comment_final2 SET bad = bad+1 WHERE cnum = ?";
		try {
			conn =dataFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public void deleteComment(int cnum) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = "delete tbl_comment_final2 WHERE cnum = ?";
		try {
			conn =dataFactory.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cnum);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
		
	}

	
}
