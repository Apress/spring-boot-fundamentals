package com.apress;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class ContactDAO implements CrudRepository<Contact> {

    private final String SQL_QUERY_ALL = "select name,email,phone from contact";

    private JdbcTemplate jdbcTemplate;

    private RowMapper  rowMapper = (ResultSet rs, int row)-> {
        Contact contact = new Contact();
        contact.setName(rs.getString(1));
        contact.setEmail(rs.getString(2));
        contact.setPhone(rs.getString(3));
        return  contact;
    };

    public ContactDAO(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Iterable<Contact> findAll() {
        return this.jdbcTemplate.query(SQL_QUERY_ALL, rowMapper);
    }

}