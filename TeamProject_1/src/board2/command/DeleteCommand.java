package board2.command;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board1.domain.Board1DAO;
import board2.domain.Board2DAO;
import board2.domain.CommentBoard2DAO;
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
			
			Board2DAO bDao = new Board2DAO();
			CommentBoard2DAO cDao = new CommentBoard2DAO();
			
			cDao.delete(num);
			bDao.delete(num);
			return new CommandAction(true, "list.final2");
		}
		return new CommandAction(true, "error.jsp");
	}

}
