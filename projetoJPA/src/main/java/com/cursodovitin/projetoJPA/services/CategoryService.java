package com.cursodovitin.projetoJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursodovitin.projetoJPA.entities.Category;
import com.cursodovitin.projetoJPA.repositories.CategoryRepository;

// o Servise serve para voce registrar esse Serviso como um conponente do spring
@Service
public class CategoryService {

    // o AutoWired faz a injeção de dependencia 
    @Autowired
    private CategoryRepository CategoryRepository;

    public List<Category> findAll(){
        return CategoryRepository.findAll();
    }

    public Category findById(Long id){
        Optional<Category> obj = CategoryRepository.findById(id);
        // o get do optional retorna um objeto do tipo User que estiver dentro do obj
        return obj.get();
    }
}
