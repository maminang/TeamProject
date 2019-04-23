package domain;

import java.io.Serializable;

public class DomainTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1713297374938913863L;
	private int num;
	private String title;
	private String content;
	private String writer;
	private String writeDate;
	private int readCnt;
	private int repRoot;
	private int repStep;
	private int repIndent;
	private int good;
	private int bad;
	private int grade;
	private int up_num;
	private String up_adress;
	private String youtube;
	private String tblName;
	private int CommentCnt;
	

	public DomainTO() {
		// TODO Auto-generated constructor stub
	}


	public DomainTO(int num, String title, String content, String writer, String writeDate, int readCnt, int repRoot,
			int repStep, int repIndent, int good, int bad, int grade, int up_num, String up_adress, String youtube,
			String tblName, int commentCnt) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.writeDate = writeDate;
		this.readCnt = readCnt;
		this.repRoot = repRoot;
		this.repStep = repStep;
		this.repIndent = repIndent;
		this.good = good;
		this.bad = bad;
		this.grade = grade;
		this.up_num = up_num;
		this.up_adress = up_adress;
		this.youtube = youtube;
		this.tblName = tblName;
		CommentCnt = commentCnt;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
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


	public int getReadCnt() {
		return readCnt;
	}


	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
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


	public int getGrade() {
		return grade;
	}


	public void setGrade(int grade) {
		this.grade = grade;
	}


	public int getUp_num() {
		return up_num;
	}


	public void setUp_num(int up_num) {
		this.up_num = up_num;
	}


	public String getUp_adress() {
		return up_adress;
	}


	public void setUp_adress(String up_adress) {
		this.up_adress = up_adress;
	}


	public String getYoutube() {
		return youtube;
	}


	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}


	public String getTblName() {
		return tblName;
	}


	public void setTblName(String tblName) {
		this.tblName = tblName;
	}


	public int getCommentCnt() {
		return CommentCnt;
	}


	public void setCommentCnt(int commentCnt) {
		CommentCnt = commentCnt;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		result = prime * result + ((tblName == null) ? 0 : tblName.hashCode());
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
		DomainTO other = (DomainTO) obj;
		if (num != other.num)
			return false;
		if (tblName == null) {
			if (other.tblName != null)
				return false;
		} else if (!tblName.equals(other.tblName))
			return false;
		return true;
	}

	
}
