package com.alifi.jgenerator.common.base;

import java.sql.Types;

public class ColumnTypes {

	public static String getType(int type) {
		String tp = "";
		switch (type) {
		case Types.ARRAY:
			tp = "ARRAY";
			break;
		case Types.BIGINT:
			tp = "BIGINT";
			break;
		case Types.BINARY:
			tp = "BINARY";
			break;
		case Types.BIT:
			tp = "BIT";
			break;
		case Types.BLOB:
			tp = "BLOB";
			break;
		case Types.BOOLEAN:
			tp = "BOOLEAN";
			break;
		case Types.CHAR:
			tp = "CHAR";
			break;
		case Types.CLOB:
			tp = "CLOB";
			break;
		case Types.DATALINK:
			tp = "DATALINK";
			break;
		case Types.DATE:
			tp = "DATE";
			break;
		case Types.DECIMAL:
			tp = "DECIMAL";
			break;
		case Types.DISTINCT:
			tp = "DISTINCT";
			break;
		case Types.DOUBLE:
			tp = "DOUBLE";
			break;
		case Types.FLOAT:
			tp = "FLOAT";
			break;
		case Types.INTEGER:
			tp = "INTEGER";
			break;
		case Types.JAVA_OBJECT:
			tp = "JAVA_OBJECT";
			break;
		case Types.LONGNVARCHAR:
			tp = "LONGNVARCHAR";
			break;
		case Types.LONGVARBINARY:
			tp = "LONGVARBINARY";
			break;
		case Types.LONGVARCHAR:
			tp = "LONGVARCHAR";
			break;
		case Types.NCHAR:
			tp = "NCHAR";
			break;
		case Types.NCLOB:
			tp = "NCLOB";
			break;
		case Types.NULL:
			tp = "NULL";
			break;
		case Types.NUMERIC:
			tp = "NUMERIC";
			break;
		case Types.NVARCHAR:
			tp = "NVARCHAR";
			break;
		case Types.OTHER:
			tp = "OTHER";
			break;
		case Types.REAL:
			tp = "REAL";
			break;
		case Types.REF:
			tp = "REF";
			break;
		case Types.ROWID:
			tp = "ROWID";
			break;
		case Types.SMALLINT:
			tp = "SMALLINT";
			break;
		case Types.SQLXML:
			tp = "SQLXML";
			break;
		case Types.STRUCT:
			tp = "STRUCT";
			break;
		case Types.TIME:
			tp = "TIME";
			break;
		case Types.TIMESTAMP:
			tp = "TIMESTAMP";
			break;
		case Types.TINYINT:
			tp = "TINYINT";
			break;
		case Types.VARBINARY:
			tp = "VARBINARY";
			break;
		case Types.VARCHAR:
			tp = "VARCHAR";
			break;
		}
		return tp;
	}

}
