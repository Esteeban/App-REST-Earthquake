package cl.utem.project.cpyd.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;


@ApiModel(value = "auth")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthVo implements Serializable{
    private static final long serialVersionUID = 123124124;
    
    private String bearer = null;

    public AuthVo() {
    }
    
    public AuthVo(String bearer) {
        this.bearer = bearer;
    }
    
    @ApiModelProperty(value = "El token usado para autenticar las operaciones",
            required = true,
            example = "asdasdasdadasdadad")
    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
    
       
}
