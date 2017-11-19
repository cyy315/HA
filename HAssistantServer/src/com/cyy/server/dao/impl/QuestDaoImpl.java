package com.cyy.server.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import com.cyy.server.dao.QuestDao;
import com.cyy.server.db.DBConn;
import com.cyy.server.entity.Quest;

public class QuestDaoImpl implements QuestDao {
	
	DBConn conn = new DBConn();

	@Override
	public List<Quest> getAllQuest() {
		// 要返回的装有quest的集合
		List<Quest> questList = new ArrayList<Quest>();
		Quest quest = null;
		//查询语句
		String sql = "select * from tb_quest";
		try {
			//得到结果集并遍历
			ResultSet rs = conn.execQuery(sql,null);
			while(rs.next()){
				quest = new Quest();
				quest.setQuestId(""+rs.getInt("quest_id"));
				quest.setQuestName(rs.getString("quest_name"));
				quest.setQuestDescription(rs.getString("quest_description"));
				quest.setQuestFileUrl(rs.getString("quest_fileurl"));
				quest.setCorrectAnswer(rs.getString("correct_answer"));
				questList.add(quest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//关闭数据库的连接
			conn.closeConn();
		}
		//返回结果
		return questList;
	}

	@Override
	public String getCorrectAnswerByQuestid(String questid) {
		// TODO Auto-generated method stub
		String ret = "";
		String sql = MessageFormat.format("select correct_answer from tb_quest where quest_id={0}", questid);
		ResultSet rs = conn.execQuery(sql,null);
		try {
			while(rs.next()){
				ret = rs.getString("correct_answer");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("查询正确答案的sql："+sql);
		
		return ret;
	}

}
