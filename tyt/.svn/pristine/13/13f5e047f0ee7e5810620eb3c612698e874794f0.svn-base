package com.bquan.util;

import java.lang.reflect.Constructor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bquan.bean.ShadowsocksAccountPlugin;
import com.bquan.bean.ShadowsocksUser;
import com.isa.pims.basic.BeanUtils;

import net.sf.json.JSONObject;

/**
 * JDBC 工具类 来自 basic 包
 * 
 * @author HaHa
 */
public class JDBCHelper {

	/**
	 * 执行sql，根据查询结果，动态反射组装成list返回 注意：使用的是Constructor，要求构造器中顺序和查询顺序必须相同 没有做类型转换，所以clazz的字段为 String
	 * 
	 * @param clazz
	 *            实体Bean
	 * @param paramLength
	 *            指定需要使用的构造器长度
	 * @param conditionSql
	 *            查询sql语句
	 * @param values
	 *            查询条件的值集合
	 * @return
	 */
	public List queryBySql(final Class clazz, final int paramLength, final String conditionSql, final Object[] values) {

		Connection conn = conUtil.getConn();
		List list = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData meta = null;
		int columnLength = 0;

		Object[] columnValues = null; // 列類型
		try {
			pstmt = conn.prepareStatement(conditionSql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();

			if (rs.next()) {
				meta = rs.getMetaData();
				columnLength = meta.getColumnCount();
				if (columnLength > 0) {
					columnValues = new Object[columnLength];
				}
				rs.beforeFirst();
			}
			// ResultSet 沒有 列
			if (columnLength < 1 || columnValues == null)
				return list;

			while (rs.next()) {
				for (int i = 0; i < columnLength; i++) {
					columnValues[i] = rs.getString(i + 1);
				}
				Constructor constructor = BeanUtils.getConstructor(clazz, paramLength);
				Object temp = constructor.newInstance(columnValues);
				list.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				meta = null;
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				// // 每次查询完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
		return list;
	}

	/**
	 * 简单查询，返回唯一结果
	 * 
	 * @param conditionSql
	 *            SQL语句
	 * @param values
	 * @return
	 */
	public Object queryBySql(final String conditionSql, final Object[] values) {
		Connection conn = conUtil.getConn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Object result = null;
		try {
			pstmt = conn.prepareStatement(conditionSql);

			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getObject(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				// // 每次查询完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	/**
	 * 简单查询，返回List
	 * 
	 * @param conditionSql
	 *            SQL语句
	 * @param values
	 * @return
	 */
	public List<String> queryBySqlReturnList(final String conditionSql, final Object[] values) {
		Connection conn = conUtil.getConn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List<String> result = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(conditionSql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				String o = rs.getString(1);
				System.out.println(rs.getFetchSize());
				result.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				// // 每次查询完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	/**
	 * 简单查询，返回数组
	 * 
	 * @param conditionSql
	 *            SQL语句
	 * @param values
	 * @return
	 */
	public Object[] queryBySqlReturnArray(final String conditionSql, final Object[] values) {
		Connection conn = conUtil.getConn();
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		List result = new ArrayList();
		try {
			pstmt = conn.prepareStatement(conditionSql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			rs = pstmt.executeQuery();
			int i = 0;
			while (rs.next()) {
				String o = rs.getString(1);
				result.add(o);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				// // 每次查询完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
		return result.toArray();
	}

	/**
	 * 执行insert或update语句 返回 true 代表执行成功 返回 false 代表执行失败
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public boolean execute(final String sql, final Object[] values) {
		boolean result = false;
		Connection conn = conUtil.getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);

			if (values != null) {
				for (int i = 0, n = values.length; i < n; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			pstmt.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				conn.commit();
				if (pstmt != null)
					pstmt.close();
				// // 每次执行完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
		return result;
	}

	public int excuteWithGenratedKey(final String sql, final Object[] values) {
		Connection conn = conUtil.getConn();
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				conn.commit();
				if (pstmt != null)
					pstmt.close();
			} catch (SQLException e) {
			}
		}
		return result;
	}



	/**
	 * 执行insert或update语句 返回 true 代表执行成功 返回 false 代表执行失败
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public void executeFile(final String sql, final Object[] values) {
		Connection conn = conUtil.getConn();
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0, n = values.length; i < n; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				conn.commit();
				if (pstmt != null)
					pstmt.close();
				// // 每次执行完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
	}

	public void executePorcedure(String sql, Object[] values) {
		CallableStatement call = null;
		try {
			// 调用存储过程更新 topic_match_fast
			Connection conn = conUtil.getConn();
			call = conn.prepareCall(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					call.setObject(i + 1, values[i]);
				}
			}
			call.execute();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			if (call != null) {
				try {
					call.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 执行insert语句，返回自增长主键
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public int executeWithGeneratedKey(final String sql, final Object[] values) {
		int result = -1;
		Connection conn = conUtil.getConn();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);

			if (values != null) {
				for (int i = 0, n = values.length; i < n; i++) {
					pstmt.setObject(i + 1, values[i]);
				}
			}
			pstmt.executeUpdate();
			rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				if (pstmt != null)
					pstmt.close();
				// // 每次执行完之后关闭连接
				// ConnectionHelper.closeConnection();
			} catch (SQLException e) {
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		List<ShadowsocksAccountPlugin> list = 
				new JDBCHelper().queryBySql(
						ShadowsocksAccountPlugin.class, 9,
						" select * from account_plugin ", 
						new Object[]{});
		for(ShadowsocksAccountPlugin s:list){
			System.out.println(JsonUtil.toJson(s));
			
			System.out.println(s.getData());
			JSONObject j = JSONObject.fromObject(s.getData());
			System.out.println(j.get("create"));
			Date d = new Date(Long.parseLong(j.getString("create")));
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println(sf.format(d));
			
		}
		//System.out.println(JsonUtil.toJson());
		
		String sql = "insert into account_plugin (type,userId,server,port,password,data,status,autoRemove) values (4,null,null,50018,123456,'{\"create\":"+new Date().getTime()+",\"flow\":99999999999,\"limit\":1}',0,1)";
		new JDBCHelper().execute(sql, new Object[]{});
	}
	
}
