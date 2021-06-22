package au.com.developer.api.common.error;

import au.com.developer.api.common.BaseObject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
public class CustomErrors extends BaseObject {

    @Getter(AccessLevel.NONE)
    private List<CustomError> errors= new ArrayList<>();

    public CustomErrors(HttpStatus status, String detail, String source) {
        CustomError error = new CustomError(status, detail, source);
        add(error);
    }

    public CustomErrors(String id, String code, String detail, String source) {
        this();
        CustomError error = new CustomError(id, code, detail, source);
        add(error);
    }

    public void add(CustomError error) {
        this.errors.add(error);
    }

    public void add(HttpStatus status, String detail, String source) {
        CustomError error = new CustomError(status, detail, source);
        add(error);
    }

    public void add(String id, String code, String detail, String source) {
        CustomError error = new CustomError(id, code, detail, source);
        add(error);
    }

    public List<CustomError> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
