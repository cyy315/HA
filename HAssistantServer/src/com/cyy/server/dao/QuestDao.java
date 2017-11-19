package com.cyy.server.dao;

import java.util.List;

import com.cyy.server.entity.Quest;

public interface QuestDao {

	List<Quest> getAllQuest();
	
	String getCorrectAnswerByQuestid(String questid);
	
}
