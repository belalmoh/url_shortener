package url_shortener.redirection_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import url_shortener.redirection_service.entities.UrlMapping;

public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    public UrlMapping findByUrlAlias(String alias);
}
