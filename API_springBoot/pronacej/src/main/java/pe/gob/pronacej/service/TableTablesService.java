package pe.gob.pronacej.service;

import pe.gob.pronacej.dto.IndicatorDTO;
import pe.gob.pronacej.dto.TableTablesDTO;

import java.util.List;
import java.util.Optional;

public interface TableTablesService {

    TableTablesDTO register(TableTablesDTO tableTablesDTO);

    TableTablesDTO edit(Integer id, TableTablesDTO tableTablesDTO);

    List<TableTablesDTO> showAll();

    Optional<TableTablesDTO> showById(Integer id);

    boolean deleteById(Integer id);
}
