package board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.Board1DAO;
import board1.domain.PageTO;
import domain.util.Command;
import domain.util.CommandAction;

public class ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCurPage = request.getParameter("curPage");
		int curPage = 1; // sCurPage媛� �뾾�쑝硫� 泥ロ럹�씠吏�濡� 媛��룄濡� 1濡� 珥덇린�솕
		if (sCurPage != null) {
			curPage = Integer.valueOf(sCurPage);
		}

		Board1DAO dao = new Board1DAO();
		PageTO to = dao.listPage(curPage);
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		
		return new CommandAction(false, "board1.jsp");
	}

}
