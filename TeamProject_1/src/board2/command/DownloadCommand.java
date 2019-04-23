package board2.command;

import java.io.File; 
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import board2.domain.Board2DAO;
import domain.util.Command;
import domain.util.CommandAction;

public class DownloadCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sNum = request.getParameter("num");
		int num = -1;
		if (sNum != null) {
			num = Integer.valueOf(sNum);

			Board2DAO dao = new Board2DAO();

			String fileName = dao.download(num);

			ServletContext application = request.getSession().getServletContext();
			String fp = application.getRealPath(File.separator + "upload" + File.separator + fileName);
			String sMimeType = application.getMimeType(fp);
			if (sMimeType == null) {
				sMimeType = "application/octet-stream";
			}
			response.setContentType(sMimeType);

			String encoding = new String(fileName.getBytes("euc-kr"), "8859_1");

			response.setHeader("Content-Disposition", "attachment;filename=" + encoding);

			InputStream in = new FileInputStream(fp);

			ServletOutputStream out = response.getOutputStream();

			byte[] arr = new byte[1024];
			int length;
			while (true) {
				length = in.read(arr, 0, arr.length);
				if (length == -1)
					break;
				out.write(arr, 0, length);
			}

			out.close();
			in.close();

		}

		return new CommandAction(true, "read.final2?num=" + num);
	}

}
