package com.example.springbatch.Mapper;

import com.example.springbatch.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// this mapper maps the incoming data stream from the database to the model
public class PersonMapper implements RowMapper<Person> {

  //  public static final String ID_COLUMN = "id";
    private String FIRST_NAME_COLUMN = "firstName";
    private String LAST_NAME_COLUMN = "lastName";
    private String BIRTH_DATE_COLUMN = "birthDate";
    private String BANKRUPTCY_DATE_COLUMN ="bankruptcyDate";
    private String BANKRUPTCY_REASON_COLUMN  = "bankruptcyReason";
    private String BANKRUPTCY_COLUMN = "bankruptcyChapter";
    private String START_DATE_COLUMN = "startDate";
    private String SSNCODE_COLUMN = "SSNCode";
    private String ZIP_CODE_COLUMN = "zipCode";
    private String YEARLY_INCOME_COLUMN = "yearlyIncome";
    private String CREDIT_SCORE_COLUMN = "creditScore";
    private String AMOUNT_COLUMN = "amount";
    private String DURATION_COLUMN =  "duration";
    private String YEARLY_INTEREST_RATE_COLUMN = "yearlyInterestRate";
    private String SHOW_TRACE_COLUMN  =  "showtrace";




    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        // map the database values to the object
        person.setFirstName(rs.getString(FIRST_NAME_COLUMN));
        person.setLastName(rs.getString(LAST_NAME_COLUMN));
        person.setBirthDate(rs.getString(BIRTH_DATE_COLUMN));
        person.setBankruptcyDate(rs.getString(BANKRUPTCY_DATE_COLUMN));
        person.setBankruptcyReason(rs.getString(BANKRUPTCY_REASON_COLUMN));
        person.setBankruptcyChapter(rs.getString(BANKRUPTCY_COLUMN));
        person.setStartDate(rs.getString(START_DATE_COLUMN));
        person.setSSNCode(rs.getString(SSNCODE_COLUMN));
        person.setZipCode(rs.getString(ZIP_CODE_COLUMN));
        person.setYearlyIncome(rs.getFloat(YEARLY_INCOME_COLUMN));
        person.setCreditScore(rs.getFloat(CREDIT_SCORE_COLUMN));
        person.setAmount(rs.getFloat(AMOUNT_COLUMN));
        person.setDuration(rs.getFloat(DURATION_COLUMN));
        person.setYearlyInterestRate(rs.getFloat(YEARLY_INTEREST_RATE_COLUMN));
        person.setShowtrace(rs.getInt(SHOW_TRACE_COLUMN));









        person.setYearlyIncome(rs.getFloat(YEARLY_INCOME_COLUMN));
        return person;
    }
}