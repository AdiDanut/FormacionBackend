package com.example.block7crudvalidation.Feign;

import com.example.block7crudvalidation.Controller.DTO.ProfesorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profesor-service", url = "http://localhost:8080")
public interface ProfesorFeignClient {

    @GetMapping("/profesores/{id}")
    ProfesorDTO getProfesor(@PathVariable String id);
}
