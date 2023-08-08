package com.example.demospringboot.web;

import com.example.demospringboot.service.db_fill_dervice.LoaderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class LoaderController {

    private final LoaderService loaderService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/db_fill/{qty}")
    public String fillDataBase(@PathVariable Integer qty) {
        log.info("fillDataBase() LoaderController - start: ");
        loaderService.generateData(qty);
        String message = "Amount clients: " + loaderService.count();
        log.info("fillDataBase() LoaderController - end: count = {}", message);
        return message;
    }
}
