package com.cyy.server.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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
 * Servlet implementation class CommitAnswer
 */
@WebServlet("/CommitAnswer")
public class CommitAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AnswerService answerService = new AnswerServiceImpl();
	AnswerSheet answerSheet = null;
	String userId = null;
	String questId = null;
	String answertxt = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommitAnswer() {
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

		// session 获取登录用户
		HttpSession session = request.getSession();
		User currentUser = (User) session.getAttribute("currentUser");

		// 执行结果
		String exeret = "";

		try {
			// 解析post过来的json参数

			JSONObject obj = new JSONObject(request.getParameter("param"));
			userId = obj.getString("uid");
			questId = obj.getString("questId");
			answertxt = URLEncoder.encode(obj.getString("myAnswer"), "UTF-8");
			System.out.println(answertxt);
			if (currentUser != null && userId.equals(currentUser.getUserId())) {

				// 先执行查询是否该用户已经有这道题的答案
				answerSheet = answerService.getAnswerByUidAndQid(userId,
						questId);

				// 没有则向数据库添加
				if (answerSheet == null) {
					answerSheet = new AnswerSheet();
					answerSheet.setUserId(userId);
					answerSheet.setQuestId(questId);
					answerSheet.setAnswer(answertxt);
					exeret = answerService.addAnswerSheet(answerSheet);

				} else {
					// 已有则更新该条数据的answer
					exeret = answerService.updateAnswer(
							answerSheet.getUserId(), answertxt);
				}

				// 输出结果json
				JSONObject ret = new JSONObject();
				// 将执行结果填充到输出json中
				ret.put("isCorrect", exeret);
				out.println(ret);
			} else {

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		out.flush();
		out.close();
	}

}
