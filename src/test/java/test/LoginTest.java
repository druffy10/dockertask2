package test;

import data.DBHelper;
import data.DataHelper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static data.DBHelper.cleanDB;

public class LoginTest {
    @AfterAll
    static void teardown() {
        cleanDB();
    }

    @Test
    void shouldSuccessLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = DBHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    void shouldGetErrorWithGenerateLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisible();
    }

    @Test
    void shouldGetErrorWithGenerateCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = DataHelper.generateCode();
        verificationPage.verify(verificationCode.getCode());
        verificationPage.verifyErrorNotificationVisible();
    }
}
