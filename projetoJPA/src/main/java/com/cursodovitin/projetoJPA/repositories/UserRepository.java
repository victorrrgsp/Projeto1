package com.cursodovitin.projetoJPA.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursodovitin.projetoJPA.entities.User;

// p JpaRepository é do tipo generics onde pede o tipo da entidade e o tipo do id
public interface UserRepository extends JpaRepository<User, Long>{
    
}
