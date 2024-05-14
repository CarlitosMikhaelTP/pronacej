package pe.gob.pronacej.repository.impl;

import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.user.TypeUser;
import pe.gob.pronacej.repository.base.BaseRepository;

@Repository
public interface TypeUserRepository extends BaseRepository<TypeUser, Integer> {
}
