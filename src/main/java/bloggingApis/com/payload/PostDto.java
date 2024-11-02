package bloggingApis.com.payload;

import bloggingApis.com.entity.Category;
import bloggingApis.com.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
