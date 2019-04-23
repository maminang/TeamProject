package domain.command;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import domain.util.Command;
import domain.util.CommandAction;
import member.MemberDAO;
import member.MemberDTO;

public class UpdateMemberCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fileName = "";
		String up = request.getSession().getServletContext().getRealPath(File.separator + "upload" + File.separator);

		MultipartRequest multi = new MultipartRequest(request, up, 1024 * 1024 * 10, "euc-kr",
				new DefaultFileRenamePolicy());

		String id = multi.getParameter("id");
		if (id != null) {
			String nick = multi.getParameter("nick");
			String email = multi.getParameter("email");
			String phone = multi.getParameter("phone");
			
			fileName = multi.getFilesystemName("file");

			MemberDTO dto = new MemberDTO(id, null, null, nick, null, email, phone, -1, -1, fileName);
			
			MemberDAO dao = new MemberDAO();
			dao.updateMember(dto);

			return new CommandAction(true, "memberInfo.domain?id=" + id);
		}

		return null;
	}

}
