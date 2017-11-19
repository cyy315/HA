package com.cyy.server.biz;

import com.cyy.server.entity.AnswerSheet;

public interface AnswerService {
	/**
	 * ����userid��questid��ѯ�û��Ĵ�����
	 * @param userId �û�id
	 * @param questId ����id
	 * @return ��ѯ���Ĵ��⿨
	 */
	AnswerSheet getAnswerByUidAndQid(String userId, String questId);
	/**
	 * ��answerId��ѯanswer
	 * @param answerId answerid
	 * @return ��Ӧanswerid��AnswerSheet
	 */
	AnswerSheet getAnswerSheetById(String answerId);
	/**
	 * �����ݿ���Ӵ��⿨
	 * @param answersheet Ҫ�ύ�Ĵ��⿨
	 * @return ִ�н��0���ɹ� 1��ʧ��
	 */
	String addAnswerSheet(AnswerSheet answersheet);
	/**
	 * ����
	 * @param answerId
	 * @param answertxt
	 * @return ���½�� 0���ɹ� 1��ʧ��
	 */
	String updateAnswer(String answerId, String answertxt);
}
