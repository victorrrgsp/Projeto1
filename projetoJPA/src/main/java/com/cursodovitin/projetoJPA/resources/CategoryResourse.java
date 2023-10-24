package com.cursodovitin.projetoJPA.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cursodovitin.projetoJPA.entities.Category;
import com.cursodovitin.projetoJPA.services.CategoryService;

// a anotecion fala que essa classe é um recurso web que controlada por um controlador Rest
@RestController
// o RequestMapping da um nome para o meu recurso
@RequestMapping(value = "/categories")
public class CategoryResourse {

    @Autowired
    private CategoryService CategoryService;

    /* o ResponseEntity é um tipo especifico do spring para retorna respostas de requesicao web
     * que sao mensagens envidas pelo cliente para iniciar uma ação no servidor, 
     * o ResponseEntity é um generics que espera um tipo de resposta
     */
    @GetMapping
    public ResponseEntity<List<Category>> findAll(){
        List<Category> list = CategoryService.findAll();
        // o ok retorna a resposta com sucesso no http e o body retorna o corpo da resposta do meu Category instaciado
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    // para o spring aceita o id como um parametro para a url terar que usar a anotacao PathVariable
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Category obj = CategoryService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
