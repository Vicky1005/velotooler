package com.velotooler.core.service;

import com.velotooler.core.model.Auth;

public class UserCreator {

    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";

    public static Auth withCredentialsFromProperty(){
        return new Auth(TestDataReader.getTestData(TESTDATA_USER_NAME),
                TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static Auth withEmptyUsername(){
        return new Auth("", TestDataReader.getTestData(TESTDATA_USER_PASSWORD));
    }

    public static Auth withEmptyPassword(){
        return new Auth(TestDataReader.getTestData(TESTDATA_USER_NAME), "");
    }
}
