package bloggingApis.com.service;

import bloggingApis.com.entity.Post;
import bloggingApis.com.payload.PostDto;
import bloggingApis.com.payload.PostResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy);
    PostDto getPostById(Integer id);
    PostDto updatePost(Post post, Integer id);
    void deletePost(Integer id);
    PostDto addPost(Post post,Integer userId,Integer categoryId);

    List<PostDto> getPostsByCategory(Integer categoryId);
    //getPostByUser
    List<PostDto> getPostsByUser(Integer id);

    List<PostDto> getResult(String keyword);
}
