package board3.command;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.domain.Board3DAO;
import board3.domain.Page3TO;
import domain.util.Command;
import domain.util.CommandAction;

public class ListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCurPage = request.getParameter("curPage");
		int curPage = 1; 
		if (sCurPage != null) {
			curPage = Integer.valueOf(sCurPage);
		}

		Board3DAO dao = new Board3DAO();
		Page3TO to = dao.listPage(curPage);
		request.setAttribute("to", to);
		request.setAttribute("list", to.getList());
		
		return new CommandAction(false, "board3.jsp");
	}

}
