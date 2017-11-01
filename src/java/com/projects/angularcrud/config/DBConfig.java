/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author BD101009
 */
public class DBConfig 
{
    @Autowired
        private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
        private SimpleJdbcCall jdbcCall;
        //private JdbcTemplate jdbcTemplateObject;

    public SimpleJdbcCall getJdbcCall() {
        return jdbcCall;
    }

    public void setJdbcCall(SimpleJdbcCall jdbcCall) {
        this.jdbcCall = jdbcCall;
    }
       

    @Bean
    public SimpleJdbcCall  getSimpleJdbcCall()
    {
        return new SimpleJdbcCall(dataSource);
    }

    public void startDBManager() 
    {



    }
    
}
