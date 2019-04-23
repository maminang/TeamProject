package board3.comment;

import java.io.IOException; 

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board3.domain.CommentBoard3DAO;
import domain.util.Command;
import domain.util.CommandAction;

public class GoodCommentCommand implements Command {

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
			
			CommentBoard3DAO dao = new CommentBoard3DAO();
			dao.good(cnum);
			
			return new CommandAction(true, "read.final3?num="+num);
		}
		return new CommandAction(true, "error.jsp");
	}

}
