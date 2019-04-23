package board2.domain;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Board2DAO {

	private DataSource dataFactory;

	public Board2DAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private Board2DTO getRSInfo(ResultSet rs) {
		Board2DTO dto = null;
		try {
			int num = rs.getInt("num");
			String title = rs.getString("title");
			String content = rs.getString("content");
			String writer = rs.getString("writer");
			String writeDate = rs.getString("writeDate");
			int readCnt = rs.getInt("readCnt");
			int repRoot = rs.getInt("repRoot");
			int repStep = rs.getInt("repStep");
			int repIndent = rs.getInt("repIndent");
			int good = rs.getInt("good");
			int bad = rs.getInt("bad");
			int grade = rs.getInt("grade");
			int up_num = rs.getInt("up_num");
			String up_adress = rs.getString("up_adress");
			String youtube = null;
			if (rs.getString("youtube") != null) {
				youtube = rs.getString("youtube");
			}

			dto = new Board2DTO(num, title, content, writer, writeDate, readCnt, repRoot, repStep, repIndent, good, bad,
					grade, up_num, up_adress, -1, youtube);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	private int getMaxNum(Connection conn) {
		int num = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT max(num)+1 FROM tbl_final2";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, rs);
		}

		return num;
	}

	private void closeAll(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closeAll(Connection conn, PreparedStatement pstmt) {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private int getAmount(Connection conn) {
		int amount = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT count(num) FROM tbl_final2";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				amount = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, rs);
		}

		return amount;
	}

	public Page2TO listPage(int curPage) {
		Page2TO to = new Page2TO(curPage);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM "
				+ "(SELECT rownum rnum,num,title,content,writer,to_char(writedate, 'yyyy/mm/dd') writedate,readcnt,reproot,repstep,repindent,good,bad,grade,up_num,up_adress,commentcnt,youtube FROM "
				+ "(SELECT A.*, "
				+ "(SELECT COUNT(*) FROM tbl_comment_final2 C WHERE C.num = A.num) commentcnt FROM tbl_final2 A ORDER BY reproot DESC, repStep ASC)) "
				+ "WHERE rnum BETWEEN ? AND ?";

		List<Board2DTO> list = new ArrayList<Board2DTO>();
		try {
			conn = dataFactory.getConnection();

			to.setAmount(getAmount(conn));

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, to.getStartNum());
			pstmt.setInt(2, to.getEndNum());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board2DTO dto = getRSInfo(rs);
				dto.setCommentCnt(rs.getInt("commentcnt"));

				list.add(dto);
			}

			to.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

		return to;
	}

	public int write(Board2DTO dto) {
		int crtdNum = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO tbl_final2 (num,title,content,writer,reproot,repstep,repindent,good,bad,grade,up_num,up_adress,youtube) "
				+ "VALUES (?,?,?,?,?,0,0,0,0,0,0, ?,?)";

		try {
			conn = dataFactory.getConnection();

			crtdNum = getMaxNum(conn);
			System.out.println("aa" + dto.getTitle());
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, crtdNum);
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getWriter());
			pstmt.setInt(5, crtdNum);
			pstmt.setString(6, dto.getUp_adress());
			pstmt.setString(7, dto.getYoutube());
			System.out.println("dao" + dto.getYoutube());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
		return crtdNum;
	}

	private Board2DTO selectByNum(int num, Connection conn) {
		Board2DTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM tbl_final2 WHERE num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = getRSInfo(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt, rs);
		}
		return dto;
	}

	public Board2DTO read(int num, String ipAddress) {
		Board2DTO dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		boolean isOk = false;
		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			ipcheck(conn, num, ipAddress);
			dto = selectByNum(num, conn);

			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

			closeAll(conn, pstmt);
		}

		return dto;
	}

	public Board2DTO read(int num) {
		Board2DTO dto = null;

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			dto = selectByNum(num, conn);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			closeAll(conn, pstmt);
		}

		return dto;
	}

	private void incrReadCnt(int num, Connection conn) {
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_final2 SET readcnt = readcnt+1 WHERE num = ?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt);
		}
	}

	public void update(Board2DTO dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_final2 SET title = ?, content = ?, youtube=? WHERE num = ?";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getYoutube());
			pstmt.setInt(4, dto.getNum());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
	}

	public void delete(int num) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "DELETE tbl_final2 WHERE num = ?";

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

	public int writeReply(int orgNum, Board2DTO dto) {
		int crtdNum = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "INSERT INTO tbl_final2 "
				+ "(num,title,content,writer,reproot,repstep,repindent,good,bad,grade,up_num,up_adress) " + "VALUES "
				+ "(?,?,?,?,?,?,?,0,0,0,0,null)";

		boolean isOk = true;

		try {
			conn = dataFactory.getConnection();
			conn.setAutoCommit(false);

			Board2DTO orgDTO = selectByNum(orgNum, conn);
			incrRepStep(conn, orgDTO);

			crtdNum = getMaxNum(conn);

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, crtdNum);
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getWriter());
			pstmt.setInt(5, orgDTO.getRepRoot());
			pstmt.setInt(6, orgDTO.getRepStep() + 1);
			pstmt.setInt(7, orgDTO.getRepIndent() + 1);

			pstmt.executeUpdate();

			isOk = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (isOk) {
					conn.commit();
				} else {
					conn.rollback();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			closeAll(conn, pstmt);
		}
		return crtdNum;
	}

	private void incrRepStep(Connection conn, Board2DTO orgDTO) {
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_final2 SET repstep = repstep+1 WHERE reproot = ? AND repstep > ?";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, orgDTO.getRepRoot());
			pstmt.setInt(2, orgDTO.getRepStep());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(null, pstmt);
		}

	}

	public void good(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_final2 SET good = good+1 WHERE num = ?";
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

	public void bad(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "UPDATE tbl_final2 SET bad = bad+1 WHERE num = ?";
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

	public String download(int num) {
		String fileName = "";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT up_adress FROM tbl_final2 WHERE num = ?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				fileName = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return fileName;
	}

	public List<Board2DTO> mainList() {

		List<Board2DTO> list = new ArrayList<Board2DTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM (SELECT rownum rnum,num,title,content,writer,to_char(writedate, 'yyyy/mm/dd')"
				+ " writedate,readcnt,reproot,repstep,repindent,good,bad,grade,up_num,up_adress FROM "
				+ "(select * from tbl_final2 ORDER BY readcnt DESC)) WHERE rnum BETWEEN 1 AND 5";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writedate = rs.getString("writedate");
				int readcnt = rs.getInt("readcnt");
				int repRoot = rs.getInt("repRoot");
				int repStep = rs.getInt("repStep");
				int repIndent = rs.getInt("repIndent");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				int grade = rs.getInt("grade");
				int up_num = rs.getInt("up_num");

				Board2DTO dto = new Board2DTO(num, title, content, writer, writedate, readcnt, repRoot, repStep,
						repIndent, good, bad, grade, up_num, null, -1, null);
				list.add(dto);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return list;
	}

	public void idNumGood(String id, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from tbl_idnumgood2 where id=? and contentnum=?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("�빐�떦 �븘�씠�뵒濡� �빐�떦 湲��뿉 醫뗭븘�슂 �닔瑜� �씠誘� �삱由곗쟻�씠 �엳�뒿�땲�떎.");
			} else {
				recordgood(id, num);
				good(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

	}

	private void recordgood(String id, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into tbl_idnumgood2 (id, contentNum) values(?,?)";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	public void idNumBad(String id, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "select * from tbl_idnumbad2 where id=? and contentnum=?";
		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			pstmt.executeUpdate();
			rs = pstmt.executeQuery();

			if (rs.next()) {
				System.out.println("�빐�떦 �븘�씠�뵒濡� �빐�떦 湲��뿉 �떕�뼱�슂 �닔瑜� �씠誘� �삱由곗쟻�씠 �엳�뒿�땲�떎.");
			} else {
				recordbad(id, num);
				bad(num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

	}

	private void recordbad(String id, int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into tbl_idnumbad2 (id, contentNum) values(?,?)";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, num);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	private void ipcheck(Connection conn, int num, String ipAddress) {
		PreparedStatement pstmt = null;
		Calendar ocal = Calendar.getInstance();
		int year = ocal.get(Calendar.YEAR);
		int month = ocal.get(Calendar.MONTH);
		int date = ocal.get(Calendar.DATE);
		String ipdate = year + "-" + month + "-" + date;

		String sql = "Select * from tbl_ipcount2 where ip=? and ipcontentnum=? and ipdate=?";

		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ipAddress);
			pstmt.setInt(2, num);
			pstmt.setString(3, ipdate);

			pstmt.executeUpdate();
			rs = pstmt.executeQuery();
			if (rs.next()) {

			}
			else {
				writeipinfo(num, ipAddress, ipdate); 
				incrReadCnt(num, conn); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

	}

	private void writeipinfo(int num, String ipAddress, String ipdate) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into tbl_ipcount2 (ip, ipcontentNum, ipdate) values(?, ?, ?)";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ipAddress);
			pstmt.setInt(2, num);
			pstmt.setString(3, ipdate);
			pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, null);
		}

	}

	public boolean checkGood(int num, String loginedId) {
		boolean isGood = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs= null;
		
		String sql="select * from tbl_idnumgood2 where contentnum = ? and id = ?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, loginedId);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				isGood = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return isGood;
	}
}
