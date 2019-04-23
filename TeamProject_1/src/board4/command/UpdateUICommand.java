package board4.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board4.domain.Board4DAO;
import board4.domain.Board4DTO;
import domain.util.Command;
import domain.util.CommandAction;

public class UpdateUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);
			
			Board4DAO dao = new Board4DAO();
			Board4DTO dto = dao.read(num);
			
			request.setAttribute("dto", dto);
			
			return new CommandAction(false, "board4_update.jsp");
		}
		return null;
	}

}
