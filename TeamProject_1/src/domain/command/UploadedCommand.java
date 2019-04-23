package domain.command;

import java.io.IOException; 
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.DomainDAO;
import domain.DomainTO;
import domain.util.Command;
import domain.util.CommandAction;

public class UploadedCommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sCurPage = request.getParameter("curPage");
		int curPage = 1;
		if (sCurPage != null)
			curPage = Integer.valueOf(sCurPage);

		DomainDAO dao = new DomainDAO();

		int perPage = 10;
		int amount = dao.getAmountUploaded();
		int totalPage = (amount - 1) / perPage + 1;

		int startNum = (curPage - 1) * perPage + 1;
		int endNum = curPage * perPage;
		if (endNum > amount)
			endNum = amount;

		List<DomainTO> list = dao.uploadedList(curPage, startNum, endNum);

		int beginPageNum = (((curPage - 1) / 10) * 10) + 1;
		int stopPageNum = (((curPage - 1) / 10) + 1) * 10;
		if (stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}

		request.setAttribute("totalPage", totalPage);
		request.setAttribute("list", list);
		request.setAttribute("curPage", curPage);
		request.setAttribute("beginPageNum", beginPageNum);
		request.setAttribute("stopPageNum", stopPageNum);

		return new CommandAction(false, "uploaded.jsp");
	}

}
