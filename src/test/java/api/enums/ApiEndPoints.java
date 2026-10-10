package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiEndPoints {

    USERS("/users"),
    USER_BY_ID("/users/{id}"),
    PRODUCTS("/products"),
    USERS_CRM("/api/v1/User"),
    AUTH_CRM("/api/v1/App/user"),
    ROLE_CRM("/api/v1/Role");

    private final String path;
}