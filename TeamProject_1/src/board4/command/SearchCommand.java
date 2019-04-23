package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.util.Command;
import domain.util.CommandAction;

public class SearchCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String searchOption = request.getParameter("searchOption");
		
		System.out.println(searchOption);
		
		return new CommandAction(true, "list.final4");
	}

}
