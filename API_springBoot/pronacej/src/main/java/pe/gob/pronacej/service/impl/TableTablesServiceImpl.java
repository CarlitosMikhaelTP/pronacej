package pe.gob.pronacej.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.entity.dto.TableTablesDTO;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.entity.graphic.TableTables;
import pe.gob.pronacej.entity.user.Admin;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.base.BaseRepository;
import pe.gob.pronacej.repository.impl.IndicatorRepository;
import pe.gob.pronacej.repository.impl.TableTablesRepository;
import pe.gob.pronacej.service.TableTablesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TableTablesServiceImpl implements TableTablesService {

    private final TableTablesRepository crudTableTables;
    private final BaseRepository<Indicators, Integer> crudIndicator;
    private final BaseRepository<Admin, Integer> crudAdmin;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TableTablesDTO register(TableTablesDTO tableTablesDTO) {

        Admin admin = crudAdmin.findById(tableTablesDTO.getAdminId())
                .orElseThrow(()-> new NotFoundException("ID del campo AdminId no encontrado"));

        Indicators indicators = crudIndicator.findById(tableTablesDTO.getIndicatorId())
                .orElseThrow(()-> new NotFoundException("ID del campo IdIndicador no encontrado."));

        TableTables newTableTables = TableTables.builder()
                .adminId(admin)
                .indicatorId(indicators)
                .nameTable(tableTablesDTO.getNameTable())
                .idField(tableTablesDTO.getIdField())
                .value(tableTablesDTO.getValue())
                .state(tableTablesDTO.getState())
                .build();

        crudTableTables.save(newTableTables);
        return tableTablesDTO;
    }

    @Override
    public TableTablesDTO edit(Integer id, TableTablesDTO tableTablesDTO) {

        TableTables tableTables = crudTableTables.findById(id)
                .orElseThrow(()-> new NotFoundException("ID de la tabla TableTables no encontrada"));

        Admin admin = tableTables.getAdminId();
        if (tableTablesDTO.getAdminId() != null){
            admin = crudAdmin.findById(tableTablesDTO.getAdminId())
                    .orElseThrow(()-> new NotFoundException("ID del IdAdmin no encontrado."));
        }
        Indicators indicators = tableTables.getIndicatorId();
        if (tableTablesDTO.getIndicatorId() != null){
            indicators = crudIndicator.findById(tableTablesDTO.getIndicatorId())
                    .orElseThrow(()-> new NotFoundException("ID del IdIndicator no encontrado"));
        }

        // Verificando campos en caso sean nulos
        if (tableTablesDTO.getAdminId() != null) {
            tableTables.setAdminId(admin);
        }
        if (tableTablesDTO.getIndicatorId() != null){
            tableTables.setIndicatorId(indicators);
        }
        if (tableTablesDTO.getNameTable() != null){
            tableTables.setNameTable(tableTablesDTO.getNameTable());
        }
        if (tableTablesDTO.getIdField() != null){
            tableTables.setIdField(tableTablesDTO.getIdField());
        }
        if (tableTablesDTO.getValue() != null){
            tableTables.setValue(tableTablesDTO.getValue());
        }
        if (tableTablesDTO.getState() != null){
            tableTables.setState(tableTablesDTO.getState());
        }

        tableTables = crudTableTables.save(tableTables);

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
            tableTablesDTO.setAdminId(tableTables.getAdminId().getId());
            tableTablesDTO.setIndicatorId(tableTables.getIndicatorId().getId());
            tableTablesDTO.setNameTable(tableTables.getNameTable());
            tableTablesDTO.setIdField(tableTables.getIdField());
            tableTablesDTO.setValue(tableTables.getValue());
            tableTablesDTO.setState(tableTablesDTO.getState());

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
    @Override
    public TableTablesDTO convertEntityToDto(TableTables tableTables) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TableTablesDTO tableTablesDTO = new TableTablesDTO();
        tableTablesDTO = modelMapper.map(tableTables, TableTablesDTO.class);
        return tableTablesDTO;
    }

    @Override
    public TableTables convertDtoToEntity(TableTablesDTO tableTablesDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        TableTables tableTables = new TableTables();
        tableTables = modelMapper.map(tableTablesDTO, TableTables.class);
        return tableTables;
    }

}
