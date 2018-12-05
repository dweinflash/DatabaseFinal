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
||  Class CashierRecordController
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
public class CashierRecordController {

    //JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method
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

    @GetMapping("/cashierRecordInsertion")
    public String cashierRecordForm(Model model) {
        model.addAttribute("cashierRecord", new CashierRecord());
        return "cashierRecordInsertion";
    }

    /*---------------------------------------------------------------------
    |  Method cashierRecordSubmit
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

    @PostMapping("/cashierRecordInsertion")
    public String cashierRecordSubmit(@ModelAttribute CashierRecord cashierRecord) {
        jdbcTemplate.update("insert into PaymentReocrd values (?, ?, ?, ?, ?, ?, ?)", cashierRecord.getPaymentID(), cashierRecord.getCashierEID(), cashierRecord.getPID(), cashierRecord.getAmountDue(),  cashierRecord.getDueDate(),  cashierRecord.getPaymentStatus(), cashierRecord.getPaymentDate() );

        return "cashierRecordResult";
    }

    /*---------------------------------------------------------------------
    |  Method cashierRecordFormUpdate
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

    @GetMapping("/cashierRecordUpdate")
    public String cashierRecordFormUpdate(Model model) {
        model.addAttribute("cashierRecord", new CashierRecord());
        return "updateCashierRecord";
    }

    /*---------------------------------------------------------------------
    |  Method cashierRecordUpdate
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

    @PostMapping("/cashierRecordUpdate")
    public String cashierRecordUpdate(@ModelAttribute CashierRecord cashierRecord) {
      jdbcTemplate.update("update  PaymentRecord set  cashierEID = ?, amountDue = ?, dueDate = ?, paymentStatus = ?, paymentDate = ? where first_name = ? and last_name = ? where paymentID = ? ", cashierRecord.getCashierEID(), cashierRecord.getAmountDue(),  cashierRecord.getDueDate(),  cashierRecord.getPaymentStatus(), cashierRecord.getPaymentDate(), cashierRecord.getPaymentID());

      return "cashierRecordResult";
    }


}
