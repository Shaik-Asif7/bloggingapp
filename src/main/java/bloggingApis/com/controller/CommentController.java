package bloggingApis.com.controller;

import bloggingApis.com.entity.Comment;
import bloggingApis.com.payload.ApiResponse;
import bloggingApis.com.payload.CommentDto;
import bloggingApis.com.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/blog")
public class CommentController {

    private final CommentService commentService;
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/user/{userId}/post/{postId}/addComment")
    public ResponseEntity<CommentDto> addComment(@RequestBody Comment comment, @PathVariable("userId") Integer userId, @PathVariable("postId") Integer postId){
        return new ResponseEntity<>(commentService.addComment(comment,userId,postId), HttpStatus.CREATED);
    }
    @DeleteMapping("deleteComment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(new ApiResponse("Comment Deleted Successfully",true), HttpStatus.OK);
    }

}
