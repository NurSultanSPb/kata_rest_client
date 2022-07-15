package org.example;

import org.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final String url = "http://94.198.50.185:7081/api/users";

    @Autowired
    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> getUsers() {
        ResponseEntity<String> responseEntity =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        String.class);
//        responseEntity.getHeaders().get("Set-Cookie").stream().forEach(System.out::println);
        return responseEntity;
    }

//    public List<User> getUsers() {
//        ResponseEntity<List<User>> responseEntity =
//                restTemplate.exchange(url, HttpMethod.GET, null,
//                        new ParameterizedTypeReference<List<User>>() {});
//        List<User> allUsers = responseEntity.getBody();
//        return allUsers;
//    }

    public String saveUser(User user, HttpHeaders headers) {
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestBody, String.class);
        return responseEntity.getBody();
    }

    public String updateUser(User user, HttpHeaders headers) {
        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestBody, String.class);
        return responseEntity.getBody();
    }

//    public String saveUser(User user, HttpHeaders headers) {
//        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
//        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestBody, String.class);
//        return responseEntity.getBody();
//    }
//
//    public String updateUser(User user, HttpHeaders headers) {
//        HttpEntity<User> requestBody = new HttpEntity<>(user, headers);
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestBody, String.class);
//        return responseEntity.getBody();
//    }

//    public User getUser(Long id) {
//        User user = restTemplate.getForObject(url + "/" + id, User.class);
//        return user;
//    }

    public String deleteUser(Long id, HttpEntity requestBody) {
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, requestBody, String.class);
        return responseEntity.getBody();
    }
}
