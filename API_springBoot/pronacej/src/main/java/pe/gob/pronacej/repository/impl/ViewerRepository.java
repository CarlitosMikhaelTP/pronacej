package pe.gob.pronacej.repository.impl;

import org.springframework.stereotype.Repository;
import pe.gob.pronacej.entity.user.Viewer;
import pe.gob.pronacej.repository.base.BaseRepository;

@Repository
public interface ViewerRepository extends BaseRepository<Viewer, Integer> {
}
