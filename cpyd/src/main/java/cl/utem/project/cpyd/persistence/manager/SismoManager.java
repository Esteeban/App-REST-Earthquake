package cl.utem.project.cpyd.persistence.manager;

import cl.utem.project.cpyd.persistence.model.Sismo;
import cl.utem.project.cpyd.persistence.repository.SismoRepository;
import java.io.Serializable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SismoManager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private transient SismoRepository sismoRepository;
    
    /**
     * 
     * @param id del sismo
     * @return objeto sismo
     */
    public Sismo getSismo(final long id) {
        Sismo sismo = null;
        if(id != 0){
            sismo = sismoRepository.findById(id);
        }
        return sismo;
    }
    
    /**
     * 
     * @return listado de sismos
     */
    public List<Sismo> getSismos(){
        return sismoRepository.findAll();
    }
}