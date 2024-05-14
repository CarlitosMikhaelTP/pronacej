package pe.gob.pronacej.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.pronacej.entity.dto.TableTablesDTO;
import pe.gob.pronacej.service.impl.TableTablesServiceImpl;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pronacej/v1/tables")
public class TableTablesWeb {

    private final TableTablesServiceImpl tableTablesService;

    //Endpoint for register
    @PostMapping("/register")
    private ResponseEntity<TableTablesDTO> registerTableTables(
            @RequestBody TableTablesDTO tableTablesDTO){
        TableTablesDTO registeredTableTables = tableTablesService.register(tableTablesDTO);
        return ResponseEntity.ok().body(registeredTableTables);
    }

    //Endpoint for editing
    @PutMapping("/edit/{id}")
    public ResponseEntity<TableTablesDTO> editTableTables(
            @PathVariable("id") Integer id,
            @RequestBody TableTablesDTO tableTablesDTO
    ) {
        TableTablesDTO updatedTableTables = tableTablesService.edit(id, tableTablesDTO);
        return ResponseEntity.ok(updatedTableTables);
    }

    // Endpoint for list all indicators
    @GetMapping("/findAllTables")
    public List<TableTablesDTO> showAllTableTables(){
        return tableTablesService.showAll();
    }

    // Endpoint for list one indicator byId
    @GetMapping("/findTableById/{id}")
    public Optional<TableTablesDTO> showTableTablesById(@PathVariable Integer id){
        return tableTablesService.showById(id);
    }

    // Enpoint for deleting a indicator for his Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTableById(@PathVariable("id") Integer id) {
        boolean deleted = tableTablesService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("The TableTables was deleted");
        }else {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("Was not possible found the TableTables for delete it.");
        }
    }
}
