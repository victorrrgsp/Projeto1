package com.cursodovitin.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursodovitin.projetoJPA.entities.OrderItem;

/* p JpaRepository é do tipo generics onde pede o tipo da entidade e o tipo do id
* a Interface OrderItemRepository ja ta herdando os conponentes de JpaRepository
* que ja estar registrado como um coponete do spring
*/ 
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    
}
