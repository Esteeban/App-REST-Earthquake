package cl.utem.project.cpyd.api.rest.vo;

import cl.utem.project.cpyd.persistence.model.Sismo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "sismo")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SismoVo{
    
    private Long id;
    private String fechaLocal;
    private String fechaUTC;
    private double latitud;
    private double longitud;
    private double profundidad;
    private String magnitud;
    private String agencia;
    private String referencia;

    public SismoVo() {
    }

    public SismoVo(Sismo sismo) {
        this.id = sismo.getId();
        this.fechaLocal = sismo.getFechaLocal();
        this.fechaUTC = sismo.getFechaUTC();
        this.latitud = sismo.getLatitud();
        this.longitud = sismo.getLongitud();
        this.profundidad = sismo.getProfundidad();
        this.magnitud = sismo.getMagnitud();
        this.agencia = sismo.getAgencia();
        this.referencia = sismo.getReferencia();
        
    }

    public SismoVo(String fechaLocal, String fechaUTC, float latitud, float longitud, float profundidad, String magnitud, String agencia, String referencia) {
                this.fechaLocal=fechaLocal;
                this.fechaUTC=fechaUTC;
                this.latitud=latitud;
                this.longitud=longitud;
                this.profundidad=profundidad;
                this.magnitud=magnitud;
                this.agencia=agencia;
                this.referencia=referencia;
           
    }

    @ApiModelProperty(value = "id del sismo",
            required = true,
            example = "1")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    @ApiModelProperty(value = "Fecha Local del Sismo",
            required = true, 
            example = "00:00:55 12/17/2021")
    public String getFechaLocal() {
        return fechaLocal;
    }

    public void setFechaLocal(String fechaLocal) {
        this.fechaLocal = fechaLocal;
    }
    
    @ApiModelProperty(value = "Fecha en formato UTC del Sismo",
            required = true, 
            example = "04:00:55 12/07/2021")
    public String getFechaUTC() {
        return fechaUTC;
    }

    public void setFechaUTC(String fechaUTC) {
        this.fechaUTC = fechaUTC;
    }

    @ApiModelProperty(value = "Latitud del Sismo",
            required = true, 
            example = "-23.924")
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    @ApiModelProperty(value = "Longitud del Sismo",
            required = true, 
            example = "-67.062")
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @ApiModelProperty(value = "Profundidad del sismo",
            required = true, 
            example = "181.6 Km")
    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
        this.profundidad = profundidad;
    }

    @ApiModelProperty(value = "Magnitud del sismo",
            required = true, 
            example = "3.2 MI")
    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    @ApiModelProperty(value = "Agencia Sismológica",
            required = true, 
            example = "GUC")
    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    @ApiModelProperty(value = "Referencia geográfica del sismo",
            required = true, 
            example = "92 Km al E de Santiago")
    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
}