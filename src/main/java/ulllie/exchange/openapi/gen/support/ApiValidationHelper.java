package ulllie.exchange.openapi.gen.support;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import ulllie.exchange.openapi.gen.exception.ExternalApiException;

@Component
@RequiredArgsConstructor
public class ApiValidationHelper {

    private final Validator validator;

    public <T> T requireValidBody(ResponseEntity<T> entity, String sourceName) {
        T body = entity.getBody();

        if (body == null) {
            throw new ExternalApiException(sourceName + ": пустой response body");
        }

        validate(body, sourceName);
        return body;
    }

    public <T> void validate(T target, String sourceName) {
        Set<ConstraintViolation<T>> violations = validator.validate(target);

        if (!violations.isEmpty()) {
            throw new ExternalApiException(format(sourceName, violations));
        }
    }

    private String format(String source, Set<? extends ConstraintViolation<?>> violations) {
        StringBuilder sb = new StringBuilder(violations.size() * 64);

        for (ConstraintViolation<?> v : violations) {
            sb.append(source)
              .append(" -> ")
              .append(v.getPropertyPath())
              .append(": ")
              .append(v.getMessage())
              .append(" (value=")
              .append(v.getInvalidValue())
              .append(")\n");
        }

        return sb.toString();
    }
}