package com.next.jpatis.core;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JdbcAccess
{
	final Logger logger = LoggerFactory.getLogger(JdbcAccess.class);
	

	public Connection conn;
	CallableStatement stat;
	protected ResultSet resultSet;
	
	JdbcAccess(Connection conn) 
	{
		this.conn = conn;
	}


	//@Override
	public int getUpdateCount() 
	{
		return updateCount;
	}
	int updateCount;
	public void close() throws SQLException
	{
		if(stat!=null)
		{
			stat.close();
			stat = null;
		}
		if(resultSet!=null)
		{
			resultSet.close();
			resultSet = null;
		}
		updateCount = -1;
	}


	public ResultSet queryScalar(String sql, Object... values) throws Exception
	{
		resultSet = query(sql, values);
		if(resultSet.next() == false)
		{
			throw new SQLNoDataException(getSqlForLog(sql, values));
		}
		return resultSet;
	}
	public Integer queryScalarInt(String sql, Object... values) throws Exception
	{
		return queryScalar(sql, values).getInt(1);
	}
	public String queryScalarString(String sql, Object... values) throws Exception
	{
		return queryScalar(sql, values).getString(1);
	}

	
	public void changeSingle(String sql, Object... values) throws Exception
	{
		exec(sql, values);
		if(updateCount!=1)
		{
			throw new SQLException("no record changed");
		}
	}
	

	public ResultSet query(String sql, Object... values) throws JpatisException 
	{
		exec(sql, values);
		return resultSet;
	}
	public ResultSet getResultSet()
	{
		return this.resultSet;
	}
	protected String getSqlForLog(String sql, Object[] values)
	{
		if(values == null) return "";
		StringBuilder sb = new StringBuilder();
		sb.append(sql).append(": ");
		for(Object v : values)
		{
			/*if(v==null)
			{
				sb.append("null");
			}
			else
			{*/
				sb.append(v);
			//}
			sb.append(", ");
		}
		sb.setLength(sb.length()-2);
		return  sb.toString();
	}
	//@Override
	public void exec(String sql, Object... values) throws JpatisException
	{
		try
		{
		String sqlLog = getSqlForLog(sql, values);
		logger.debug("begin SQL : {}", sqlLog);
		close();
		stat = conn.prepareCall(sql);
		if(values!=null)
		{
			int i = 1;
			for(Object v:values)
			{
				stat.setObject(i,v);
				i++;
			}
		}
		updateCount = -1;
		resultSet = null;
		if(stat.execute())
		{
			resultSet = stat.getResultSet();
		}
		else
		{
			updateCount = stat.getUpdateCount();
		}
		logger.debug("finish execute SQL : {}", sqlLog);
		}
		catch(SQLException ex)
		{
			throw new JpatisException(ex);
		}
	}

	public void nativeBatchPrepare(String sql) throws SQLException
	{
		this.stat = this.conn.prepareCall(sql);
	}
	public void nativeBatchSetParameter(Object ...params) throws SQLException
	{
		int parameterIndex = 1;
		for(Object p:params)
		{
			this.stat.setObject(parameterIndex, p);
			parameterIndex++;
		}
		this.stat.addBatch();
	}
	public void nativeBatchExecutePrepared() throws SQLException
	{
		int [] rt = this.stat.executeBatch();
		for(int r:rt)
		{
			if(r!=1) throw new SQLException("SQL batch execution falied");
		}
	}

	
	public void batchExecute(ArrayList<String> sqlBatch) throws SQLException 
	{
		
		for(String s:sqlBatch)
		{
			System.out.println(s+";");
		}
		Statement stat = this.conn.createStatement();
		for(String sql:sqlBatch)
		{
			stat.addBatch(sql);
		}
		//stat.executeBatch();
		stat.executeBatch();
		stat.close();
		/*boolean failed = false;
		for(int i=0;i<rt.length;i++)
		{
			if(rt[i]==Statement.EXECUTE_FAILED)
			{
				this.logger.error("SQL : {}", sqlBatch.get(i));
			}
		}
		if(failed)
		{
			throw new SQLException("SQL batch execution falied");
		}*/
		
	}

/*	@Override
	public void afterPropertiesSet() throws Exception 
	{
		this.conn = dataSource.getConnection();
		
	}*/

}
