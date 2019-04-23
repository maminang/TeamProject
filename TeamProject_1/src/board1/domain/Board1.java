package board1.domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board1.command.BadCommand;
import board1.command.BadCommentCommand;
import board1.command.DeleteCommand;
import board1.command.DownloadCommand;
import board1.command.GoodCommand;
import board1.command.ListCommand;
import board1.command.ReadCommand;
import board1.command.SearchCommand;
import board1.command.UpdateCommand;
import board1.command.UpdateUICommand;
import board1.command.WriteCommand;
import board1.command.WriteCommentCommand;
import board1.command.WriteReplyCommand;
import board1.command.WriteReplyUICommand;
import board1.command.WriteUICommand;
import board1.comment.DeleteCommentCommand;
import board1.comment.GoodCommentCommand;
import domain.util.Command;
import domain.util.CommandAction;

/**
 * Servlet implementation class Board1
 */
@WebServlet("*.final")
public class Board1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board1() {
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

		if (sp.equalsIgnoreCase("/list.final"))
			com = new ListCommand();
		else if (sp.equalsIgnoreCase("/writeUI.final"))
			com = new WriteUICommand();
		else if (sp.equalsIgnoreCase("/write.final"))
			com = new WriteCommand();
		else if (sp.equalsIgnoreCase("/read.final"))
			com = new ReadCommand();
		else if (sp.equalsIgnoreCase("/updateUI.final"))
			com = new UpdateUICommand();
		else if (sp.equalsIgnoreCase("/update.final"))
			com = new UpdateCommand();
		else if (sp.equalsIgnoreCase("/delete.final"))
			com = new DeleteCommand();
		else if (sp.equalsIgnoreCase("/writeReplyUI.final"))
			com = new WriteReplyUICommand();
		else if (sp.equalsIgnoreCase("/writeReply.final"))
			com = new WriteReplyCommand();
		else if (sp.equalsIgnoreCase("/search.final"))
			com = new SearchCommand();
		else if (sp.equalsIgnoreCase("/writeComment.final"))
			com = new WriteCommentCommand();
		else if (sp.equalsIgnoreCase("/good.final"))
			com = new GoodCommand();
		else if (sp.equalsIgnoreCase("/bad.final"))
			com = new BadCommand();
		else if (sp.equalsIgnoreCase("/goodComment.final"))
			com = new GoodCommentCommand();
		else if (sp.equalsIgnoreCase("/badComment.final"))
			com = new BadCommentCommand();
		else if (sp.equalsIgnoreCase("/download.final"))
			com = new DownloadCommand();
		else if (sp.equalsIgnoreCase("/deleteComment.final"))
			com = new DeleteCommentCommand();

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
