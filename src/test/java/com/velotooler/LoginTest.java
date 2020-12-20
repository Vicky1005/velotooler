package com.velotooler;

import com.velotooler.core.parser.XMLParser;
import com.velotooler.pages.ProfilePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    private XMLParser xmlParser = new XMLParser();

    @Test
    public void loginIsSuccessful() {
        ProfilePage profilePage = dashboardPage.goToProfile();
        Assertions.assertEquals(xmlParser.parseLogin("email"), profilePage.getUserEmail());
    }
}
