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
    public String query1Form(Model model) {
        return "query1";
    }
    @GetMapping("/query2")
    public String query2Form(Model model) {
        return "query2";
    }
    @GetMapping("/query3")
    public String query3Form(Model model) {
        return "query3";
    }
    @GetMapping("/query4")
    public String query4Form(Model model) {
        return "query4";
    }

}
