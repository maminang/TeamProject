package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DomainDAO {
	private DataSource dataFactory;

	public DomainDAO() {
		try {
			Context ctxt = new InitialContext();
			dataFactory = (DataSource) ctxt.lookup("java:comp/env/jdbc/oracle11g");
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

	private DomainTO getRSInfo(ResultSet rs) {
		DomainTO dto = null;
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
			String youtube = rs.getString("youtube");
			String tblName = rs.getString("tblname");
			int commentCnt = rs.getInt("commentcnt");

			dto = new DomainTO(num, title, content, writer, writeDate, readCnt, repRoot, repStep, repIndent, good, bad,
					grade, up_num, up_adress, youtube, tblName, commentCnt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
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

	public List<DomainTO> searchList(String keyword, String searchOption, String searchOption2, int startNum,
			int endNum) {
		List<DomainTO> list = new ArrayList<DomainTO>();
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql;
		if (searchOption == null || searchOption.equals("")) {
			sql = "SELECT * FROM (SELECT rownum rnum,num,title,content,writer,to_char(writedate, 'yyyy/mm/dd') writedate,readcnt,reproot,repstep,repindent,good,bad,up_adress,tblname FROM (select * from view_all where title LIKE ? OR content LIKE ? OR writer LIKE ? ORDER BY "
					+ searchOption2 + " DESC)) WHERE rnum BETWEEN ? AND ?";
		} else {
			sql = "SELECT * FROM (SELECT rownum rnum,num,title,content,writer,to_char(writedate, 'yyyy/mm/dd') writedate,readcnt,reproot,repstep,repindent,good,bad,up_adress,tblname FROM (select * from view_all where "
					+ searchOption + " like ? ORDER BY " + searchOption2 + " DESC)) WHERE rnum BETWEEN ? AND ?";
		}

		ResultSet rs = null;

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			if (searchOption == null || searchOption.equals("")) {
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setString(3, "%" + keyword + "%");
				pstmt.setInt(4, startNum);
				pstmt.setInt(5, endNum);
			} else {
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setInt(2, startNum);
				pstmt.setInt(3, endNum);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				int num = rs.getInt("num");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String writer = rs.getString("writer");
				String writedate = rs.getString("writedate");
				int readcnt = rs.getInt("readcnt");
				int repIndent = rs.getInt("repIndent");
				int good = rs.getInt("good");
				int bad = rs.getInt("bad");
				String tblName = rs.getString("tblName");

				DomainTO dto = new DomainTO(num, title, content, writer, writedate, readcnt, -1, -1, repIndent, good,
						bad, -1, -1, null, null, tblName, -1);
				list.add(dto);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}

		return list;
	}

	public int getAmountUploaded() {
		int amount = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT count(num) FROM view_all WHERE up_adress IS NOT NULL";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				amount = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return amount;
	}

	public int getSearchAmount(String searchOption, String keyword) {
		int amount = -1;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql;
		if (searchOption == null || searchOption.equals(""))
			sql = "SELECT count(num) cnt FROM view_all WHERE title LIKE ? OR content LIKE ? OR writer LIKE ?";
		else
			sql = "SELECT count(num) cnt FROM view_all WHERE " + searchOption + " LIKE ?";

		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			if (searchOption == null || searchOption.equals("")) {
				pstmt.setString(1, "%" + keyword + "%");
				pstmt.setString(2, "%" + keyword + "%");
				pstmt.setString(3, "%" + keyword + "%");
			} else {
				pstmt.setString(1, "%" + keyword + "%");
			}

			rs = pstmt.executeQuery();

			if (rs.next()) {
				amount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		return amount;
	}

	public List<DomainTO> uploadedList(int curPage, int startNum, int endNum) {
	      List<DomainTO> list = new ArrayList<DomainTO>();

	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;

	      String sql = "SELECT * FROM "
	            + "(SELECT rownum rnum,num,title,content,writer,to_char(writedate, 'yyyy/mm/dd') writedate,readcnt,reproot,repstep,repindent,good,bad,grade,up_num,up_adress,youtube,commentcnt,tblname FROM "
	            + "(SELECT A.*, "
	            + "(SELECT COUNT(*) FROM view_comment_all C WHERE A.num = C.num) commentcnt FROM view_all A WHERE up_adress IS NOT NULL ORDER BY reproot DESC, repStep ASC)) "
	            + "WHERE rnum BETWEEN ? AND ?";
	      try {
	         conn = dataFactory.getConnection();

	         pstmt = conn.prepareStatement(sql);
	         pstmt.setInt(1, startNum);
	         pstmt.setInt(2, endNum);

	         rs = pstmt.executeQuery();

	         while (rs.next()) {
	            DomainTO dto = getRSInfo(rs);
	            dto.setCommentCnt(rs.getInt("commentcnt"));

	            list.add(dto);
	         }

	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         closeAll(conn, pstmt, rs);
	      }

	      return list;
	   }

	public int getNbrPosts() {
		int nbrPosts = -1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(num) nbrMember FROM view_all";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				nbrPosts = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt, rs);
		}
		
		return nbrPosts;
	}

	public int getNbrPostMonth(String month) {
		int nbrPostsMonth = -1;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "select count(num) from view_all where to_char(writedate , 'yyyy-mm') like ?";
		try {
			conn = dataFactory.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, month);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				nbrPostsMonth = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeAll(conn, pstmt);
		}
		
		return nbrPostsMonth;
	}

}
