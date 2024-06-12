package com.testing.supermarket.services;

import com.testing.supermarket.data.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final ProductRepository productRepository;
    private final TypeRepository typeRepository;
    private final BrandRepository brandRepository;

    public InventoryService(ProductRepository productRepository,
                            TypeRepository typeRepository,
                            BrandRepository brandRepository) {
        this.productRepository = productRepository;
        this.typeRepository = typeRepository;
        this.brandRepository = brandRepository;
    }

    public List<Product> findAllProducts(String stringFilter) {
        if (stringFilter == null || stringFilter.isEmpty()) {
            return productRepository.findAll();
        } else {
            return productRepository.search(stringFilter);
        }
    }

    public long countProducts() {
        return productRepository.count();
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    public void saveProduct(Product product) {
        if (product == null) {
            System.err.println("Product is null. Are you sure you have connected your form to the application?");
            return;
        }
        productRepository.save(product);
    }

    public List<Type> findAllTypes() {
        return typeRepository.findAll();
    }

    public List<Brand> findAllBrands() {
        return brandRepository.findAll();
    }
}

