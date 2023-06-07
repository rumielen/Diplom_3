package extensions;

import org.apache.commons.lang3.RandomStringUtils;

public class UserGenerator {
    public static User getRandomUser() {

        final String email = RandomStringUtils.randomAlphabetic(10)+ "@yandex.ru";
        final String password = RandomStringUtils.randomAlphabetic(8);
        final String name = "Иван";
        return new User(email, password, name);
    }
}
