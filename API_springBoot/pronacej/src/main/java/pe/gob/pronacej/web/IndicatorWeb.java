package pe.gob.pronacej.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.pronacej.dto.IndicatorDTO;
import pe.gob.pronacej.service.IndicatorServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pronacej/v1/indicator")
public class IndicatorWeb {

    private final IndicatorServiceImpl indicatorService;

    //Endpoint for register
    @PostMapping("/register")
    private ResponseEntity<IndicatorDTO> registerIndicator(
            @RequestBody IndicatorDTO indicatorDTO){
        IndicatorDTO registeredIndicator = indicatorService.register(indicatorDTO);
        return ResponseEntity.ok().body(registeredIndicator);
    }

    //Endpoint for editing
    @PutMapping("/edit/{id}")
    public ResponseEntity<IndicatorDTO> editIndicator(
            @PathVariable("id") Integer id,
            @RequestBody IndicatorDTO indicatorDTO
    ) {
        IndicatorDTO updatedIndicator = indicatorService.edit(id, indicatorDTO);
        return ResponseEntity.ok(updatedIndicator);
    }

    // Endpoint for list all indicators
    @GetMapping("/findAllIndicator")
    public ResponseEntity<List<IndicatorDTO>> showAllIndicator(){
        List<IndicatorDTO> indicatorDTOList = indicatorService.showAll();
        return ResponseEntity.ok(indicatorDTOList);
    }

    // Endpoint for list one indicator byId
    @GetMapping("/findIndicatorById/{id}")
    public Optional<IndicatorDTO> showIndicatorById(@PathVariable Integer id){
        return indicatorService.showById(id);
    }

    // Enpoint for deleting a indicator for his Id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteIndicatorById(@PathVariable("id") Integer id) {
        boolean deleted = indicatorService.deleteById(id);

        if (deleted) {
            return ResponseEntity.ok("Indicator was deleted");
        }else {
            return ResponseEntity.
                    status(HttpStatus.NOT_FOUND).
                    body("Was not possible found the indicator for delete it.");
        }
    }
}
