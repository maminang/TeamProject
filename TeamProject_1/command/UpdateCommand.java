package board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.Board1DAO;
import board1.domain.Board1DTO;
import domain.util.Command;
import domain.util.CommandAction;

public class UpdateCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);

			String title = request.getParameter("title");
			String content = request.getParameter("content");

			Board1DAO dao = new Board1DAO();
			dao.update(new Board1DTO(num, title, content, null, null, -1, -1, -1, -1, -1, -1, -1, -1, null, -1));
			return new CommandAction(true, "read.final?num=" + num);
		}
		return new CommandAction(true, "error.jsp");
	}

}
