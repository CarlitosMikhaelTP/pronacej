package pe.gob.pronacej.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.TableTables;

@Repository
public interface TableTablesRepository extends JpaRepository<TableTables, Integer> {

    // LOGICA PARA IMPEDIR QUE SE CREEN OTROS NOMBRES
}
