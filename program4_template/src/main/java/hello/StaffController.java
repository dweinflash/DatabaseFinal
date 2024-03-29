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

/*+----------------------------------------------------------------------
||
||  Class StaffController
||
||
||        Purpose:  Resposible for the controller of the MVC. Will handle
||                the HTTP requests. Identified by the @Controller annotation.
||                Controller will handle the GET requests by returning a view
||                As well as handling any other requests such as POST and its
||                correct endpoint mapping. Handles all communication with the
||                database using JDBC for queries and sql statements.
||
||
++-----------------------------------------------------------------------*/

@Controller
public class StaffController {

    //JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method staffForm
    |
    |  Purpose: This method will handle the GET HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also add attributes to the model and
    |           create a new instance of the class and add the attributes
    |           if needed.
    |
    |  Parameters: Model model- the model
    |
    |  Returns: The name of the view to load.
    |
     *-------------------------------------------------------------------*/

    @GetMapping("/addStaff")
    public String staffForm(Model model) {
        model.addAttribute("staff", new Staff());
        return "addStaff";
    }

    /*---------------------------------------------------------------------
    |  Method staffSubmit
    |
    |  Purpose: This medthod will handle the POST HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also take the created class object and
    |           use it's field values in order to perform the proper
    |           JDBC sql statement if necessary.
    |
    |  Parameters: ModelAttribute of class object
    |
    |  Returns: The name of the result view
    |
     *-------------------------------------------------------------------*/

    @PostMapping("/addStaff")
    public String staffSubmit(@ModelAttribute Staff staff) {
        jdbcTemplate.update("insert into staff values (?, ?, ?,?, ?, ?,?, ?,? )", staff.getEID(), staff.getlName(), staff.getfName(), staff.getDOB(), staff.getDeptID(), staff.getSalary(), staff.getContactNo(), staff.getTitle(), staff.getGender());

        return "staffResult";
    }

    /*---------------------------------------------------------------------
    |  Method staffFormUpdate
    |
    |  Purpose: This method will handle the GET HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also add attributes to the model and
    |           create a new instance of the class and add the attributes
    |           if needed.
    |
    |  Parameters: Model model- the model
    |
    |  Returns: The name of the view to load.
    |
     *-------------------------------------------------------------------*/

    @GetMapping("/updateStaff")
    public String staffFormUpdate(Model model) {
        model.addAttribute("staff", new Staff());
        return "updateStaff";
    }

    /*---------------------------------------------------------------------
    |  Method staffUpdate
    |
    |  Purpose: This medthod will handle the POST HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also take the created class object and
    |           use it's field values in order to perform the proper
    |           JDBC sql statement if necessary.
    |
    |  Parameters: ModelAttribute of class object
    |
    |  Returns: The name of the result view
    |
     *-------------------------------------------------------------------*/

    @PostMapping("/updateStaff")
    public String staffUpdate(@ModelAttribute Staff staff) {
        jdbcTemplate.update("update staff set fname = ?, lname = ?, deptID = ?, salary = ?, contactNo = ?, title  = ?, gender = ?,  where EID = ?", staff.getfName(), staff.getlName(), staff.getDeptID(), staff.getSalary(), staff.getContactNo(), staff.getTitle(), staff.getGender(), staff.getEID());

      return "staffResult";
    }

    /*---------------------------------------------------------------------
    |  Method staffFormDelete
    |
    |  Purpose: This method will handle the GET HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also add attributes to the model and
    |           create a new instance of the class and add the attributes
    |           if needed.
    |
    |  Parameters: Model model- the model
    |
    |  Returns: The name of the view to load.
    |
     *-------------------------------------------------------------------*/

    @GetMapping("/deleteStaff")
    public String staffFormDelete(Model model) {
        model.addAttribute("staff", new Staff());
        return "deleteStaff";
    }

    /*---------------------------------------------------------------------
    |  Method staffDelete
    |
    |  Purpose: This medthod will handle the POST HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also take the created class object and
    |           use it's field values in order to perform the proper
    |           JDBC sql statement if necessary.
    |
    |  Parameters: ModelAttribute of class object
    |
    |  Returns: The name of the result view
    |
     *-------------------------------------------------------------------*/

    @PostMapping("/deleteStaff")
    public String staffDelete(@ModelAttribute Staff staff) {
      jdbcTemplate.update("delete from staff where EID = ?", staff.getEID());

      return "staffResult";
    }

}
