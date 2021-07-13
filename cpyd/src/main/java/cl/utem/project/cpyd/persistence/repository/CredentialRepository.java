package cl.utem.project.cpyd.persistence.repository;

import cl.utem.project.cpyd.persistence.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long>{
    
    public Credential findByToken(String token);
    public Credential findByAppIgnoreCase(String app);
    public Credential findByAppIgnoreCaseAndPassword(String app, String password);
}
