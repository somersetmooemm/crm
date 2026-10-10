package docs.fakestore.pojo.request;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserPost {
    private String username;
    private String email;
    private String password;
}
