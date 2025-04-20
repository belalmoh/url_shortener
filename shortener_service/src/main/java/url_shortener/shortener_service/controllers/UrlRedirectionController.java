package url_shortener.shortener_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import url_shortener.shortener_service.services.UrlMappingService;

@RestController
@RequestMapping("/url")
public class UrlRedirectionController {

    @Autowired
    private UrlMappingService urlMappingService;

    @GetMapping("{alias}")
    public ResponseEntity<String> redirect(@PathVariable String alias) {
        String originalUrl = urlMappingService.findByUrlAlias(alias);
        if(originalUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();
    }
}
