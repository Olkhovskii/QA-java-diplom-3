import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.page.object.LoginPage;
import ru.yandex.praktikum.page.object.MainPage;
import ru.yandex.praktikum.page.object.RecPassPage;
import ru.yandex.praktikum.page.object.RegPage;
import ru.yandex.praktikum.user.UserAPI;
import ru.yandex.praktikum.user.UserCredentials;
import static org.junit.Assert.assertTrue;

public class LoginTest extends WebDriverShell {

    MainPage objMainPage;
    LoginPage objLoginPage;
    RegPage objRegPage;
    RecPassPage objRecPassPage;
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
        objRecPassPage = new RecPassPage(driver);
    }

    @After
    @DisplayName("Удаление созданного пользователя")
    public void deleteUser(){
        userAPI.login(UserCredentials.from(UserCredentials.user));
        userAPI.delete(token);
    }

    @Test
    @DisplayName("Вход по кнопке Войти")
    public void checkUserAuthByLoginButton() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.waitForLoadHomePage();
        boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
        assertTrue(isButtonVisible);
    }

    @Test
    @DisplayName("Вход через личный кабинет")
    public void userAuthByPersonalCabinetButton() {
        objMainPage.openMainPage();
        objMainPage.clickPersonalCabinetButton();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.waitForLoadHomePage();
        boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
        assertTrue(isButtonVisible);
    }

    @Test
    @DisplayName("Вход через регистрацию")
    public void userAuthFromRegPage() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.clickRegistrationLink();
        objRegPage.clickLoginLink();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.waitForLoadHomePage();
        boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
        assertTrue(isButtonVisible);
    }

    @Test
    @DisplayName("Вход через страницу восстановления пароля")
    public void userAuthFromRecoverPasswordPage() {
        objMainPage.openMainPage();
        objMainPage.clickLoginButton();
        objLoginPage.clickRecoverLink();
        objRecPassPage.clickLoginLink();
        objLoginPage.setUserData(UserCredentials.fakeEmail, UserCredentials.fakePassword);
        objMainPage.waitForLoadHomePage();
        boolean isButtonVisible = objMainPage.isCreateOrderButtonDisplayed();
        assertTrue(isButtonVisible);
    }

}
