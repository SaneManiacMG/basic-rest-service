package com.tutorial.restfulservice.controller;

import com.tutorial.restfulservice.exception.ProductNotFoundException;
import com.tutorial.restfulservice.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {

    private static Map<String, Product> productRepo = new HashMap<>();

    static {
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    @RequestMapping(value = "/products")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id) {
        if(!productRepo.containsKey(id)) throw new ProductNotFoundException();
        return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST )
    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
        productRepo.put(product.getId(), product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,
                                                @RequestBody Product product) {
        if(!productRepo.containsKey(id)) throw new ProductNotFoundException();
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if(!productRepo.containsKey(id)) throw new ProductNotFoundException();
        productRepo.remove(id);
        return new ResponseEntity<>("product is deleted successfully", HttpStatus.OK);
    }
}
