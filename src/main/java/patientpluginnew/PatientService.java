package patientpluginnew;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {
    private static final Logger log = LoggerFactory.getLogger(PatientService.class);

    private final PatientRepository patientRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Transactional(readOnly = true)
    public Page<Patient> getAllPatients(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Patient> searchPatients(String query) {
        return patientRepository.searchByName(query);
    }

    public Patient createPatient(Patient patient) {
        log.info("Creating new patient: {} {}", patient.getFirstName(), patient.getLastName());
        return patientRepository.save(patient);
    }

    public Optional<Patient> updatePatient(Long id, Patient patientDetails) {
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    log.info("Updating patient with ID: {}", id);
                    // Update only non-null fields
                    if (patientDetails.getFirstName() != null) {
                        existingPatient.setFirstName(patientDetails.getFirstName());
                    }
                    if (patientDetails.getLastName() != null) {
                        existingPatient.setLastName(patientDetails.getLastName());
                    }
                    if (patientDetails.getDateOfBirth() != null) {
                        existingPatient.setDateOfBirth(patientDetails.getDateOfBirth());
                    }
                    if (patientDetails.getGender() != null) {
                        existingPatient.setGender(patientDetails.getGender());
                    }
                    if (patientDetails.getEmail() != null) {
                        existingPatient.setEmail(patientDetails.getEmail());
                    }
                    if (patientDetails.getPhoneNumber() != null) {
                        existingPatient.setPhoneNumber(patientDetails.getPhoneNumber());
                    }
                    if (patientDetails.getAddress() != null) {
                        existingPatient.setAddress(patientDetails.getAddress());
                    }
                    if (patientDetails.getMedicalHistory() != null) {
                        existingPatient.setMedicalHistory(patientDetails.getMedicalHistory());
                    }

                    return patientRepository.save(existingPatient);
                });
    }

    public boolean deletePatient(Long id) {
        return patientRepository.findById(id)
                .map(patient -> {
                    log.info("Deleting patient with ID: {}", id);
                    patientRepository.delete(patient);
                    return true;
                })
                .orElse(false);
    }

    @Transactional(readOnly = true)
    public List<Patient> findPatientsByDateOfBirthRange(LocalDate startDate, LocalDate endDate) {
        return patientRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    @Transactional(readOnly = true)
    public long countPatientsByGender(String gender) {
        return patientRepository.countByGender(gender);
    }

    // SAMPLE ON HOW T USE THE AUDIT IN THE SERVICE LAYER

//
//    @Auditable(action = "SETTINGS_CHANGE", resourceType = "CONFIGURATION")
//    public ConfigDTO updateConfiguration(String configId, ConfigDTO config) {
//        // Implementation...
//        return updatedConfig;
//    }
//
//    // Manual auditing for complex scenarios
//    public void performBulkOperation(List<String> ids, String operation) {
//        try {
//            // Perform operation
//            // ...
//
//            // Log custom audit event manually
//            auditService.logEvent(
//                    "my-plugin-id",
//                    getCurrentUserId(),
//                    "BULK_" + operation.toUpperCase(),
//                    "BATCH_PROCESS",
//                    String.join(",", ids),
//                    "Processed " + ids.size() + " items",
//                    "SUCCESS"
//            );
//        } catch (Exception e) {
//            // Log failure audit
//            auditService.logEvent(
//                    "my-plugin-id",
//                    getCurrentUserId(),
//                    "BULK_" + operation.toUpperCase(),
//                    "BATCH_PROCESS",
//                    String.join(",", ids),
//                    "Error: " + e.getMessage(),
//                    "FAILURE"
//            );
//            throw e;
//        }
//    }
//
//    private String getCurrentUserId() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        return auth != null ? auth.getName() : "system";
//    }
}