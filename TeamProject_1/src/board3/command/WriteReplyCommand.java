package board3.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.domain.Board3DAO;
import board3.domain.Board3DTO;
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

			Board3DAO dao = new Board3DAO();
			Board3DTO dto = new Board3DTO(-1, title, content, writer, null, -1, -1, -1, -1, -1, -1, -1, -1, null, -1,null);
			int crtdNum = dao.writeReply(orgNum, dto);

			return new CommandAction(true, "read.final3?num=" + crtdNum);
		}
		return new CommandAction(true, "error.jsp");
	}

}
