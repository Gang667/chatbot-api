package cn.ganggang.chatbot.api.domain.ai;

/**
 * 调用ai的接口
 */
public interface IOpenAI {

    String doChatGPT(String question) throws Exception;

}
