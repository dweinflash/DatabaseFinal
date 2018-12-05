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
||  Class PatientController
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
public class PatientController {

//JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method patientForm
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

    @GetMapping("/addPatient")
    public String patientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "addPatient";
    }

    /*---------------------------------------------------------------------
    |  Method patientSubmit
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

    @PostMapping("/addPatient")
    public String patientSubmit(@ModelAttribute Patient patient) {
        jdbcTemplate.update("insert into patient values (?, ?, ?, ?, ?, ?, ?)", patient.getPID(), patient.getlName(), patient.getfName(), patient.getgender(), patient.getDOB(),  patient.getaddress(), patient.getcontactNo());

        return "patientResult";
    }

    /*---------------------------------------------------------------------
    |  Method patientFormUpdate
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

    @GetMapping("/updatePatient")
    public String patientFormUpdate(Model model) {
        model.addAttribute("patient", new Patient());
        return "updatePatient";
    }

    /*---------------------------------------------------------------------
    |  Method patientUpdate
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

    @PostMapping("/updatePatient")
    public String patientUpdate(@ModelAttribute Patient patient) {
      jdbcTemplate.update("update patient set lName = ?, fName = ?, gender = ?, address = ?, contactNo = ? where PID = ?", patient.getfName(), patient.getlName(), patient.getgender(), patient.getaddress(), patient.getcontactNo(),patient.getPID());

      return "patientResult";
    }

    /*---------------------------------------------------------------------
    |  Method patientFormDelete
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

    @GetMapping("/deletePatient")
    public String patientFormDelete(Model model) {
        model.addAttribute("patient", new Patient());
        return "deletePatient";
    }

    /*---------------------------------------------------------------------
    |  Method patientDelete
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

    @PostMapping("/deletePatient")
    public String patientDelete(@ModelAttribute Patient patient) {
      jdbcTemplate.update("delete from patient where PID = ?", patient.getPID());

      return "patientResult";
    }

}
