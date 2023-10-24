package com.cursodovitin.projetoJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursodovitin.projetoJPA.entities.Product;
import com.cursodovitin.projetoJPA.repositories.ProductRepository;

// o Servise serve para voce registrar esse Serviso como um conponente do spring
@Service
public class ProductService {

    // o AutoWired faz a injeção de dependencia 
    @Autowired
    private ProductRepository ProductRepository;

    public List<Product> findAll(){
        return ProductRepository.findAll();
    }

    public Product findById(Long id){
        Optional<Product> obj = ProductRepository.findById(id);
        // o get do optional retorna um objeto do tipo User que estiver dentro do obj
        return obj.get();
    }
}
