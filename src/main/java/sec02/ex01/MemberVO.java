package sec02.ex01;

import java.sql.Date;

public class MemberVO {

	// 필드 field - 객체의 데이터가 저장되는 곳
	private String userId;
	private String userPass;
	private String userName;
	private String userEmail;
	private Date joinDate;

	// 생성자 - 객체 생성 시 초기화 역할 담당, 일종의 메서드 , 생성자 형식 - 클래스명() { }
	public MemberVO() {
		System.out.println(" Member 생성자 호출됨 !! ");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	// 메서드 - 객체의 동작에 해당하는 실행 블록

	
}
