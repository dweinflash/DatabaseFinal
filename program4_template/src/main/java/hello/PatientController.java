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

    @GetMapping("/query1result")
    public String searchPatientRecord(Model model) {
        model.addAttribute("query", new QueryResults());
        return "/query1result";
    }

    @PostMapping("/query1result")
    public String searchPatientRecordQuery(@ModelAttribute QueryResults query){
        String sql = "select Patient.PID, Patient.lName as ptfname, Patient.fName as ptlname, Patient.gender, Patient.DOB, visitDate, visitReason, treatmentMethod, Doctor.fName as docfname, Doctor.lName as doclname from Patient join TreatmentRecord on PID join Doctor on DID where Patient.lName = " + query.getlName() + " and Patient.fName = " +query.getfName() + " and Patient.DOB = " + query.getDOB();
        List<String> patientsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String name = rs.getString("ptfName") + rs.getString("ptlName");;
                        String dob = String.valueOf(rs.getInt("DOB"));
                        String doctor = rs.getString("docfname") + rs.getString("docfname");
                        return "Patient Name: " + name + "\nProvider: Dr. " + doctor + "\nPatient DOB: " + dob;
                    }
                });
        if(patientsFound.size() > 0) {
            query.setResult(patientsFound.get(0));
        }
        else{
            query.setResult("No patients found.");
        }
        return "/query1result";
    }

}
