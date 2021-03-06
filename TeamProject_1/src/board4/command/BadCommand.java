package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board4.domain.Board4DAO;
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
	               Board4DAO dao = new Board4DAO();
	               dao.idNumBad(id,num);
	               return new CommandAction(true, "read.final4?num="+num);
	            }
	      }
	            return new CommandAction(true, "login.jsp");
	   }

}
