package board1.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.domain.CommentBoard1DAO;
import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;

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
		
		System.out.println(cnum);
		CommentBoard1DAO dao=new CommentBoard1DAO();
		dao.deleteComment(cnum);
		
		
		return new CommandAction(true, "read.final?num="+num);
	}

}
