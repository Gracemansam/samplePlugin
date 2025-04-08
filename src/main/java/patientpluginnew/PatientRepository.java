package patientpluginnew;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Find by name (case insensitive)
    List<Patient> findByLastNameIgnoreCase(String lastName);

    // Search by name (partial match)
    @Query("SELECT p FROM Patient p WHERE LOWER(p.firstName) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Patient> searchByName(@Param("query") String query);

    // Find patients by date of birth range
    List<Patient> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);

    // Count patients by gender
    long countByGender(String gender);
}