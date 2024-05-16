package pe.gob.pronacej.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.pronacej.entity.dto.SabanaDTO;
import pe.gob.pronacej.service.impl.SabanaServiceImpl;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pronacej/v1/sabana")
public class SabanaWeb {

    private final SabanaServiceImpl sabanaService;

    @PostMapping("/register")
    private ResponseEntity<SabanaDTO> registerSabana(
            @RequestBody SabanaDTO sabanaDTO){
        SabanaDTO registeredSabana = sabanaService.register(sabanaDTO);
        return ResponseEntity.ok().body(registeredSabana);
    }

    //Endpoint for editing
    @PutMapping("/edit/{id}")
    public ResponseEntity<SabanaDTO> editTableTables(
            @PathVariable("id") Integer id,
            @RequestBody SabanaDTO sabanaDTO
    ) {
        SabanaDTO updatedSabana = sabanaService.edit(id, sabanaDTO);
        return ResponseEntity.ok(updatedSabana);
    }

    // Endpoint for list all indicators
    @GetMapping("/findAllSabana")
    public ResponseEntity<List<SabanaDTO>> showAllSabana(){
        List<SabanaDTO> sabanaDTOList = sabanaService.showAll();
        return ResponseEntity.ok(sabanaDTOList);
    }

    // Endpoint for list one indicator byId
    @GetMapping("/findSabanaById/{id}")
    public Optional<SabanaDTO> showTableSabanaById(@PathVariable Integer id){
        return sabanaService.showById(id);
    }

    // Enpoint for deleting a indicator for his Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSabanaById(@PathVariable("id") Integer id) {
        boolean deleted = sabanaService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("The  table Sabana was deleted");
        }else {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("Was not possible found the table Sabana for delete it.");
        }
    }
}
