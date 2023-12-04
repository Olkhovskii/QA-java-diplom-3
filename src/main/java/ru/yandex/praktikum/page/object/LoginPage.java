package ru.yandex.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final By loginText = By.xpath(".//main/div/h2");
    private final By emailField = By.xpath(".//*[@type='text']");
    private final By passwordField = By.xpath(".//*[@type='password']");
    private final By loginButton = By.xpath("//div/form/button");
    private final By registerLink = By.xpath("//div/p[1]/*[@href='/register']");
    private final By recoverLink = By.xpath("//div/p[2]/*[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание открытия страницы авторизации")
    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(loginText).isDisplayed());
    }

    @Step("Нажатие на кнопку Регистрация")
    public void clickRegistrationLink() {
        waitForLoadLoginPage();
        driver.findElement(registerLink).click();
    }

    @Step("Нажатие на кнопку Восстановить пароль")
    public void clickRecoverLink() {
        waitForLoadLoginPage();
        driver.findElement(recoverLink).click();
    }

    @Step("Проверка отображения кнопки Войти")
    public boolean isLoginButtonVisible() {
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Заполнить поля авторизации")
    public void setUserData(String email, String password) {
        waitForLoadLoginPage();
        setLoginData(email, password);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void setLoginData(String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }

}
