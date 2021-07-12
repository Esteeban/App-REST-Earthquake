package cl.utem.project.cpyd.db.repository;
 
import cl.utem.project.cpyd.db.model.Sismo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author esteeban
 */

@Repository
public interface SismoRepository extends JpaRepository<Sismo, Long>{
    Sismo findById(long id);
}

