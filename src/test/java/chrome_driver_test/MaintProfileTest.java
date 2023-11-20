package chrome_driver_test;

import io.qameta.allure.junit4.DisplayName;
import org.example.User;
import org.example.UserClient;
import org.example.UserGenerator;
import org.example.page_object.LoginPage;
import org.example.page_object.MainPage;
import org.example.page_object.ProfilePage;
import org.example.page_object.RegisterPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class MaintProfileTest extends BaseTest {
    private final UserGenerator userGenerator = new UserGenerator();
    private final UserClient userClient = new UserClient();
    private User user;
    private String accessToken;

    @Before
    @DisplayName("Регистрация тестового аккаунта")
    public void accountRegistration() {
        user = UserGenerator.getDefault();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        String expected = "Вход";
        String actual = loginPage.getTitleTextInput();
        Assert.assertEquals("Не совершен выход из аккаунта пользователя", expected, actual);
        loginPage.login(user.getEmail(), user.getPassword());
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAreaButton();
    }

    @Test
    @DisplayName("Переход из личного кабинета в «Конструктор»")
    public void constructorButtonFromPersonalAccount() {
        ProfilePage accountProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickBurgerConstructorButton();
        MainPage mainPage = new MainPage(driver);
        String expected = "Соберите бургер";
        String actual = mainPage.getTitleTextAssembleBurger();
        Assert.assertEquals("Данные не совпадают", expected, actual);
    }

    @Test
    @DisplayName("Переход из личного кабинета в Лого")
    public void logoButtonFromPersonalAccount() {
        ProfilePage accountProfilePage = new ProfilePage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(accountProfilePage.getProfileSection()));
        accountProfilePage.clickLogoButton();
        MainPage mainPage = new MainPage(driver);
        assertTrue("Данные не совпадают", mainPage.checkoutButtonIsDisplayed());
    }

    @After
    public void accountDelete() {
        accessToken = userClient.getAccessTokenOnLogin(user);
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
    }
}