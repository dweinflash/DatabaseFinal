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
        jdbcTemplate.update("insert into staff values (?, ?, ?,?, ?, ?,?, ?,? )", staff.getEID(), staff.getlName(), staff.getfName(), staff.getDOB(), staff.getDeptID(), staff.getSalary(), staff.getContactNo(), staff.getTitle(), staff.getGender());

        return "staffResult";
    }

    @GetMapping("/updateStaff")
    public String staffFormUpdate(Model model) {
        model.addAttribute("staff", new Staff());
        return "updateStaff";
    }

    @PostMapping("/updateStaff")
    public String staffUpdate(@ModelAttribute Staff staff) {
        jdbcTemplate.update("update staff set fname = ?, lname = ?, deptID = ?, salary = ?, contactNo = ?, title  = ?, gender = ?,  where EID = ?", staff.getfName(), staff.getlName(), staff.getDeptID(), staff.getSalary(), staff.getContactNo(), staff.getTitle(), staff.getGender(), staff.getEID());

      return "staffResult";
    }

    @GetMapping("/deleteStaff")
    public String staffFormDelete(Model model) {
        model.addAttribute("staff", new Staff());
        return "deleteStaff";
    }

    @PostMapping("/deleteStaff")
    public String staffDelete(@ModelAttribute Staff staff) {
      jdbcTemplate.update("delete from staff where EID = ?", staff.getEID());

      return "staffResult";
    }

}
