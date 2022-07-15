package org.example;

import org.example.configuration.MyConfig;
import org.example.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        ResponseEntity<String> userListEntity= communication.getUsers();
        List<String> cookies = userListEntity.getHeaders().get("Set-Cookie");
//        System.out.println(userListEntity);
//        System.out.println("--------------------------------");
//        System.out.println(cookies);
//        System.out.println("--------------------------------");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie",cookies.stream().collect(Collectors.joining(";")));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);



        User user = new User(3L, "James", "Brown", (byte)24);
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        String responsePost = communication.saveUser(user, headers);
        System.out.println(responsePost);

        user.setName("Thomas");
        user.setLastName("Shelby");
        String responsePut = communication.updateUser(user, headers);
        System.out.println(responsePut);
//
        String responseDelete = communication.deleteUser(3L, requestBody);
        System.out.println(responseDelete);
    }
}
