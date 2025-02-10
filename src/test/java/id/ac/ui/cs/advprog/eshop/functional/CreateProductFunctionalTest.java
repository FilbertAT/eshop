package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
public class CreateProductFunctionalTest {
    /*
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */
    @LocalServerPort
    private int serverPort;

    /*
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;

    @BeforeEach
    void setUpTest(){
        baseUrl=String.format("%s:%d",testBaseUrl, serverPort);
    }

    @AfterEach
    void cleanup(ChromeDriver driver) {
        driver.get(baseUrl + "/product/list");
        while (pageSourceContains(driver, "Delete")) {
            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Delete"))).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        }
        driver.quit();
    }

    @Nested
    class ProductListPageTests {
        @Test
        void productListPage_isDisplayed(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/list");
            assertPageTitle(driver, "Product List");
        }

        @Test
        void productListPage_hasCreateButton(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/list");
            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Create Product"))).click();
            assertPageTitle(driver, "Create New Product");
        }
    }

    @Nested
    class CreateProductPageTests {
        @Test
        void createProductPage_isDisplayed(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            assertPageTitle(driver, "Create New Product");
        }

        @Test
        void createProductPage_hasCorrectFormFields(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");

            assertFormAction(driver, baseUrl + "/product/create");
            assertFormFieldNotNull(driver, "nameInput");
            assertFormFieldNotNull(driver, "quantityInput");
        }

        @Test
        void createProductPage_canCreateProduct(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");

            driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
            driver.findElement(By.id("quantityInput")).sendKeys("369");
            driver.findElement(By.tagName("form")).submit();

            assertPageTitle(driver, "Product List");

            driver.get(baseUrl + "/product/list");
            assertEquals(true, pageSourceContains(driver, "Hulahup Domba Hitam"));
            assertEquals(true, pageSourceContains(driver, "369"));
        }
    }

    @Nested
    class EditProductPageTests {
        @Test
        void editProductPage_isDisplayed(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
            driver.findElement(By.id("quantityInput")).sendKeys("369");
            driver.findElement(By.tagName("form")).submit();

            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
            assertPageTitle(driver, "Edit Product");
        }

        @Test
        void editProductPage_hasCorrectFormFields(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
            driver.findElement(By.id("quantityInput")).sendKeys("369");
            driver.findElement(By.tagName("form")).submit();

            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
            assertPageTitle(driver, "Edit Product");

            assertFormFieldNotNull(driver, "nameInput");
            assertFormFieldNotNull(driver, "quantityInput");
            assertEquals("Hulahup Domba Hitam", getFormFieldValue(driver, "nameInput"));
            assertEquals("369", getFormFieldValue(driver, "quantityInput"));
        }

        @Test
        void editProductPage_canEditProduct(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Hulahup Domba Hitam");
            driver.findElement(By.id("quantityInput")).sendKeys("369");
            driver.findElement(By.tagName("form")).submit();

            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
            driver.findElement(By.id("nameInput")).clear();
            driver.findElement(By.id("nameInput")).sendKeys("Maya Tilis");
            driver.findElement(By.id("quantityInput")).clear();
            driver.findElement(By.id("quantityInput")).sendKeys("666666");
            driver.findElement(By.tagName("form")).submit();

            assertPageTitle(driver, "Product List");

            driver.get(baseUrl + "/product/list");
            assertEquals(true, pageSourceContains(driver, "Maya Tilis"));
            assertEquals(true, pageSourceContains(driver, "666666"));
        }
    }

    @Nested
    class DeleteProductTests {
        @Test
        void deleteProduct(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Prabowo Coblos Nomor 2 Official T-Shirt");
            driver.findElement(By.id("quantityInput")).sendKeys("7171733");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/list");
            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Delete"))).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            assertPageTitle(driver, "Product List");
            assertEquals(false, pageSourceContains(driver, "Prabowo Coblos Nomor 2 Official T-Shirt"));
        }

        @Test
        void deleteFirstProduct(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Prabowo Coblos Nomor 2 Official T-Shirt");
            driver.findElement(By.id("quantityInput")).sendKeys("7171733");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Figurine di Meja Kerja Gibran di Solo");
            driver.findElement(By.id("quantityInput")).sendKeys("98");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/list");
            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Delete"))).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            assertPageTitle(driver, "Product List");
            assertEquals(false, pageSourceContains(driver, "Prabowo Coblos Nomor 2 Official T-Shirt"));
            assertEquals(true, pageSourceContains(driver, "Figurine di Meja Kerja Gibran di Solo"));
        }

        @Test
        void deleteLastProduct(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Prabowo Coblos Nomor 2 Official T-Shirt");
            driver.findElement(By.id("quantityInput")).sendKeys("7171733");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Figurine di Meja Kerja Gibran di Solo");
            driver.findElement(By.id("quantityInput")).sendKeys("98");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/list");
            driver.findElement(By.xpath(String.format("(//*[text()='%s'])[last()]", "Delete"))).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            assertPageTitle(driver, "Product List");
            assertEquals(true, pageSourceContains(driver, "Prabowo Coblos Nomor 2 Official T-Shirt"));
            assertEquals(false, pageSourceContains(driver, "Figurine di Meja Kerja Gibran di Solo"));
        }

        @Test
        void deleteEditedProduct(ChromeDriver driver) throws Exception {
            driver.get(baseUrl + "/product/create");
            driver.findElement(By.id("nameInput")).sendKeys("Pokemon");
            driver.findElement(By.id("quantityInput")).sendKeys("7171733");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/list");
            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Edit"))).click();
            driver.findElement(By.id("nameInput")).clear();
            driver.findElement(By.id("nameInput")).sendKeys("Indonesia Cemas");
            driver.findElement(By.id("quantityInput")).clear();
            driver.findElement(By.id("quantityInput")).sendKeys("98");
            driver.findElement(By.tagName("form")).submit();

            driver.get(baseUrl + "/product/list");
            driver.findElement(By.xpath(String.format("//*[text()='%s']", "Delete"))).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
            assertPageTitle(driver, "Product List");
            assertEquals(false, pageSourceContains(driver, "Indonesia Cemas"));
        }
    }

    //////////////////////////////
    // Helper methods for tests //
    //////////////////////////////


    private String getPageTitle(ChromeDriver driver) {
        return driver.getTitle();
    }

    private void assertPageTitle(ChromeDriver driver, String expectedTitle) {
        String pageTitle = getPageTitle(driver);
        assertEquals(expectedTitle, pageTitle);
    }

    private void assertFormFieldNotNull(ChromeDriver driver, String fieldId) {
        assertNotNull(driver.findElement(By.id(fieldId)));
    }

    private String getFormFieldValue(ChromeDriver driver, String fieldId) {
        return driver.findElement(By.id(fieldId)).getAttribute("value");
    }

    private void assertFormAction(ChromeDriver driver, String expectedAction) {
        String formAction = driver.findElement(By.tagName("form")).getAttribute("action");
        assertEquals(expectedAction, formAction);
    }

    private boolean pageSourceContains(ChromeDriver driver, String expectedContent) {
        String pageSource = driver.getPageSource();
        return pageSource.contains(expectedContent);
    }
}