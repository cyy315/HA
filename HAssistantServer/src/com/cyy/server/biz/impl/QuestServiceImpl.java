package com.cyy.server.biz.impl;

import java.util.List;

import com.cyy.server.biz.QuestService;
import com.cyy.server.dao.QuestDao;
import com.cyy.server.dao.impl.QuestDaoImpl;
import com.cyy.server.entity.Quest;

public class QuestServiceImpl implements QuestService {

	public QuestDao questDao = new QuestDaoImpl();
	
	@Override
	public List<Quest> getAllQuest() {
		// TODO Auto-generated method stub
		return questDao.getAllQuest();
	}

	@Override
	public String getCorrectAnswerByQuestid(String questid) {
		// TODO Auto-generated method stub
		return questDao.getCorrectAnswerByQuestid(questid);
	}

}
