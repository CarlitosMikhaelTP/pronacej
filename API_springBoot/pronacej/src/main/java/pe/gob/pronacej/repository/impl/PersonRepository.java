package pe.gob.pronacej.repository.impl;

import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.user.Person;
import pe.gob.pronacej.repository.base.BaseRepository;

@Repository
public interface PersonRepository extends BaseRepository<Person, Integer> {
}
