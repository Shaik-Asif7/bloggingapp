package bloggingApis.com.service;

import bloggingApis.com.entity.Category;
import bloggingApis.com.payload.CategoryDto;
import bloggingApis.com.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryDto addCategory(Category category);
    CategoryDto updateCategory(Category category,Integer id);
    public void deleteCategory(Integer id);
    public  CategoryDto getCategoryById(Integer id);
    public List<CategoryDto> getAllCategories();
}
