package cl.utem.project.cpyd.persistence.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name="sismo")
public class Sismo implements Serializable {
    
    private static final long seialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "pk",nullable = false)
    private Long id = null;
    
    @Column(name= "fechaLocal",nullable = false)
    private String fechaLocal = null;
    
    @Column(name= "fechaUTCl",nullable = false)
    private String fechaUTC = null;
    
    @Column(name= "latitud",nullable = false, unique = true)
    private double latitud = 0.0;
    
    @Column(name= "longitud",nullable = false, unique = true)
    private double longitud = 0.0;
    
    @Column(name= "profundidad",nullable = false, unique = true)
    private double profundidad = 0;
    
    @Column(name= "magnitud",nullable = false, unique = true)
    private String magnitud = null;
    
    @Column(name= "agencia",nullable = false, unique = true)
    private String agencia = null;
    
    @Column(name= "refencia",nullable = false, unique = true)
    private String referencia = null;

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

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(double profundidad) {
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
