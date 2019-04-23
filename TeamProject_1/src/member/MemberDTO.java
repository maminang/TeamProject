package member;

import java.io.Serializable;

public class MemberDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2097846100131846184L;
	private String id;
	private String pw;
	private String name;
	private String nick;
	private String birth;
	private String email;
	private String phone;
	private int grade;
	private int point;
	private String pfa;

	public MemberDTO() {
		// TODO Auto-generated constructor stub
	}

	public MemberDTO(String id, String pw, String name, String nick, String birth, String email, String phone,
			int grade, int point, String pfa) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.nick = nick;
		this.birth = birth;
		this.email = email;
		this.phone = phone;
		this.grade = grade;
		this.point = point;
		this.pfa = pfa;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPfa() {
		return pfa;
	}

	public void setPfa(String pfa) {
		this.pfa = pfa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MemberDTO other = (MemberDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", nick=" + nick + ", birth=" + birth
				+ ", email=" + email + ", phone=" + phone + ", grade=" + grade + ", point=" + point + ", pfa=" + pfa
				+ "]";
	}

}
