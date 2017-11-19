package com.cyy.server.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.cyy.server.biz.UserService;
import com.cyy.server.biz.impl.UserServiceImpl;
import com.cyy.server.entity.User;


/**
 * Servlet implementation class Login
 */
@WebServlet("/DoLogin")
public class DoLogin extends HttpServlet {
	
	//一般开发不能直接调用DAO层
	UserService userService = new UserServiceImpl();
	User user = null;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		String result = "";
		
		try {
			String username = request.getParameter("user");
			String password = request.getParameter("pass");
			System.out.println(password);
			user = userService.getUserByName(username);
			System.out.println(user.getPass());
			
			if(user!=null&&password.equals(user.getPass())){
				JSONObject ret = new JSONObject();
				ret.put("userId", user.getUserId());
				
				session.setAttribute("currentUser", user);
				result = ret.toString();
			}else{
				JSONObject ret = new JSONObject();
				ret.put("userId", "0");
				result = ret.toString();
			}
			out.println(result);

			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();
		String result = "";
		
		JSONObject jo;
		try {
			jo = new JSONObject(request.getParameter("param"));
			String username = jo.getString("user");
			String password = jo.getString("pass");
			
			user = userService.getUserByName(username);
			if(user != null && password.equals(user.getPass())){
				JSONObject ret = new JSONObject();
				ret.put("userId", user.getUserId());
				
				session.setAttribute("currenUser", user);
				result = ret.toString();
				
			}else{
				JSONObject ret = new JSONObject();
				ret.put("userId", "0");
				result = ret.toString();

			}
			
			out.println(result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		out.flush();
		out.close();
	}

}
