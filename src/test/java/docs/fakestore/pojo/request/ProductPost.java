package docs.fakestore.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductPost {
    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
}
