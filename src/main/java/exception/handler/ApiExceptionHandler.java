package exception.handler;

import exception.EmailJaUtilizadoException;
import exception.ParametroProibidoCalculoDigitoUnicoException;
import exception.response.GenericApiDesafioExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ParametroProibidoCalculoDigitoUnicoException.class, EmailJaUtilizadoException.class})
    public ResponseEntity<GenericApiDesafioExceptionResponse> handleExcecaoParametroProibidoCalculoDigitoUnico(GenericApiDesafioExceptionResponse e){
        GenericApiDesafioExceptionResponse retorno = GenericApiDesafioExceptionResponse.builder()
                .message(e.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return ResponseEntity.badRequest().body(retorno);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleExcecaoValidacoesDto(
            MethodArgumentNotValidException e) {
        Map<String, String> erros = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(erro -> {
            String fieldName = ((FieldError) erro).getField();
            String errorMessage = erro.getDefaultMessage();
            erros.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(erros);
    }
}
