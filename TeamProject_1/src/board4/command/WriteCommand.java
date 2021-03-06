package board4.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board4.domain.Board4DAO;
import board4.domain.Board4DTO;
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
		Board4DTO dto = new Board4DTO(-1, title, content, writer, null, 0, -1, -1, -1, -1, -1, -1, -1, fileName, -1,youtube);
		Board4DAO dao = new Board4DAO();
		MemberDAO mdao=new MemberDAO();
		
		dao.write(dto);
		
		mdao.incrGrade(writer);
		
		request.setAttribute("title", title);
		request.setAttribute("writer", writer);
		request.setAttribute("content", content);
		request.setAttribute("fileName", fileName);
		request.setAttribute("youtube", youtube);
	return new CommandAction(false, "list.final4");
	}
}
