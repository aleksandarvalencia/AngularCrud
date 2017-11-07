/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projects.angularcrud.service;

import org.springframework.jdbc.object.StoredProcedure;
import java.sql.ResultSet; 
import java.sql.SQLException; 
//import java.sql.Types; 
//import java.util.HashMap; 
import java.util.List; 
import java.util.Map;   
import javax.sql.DataSource;  
import oracle.jdbc.OracleTypes;
//import java.sql.Types;
//import org.apache.commons.lang.StringUtils; 

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.jdbc.core.RowMapper; 
import org.springframework.jdbc.core.SqlParameter; 
import org.springframework.jdbc.core.SqlReturnResultSet; 
import com.projects.angularcrud.exceptions.CustomException;
import com.projects.angularcrud.exceptions.DatabaseException;
import com.projects.angularcrud.exceptions.DataAccessException; 
import com.projects.angularcrud.model.Attributes;
import com.projects.angularcrud.model.ComboValues;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import com.projects.angularcrud.model.TData;
import org.springframework.jdbc.core.SqlOutParameter;
//import org.springframework.dao.InvalidDataAccessApiUsageException;
//import org.springframework.jdbc.core.SqlOutParameter;
//import com.projects.halkom.service.StoredProcedureCall;
/**
 *
 * @author bd101009
 */
public class StoredProcedureCall 
{
    private String SPROC_NAME = ""; 
    private final JdbcTemplate jdbcTemplate;
    private final DataSource data_source;
    //Map<String, Object> in_params;
    SqlParameter[] in_params;
    
   /* @Autowired
    public void setDataSource(DataSource dataSource) 
    { this.jdbcTemplate = new JdbcTemplate(dataSource); 
    } 
    public JdbcTemplate getJdbcTemplate() 
    { return jdbcTemplate; } */
    
   // public StoredProcedureCall(DataSource ds, String proc_name,Map<String, Object> params)
    public StoredProcedureCall(DataSource ds, String proc_name,SqlParameter[] params)
    {
        this.SPROC_NAME=proc_name;
        this.data_source=ds;
        this.in_params=params;
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public JdbcTemplate getJdbcTemplate() 
    { 
        return jdbcTemplate;
    } 
    final class CustomizedStoredProcedure extends StoredProcedure 
    { 
        //private static final String ACCOUNTNUMBER_PARAM = "@ACCTNUM";   
        //public  CustomizedStoredProcedure(DataSource dataSource, String sprocName,Map<String,Object> params, Integer combo) throws RuntimeException
         public  CustomizedStoredProcedure(DataSource dataSource, String sprocName,SqlParameter[] parameters, Integer combo) throws RuntimeException
        { 
            super(dataSource, sprocName);
           /* if (combo==0)
            {
               // declareParameter(new SqlReturnResultSet("rs", new TreeViewMapper())); 
               declareParameter(new SqlOutParameter("rs", OracleTypes.CURSOR, new TreeViewMapper()));

            }
            else
            {
                declareParameter(new SqlReturnResultSet("combo", new ComboValueMapper())); 
            }*/
            
            
            /*Map<String,Object> paramsToUse =params;
            String[] keys = new String[paramsToUse.size()];
            Object[] values = new Object[paramsToUse.size()];
            int index = 0;
            for (Map.Entry<String,Object> mapEntry : paramsToUse.entrySet())
            {
                keys[index] = mapEntry.getKey();//Naziv parametra
                values[index] = mapEntry.getValue();//tip parametra
                declareParameter(new SqlParameter(keys[index],(int)(values[index])));
                index++;
            }
            declareParameter(new SqlOutParameter("RetVal",Types.INTEGER));*/
            setFunction(false);
            setParameters(parameters);
            compile(); 
            
            
        }   
       
        /*@Override
     	public void declareParameter(SqlParameter param) throws InvalidDataAccessApiUsageException {
     		if (param.getName() == null) {
     			throw new InvalidDataAccessApiUsageException("Parameters to stored procedures must have names as well as types");
      		}
      		super.declareParameter(param);
     	}
        @Override
        public Map execute(Map<String, ?> inParams) throws RuntimeException
        { 
            
            validateParameters(inParams.values().toArray());
            Map inputs = new HashMap(); 
            inputs.putAll(inParams);
            return super.execute(inputs); 
        }  */
        
    }  
   
   // public void executeProc(Map<String, ?> inParams) throws RuntimeException
    public Map executeProc(Object... inParams) throws RuntimeException
    {
        Map results;
        if (data_source == null) 
        { throw new DatabaseException("Greska u definisanju objekta!");} 
        try 
        { 
           
            CustomizedStoredProcedure proc=new CustomizedStoredProcedure(data_source,SPROC_NAME,in_params,1);
            results=proc.execute(inParams); 
            //res = (Integer)results.get("rs");
        }
        catch (RuntimeException ex) 
        {
            throw new CustomException(ex.getMessage()); 
        }   
        
        return results;
    }
    //public List<JsTreeNode> get(Map<String, ?> inParams) throws RuntimeException 
    /*public List<JsTreeNode> get(Object... inParams) throws RuntimeException
    {
         
        if (data_source == null) 
        { throw new DatabaseException("Greska u definisanju objekta!");} 
        try 
        { 
            
            CustomizedStoredProcedure proc=new CustomizedStoredProcedure(data_source,SPROC_NAME,in_params,0);
            
            Map results = proc.execute(inParams); 
            List<JsTreeNode> jstree = (List<JsTreeNode>) results.get("rs");
            if (!CollectionUtils.isEmpty(jstree)) 
            { 
                return jstree; 
            }
                return null; 
        }
        catch (RuntimeException ex) 
        { throw new DataAccessException(ex.getMessage()); 
        } 
    }*/
    
     //public List<ComboValues> getComboValue(Map<String, ?> inParams) throws RuntimeException 
     public List<ComboValues> getComboValue(Object... inParams) throws RuntimeException 
    {
         
        if (data_source == null) 
        { throw new DatabaseException("Greska u definisanju objekta!");} 
        try 
        { 
           
            CustomizedStoredProcedure proc=new CustomizedStoredProcedure(data_source,SPROC_NAME,in_params,1);
            Map results = proc.execute(inParams); 
            List<ComboValues> combo = (List<ComboValues>) results.get("combo");
            return combo;
        }
        catch (RuntimeException ex) 
        { throw new DatabaseException(ex.getMessage()); 
        } 
    }
    
    
     /*private static final class TreeViewMapper implements RowMapper<JsTreeNode> {
                @Override
		public JsTreeNode mapRow(ResultSet rs, int rowNum) throws SQLException {
			JsTreeNode jst = new JsTreeNode();
                        Attributes att = new Attributes();
                        TData tdata=new TData();
                        att.setText(rs.getString("itemAction"));
                        jst.setId(rs.getString("idTv"));
                        jst.setText(rs.getString("itemTitle"));
                        jst.setIdAuth(rs.getString("idAuth"));
                        jst.setIdParent(rs.getString("idParent"));
                        jst.setData(att);
                        tdata.setNodeid(rs.getString("idTv"));
                        tdata.setNodename(rs.getString("itemTitle"));
                        tdata.setHref(rs.getString("itemAction"));
                        jst.setMetadata(tdata);
			return jst;
		}
	}*/
      private static final class ComboValueMapper implements RowMapper<ComboValues> {
                @Override
		public ComboValues mapRow(ResultSet rs, int rowNum) throws SQLException {
			ComboValues cmb = new ComboValues(Integer.parseInt(rs.getString("idMenuModule")),rs.getString("ModuleCaption"));
			return cmb;
		}
	}
     
}
