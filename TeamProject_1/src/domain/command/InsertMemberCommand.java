package domain.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;
import member.MemberDTO;

public class InsertMemberCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {
			String pw = request.getParameter("pw");
			String name = request.getParameter("name");
			String nick = request.getParameter("nick");
			String birth = request.getParameter("birth");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			
			MemberDAO dao = new MemberDAO();
			dao.insert(new MemberDTO(id, pw, name, nick, birth, email, phone, -1, -1, null));
			
			return new CommandAction(false, "login.domain?id="+id+"&pw="+pw);
		}
		
		return new CommandAction(true, "error.jsp");
	}

}
