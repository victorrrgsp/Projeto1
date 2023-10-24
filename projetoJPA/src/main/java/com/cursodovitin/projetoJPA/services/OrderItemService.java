package com.cursodovitin.projetoJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursodovitin.projetoJPA.entities.OrderItem;
import com.cursodovitin.projetoJPA.repositories.OrderItemRepository;

// o Servise serve para voce registrar esse Serviso como um conponente do spring
@Service
public class OrderItemService {

    // o AutoWired faz a injeção de dependencia 
    @Autowired
    private OrderItemRepository OrderItemRepository;

    public List<OrderItem> findAll(){
        return OrderItemRepository.findAll();
    }

    public OrderItem findById(Long id){
        Optional<OrderItem> obj = OrderItemRepository.findById(id);
        // o get do optional retorna um objeto do tipo OrderItem que estiver dentro do obj
        return obj.get();
    }
}
