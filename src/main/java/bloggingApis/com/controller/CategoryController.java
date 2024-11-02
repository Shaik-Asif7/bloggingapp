package bloggingApis.com.controller;

import bloggingApis.com.entity.Category;
import bloggingApis.com.payload.ApiResponse;
import bloggingApis.com.payload.CategoryDto;
import bloggingApis.com.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    // Implement your category-related API endpoints here
    @PostMapping("/addCategory")
    public ResponseEntity<CategoryDto>  addCategory(@Valid  @RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }
    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody Category category,@PathVariable("id") Integer id) {
        return new ResponseEntity<>(categoryService.updateCategory(category,id), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully",true), HttpStatus.OK);
    }
    @GetMapping("/getCategory/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }
}
