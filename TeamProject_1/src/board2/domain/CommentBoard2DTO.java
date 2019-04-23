package board2.domain;

import java.io.Serializable;

public class CommentBoard2DTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4724638302281787639L;
	private int cnum;
	private String content;
	private String writer;
	private String writeDate;
	private int repRoot;
	private int repStep;
	private int repIndent;
	private int good;
	private int bad;
	private int num;

	public CommentBoard2DTO() {
		// TODO Auto-generated constructor stub
	}

	public CommentBoard2DTO(int cnum, String content, String writer, String writeDate, int repRoot, int repStep,
			int repIndent, int good, int bad, int num) {
		super();
		this.cnum = cnum;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.good = good;
		this.bad = bad;
		this.num = num;
	}

	public int getCnum() {
		return cnum;
	}

	public void setCnum(int cnum) {
		this.cnum = cnum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(String writeDate) {
		this.writeDate = writeDate;
	}

	public int getRepRoot() {
		return repRoot;
	}

	public void setRepRoot(int repRoot) {
		this.repRoot = repRoot;
	}

	public int getRepStep() {
		return repStep;
	}

	public void setRepStep(int repStep) {
		this.repStep = repStep;
	}

	public int getRepIndent() {
		return repIndent;
	}

	public void setRepIndent(int repIndent) {
		this.repIndent = repIndent;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public int getBad() {
		return bad;
	}

	public void setBad(int bad) {
		this.bad = bad;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cnum;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommentBoard2DTO other = (CommentBoard2DTO) obj;
		if (cnum != other.cnum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CommentBoard2DTO [cnum=" + cnum + ", content=" + content + ", writer=" + writer + ", writeDate="
				+ writeDate + ", repRoot=" + repRoot + ", repStep=" + repStep + ", repIndent=" + repIndent + ", good="
				+ good + ", bad=" + bad + ", num=" + num + "]";
	}

}
