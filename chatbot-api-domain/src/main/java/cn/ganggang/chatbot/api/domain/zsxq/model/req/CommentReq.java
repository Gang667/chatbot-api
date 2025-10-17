package cn.ganggang.chatbot.api.domain.zsxq.model.req;

/**
 * 请求问答接口信息
 */
public class CommentReq {

    private ReqData req_data;

    public CommentReq(ReqData req_data) {
        this.req_data = req_data;
    }

    public ReqData getReq_data() {
        return req_data;
    }

    public void setReq_data(ReqData req_data) {
        this.req_data = req_data;
    }
}
