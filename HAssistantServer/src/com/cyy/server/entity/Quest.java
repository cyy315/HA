package com.cyy.server.entity;

import com.google.gson.annotations.Expose;

public class Quest {

	@Expose
	private String questId;
	@Expose
	private String questName;
	@Expose
	private String questDescription;
	@Expose
	private String questFileUrl;

	private String correctAnswer;

	public String getQuestId() {
		return questId;
	}

	public void setQuestId(String questId) {
		this.questId = questId;
	}

	public String getQuestName() {
		return questName;
	}

	public void setQuestName(String questName) {
		this.questName = questName;
	}

	public String getQuestDescription() {
		return questDescription;
	}

	public void setQuestDescription(String questDescription) {
		this.questDescription = questDescription;
	}

	public String getQuestFileUrl() {
		return questFileUrl;
	}

	public void setQuestFileUrl(String questFileUrl) {
		this.questFileUrl = questFileUrl;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}
