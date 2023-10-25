package com.cursodovitin.projetoJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cursodovitin.projetoJPA.entities.User;
import com.cursodovitin.projetoJPA.repositories.UserRepository;
import com.cursodovitin.projetoJPA.resources.exceptions.DatabaseException;
import com.cursodovitin.projetoJPA.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

// o Servise serve para voce registrar esse Serviso como um conponente do spring
@Service
public class UserService {

    // o AutoWired faz a injeção de dependencia 
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> obj = userRepository.findById(id);
        /* o get do optional retorna um objeto do tipo User que estiver dentro do obj
         * o orElseThrow vai tentar dar o get, se não tiver o usuario ele vai lançar uma exceção
        */ 
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        try {
            // vai deletar o id que estar como parametro
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }
    }

    // o id vai indicar qual o user do banco e o obj os atributos do mesmo
    public User update(Long id, User obj){
        try {
            /* o getReferenceById vai instaciar um usuario mas ele não vai pro banco de dados ainda 
            * ele só vai deixar um objeto monitorado pelo jpa trabalha com ele e em seguida posso afetua uma operação ao banco de dados
            */ 
            User entity = userRepository.getReferenceById(id);
            updateDate(entity, obj);
            return userRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateDate(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
