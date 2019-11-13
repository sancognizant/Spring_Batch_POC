package com.example.springbatch.Mapper;

import com.example.springbatch.model.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

// this mapper maps the incoming data stream from the database to the model
public class PersonMapper implements RowMapper<Person> {

    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String AGE_COLUMN = "age";


    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();

        person.setId(rs.getInt(ID_COLUMN));
        person.setName(rs.getString(NAME_COLUMN));
        person.setAge(rs.getInt(AGE_COLUMN));
        return person;
    }
}