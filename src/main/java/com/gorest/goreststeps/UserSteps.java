package com.gorest.goreststeps;

import com.gorest.constants.EndPoints;
import com.gorest.model.UserPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class UserSteps {
    @Step("Creating user with name : {0}, email : {1}, gender : {2}, status : {3}")
    public ValidatableResponse createUser(String name,String email,String gender,String status){
        UserPojo userPojo=UserPojo.getUserPojo(name, email, gender, status);
        return  SerenityRest.given()
                .contentType(ContentType.JSON)
                .header("Accept","application/json")
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .when()
                .body(userPojo)
                .post(EndPoints.CREATE_USER)
                .then();
    }
    @Step("Getting the User id with id : {0}")
    public int getUserId(int id){
        return SerenityRest.given()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParams("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then().statusCode(200)
                .extract()
                .path("id");
    }
    @Step("Getting the User information with id : {0}")
    public ValidatableResponse getUserInformationById(int id){
        return SerenityRest.given()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParams("id",id)
                .when()
                .get(EndPoints.GET_SINGLE_USER_BY_ID)
                .then();
    }
    @Step("Updating User information with id: {0}, name: {1}, email: {2}, gender: {3}, status: {4}")
    public ValidatableResponse updateUser(int id, String name, String email, String gender, String status){
        UserPojo userPojo=UserPojo.getUserPojo(name, email, gender, status);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParams("id", id)
                .body(userPojo)
                .when()
                .put(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("Updating Partial User information with id: {0}, name: {1}, email: {2}, gender: {3}, status: {4}")
    public ValidatableResponse updateUser(int id, String email){
        UserPojo userPojo=UserPojo.getUserPojo(email);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParams("id", id)
                .body(userPojo)
                .when()
                .patch(EndPoints.UPDATE_USER_BY_ID)
                .then();
    }
    @Step("Deleting User information with id: {0}")
    public ValidatableResponse deleteUser(int id){
        return SerenityRest.given().log().all()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .pathParam("id", id)
                .when()
                .delete(EndPoints.DELETE_USER_BY_ID)
                .then();
    }
    @Step("Getting all User information")
    public ValidatableResponse getAllUserInfo(){
        return SerenityRest.given()
                .header("Authorization","Bearer 13eb5d72be985bf927680ae4be97ec5c6f76dcffe0aaa2235c207d32925d495e")
                .when()
                .get(EndPoints.GET_ALL_USERS)
                .then();
    }
}
