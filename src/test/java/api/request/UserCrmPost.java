package api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//класс для создания обычного юзера
@AllArgsConstructor
@Data
@Builder
public class UserCrmPost {

    private String firstName;
    private boolean isActive;
    private String lastName;
    private String name;
    private String salutationName;
    private String type;
    private String userName;
}
