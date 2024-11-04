package bloggingApis.com.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDto> content;
    private Long totalElements;
    private Integer totalPages;
    private Integer pageNumber;
    private Integer pageSize;
    private boolean lastPage;
}
