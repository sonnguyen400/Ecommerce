package com.nhs.individual.Service;

import com.nhs.individual.Domain.Category;
import com.nhs.individual.Exception.ResourceNotFoundException;
import com.nhs.individual.Repository.CategoryRepository;
import com.nhs.individual.Utils.ObjectUtils;
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
            if(findAllByParentId(parent.getId()).stream().anyMatch(sibCategory-> sibCategory.getName().equals(category.getName()))){
                throw new NonUniqueObjectException("The category's name can not be the same as its siblings",String.valueOf(parent.getId()));
            }
        }
        return categoryRepository.save(category);
    }
    public Category addChild(int parentId,Category category){
        return findById(parentId).map(parentCategory->{
            category.setParentCategory(parentCategory);
            return categoryRepository.save(category);
        }).orElseThrow(()->  new ResourceNotFoundException("Could not find category with id: "+parentId));
    }
    public Optional<Category> findById(int id){
        return categoryRepository.findById(id);
    }
    public Collection<Category> findAllByParentId(int parentId){
        return categoryRepository.findAllByParentCategoryId(parentId);
    }
    public Category updateCategory(int id,Category category){
        return findById(id).map(oldCategory->{
            category.setId(oldCategory.getId());
            return categoryRepository.save(ObjectUtils.merge(oldCategory,category,Category.class));
        }).orElseThrow(()->new ResourceNotFoundException("Couldn't find category with id " + id));
    }
    public Collection<Category> findAll(){
        return categoryRepository.findAll();
    }
    public void deleteById(Integer id){
        categoryRepository.deleteById(id);
    }
}
