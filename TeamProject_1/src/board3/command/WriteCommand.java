package board3.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board3.domain.Board3DAO;
import board3.domain.Board3DTO;
import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;

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
		String youtube = multi.getParameter("youtube");
		Board3DTO dto = new Board3DTO(-1, title, content, writer, null, 0, -1, -1, -1, -1, -1, -1, -1, fileName, -1,youtube);
		Board3DAO dao = new Board3DAO();
		MemberDAO mdao=new MemberDAO();
		
		int crtdNum = dao.write(dto);
		
		mdao.incrGrade(writer);
		
		request.setAttribute("title", title);
		request.setAttribute("writer", writer);
		request.setAttribute("content", content);
		request.setAttribute("fileName", fileName);
		request.setAttribute("youtube", youtube);
	return new CommandAction(false, "list.final3");
	}
}
