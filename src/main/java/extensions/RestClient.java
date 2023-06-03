package extensions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {
    //настройки
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/";
    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";
    public static final String RECOVER_PASSWORD_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    public static final String PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";
    public static final String REGISTRATION_PAGE = "https://stellarburgers.nomoreparties.site/register";
    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

}