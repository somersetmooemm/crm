package docs.fakestore.tests;

import api.enums.StatusCode;
import docs.fakestore.controllers.ProductController;
import docs.fakestore.controllers.UserController;
import docs.fakestore.data.CreateProduct;
import docs.fakestore.data.CreateRandomData;
import docs.fakestore.pojo.request.ProductPost;
import docs.fakestore.pojo.response.productList.Product;
import extensions.TestLoggingExtension;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@ExtendWith({TestLoggingExtension.class})
public class ApiTest extends BaseApiTest {

    @Test
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    public void testGetAllUsers() {
        final String expectedName = "john";
        log.info("Отправляем запрос на получение всех юзеров");
        UserController.getAllUsers()
                .statusCode(StatusCode.OK.getCode())
                .body("", Matchers.hasSize(10))
                .body("name.firstname", Matchers.hasItem(expectedName));

    }

    @Test
    @DisplayName("Проверка конкретного юзера при получении по id = {id}")
    public void testGetSingleUser() {
        int id = 5;
        log.info("Отправляем запрос на получение юзера по  id = {}", id);
        String city = "san Antonio";
        UserController.getSingleUser(id)
                .statusCode(StatusCode.OK.getCode())
                .body("id", Matchers.equalTo(id))
                .body("address.city", Matchers.equalTo(city))
                .body("address.zipcode", Matchers.matchesPattern("\\d{5}-\\d{4}"));

    }

    @Test
    @DisplayName("Создание нового юзера")
    public void testPostCreateUser() {
        log.info("Отправляем запрос на создание пользователя");
        UserController.postCreateUser(CreateRandomData.getRandomBodyUserPost())
                .statusCode(StatusCode.CREATED.getCode())
                .body("id", Matchers.notNullValue());
    }

    @Test
    @DisplayName("Проверка конкретного продукта по описанию в списке продуктов")
    public void testGetAllProducts() {
        log.info("Отправляем запрос на проверку конкретного продукта");
        String expectedDescription = "From our Legends Collection, the Naga was inspired by the mythical water dragon that protects the ocean's pearl. Wear facing inward to be bestowed with love and abundance, or outward for protection.";
        List<Product> productsResponse = ProductController.getAllProduct()
                .statusCode(StatusCode.OK.getCode())
                .extract()
                .jsonPath()
                .getList(".", Product.class);

        Product foundProduct = productsResponse.stream()
                .filter(product -> product.getDescription()
                        .equals(expectedDescription))
                .findFirst()
                .orElse(null);
        assertNotNull(foundProduct, "Продукт с указанным Description не найден");
    }

    @Test
    @DisplayName("Создание нового продукта")
    public void testPostCreateProduct() {
        log.info("Отправляем запрос создание нового продукта");
        ProductPost newProduct = CreateProduct.getProduct();
        String title = newProduct.getTitle();
        ProductController.postCreateProduct(newProduct)
                .statusCode(StatusCode.CREATED.getCode())
                .body("title", Matchers.equalTo(title));
    }
}
