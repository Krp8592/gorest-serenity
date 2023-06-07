package com.gorest.gorestest;

import com.gorest.goreststeps.UserSteps;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UserCURDTestWithSteps extends TestBase {
    @Steps
    UserSteps userSteps;
    static String name="Kaushik"+ TestUtils.getRandomValue();
    static String email="kaushik"+TestUtils.getRandomValue()+"@gmail.com";
    static String gender="male";
    static String status="active";
    static int id;
    @Title("This will create a new User")
    @Test
    public void test001(){
        ValidatableResponse response= userSteps.createUser(name,email,gender,status);
        response.log().all().statusCode(201);
        id=response.extract().path("id");
    }
    @Title("Verify if the User is added to application")
    @Test
    public void test002() {
        int uId = userSteps.getUserId(id);
        Assert.assertEquals(uId,id);
    }
    @Title("Update the student information and verify the updated information")
    @Test
    public void test003(){
        email="Milan"+TestUtils.getRandomValue()+"@gmail.com";
        userSteps.updateUser(id,email);
        int uId = userSteps.getUserId(id);
        Assert.assertEquals(uId,id);
    }
    @Title("This will delete student and verify that student is deleted")
    @Test
    public void test004(){
        userSteps.deleteUser(id).statusCode(204);
        userSteps.getUserInformationById(id).statusCode(404);
    }
}
