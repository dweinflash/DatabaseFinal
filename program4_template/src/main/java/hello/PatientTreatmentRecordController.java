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

    @GetMapping("/addPatientTreatmentRecord")
    public String patientTreatmentRecordForm(Model model) {
        model.addAttribute("patientTreatmentRecord", new PatientTreatmentRecord());
        return "addPatientTreatmentRecord";
    }

    @PostMapping("/addPatientTreatmentRecord")
    public String patientTreatmentRecordSubmit(@ModelAttribute PatientTreatmentRecord patientTreatmentRecord) {
        //jdbcTemplate.update("insert into .patientTreatmentRecord values (?, ?)", patientTreatmentRecord.getFirstName());

        return "patientTreatmentRecordResult";
    }

    @GetMapping("/updatePatientTreatmentRecord")
    public String patientTreatmentRecordFormUpdate(Model model) {
        model.addAttribute("patientTreatmentRecord", new PatientTreatmentRecord());
        return "updatePatientTreatmentRecord";
    }

    @PostMapping("/updatePatientTreatmentRecord")
    public String patientTreatmentRecordUpdate(@ModelAttribute PatientTreatmentRecord patientTreatmentRecord) {
      //jdbcTemplate.update("update from .patientTreatmentRecord where first_name = ? and last_name = ?", patientTreatmentRecord.getFirstName(), patientTreatmentRecord.getLastName());

      return "patientTreatmentRecordResult";
    }

}
