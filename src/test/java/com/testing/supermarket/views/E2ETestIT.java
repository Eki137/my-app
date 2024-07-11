package com.testing.supermarket.views;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class E2ETestIT {

    private WebDriver driver;

    @BeforeEach
    public void setup(){
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.get("http://localhost:8181");
        new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                .until(titleIs("Inventory"));
    }

    @Test
    public void checkPageLoad() {
        try {
            WebElement button = driver.findElement(By.xpath("//vaadin-button[contains(.,'Add Product')]"));

            assertEquals("Add Product", button.getText());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testFilter() throws InterruptedException{
        try {
            WebElement filterField = driver.findElement(By.xpath("//vaadin-text-field"));
            WebElement productGrid = driver.findElement(By.className("product-grid"));
            assertTrue(productGrid.getText().contains("VACIO"));
            filterField.sendKeys("ser");
            Thread.sleep(1000);
            assertTrue(productGrid.getText().contains("SERENITO") && productGrid.getText().contains("SER"));
            assertFalse(productGrid.getText().contains("VACIO"));
        } finally {
            driver.quit();
        }
    }

    @Test
    public void addProduct() throws InterruptedException{
        try {
            WebElement addButton = driver.findElement(By.xpath("//vaadin-button[contains(.,'Add Product')]"));
            addButton.click();
            Thread.sleep(1000);

            WebElement descriptionField = driver.findElement(By.xpath("//vaadin-form-layout//vaadin-text-field[contains(.,'Description')]"));
            descriptionField.sendKeys("Costillar");

            WebElement stockField = driver.findElement(By.xpath("//vaadin-form-layout//vaadin-text-field[contains(.,'Stock')]"));
            stockField.sendKeys("17");

            WebElement priceField = driver.findElement(By.xpath("//vaadin-form-layout//vaadin-text-field[contains(.,'Price')]"));
            priceField.sendKeys("6000");

            WebElement typeComboBox = driver.findElement(By.xpath("//vaadin-combo-box[contains(.,'Type')]"));
            Thread.sleep(1000);
            typeComboBox.click();
            Thread.sleep(1000);
            WebElement typeOption = driver.findElement(By.xpath("//vaadin-combo-box-item[contains(.,'Meat')]"));
            Thread.sleep(1000);
            typeOption.click();
            Thread.sleep(1000);

            WebElement brandComboBox = driver.findElement(By.xpath("//vaadin-combo-box[contains(.,'Brand')]"));
            brandComboBox.click();
            Thread.sleep(1000);
            WebElement brandOption = driver.findElement(By.xpath("//vaadin-combo-box-item[contains(.,'Estancia')]"));
            Thread.sleep(1000);
            brandOption.click();

            WebElement discountField = driver.findElement(By.xpath("//vaadin-form-layout//vaadin-text-field[contains(.,'Discount')]"));
            discountField.sendKeys("0.4");

            WebElement saveButton = driver.findElement(By.xpath("//vaadin-button[contains(.,'Save')]"));
            saveButton.click();

            new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                    .until(invisibilityOf(saveButton));

            WebElement productGrid = driver.findElement(By.className("product-grid"));
            assertTrue(productGrid.getText().contains("Costillar"));

        } finally {
            driver.quit();
        }
    }

}
