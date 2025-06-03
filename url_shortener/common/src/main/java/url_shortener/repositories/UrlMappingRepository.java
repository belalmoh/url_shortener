package url_shortener.repositories;

import url_shortener.entities.UrlMapping;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlMappingRepository extends MongoRepository<UrlMapping, String> {
    public UrlMapping findByUrlAlias(String alias);
}
