package board3.comment;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board3.domain.CommentBoard3DAO;
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
		
		CommentBoard3DAO dao=new CommentBoard3DAO();
		dao.deleteComment(cnum);
		
		
		return new CommandAction(true, "read.final3?num="+num);
	}

}
