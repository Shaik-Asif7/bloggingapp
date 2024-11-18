package bloggingApis.com.serviceImpl;

import bloggingApis.com.entity.Comment;
import bloggingApis.com.entity.Post;
import bloggingApis.com.entity.User;
import bloggingApis.com.exception.CommentCustomException;
import bloggingApis.com.exception.UserCustomException;
import bloggingApis.com.payload.CommentDto;
import bloggingApis.com.repository.CommentRepository;
import bloggingApis.com.repository.PostRepository;
import bloggingApis.com.repository.UserRepository;
import bloggingApis.com.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ModelMapper mapper;
    public CommentServiceImpl(CommentRepository commentRepository,UserRepository userRepository,PostRepository postRepository,ModelMapper mapper) {
        this.commentRepository=commentRepository;
        this.userRepository=userRepository;
        this.postRepository=postRepository;
        this.mapper=mapper;
    }

    public CommentDto addComment(Comment comment, Integer userId, Integer postId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new UserCustomException("User not found",userId));
        Post post=postRepository.findById(postId).orElseThrow(()-> new UserCustomException("Post not found",postId));
        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
        return mapper.map(comment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment=commentRepository.findById(commentId).orElseThrow(()-> new CommentCustomException("Comment not found "));
        commentRepository.delete(comment);
    }
}
