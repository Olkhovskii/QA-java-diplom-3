package ru.yandex.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegPage {

    private WebDriver driver;

    private final By regText = By.xpath(".//*[text()='Регистрация']");
    private final By nameField = By.xpath("//div/form/fieldset[1]//input[@name='name']");
    private final By emailField = By.xpath("//div/form/fieldset[2]//input[@name='name']");
    private final By passwordField = By.xpath("//div/form/fieldset[3]//input[@name='Пароль']");
    private final By regButton = By.xpath(".//*[text()='Зарегистрироваться']");
    private final By wrongPasswordText = By.xpath(".//p[text() = 'Некорректный пароль']");
    private final By loginLink = By.xpath("//main/div/div/p/*[@href='/login']");

    public RegPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание открытия страницы регистрации")
    public void waitForLoadRegPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(regText).isDisplayed());
    }

    public void clickName() {
        driver.findElement(nameField).click();
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegButton() {
        driver.findElement (regButton).click();
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginLink() {
        waitForLoadRegPage();
        driver.findElement(loginLink).click();
    }

    @Step("Заполнение полей для регистрации")
    public void setRegData(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickName();
    }

    @Step("Проверка отображения надписи о некорректности пароля")
    public boolean isWrongPasswordTextVisible(){
        return driver.findElements(wrongPasswordText).size() > 0;
    }
}
