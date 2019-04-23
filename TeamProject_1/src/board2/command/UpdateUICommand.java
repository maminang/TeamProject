package board2.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board2.domain.Board2DAO;
import board2.domain.Board2DTO;
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
			
			Board2DAO dao = new Board2DAO();
			Board2DTO dto = dao.read(num);
			
			request.setAttribute("dto", dto);
			
			return new CommandAction(false, "board2_update.jsp");
		}
		return null;
	}

}
