package api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiUser {

    private String userName;
    private String apiKey;
    private String firstName;
    private String lastName;
    private String type;
    private Boolean isActive;
}
