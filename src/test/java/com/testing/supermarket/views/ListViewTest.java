package com.testing.supermarket.views;

import com.testing.supermarket.data.Brand;
import com.testing.supermarket.data.Product;
import com.testing.supermarket.data.Type;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.provider.Query;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ListViewTest {

    @Autowired
    private ListView listView;


    @Test
    public void formShownWhenContactSelected(){
        Grid<Product> grid = listView.grid;
        Product firstProduct = getFirstItem(grid);

        ProductForm form = listView.form;
        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstProduct);
        assertTrue(form.isVisible());
        assertEquals(firstProduct.getDescription(), form.description.getValue());
    }

    @Test
    public void deleteProductFromList(){
        Grid<Product> grid = listView.grid;
        Product firstProduct = getFirstItem(grid);

        ProductForm form = listView.form;
        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstProduct);
        assertTrue(form.isVisible());
        int expectedSize = ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>()) - 1;

        form.delete.click();
        int actualSize = ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>());

        assertEquals(expectedSize, actualSize);
        assertNotEquals(firstProduct, getFirstItem(grid));
    }

    @Test
    public void addProductToList(){
        Grid<Product> grid = listView.grid;
        ProductForm form = listView.form;

        listView.addProductButton.click();
        assertTrue(form.isVisible());

        form.description.setValue("Coca-Cola");
        form.stock.setValue("12");
        form.price.setValue("1200");
        form.discount.setValue("0.5");
        Type type = listView.service.findAllTypes().getFirst();
        Brand brand = listView.service.findAllBrands().getFirst();
        form.type.setValue(type);
        form.brand.setValue(brand);

        int expectedSize = ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>()) + 1;

        form.save.click();
        int actualSize = ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>());

        assertEquals(expectedSize, actualSize);
        assertEquals("Coca-Cola", getLastItem(grid).getDescription());
    }

    @Test
    public void modifyProductInList(){
        Grid<Product> grid = listView.grid;
        Product firstProduct = getFirstItem(grid);

        ProductForm form = listView.form;
        assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(firstProduct);
        assertTrue(form.isVisible());

        int expectedSize = ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>());
        form.description.setValue("Test");
        form.price.setValue("1");

        form.save.click();
        int actualSize = ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>());

        assertEquals(expectedSize, actualSize);
        assertEquals("Test", getFirstItem(grid).getDescription());
        assertEquals(1, getFirstItem(grid).getPrice());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void filteringList() {
        Grid<Product> grid = listView.grid;

        listView.filterText.setValue("sere");
        assertEquals(1, ( (ListDataProvider<Product>) grid.getDataProvider()).size(new Query<>()));
        assertEquals("SERENITO", getFirstItem(grid).getDescription());
    }

    private Product getFirstItem(Grid<Product> grid) {
        return ( (ListDataProvider<Product>) grid.getDataProvider()).getItems().iterator().next();
    }

    private Product getLastItem(Grid<Product> grid) {
        return ( (ListDataProvider<Product>) grid.getDataProvider()).getItems().stream().toList().getLast();
    }
}
