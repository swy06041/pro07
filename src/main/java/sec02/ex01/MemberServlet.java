package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member2")
public class MemberServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		MemberDAO dao = new MemberDAO();

		List<MemberVO> list = dao.listMembers();

		out.print("<html><body>");
		out.print("<table  border=1><tr align='center' bgcolor='lightgreen'>");
		out.print("<td>아이디</td><td>비밀번호</td><td>이름tt</td><td>이메일</td><td>가입일</td></tr>");
		for (int i = 0; i < list.size(); i++) {
			MemberVO memberVO = (MemberVO) list.get(i);
			String userId = memberVO.getUserId();
			String userPass = memberVO.getUserPass();
			String userName = memberVO.getUserName();
			String userEmail = memberVO.getUserEmail();
			Date joinDate = memberVO.getJoinDate();
			out.print("<tr><td>" + userId + "</td><td>" + userPass + "</td><td>" + userName + "</td><td>" + userEmail + "</td><td>"
					+ joinDate + "</td></tr>");
		}
		out.print("</table></body></html>");
		out.print("");
	}

}
