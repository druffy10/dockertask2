package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;


public class DataHelper {
    private static final Faker faker = new Faker(new Locale("en"));

    private DataHelper() {

    }

    public static AuthInfo getAuthInfoWithTestData() {
        return new AuthInfo("vasya", "qwerty123");
    }

    private static String generateLogin() {
        return faker.name().username();
    }

    private static String generatePass() {
        return faker.internet().password();
    }

    public static AuthInfo generateUser() {
        return new AuthInfo(generateLogin(), generatePass());
    }

    public static VerificationCode generateCode() {
        return new VerificationCode(faker.numerify("######"));
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }
}
