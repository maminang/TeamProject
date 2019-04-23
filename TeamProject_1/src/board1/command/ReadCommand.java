package board1.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board1.domain.Board1DAO;
import board1.domain.Board1DTO;
import board1.domain.CommentBoard1DAO;
import board1.domain.CommentBoard1DTO;
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
			Board1DAO dao = new Board1DAO();
			String ipAddress = request.getRemoteAddr();
			Board1DTO dto = dao.read(num, ipAddress);
			String youtube = dto.getYoutube();
			
			String loginedId = "";
			HttpSession session =  request.getSession(false);
			Object oLogin = null;
			if (session != null) {				
				 oLogin = session.getAttribute("login");
			}
			if (oLogin != null && oLogin instanceof MemberDTO ) {
				MemberDTO login = (MemberDTO) oLogin;
				loginedId = login.getId();
			}
			boolean isGood = dao.checkGood(num, loginedId);

			MemberDAO mdao = new MemberDAO();
			String id = dto.getWriter();
			MemberDTO mdto = mdao.memberInfo(id);

			CommentBoard1DAO cDao = new CommentBoard1DAO();
			List<CommentBoard1DTO> list = cDao.list(num);

			request.setAttribute("mdto", mdto);
			if (youtube == null) {
				request.setAttribute("curPage", curPage);
				request.setAttribute("dto", dto);
				request.setAttribute("commentList", list);
				request.setAttribute("youtube", youtube);
				request.setAttribute("idGood", isGood);
				return new CommandAction(false, "board1_read.jsp");
			} else if (youtube.length() < 25) {
				String youtube2 = null;
				request.setAttribute("curPage", curPage);
				request.setAttribute("dto", dto);
				request.setAttribute("commentList", list);
				request.setAttribute("youtube", youtube2);
				request.setAttribute("idGood", isGood);
				return new CommandAction(false, "board1_read.jsp");
			} else {
				String youtube2 = youtube.substring(16);
				request.setAttribute("curPage", curPage);
				request.setAttribute("dto", dto);
				request.setAttribute("commentList", list);
				request.setAttribute("youtube", youtube2);
				request.setAttribute("idGood", isGood);
				return new CommandAction(false, "board1_read.jsp");
			}
		}
		return new CommandAction(true, "error.jsp");
	}

}
