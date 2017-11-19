package com.cyy.server.biz.impl;

import com.cyy.server.biz.AnswerService;
import com.cyy.server.dao.AnswerDao;
import com.cyy.server.dao.impl.AnswerDaoImpl;
import com.cyy.server.entity.AnswerSheet;

public class AnswerServiceImpl implements AnswerService {

	AnswerDao answerDao = new AnswerDaoImpl();
	@Override
	public AnswerSheet getAnswerByUidAndQid(String userId, String questId) {
		// TODO Auto-generated method stub
		return answerDao.getAnswerByUidAndQid(userId, questId);
	}

	@Override
	public AnswerSheet getAnswerSheetById(String answerId) {
		// TODO Auto-generated method stub
		return answerDao.getAnswerSheetById(answerId);
	}

	@Override
	public String addAnswerSheet(AnswerSheet answersheet) {
		// TODO Auto-generated method stub
		return answerDao.addAnswerSheet(answersheet);
	}

	@Override
	public String updateAnswer(String answerId, String answertxt) {
		// TODO Auto-generated method stub
		return answerDao.updateAnswer(answerId, answertxt);
	}

}
