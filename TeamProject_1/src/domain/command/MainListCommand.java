package domain.command;

import java.io.IOException;  
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board1.domain.Board1DAO;
import board1.domain.Board1DTO;
import board2.domain.Board2DAO;
import board2.domain.Board2DTO;
import board3.domain.Board3DAO;
import board3.domain.Board3DTO;
import board4.domain.Board4DAO;
import board4.domain.Board4DTO;
import domain.util.Command;
import domain.util.CommandAction;

public class MainListCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Board1DAO b1dao = new Board1DAO();
		Board2DAO b2dao = new Board2DAO();
		Board3DAO b3dao = new Board3DAO();
		Board4DAO b4dao = new Board4DAO();
		
		List<Board1DTO> board1List = b1dao.mainList();
		List<Board2DTO> board2List = b2dao.mainList();
		List<Board3DTO> board3List = b3dao.mainList();
		List<Board4DTO> board4List = b4dao.mainList();
		
		request.setAttribute("board1List", board1List);
		request.setAttribute("board2List", board2List);
		request.setAttribute("board3List", board3List);
		request.setAttribute("board4List", board4List);
		

		return new CommandAction(false, "main.jsp");
	}

}
