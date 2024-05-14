package pe.gob.pronacej.repository.impl;

import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.user.Admin;
import pe.gob.pronacej.repository.base.BaseRepository;

@Repository
public interface AdminRepository extends BaseRepository<Admin, Integer> {

}
