package com.catl.community.controller;

import com.catl.community.entity.DiscussPost;
import com.catl.community.entity.User;
import com.catl.community.service.DiscussPostService;
import com.catl.community.service.UserService;
import com.catl.community.util.CommunityUtil;
import com.catl.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/discuss")
public class DiscussPostController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private UserService userService;

    //开始增加帖子的异步请求 增加请求路径 返回是字符串 不是网页 加上ResponseBody
    //浏览器只传入 title 和 content 还要获取当前用户 发帖的前提是登录 403 没有权限

    @RequestMapping(path = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addDiscussPost(String title,String content){
        User user = hostHolder.getUser();
        if(user == null){
            //异步的JSON格式的数据
            return CommunityUtil.getJSONString(403,"你还没有登录哦!");
        }

        //已经登录
        DiscussPost post = new DiscussPost();
        post.setUserID(user.getId());
        post.setTitle(title);
        post.setContent(content);
        post.setCreateTime(new Date());
        discussPostService.addDiscussPost(post);

        //程序执行过程中报错会统一处理 默认程序不会报错
        return CommunityUtil.getJSONString(0,"发布成功!");
    }

    @RequestMapping(path = "/detail/{discussPostId}",method = RequestMethod.GET)
    public String getDiscussPost(@PathVariable("discussPostId") int discussPostId, Model model){
        //查询帖子
        DiscussPost post = discussPostService.findDiscussPostById(discussPostId);
        //帖子数据传给模板
        model.addAttribute("post",post);
        //作者
        User user = userService.findUserById(post.getUserID());
        //帖子作者传给模板
        model.addAttribute("user",user);
        //返回模板的路径
        return "/site/discuss-detail";
    }
}
