package pe.gob.pronacej.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.dto.CentroJuvenilDTO;
import pe.gob.pronacej.entity.CentroJuvenil;
import pe.gob.pronacej.repository.RepositoryGeneric;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CentroJuvenilServiceImpl implements ServiceGeneric<CentroJuvenilDTO>{

    private final RepositoryGeneric<CentroJuvenil> metodoRepositoryCentrojuvenil;

    @Override
    public CentroJuvenilDTO registrar(CentroJuvenilDTO centroJuvenilDTO) {

        CentroJuvenil nuevoCentroJuvenil = CentroJuvenil.builder()
                .lugar(centroJuvenilDTO.getLugar())
                .descripcion(centroJuvenilDTO.getDescripcion())
                .build();

        metodoRepositoryCentrojuvenil.save(nuevoCentroJuvenil);
        return centroJuvenilDTO;
    }

    @Override
    public CentroJuvenilDTO editar(Integer id, CentroJuvenilDTO centroJuvenilDTO) {
        return null;
    }

    @Override
    public List<CentroJuvenilDTO> mostrarTodos() {
        return null;
    }

    @Override
    public Optional<CentroJuvenilDTO> mostrarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean eliminarPorId(Integer id) {
        return false;
    }
}
