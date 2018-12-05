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
        jdbcTemplate.update("insert into patient values (?, ?, ?, ?, ?, ?, ?)", patient.getPID(), patient.getlName(), patient.getfName(), patient.getgender(), patient.getDOB(),  patient.getaddress(), patient.getcontactNo());

        return "patientResult";
    }

    @GetMapping("/updatePatient")
    public String patientFormUpdate(Model model) {
        model.addAttribute("patient", new Patient());
        return "updatePatient";
    }

    @PostMapping("/updatePatient")
    public String patientUpdate(@ModelAttribute Patient patient) {
      jdbcTemplate.update("update patient set lName = ?, fName = ?, gender = ?, address = ?, contactNo = ? where PID = ?", patient.getfName(), patient.getlName(), patient.getgender(), patient.getaddress(), patient.getcontactNo(),patient.getPID());

      return "patientResult";
    }

    @GetMapping("/deletePatient")
    public String patientFormDelete(Model model) {
        model.addAttribute("patient", new Patient());
        return "deletePatient";
    }

    @PostMapping("/deletePatient")
    public String patientDelete(@ModelAttribute Patient patient) {
      jdbcTemplate.update("delete from patient where PID = ?", patient.getPID());

      return "patientResult";
    }

}
