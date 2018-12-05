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
||  Class WebController
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
public class WebController {

    //JDBC Setup Functions

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*---------------------------------------------------------------------
    |  Method queriesForm
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

    @GetMapping("/queries")
    public String queriesForm(Model model) {
        return "queries";
    }

    /*---------------------------------------------------------------------
    |  Method query1
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

    @GetMapping("/query1")
    public String searchPatientRecord(Model model) {
        model.addAttribute("queryOne", new QueryResults());
        return "/query1";
    }

    /*---------------------------------------------------------------------
    |  Method searchPatientRecordQuery
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

    @PostMapping("/query1")
    public String searchPatientRecordQuery(@ModelAttribute QueryResults queryOne, Model model){
        String sql = "select Patient.PID, Patient.lName as ptfname, Patient.fName as ptlname, Patient.gender, Patient.DOB, visitDate, visitReason, treatmentMethod, Doctor.fName as docfname, Doctor.lName as doclname from Patient, TreatmentRecord, Doctor where Patient.PID = TreatmentRecord.PID and Doctor.DID = TreatmentRecord.DID and Patient.lName = '"+
         queryOne.getlName() + "' and Patient.fName = '" +queryOne.getfName() + "' and Patient.DOB = to_date('" + queryOne.getDOB() + "','DD-MON-YYYY')";
        List<String> patientsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String name = rs.getString("ptfName") +" "+ rs.getString("ptlName");;
                        String dob = rs.getString("DOB");
                        String doctor = rs.getString("docfname") +" "+ rs.getString("doclname");
                        return "Patient Name: " + name + "\nProvider: Dr. " + doctor + "\nPatient DOB: " + dob;
                    }
                });
    model.addAttribute("queryResults", patientsFound);
/*
        if(patientsFound.size() > 0) {
            query.setResult(patientsFound.get(0));
        }
        else{
            query.setResult("No patients found.");
        }
*/
        return "/query1result";
    }

    /*---------------------------------------------------------------------
    |  Method query2
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

    @GetMapping("/query2")
    public String searchDoctorRecord(Model model) {
        model.addAttribute("querytwo", new QueryTwo());
        return "/query2";
    }

    /*---------------------------------------------------------------------
    |  Method searchDoctorRecordQuery
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

    @PostMapping("/query2")
    public String searchDoctorRecordQuery(@ModelAttribute QueryTwo querytwo, Model model){
        String sql = "select Doctor.fname as dfname, Doctor.lname as dlname, Doctor.officeNo, Department.bldgName from Doctor, Department WHERE Department.name='"+ querytwo.getname() +"' AND Doctor.deptID = Department.deptID";
        List<String> doctorsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String name = rs.getString("dfname") +" "+ rs.getString("dlname");
                        String officeno = rs.getString("officeno");
                        String bldgname = rs.getString("bldgname");
                        return "Doctor Name: " + name + "\nBuilding Name: " + bldgname + "\nOffice No: " + officeno;
                    }
                });
	model.addAttribute("queryResults", doctorsFound);
        return "/query2result";
    }

    /*---------------------------------------------------------------------
    |  Method query2
    |
    |  Purpose: This method will handle the GET HTTP request by returning
    |           the name of a view. The view will then render the correct
    |           HTML content. Will also add attributes to the model and
    |           create a new instance of the class and add the attributes
    |           if needed. This function will also perform the query and
    |           parse the result as needed for the returned view.
    |
    |  Parameters: Model model- the model
    |
    |  Returns: The name of the view to load.
    |
     *-------------------------------------------------------------------*/

    @GetMapping("/query3")
    public String searchCurrentPatientQuery(Model model){
        String sql = "select Patient.PID, Patient.fName as pfname, Patient.lName as plname, expectedDischarge - trunc(SYSDATE) as DaysLeft, roomNo, amountDue from Patient, TreatmentRecord, PaymentRecord where Patient.PID = TreatmentRecord.PID  and TreatmentRecord.PID = PaymentRecord.PID and amountDue is not null and amountDue > 0 and dateHospitalized is not null and actualDischarge is null and expectedDischarge is not null and (SYSDATE + 5)<expectedDischarge";
        List<String> currPatientsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String pid = rs.getString("PID");
                        String name = rs.getString("pfname") +" "+ rs.getString("plname");
                        String numdays = rs.getString("DaysLeft");
                        String roomnum = rs.getString("roomNo");
                        String totaldue = rs.getString("amountDue");
                        return "Patient ID: " + pid + " Name: " + name + " Expected Hospital Days: " + numdays + " Room Number: " + roomnum + " Total Due: $" + totaldue;
                    }
                });
	model.addAttribute("queryResults", currPatientsFound);
        return "/query3result";
    }

    /*---------------------------------------------------------------------
    |  Method query4
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

    @GetMapping("/query4")
    public String searchReceptionistRecord(Model model) {
        model.addAttribute("queryfour", new QueryFour());
        return "/query4";
    }

    /*---------------------------------------------------------------------
    |  Method searchReceptionistRecordQuery
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

    @PostMapping("/query4")
    public String searchReceptionistRecordQuery(@ModelAttribute QueryFour queryfour, Model model){
        String sql = "select staff.fName as sfName, staff.lName as slName, staff.EID as sEID from (" +
            "select distinct receptionistEID from (" +
             "select PrescriptionRecord.PID as scriptPID from Pharmacist,PrescriptionRecord where medName = 'Viagra' and PrescriptionRecord.pharmID=" + queryfour.getID() +
            "),Appointment where scriptPID=Appointment.PID" +
        "),Staff where Staff.EID=receptionistEID and Staff.title='Receptionist'";
        List<String> receptionistsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String name = rs.getString("sfName") +" "+ rs.getString("slName");
                        String empID = rs.getString("sEID");
                        return "Receptionist Name: " + name + "\tReceptionist EID: " + empID;
                    }
                });
        model.addAttribute("queryResults", receptionistsFound);
        return "/query4result";
    }

}
