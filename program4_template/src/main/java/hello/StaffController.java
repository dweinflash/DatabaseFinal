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
public class StaffController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addStaff")
    public String staffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "addStaff";
    }

    @PostMapping("/addStaff")
    public String staffSubmit(@ModelAttribute Staff staff) {
        jdbcTemplate.update("insert into .staff values (?, ?)", staff.getfName());

        return "staffResult";
    }

    @GetMapping("/updateStaff")
    public String staffFormUpdate(Model model) {
        model.addAttribute("staff", new Staff());
        return "updateStaff";
    }

    @PostMapping("/updateStaff")
    public String staffUpdate(@ModelAttribute Staff staff) {
      jdbcTemplate.update("update from .staff where first_name = ? and last_name = ?", staff.getfName(), staff.getlName());

      return "staffResult";
    }

    @GetMapping("/deleteStaff")
    public String staffFormDelete(Model model) {
        model.addAttribute("staff", new Staff());
        return "deleteStaff";
    }

    @PostMapping("/deleteStaff")
    public String staffDelete(@ModelAttribute Staff staff) {
      jdbcTemplate.update("delete from .staff where first_name = ? and last_name = ?", staff.getfName(), staff.getlName());

      return "staffResult";
    }

}
