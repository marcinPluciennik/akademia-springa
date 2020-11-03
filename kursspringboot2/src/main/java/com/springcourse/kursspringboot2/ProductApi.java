package com.springcourse.kursspringboot2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class ProductApi {
/*
    //@RequestMapping(value = "/products", method = RequestMethod.GET)
    @GetMapping
    public String getProducts(@RequestParam String name, @RequestParam(required = false, defaultValue = "") String surname){
        return "Hello " + name + " " + surname;
    }

    @GetMapping("/{name}")
    public String getProducts(@PathVariable String name){
        return "Hello " + name;
    }

    @GetMapping
    public String getProducts(@RequestHeader String name){
        return "Hello " + name;
    }

    @GetMapping
    public String getProducts(@RequestBody String name){
        return "Hello " + name;
    }


    @GetMapping
    public String getProducts(@RequestParam String name, @RequestHeader String surname){
        return "Hello " + name + " " + surname;
    }

 */
    @GetMapping
    public ResponseEntity<String> getProducts(){
        return new ResponseEntity("Hello world", HttpStatus.OK);
    }



    @PostMapping
    public String addProducts(){
        return "Hello World with POST";
    }

    @PutMapping
    public String modProducts(){
        return "Hello World with PUT";
    }

    @DeleteMapping
    public String removeProducts(){
        return "Hello World with DELETE";
    }
}

