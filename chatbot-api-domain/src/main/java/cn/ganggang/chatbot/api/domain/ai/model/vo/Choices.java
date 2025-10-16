package cn.ganggang.chatbot.api.domain.ai.model.vo;

/**
 * 选择：调用哪个模型，怎么调用的
 */
public class Choices {

    private int index;

    private DeepSeekMessage message;

    private String logprobs;

    private String finish_reason;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DeepSeekMessage getMessage() {
        return message;
    }

    public void setMessage(DeepSeekMessage message) {
        this.message = message;
    }

    public String getLogprobs() {
        return logprobs;
    }

    public void setLogprobs(String logprobs) {
        this.logprobs = logprobs;
    }

    public String getFinish_reason() {
        return finish_reason;
    }

    public void setFinish_reason(String finish_reason) {
        this.finish_reason = finish_reason;
    }
}
