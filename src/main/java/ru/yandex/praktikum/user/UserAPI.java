package ru.yandex.praktikum.user;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.yandex.praktikum.page.object.MainPage;

import static io.restassured.RestAssured.given;

public class UserAPI {

    private static final String CREATE_USER_PATH = "api/auth/register";
    private static final String LOGIN_USER_PATH = "api/auth/login";
    public static final String DELETE_USER_PATH = "api/auth/user";

    @Step("Отправка запроса на создаение пользователя")
    public Response create(User user) {
        return given()
                .baseUri(MainPage.MAIN_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(CREATE_USER_PATH);
    }

    public Response login(UserCredentials user) {
        return given()
                .baseUri(MainPage.MAIN_URL)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_USER_PATH);
    }

    public void delete(String token){
        given()
                .baseUri(MainPage.MAIN_URL)
                .contentType("application/json")
                .header("Authorization", token)
                .body(UserCredentials.from(UserCredentials.user))
                .delete(DELETE_USER_PATH);
    }

}
