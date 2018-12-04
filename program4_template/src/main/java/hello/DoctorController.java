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
public class DoctorController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addDoctor")
    public String doctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "addDoctor";
    }

    @PostMapping("/addDoctor")
    public String doctorSubmit(@ModelAttribute Doctor doctor) {
        jdbcTemplate.update("insert into .doctor values (?, ?)", doctor.getfName());

        return "doctorResult";
    }

    @GetMapping("/updateDoctor")
    public String doctorFormUpdate(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "updateDoctor";
    }

    @PostMapping("/updateDoctor")
    public String doctorUpdate(@ModelAttribute Doctor doctor) {
      jdbcTemplate.update("update from .doctor where first_name = ? and last_name = ?", doctor.getfName(), doctor.getlName());

      return "doctorResult";
    }

    @GetMapping("/deleteDoctor")
    public String doctorFormDelete(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "deleteDoctor";
    }

    @PostMapping("/deleteDoctor")
    public String doctorDelete(@ModelAttribute Doctor doctor) {
      jdbcTemplate.update("delete from .doctor where first_name = ? and last_name = ?", doctor.getfName(), doctor.getlName());

      return "doctorResult";
    }


}
