package cl.utem.project.cpyd.persistence.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name="sismo")
public class Sismo implements Serializable {
       
    private static final long seialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "pk",nullable = false)
    private Long id = null;
    
    @Column(name= "fechalocal",nullable = false)
    private String fechaLocal = null;
    
    @Column(name= "fechautc",nullable = false)
    private String fechaUTC = null;
    
    @Column(name= "latitud",nullable = false, unique = true)
    private float latitud = 0;
    
    @Column(name= "longitud",nullable = false, unique = true)
    private float longitud = 0;
    
    @Column(name= "profundidad",nullable = false, unique = true)
    private float profundidad = 0;
    
    @Column(name= "magnitud",nullable = false, unique = true)
    private String magnitud = null;
    
    @Column(name= "agencia",nullable = false, unique = true)
    private String agencia = null;
    
    @Column(name= "referencia",nullable = false, unique = true)
    private String referencia = null;

    //Constructores
    public Sismo() {
        
    }
    public Sismo(String fechalocal, String utc, float latitud, float longitud, float profundidad, String magnitud, String agencia, String referencia) {
    
        this.fechaLocal=fechalocal;
        this.fechaUTC=utc;
        this.latitud=latitud;
        this.longitud=longitud;
        this.profundidad=profundidad;
        this.magnitud=magnitud;
        this.agencia=agencia;
        this.referencia=referencia;
    }
    
    //Getters y Setters
    public static long getSeialVersionUID() {
        return seialVersionUID;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaLocal() {
        return fechaLocal;
    }

    public void setFechaLocal(String fechaLocal) {
        this.fechaLocal = fechaLocal;
    }

    public String getFechaUTC() {
        return fechaUTC;
    }

    public void setFechaUTC(String fechaUTC) {
        this.fechaUTC = fechaUTC;
    }

    public float getLatitud() {
        return latitud;
    }

    public void setLatitud(float latitud) {
        this.latitud = latitud;
    }

    public float getLongitud() {
        return longitud;
    }

    public void setLongitud(float longitud) {
        this.longitud = longitud;
    }

    public float getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(float profundidad) {
        this.profundidad = profundidad;
    }

    public String getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(String magnitud) {
        this.magnitud = magnitud;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sismo sismo = (Sismo) obj;
        return Objects.equals(this.id, sismo.id);
    }
    
    
    
}
