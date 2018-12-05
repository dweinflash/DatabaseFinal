package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class PatientTreatmentRecordController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/ptRecordInsertion")
    public String patientTreatmentRecordForm(Model model) {
        model.addAttribute("patientTreatmentRecord", new PatientTreatmentRecord());
        return "ptRecordInsertion";
    }

    @PostMapping("/ptRecordInsertion")
    public String patientTreatmentRecordSubmit(@ModelAttribute PatientTreatmentRecord patientTreatmentRecord) {
        jdbcTemplate.update("insert into PatientTreatmentRecord values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", patientTreatmentRecord.getPID(), patientTreatmentRecord.getDID(), patientTreatmentRecord.getApptNo(), patientTreatmentRecord.getVisitReason(), patientTreatmentRecord.getVisitDate(), patientTreatmentRecord.getDateHospitalized(), patientTreatmentRecord.getExpectedDischarge(), patientTreatmentRecord.getActualDischarge(), patientTreatmentRecord.getRoomNo(), patientTreatmentRecord.getTreatmentMethod());

        return "patientTreatmentRecordResult";
    }

    @GetMapping("/ptRecordUpdate")
    public String patientTreatmentRecordFormUpdate(Model model) {
        model.addAttribute("patientTreatmentRecord", new PatientTreatmentRecord());
        return "ptRecordUpdate";
    }

    @PostMapping("/ptRecordUpdate")
    public String patientTreatmentRecordUpdate(@ModelAttribute PatientTreatmentRecord patientTreatmentRecord) {
      jdbcTemplate.update("update  patientTreatmentRecord set dateHospitalized = ?, expectedDischarge = ?, actualDischarge = ?, roomNo = ?, treatmentMethod = ? where PID = ? and DID = ? and apptNo = ?", patientTreatmentRecord.getDateHospitalized(), patientTreatmentRecord.getExpectedDischarge(), patientTreatmentRecord.getActualDischarge(), patientTreatmentRecord.getRoomNo(), patientTreatmentRecord.getTreatmentMethod(), patientTreatmentRecord.getPID(), patientTreatmentRecord.getDID(), patientTreatmentRecord.getApptNo());

      return "patientTreatmentRecordResult";
    }

}
