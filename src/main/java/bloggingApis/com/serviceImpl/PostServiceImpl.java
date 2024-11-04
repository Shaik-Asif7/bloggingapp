package bloggingApis.com.serviceImpl;

import bloggingApis.com.entity.Category;
import bloggingApis.com.entity.Post;
import bloggingApis.com.entity.User;
import bloggingApis.com.exception.CategoryCustomException;
import bloggingApis.com.exception.PostCustomException;
import bloggingApis.com.exception.UserCustomException;
import bloggingApis.com.payload.PostDto;
import bloggingApis.com.payload.PostResponse;
import bloggingApis.com.repository.CategoryRepository;
import bloggingApis.com.repository.PostRepository;
import bloggingApis.com.repository.UserRepository;
import bloggingApis.com.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy) {
        Pageable p= PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Post> posts=postRepository.findAll(p);
        List<Post> post=posts.getContent();
        List<PostDto> postDtos= post.stream()
                .map(post1->mapper.map(post1,PostDto.class))
                .collect(Collectors.toList());
        PostResponse postResponse=new PostResponse();
        postResponse.setPageNumber(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLastPage(posts.isLast());
        postResponse.setContent(postDtos);
        postResponse.setTotalElements(posts.getTotalElements());
        return  postResponse;

    }
    public PostDto getPostById(Integer id) {
        Post post=postRepository.findById(id).orElseThrow(()->new PostCustomException("Post not found with id "+id));
        return mapper.map(post, PostDto.class);
    }
    public PostDto updatePost(Post post, Integer id) {
        Post post1=postRepository.findById(id).orElseThrow(()->new PostCustomException("Post not found with id "+id));
        post1.setTitle(post.getTitle());
        post1.setContent(post.getContent());
        post1.setImage(post.getImage());
        postRepository.save(post1);
        return mapper.map(post1,PostDto.class);
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


    public List<PostDto> getPostsByUser(Integer userId) {
        User user=userRepository.findById(userId).orElseThrow(()-> new UserCustomException("user not found with id %d",userId));
        List<Post> post=postRepository.findByUser(user);
         return  post.stream()
                 .map(post1->mapper.map(post1, PostDto.class))
                 .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getResult(String keyword) {
        List<Post> posts=postRepository.findByTitleContaining(keyword);
        return posts.stream().map(post->mapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }

    //
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category cat=categoryRepository.findById(categoryId).orElseThrow(()-> new CategoryCustomException("Category not found"));
        List<Post> post=postRepository.findByCategory(cat);
        return post.stream()
                .map(post1->mapper.map(post1, PostDto.class))
                .collect(Collectors.toList());
    }
}
