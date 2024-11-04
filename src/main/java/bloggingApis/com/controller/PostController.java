package bloggingApis.com.controller;

import bloggingApis.com.entity.Post;
import bloggingApis.com.payload.ApiResponse;
import bloggingApis.com.payload.PostDto;
import bloggingApis.com.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService=postService;
    }
    @PostMapping("/user/{userId}/category/{categoryId}/addPost")
    public ResponseEntity<PostDto> addPost(@RequestBody Post post,@PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId) {
        return new ResponseEntity<>(postService.addPost(post,userId,categoryId), HttpStatus.CREATED);
    }
    @GetMapping("/getAllPosts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }
    @GetMapping("/getPostById/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
    }
    @PutMapping("/updatePostById/{id}")
    public PostDto updatePost(@RequestBody Post post, @PathVariable("id") Integer id) {
        return postService.updatePost(post, id);
    }
    @DeleteMapping("/deletePostById/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("id") Integer id) {
        postService.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Post Deleted Successfully",true), HttpStatus.OK);
    }
    @GetMapping("/getPostByCategory/{id}")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable("id") Integer categoryId) {
        return new ResponseEntity<>(postService.getPostsByCategory(categoryId), HttpStatus.OK);
    }
    @GetMapping("/getPostByUser/{id}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable("id") Integer userId) {
        return new ResponseEntity<>(postService.getPostsByUser(userId), HttpStatus.OK);
    }


}
