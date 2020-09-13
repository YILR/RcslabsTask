package example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
@RequestMapping("/")
@RestController
public class TaxController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(value = "row", required = false) String row, @RequestParam(value = "col", required = false) String col){
        String sql = "select " + row + " as row, " + col + " as col, sum(v) as val " +" from source_data group by " + col + ", " + row;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}