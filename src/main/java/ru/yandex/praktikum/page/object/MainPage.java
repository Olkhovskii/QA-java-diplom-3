package ru.yandex.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";
    private final By homeText = By.xpath(".//main/section[1]/h1");
    private final By loginButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private final By personalCabinetButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By createOrderButton = By.xpath(".//button[text() = 'Оформить заказ']");
    private final By constructorButton = By.xpath(".//p[text() = 'Конструктор']");
    private final By ingredientsContainer = By.className("constructor-element");
    private final By headerBuns = By.xpath(".//span[text() = 'Булки']");
    private final By headerSauce = By.xpath(".//span[text() = 'Соусы']");
    private final By headerFilling = By.xpath(".//span[text() = 'Начинки']");
    private final By sectionCurrent = By.xpath(".//div[contains(@class,'tab_tab_type_current')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Открытие главной страницы")
    public void openMainPage() { driver.get(MAIN_URL); }

    @Step("Ожидание открытия главной страницы")
    public void waitForLoadHomePage() {
        new WebDriverWait(driver,  Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(homeText));
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public void clickLoginButton() { driver.findElement(loginButton).click(); }

    @Step("Нажатие на кнопку Личный кабинет")
    public void clickPersonalCabinetButton() {  driver.findElement(personalCabinetButton).click(); }

    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructorButton() { driver.findElement(constructorButton).click(); }

    @Step("Проверка Авторизации, кнопка оформления заказа отображается")
    public boolean isCreateOrderButtonDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }

    @Step("Проверка отображения состава ингредиентов")
    public boolean isIngredientsContainerVisible() {
        return driver.findElement(ingredientsContainer).isDisplayed();
    }

    @Step("Нажатие на секцию Булки")
    public void clickSectionBuns() {
        driver.findElement(headerBuns).click();
    }

    @Step("Нажатие на секцию Соусы")
    public void clickSectionSauce() {
        driver.findElement(headerSauce).click();
    }

    @Step("Нажатие на секцию Начинки")
    public void clickSectionFilling() {
        driver.findElement(headerFilling).click();
    }

    @Step("Проверка, что секция Булки текущая")
    public boolean isCurrentSectionBuns() {
        return "Булки".equals(getCurrentSectionType());
    }

    @Step("Проверка, что секция Соусы текущая")
    public boolean isCurrentSectionSauce() {
        return "Соусы".equals(getCurrentSectionType());
    }

    @Step("Проверка, что секция Начинки текущая")
    public boolean isCurrentSectionFilling() {
        return "Начинки".equals(getCurrentSectionType());
    }

    @Step("Получение названия текущего раздела")
    public String getCurrentSectionType() {
        return driver.findElement(sectionCurrent).getText();
    }

}
