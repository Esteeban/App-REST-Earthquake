package cl.utem.project.cpyd.persistence.repository;
 
import cl.utem.project.cpyd.persistence.model.Sismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SismoRepository extends JpaRepository<Sismo, Long>{
    Sismo findById(long id);
}

