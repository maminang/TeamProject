package board3.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.domain.CommentBoard3DAO;
import board3.domain.CommentBoard3DTO;
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
			
			CommentBoard3DAO dao = new CommentBoard3DAO();
			dao.write(new CommentBoard3DTO(-1, content, writer, null, -1, -1, -1, -1, -1, num));
			return new CommandAction(true, "read.final3?num="+num+"&id="+id);
		}
		
		return new CommandAction(true, "error.jsp");
	}

}
