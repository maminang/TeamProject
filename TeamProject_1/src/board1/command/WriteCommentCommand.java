package board1.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.CommentBoard1DAO;
import board1.domain.CommentBoard1DTO;
import domain.util.Command;
import domain.util.CommandAction;

public class WriteCommentCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);
			
			String id = request.getParameter("id");
			
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			
			CommentBoard1DAO dao = new CommentBoard1DAO();
			dao.write(new CommentBoard1DTO(-1, content, writer, null, -1, -1, -1, -1, -1, num));
			
			return new CommandAction(true, "read.final?num="+num+"&id="+id);
		}
		
		return new CommandAction(true, "error.jsp");
	}

}
