package ru.yandex.praktikum.user;

public class UserCredentials {

    private String email;
    private String password;

    static UserGenerator userGenerator = new UserGenerator();

    public static String fakeName = userGenerator.fakeUserName();
    public static String fakeEmail = userGenerator.fakeUserEmail();
    public static String fakePassword = userGenerator.fakeUserPassword(6);
    public static String fakePassword0 = userGenerator.fakeUserPassword(0);
    public static String fakePassword5 = userGenerator.fakeUserPassword(5);
    public static String fakePassword6 = userGenerator.fakeUserPassword(6);
    public static String fakePassword7 = userGenerator.fakeUserPassword(7);

    public UserCredentials() {
    }

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static UserCredentials from(User user){
        return new UserCredentials(user.getEmail(), user.getPassword());
    }

    public static User user = new User(fakeName, fakeEmail, fakePassword);

}
