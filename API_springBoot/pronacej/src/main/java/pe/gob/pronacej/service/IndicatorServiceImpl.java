package pe.gob.pronacej.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.dto.IndicatorDTO;
import pe.gob.pronacej.entity.Indicator;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.IndicatorRepository;

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
        Indicator newIndicator = Indicator.builder()
                .nameIndicator(indicatorDTO.getNameIndicator())
                .build();
        crudIndicator.save(newIndicator);
        return indicatorDTO;
    }

    @Override
    public IndicatorDTO edit(Integer id, IndicatorDTO indicatorDTO) {
        Indicator existsIndicator = crudIndicator.findById(id)
                .orElseThrow(()-> new NotFoundException("Indicator no encontrado"));

        // Verifyng the fields
        if (indicatorDTO.getNameIndicator() != null){
            existsIndicator.setNameIndicator(indicatorDTO.getNameIndicator());
        }

        existsIndicator = crudIndicator.save(existsIndicator);

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
        Optional<Indicator> indicatorOptional = crudIndicator.findById(id);
        return indicatorOptional.map(indicator -> {
            IndicatorDTO indicatorDTO = new IndicatorDTO();
            indicatorDTO.setNameIndicator(indicator.getNameIndicator());

            return indicatorDTO;
        });
    }

    @Override
    public boolean deleteById(Integer id) {
        Indicator indicator = crudIndicator.findById(id)
                .orElseThrow(()-> new NotFoundException("ID del Indicador no encontrado"));
        crudIndicator.delete(indicator);
        return true;
    }


    // Creating methods using ModelMapper
    private IndicatorDTO convertEntityToDto(Indicator indicator){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        IndicatorDTO indicatorDTO = new IndicatorDTO();
        indicatorDTO = modelMapper.map(indicator, IndicatorDTO.class);
        return indicatorDTO;
    }

    private IndicatorDTO convertDtoToEntity(IndicatorDTO indicatorDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Indicator indicator = new Indicator();
        indicator = modelMapper.map(indicatorDTO, Indicator.class);
        return indicatorDTO;
    }
}
