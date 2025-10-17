package cn.ganggang.chatbot.api.domain.zsxq;

import cn.ganggang.chatbot.api.domain.zsxq.model.aggregates.UnCommentedAggregates;

import java.io.IOException;

public interface IZsxqApi {

    UnCommentedAggregates queryUnCommentedTopicId(String GroupId, String cookie) throws IOException;

    boolean comment(String GroupId, String cookie, String topicId, String text) throws IOException;
}
