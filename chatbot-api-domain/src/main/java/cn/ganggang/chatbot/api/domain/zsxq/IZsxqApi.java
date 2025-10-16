package cn.ganggang.chatbot.api.domain.zsxq;

import cn.ganggang.chatbot.api.domain.zsxq.model.aggregates.UnAnsweredQuestionsAggregates;

import java.io.IOException;

public interface IZsxqApi {

    UnAnsweredQuestionsAggregates queryUnAnsweredQuestionsTopicId(String GroupId, String cookie) throws IOException;

    boolean answer(String GroupId, String cookie, String topicId, String text, boolean silenced) throws IOException;
}
