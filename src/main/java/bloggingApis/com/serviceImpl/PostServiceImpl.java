package bloggingApis.com.serviceImpl;

import bloggingApis.com.entity.Category;
import bloggingApis.com.entity.Post;
import bloggingApis.com.entity.User;
import bloggingApis.com.exception.CategoryCustomException;
import bloggingApis.com.exception.PostCustomException;
import bloggingApis.com.exception.UserCustomException;
import bloggingApis.com.payload.PostDto;
import bloggingApis.com.repository.CategoryRepository;
import bloggingApis.com.repository.PostRepository;
import bloggingApis.com.repository.UserRepository;
import bloggingApis.com.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private ModelMapper mapper;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    public PostServiceImpl(PostRepository postRepository, ModelMapper mapper, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.mapper = mapper;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }
    public List<PostDto> getAllPosts() {
        List<Post> post=postRepository.findAll();
        return post.stream()
                .map(post1->mapper.map(post1,PostDto.class))
                .collect(Collectors.toList());
    }
    public PostDto getPostById(Integer id) {
        Post post=postRepository.findById(id).orElseThrow(()->new PostCustomException("Post not found with id "+id));
        return mapper.map(post, PostDto.class);
    }
    public PostDto updatePost(PostDto postDto, Integer id) {
        Post post=postRepository.findById(id).orElseThrow(()->new PostCustomException("Post not found with id "+id));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImage(post.getImage());
        postRepository.save(post);
        return mapper.map(post,PostDto.class);
    }

    public void deletePost(Integer id) {
        Post post=postRepository.findById(id).orElseThrow(()->new PostCustomException("Post not found with id "+id));
        postRepository.deleteById(id);
    }
    public PostDto addPost(Post post,Integer userId,Integer categoryId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new UserCustomException("User not found",userId));
        Category category=categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryCustomException("Category not found"));
        post.setUser(user);
        post.setCategory(category);
        postRepository.save(post);
        return mapper.map(post, PostDto.class);
    }


//    public List<PostDto> getPostsByUser(Integer id) {
//        List<Post> post=postRepository.getPostsByUser(id);
//         return  post.stream()
//                 .map(post1->mapper.map(post1, PostDto.class))
//                 .collect(Collectors.toList());
//    }
//
//
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        List<Post> post=postRepository.getPostsByCategory(categoryId);
        return post.stream()
                .map(post1->mapper.map(post1, PostDto.class))
                .collect(Collectors.toList());
    }
}
