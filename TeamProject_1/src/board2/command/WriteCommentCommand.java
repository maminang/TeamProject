package board2.command;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.domain.CommentBoard2DAO;
import board2.domain.CommentBoard2DTO;
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
			
			CommentBoard2DAO dao = new CommentBoard2DAO();
			dao.write(new CommentBoard2DTO(-1, content, writer, null, -1, -1, -1, -1, -1, num));
			
			return new CommandAction(true, "read.final2?num="+num+"&id="+id);
		}
		
		return new CommandAction(true, "error.jsp");
	}

}
