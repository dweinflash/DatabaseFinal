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
public class NurseController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addNurse")
    public String nurseForm(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "addNurse";
    }

    @PostMapping("/addNurse")
    public String nurseSubmit(@ModelAttribute Nurse nurse) {
        jdbcTemplate.update("insert into nurse values (?, ?, ?, ?, ?, ?)", nurse.getNurseID(), nurse.getlName(), nurse.getfName(), nurse.getDOB(), nurse.getRoomNum(), nurse.getDeptID());

        return "nurseResult";
    }

    @GetMapping("/updateNurse")
    public String nurseFormUpdate(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "updateNurse";
    }

    @PostMapping("/updateNurse")
    public String nurseUpdate(@ModelAttribute Nurse nurse) {
      jdbcTemplate.update("update from nurse set fname = ?, lname = ?, roomNo = ?, deptID = ? where nurseID = ?", nurse.getfName(), nurse.getlName(), nurse.getRoomNum(), nurse.getDeptID(), nurse.getNurseID());
      return "nurseResult";
    }

    @GetMapping("/deleteNurse")
    public String nurseFormDelete(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "deleteNurse";
    }

    @PostMapping("/deleteNurse")
    public String nurseDelete(@ModelAttribute Nurse nurse) {
      jdbcTemplate.update("delete from nurse where nurseID = ?", nurse.getNurseID());

      return "nurseResult";
    }


}
