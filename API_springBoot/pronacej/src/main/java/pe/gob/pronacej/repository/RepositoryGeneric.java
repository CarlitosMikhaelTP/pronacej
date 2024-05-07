package pe.gob.pronacej.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

// interfaz base que otras interfaces de repositorio pueden extende
@NoRepositoryBean
@Repository
public interface RepositoryGeneric<T> extends JpaRepository<T, Integer> {

}
