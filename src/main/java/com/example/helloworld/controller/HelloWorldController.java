package com.example.helloworld.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorldController {

    private final RestTemplate restTemplate;

    public HelloWorldController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping("/hello")
    public String sendGreetings() {
        boolean ping1 = pingUrl("https://bcp-ti.atlassian.net.mcas.ms/jira/");
        boolean ping2 = pingUrl("https://bcp-ti.atlassian.net/jira/");
        return "Hello url1: " + ping1 + "url2" + ping2;
    }

    public boolean pingUrl(String url) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return response.getStatusCode() == HttpStatus.OK || response.getStatusCode() == HttpStatus.UNAUTHORIZED;
        } catch (Exception e) {
            System.out.println("error: " + e.toString());
            return false;
        }
    }
}
