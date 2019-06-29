package com.huskyshare.backend.restapi;

import com.huskyshare.backend.entity.User;
import com.huskyshare.backend.entity.Post;
import com.huskyshare.backend.entity.Vote;
import com.huskyshare.backend.service.PostService;
import com.huskyshare.backend.service.UserService;
import com.huskyshare.backend.service.VoteService;
import com.huskyshare.backend.utils.JWTUtil;
import org.apache.http.HttpStatus;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import com.huskyshare.backend.json_entity.ResponseBean;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class PostController {
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    VoteService voteService;

    @RequestMapping(method = RequestMethod.POST, value = "/rest/v1.0/post")
    @RequiresRoles("USER")
    @RequiresAuthentication
    public ResponseBean post(@RequestBody String content, @RequestHeader String Authorization){
        ResponseBean response = new ResponseBean();
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        response.setDate(new Date());
        Post post = new Post();
        post.setDeleted(false);
        post.setContent(content);
        post.setUser(current);
        post.setId(current.getId());
        postService.save(post);
        response.setStatus(HttpStatus.SC_OK);
        response.setMsg("post successfully!");
        return response;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/rest/v1.0/delete_post")
    @RequiresRoles("USER")
    @RequiresAuthentication
    public ResponseBean deletePost(@RequestParam Long post_id, @RequestHeader String Authorization){
        ResponseBean response = new ResponseBean();
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        response.setDate(new Date());
        Post post = postService.findById(post_id);
        post.setDeleted(true);
        postService.save(post);
        response.setStatus(HttpStatus.SC_OK);
        response.setMsg("delete post successfully!");
        return response;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/rest/v1.0/like_post")
    @RequiresRoles("USER")
    @RequiresAuthentication
    public ResponseBean likePost(@RequestParam Long post_id, @RequestHeader String Authorization){
        ResponseBean response = new ResponseBean();
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        response.setDate(new Date());
        Vote vote = new Vote();
        Post post = postService.findById(post_id);
        vote.setUserId(current.getId());
        vote.setPost(post);
        post.getVotes().add(vote);
        voteService.save(vote);
        response.setStatus(HttpStatus.SC_OK);
        response.setMsg("like successfully!");
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rest/v1.0/unlike_post")
    @RequiresRoles("USER")
    @RequiresAuthentication
    public ResponseBean unlikePost(@RequestParam Long post_id, @RequestHeader String Authorization){
        ResponseBean response = new ResponseBean();
        User current = userService.findUserByUsername(JWTUtil.getUsername(Authorization));
        response.setDate(new Date());
        Vote vote = voteService.findVoteByUser(current.getId());
        postService.deleteVote(postService.findById(post_id), vote);
        voteService.delete(vote);
        response.setStatus(HttpStatus.SC_OK);
        response.setMsg("unlike successfully!");
        return response;
    }


}
