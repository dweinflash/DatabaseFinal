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
public class WebController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/queries")
    public String queriesForm(Model model) {
        return "queries";
    }

    @GetMapping("/query1")
    public String searchPatientRecord(Model model) {
        model.addAttribute("queryOne", new QueryResults());
        return "/query1";
    }

    @PostMapping("/query1")
    public String searchPatientRecordQuery(@ModelAttribute QueryResults queryOne, Model model){
        String sql = "select Patient.PID, Patient.lName as ptfname, Patient.fName as ptlname, Patient.gender, Patient.DOB, visitDate, visitReason, treatmentMethod, Doctor.fName as docfname, Doctor.lName as doclname from Patient, TreatmentRecord, Doctor where Patient.PID = TreatmentRecord.PID and Doctor.DID = TreatmentRecord.DID and Patient.lName = '" + queryOne.getlName() + "' and Patient.fName = '" +queryOne.getfName() + "' and Patient.DOB = " + queryOne.getDOB();
        List<String> patientsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String name = rs.getString("ptfName") +" "+ rs.getString("ptlName");;
                        String dob = rs.getString("DOB");
                        String doctor = rs.getString("docfname") +" "+ rs.getString("docfname");
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

    @GetMapping("/query2")
    public String searchDoctorRecord(Model model) {
        model.addAttribute("querytwo", new QueryTwo());
        return "/query2";
    }

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

    @GetMapping("/query3")
    public String searchCurrentPatientQuery(Model model){
        String sql = "select Patient.PID, Patient.fName as pfname, Patient.lName as plname, expectedDischarge - SYSDATE as DaysLeft, roomNo, amountDue from Patient, TreatmentRecord, PaymentRecord where Patient.PID = TreatmentRecord.PID  and TreatmentRecord.PID = PaymentRecord.PID and amountDue is not null and amountDue > 0 and dateHospitalized is not null and actualDischarge is null and expectedDischarge is not null and (SYSDATE + 5)<expectedDischarge";
        List<String> currPatientsFound = this.jdbcTemplate.query(
                sql,
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String pid = rs.getString("PID");
                        String name = rs.getString("pfname") +" "+ rs.getString("plname");
                        String numdays = rs.getString("DaysLeft");
                        String roomnum = rs.getString("roomNo");
                        String totaldue = rs.getString("amountDue");
                        return "Patient ID: " + pid + " Name: " + name + " Expected Hospital Days: " + numdays + " Room Number: " + roomnum + " Total Due: " + totaldue;
                    }
                });
	model.addAttribute("queryResults", currPatientsFound);
        return "/query3result";
    }

    @GetMapping("/query4")
    public String query4Form(Model model) {
        return "query4";
    }

}
