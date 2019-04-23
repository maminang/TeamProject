
package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.util.Command;
import domain.util.CommandAction;

public class WriteUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCurPage = request.getParameter("curPage");
		int curPage = 1;
		if (sCurPage != null) {
			curPage = Integer.valueOf(sCurPage);
		}
		request.setAttribute("curPage", curPage);
		
		return new CommandAction(false, "board4_write.jsp");
	}

}
