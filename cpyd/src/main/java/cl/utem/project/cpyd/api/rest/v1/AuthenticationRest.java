package cl.utem.project.cpyd.api.rest.v1;

import cl.utem.project.cpyd.api.vo.ErrorVo;
import cl.utem.project.cpyd.api.vo.LoginVo;
import cl.utem.project.cpyd.db.model.Credential;
import cl.utem.project.cpyd.exception.Exceptions;
import cl.utem.project.cpyd.manager.CredentialManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/authentication"}, consumes = {"application/json; charset=utf-8"}, produces = {"application/json; charset=utf-8"})
public class AuthenticationRest implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient CredentialManager credentialManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationRest.class);
    
    @ApiOperation(value = "Permite obtener un JWT válido para consumir la operación")
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Peticion invalida", response = ErrorVo.class),
        @ApiResponse(code = 401, message = "Usuario o contraseña incorrecta", response = ErrorVo.class),
        @ApiResponse(code = 403, message = "Usuario sin permisos", response = ErrorVo.class),
        @ApiResponse(code = 412, message = "Error de validacion", response = ErrorVo.class)
    })
    @PostMapping(value = "/login", consumes = {"application/json; charset=utf-8"}, produces = {"application/json; charset=utf-8"})
    public ResponseEntity login(@RequestBody LoginVo request){
        /**
         * Validar que exista una petición
         */
        if(request == null){
            LOGGER.error("no hay cuerpo en el mensaje");
            throw new Exceptions(400, "Peticion invalida");
            
        }
        
        /**
         * Verificar que vengan los datos minimos
         */
        final String app = StringUtils.trimToEmpty(request.getApp());
        if(StringUtils.isBlank(app)){
            LOGGER.error("Falta parámetro");
            throw new Exceptions(412, "El atributo app es requerido");
        }
        
        final String password = request.getPassword();
        if(StringUtils.isBlank(password)){
            LOGGER.error("Falta contraseña");
            throw new Exceptions("El atributo password es requerido");
        }
        
        boolean ok = credentialManager.authenticate(app, password);
        if(!ok){
            LOGGER.error("Credencial invalida");
            throw new Exceptions(401,"Credencial inválida, Usuario o contraseña incorrectos ");
        }
        /*Credential credential = credentialManager.authenticate(app, password);
        if(credential == false || !credential.getActive()){
            LOGGER.error("Usuario / Contraseña incorrectos");
            throw new Exceptions(401,"Usuario o contraseña incorrectos");
        }
        if(!credential.getActive()){
            LOGGER.error("Credencial NO Activa");
            throw new Exceptions(403,"Usuario sin permiso");
        }
        
        */
        return null;
    }
}
