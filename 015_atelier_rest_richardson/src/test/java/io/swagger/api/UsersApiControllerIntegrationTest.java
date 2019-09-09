package io.swagger.api;

import io.swagger.model.Error;
import io.swagger.model.JWT;
import io.swagger.model.User;
import io.swagger.model.UserPartial;

import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersApiControllerIntegrationTest {

    @Autowired
    private UsersApi api;

    @Test
    public void authUserTest() throws Exception {
        ResponseEntity<JWT> responseEntity = api.authUser();
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void createUserTest() throws Exception {
        UserPartial body = new UserPartial();
        ResponseEntity<User> responseEntity = api.createUser(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void deleteUserTest() throws Exception {
        String userId = "userId_example";
        ResponseEntity<ResourceSupport> responseEntity = api.deleteUser(userId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void showUserTest() throws Exception {
        String userId = "userId_example";
        ResponseEntity<User> responseEntity = api.showUser(userId);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateUserTest() throws Exception {
        String userId = "userId_example";
        UserPartial body = new UserPartial();
        ResponseEntity<User> responseEntity = api.updateUser(userId, body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
