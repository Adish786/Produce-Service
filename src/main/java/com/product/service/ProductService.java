package com.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> saveProducts(List<Product> products) {
        return repository.saveAll(products);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Product getProductById(int id) {
        return repository.findById(id);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }
    
    /*
    public Product updateProduct(Product product) {
    	Product existingProduct = repository.findById(product.getId()).orElse(null);
    	existingProduct.setId(product.getId());
    	existingProduct.setCompany(product.getCompany());
    	existingProduct.setName(product.getName());
    	existingProduct.setPrice(product.getPrice());
    	existingProduct.setQuantity(product.getQuantity());
        return repository.save(existingProduct);
    }
*/
    /*
    public Product updateProduct(Product product) {
        Product outProduct = null;

        if (product.getCompany() != null && product.getName() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setName(product.getName());
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        } else if (product.getName() != null && product.getQuantity() > 0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setName(product.getName());
            outProduct = repository.save(existingProduct);
        }else if (product.getQuantity() != 0 && product.getCompany() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        } else if (product.getName() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setName(product.getName());
            outProduct = repository.save(existingProduct);
        } else if (product.getCompany() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        } else if (product.getQuantity() != 0 && product.getPrice() != 0.0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            outProduct = repository.save(existingProduct);
        }  else if (product.getQuantity() != 0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            ;
            outProduct = repository.save(existingProduct);
        } else if (product.getPrice() != 0.0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setPrice(product.getPrice());
            outProduct = repository.save(existingProduct);
        }
        return outProduct;
    }
    public Product updateProductByName(Product product) {
        Product outProduct = null;

        if (product.getCompany() != null && product.getName() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setName(product.getName());
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        } else if (product.getName() != null && product.getQuantity() > 0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setName(product.getName());
            outProduct = repository.save(existingProduct);
        }else if (product.getQuantity() != 0 && product.getCompany() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        } else if (product.getName() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setName(product.getName());
            outProduct = repository.save(existingProduct);
        } else if (product.getCompany() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        } else if (product.getQuantity() != 0 && product.getPrice() != 0.0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            outProduct = repository.save(existingProduct);
        }  else if (product.getQuantity() != 0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            ;
            outProduct = repository.save(existingProduct);
        } else if (product.getPrice() != 0.0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setPrice(product.getPrice());
            outProduct = repository.save(existingProduct);
        }
        return outProduct;
    }
    
    */
}
