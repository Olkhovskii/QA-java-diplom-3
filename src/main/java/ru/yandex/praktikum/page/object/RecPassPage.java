package ru.yandex.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecPassPage {

    private WebDriver driver;
    private final By recoverPasswordText = By.xpath(".//main/div/h2");
    private final By loginLink = By.xpath(".//main/div/div/p/*[@href='/login']");

    public RecPassPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание открытия страницы восстановления пароля")
    public void waitForLoadRecoverPasswordPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> driver.findElement(recoverPasswordText).isDisplayed());
    }

    @Step("Нажатие на кнопку Войти")
    public void clickLoginLink() {
        waitForLoadRecoverPasswordPage();
        driver.findElement(loginLink).click();
    }

}
