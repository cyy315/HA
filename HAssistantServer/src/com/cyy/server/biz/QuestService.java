package com.cyy.server.biz;

import java.util.List;

import com.cyy.server.entity.Quest;

public interface QuestService {
	List<Quest> getAllQuest();

	String getCorrectAnswerByQuestid(String questid);
}
