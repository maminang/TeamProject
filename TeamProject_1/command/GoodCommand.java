package board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.Board1DAO;
import domain.util.Command;
import domain.util.CommandAction;

public class GoodCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);
			
			Board1DAO dao = new Board1DAO();
			dao.good(num);
			
			return new CommandAction(true, "read.final?num="+num);
		}
		return new CommandAction(true, "error.jsp");
	}

}
