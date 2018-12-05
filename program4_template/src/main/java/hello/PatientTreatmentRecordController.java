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
||  Class PatientTreatmentRecordController
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
public class PatientTreatmentRecordController {

    //JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method patientTreatmentRecordForm
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

    @GetMapping("/ptRecordInsertion")
    public String patientTreatmentRecordForm(Model model) {
        model.addAttribute("patientTreatmentRecord", new PatientTreatmentRecord());
        return "ptRecordInsertion";
    }

    @PostMapping("/ptRecordInsertion")
    public String patientTreatmentRecordSubmit(@ModelAttribute PatientTreatmentRecord patientTreatmentRecord) {
        jdbcTemplate.update("insert into TreatmentReocord values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", patientTreatmentRecord.getPID(), patientTreatmentRecord.getDID(), patientTreatmentRecord.getApptNo(), patientTreatmentRecord.getVisitReason(), patientTreatmentRecord.getVisitDate(), patientTreatmentRecord.getDateHospitalized(), patientTreatmentRecord.getExpectedDischarge(), patientTreatmentRecord.getActualDischarge(), patientTreatmentRecord.getRoomNo(), patientTreatmentRecord.getTreatmentMethod());

        return "patientTreatmentRecordResult";
    }

    /*---------------------------------------------------------------------
    |  Method patientTreatmentRecordFormUpdate
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

    @GetMapping("/ptRecordUpdate")
    public String patientTreatmentRecordFormUpdate(Model model) {
        model.addAttribute("patientTreatmentRecord", new PatientTreatmentRecord());
        return "ptRecordUpdate";
    }

    /*---------------------------------------------------------------------
    |  Method patientTreatmentRecordUpdate
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

    @PostMapping("/ptRecordUpdate")
    public String patientTreatmentRecordUpdate(@ModelAttribute PatientTreatmentRecord patientTreatmentRecord) {
      jdbcTemplate.update("update  TreatmentRecord set dateHospitalized = ?, expectedDischarge = ?, actualDischarge = ?, roomNo = ?, treatmentMethod = ? where PID = ? and DID = ? and apptNo = ?", patientTreatmentRecord.getDateHospitalized(), patientTreatmentRecord.getExpectedDischarge(), patientTreatmentRecord.getActualDischarge(), patientTreatmentRecord.getRoomNo(), patientTreatmentRecord.getTreatmentMethod(), patientTreatmentRecord.getPID(), patientTreatmentRecord.getDID(), patientTreatmentRecord.getApptNo());

      return "patientTreatmentRecordResult";
    }

}
