package board3.domain;

import java.io.IOException; 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board3.command.BadCommand;
import board3.command.BadCommentCommand;
import board3.command.DeleteCommand;
import board3.command.DownloadCommand;
import board3.command.GoodCommand;
import board3.command.ListCommand;
import board3.command.ReadCommand;
import board3.command.SearchCommand;
import board3.command.UpdateCommand;
import board3.command.UpdateUICommand;
import board3.command.WriteCommand;
import board3.command.WriteCommentCommand;
import board3.command.WriteReplyCommand;
import board3.command.WriteReplyUICommand;
import board3.command.WriteUICommand;
import board3.comment.DeleteCommentCommand;
import board3.comment.GoodCommentCommand;
import domain.util.Command;
import domain.util.CommandAction;

/**
 * Servlet implementation class Board3
 */
@WebServlet("*.final3")
public class Board3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board3() {
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

		if (sp.equalsIgnoreCase("/list.final3"))
			com = new ListCommand();
		else if (sp.equalsIgnoreCase("/writeUI.final3"))
			com = new WriteUICommand();
		else if (sp.equalsIgnoreCase("/write.final3"))
			com = new WriteCommand();
		else if (sp.equalsIgnoreCase("/read.final3"))
			com = new ReadCommand();
		else if (sp.equalsIgnoreCase("/updateUI.final3"))
			com = new UpdateUICommand();
		else if (sp.equalsIgnoreCase("/update.final3"))
			com = new UpdateCommand();
		else if (sp.equalsIgnoreCase("/delete.final3"))
			com = new DeleteCommand();
		else if (sp.equalsIgnoreCase("/writeReplyUI.final3"))
			com = new WriteReplyUICommand();
		else if (sp.equalsIgnoreCase("/writeReply.final3"))
			com = new WriteReplyCommand();
		else if (sp.equalsIgnoreCase("/search.final3"))
			com = new SearchCommand();
		else if (sp.equalsIgnoreCase("/writeComment.final3"))
			com = new WriteCommentCommand();
		else if (sp.equalsIgnoreCase("/good.final3"))
			com = new GoodCommand();
		else if (sp.equalsIgnoreCase("/bad.final3"))
			com = new BadCommand();
		else if (sp.equalsIgnoreCase("/goodComment.final3"))
			com = new GoodCommentCommand();
		else if (sp.equalsIgnoreCase("/badComment.final3"))
			com = new BadCommentCommand();
		else if (sp.equalsIgnoreCase("/download.final3"))
			com = new DownloadCommand();
		else if (sp.equalsIgnoreCase("/deleteComment.final3"))
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
