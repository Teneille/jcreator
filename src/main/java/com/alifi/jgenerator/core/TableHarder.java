package com.alifi.jgenerator.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alifi.jgenerator.common.CreateConfig;
import com.alifi.jgenerator.common.base.DataSourceMap;
import com.alifi.jgenerator.common.model.Column;
import com.alifi.jgenerator.common.model.PrimaryKey;
import com.alifi.jgenerator.common.model.Table;
import com.alifi.jgenerator.utils.DataSourceUtil;
import com.alifi.jgenerator.utils.StringFormatUtils;

public class TableHarder {
	private String pix = "";
	private Connection con;
	private DbHelper dbHelper = new DbHelper();

	public List<Table> getAllTablesByKey(String key) {
		return getAllTables(DataSourceUtil.getByKey(key));
	}

	public List<Table> getAllTablesByDesc(String desc) {
		return getAllTables(DataSourceUtil.getByDesc(desc));
	}

	public List<Table> getAllTablesByIndex(int index) {
		return getAllTables(DataSourceUtil.getByIndex(index));
	}

	public List<Table> getAllTablesByDName(String desc, String... table) {
		DataSourceUtil.getByDesc(desc);
		return null;
	}

	public List<Table> getTablesByKeyAndTNames(String key, String... table) {
		DataSourceUtil.getByKey(key);
		return null;
	}

	public List<Table> getTablesByIndexAndIName(int index, String... table) {
		DataSourceUtil.getByIndex(index);
		return null;
	}

	private List<Table> getAllTables(DataSourceMap dsm) {
		List<Table> result = new ArrayList<Table>();
		DataSource ds = dsm.getDataSource();
		try {
			con = ds.getConnection();
			DatabaseMetaData dmd = con.getMetaData();
			String catalog = con.getCatalog();
			String schema = dmd.getSchemaTerm();
			ResultSet rs = dmd.getTables(catalog, schema, null, null);
			while (rs.next()) {
				Table table = createTable(rs, catalog, schema);
				fillColumn(dmd, catalog, schema, table);
				fillPrimaryKey(dmd, catalog, schema, table);
				fillIndexInfo(dmd, catalog, schema, table);
				result.add(table);
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private void fillIndexInfo(DatabaseMetaData dmd, String catalog,
			String schema, Table table) throws SQLException {
		ResultSet rs;
		if (table.getOwnerSynonymName() != null) {
			rs = dmd.getIndexInfo(catalog, table.getOwnerSynonymName(), table
					.getTableSynonymName(), true, false);
		} else {
			rs = dmd
					.getIndexInfo(catalog, schema, table.getName(), true, false);
		}
		while(rs.next()){
			
		}
	}

	/**
	 * 添加主键
	 * 
	 * @param dmd
	 * @param schema
	 * @param table
	 * @throws SQLException
	 */
	private void fillPrimaryKey(DatabaseMetaData dmd, String catalog,
			String schema, Table table) throws SQLException {
		ResultSet rs;
		if (table.getOwnerSynonymName() != null) {
			rs = dmd.getPrimaryKeys(catalog, table.getOwnerSynonymName(), table
					.getTableSynonymName());
		} else {
			rs = dmd.getPrimaryKeys(catalog, schema, table.getName());
		}
		while (rs.next()) {
			PrimaryKey pk = new PrimaryKey();
			pk.setName(rs.getString(CreateConfig.PrimaryKey.COLUMN_NAME));
			pk.setSeq(rs.getShort(CreateConfig.PrimaryKey.KEY_SEQ));
			pk.setPkName(rs.getString(CreateConfig.PrimaryKey.PK_NAME));
			table.addPrimaryKey(pk);
		}
		rs.close();
	}

	/**
	 * 添加字段
	 * 
	 * @param dmd
	 * @param table
	 * @throws SQLException
	 */
	private void fillColumn(DatabaseMetaData dmd, String catalog,
			String schema, Table table) throws SQLException {
		ResultSet rs;
		if (table.getOwnerSynonymName() != null) {
			rs = dmd.getColumns(catalog, table.getOwnerSynonymName(), table
					.getTableSynonymName(), null);
		} else {
			rs = dmd.getColumns(catalog, schema, table.getName(), null);
		}
		while (rs.next()) {
			Column cln = new Column();
			String columnName = rs.getString(CreateConfig.Column.COLUMN_NAME);
			cln.setName(columnName);
			cln.setDbType(rs.getString(CreateConfig.Column.TYPE_NAME));
			cln.setColumnSize(rs.getString(CreateConfig.Column.COLUMN_SIZE));
			cln.setDecimalDigits(rs
					.getString(CreateConfig.Column.DECIMAL_DIGITS));
			String remarks = rs.getString(CreateConfig.Column.REMARKS);
			if (remarks == null && isOracle(table)) {
				remarks = getOracleColumnComments(table.getName(), columnName);
			}
			cln.setRemarks(remarks);
			cln.setColumnDef(rs.getString(CreateConfig.Column.COLUMN_DEF));
			cln.setIsNullable(rs.getString(CreateConfig.Column.IS_NULLABLE));
			table.addColumn(cln);
		}
		rs.close();
	}

	private String getOracleColumnComments(String table, String columnName) {
		String sql = "SELECT comments FROM user_col_comments WHERE table_name='"
				+ table + "' AND column_name = '" + columnName + "'";
		return dbHelper.queryForString(sql);
	}

	private String getOracleTableComments(String table) {
		String sql = "SELECT comments FROM user_tab_comments WHERE table_name='"
				+ table + "'";
		return dbHelper.queryForString(sql);
	}

	/**
	 * 创建Table
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Table createTable(ResultSet rs, String catalog, String schema)
			throws SQLException {
		Table table = new Table();
		table.setCat(rs.getString(CreateConfig.Table.TABLE_CAT));
		table.setName(rs.getString(CreateConfig.Column.TABLE_NAME));
		table.setClassName(StringFormatUtils.humpCPix(pix, table.getName()));
		String remarks = rs.getString(CreateConfig.Table.REMARKS);
		if (remarks == null && isOracle(table)) {
			remarks = getOracleTableComments(table.getName());
		}
		table.setRemarks(remarks);
		table.setType(rs.getString(CreateConfig.Table.TABLE_TYPE));
		table.setSchem(rs.getString(CreateConfig.Table.TABLE_SCHEM));
		String tableType = rs.getString("TABLE_TYPE");
		if ("SYNONYM".equals(tableType) && isOracle(table)) {
			String[] ownerAndTableName = getSynonymOwnerAndTableName(table
					.getName(), catalog, schema);
			table.setOwnerSynonymName(ownerAndTableName[0]);
			table.setTableSynonymName(ownerAndTableName[1]);
		}
		return table;
	}

	private String[] getSynonymOwnerAndTableName(String synonymName,
			String catalog, String schema) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String[] ret = new String[2];
		try {
			ps = con
					.prepareStatement("select table_owner,table_name from sys.all_synonyms where synonym_name=? and owner=?");
			ps.setString(1, synonymName);
			ps.setString(2, schema);
			rs = ps.executeQuery();
			if (rs.next()) {
				ret[0] = rs.getString(1);
				ret[1] = rs.getString(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	class DbHelper {
		public String queryForString(String sql) {
			Statement s = null;
			ResultSet rs = null;
			try {
				s = con.createStatement();
				rs = s.executeQuery(sql);
				if (rs.next()) {
					return rs.getString(1);
				}
				rs.close();
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	private boolean isOracle(Table table) {
		return table.getCat().toLowerCase().indexOf("oracle") != -1;
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"META-INF/SpringContext.xml");
		TableHarder th = new TableHarder();
		th.pix = "jforum_";
		List<Table> tList = th.getAllTablesByIndex(0);
		for (Table t : tList) {
			System.out.println(t);
		}
	}
	@Override
	protected void finalize() throws Throwable {
		try{
		this.con.close();
		}catch(Exception e){
			System.out.println(this.getClass()+" 垃圾回收");
		}
		super.finalize();
	}
}
