package pe.gob.pronacej.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import pe.gob.pronacej.dto.CentroJuvenilDTO;
import pe.gob.pronacej.dto.MatrizDTO;
import pe.gob.pronacej.entity.CentroJuvenil;
import pe.gob.pronacej.entity.EstadoCivil;
import pe.gob.pronacej.entity.Matriz;
import pe.gob.pronacej.exceptions.NotFoundException;
import pe.gob.pronacej.repository.RepositoryGeneric;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MatrizServiceImpl implements ServiceGeneric<MatrizDTO>{

    private final RepositoryGeneric<Matriz> metodosRepositoryMatriz;
    private final RepositoryGeneric<EstadoCivil> metodosRepositoryEstadoCivil;
    private final RepositoryGeneric<CentroJuvenil> metodosRepositoryCentroJuvenil;
    private  ModelMapper modelMapper;

    @Override
    public MatrizDTO registrar(MatrizDTO matrizDTO) {
        CentroJuvenil centroJuvenil = metodosRepositoryCentroJuvenil.findById(matrizDTO.getCentroJuvenil())
                .orElseThrow(()-> new NotFoundException("ID del Centro Juvenil asociado no encontrado !!"));
        EstadoCivil estadoCivil = metodosRepositoryEstadoCivil.findById(matrizDTO.getEstadoCivil())
                .orElseThrow(()-> new NotFoundException("ID del Estado Civil asociado no encontrado"));

        Matriz nuevaMatriz = Matriz.builder()
                .centroJuvenil(centroJuvenil)
                .estadoCivil(estadoCivil)
                .descripcion(matrizDTO.getDescripcion())
                .build();

         metodosRepositoryMatriz.save(nuevaMatriz);
         return  matrizDTO;
    }

    @Override
    public MatrizDTO editar(Integer id, MatrizDTO matrizDTO) {
        Matriz matrizExistente = metodosRepositoryMatriz.findById(id)
                .orElseThrow(()-> new NotFoundException("Matriz no encontrada"));

        CentroJuvenil centroJuvenil = matrizExistente.getCentroJuvenil();
        if (matrizDTO.getCentroJuvenil() != null){
            centroJuvenil = metodosRepositoryCentroJuvenil.findById(matrizDTO.getCentroJuvenil())
                    .orElseThrow(()-> new NotFoundException("Id del Centro Juvenil no encontrado"));
        }
        EstadoCivil estadoCivil = matrizExistente.getEstadoCivil();
        if (matrizDTO.getEstadoCivil() != null){
            estadoCivil = metodosRepositoryEstadoCivil.findById(matrizDTO.getEstadoCivil())
                    .orElseThrow(()-> new NotFoundException("Id del Estado Civil no encontraoo"));
        }

        // Verificando cada campo antes de actualizar
        if (matrizDTO.getDescripcion() != null) {
            matrizExistente.setDescripcion(matrizDTO.getDescripcion());
        }

        matrizExistente = metodosRepositoryMatriz.save(matrizExistente);

        return matrizDTO;
    }

    @Override
    public List<MatrizDTO> mostrarTodos() {
        return metodosRepositoryMatriz.findAll()
                .stream()
                .map(this::convertEntidadDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MatrizDTO> mostrarPorId(Integer id) {
        Optional<Matriz> matrizOptional = metodosRepositoryMatriz.findById(id);
        return matrizOptional.map(matriz -> {
            MatrizDTO matrizDTO = new MatrizDTO();
            matrizDTO.setCentroJuvenil(matriz.getCentroJuvenil().getId());
            matrizDTO.setEstadoCivil(matriz.getEstadoCivil().getId());
            matrizDTO.setDescripcion(matriz.getDescripcion());

            return matrizDTO;
        });
    }

    @Override
    public boolean eliminarPorId(Integer id) {
        Matriz matriz = metodosRepositoryMatriz.findById(id)
                .orElseThrow(()-> new NotFoundException("Id dela matriz no encontrada"));
        metodosRepositoryMatriz.delete(matriz);
        return true;
    }



    // Métodos Mapper
    private MatrizDTO convertEntidadDto(Matriz matriz){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        MatrizDTO matrizDTO = new MatrizDTO();
        matrizDTO = modelMapper.map(matriz, MatrizDTO.class);
        return matrizDTO;
    }

    private Matriz convertirDtoEntidad(MatrizDTO matrizDTO){
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.LOOSE);
        Matriz matriz = new Matriz();
        matriz = modelMapper.map(matrizDTO, Matriz.class);
        return matriz;
    }
}
