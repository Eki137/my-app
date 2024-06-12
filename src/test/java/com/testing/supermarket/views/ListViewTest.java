package com.testing.supermarket.views;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.testing.supermarket.data.Product;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    private Product getFirstItem(Grid<Product> grid) {
        return ( (ListDataProvider<Product>) grid.getDataProvider()).getItems().iterator().next();
    }


}
