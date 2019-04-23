package board2.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board2.domain.Board2DAO;
import board2.domain.Board2DTO;
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
		Board2DTO dto = new Board2DTO(-1, title, content, writer, null, 0, -1, -1, -1, -1, -1, -1, -1, fileName, -1,youtube);
		Board2DAO dao = new Board2DAO();
		MemberDAO mdao=new MemberDAO();
		System.out.println("write "+youtube);
		dao.write(dto);
		
		mdao.incrGrade(writer);
		
		request.setAttribute("title", title);
		request.setAttribute("writer", writer);
		request.setAttribute("content", content);
		request.setAttribute("fileName", fileName);
		request.setAttribute("youtube", youtube);
	return new CommandAction(false, "list.final2");
	}
}
