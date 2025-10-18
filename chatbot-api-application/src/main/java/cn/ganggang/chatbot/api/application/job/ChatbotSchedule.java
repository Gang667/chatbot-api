package cn.ganggang.chatbot.api.application.job;

import cn.ganggang.chatbot.api.domain.ai.IDeepSeek;
import cn.ganggang.chatbot.api.domain.zsxq.IZsxqApi;
import cn.ganggang.chatbot.api.domain.zsxq.model.aggregates.UnCommentedAggregates;
import cn.ganggang.chatbot.api.domain.zsxq.model.vo.Topics;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

/**
 * 问答任务
 */
@EnableScheduling
@Configuration
public class ChatbotSchedule {

    private Logger logger = LoggerFactory.getLogger(ChatbotSchedule.class);

    @Value("${chatbot-api.groupId}")
    private String groupId;
    @Value("${chatbot-api.cookie}")
    private String cookie;

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IDeepSeek deepSeek;

    @Scheduled(cron = "0/5 * * * * ?")
    public void run() {
        try {
            if(new Random().nextBoolean()) {
                logger.info("随机打烊中……");
                return;
            }

            GregorianCalendar calendar = new GregorianCalendar();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if(hour > 22 || hour < 7) {
                logger.info("AI下班了！");
                return;
            }

            // 1.检索问题
            UnCommentedAggregates unCommentedAggregates = zsxqApi.queryUnCommentedTopicId(groupId, cookie);
            logger.info("测试结果：{}", JSON.toJSONString(unCommentedAggregates));
            List<Topics> topics = unCommentedAggregates.getResp_data().getTopics();
            if(null == topics || topics.isEmpty()) {
                logger.info("本次检索未查询到待回答问题");
                return;
            }

            // 2.AI回答
            Topics topic = new Topics();
            for (int i = 0; i < topics.size(); i++) {
                topic = topics.get(i);
                if(topic.getComments_count() == 0 && topic.getTalk().getOwner().getName().equals("岗岗")) {
                    break;
                }
                if(i == topics.size() - 1) {
                    logger.info("本次检索未查询到待回答问题");
                    return;
                }
            }
            String answer = deepSeek.doChatGPT(topic.getTalk().getText().trim());

            // 3.问题回复
            boolean status = zsxqApi.comment(groupId, cookie, topic.getTopic_id(), answer);
            logger.info("编号: {} 问题: {} 回答: {} 状态: {}", topic.getTopic_id(), topic.getTalk().getText(), answer, status);

        }
        catch(Exception e){
            logger.error("自动回答问题异常", e);
        }
    }

}
