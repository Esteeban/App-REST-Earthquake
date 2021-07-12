package cl.utem.project.cpyd.api.vo;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
 
}
