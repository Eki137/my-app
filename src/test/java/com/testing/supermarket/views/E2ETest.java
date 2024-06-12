package com.testing.supermarket.views;

import com.vaadin.flow.component.button.Button;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class E2ETest {

    @Test
    public void checkPageLoad() {
        WebDriverManager.edgedriver().setup();
        WebDriver driver = new EdgeDriver();

        try {
            driver.get("http://localhost:8181");

            new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                    .until(titleIs("Inventory"));

            WebElement button = driver.findElement(By.xpath("//vaadin-button[contains(.,'Add product')]"));

            assertEquals(button.getText(), "Add product");
        } finally {
            driver.quit();
        }

    }
}
