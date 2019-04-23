package board2.command;

import java.io.IOException;  
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import board2.domain.Board2DAO;
import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDTO;

public class BadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      HttpSession test = request.getSession();
	      MemberDTO login = (MemberDTO) test.getAttribute("login");
	      if(login != null) {
	            String sNum = request.getParameter("num");
	            String id = login.getId();
	            int num = -1;
	            if (sNum != null) {
	               num = Integer.parseInt(sNum);
	               Board2DAO dao = new Board2DAO();
	               dao.idNumBad(id,num);
	               return new CommandAction(true, "read.final2?num="+num);
	            }
	      }
	            return new CommandAction(true, "login.jsp");
	   }

}
