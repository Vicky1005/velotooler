package com.velotooler;

import com.velotooler.core.model.Auth;
import com.velotooler.core.service.UserCreator;
import com.velotooler.pages.sidemenu.ProfilePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    @Tag("smoke")
    public void loginIsSuccessful() {
        Auth auth = UserCreator.withCredentialsFromProperty();
        ProfilePage profilePage = dashboardPage.goToProfile();
        Assertions.assertEquals(auth.getUsername(), profilePage.getUserEmail());
    }
}
