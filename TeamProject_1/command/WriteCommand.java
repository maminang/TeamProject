package board1.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board1.domain.Board1DAO;
import board1.domain.Board1DTO;
import domain.util.Command;
import domain.util.CommandAction;

public class WriteCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = "";

		String up = request.getSession().getServletContext().getRealPath(File.separator + "upload" + File.separator);

		MultipartRequest multi = new MultipartRequest(request, up, 1024 * 1024 * 10, "euc-kr",
				new DefaultFileRenamePolicy());

		String title = multi.getParameter("title");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		fileName = multi.getFilesystemName("file");

		Board1DTO dto = new Board1DTO(-1, title, content, writer, null, 0, -1, -1, -1, -1, -1, -1, -1, fileName, -1);
		Board1DAO dao = new Board1DAO();

		int crtdNum = dao.write(dto);
		
		return new CommandAction(true, "read.final?num="+crtdNum);
	}

}
