package board4.domain;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board4.command.BadCommand;
import board4.command.BadCommentCommand;
import board4.command.DeleteCommand;
import board4.command.DownloadCommand;
import board4.command.GoodCommand;
import board4.command.ListCommand;
import board4.command.ReadCommand;
import board4.command.SearchCommand;
import board4.command.UpdateCommand;
import board4.command.UpdateUICommand;
import board4.command.WriteCommand;
import board4.command.WriteCommentCommand;
import board4.command.WriteReplyCommand;
import board4.command.WriteReplyUICommand;
import board4.command.WriteUICommand;
import board4.comment.DeleteCommentCommand;
import board4.comment.GoodCommentCommand;
import domain.util.Command;
import domain.util.CommandAction;

/**
 * Servlet implementation class Board4
 */
@WebServlet("*.final4")
public class Board4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board4() {
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

		if (sp.equalsIgnoreCase("/list.final4"))
			com = new ListCommand();
		else if (sp.equalsIgnoreCase("/writeUI.final4"))
			com = new WriteUICommand();
		else if (sp.equalsIgnoreCase("/write.final4"))
			com = new WriteCommand();
		else if (sp.equalsIgnoreCase("/read.final4"))
			com = new ReadCommand();
		else if (sp.equalsIgnoreCase("/updateUI.final4"))
			com = new UpdateUICommand();
		else if (sp.equalsIgnoreCase("/update.final4"))
			com = new UpdateCommand();
		else if (sp.equalsIgnoreCase("/delete.final4"))
			com = new DeleteCommand();
		else if (sp.equalsIgnoreCase("/writeReplyUI.final4"))
			com = new WriteReplyUICommand();
		else if (sp.equalsIgnoreCase("/writeReply.final4"))
			com = new WriteReplyCommand();
		else if (sp.equalsIgnoreCase("/search.final4"))
			com = new SearchCommand();
		else if (sp.equalsIgnoreCase("/writeComment.final4"))
			com = new WriteCommentCommand();
		else if (sp.equalsIgnoreCase("/good.final4"))
			com = new GoodCommand();
		else if (sp.equalsIgnoreCase("/bad.final4"))
			com = new BadCommand();
		else if (sp.equalsIgnoreCase("/goodComment.final4"))
			com = new GoodCommentCommand();
		else if (sp.equalsIgnoreCase("/badComment.final4"))
			com = new BadCommentCommand();
		else if (sp.equalsIgnoreCase("/download.final4"))
			com = new DownloadCommand();
		else if (sp.equalsIgnoreCase("/deleteComment.final4"))
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
