package com.cursodovitin.projetoJPA.services.exceptions;

/* essa classe vai ser uma sub classe de RuntimeException 
 * que é uma exeção que o conpilador não de obriga a tratar 
*/ 
public class ResourceNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException (Object id){
        super("Resource not found. " + id);
    }
}
