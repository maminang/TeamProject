package board2.command;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.domain.Board2DAO;
import board2.domain.Board2DTO;
import board2.domain.CommentBoard2DAO;
import board2.domain.CommentBoard2DTO;
import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;
import member.MemberDTO;

public class ReadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);

			String sCurPage = request.getParameter("curPage");
			int curPage = 1;
			if (sCurPage != null) {
				curPage = Integer.valueOf(sCurPage);
			}
			Board2DAO dao = new Board2DAO();
			String ipAddress = request.getRemoteAddr();
			Board2DTO dto = dao.read(num ,ipAddress);
			String youtube = dto.getYoutube();
			
			String loginedId = "";
			Object oLogin = request.getSession(false).getAttribute("login");
			if (oLogin instanceof MemberDTO) {
				MemberDTO login = (MemberDTO) oLogin;
				loginedId = login.getId();
			}
			boolean isGood = dao.checkGood(num, loginedId);
			
			MemberDAO mdao = new MemberDAO();
			String id = dto.getWriter();
			MemberDTO mdto = mdao.memberInfo(id);

			CommentBoard2DAO cDao = new CommentBoard2DAO();
			List<CommentBoard2DTO> list = cDao.list(num);
			
			request.setAttribute("mdto", mdto);
			if (youtube==null) {
				request.setAttribute("curPage", curPage);
				request.setAttribute("dto", dto);
				request.setAttribute("commentList", list);
				request.setAttribute("youtube", youtube);
				request.setAttribute("idGood", isGood);
				return new CommandAction(false, "board2_read.jsp");
			} else if (youtube.length() < 25) {
				String youtube2 = null;
				request.setAttribute("curPage", curPage);
				request.setAttribute("dto", dto);
				request.setAttribute("commentList", list);
				request.setAttribute("youtube", youtube2);
				request.setAttribute("idGood", isGood);
				return new CommandAction(false, "board2_read.jsp");
			} else {
				String youtube2 = youtube.substring(16);
				request.setAttribute("curPage", curPage);
				request.setAttribute("dto", dto);
				request.setAttribute("commentList", list);
				request.setAttribute("youtube", youtube2);
				request.setAttribute("idGood", isGood);
				return new CommandAction(false, "board2_read.jsp");
			}
		}
		return new CommandAction(true, "error.jsp");
	}

}
