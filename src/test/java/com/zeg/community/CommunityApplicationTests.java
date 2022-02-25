package com.zeg.community;

import com.zeg.community.entity.DiscussPost;
import com.zeg.community.entity.User;
import com.zeg.community.mapper.DiscussPostMapper;
import com.zeg.community.mapper.UserMapper;
import com.zeg.community.service.DiscussPostService;
import com.zeg.community.service.UserService;
import com.zeg.community.util.CommunityUtil;
import com.zeg.community.util.HostHolder;
import com.zeg.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CommunityApplicationTests {
    @Autowired
    private DiscussPostService discussPostService;

    @Test
    public void text(){

        DiscussPost post = new DiscussPost();
        post.setUserId(156);
        post.setTitle("title");
        post.setContent("content");
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

    }

}
