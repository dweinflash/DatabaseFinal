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
public class PatientController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addPatient")
    public String patientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    @PostMapping("/addPatient")
    public String patientSubmit(@ModelAttribute Patient patient) {
        jdbcTemplate.update("insert into .patient values (?, ?)", patient.getfName());

        return "patientResult";
    }

    @GetMapping("/updatePatient")
    public String patientFormUpdate(Model model) {
        model.addAttribute("patient", new Patient());
        return "updatePatient";
    }

    @PostMapping("/updatePatient")
    public String patientUpdate(@ModelAttribute Patient patient) {
      jdbcTemplate.update("update from .patient where first_name = ? and last_name = ?", patient.getfName(), patient.getlName());

      return "patientResult";
    }

    @GetMapping("/deletePatient")
    public String patientFormDelete(Model model) {
        model.addAttribute("patient", new Patient());
        return "deletePatient";
    }

    @PostMapping("/deletePatient")
    public String patientDelete(@ModelAttribute Patient patient) {
      jdbcTemplate.update("delete from .patient where first_name = ? and last_name = ?", patient.getfName(), patient.getlName());

      return "patientResult";
    }

    @GetMapping("/query1")
    public String searchPatientRecord(Model model) {
        model.addAttribute("queryOne", new QueryResults());
        return "/query1";
    }

    @PostMapping("/query1")
    public String searchPatientRecordQuery(@ModelAttribute QueryResults queryOne, Model model){
        String sql = "select Patient.PID, Patient.lName as ptfname, Patient.fName as ptlname, Patient.gender, Patient.DOB, visitDate, visitReason, treatmentMethod, Doctor.fName as docfname, Doctor.lName as doclname from Patient, TreatmentRecord, Doctor where Patient.PID = TreatmentRecord.PID and Doctor.DID = TreatmentRecord.DID and Patient.lName = '" + queryOne.getlName() + "' and Patient.fName = '" +queryOne.getfName() + "' and Patient.DOB = " + queryOne.getDOB();
        List<String> patientsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String name = rs.getString("ptfName ") + rs.getString("ptlName");;
                        String dob = rs.getString("DOB");
                        String doctor = rs.getString("docfname ") + rs.getString("docfname");
                        return "Patient Name: " + name + "\nProvider: Dr. " + doctor + "\nPatient DOB: " + dob;
                    }
                });
	model.addAttribute("queryResults", patientsFound);
/*
        if(patientsFound.size() > 0) {
            query.setResult(patientsFound.get(0));
        }
        else{
            query.setResult("No patients found.");
        }
*/
        return "/query1result";
    }

}
