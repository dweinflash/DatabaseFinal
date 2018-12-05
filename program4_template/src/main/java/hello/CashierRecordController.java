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
public class CashierRecordController {

  @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/cashierRecordInsertion")
    public String cashierRecordForm(Model model) {
        model.addAttribute("cashierRecord", new CashierRecord());
        return "cashierRecordInsertion";
    }

    @PostMapping("/cashierRecordInsertion")
    public String cashierRecordSubmit(@ModelAttribute CashierRecord cashierRecord) {
        jdbcTemplate.update("insert into PaymentReocrd values (?, ?, ?, ?, ?, ?, ?)", cashierRecord.getPaymentID(), cashierRecord.getCashierEID(), cashierRecord.getPID(), cashierRecord.getAmountDue(),  cashierRecord.getDueDate(),  cashierRecord.getPaymentStatus(), cashierRecord.getPaymentDate() );

        return "cashierRecordResult";
    }

    @GetMapping("/cashierRecordUpdate")
    public String cashierRecordFormUpdate(Model model) {
        model.addAttribute("cashierRecord", new CashierRecord());
        return "updateCashierRecord";
    }

    @PostMapping("/cashierRecordUpdate")
    public String cashierRecordUpdate(@ModelAttribute CashierRecord cashierRecord) {
      jdbcTemplate.update("update  cashierRecord set  cashierEID = ?, amountDue = ?, dueDate = ?, paymentStatus = ?, paymentDate = ? where first_name = ? and last_name = ? where paymentID = ? ", cashierRecord.getCashierEID(), cashierRecord.getAmountDue(),  cashierRecord.getDueDate(),  cashierRecord.getPaymentStatus(), cashierRecord.getPaymentDate(), cashierRecord.getPaymentID());

      return "cashierRecordResult";
    }


}
