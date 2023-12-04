import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page.object.LoginPage;
import ru.yandex.praktikum.page.object.MainPage;
import ru.yandex.praktikum.page.object.ProfilePage;
import ru.yandex.praktikum.page.object.RegPage;
import ru.yandex.praktikum.user.UserAPI;
import ru.yandex.praktikum.user.UserCredentials;

import static org.junit.Assert.assertTrue;

public class ProfileTest extends WebDriverShell {

    MainPage objMainPage;
    LoginPage objLoginPage;
    RegPage objRegPage;
    ProfilePage objProfilePage;
    UserAPI userAPI = new UserAPI();
    String token;

    @Before
    @DisplayName("Создать нового пользователя")
    public void createUser() {
        Response response = userAPI.create(UserCredentials.user);
        token = response.then().extract().path("accessToken");
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objRegPage = new RegPage(driver);
        objProfilePage = new ProfilePage(driver);
    }

    @After
    @DisplayName("Удаление созданного пользователя")
    public void deleteUser(){
        userAPI.login(UserCredentials.from(UserCredentials.user));
        userAPI.delete(token);
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void checkGetPersonalCabinetPage() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.clickPersonalCabinetButton();
        objProfilePage.waitForLoadPage();
        boolean isButtonVisible = objProfilePage.isLogoutLinkVisible();
        assertTrue(isButtonVisible);
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета (по кнопке)")
    public void checkGetConstructorPageByConstructorButton() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.clickPersonalCabinetButton();
        objProfilePage.clickConstructButton();
        objMainPage.waitForLoadHomePage();
        boolean isButtonVisible = objMainPage.isIngredientsContainerVisible();
        assertTrue(isButtonVisible);
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета (по логотипу)")
    public void getConstructorPageByLogo() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.clickPersonalCabinetButton();
        objProfilePage.clickLogo();
        objMainPage.waitForLoadHomePage();
        boolean isButtonVisible = objMainPage.isIngredientsContainerVisible();
        assertTrue(isButtonVisible);
    }

    @Test
    @DisplayName("Выход из профиля")
    public void checkQuitFromProfile() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.clickPersonalCabinetButton();
        objProfilePage.clickLogoutButton();
        objLoginPage.waitForLoadLoginPage();
        boolean isButtonVisible = objLoginPage.isLoginButtonVisible();
        assertTrue(isButtonVisible);
    }

}
