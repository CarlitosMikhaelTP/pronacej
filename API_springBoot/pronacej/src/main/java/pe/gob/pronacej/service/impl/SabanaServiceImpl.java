package pe.gob.pronacej.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.entity.dto.SabanaDTO;
import pe.gob.pronacej.entity.graphic.TableTables;
import pe.gob.pronacej.entity.sabana.ProcessHeader;
import pe.gob.pronacej.entity.sabana.Sabana;
import pe.gob.pronacej.entity.user.Admin;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.base.BaseRepository;
import pe.gob.pronacej.repository.impl.SabanaRepository;
import pe.gob.pronacej.service.SabanaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class SabanaServiceImpl implements SabanaService {

    private final SabanaRepository crudSabana;
    private final BaseRepository<ProcessHeader, Integer> crudProcessHeader;
    private final BaseRepository<TableTables, Integer> crudTableTables;
    private final BaseRepository<Admin, Integer> crudAdmin;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public SabanaDTO register(SabanaDTO sabanaDTO) {

        Admin admin = crudAdmin.findById(sabanaDTO.getAdminId())
                .orElseThrow(()-> new NotFoundException("Id del campo AdminId no encontrado"));

        TableTables tableTables = crudTableTables.findById(sabanaDTO.getTableTablesId())
                .orElseThrow(() -> new NotFoundException("Id del campo TableTablesId no encontrado."));

        ProcessHeader processHeader = crudProcessHeader.findById(sabanaDTO.getProcessHeaderId())
                .orElseThrow(() -> new NotFoundException("Id del campo ProcessHeaderId no encontrado"));

        Sabana newSabana = Sabana.builder()
                .adminId(admin)
                .tableTablesId(tableTables)
                .processHeaderId(processHeader)
                .value(sabanaDTO.getValue())
                .state(sabanaDTO.getState())
                .build();

        crudSabana.save(newSabana);
        return sabanaDTO;
    }

    @Override
    public SabanaDTO edit(Integer id, SabanaDTO sabanaDTO) {

        Sabana sabana = crudSabana.findById(id)
                .orElseThrow(()-> new NotFoundException("Id de la tabla Sabana no encontrado."));

        Admin admin = sabana.getAdminId();
        if (sabanaDTO.getAdminId() != null) {
            admin = crudAdmin.findById(sabanaDTO.getAdminId())
                    .orElseThrow(() -> new NotFoundException("Id del Admin no encontrado"));
        }
        TableTables tablesTables = sabana.getTableTablesId();
        if (sabanaDTO.getTableTablesId() != null) {
            tablesTables = crudTableTables.findById(sabanaDTO.getTableTablesId())
                    .orElseThrow(()-> new NotFoundException("Id de la tabla TablaTablas no encontrado"));
        }
        ProcessHeader processHeader = sabana.getProcessHeaderId();
        if (sabanaDTO.getProcessHeaderId() != null){
            processHeader = crudProcessHeader.findById(sabanaDTO.getProcessHeaderId())
                    .orElseThrow(()-> new NotFoundException("Id de la tabla ProcessHeader no encontrado"));
        }
        // Verificando campos en caso sean nulos
        if (sabanaDTO.getAdminId() != null){
            sabana.setAdminId(admin);
        }
        if (sabanaDTO.getTableTablesId() != null){
            sabana.setTableTablesId(tablesTables);
        }
        if (sabanaDTO.getProcessHeaderId() != null){
            sabana.setProcessHeaderId(processHeader);
        }
        if (sabanaDTO.getValue() != null){
            sabana.setValue(sabanaDTO.getValue());
        }
        if (sabanaDTO.getState() != null){
            sabana.setState(sabanaDTO.getState());
        }

        sabana = crudSabana.save(sabana);

        return sabanaDTO;
    }

    @Override
    public List<SabanaDTO> showAll() {
        return StreamSupport.stream(crudSabana
                .findAll()
                .spliterator(), false)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<SabanaDTO> showById(Integer id) {
        Optional<Sabana> sabanaOptional= crudSabana.findById(id);

        return sabanaOptional.map(sabana -> {
            SabanaDTO sabanaDTO = new SabanaDTO();
            sabanaDTO.setAdminId(sabana.getAdminId().getId());
            sabanaDTO.setTableTablesId(sabana.getTableTablesId().getId());
            sabanaDTO.setProcessHeaderId(sabana.getProcessHeaderId().getId());
            sabanaDTO.setValue(sabana.getValue());
            sabanaDTO.setState(sabana.getState());

            return sabanaDTO;
        });
    }

    @Override
    public boolean deleteById(Integer id) {
        Sabana sabana = crudSabana.findById(id)
                .orElseThrow(()-> new NotFoundException("ID de la tabla Sabana no encontrada"));
        crudSabana.delete(sabana);
        return true;
    }

    // Creating methods using ModelMapper
    @Override
    public SabanaDTO convertEntityToDto(Sabana sabana) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        SabanaDTO sabanaDTO = new SabanaDTO();
        sabanaDTO = modelMapper.map(sabana, SabanaDTO.class);
        return sabanaDTO;
    }

    @Override
    public Sabana convertDtoToEntity(SabanaDTO sabanaDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Sabana sabana = new Sabana();
        sabana = modelMapper.map(sabanaDTO, Sabana.class);
        return sabana;
    }
}
