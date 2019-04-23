package board2.domain;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board2.command.BadCommand;
import board2.command.BadCommentCommand;
import board2.command.DeleteCommand;
import board2.command.DownloadCommand;
import board2.command.GoodCommand;
import board2.command.ListCommand;
import board2.command.ReadCommand;
import board2.command.SearchCommand;
import board2.command.UpdateCommand;
import board2.command.UpdateUICommand;
import board2.command.WriteCommand;
import board2.command.WriteCommentCommand;
import board2.command.WriteReplyCommand;
import board2.command.WriteReplyUICommand;
import board2.command.WriteUICommand;
import board2.comment.DeleteCommentCommand;
import board2.comment.GoodCommentCommand;
import domain.util.Command;
import domain.util.CommandAction;

/**
 * Servlet implementation class Board2
 */
@WebServlet("*.final2")
public class Board2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Board2() {
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

		if (sp.equalsIgnoreCase("/list.final2"))
			com = new ListCommand();
		else if (sp.equalsIgnoreCase("/writeUI.final2"))
			com = new WriteUICommand();
		else if (sp.equalsIgnoreCase("/write.final2"))
			com = new WriteCommand();
		else if (sp.equalsIgnoreCase("/read.final2"))
			com = new ReadCommand();
		else if (sp.equalsIgnoreCase("/updateUI.final2"))
			com = new UpdateUICommand();
		else if (sp.equalsIgnoreCase("/update.final2"))
			com = new UpdateCommand();
		else if (sp.equalsIgnoreCase("/delete.final2"))
			com = new DeleteCommand();
		else if (sp.equalsIgnoreCase("/writeReplyUI.final2"))
			com = new WriteReplyUICommand();
		else if (sp.equalsIgnoreCase("/writeReply.final2"))
			com = new WriteReplyCommand();
		else if (sp.equalsIgnoreCase("/search.final2"))
			com = new SearchCommand();
		else if (sp.equalsIgnoreCase("/writeComment.final2"))
			com = new WriteCommentCommand();
		else if (sp.equalsIgnoreCase("/good.final2"))
			com = new GoodCommand();
		else if (sp.equalsIgnoreCase("/bad.final2"))
			com = new BadCommand();
		else if (sp.equalsIgnoreCase("/goodComment.final2"))
			com = new GoodCommentCommand();
		else if (sp.equalsIgnoreCase("/badComment.final2"))
			com = new BadCommentCommand();
		else if (sp.equalsIgnoreCase("/download.final2"))
			com = new DownloadCommand();
		else if (sp.equalsIgnoreCase("/deleteComment.final2"))
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
