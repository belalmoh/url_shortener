package url_shortener.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

import url_shortener.dto.ShortenUrlResponse;
import url_shortener.dto.UrlMappingRequest;
import url_shortener.entities.UrlMapping;
import url_shortener.services.UrlMappingService;
import static url_shortener.utils.Utils.getBaseUrl;


@RestController
@RequestMapping("/api")
public class UrlMappingController {

    @Autowired
    private UrlMappingService urlMappingService;

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
