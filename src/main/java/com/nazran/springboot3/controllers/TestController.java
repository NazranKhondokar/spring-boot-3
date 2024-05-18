package com.nazran.springboot3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/check")
@Tag(name = "Test APIs")
public class TestController {

    @GetMapping("/status")
    @Operation(summary = "Check API Status", description = "This API is for testing only")
    public ResponseEntity<Object> checkAPIStatus() {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", "API is up and running");
        map.put("status", "success");
        map.put("data", "");

        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }
}
