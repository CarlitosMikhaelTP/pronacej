package pe.gob.pronacej.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.dto.TableTablesDTO;
import pe.gob.pronacej.entity.Indicator;
import pe.gob.pronacej.entity.TableTables;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.RepositoryGeneric;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TableTablesServiceImpl implements ServiceGeneric<TableTablesDTO>{

    private final RepositoryGeneric<TableTables> crudTableTables;
    private final RepositoryGeneric<Indicator> crudIndicator;

    private ModelMapper modelMapper;

    @Override
    public TableTablesDTO register(TableTablesDTO tableTablesDTO) {
        Indicator indicator = crudIndicator.findById(tableTablesDTO.getIdIndicator())
                .orElseThrow(()-> new NotFoundException("ID del campo IdIndicador no encontrado."));
        TableTables newTableTables = TableTables.builder()
                .idIndicator(indicator)
                .nameTable(tableTablesDTO.getNameTable())
                .idField(tableTablesDTO.getIdField())
                .value(tableTablesDTO.getValue())
                .build();

        crudTableTables.save(newTableTables);
        return tableTablesDTO;
    }

    @Override
    public TableTablesDTO edit(Integer id, TableTablesDTO tableTablesDTO) {
        TableTables existsTableTables = crudTableTables.findById(id)
                .orElseThrow(()-> new NotFoundException("TableTables no encontrada"));

        Indicator indicator = existsTableTables.getIdIndicator();
        if (tableTablesDTO.getIdIndicator() != null){
            indicator = crudIndicator.findById(tableTablesDTO.getIdIndicator())
                    .orElseThrow(()-> new NotFoundException("ID del IdIndicator no encontrado"));
        }
        // Verifyng the fields
        if (tableTablesDTO.getNameTable() != null){
            existsTableTables.setNameTable(tableTablesDTO.getNameTable());
        }
        if (tableTablesDTO.getIdField() != null){
            existsTableTables.setIdField(tableTablesDTO.getIdField());
        }
        if (tableTablesDTO.getValue() != null){
            existsTableTables.setValue(tableTablesDTO.getValue());
        }

        existsTableTables = crudTableTables.save(existsTableTables);

        return tableTablesDTO;
    }

    @Override
    public List<TableTablesDTO> showAll() {
        return StreamSupport.stream(crudTableTables
                .findAll()
                .spliterator(), false)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TableTablesDTO> showById(Integer id) {
        Optional<TableTables> tableTablesOptional = crudTableTables.findById(id);
        return tableTablesOptional.map(tableTables -> {
            TableTablesDTO tableTablesDTO = new TableTablesDTO();
            tableTablesDTO.setIdIndicator(tableTables.getIdIndicator().getId());
            tableTablesDTO.setNameTable(tableTables.getNameTable());
            tableTablesDTO.setIdField(tableTables.getIdField());
            tableTablesDTO.setValue(tableTables.getValue());

            return tableTablesDTO;
        });
    }

    @Override
    public boolean deleteById(Integer id) {
        TableTables tableTables = crudTableTables.findById(id)
                .orElseThrow(()-> new NotFoundException("ID del TableTable no encontrado"));
        crudTableTables.delete(tableTables);
        return true;
    }


    // Creating methods using ModelMapper
    private TableTablesDTO convertEntityToDto(TableTables tableTables){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TableTablesDTO tableTablesDTO = new TableTablesDTO();
        tableTablesDTO = modelMapper.map(tableTables, TableTablesDTO.class);
        return tableTablesDTO;
    }

    private TableTables convertDtoToEntity(TableTablesDTO tableTablesDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TableTables tableTables = new TableTables();
        tableTables = modelMapper.map(tableTablesDTO, TableTables.class);
        return tableTables;
    }
}
