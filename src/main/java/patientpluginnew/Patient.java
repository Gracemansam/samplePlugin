package patientpluginnew;

import coreapplication.plugin.api.PluginEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@PluginEntity(pluginId = "patient-management")
@Table(name = "plugin_patients")

@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String address;

    @Column(length = 2000)
    private String medicalHistory;

    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate = LocalDate.now();

    @Version
    private Integer version;
}


//package patientpluginnew.patientpluginnew;
//
//import coreapplication.plugin.api.PluginEntity;
//import jakarta.persistence.*;
//import lombok.Data;
//
//import java.time.LocalDate;
//
//@Data
//@PluginEntity(pluginId = "patient-management")
//@Entity
//@Table(name = "patients")
//public class Patient {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//
//    private String firstName;
//
//    private String lastName;
//
//    private String dateOfBirth;
//
//    private String gender;
//
//    private String contactNumber;
//
//    private String email;
//
//    private String address;
//
//    private String medicalHistory;
//
//}