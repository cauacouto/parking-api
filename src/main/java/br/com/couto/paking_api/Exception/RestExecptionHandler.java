package br.com.couto.paking_api.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class RestExecptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    private ResponseEntity<RestErroMenssage> tratarerro500(Exception exception) {
        RestErroMenssage threatResonse = new RestErroMenssage(HttpStatus.INTERNAL_SERVER_ERROR,exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(threatResonse);
    }


    @ExceptionHandler(RuntimeException.class)
    private ResponseEntity<RestErroMenssage> runtimeErroHandler(RuntimeException exception) {
        RestErroMenssage thereatResponse = new RestErroMenssage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(thereatResponse);
    }

    @ExceptionHandler(UsuarioExcepiton.class)
    private ResponseEntity<RestErroMenssage> tratarUsuario(UsuarioExcepiton ex) {
        RestErroMenssage threatResponse = new RestErroMenssage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(VeiculoExcepiton.class)
    private ResponseEntity<RestErroMenssage> tratarVeiculo(VeiculoExcepiton ex) {
        RestErroMenssage threatResponse = new RestErroMenssage(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<RestErroMenssage> tratarBadRequest(MethodArgumentNotValidException exception){
        RestErroMenssage threatResponse = new RestErroMenssage(HttpStatus.BAD_REQUEST,exception.getMessage());
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatResponse);
    }
}

