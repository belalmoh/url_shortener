<<<<<<<< HEAD:redirection_service/src/main/java/url_shortener/redirection_service/controllers/UrlRedirectionController.java
package url_shortener.redirection_service.controllers;
========
package url_shortener.controllers;
>>>>>>>> main:url_shortener/redirection_service/src/main/java/url_shortener/controllers/RedirectionController.java

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
<<<<<<<< HEAD:redirection_service/src/main/java/url_shortener/redirection_service/controllers/UrlRedirectionController.java
import org.springframework.web.client.RestTemplate;
import url_shortener.redirection_service.services.UrlMappingService;
========
import url_shortener.services.UrlMappingService;
>>>>>>>> main:url_shortener/redirection_service/src/main/java/url_shortener/controllers/RedirectionController.java


@Slf4j
@RestController
@RequestMapping("/url")
public class RedirectionController {
    @Autowired
    private UrlMappingService urlMappingService;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("{alias}")
    public ResponseEntity<String> redirect(@PathVariable String alias) {
        String originalUrl = urlMappingService.findByUrlAlias(alias);
        if(originalUrl == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("Original Url " + originalUrl);

        return ResponseEntity.status(HttpStatus.FOUND).header("Location", originalUrl).build();

//        try {
//            String redirectionUrl = restTemplate.getForObject(originalUrl, String.class);
//
//            log.info("Redirection Url " + redirectionUrl);
//
//            return ResponseEntity.status(HttpStatus.FOUND).header("Location", redirectionUrl).build();
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
    }
}
