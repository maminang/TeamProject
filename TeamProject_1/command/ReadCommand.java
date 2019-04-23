package board1.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.Board1DAO;
import board1.domain.Board1DTO;
import board1.domain.CommentBoard1DAO;
import board1.domain.CommentBoard1DTO;
import domain.util.Command;
import domain.util.CommandAction;

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

			Board1DAO bDao = new Board1DAO();
			Board1DTO dto = bDao.read(num);
			
			CommentBoard1DAO cDao = new CommentBoard1DAO();
			List<CommentBoard1DTO> list = cDao.list(num);

			request.setAttribute("curPage", curPage);
			request.setAttribute("dto", dto);
			request.setAttribute("commentList", list);
			return new CommandAction(false, "board1_read.jsp");
		}
		return new CommandAction(true, "error.jsp");
	}

}
