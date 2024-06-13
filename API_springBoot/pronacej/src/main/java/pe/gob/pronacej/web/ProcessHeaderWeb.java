package pe.gob.pronacej.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.pronacej.entity.dto.ProcessHeaderDTO;
import pe.gob.pronacej.service.impl.ProcessHeaderServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pronacej/v1/process")
public class ProcessHeaderWeb {

    private final ProcessHeaderServiceImpl processHeaderService;

    //Endpoint for register
    @PostMapping("/register")
    private ResponseEntity<ProcessHeaderDTO> registerProcess(
            @RequestBody ProcessHeaderDTO processHeaderDTO){
        ProcessHeaderDTO registeredProcess = processHeaderService.register(processHeaderDTO);
        return ResponseEntity.ok().body(registeredProcess);
    }

    //Endpoint for editing
    @PutMapping("/edit/{id}")
    public ResponseEntity<ProcessHeaderDTO> editProcess(
            @PathVariable("id") Integer id,
            @RequestBody ProcessHeaderDTO processHeaderDTO
    ) {
        ProcessHeaderDTO updatedProcess = processHeaderService.edit(id, processHeaderDTO);
        return ResponseEntity.ok(updatedProcess);
    }

    // Endpoint for list all indicators
    @GetMapping("/findAllProcess")
    public ResponseEntity<List<ProcessHeaderDTO>> showAllProcess(){
        List<ProcessHeaderDTO> processHeaderDTOList = processHeaderService.showAll();
        return ResponseEntity.ok(processHeaderDTOList);
    }

    // Endpoint for list one indicator byId
    @GetMapping("/findProcessById/{id}")
    public Optional<ProcessHeaderDTO> showProcessById(@PathVariable Integer id){
        return processHeaderService.showById(id);
    }

    // Enpoint for deleting a indicator for his Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProcessById(@PathVariable("id") Integer id) {
        boolean deleted = processHeaderService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Process was deleted");
        }else {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("Was not possible found the process for delete it.");
        }
    }
}
