package sec01.ex01;

import java.sql.Connection; // 특정 데이터베이스에 연결
import java.sql.Date;
import java.sql.DriverManager;	// JDBC 드라이버를 관리하기 위한 기본 클래스
import java.sql.PreparedStatement; // SQL문을 미리 컴파일 하기 위한 클래스
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
//	필
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";	// jdbc:사용할 JDBC드라이버(oracle, mysql):드라이버 타입:@서버이름(ip):포트번호:DB서비스아이디
	private static final String user = "scott";
	private static final String pwd = "12341234";
//	생
//	Statement stmt;	// Statement 클래스는 데이터베이스 연결로부터 SQL문을 수행 할 수 있도록 해주는 클래스
	PreparedStatement pstmt;	// SQL문을 미리 만들어 두고 변수를 따로 입력하는 방식
	ResultSet rs;
	Connection conn;
 
//	메
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();

		connDB();

		String query = "SELECT * FROM t_member";
		System.out.println(query);

		try {
//			rs = pstmt.executeQuery(query);
			pstmt = conn.prepareStatement(query); // 
			rs = pstmt.executeQuery();	// SELECT문을 수행 할 때  사용한다. 반환 값은 ResultSet 클래스의 인스턴스로, 해당 SELECT문의 결과에 해당하는 데이터에 접근할 수 있는 방법을 제공
			// rs.next() 은 한줄씩 커서(결과가 여러개이므로 맨 위에 줄부터 접근하게 하는 포인터) 를 이동함
			// ResultSet 은 객체이므로 다시 풀어주는 과정을 코딩
			// ResultSet 은 데이터베이스 내부적으로 수행된 SQL문의 처리 결과를 JDBC에서 쉽게 관리 할 수 있도록 해줌 ResultSet은 next()메서드를 이용해서 다음 row(행) 로 이동 할 수 있다.
			// 커서를 최초 데이터 위치로 이동시키려면, ResultSet 을 사용하기 전에 rs.next()메서드를 한 번 호출 해 줄 필요가 있다. 
			// 대부분의 경우 executeQuery()를 수행한 후 while (rs.next())과 같이 더이상 로우가 없을 때 까지 루프를 돌면서 데이터를 처리하는 방법을 이용
			while (rs.next()) {
//				String userId = rs.getString("userId"); // 칼럼 이름(ID)에 해당하는 값을 가져옴
				String userId = rs.getString(1);	//the first column is 1, the second is 2, ... 
				String userPass = rs.getString("userPass");
				String userName = rs.getString("userName");
				String userEmail = rs.getString("userEmail");
				Date joinDate = rs.getDate("joinDate");
//
//				System.out.println(userId +userPass+userName+userEmail+joinDate);
//				
//				System.out.println("============================");
//				

				MemberVO vo = new MemberVO();
				vo.setUserId(userId);
				vo.setUserPass(userPass);
				vo.setUserName(userName);
				vo.setUserEmail(userEmail);
				vo.setJoinDate(joinDate);

				list.add(vo);
			}

			rs.close();	// 자원이므로 닫아줘야함
			pstmt.close();
			conn.close(); // 데이터베이스와의 연결을 관리하는 connection인스턴스는 사용한 뒤 반납하지 않으면 계속 연결을 유지하므로
			// 어느 시점에서는 데이터베이스연결이 부족한 상황이 발생 할 수 있다. 따라서 사용자가 많은 시스템일수록 커넥션 관리가 중요한데, 이를 위해 커넥션 풀을 이용함.
			// 커넥션 풀의 사용여부와는 상관 없이 데이터베이스 연결은 해당 연결을 통한 작업이 모두 끝나는 시점에서 close메서드를 이용해 해제하는것이 좋다
			
//			ResultSet은 객체 이므로 다시 풀어주는 과정을 코딩

		} catch (Exception e) {
			System.out.println("SQL 문장을 돌리는데 문제가 생김");
		}
		return list;
	}

	private void connDB() {
		try {
			Class.forName(driver); // 해당 드라이버 클래스를 불러옴
			System.out.println("Oracle 드라이버 로딩 성공");
			conn = DriverManager.getConnection(url, user, pwd); // Attempts to establish a connection to the
			System.out.println("Connection 생성 성공"); // given database URL.

//			stmt = conn.createStatement(); // Creates a Statement object for sendingSQL statements to the
			System.out.println("Statement 생성 성공"); // database.

		} catch (Exception e) {

			System.out.println("DB 연결에 문제가 생김");
			e.printStackTrace();

		}

	}
}