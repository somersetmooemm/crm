package docs.fakestore.data;

import docs.fakestore.pojo.request.ProductPost;

import static utils.RandomUtils.FAKER;

public class CreateProduct {

    public static ProductPost getProduct() {
        return ProductPost.builder()
                .id(FAKER.number().randomDigit())
                .title(FAKER.commerce().productName())
                .price(Double.parseDouble(FAKER.commerce().price(10.0, 500.0)))
                .description(FAKER.lorem().paragraph(5))
                .category(FAKER.commerce().department())
                .image(FAKER.internet().image())
                .build();
    }
}
