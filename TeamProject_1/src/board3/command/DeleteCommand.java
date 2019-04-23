package board3.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.domain.Board3DAO;
import board3.domain.CommentBoard3DAO;
import domain.util.Command;
import domain.util.CommandAction;

public class DeleteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);
			
			Board3DAO bDao = new Board3DAO();
			CommentBoard3DAO cDao = new CommentBoard3DAO();
			
			cDao.delete(num);
			bDao.delete(num);
			return new CommandAction(true, "list.final3");
		}
		return new CommandAction(true, "error.jsp");
	}

}
