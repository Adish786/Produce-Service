package com.product.controller;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping(value = "/addProduct" , method = RequestMethod.POST)
    public Product addProduct(@RequestBody Product product) {
        return service.saveProduct(product);
    }

    @RequestMapping(value = "/addProducts" , method = RequestMethod.POST)
    public List<Product> addProducts(@RequestBody List<Product> products) {
        return service.saveProducts(products);
    }

    @RequestMapping(value = "/products" , method = RequestMethod.GET)
    
    public List<Product> findAllProducts() {
        return service.getProducts();
    }

    @RequestMapping(value = "/productById/{id}" , method = RequestMethod.GET)
   
    public Product findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

  //When @GetMapping("/welcome") Method are down then Falback Method are call by Shopping Service
  
    @RequestMapping(value = "/welcome" , method = RequestMethod.GET)
    public String Welcome() {
    	return "Welcome To Product Service";
    }

    @RequestMapping(value = "/product/{name}", method = RequestMethod.GET)
    public Product findProductByName(@PathVariable(name = "name") String name) {
        return service.getProductByName(name);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
    
    /*
    @PutMapping("/update/{name}")
    public Product updateProductByName(@PathVariable(name = "name") String name,Product product) {
        return service.updateProductByName(product);
    }
    */

 /*   
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }
   */
    
    @PutMapping("update/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") int id, @RequestBody Product product) {
        Optional<Product> optionalProject = Optional.of(productRepository.findById(id));
        if (optionalProject.isPresent()) {
        	Product p = optionalProject.get();
            if (product.getCompany() != null)
                p.setCompany(product.getCompany());
            if (product.getName() !=null)
                p.setName(product.getName());
            if (product.getQuantity() != 0)
                p.setQuantity(product.getQuantity());
            if (product.getPrice() != 0)
                p.setPrice(product.getPrice());
            return new ResponseEntity<>(productRepository.save(p), HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    
}
