package url_shortener.shortener_service.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document
public class UrlMapping {

    @Id
    @Indexed(unique = true)
    private String id;
    private String url;
    private String urlAlias;
    private int clickCount = 0;
    private LocalDateTime expireAt = LocalDateTime.now().plusDays(7);

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
