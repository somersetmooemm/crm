package api.tests;

import annotations.Run;
import api.assertions.Conditions;
import api.contollers.AuthApiController;
import api.contollers.CrmUserController;
import api.dto.ApiUser;
import api.enums.StatusCode;
import api.request.UserCrmPost;
import db.CrmDbClient;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.testit.annotations.ExternalId;
import ru.testit.annotations.Title;
import ru.testit.annotations.WorkItemId;
import utils.CreateRandomData;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CrmTest extends BaseCrmTest {
    @Test
    @Run
    @ExternalId("АТ-3")
    @WorkItemId("3")
    @Title("API")
    //@RetryingTest(maxAttempts = 3, suspendForMs = 500, name  = "{displayName} | {index}")
    @ru.testit.annotations.DisplayName("Тест - Проверка конкретного юзера в списке юзеров")
    @DisplayName("Проверка конкретного юзера в списке юзеров")
    public void testGetAllCrmUser() {
        CrmUserController.getAllUsers()
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()))
                .should(Conditions.hasBody("list[0].userName", Matchers.equalTo("admin")))
                .should(Conditions.hasBody("list[0].name", Matchers.equalTo("Admin")))
                .should(Conditions.hasBody("list[0].type", Matchers.equalTo("admin")))
                .should(Conditions.hasBody("list[0].isActive", Matchers.equalTo(true)));
    }

    @Test
    @Run
    @ExternalId("АТ-10")
    @WorkItemId("10")
    @Title("API")
    //@RetryingTest(maxAttempts = 3, suspendForMs = 500, name  = "{displayName} | {index}")
    @ru.testit.annotations.DisplayName("Тест - Логин под юзером с API key и проверка ответа")
    @DisplayName("Логин под юзером с API key и проверка ответа")
    public void userProfileTest() {
        AuthApiController.getUser()
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()));

        ApiUser dbUser = CrmDbClient.getUserFromDb();
        assertThat(dbUser).as("API-пользователь в БД").isNotNull();

        AuthApiController.getUser()
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()))
                .should(Conditions.hasBody("user.userName", Matchers.equalTo(dbUser.getUserName())))
                .should(Conditions.hasBody("user.firstName", Matchers.equalTo(dbUser.getFirstName())))
                .should(Conditions.hasBody("user.lastName", Matchers.equalTo(dbUser.getLastName())))
                .should(Conditions.hasBody("user.type", Matchers.equalTo(dbUser.getType())))
                .should(Conditions.hasBody("user.isActive", Matchers.equalTo(dbUser.getIsActive())));
    }

    @Test
    @ExternalId("АТ-11")
    @WorkItemId("11")
    @Run
    @Title("API")
    //@RetryingTest(maxAttempts = 3, suspendForMs = 500, name  = "{displayName} | {index}")
    @ru.testit.annotations.DisplayName("Тест - Создание нового пользователя")
    @DisplayName("Создание нового пользователя")
    public void testPostCreateNewUser() {
        UserCrmPost newUser = CreateRandomData.getRandomCrmUser();
        CrmUserController.postNewUsers(newUser)
                .should(Conditions.hasStatusCode(StatusCode.OK.getCode()))
                .should(Conditions.hasBody("salutationName", Matchers.equalTo(newUser.getSalutationName())))
                .should(Conditions.hasBody("firstName", Matchers.equalTo(newUser.getFirstName())))
                .should(Conditions.hasBody("lastName", Matchers.equalTo(newUser.getLastName())));
    }
}
