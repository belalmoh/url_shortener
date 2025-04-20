package url_shortener.shortener_service.controllers;

import jakarta.validation.Valid;
import url_shortener.shortener_service.dto.UrlMappingRequest;
import url_shortener.shortener_service.entities.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import url_shortener.shortener_service.services.UrlMappingService;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api")
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shorten(@RequestBody @Valid UrlMappingRequest urlMappingRequest) throws NoSuchAlgorithmException {
        UrlMapping urlMapping = urlMappingService.save(urlMappingRequest.getUrl());

        return ResponseEntity.ok(urlMapping.getUrlAlias());
    }

    @GetMapping("/urls")
    public ResponseEntity<?> getUrls() {
        return ResponseEntity.ok(urlMappingService.findAll());
    }
}
