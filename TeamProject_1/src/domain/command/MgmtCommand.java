package domain.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.DomainDAO;
import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;

public class MgmtCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String month = request.getParameter("month");
		if (month == null || month.equals("")) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Date time = new Date();
			month = format.format(time);
		}

		MemberDAO mDao = new MemberDAO();
		int nbrMembers = mDao.getNbrMembers();
		DomainDAO dDao = new DomainDAO();
		int nbrPosts = dDao.getNbrPosts();
		int nbrPostsMonth = dDao.getNbrPostMonth(month);

		request.setAttribute("nbrMembers", nbrMembers);
		request.setAttribute("nbrPosts", nbrPosts);
		request.setAttribute("nbrPostsMonth", nbrPostsMonth);
		request.setAttribute("month", month);

		return new CommandAction(false, "mgmt.jsp");
	}

}
