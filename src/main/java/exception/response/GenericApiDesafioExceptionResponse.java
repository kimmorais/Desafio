package exception.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@Builder
public class GenericApiDesafioExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
}
