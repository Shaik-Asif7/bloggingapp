package bloggingApis.com.serviceImpl;

import bloggingApis.com.entity.Category;
import bloggingApis.com.exception.CategoryCustomException;
import bloggingApis.com.payload.CategoryDto;
import bloggingApis.com.repository.CategoryRepository;
import bloggingApis.com.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    public CategoryServiceImpl(CategoryRepository categoryRepository,ModelMapper mapper){
        this.categoryRepository=categoryRepository;
        this.mapper=mapper;
    }

    public CategoryDto addCategory(Category category) {
        categoryRepository.save(category);
        return convertEntityToDto(category);
    }

    public CategoryDto updateCategory(Category category,Integer id) {
       Category category1=categoryRepository.findById(id).orElseThrow(()->new CategoryCustomException("Category not found"));
       category1.setTitle(category.getTitle());
       category1.setDescription(category.getDescription());
       categoryRepository.save(category1);
       return convertEntityToDto(category1);
    }


    public void deleteCategory(Integer id) {
        Category catergory=categoryRepository.findById(id).orElseThrow(()-> new CategoryCustomException("Category not found"));
        categoryRepository.delete(catergory);
    }
    public CategoryDto getCategoryById(Integer id) {
        Category catergory=categoryRepository.findById(id).orElseThrow(()-> new CategoryCustomException("Category not found"));
        return convertEntityToDto(catergory);
    }

    public List<CategoryDto> getAllCategories() {
        List<Category> category=categoryRepository.findAll();
        return  category.stream()
                .map(cat -> convertEntityToDto(cat))
                .collect(Collectors.toList());
    }
    public CategoryDto convertEntityToDto(Category category){
        return mapper.map(category,CategoryDto.class);
    }
}
