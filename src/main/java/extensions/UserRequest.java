package extensions;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;


public class UserRequest extends RestClient {
    private final static String USER_REG_PATH = "/api/auth/register";
    private final static String USER_LOGIN = "/api/auth/login";
    private final static String USER = "/api/auth/user";
    @Step("Успешный запрос на создание пользователя")
    public String assertUserCreatedSuccessfully(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract().path("accessToken");
    }

    @Step("Успешное удаление пользователя")
    public void assertUserDeleteSuccessfully(ValidatableResponse response) {
        response.assertThat()
                .statusCode(202)
                .body("message", equalTo("User successfully removed"));
    }

    @Step("Ответ при успешной авторизации")
    public String assertUserAuthorizationIsSuccess(ValidatableResponse response) {
        return response.assertThat()
                .statusCode(200)
                .body("success", is(true))
                .extract().path("accessToken");

    }

    @Step("Запрос на регистрация пользователя")

    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getBaseSpec())
                .body(user)
                .when()
                .post(USER_REG_PATH)
                .then();
    }

    @Step("Запрос на авторизацию")
    public ValidatableResponse loginUser(UserCredentials credentials) {
        return given()
                .spec(getBaseSpec())
                .when()
                .body(credentials)
                .post(USER_LOGIN)
                .then();
    }


    //DELETE https://stellarburgers.nomoreparties.site/api/auth/user
    @Step("Запрос на удаление пользователя")
    public ValidatableResponse deleteUser(String token) {
        return given()
                .spec(getBaseSpec())
                .header("Authorization", token)
                .when()
                .delete(USER)
                .then();
    }
}
