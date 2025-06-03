package url_shortener.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import url_shortener.shortener_service.dto.ShortenUrlResponse;
import url_shortener.shortener_service.dto.UrlMappingRequest;
import url_shortener.shortener_service.entities.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import url_shortener.dto.ShortenUrlResponse;
import url_shortener.dto.UrlMappingRequest;
import url_shortener.entities.UrlMapping;
import url_shortener.services.UrlMappingService;


import java.security.NoSuchAlgorithmException;

import static url_shortener.utils.Utils.getBaseUrl;

@RestController
@RequestMapping("/api")
public class ShortenerController {
    @Autowired
    private UrlMappingService urlMappingService;

    @Value("${api.gateway.public-base-url}")
    private String apiGatewayPublicBaseUrl;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shorten(@RequestBody @Valid UrlMappingRequest urlMappingRequest, HttpServletRequest request) throws NoSuchAlgorithmException {
        UrlMapping urlMapping = urlMappingService.save(urlMappingRequest.getUrl());

        String response = apiGatewayPublicBaseUrl + "/url/" + urlMapping.getUrlAlias();

        return ResponseEntity.ok().body(new ShortenUrlResponse(response));
    }

    @GetMapping("/urls")
    public ResponseEntity<?> getUrls() {
        return ResponseEntity.ok(urlMappingService.findAll());
    }
}
