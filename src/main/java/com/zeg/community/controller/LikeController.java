package com.zeg.community.controller;

import com.zeg.community.entity.User;
import com.zeg.community.service.LikeService;
import com.zeg.community.util.CommunityUtil;
import com.zeg.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/like")
    @ResponseBody
    public String like(int entityType, int entityId,int entityUserId){
        User user = hostHolder.getUser();
        //点赞
        likeService.like(user.getId(), entityType, entityId,entityUserId);
        //数量
        long count = likeService.findEntityLikeCount(entityType, entityId);
        //状态
        int status = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        //返回结果
        HashMap<String, Object> map = new HashMap<>();
        map.put("likeCount",count);
        map.put("likeStatus",status);
        return CommunityUtil.getJSONString(0,null,map);
    }
}
