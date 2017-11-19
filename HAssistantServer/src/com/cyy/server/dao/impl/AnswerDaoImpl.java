package com.cyy.server.dao.impl;

import java.sql.ResultSet;
import java.text.MessageFormat;

import com.cyy.server.dao.AnswerDao;
import com.cyy.server.db.DBConn;
import com.cyy.server.entity.AnswerSheet;

public class AnswerDaoImpl implements AnswerDao {

	DBConn conn = new DBConn();

	@Override
	public AnswerSheet getAnswerByUidAndQid(String userId, String questId) {
		// TODO Auto-generated method stub
		AnswerSheet as = null;
		try {
			String sql = MessageFormat
					.format("select * from tb_answer where user_id={0} and quest_id={1}",
							userId, questId);
			ResultSet rs = conn.execQuery(sql, null);
			while (rs.next()) {
				as = new AnswerSheet();
				as.setQuestId(questId);
				as.setUserId(userId);
				as.setAnswerId("" + rs.getInt("answer_id"));
				as.setAnswer(rs.getString("answer"));
			}
			System.out.println("getAnswerByUidAndQid : " + sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conn.closeConn();
		}

		return as;
	}

	@Override
	public AnswerSheet getAnswerSheetById(String answerId) {
		// TODO Auto-generated method stub

		AnswerSheet as = null;
		try {
			String sql = MessageFormat.format(
					"select * from tb_answer where user_id={0}", answerId);
			ResultSet rs = conn.execQuery(sql, null);
			while (rs.next()) {
				as = new AnswerSheet();
				as.setAnswerId(answerId);
				as.setQuestId("" + rs.getInt("quest_id"));
				as.setUserId("" + rs.getInt("user_id"));
				as.setAnswer(rs.getString("answer"));
			}
			System.out.println("getAnswerSheetById : " + sql);
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			conn.closeConn();
		}
		return as;
	}

	@Override
	public String addAnswerSheet(AnswerSheet answersheet) {
		// TODO Auto-generated method stub

		String answerId = answersheet.getAnswerId();
		String answer = answersheet.getAnswer();
		String questId = answersheet.getQuestId();
		String userId = answersheet.getUserId();
		String sql = MessageFormat
				.format("insert into tb_answer(user_id,quest_id,answer) values({0},{1},\"{2}\")",
						userId, questId, answer);
		System.out.println("insert sql: " + sql);
		int ret = conn.execOther(sql, null);

		if (ret >= 1) {

			return "0";
		} else {
			return "1";
		}

	}

	@Override
	public String updateAnswer(String answerId, String answertxt) {
		// TODO Auto-generated method stub
		String sql = MessageFormat.format("update tb_answer set answer=\"{0}\" where answer_id={1}", answertxt,answerId);
		System.out.println("updatesql: "+sql);
		int ret = conn.execOther(sql, null);
		if(ret>=1){
			return "0";
		}else{
			return "1";
		}
		
	}

}
