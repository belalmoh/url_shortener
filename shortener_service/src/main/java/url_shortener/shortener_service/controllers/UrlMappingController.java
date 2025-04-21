package url_shortener.shortener_service.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.core.env.Environment;
import url_shortener.shortener_service.dto.ShortenUrlResponse;
import url_shortener.shortener_service.dto.UrlMappingRequest;
import url_shortener.shortener_service.entities.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import url_shortener.shortener_service.services.UrlMappingService;

import java.security.NoSuchAlgorithmException;

import static url_shortener.shortener_service.utils.Utils.getBaseUrl;

@RestController
@RequestMapping("/api")
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;

    @Autowired
    private Environment environment;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shorten(@RequestBody @Valid UrlMappingRequest urlMappingRequest, HttpServletRequest request) throws NoSuchAlgorithmException {
        UrlMapping urlMapping = urlMappingService.save(urlMappingRequest.getUrl());

        String response = getBaseUrl(request) + "/url/" + urlMapping.getUrlAlias();

        return ResponseEntity.ok().body(new ShortenUrlResponse(response));
    }

    @GetMapping("/urls")
    public ResponseEntity<?> getUrls() {
        return ResponseEntity.ok(urlMappingService.findAll());
    }
}
