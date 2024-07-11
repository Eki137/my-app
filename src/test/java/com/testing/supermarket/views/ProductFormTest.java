package com.testing.supermarket.views;

import com.testing.supermarket.data.Brand;
import com.testing.supermarket.data.Product;
import com.testing.supermarket.data.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFormTest {
    private List<Type> types;
    private List<Brand> brands;
    private Product product;
    private Type type1;
    private Type type2;
    private Brand brand1;
    private Brand brand2;

    @BeforeEach
    public void setupData() {
        types = new ArrayList<>();
        type1 = new Type();
        type1.setName("Dairy Product");
        type2 = new Type();
        type2.setName("Chips");
        types.add(type1);
        types.add(type2);

        brands = new ArrayList<>();
        brand1 = new Brand();
        brand1.setName("Lays");
        brand2 = new Brand();
        brand2.setName("Serenisima");
        brands.add(brand1);
        brands.add(brand2);

        product = new Product();
        product.setDescription("Yogurt");
        product.setStock(8);
        product.setDiscount(0.5);
        product.setPrice(67.4);
        product.setType(type1);
        product.setBrand(brand2);
    }

    @Test
    public void formFieldsPopulated() {
        ProductForm form = new ProductForm(types, brands);
        form.setProduct(product);
        assertEquals("Yogurt", form.description.getValue());
        assertEquals("8", form.stock.getValue());
        assertEquals("0.5", form.discount.getValue());
        assertEquals("67.4", form.price.getValue());
        assertEquals(type1, form.type.getValue());
        assertEquals(brand2, form.brand.getValue());
    }

    @Test
    public void saveEventHasCorrectValues() {
        ProductForm form = new ProductForm(types, brands);
        form.setProduct(product);

        AtomicReference<Product> savedProductRef = new AtomicReference<>(null);
        form.addSaveListener(e -> {
            savedProductRef.set(e.getProduct());
        });
        form.save.click();
        Product savedProduct = savedProductRef.get();

        assertEquals("Yogurt", savedProduct.getDescription());
        assertEquals(8, savedProduct.getStock());
        assertEquals(0.5, savedProduct.getDiscount());
        assertEquals(67.4, savedProduct.getPrice());
        assertEquals(type1, savedProduct.getType());
        assertEquals(brand2, savedProduct.getBrand());
    }

    @Test
    public void checkDescriptionValidation(){
        ProductForm form = new ProductForm(types, brands);
        form.setProduct(product);
        assertTrue(form.binder.isValid());
        assertTrue(form.save.isEnabled());
        form.description.setValue("");
        assertFalse(form.binder.isValid());
        assertFalse(form.save.isEnabled());
    }

    @Test
    public void checkStockValidation(){
        ProductForm form = new ProductForm(types, brands);
        form.setProduct(product);
        assertTrue(form.binder.isValid());
        assertTrue(form.save.isEnabled());
        form.stock.setValue("-1");
        assertFalse(form.binder.isValid());
        assertFalse(form.save.isEnabled());
    }

    @Test
    public void checkPriceValidation(){
        ProductForm form = new ProductForm(types, brands);
        form.setProduct(product);
        assertTrue(form.binder.isValid());
        assertTrue(form.save.isEnabled());
        form.price.setValue("0");
        assertFalse(form.binder.isValid());
        assertFalse(form.save.isEnabled());
    }

    @Test
    public void checkDiscountValidation(){
        ProductForm form = new ProductForm(types, brands);
        form.setProduct(product);
        assertTrue(form.binder.isValid());
        assertTrue(form.save.isEnabled());
        form.discount.setValue("-1");
        assertFalse(form.binder.isValid());
        assertFalse(form.save.isEnabled());
    }
}
