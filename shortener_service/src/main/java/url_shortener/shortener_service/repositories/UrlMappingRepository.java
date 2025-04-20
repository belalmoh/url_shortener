package url_shortener.shortener_service.repositories;

import url_shortener.shortener_service.entities.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    public UrlMapping findByUrlAlias(String alias);
}
