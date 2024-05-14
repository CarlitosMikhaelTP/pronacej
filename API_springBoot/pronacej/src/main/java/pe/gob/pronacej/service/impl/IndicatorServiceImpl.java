package pe.gob.pronacej.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.entity.dto.IndicatorDTO;
import pe.gob.pronacej.entity.graphic.Indicators;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.impl.IndicatorRepository;
import pe.gob.pronacej.service.IndicatorService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class IndicatorServiceImpl implements IndicatorService {

    private final IndicatorRepository crudIndicator;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public IndicatorDTO register(IndicatorDTO indicatorDTO) {
        Indicators newIndicators = Indicators.builder()
                //.nameIndicator(indicatorDTO.getNameIndicator())
                .build();
        crudIndicator.save(newIndicators);
        return indicatorDTO;
    }

    @Override
    public IndicatorDTO edit(Integer id, IndicatorDTO indicatorDTO) {
        Indicators existsIndicators = crudIndicator.findById(id)
                .orElseThrow(()-> new NotFoundException("Indicators no encontrado"));

        // Verifyng the fields
        if (indicatorDTO.getName() != null){
            //existsIndicators.setNameIndicator(indicatorDTO.getNameIndicator());
        }

        existsIndicators = crudIndicator.save(existsIndicators);

        return indicatorDTO;
    }

    @Override
    public List<IndicatorDTO> showAll() {
        return StreamSupport.stream(crudIndicator
                .findAll()
                .spliterator(), false)
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<IndicatorDTO> showById(Integer id) {
        Optional<Indicators> indicatorOptional = crudIndicator.findById(id);
        return indicatorOptional.map(indicators -> {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
           // indicatorDTO.setNameIndicator(indicators.getNameIndicator());

            return indicatorDTO;
        });
    }

    @Override
    public boolean deleteById(Integer id) {
        Indicators indicators = crudIndicator.findById(id)
                .orElseThrow(()-> new NotFoundException("ID del Indicador no encontrado"));
        crudIndicator.delete(indicators);
        return true;
    }

    // Creating methods using ModelMapper
    @Override
    public IndicatorDTO convertEntityToDto(Indicators indicators) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        IndicatorDTO indicatorDTO = new IndicatorDTO();
        indicatorDTO = modelMapper.map(indicators, IndicatorDTO.class);
        return indicatorDTO;
    }

    @Override
    public IndicatorDTO convertDtoToEntity(IndicatorDTO indicatorDTO) {
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Indicators indicators = new Indicators();
        indicators = modelMapper.map(indicatorDTO, Indicators.class);
        return indicatorDTO;
    }




}
