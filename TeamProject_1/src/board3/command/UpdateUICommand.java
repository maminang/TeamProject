package board3.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.domain.Board3DAO;
import board3.domain.Board3DTO;
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
			
			Board3DAO dao = new Board3DAO();
			Board3DTO dto = dao.read(num);
			
			request.setAttribute("dto", dto);
			
			return new CommandAction(false, "board3_update.jsp");
		}
		return null;
	}

}
