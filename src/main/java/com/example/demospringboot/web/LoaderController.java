package com.example.demospringboot.web;

import com.example.demospringboot.service.interfaces.LoaderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
public class LoaderController {

    private final LoaderService loaderService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/fill_db/customers/{qty}")
    public String fillDataBaseCustomers(@PathVariable Integer qty) {
        loaderService.generateCustomers(qty);
        return "Amount customers: " + loaderService.countCustomers();
    }
}
