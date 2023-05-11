package com.edddoubled.microservice.solver;


import com.edddoubled.microservice.solver.model.SolutionMetadata;
import com.edddoubled.microservice.solver.service.SudokuSolverService;
import com.google.gson.Gson;
import com.netflix.discovery.DiscoveryClient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/solve")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
@Slf4j
public class Controller {
    private static final Gson gson = new Gson();
    private static final String URL_HASH = "http://data/solutionMetadata/search/findByHash?hash=";
    private static final String URL = "http://data/solutionMetadata";
    SudokuSolverService solverService;
    RestTemplate restTemplate;

    @GetMapping
    public ResponseEntity<String> solve(@RequestParam String matrix) {
        String hash = String.valueOf(matrix.hashCode());
        SolutionMetadata metadata;
        try {
            metadata = restTemplate.getForObject(URL_HASH + hash, SolutionMetadata.class);
            if (metadata != null) {
                return new ResponseEntity<>(metadata.getSolution(), HttpStatus.OK);
            }
        } catch (HttpClientErrorException e) {
            log.error(e.getLocalizedMessage(), e);
        }

        metadata = new SolutionMetadata();
        metadata.setHash(hash);

        String result = solverService.solve(matrix);
        metadata.setSolution(result);
        try {
            restTemplate.postForLocation(URL, gson.toJson(metadata));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
