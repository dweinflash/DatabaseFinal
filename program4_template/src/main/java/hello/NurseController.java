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
||  Class NurseController
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
public class NurseController {

    //JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method nurseForm
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

    /*---------------------------------------------------------------------
    |  Method nurseFormUpdate
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

    /*---------------------------------------------------------------------
    |  Method nurseFormDelete
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

    @GetMapping("/deleteNurse")
    public String nurseFormDelete(Model model) {
        model.addAttribute("nurse", new Nurse());
        return "deleteNurse";
    }

    /*---------------------------------------------------------------------
    |  Method nurseDelete
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

    @PostMapping("/deleteNurse")
    public String nurseDelete(@ModelAttribute Nurse nurse) {
      jdbcTemplate.update("delete from nurse where nurseID = ?", nurse.getNurseID());

      return "nurseResult";
    }


}
