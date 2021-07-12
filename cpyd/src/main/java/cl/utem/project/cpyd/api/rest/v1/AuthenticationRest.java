package cl.utem.project.cpyd.api.rest.v1;

import cl.utem.project.cpyd.api.utils.IPUtils;
import cl.utem.project.cpyd.api.utils.JwtUtils;
import cl.utem.project.cpyd.api.vo.AuthVo;
import cl.utem.project.cpyd.api.vo.ErrorVo;
import cl.utem.project.cpyd.api.vo.LoginVo;
import cl.utem.project.cpyd.db.model.Credential;
import cl.utem.project.cpyd.exception.Exceptions;
import cl.utem.project.cpyd.manager.CredentialManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/v1/authentications"}, consumes = {"application/json; charset=utf-8"}, produces = {"application/json; charset=utf-8"})
public class AuthenticationRest implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient CredentialManager credentialManager;
    
    @Autowired
    private transient HttpServletRequest httpRequest;
            
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
        
        final Credential credential = credentialManager.getCredentialByApp(app);
        if(credential == null){
            LOGGER.error("Credencial no existe");
            throw new Exceptions(401, "Credencial inválida");
        }
        if(!credential.getActive()){
            LOGGER.error("Credencial NO activa");
            throw new Exceptions(403, "NO tiene permisos");
        }
        
        boolean equals = StringUtils.equals(credential.getPassword(), password);
        if(!equals){
            LOGGER.error("contraseña incorrecta");
            throw new Exceptions(401, "contraseña inválida");
        }
        final String ip = IPUtils.getClientIpAddress(httpRequest);
        if(!InetAddressValidator.getInstance().isValid(ip)){
            LOGGER.error("IP NO válida");
            throw new Exceptions(403, "NO tiene permisos");
        }
        
        final String jwt = JwtUtils.createJwt("/v1/authentications/login", ip, credential);
        if(StringUtils.isBlank(jwt)){
            LOGGER.error("No se puede generar el jwt");
            throw new Exceptions("NO es posible completar la petición");
        }
        return ResponseEntity.ok(new AuthVo(jwt));
    }
}
