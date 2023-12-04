import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.page.object.LoginPage;
import ru.yandex.praktikum.page.object.MainPage;
import ru.yandex.praktikum.page.object.RegPage;
import ru.yandex.praktikum.user.UserCredentials;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class RegistrationPasswordTest extends WebDriverShell {

    private final int passwordLength;
    private final boolean isCorrect;
    MainPage objMainPage;
    LoginPage objLoginPage;
    RegPage objRegPage;

    public RegistrationPasswordTest(int passwordLength, boolean isCorrect) {
        this.passwordLength = passwordLength;
        this.isCorrect = isCorrect;
    }

    @Parameterized.Parameters(name = "Длина пароля: {0}; Корректный: {1}")
    public static Object[] getData() {
        return new Object[][]{
                {0, true},
                {5, false},
                {6, true},
                {7, true},
        };
    }

    @Test
    @DisplayName("Проверка корректности пароля при регистрации")
    public void checkPasswordOnRegistration () {
        objMainPage = new MainPage(driver);
        objMainPage.openMainPage();
        objMainPage.waitForLoadHomePage();
        objMainPage.clickLoginButton();

        objLoginPage = new LoginPage(driver);
        objLoginPage.waitForLoadLoginPage();
        objLoginPage.clickRegistrationLink();

        objRegPage = new RegPage(driver);
        objRegPage.waitForLoadRegPage();

        String password = "";
        switch (passwordLength){
            case 0:
                password = UserCredentials.fakePassword0;
                break;
            case 5:
                password = UserCredentials.fakePassword5;
                break;
            case 6:
                password = UserCredentials.fakePassword6;
                break;
            case 7:
                password = UserCredentials.fakePassword7;
                break;
        }
        objRegPage.setRegData(UserCredentials.fakeName, UserCredentials.fakeEmail, password);

        boolean isTextVisible = objRegPage.isWrongPasswordTextVisible();
        assertEquals(isCorrect, !(isTextVisible));
    }

}
