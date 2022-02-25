package com.zeg.community.controller;

import com.zeg.community.entity.User;
import com.zeg.community.service.FollowService;
import com.zeg.community.util.CommunityUtil;
import com.zeg.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {
    @Autowired
    private FollowService followService;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/follow")
    @ResponseBody
    public String follow(int entityType, int entityId){
        User user = hostHolder.getUser();
        followService.follow(user.getId(), entityType,entityId);
        return CommunityUtil.getJSONString(0,"已关注");
    }

    @PostMapping("/unfollow")
    @ResponseBody
    public String unfollow(int entityType, int entityId){
        User user = hostHolder.getUser();
        followService.unfollow(user.getId(), entityType,entityId);
        return CommunityUtil.getJSONString(0,"已取消关注");
    }
}
