package cl.utem.project.cpyd.api.rest;

import cl.utem.project.cpyd.api.vo.ErrorVo;
import cl.utem.project.cpyd.exception.Exceptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"cl.utem.project.cpyd.api.rest"})
public class ApiExceptionHandler {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiExceptionHandler.class);
    
    @ExceptionHandler({Exceptions.class})
    public ResponseEntity handlerException(Exceptions e){
        LOGGER.error("Se ha atrapado un error en la ejecucion: {}", e.getMessage());
        
        final HttpStatus error = HttpStatus.valueOf(e.getHttpCode());
        final ErrorVo response = new ErrorVo(e.getMessage());
        
        return new ResponseEntity<>(response, error);
    }
    
    @ExceptionHandler({Exception.class})
    public ResponseEntity handlerException(Exception e){
        LOGGER.error("Se ha atrapado un error en la ejecucion: {}", e.getMessage());
        LOGGER.debug("Se ha atrapado un error en la ejecucion: {}", e.getMessage(),e);
        
        final HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
        final ErrorVo response = new ErrorVo(e.getMessage());
        
        return new ResponseEntity<>(response, error);
    }
    
}
