package board4.comment;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board4.domain.CommentBoard4DAO;
import domain.util.Command;
import domain.util.CommandAction;

public class DeleteCommentCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String scnum=request.getParameter("cnum");
		int cnum=-1;
		if (scnum!=null) {
			cnum=Integer.parseInt(scnum);
		}
		String snum=request.getParameter("num");
		int num=-1;
		if (snum!=null) {
			num=Integer.parseInt(snum);
		}
		
		CommentBoard4DAO dao=new CommentBoard4DAO();
		dao.deleteComment(cnum);
		
		
		return new CommandAction(true, "read.final4?num="+num);
	}

}
