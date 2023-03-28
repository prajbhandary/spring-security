package com.pranab.security.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/demo-controller")
public class DemoController {

    @GetMapping
    public ResponseEntity<String> securedEndpoint(){
        return ResponseEntity.ok("This is a message from secured Endpoint");
    }
}
