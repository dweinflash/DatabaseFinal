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
||  Class PharmacistController
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
public class PharmacistController {

    //JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method pharmacistForm
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

    @GetMapping("/addPharmacist")
    public String pharmacistForm(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "addPharmacist";
    }

    /*---------------------------------------------------------------------
    |  Method pharmacistSubmit
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

    @PostMapping("/addPharmacist")
    public String pharmacistSubmit(@ModelAttribute Pharmacist pharmacist) {
        jdbcTemplate.update("insert into pharmacist values (?, ?, ?, ?, ?, ?)", pharmacist.getPharmID(), pharmacist.getlName(), pharmacist.getfName(), pharmacist.getDOB(), pharmacist.getOfficeNum(), pharmacist.getStatus());

        return "pharmacistResult";
    }

    /*---------------------------------------------------------------------
    |  Method pharmacistFormUpdate
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

    @GetMapping("/updatePharmacist")
    public String pharmacistFormUpdate(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "updatePharmacist";
    }

    /*---------------------------------------------------------------------
    |  Method pharmacistUpdate
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

    @PostMapping("/updatePharmacist")
    public String pharmacistUpdate(@ModelAttribute Pharmacist pharmacist) {
        jdbcTemplate.update("update pharmacist set fname = ?, lname = ?, officeNum = ?, status = ? where pharmID = ?", pharmacist.getfName(), pharmacist.getlName(), pharmacist.getOfficeNum(), pharmacist.getStatus(), pharmacist.getPharmID());

      return "pharmacistResult";
    }

    /*---------------------------------------------------------------------
    |  Method pharmacistDelete
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

    @GetMapping("/deletePharmacist")
    public String pharmacistFormDelete(Model model) {
        model.addAttribute("pharmacist", new Pharmacist());
        return "deletePharmacist";
    }

    /*---------------------------------------------------------------------
    |  Method pharmacistDelete
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

    @PostMapping("/deletePharmacist")
    public String pharmacistDelete(@ModelAttribute Pharmacist pharmacist) {
      jdbcTemplate.update("delete from pharmacist where pharmID = ?", pharmacist.getPharmID());

      return "pharmacistResult";
    }


}
