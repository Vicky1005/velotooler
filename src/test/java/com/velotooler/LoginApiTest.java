package com.velotooler;

import com.velotooler.core.model.Auth;
import com.velotooler.pages.sidemenu.ProfilePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginApiTest extends BaseTest {

    @Test
    public void loginIsSuccessful() {
        Auth auth = parser.get("logIn", Auth.class);
        ProfilePage profilePage = dashboardPage.goToProfile();
        Assertions.assertEquals(auth.getUsername(), profilePage.getUserEmail());
    }
}
