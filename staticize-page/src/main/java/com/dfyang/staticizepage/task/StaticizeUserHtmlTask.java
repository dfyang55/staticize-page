package com.dfyang.staticizepage.task;

import com.dfyang.staticizepage.utils.StaticizePageUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 静态化用户界面
 */
@Component
public class StaticizeUserHtmlTask {

    @Value("${custom.user.url}")
    private String url;

    @Value("${custom.user.file}")
    private String file;

    @Scheduled(fixedRate = 300000)
    public void generateStaticJSP() {
        System.out.printf("生成静态页面");
        StaticizePageUtil.staticize(url, file);
    }

}
