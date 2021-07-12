package cl.utem.project.cpyd.db.repository;

import cl.utem.project.cpyd.db.model.Credential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialRepository extends JpaRepository<Credential, Long>{
    
    public Credential findByToken(String token);
    public Credential findByAppIgnoreCaseAndPassword(String app, String password);
}
