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

public class SearchUICommand implements Command {

	@Override
	public CommandAction execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String keyword=request.getParameter("keyword");
		String searchOption=request.getParameter("searchOption");
		String searchOption2= request.getParameter("searchOption2");
		if (searchOption2==null) {
			searchOption2="writeDate";
		}
		
		String sCurPage = request.getParameter("curPage");
		int curPage = 1;
		if (sCurPage != null)
			curPage = Integer.valueOf(sCurPage);

		DomainDAO dao = new DomainDAO();

		int perPage = 10;
		int amount = dao.getSearchAmount(searchOption, keyword);
		int totalPage = (amount - 1) / perPage + 1;

		int startNum = (curPage - 1) * perPage + 1;
		int endNum = curPage * perPage;
		if (endNum > amount)
			endNum = amount;

		int beginPageNum = (((curPage - 1) / 10) * 10) + 1;
		int stopPageNum = (((curPage - 1) / 10) + 1) * 10;
		if (stopPageNum > totalPage) {
			stopPageNum = totalPage;
		}
		
		List<DomainTO> list= dao.searchList(keyword, searchOption,searchOption2,startNum,endNum);
		
		
		request.setAttribute("list", list);
		request.setAttribute("keyword", keyword);
		request.setAttribute("searchOption", searchOption);
		request.setAttribute("searchOption2", searchOption2);
		
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("curPage", curPage);
		request.setAttribute("beginPageNum", beginPageNum);
		request.setAttribute("stopPageNum", stopPageNum);
		
		return new CommandAction(false, "searchUI.jsp");
	}

}
