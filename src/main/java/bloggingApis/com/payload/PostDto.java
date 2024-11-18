package bloggingApis.com.payload;

import bloggingApis.com.entity.Category;
import bloggingApis.com.entity.Comment;
import bloggingApis.com.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Integer postId;
    private String title;
    private String image;
    private String content;

    private UserDto user;
    private CategoryDto category;

    private Set<CommentDto> comments=new HashSet<>();

}
