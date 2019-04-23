package domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.command.ChatCommand;
import domain.command.InsertMemberCommand;
import domain.command.InsertMemberUICommand;
import domain.command.LoginCommand;
import domain.command.LogoutCommand;
import domain.command.MainListCommand;
import domain.command.MemberInfoCommand;
import domain.command.MgmtCommand;
import domain.command.SearchUICommand;
import domain.command.UpdateMemberCommand;
import domain.command.UpdateMemberUICommand;
import domain.command.UploadedCommand;
import domain.util.Command;
import domain.util.CommandAction;

/**
 * Servlet implementation class DomainController
 */
@WebServlet("*.domain")
public class DomainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DomainController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sp = request.getServletPath();

		Command com = null;

		if (sp.equalsIgnoreCase("/mainList.domain"))
			com = new MainListCommand();
		else if (sp.equalsIgnoreCase("/searchUI.domain"))
			com = new SearchUICommand();
		else if (sp.equalsIgnoreCase("/uploaded.domain"))
			com = new UploadedCommand();
		else if (sp.equalsIgnoreCase("/insertMemberUI.domain"))
			com = new InsertMemberUICommand();
		else if (sp.equalsIgnoreCase("/insertMember.domain"))
			com = new InsertMemberCommand();
		else if (sp.equalsIgnoreCase("/memberInfo.domain"))
			com = new MemberInfoCommand();
		else if (sp.equalsIgnoreCase("/updateMemberUI.domain"))
			com = new UpdateMemberUICommand();
		else if (sp.equalsIgnoreCase("/updateMember.domain"))
			com = new UpdateMemberCommand();
		else if (sp.equalsIgnoreCase("/login.domain"))
			com = new LoginCommand();
		else if (sp.equalsIgnoreCase("/logout.domain"))
			com = new LogoutCommand();
		else if (sp.equalsIgnoreCase("/chat.domain"))
			com = new ChatCommand();
		else if (sp.equalsIgnoreCase("/mgmt.domain"))
			com = new MgmtCommand();
		
			if (com != null) {
				CommandAction action = com.execute(request, response);

				if (action.isRedirect()) {
					response.sendRedirect(action.getWhere());
				} else {
					request.getRequestDispatcher(action.getWhere()).forward(request, response);
				}
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
