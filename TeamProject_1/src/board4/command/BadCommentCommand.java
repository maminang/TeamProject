package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board4.domain.CommentBoard4DAO;
import domain.util.Command;
import domain.util.CommandAction;

public class BadCommentCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCnum = request.getParameter("cnum");
		int cnum = -1;
		if (sCnum != null) {
			cnum = Integer.valueOf(sCnum);
			
			String sNum = request.getParameter("num");
			int num = -1;
			if (sNum != null) {
				num = Integer.valueOf(sNum);
			}
			
			CommentBoard4DAO dao = new CommentBoard4DAO();
			dao.bad(cnum);
			
			return new CommandAction(true, "read.final4?num="+num);
		}
		return new CommandAction(true, "error.jsp");
	}

}
