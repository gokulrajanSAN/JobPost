package com.gokul.JobList.post;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Data
@RestController
@RequestMapping("/")

public class PostController {

   private final PostService postService;
//   private final PostRepo postRepo;


   @PostMapping("post")
   public Post addPost(@RequestBody Post post){
       return postService.save(post);
   }

   @GetMapping("allPosts")
   public List<Post> findAllPost(){
        return postService.findAll();
    }

   @GetMapping("post/{text}")
   public List<Post> findByText(@PathVariable String text){
       return postService.findByText(text);
   }

}
