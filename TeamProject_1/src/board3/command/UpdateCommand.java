package board3.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.domain.Board3DAO;
import board3.domain.Board3DTO;
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
			String youtube = request.getParameter("youtube");
		      
		         
			Board3DAO dao = new Board3DAO();
			dao.update(new Board3DTO(num, title, content, null, null, -1, -1, -1, -1, -1, -1, -1, -1, null, -1,youtube));
			return new CommandAction(true, "read.final3?num=" + num);
		}
		return new CommandAction(true, "error.jsp");
	}

}
