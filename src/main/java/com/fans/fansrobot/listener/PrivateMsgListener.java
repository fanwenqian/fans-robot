package com.fans.fansrobot.listener;

import com.fans.fansrobot.util.FileReaderUtils;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnPrivate;
import love.forte.simbot.annotation.Priority;
import love.forte.simbot.api.message.events.PrivateMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.constant.PriorityConstant;
import love.forte.simbot.filter.MatchType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Random;

/**
 * 私聊消息监听器
 *
 * @author fans
 * @date 2021/12/31
 */
@Component
public class PrivateMsgListener {

    /**
     * 监听私聊并随机发送一行文本（优先级：5）
     * @param privateMsg 私聊消息
     * @param sender 送信器
     * @throws IOException io异常
     */
    @Priority(PriorityConstant.FIFTH)
    @OnPrivate
    @Filter(value = "^(?!机器人)$",matchType = MatchType.REGEX_MATCHES)
    @Filter(value = "^(?!？)$",matchType = MatchType.REGEX_MATCHES)
    public void aphorisms(PrivateMsg privateMsg, MsgSender sender) throws IOException {
        long total = FileReaderUtils.getTotalLines("static/aphorisms.txt");
        int lineNum = new Random().nextInt((int) total) + 1;
        sender.SENDER.sendPrivateMsg(privateMsg, FileReaderUtils.getContentByLine("static/aphorisms.txt",lineNum));
    }

    /**
     * 监听私聊并自我介绍 （优先级：3）
     * @param privateMsg 私聊消息
     * @param sender 送信器
     */
    @Priority(PriorityConstant.THIRD)
    @OnPrivate
    @Filter(value = "机器人",matchType = MatchType.CONTAINS)
    public void robotReply(PrivateMsg privateMsg, MsgSender sender) {
        sender.SENDER.sendPrivateMsg(privateMsg, "是的呢，我是一名机器人，这是主人的qq号：1136568063，您有什么疑问可以直接找他哦！");
    }

    /**
     * 监听私聊并回复 （优先级：4）
     * @param privateMsg 私聊消息
     * @param sender 送信器
     */
    @Priority(PriorityConstant.THIRD)
    @OnPrivate
    @Filter(value = "？",matchType = MatchType.CONTAINS)
    public void robotReply2(PrivateMsg privateMsg, MsgSender sender) {
        sender.SENDER.sendPrivateMsg(privateMsg, "您好呀，我是一名机器人，并不具备智能回复的能力呢，这是主人的qq号：1136568063，您有什么疑问可以直接找他哦！");
    }
}
