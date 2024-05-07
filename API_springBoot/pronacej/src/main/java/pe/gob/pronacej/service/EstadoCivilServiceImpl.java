package pe.gob.pronacej.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.dto.EstadoCivilDTO;
import pe.gob.pronacej.entity.EstadoCivil;
import pe.gob.pronacej.entity.Matriz;
import pe.gob.pronacej.repository.RepositoryGeneric;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstadoCivilServiceImpl implements ServiceGeneric<EstadoCivilDTO>{

    private final RepositoryGeneric<EstadoCivil> metodoRepositoryEstadoCivil;
    @Override
    public EstadoCivilDTO registrar(EstadoCivilDTO estadoCivilDTO) {
        EstadoCivil nuevoEstadoCivil = EstadoCivil.builder()
                .tipoEstado(estadoCivilDTO.getTipoEstado())
                .descripcion(estadoCivilDTO.getDescripcion())
                .build();

        metodoRepositoryEstadoCivil.save(nuevoEstadoCivil);
        return estadoCivilDTO;
    }

    @Override
    public EstadoCivilDTO editar(Integer id, EstadoCivilDTO estadoCivilDTO) {
        return null;
    }

    @Override
    public List<EstadoCivilDTO> mostrarTodos() {
        return null;
    }

    @Override
    public Optional<EstadoCivilDTO> mostrarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public boolean eliminarPorId(Integer id) {
        return false;
    }
}
