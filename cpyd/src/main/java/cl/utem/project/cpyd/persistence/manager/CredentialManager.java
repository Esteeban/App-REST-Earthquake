package cl.utem.project.cpyd.persistence.manager;

import cl.utem.project.cpyd.persistence.model.Credential;
import cl.utem.project.cpyd.persistence.repository.CredentialRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CredentialManager implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private transient CredentialRepository credentialRepository;
    
    /**
     * 
     * @param id Identificador de la fila en la base de datos
     * @return un objeto Credencial o nulo 
     */
    public Credential getCredential(final Long id){
        Credential credential = null;
        if(id != null && id > 0L){
            Optional<Credential> finded = credentialRepository.findById(id);
            if(finded.isPresent()){
                credential = finded.get();
            }
        }
        return credential;
    }
    
    /**
     * 
     * @param app que identifica la credencial
     * @return un objeto Credencial o nulo
     */
    public Credential getCredentialByApp(final String app){
        Credential credential = null;
        if(StringUtils.isNotBlank(app)){
            credential = credentialRepository.findByAppIgnoreCase(app);
        }
        return credential;
    }
    
    
    /**
     * 
     * @param token que identifica la credencial
     * @return un objeto Credencial o nulo
     */
    public Credential getCredential(final String token){
        Credential credential = null;
        if(StringUtils.isNotBlank(token)){
            credential = credentialRepository.findByToken(token);
        }
        return credential;
    }
    
    /**
     * 
     * @return listado de todas las credenciales existentes
     */
    public List<Credential> getCredentials(){
        return credentialRepository.findAll(Sort.by(Sort.Direction.ASC,"created"));
        
    }
    
    /**
     * 
     * @param credential objeto credencial
     * @return un objeto persistido, null si es null o una excepcion si es error
     */
    @Transactional
    public Credential save(Credential credential){
        Credential saved = null;
        if(credential != null){
            saved = credentialRepository.save(credential);
        }
        return saved;
    }
    
    /**
     * 
     * @param credential objeto credencial
     * @return true si se elimina la credencial, false si el parámetro es nulo o excepcion si hay error
     */
    @Transactional
    public Boolean delete(Credential credential){
        boolean ok = false;
        if(credential != null){
            credentialRepository.delete(credential);
            ok = false;
        }
        return ok;
    }
    
    /**
     * 
     * @param app Identificador de la credencial
     * @param password  contraseña para autenticar
     * @return true si es correcto o false en otro caso
     */
    public boolean authenticate(final String app, String password){
        boolean ok = false;
        if(StringUtils.isNotBlank(app) && StringUtils.isNotBlank(password)){
            Credential credential = credentialRepository.findByAppIgnoreCaseAndPassword(app, password);
            if(credential != null && credential.getActive()){
                ok = StringUtils.equals(credential.getPassword(), password);
            }
        }
        return ok;
    }
    
    
}
