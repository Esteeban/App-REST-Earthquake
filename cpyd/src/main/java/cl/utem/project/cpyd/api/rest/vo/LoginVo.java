package cl.utem.project.cpyd.api.rest.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "login")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class LoginVo {
    private String app = null;
    private String password = null;
    
    
    @ApiModelProperty(value = "Nombre de la aplicacion que consumirá el servicio",
            required = true,
            example = "utem")
       public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @ApiModelProperty(value = "Contraseña de la aplicacion que consumirá el servicio",
            required = true,
            example = "1234")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
   
}
