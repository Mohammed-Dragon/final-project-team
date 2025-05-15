package Tests;

import Base.OpenDriver;
import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInTests extends OpenDriver {

    @Test(priority = 1)
    public void TC1_LoginFormOpens() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.isLoginFormVisible(), "Login form did not appear.");
    }

    @Test(priority = 2)
    public void TC2_SuccessfulLoginAndLogout() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("buvufg5r4e87t75ybjhvjfxrgstr3wtu0kygk61");
        loginPage.enterPassword("Password123!");
        loginPage.clickLoginButton();
        loginPage.logoutIfVisible();
    }

    @Test(priority = 3)
    public void TC3_EmptyFieldsTriggerAlert() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clearFields();
        loginPage.clickLoginButton();
        String alertText = loginPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
    }

    @Test(priority = 4)
    public void TC4_EmptyUsernameOnly() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("");
        loginPage.enterPassword("Password123!");
        loginPage.clickLoginButton();
        String alertText = loginPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
    }

    @Test(priority = 5)
    public void TC5_EmptyPasswordOnly() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("buvufg5r4e87t75ybjhvjfxrgstr3wtu0kygk61");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        String alertText = loginPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
    }

    @Test(priority = 6)
    public void TC6_InvalidUsername() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("buvufg5r4e87t75ybjhvjfxrgstr3wtu0kygk6hvjhbuibi1");
        loginPage.enterPassword("Password123!");
        loginPage.clickLoginButton();
        String alertText = loginPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "User does not exist.");
    }

    @Test(priority = 7)
    public void TC7_InvalidPassword() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("buvufg5r4e87t75ybjhvjfxrgstr3wtu0kygk61");
        loginPage.enterPassword("zzz");
        loginPage.clickLoginButton();
        String alertText = loginPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "Wrong password.");
    }

    @Test(priority = 8)
    public void TC8_InvalidCredentials() {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("buvufg5r4e87t75ybjhvjfxrgstr3wtu0kygk6dytf1");
        loginPage.enterPassword("zzz");
        loginPage.clickLoginButton();
        String alertText = loginPage.getAlertTextAndAccept();
        Assert.assertEquals(alertText, "User does not exist.");
    }

    @Test(priority = 9)
    public void TC9_CloseLoginForm() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.openLoginForm();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickCloseButton();
        Assert.assertTrue(loginPage.isLoginModalClosed(), "Login form did not close properly.");
    }
}
