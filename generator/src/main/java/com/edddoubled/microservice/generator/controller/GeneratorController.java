package com.edddoubled.microservice.generator.controller;

import com.edddoubled.microservice.generator.service.SudokuGeneratorService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/generate")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class GeneratorController {
    SudokuGeneratorService generatorService;


    @GetMapping
    public ResponseEntity<String> generate(@RequestParam int complexity) {
        log.info("request {}", complexity);
        if (complexity < 0 || complexity > 65) {
            return new ResponseEntity<>(generatorService.generate(true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(generatorService.generate(complexity, true), HttpStatus.OK);
        }
    }


    @GetMapping("/tech")
    public ResponseEntity<String> generateTech(@RequestParam int complexity) {
        log.info("request {}", complexity);
        if (complexity < 0 || complexity > 65) {
            return new ResponseEntity<>(generatorService.generate(false), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(generatorService.generate(complexity, false), HttpStatus.OK);
        }
    }
}
