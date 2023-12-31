package com.cursodovitin.projetoJPA.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // @Transient
    // muitos para muitos
    @ManyToMany
    /* O JoinTable vai pegar qual o nome da tabela e 
    * quais vai ser as chaves estrageiras que vai associa a tabela de product e de category
    */ 
    @JoinTable(name = "tb_product_category", 
    joinColumns = @JoinColumn(name = "product_id"),
    inverseJoinColumns = @JoinColumn(name = "category_id"))
    /* o Set garante q nao vai ter um produto com mais de um categoria
     * o set nao pode ser instaciado pois é uma interface
     * onde terá que usar um classe referente a interface Set
    */
    private Set<Category> categories = new HashSet<>();
	
    @OneToMany(mappedBy = "id.product")
    // o Set não deixa o mesmo product ser repetido
    private Set<OrderItem> items = new HashSet<>();

    public Product (){
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @JsonIgnore
    public Set<Order> getOrders(){
        Set<Order> set = new HashSet<>();
        for (OrderItem orderItem : items){
            set.add(orderItem.getOrder());
        }
        return set;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Product other = (Product) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
