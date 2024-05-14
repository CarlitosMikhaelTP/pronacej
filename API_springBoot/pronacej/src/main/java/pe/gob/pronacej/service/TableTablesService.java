package pe.gob.pronacej.service;

import pe.gob.pronacej.entity.dto.TableTablesDTO;
import pe.gob.pronacej.entity.graphic.TableTables;

import java.util.List;
import java.util.Optional;

public interface TableTablesService {

    TableTablesDTO register(TableTablesDTO tableTablesDTO);

    TableTablesDTO edit(Integer id, TableTablesDTO tableTablesDTO);

    List<TableTablesDTO> showAll();

    Optional<TableTablesDTO> showById(Integer id);

    boolean deleteById(Integer id);

    // Agregando métodos Mapper
    TableTablesDTO convertEntityToDto(TableTables tableTables);

    TableTables convertDtoToEntity(TableTablesDTO tableTablesDTO);
}
