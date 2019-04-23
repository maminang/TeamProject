package board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.Board1DAO;
import board1.domain.CommentBoard1DAO;
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
			
			Board1DAO bDao = new Board1DAO();
			CommentBoard1DAO cDao = new CommentBoard1DAO();
			
			cDao.delete(num);
			bDao.delete(num);
			return new CommandAction(true, "list.final");
		}
		return new CommandAction(true, "error.jsp");
	}

}
