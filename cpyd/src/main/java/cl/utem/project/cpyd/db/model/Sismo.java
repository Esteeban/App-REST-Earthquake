
package cl.utem.project.cpyd.db.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GenerationType;

@Entity
@Table(name="sismo")
public class Sismo implements Serializable {
    
    private static final long seialVersionUID=1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "pk",nullable = false)
    private Long id = null;
    
    @Column(name= "fechaHora",nullable = false)
    private String fechaHora = null;
    
    @Column(name= "latitud",nullable = false, unique = true)
    private double latitud = 0.0;
    
    @Column(name= "longitud",nullable = false, unique = true)
    private double longitud = 0.0;
    
    @Column(name= "profundidad",nullable = false, unique = true)
    private int profundidad = 0;
    
    @Column(name= "magnitud",nullable = false, unique = true)
    private double magnitud = 0.0;
    
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

    public String getFechaHora() {
        return fechaHora;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public double getMagnitud() {
        return magnitud;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public void setMagnitud(double magnitud) {
        this.magnitud = magnitud;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    
}
