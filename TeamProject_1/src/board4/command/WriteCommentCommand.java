package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board4.domain.CommentBoard4DAO;
import board4.domain.CommentBoard4DTO;
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
			
			CommentBoard4DAO dao = new CommentBoard4DAO();
			dao.write(new CommentBoard4DTO(-1, content, writer, null, -1, -1, -1, -1, -1, num));
			
			return new CommandAction(true, "read.final4?num="+num+"&id="+id);
		}
		
		return new CommandAction(true, "error.jsp");
	}

}
