package com.example.springphoto.Repository;

import com.example.springphoto.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    @Query("SELECT product FROM Product product WHERE CONCAT(product.id, ' ', product.name, ' ', product.price) LIKE %?1%")
//    public List<Product> search(String keyword);
//    public Product findByName(String name);
}
