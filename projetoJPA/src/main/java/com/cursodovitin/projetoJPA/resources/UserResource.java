package com.cursodovitin.projetoJPA.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cursodovitin.projetoJPA.entities.User;
import com.cursodovitin.projetoJPA.services.UserService;

// a anotecion fala que essa classe é um recurso web que controlada por um controlador Rest
@RestController
// o RequestMapping da um nome para o meu recurso
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService userService;

    /* o ResponseEntity é um tipo especifico do spring para retorna respostas de requesicao web
     * que sao mensagens envidas pelo cliente para iniciar uma ação no servidor, 
     * o ResponseEntity é um generics que espera um tipo de resposta
     */
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        // o ok retorna a resposta com sucesso no http e o body retorna o corpo da resposta do meu user instaciado
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    // para o spring aceita o id como um parametro para a url terar que usar a anotacao PathVariable
    public ResponseEntity<User> findById(@PathVariable Long id){
        User obj = userService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    /* O obj vai chager no tipo Json na hora de fazer a requisição, e esse Json vai ser desserializado 
     * para um objeto user aqui no meu java onde terar q ter uma anotação 
     * que no caso é o @RequestBody */
    public ResponseEntity<User> insert (@RequestBody User obj){
        obj = userService.insert(obj);
        /* o created esperar um objeto do tipo URI 
         * por que o padrao http quando vai retorna um 201 é esperado que a resposta contenha
         * um cabecalho chamado location contedo o inderenco do novo recurso que voce inseriu
        */ 
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
            .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    // quando for deleta 
    @DeleteMapping(value = "/{id}")
    // para o Long id ser reconhecido como uma vareavel da minha url
    public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        /* o noContent vai retorna uma resposta vazia 
         * e o codigo http de uma resposta que não tem conteudo é o 204 
        */ 
        return ResponseEntity.noContent().build();
    }
}
