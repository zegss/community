package com.zeg.community.controller;

import com.zeg.community.entity.DiscussPost;
import com.zeg.community.entity.Page;
import com.zeg.community.entity.User;
import com.zeg.community.service.DiscussPostService;
import com.zeg.community.service.LikeService;
import com.zeg.community.service.UserService;
import com.zeg.community.util.CommunityConStant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements CommunityConStant {
    @Autowired
    private UserService userService;
    @Autowired
    private DiscussPostService discussPostService;
    @Autowired
    private LikeService likeService;

    @RequestMapping(value = {"/index","/"})
    public String getIndex(Model model, Page page){
        page.setRows(discussPostService.selectDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.selectDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String,Object>> discussPosts = new ArrayList<>();
        if (list!=null){
            for (DiscussPost post : list){
                Map<String, Object> map =  new HashMap<>();
                map.put("post",post);
                User user = userService.selectById(post.getUserId());
                map.put("user",user);

                long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, post.getId());
                map.put("likeCount",likeCount);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);
        return "index";
    }

    @GetMapping("/error")
    public String getErrorPage(){
        return "error/500";

    }
}
