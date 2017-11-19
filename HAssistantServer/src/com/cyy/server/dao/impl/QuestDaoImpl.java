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
		// Ҫ���ص�װ��quest�ļ���
		List<Quest> questList = new ArrayList<Quest>();
		Quest quest = null;
		//��ѯ���
		String sql = "select * from tb_quest";
		try {
			//�õ������������
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
			//�ر����ݿ������
			conn.closeConn();
		}
		//���ؽ��
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
		System.out.println("��ѯ��ȷ�𰸵�sql��"+sql);
		
		return ret;
	}

}
