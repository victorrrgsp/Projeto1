package com.cursodovitin.projetoJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursodovitin.projetoJPA.entities.Order;
import com.cursodovitin.projetoJPA.repositories.OrderRepository;

// o Servise serve para voce registrar esse Serviso como um conponente do spring
@Service
public class OrderService {

    // o AutoWired faz a injeção de dependencia 
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findAll(){
        return orderRepository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> obj = orderRepository.findById(id);
        // o get do optional retorna um objeto do tipo User que estiver dentro do obj
        return obj.get();
    }
}
