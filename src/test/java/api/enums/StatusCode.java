package api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    OK(200),
    CREATED(201),
    BAD_REQUEST(400);

    private final int code;
}
