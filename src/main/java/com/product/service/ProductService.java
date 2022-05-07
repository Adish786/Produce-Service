package com.product.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.model.Product;
import com.product.repository.ProductRepository;

import java.util.List;

@Service
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
        return repository.findById(id).orElse(null);
    }

    public Product getProductByName(String name) {
        return repository.findByName(name);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Product updateProduct(Product product) {
    	Product outProduct = null;

    	if(product.getCompany() != null && product.getName() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setName(product.getName());
            existingProduct.setCompany(product.getCompany());
            outProduct =  repository.save(existingProduct);
    	}
        else if(product.getName()!=null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setName(product.getName());
            outProduct = repository.save(existingProduct);
        }
        else if(product.getCompany() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        }
    	else if(product.getQuantity() != 0 && product.getPrice() != 0.0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setPrice(product.getPrice());
            outProduct = repository.save(existingProduct);
    	}
        else if(product.getQuantity() != 0 && product.getCompany() != null) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());
            existingProduct.setCompany(product.getCompany());
            outProduct = repository.save(existingProduct);
        }
        else if(product.getQuantity() != 0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setQuantity(product.getQuantity());;
            outProduct = repository.save(existingProduct);
        }
        else if(product.getPrice() != 0.0) {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            existingProduct.setPrice(product.getPrice());
            outProduct = repository.save(existingProduct);
        }
    	return outProduct;
    }


}
