package com.cyy.server.biz;

import com.cyy.server.entity.AnswerSheet;

public interface AnswerService {
	/**
	 * 根据userid和questid查询用户的答题结果
	 * @param userId 用户id
	 * @param questId 问题id
	 * @return 查询出的答题卡
	 */
	AnswerSheet getAnswerByUidAndQid(String userId, String questId);
	/**
	 * 按answerId查询answer
	 * @param answerId answerid
	 * @return 对应answerid的AnswerSheet
	 */
	AnswerSheet getAnswerSheetById(String answerId);
	/**
	 * 向数据库添加答题卡
	 * @param answersheet 要提交的答题卡
	 * @return 执行结果0：成功 1：失败
	 */
	String addAnswerSheet(AnswerSheet answersheet);
	/**
	 * 更新
	 * @param answerId
	 * @param answertxt
	 * @return 更新结果 0：成功 1：失败
	 */
	String updateAnswer(String answerId, String answertxt);
}
