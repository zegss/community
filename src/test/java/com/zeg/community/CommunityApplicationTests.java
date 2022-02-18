package com.zeg.community;

import com.zeg.community.entity.DiscussPost;
import com.zeg.community.entity.User;
import com.zeg.community.mapper.DiscussPostMapper;
import com.zeg.community.mapper.UserMapper;
import com.zeg.community.service.UserService;
import com.zeg.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailClient mailClient;

    @Value("${community.path.domain}")
    private String domain;

    @Test
    public void text(){
        Context context = new Context();
        context.setVariable("email", "491094578@qq.com");
        // http://localhost:8081/activation/101/code
        String url = domain  + "/activation/" + "101" + "/" + "1111";
        context.setVariable("url", url);
        System.out.println(url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail("491094578@qq.com", "激活账号", content);

    }

}
