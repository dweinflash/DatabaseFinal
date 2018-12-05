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
public class DepartmentRecordController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/departmentRecordUpdate")
    public String departmentRecordFormUpdate(Model model) {
        model.addAttribute("departmentRecord", new DepartmentRecord());
        return "departmentRecordUpdate";
    }

    @PostMapping("/departmentRecordUpdate")
    public String departmentRecordUpdate(@ModelAttribute DepartmentRecord departmentRecord) {
      jdbcTemplate.update("update departmentRecord set deptOffNum = ?, name = ?, bldgName = ? where deptId = ?", departmentRecord.getDeptOffNum(), departmentRecord.getName(), departmentRecord.getBldgName(), departmentRecord.getDeptID());

      return "departmentRecordResult";
    }


}
