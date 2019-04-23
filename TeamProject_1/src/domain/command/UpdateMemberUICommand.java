package domain.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;
import member.MemberDTO;

public class UpdateMemberUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id != null) {

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = dao.memberInfo(id);

			request.setAttribute("dto", dto);

			return new CommandAction(false, "updateMember.jsp");
		}
		return new CommandAction(true, "error.jsp");
	}

}
