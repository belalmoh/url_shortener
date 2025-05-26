package url_shortener.redirection_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UrlMappingRequest {
    @NotBlank(message = "Url is required")
    @NotNull(message = "Url is required")
    @Pattern(
            regexp = "^(http|https)://[^\\s$.?#].[^\\s]*$",
            message = "Invalid URL format"
    )
    private String url;
    private String alias;
}
