package com.fans.fansrobot.listener;

import catcode.CatCodeUtil;
import com.fans.fansrobot.util.FileReaderUtils;
import love.forte.simbot.annotation.Filter;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.MsgSender;
import love.forte.simbot.filter.MatchType;
import org.springframework.stereotype.Component;

/**
 * 群聊消息监听器
 *
 * @author fans
 * @date 2022/1/1
 */
@Component
public class GroupMsgListener {

    @OnGroup
    @Filter(value = "狗鑫", matchType = MatchType.CONTAINS)
    public void hjx(GroupMsg msg, MsgSender msgSender) {
        CatCodeUtil util = CatCodeUtil.getInstance();
        String hjxImage = util.getStringCodeBuilder("image", true).key("file").value(FileReaderUtils.CLASS_PATH + "static/images/hjx.jpg").build();
        msgSender.SENDER.sendGroupMsg(msg, "[CAT:at,code=" + msg.getAccountInfo().getAccountCode() + "] " + hjxImage);
    }

    @OnGroup
    @Filter(value = "15", matchType = MatchType.CONTAINS)
    public void cjw(GroupMsg msg, MsgSender msgSender) {
        CatCodeUtil util = CatCodeUtil.getInstance();
        String cjwImage = util.getStringCodeBuilder("image", true).key("file").value(FileReaderUtils.CLASS_PATH + "static/images/154gou.jpg").build();
        msgSender.SENDER.sendGroupMsg(msg, "[CAT:at,code=" + msg.getAccountInfo().getAccountCode() + "] " + cjwImage);
    }

    @OnGroup
    @Filter("粘贴板")
    public void paste(GroupMsg msg, MsgSender msgSender) {
        msgSender.SENDER.sendGroupMsg(msg, "[CAT:at,code=" + msg.getAccountInfo().getAccountCode() + "]粘贴板：\nhttps://paste.ubuntu.com/");
    }

    @OnGroup
    @Filter(value = "合肥市人才政策", matchType = MatchType.CONTAINS)
    public void talentsPolicy(GroupMsg msg, MsgSender msgSender) {
        msgSender.SENDER.sendGroupMsg(msg, "[CAT:at,code=" + msg.getAccountInfo().getAccountCode() + "]合肥市人才政策：\nhttps://docs.qq.com/doc/DZEJEQXdCRkZiQlV1");
    }

    @OnGroup
    @Filter(value = "牛马下班", matchType = MatchType.CONTAINS)
    @Filter(value = "下班了", matchType = MatchType.CONTAINS)
    @Filter(value = "下班啦", matchType = MatchType.CONTAINS)
    public void xb(GroupMsg msg, MsgSender msgSender) {
        CatCodeUtil util = CatCodeUtil.getInstance();
        String xklImage = util.getStringCodeBuilder("image", true).key("file").value(FileReaderUtils.CLASS_PATH + "static/images/xkl.jpg").build();
        msgSender.SENDER.sendGroupMsg(msg, "[CAT:at,code=" + msg.getAccountInfo().getAccountCode() + "] " + xklImage);
    }
}
