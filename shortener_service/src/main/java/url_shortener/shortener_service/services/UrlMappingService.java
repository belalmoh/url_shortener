package url_shortener.shortener_service.services;

import url_shortener.shortener_service.entities.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import url_shortener.shortener_service.repositories.UrlMappingRepository;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

import url_shortener.shortener_service.utils.Utils;

@Service
public class UrlMappingService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public UrlMapping save(String url) throws NoSuchAlgorithmException {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setUrl(url);
        urlMapping.setUrlAlias(Utils.generateRandomAlias(url));

        return urlMappingRepository.save(urlMapping);
    }

    public UrlMapping save(String url, String alias) {
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setUrl(url);
        urlMapping.setUrlAlias(alias);
        return urlMappingRepository.save(urlMapping);
    }

    public String findByUrlAlias(String alias) {
        UrlMapping urlMapping = urlMappingRepository.findByUrlAlias(alias);
        if(!this.isUrlMappingExpired(urlMapping)) {
            this.incrementClickCount(urlMapping);
            return urlMapping.getUrl();
        }
        return null;
    }

    public boolean isUrlMappingExpired(UrlMapping urlMapping) {
        LocalDateTime now = LocalDateTime.now();

        if(urlMapping.getExpireAt().isAfter(now)) {
            return false;
        }
        return true;
    }

    public void incrementClickCount(UrlMapping urlMapping) {
        urlMapping.setClickCount(urlMapping.getClickCount() + 1);
        urlMappingRepository.save(urlMapping);
    }

    public List<UrlMapping> findAll() {
        return urlMappingRepository.findAll();
    }

}
