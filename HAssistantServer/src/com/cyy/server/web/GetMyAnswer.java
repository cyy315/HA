package com.cyy.server.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.cyy.server.biz.AnswerService;
import com.cyy.server.biz.impl.AnswerServiceImpl;
import com.cyy.server.entity.AnswerSheet;
import com.cyy.server.entity.User;

	
/**
 * Servlet implementation class GetMyAnswer
 */
@WebServlet("/GetMyAnswer")
public class GetMyAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//业务逻辑层的一个对象
	AnswerService answerServive = new AnswerServiceImpl();
	AnswerSheet as = new AnswerSheet();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetMyAnswer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");

		String userId = request.getParameter("uid");
		String questId = request.getParameter("questId");

		try {
			JSONObject ret = new JSONObject();
			if (currentUser != null && userId.equals(currentUser.getUserId())) {
				as = answerServive.getAnswerByUidAndQid(userId, questId);
				ret.put("myAnswer", new JSONObject(URLDecoder.decode(as.getAnswer(),"utf-8")));
			}else{
				ret.put("myAnswer", "please login");
			}
			out.println(ret);
		} catch (Exception e) {
			// TODO: handle exception
		}

		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//得到session
		HttpSession session = request.getSession();
		//通过session对象获取当前登录的用户
		
		User currentUser = (User) session.getAttribute("currentUser");
		
		try {
			//解析post传过来的json参数
			JSONObject obj = new JSONObject(request.getParameter("param"));
			String userId = obj.getString("uid");
			String questId = obj.getString("questId");
			JSONObject ret = new JSONObject();
			
			//判断是否有用户登录而且登录用户的id跟传过来的userid是否相同
			if(currentUser!=null&&userId.equals(currentUser.getUserId())){
				as = answerServive.getAnswerByUidAndQid(userId, questId);
				ret.put("myAnswer", new JSONObject(URLDecoder.decode(as.getAnswer(),"utf-8")));//将数据库中被urldecoder转换的字符串还原
				out.println(ret);
			}else{
				/*ret.put("myAnswer", "please login");
				out.println("404");*/
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		out.flush();
		out.close();
	}

}
