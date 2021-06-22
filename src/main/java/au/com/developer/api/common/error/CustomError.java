package au.com.developer.api.common.error;

import au.com.developer.api.common.BaseObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class CustomError extends BaseObject {

    private String id;

    private String code;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    private String detail;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Source source;

    public CustomError(HttpStatus status, String detail, String source) {
        this(null, null, detail, source);
        this.id = (status != null) ? status.toString() : null;
        this.code = (status != null) ? status.getReasonPhrase() : null;
    }

    public CustomError(String id, String code, String detail, String source) {
        this.id = id;
        this.code = code;
        this.detail = detail;
        if (source != null) {
            this.source = new Source();
            if (source.startsWith("/")) {
                this.source.setPointer(source);
            } else {
                this.source.setParameter(source);
            }
        }
    }

    @Data
    private class Source {
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String pointer;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String parameter;
    }
}
