package com.fans.fansrobot.task;

import catcode.CatCodeUtil;
import com.fans.fansrobot.util.FileReaderUtils;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.timer.Cron;
import love.forte.simbot.timer.EnableTimeTask;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Random;

/**
 * 群消息定时任务
 *
 * @author fans
 * @date 2022/1/1
 */
@EnableTimeTask
@Component
public class GroupTask {

    @Resource
    private BotManager botManager;

    /**
     * 早安
     * 每天早上7点执行
     */
    @Cron("0 0 7 * * ?")
    public void goodMorning() {
        botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(958059391, "早上起来！拥抱太阳！满满正能量！");
        CatCodeUtil util = CatCodeUtil.getInstance();
        String cuteImage = util.getStringCodeBuilder("image", true).key("file").value(FileReaderUtils.CLASS_PATH + "static/images/cute.gif").build();
        botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(958059391, cuteImage);
    }

    /**
     * 饮茶
     * 每天下午3点执行
     */
    @Cron("0 0 15 * * ?")
    public void drinkTea() {
        CatCodeUtil util = CatCodeUtil.getInstance();
        Random random = new Random();
        int a = random.nextInt(2);
        String drinkImg;
        if (a == 0) {
            drinkImg = util.getStringCodeBuilder("image", true).key("file").value(FileReaderUtils.CLASS_PATH + "static/images/drink1.jpg").build();
        } else {
            drinkImg = util.getStringCodeBuilder("image", true).key("file").value(FileReaderUtils.CLASS_PATH + "static/images/drink2.jpg").build();
        }
        botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(958059391, drinkImg);
    }

    /**
     * 名言警句
     * 每天11,19点执行
     */
    @Cron("0 0 11,19 * * ?")
    public void aphorisms() throws IOException {
        long total = FileReaderUtils.getTotalLines("static/aphorisms.txt");
        int lineNum = new Random().nextInt((int) total) + 1;
        botManager.getDefaultBot().getSender().SENDER.sendGroupMsg(958059391, FileReaderUtils.getContentByLine("static/aphorisms.txt", lineNum));
    }
}
