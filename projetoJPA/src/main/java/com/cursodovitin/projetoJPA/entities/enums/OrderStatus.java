package com.cursodovitin.projetoJPA.entities.enums;

public enum OrderStatus {

    // quando o tipo enum for definido, o java exige q voce emplemente outras coisas
    WAITING_PAYMENT(1),
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    // isso será o codigo do tipo enum
    private int code;

    // o contrutor do tipo enum é diferente, no caso private
    private OrderStatus (int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

    // um metodeo que converte um valor numerico para um do tipo enum,onde será static
    public static OrderStatus valueOf(int code){
        // vai percorrer todos os valores de OrderStatus
        for (OrderStatus value : OrderStatus.values()) {
            if (value.getCode() == code){
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code");
    }
}
