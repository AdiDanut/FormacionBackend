package com.example.block6pathvariableheaders.Controller;

import com.example.block6pathvariableheaders.Entity.ResultEntity;
import com.example.block6pathvariableheaders.Entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class Controller {


    @PostMapping("/addUser")
    public UserEntity addUser(@RequestBody UserEntity user){
        return user;
    }

    @GetMapping("user/{id}")
    public String getId(@PathVariable int id){
        return "El id del usuario es: " + id;
    }

    @PutMapping("/put")
    public ResponseEntity<Map<String,String>> getMap (@RequestParam Map<String,String> params){
        return ResponseEntity.ok(params);
    }

    @GetMapping("/header")
    public ResponseEntity<String> getHeader(
            @RequestHeader(required = true) String param1,
            @RequestHeader(required = true) String param2){
        String responseMessage = "El parámetro h1 es: " + param1 + " y el parámetro H2 es: " + param2;

        return ResponseEntity.ok(responseMessage);
    }


    @PostMapping("/all")
    public ResponseEntity<ResultEntity> getAll(
            @RequestBody(required = false) String body,
            @RequestHeader Map<String, String> headers,
            @RequestParam Map<String, String> requestParams) {
        List<String> headerList = new ArrayList<>(headers.values());
        List<String> requestParamList = new ArrayList<>(requestParams.values());

        ResultEntity response = new ResultEntity(body, headerList, requestParamList);

        return ResponseEntity.ok(response);
    }
}