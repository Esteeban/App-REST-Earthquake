package cl.utem.project.cpyd.api.rest.vo;

import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;

public class ErrorVo {
    public String message = null;
    public LocalDateTime date = LocalDateTime.now();

    public ErrorVo() {
        this.message = "Error desconocido";
        this.date = LocalDateTime.now();
    }
    
    public ErrorVo(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }

    @ApiModelProperty(value = "Mensaje del error",
            required = true)
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty(value = "fecha del error",
            required = true)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
 
}
