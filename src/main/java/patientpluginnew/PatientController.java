package patientpluginnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/plugins/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    //@PreAuthorize("hasRole('patient-management_READER')")
    public ResponseEntity<Page<Patient>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "lastName") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort.Direction sortDirection = direction.equalsIgnoreCase("desc") ?
                Sort.Direction.DESC : Sort.Direction.ASC;

        Page<Patient> patients = patientService.getAllPatients(
                PageRequest.of(page, size, Sort.by(sortDirection, sortBy)));

        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam String query) {
        List<Patient> patients = patientService.searchPatients(query);
        return ResponseEntity.ok(patients);
    }

    @PostMapping
   // @PreAuthorize("hasRole('patient-management_ADMIN')")
    public ResponseEntity<Patient> createPatient(@RequestBody @Validated Patient patient) {
        Patient newPatient = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id,
            @RequestBody Patient patientDetails) {

        return patientService.updatePatient(id, patientDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.deletePatient(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);

        return deleted ?
                ResponseEntity.ok(response) :
                ResponseEntity.notFound().build();
    }

    @GetMapping("/birthdate-range")
    public ResponseEntity<List<Patient>> getPatientsByBirthDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Patient> patients = patientService.findPatientsByDateOfBirthRange(startDate, endDate);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/stats/gender")
    public ResponseEntity<Map<String, Long>> getGenderStats() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("male", patientService.countPatientsByGender("Male"));
        stats.put("female", patientService.countPatientsByGender("Female"));
        stats.put("other", patientService.countPatientsByGender("Other"));

        return ResponseEntity.ok(stats);
    }
    @GetMapping("/test")
    public String getTest(){
        return  " Hello from test plugin";
    }
}