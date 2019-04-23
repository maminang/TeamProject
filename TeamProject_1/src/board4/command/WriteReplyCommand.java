package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board4.domain.Board4DAO;
import board4.domain.Board4DTO;
import domain.util.Command;
import domain.util.CommandAction;

public class WriteReplyCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int orgNum = -1;
		if (sNum != null) {
			orgNum = Integer.valueOf(sNum);

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");

			Board4DAO dao = new Board4DAO();
			Board4DTO dto = new Board4DTO(-1, title, content, writer, null, -1, -1, -1, -1, -1, -1, -1, -1, null, -1,null);
			int crtdNum = dao.writeReply(orgNum, dto);

			return new CommandAction(true, "read.final4?num=" + crtdNum);
		}
		return new CommandAction(true, "error.jsp");
	}

}
