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
public class PharmacistController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addPharmacist")
    public String pharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "addPharmacist";
    }

    @PostMapping("/addPharmacist")
    public String pharmacistSubmit(@ModelAttribute Pharmacist pharmacist) {
        jdbcTemplate.update("insert into pharmacist values (?, ?, ?, ?, ?, ?)", pharmacist.getPharmID(), pharmacist.getlName(), pharmacist.getfName(), pharmacist.getDOB(), pharmacist.getOfficeNum(), pharmacist.getStatus());

        return "pharmacistResult";
    }

    @GetMapping("/updatePharmacist")
    public String pharmacistFormUpdate(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "updatePharmacist";
    }

    @PostMapping("/updatePharmacist")
    public String pharmacistUpdate(@ModelAttribute Pharmacist pharmacist) {
        jdbcTemplate.update("update pharmacist set fname = ?, lname = ?, officeNum = ?, status = ? where pharmID = ?", pharmacist.getfName(), pharmacist.getlName(), pharmacist.getOfficeNum(), pharmacist.getStatus(), pharmacist.getPharmID());

      return "pharmacistResult";
    }

    @GetMapping("/deletePharmacist")
    public String pharmacistFormDelete(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "deletePharmacist";
    }

    @PostMapping("/deletePharmacist")
    public String pharmacistDelete(@ModelAttribute Pharmacist pharmacist) {
      jdbcTemplate.update("delete from pharmacist where pharmID = ?", pharmacist.getPharmID());

      return "pharmacistResult";
    }


}
