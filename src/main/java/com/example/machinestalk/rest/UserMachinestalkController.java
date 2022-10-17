package com.example.machinestalk.rest;

import com.example.machinestalk.dao.IUserMachinestalkDao;
import com.example.machinestalk.model.Data;
import com.example.machinestalk.model.UserMachinestalk;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jakarta.json.JsonObject;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class UserMachinestalkController {

    @Autowired
    IUserMachinestalkDao userDao;

    public void setUserDao(IUserMachinestalkDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users")
    public List<UserMachinestalk> getUsers() {
        return userDao.findAll();
    }

    @PostMapping("/saveUsers")
    public void saveUsers(@RequestBody List<UserMachinestalk> usersList) {
        userDao.saveAll(usersList);
    }

    @PostMapping("/addUser")
    public void add(@RequestBody UserMachinestalk user) {
        userDao.save(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") int id) {
        userDao.deleteById(id);
    }

    WebClient webClient2 = WebClient.builder()
            .baseUrl("https://gorest.co.in/public-api/users")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();


    public Object findAll(){
        return webClient2.get()
                .uri("/allUsers")
                .retrieve()
                .bodyToFlux(Object.class);
    }








    @GetMapping(value="/all-users")
    private  List<UserMachinestalk> getAllUsers() {
        String url = "https://gorest.co.in/public-api/users";
        List<UserMachinestalk> users =new ArrayList<>();
        //RestTemplate restTemplate = new RestTemplate();
        //Object users = restTemplate.getForObject(url,Object.class );

        //String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Data> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Data>() {
                }
        );
        response.getBody().getData().forEach(userMachinestalk -> {
            userDao.save(userMachinestalk);
        });
      //  response.getBody().
        return response.getBody().getData();

    }

}
