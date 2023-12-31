package com.example.dbdemo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Health")
@RequiredArgsConstructor
@RequestMapping("/health")
public class HealthController {

    @Operation(
            summary = "Provides a health indicator.",
            description = "Returns 200 if alive."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O.K.")
    })
    @GetMapping(path = "")
    public ResponseEntity<Void> health() {
        log.info("Requested GET /health");

        return ResponseEntity.status(HttpStatus.OK)
                .cacheControl(CacheControl.noCache())
                .build();
    }

}
