package com.cursodovitin.projetoJPA.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursodovitin.projetoJPA.entities.User;
import com.cursodovitin.projetoJPA.repositories.UserRepository;

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
        // o get do optional retorna um objeto do tipo User que estiver dentro do obj
        return obj.get();
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        // vai deletar o id que estar como parametro
        userRepository.deleteById(id);
    }

    // o id vai indicar qual o user do banco e o obj os atributos do mesmo
    public User update(Long id, User obj){
        /* o getReferenceById vai instaciar um usuario mas ele não vai pro banco de dados ainda 
         * ele só vai deixar um objeto monitorado pelo jpa trabalha com ele e em seguida posso afetua uma operação ao banco de dados
        */ 
        User entity = userRepository.getReferenceById(id);
        updateDate(entity, obj);
        return userRepository.save(entity);
    }

    private void updateDate(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
