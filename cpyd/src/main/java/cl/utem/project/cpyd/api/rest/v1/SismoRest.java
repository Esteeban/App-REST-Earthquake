package cl.utem.project.cpyd.api.rest.v1;

import cl.utem.project.cpyd.api.utils.IPUtils;
import cl.utem.project.cpyd.api.utils.JwtUtils;
import cl.utem.project.cpyd.api.rest.vo.ErrorVo;
import cl.utem.project.cpyd.api.rest.vo.SismoVo;
import cl.utem.project.cpyd.persistence.model.Sismo;
import cl.utem.project.cpyd.exception.Exceptions;
import cl.utem.project.cpyd.persistence.manager.SismoManager;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/sismos")
public class SismoRest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient SismoManager sismoManager;

    @Autowired
    private transient HttpServletRequest httpRequest;

    private static final Logger LOGGER = LoggerFactory.getLogger(SismoRest.class);

    @ApiOperation(value = "Permite obtener el listado de sismos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta fue exitosa", response = SismoVo.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Acceso no autorizado", response = ErrorVo.class),
        @ApiResponse(code = 403, message = "No tiene permisos", response = ErrorVo.class),
        @ApiResponse(code = 404, message = "No se ha encontrado la información solicitada", response = ErrorVo.class),
        @ApiResponse(code = 412, message = "Falló alguna precondición", response = ErrorVo.class)
    })
    
    @GetMapping(value = "/all", consumes = {"*/*"}, produces = {"application/json;charset=utf-8"})
    public ResponseEntity getAll(@ApiParam(name = "Authentication", value = "Cabecera de autenticación", required = true, 
            example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
            @RequestHeader(name = "Authentication", required = true) String bearer) {
        if (StringUtils.isBlank(bearer)) {
            LOGGER.error("Sin token de autenticación");
            throw new Exceptions(401, "Credenciales inválidas");
        }

        final String ip = IPUtils.getClientIpAddress(httpRequest);
        if (!InetAddressValidator.getInstance().isValid(ip)) {
            LOGGER.error("IP NO válida, posible ataque");
            throw new Exceptions(403, "No tiene permiso para acceder a este recurso");
        }

        final String jwt = StringUtils.trimToEmpty(StringUtils.removeStartIgnoreCase(bearer, "bearer"));
        boolean valid = JwtUtils.isValid(ip, jwt);
        if (!valid) {
            LOGGER.error("El token de autenticación no es válido");
            throw new Exceptions(401, "Credenciales inválidas");
        }

        List<Sismo> sismos = sismoManager.getSismos();
        if (CollectionUtils.isEmpty(sismos)) {
            LOGGER.error("Lista de sismos vacía");
            throw new Exceptions(404, "No se han encontrado sismos");
        }

        List<SismoVo> resultList = new ArrayList<>();
        sismos.forEach(sismo->{
            resultList.add(new SismoVo(sismo));
            System.out.println(sismo.getFechaLocal());
        });
        sismos.clear();
        return ResponseEntity.ok(resultList);
    }

}