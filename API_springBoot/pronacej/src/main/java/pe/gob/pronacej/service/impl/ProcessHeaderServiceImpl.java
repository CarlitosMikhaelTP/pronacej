package pe.gob.pronacej.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.entity.dto.IndicatorDTO;
import pe.gob.pronacej.entity.dto.ProcessHeaderDTO;
import pe.gob.pronacej.entity.sabana.ProcessHeader;
import pe.gob.pronacej.entity.sabana.TypeProcessHeader;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.impl.ProcessHeaderRepository;
import pe.gob.pronacej.repository.impl.TypeProcessHeaderRepository;
import pe.gob.pronacej.service.ProcessHeaderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class ProcessHeaderServiceImpl implements ProcessHeaderService {

    private final ProcessHeaderRepository crudProcess;
    private final TypeProcessHeaderRepository crudTypeProcess;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProcessHeaderDTO register(ProcessHeaderDTO processHeaderDTO) {

        TypeProcessHeader typeProcessHeader = crudTypeProcess.findById(processHeaderDTO.getTypeProcessHeaderId())
                .orElseThrow(() -> new  NotFoundException("ID del tipo de proceso no encontrado"));

        ProcessHeader newProcess = ProcessHeader.builder()
                .typeProcessHeaderId(typeProcessHeader)
                .startTime(processHeaderDTO.getStart_time())
                .endTime(processHeaderDTO.getEnd_time())
                .amount(processHeaderDTO.getAmount())
                .message(processHeaderDTO.getMessage())
                .status(processHeaderDTO.getStatus())
                .state(processHeaderDTO.getState())
                .build();

        crudProcess.save(newProcess);
        return processHeaderDTO;
    }

    @Override
    public ProcessHeaderDTO edit(Integer id, ProcessHeaderDTO processHeaderDTO) {
        ProcessHeader processHeader = crudProcess.findById(id)
                .orElseThrow(() -> new NotFoundException("Id de proceso no encontrado"));

        TypeProcessHeader typeProcessHeader = crudTypeProcess.findById(processHeaderDTO.getTypeProcessHeaderId())
                .orElseThrow(() -> new NotFoundException("ID del tipo de proceso no encontrado"));

        if (processHeaderDTO.getTypeProcessHeaderId() != null) {
            processHeader.setTypeProcessHeaderId(typeProcessHeader);
        }
        if (processHeaderDTO.getStart_time() != null){
            processHeader.setStartTime(processHeaderDTO.getStart_time());
        }
        if (processHeaderDTO.getEnd_time() != null){
            processHeader.setStartTime(processHeaderDTO.getEnd_time());
        }
        if (processHeaderDTO.getAmount() != null){
            processHeader.setAmount(processHeaderDTO.getAmount());
        }
        if (processHeaderDTO.getMessage() != null){
            processHeader.setMessage(processHeaderDTO.getMessage());
        }
        if (processHeaderDTO.getStatus() != null){
            processHeader.setStatus(processHeaderDTO.getStatus());
        }
        if (processHeaderDTO.getState() != null){
            processHeader.setState(processHeaderDTO.getState());
        }

        processHeader =crudProcess.save(processHeader);

        return processHeaderDTO;
    }

    @Override
    public List<ProcessHeaderDTO> showAll() {
        return StreamSupport.stream(crudProcess
                        .findAll()
                        .spliterator(), false)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProcessHeaderDTO> showById(Integer id) {
        Optional<ProcessHeader> processHeaderOptional = crudProcess.findById(id);

        return processHeaderOptional.map(processHeader -> {
            ProcessHeaderDTO processHeaderDTO = new ProcessHeaderDTO();
            processHeaderDTO.setId(processHeaderDTO.getId());
            processHeaderDTO.setTypeProcessHeaderId(processHeaderDTO.getTypeProcessHeaderId());
            processHeaderDTO.setStart_time(processHeader.getStartTime());
            processHeaderDTO.setEnd_time(processHeader.getEndTime());
            processHeaderDTO.setAmount(processHeader.getAmount());
            processHeaderDTO.setMessage(processHeader.getMessage());
            processHeaderDTO.setStatus(processHeader.getStatus());
            processHeaderDTO.setState(processHeader.getState());

            return processHeaderDTO;
        });
    }

    @Override
    public boolean deleteById(Integer id) {
        ProcessHeader processHeader = crudProcess.findById(id)
                .orElseThrow(()-> new NotFoundException("ID del Proceso no encontrado"));
        crudProcess.delete(processHeader);
        return true;
    }

    @Override
    public ProcessHeaderDTO convertEntityToDto(ProcessHeader processHeader) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ProcessHeaderDTO processHeaderDTO = new ProcessHeaderDTO();
        processHeaderDTO = modelMapper.map(processHeader, ProcessHeaderDTO.class);
        return processHeaderDTO;
    }

    @Override
    public ProcessHeaderDTO convertDtoToEntity(ProcessHeaderDTO processHeaderDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        ProcessHeader processHeader = new ProcessHeader();
        processHeader = modelMapper.map(processHeaderDTO, ProcessHeader.class);
        return processHeaderDTO;
    }
}
