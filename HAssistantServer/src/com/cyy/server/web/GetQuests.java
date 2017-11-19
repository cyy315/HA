package com.cyy.server.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.server.biz.QuestService;
import com.cyy.server.biz.impl.QuestServiceImpl;
import com.cyy.server.entity.Quest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GetQuests
 */
@WebServlet("/GetQuests")
public class GetQuests extends HttpServlet {
	private static final long serialVersionUID = 1L;
    QuestService questService = new QuestServiceImpl();   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetQuests() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//获取需要quest列表
		List<Quest> questList = questService.getAllQuest();
		try{
			//json生成器，不需要未注解的属性
			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			
			JsonObject quests = new JsonObject();
			//输出结果
			quests.add("quests", gson.toJsonTree(questList));
			out.println(quests);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
