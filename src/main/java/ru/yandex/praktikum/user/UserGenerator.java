package ru.yandex.praktikum.user;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {

    public String fakeUserName() { return RandomStringUtils.randomAlphabetic(10); }

    public String fakeUserEmail(){
        return (RandomStringUtils.randomAlphabetic(10) + "@" + RandomStringUtils.randomAlphabetic(5) + ".com")
                .toLowerCase();
    }

    public String fakeUserPassword(int Count) { return RandomStringUtils.randomAlphabetic(Count); }

}
