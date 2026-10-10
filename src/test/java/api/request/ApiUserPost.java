package api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

//класс для создания API юзера
@AllArgsConstructor
@Data
@Builder
public class ApiUserPost {

    private String userName;
    private String type;
    private String authMethod;
    private Boolean isActive;
    private List<String> rolesIds;
}
