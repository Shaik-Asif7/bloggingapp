package bloggingApis.com.service;

import bloggingApis.com.entity.Comment;
import bloggingApis.com.payload.CommentDto;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    CommentDto addComment(Comment comment,Integer userId,Integer postId);

    void deleteComment(Integer commentId);
}
