package board2.domain;

import java.util.List;

public class Page2TO {
	private int perPage = 20;
	private int amount;
	private int curPage;
	private int totalPage;
	private List<Board2DTO> list;
	private int startNum;
	private int endNum;
	private int beginPageNum;
	private int stopPageNum;

	public Page2TO() {
		// TODO Auto-generated constructor stub
	}

	public Page2TO(int curPage) {
		super();
		setCurPage(curPage);
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		totalPage = ((amount - 1) / perPage) + 1;

		startNum = (curPage - 1) * perPage + 1;
		endNum = (curPage) * perPage;
		if (endNum > amount)
			endNum = amount;
		
		stopPageNum = (((curPage - 1) / 10) + 1) * 10;
		if (stopPageNum > totalPage)
			stopPageNum = totalPage;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		totalPage = ((amount - 1) / perPage) + 1;

		endNum = (curPage) * perPage;
		if (endNum > amount)
			endNum = amount;
		
		stopPageNum = (((curPage - 1) / 10) + 1) * 10;
		if (stopPageNum > totalPage)
			stopPageNum = totalPage;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;		

		startNum = (curPage - 1) * perPage + 1;
		endNum = (curPage) * perPage;
		if (endNum > amount)
			endNum = amount;

		beginPageNum = (((curPage - 1) / 10) * 10) + 1;
		stopPageNum = (((curPage - 1) / 10) + 1) * 10;
		if (stopPageNum > totalPage)
			stopPageNum = totalPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		stopPageNum = (((curPage - 1) / 10) + 1) * 10;
		if (stopPageNum > totalPage)
			stopPageNum = totalPage;
	}

	public List<Board2DTO> getList() {
		return list;
	}

	public void setList(List<Board2DTO> list) {
		this.list = list;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getBeginPageNum() {
		return beginPageNum;
	}

	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}

	public int getStopPageNum() {
		return stopPageNum;
	}

	public void setStopPageNum(int stopPageNum) {
		this.stopPageNum = stopPageNum;
	}

	@Override
	public String toString() {
		return "Page2TO [perPage=" + perPage + ", amount=" + amount + ", curPage=" + curPage + ", totalPage=" + totalPage
				+ ", list=" + list + ", startNum=" + startNum + ", endNum=" + endNum + ", beginPageNum=" + beginPageNum
				+ ", stopPageNum=" + stopPageNum + "]";
	}

}
