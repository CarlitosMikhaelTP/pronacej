package pe.gob.pronacej.repository.impl;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.user.Person;
import pe.gob.pronacej.repository.base.BaseRepository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    @EntityGraph(attributePaths = "typeUserId") // Carga explícitamente el TipoUsuario
    Optional<Person> findByEmail(String email);

    @Query("SELECT u FROM Person u LEFT JOIN FETCH u.typeUserId WHERE u.id = :id")
    Person loadTypeUser(@Param("id") Integer id);

}
