package com.testing.supermarket.views;

import com.testing.supermarket.data.Product;
import com.testing.supermarket.services.InventoryService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.button.Button;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * The main view is a top-level placeholder for other views.
 */
@Component
@Scope("prototype")
@Route(value = "")
@PageTitle("Inventory")
public class ListView extends VerticalLayout {
    Grid<Product> grid = new Grid<>(Product.class);
    TextField filterText = new TextField();
    Button addProductButton = new Button();
    ProductForm form;
    InventoryService service;

    public ListView(InventoryService service) {
        this.service = service;
        addClassName("list-view");
        setSizeFull();
        configureGrid();
        configureForm();

        add(getToolbar(), getContent());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setProduct(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(service.findAllProducts(filterText.getValue()));
    }

    private HorizontalLayout getContent() {
        HorizontalLayout content = new HorizontalLayout(grid, form);
        content.setFlexGrow(2, grid);
        content.setFlexGrow(1, form);
        content.addClassNames("content");
        content.setSizeFull();
        return content;
    }

    private void configureForm() {
        form = new ProductForm(service.findAllTypes(), service.findAllBrands());
        form.setWidth("25em");
        form.addSaveListener(this::saveProduct);
        form.addDeleteListener(this::deleteProduct);
        form.addCloseListener(e -> closeEditor());
    }

    private void deleteProduct(ProductForm.DeleteEvent event) {
        service.deleteProduct(event.getProduct());
        updateList();
        closeEditor();
    }

    private void saveProduct(ProductForm.SaveEvent event) {
        service.saveProduct(event.getProduct());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by description...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        addProductButton.setText("Add Product");
        addProductButton.addClickListener(click -> addProduct());

        var toolbar = new HorizontalLayout(filterText, addProductButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addProduct() {
        grid.asSingleSelect().clear();
        editProduct(new Product());
    }

    private void configureGrid() {
        grid.addClassNames("product-grid");
        grid.setSizeFull();
        grid.setColumns("description", "stock", "price", "discount");
        grid.addColumn(product -> product.getType().getName()).setHeader("Type");
        grid.addColumn(product -> product.getBrand().getName()).setHeader("Brand");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.asSingleSelect().addValueChangeListener(event -> editProduct(event.getValue()));
    }

    private void editProduct(Product product) {
        if (product == null) {
            closeEditor();
        } else {
            form.setProduct(product);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}
