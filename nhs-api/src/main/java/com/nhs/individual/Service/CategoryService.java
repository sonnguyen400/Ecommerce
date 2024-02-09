package com.nhs.individual.Service;

import com.nhs.individual.Domain.Category;
import com.nhs.individual.Repository.CategoryRepository;
import org.hibernate.NonUniqueObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category create(Category category){
        Category parent=category.getParentCategory();
        if(parent.getParentCategory()!=null){
            if(findAllByParentId(parent.getId()).stream().anyMatch(sibCategory->{
                return sibCategory.getName().equals(category.getName());
            })){
                throw new NonUniqueObjectException("The category's name can not be the same as its siblings",String.valueOf(parent.getId()));
            };
        }
        return categoryRepository.save(category);
    }
    public Optional<Category> findById(Integer id){
        return categoryRepository.findById(id);
    }
    public Collection<Category> findAllByParentId(Integer parentId){
        return categoryRepository.findAllByParentCategoryId(parentId);
    }
    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }
}
