package kill.me.palas.services;

import kill.me.palas.models.Category;
import kill.me.palas.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Category findOne(int id){
        return categoryRepository.findById(id);
    }

    public List<String> findAllNames(){
        List<Category> categories = categoryRepository.findAll();
        List<String> names = new ArrayList<>();
        for (Category category: categories) names.add(category.getName());
        return names;
    }

    public Category findByName(String name){
        return categoryRepository.findByName(name);
    }
}
