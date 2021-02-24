package com.velotooler;

import com.velotooler.core.model.Auth;
import com.velotooler.core.service.UserCreator;
import com.velotooler.pages.sidemenu.ProfilePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    @Tag("smoke")
    @Epic("IAMAPPS-2222")
    @Story("IAMAPPS-3296")
    @DisplayName("Login is successful")
    @Severity(SeverityLevel.NORMAL)
    public void loginIsSuccessful() {
        Auth auth = UserCreator.withCredentialsFromProperty();
        ProfilePage profilePage = dashboardPage.goToProfile();
        Assertions.assertEquals(auth.getUsername(), profilePage.getUserEmail());
    }
}
