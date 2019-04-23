package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board4.domain.Board4DAO;
import board4.domain.CommentBoard4DAO;
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
			
			Board4DAO bDao = new Board4DAO();
			CommentBoard4DAO cDao = new CommentBoard4DAO();
			
			cDao.delete(num);
			bDao.delete(num);
			return new CommandAction(true, "list.final4");
		}
		return new CommandAction(true, "error.jsp");
	}

}
