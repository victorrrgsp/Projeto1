package com.cursodovitin.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursodovitin.projetoJPA.entities.Category;

/* p JpaRepository é do tipo generics onde pede o tipo da entidade e o tipo do id
* a Interface UserRepository ja ta herdando os conponentes de JpaRepository
* que ja estar registrado como um coponete do spring
*/ 
public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
