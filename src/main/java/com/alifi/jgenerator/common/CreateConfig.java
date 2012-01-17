package com.alifi.jgenerator.common;

/**
 * 统一配置入口
 * 
 * @author Administrator
 * 
 */
public abstract class CreateConfig {

	public static class Table {
		/**
		 * String => 表类别（可为 null）
		 */
		public static String TABLE_CAT="TABLE_CAT";
		/**
		 * String => 表模式（可为 null）
		 */
		public static String TABLE_SCHEM="TABLE_SCHEM";
		/**
		 * String => 表名称
		 */
		public static String TABLE_NAME="TABLE_SCHEM";
		/**
		 * String => 表类型。典型的类型是
		 * "TABLE"、"VIEW"、"SYSTEM TABLE"、"GLOBAL TEMPORARY"、
		 * "LOCAL TEMPORARY"、"ALIAS" 和 "SYNONYM"。
		 */
		public static String TABLE_TYPE="TABLE_TYPE";
		/**
		 * String => 表的解释性注释
		 */
		public static String REMARKS="REMARKS";
		/**
		 * String => 类型的类别（可为 null）
		 */
		public static String TYPE_CAT="TYPE_CAT";
		/**
		 * String => 类型模式（可为 null）
		 */
		public static String TYPE_SCHEM;
		/**
		 * String => 类型名称（可为 null）
		 */
		public static String TYPE_NAME;
		/**
		 * String => 有类型表的指定 "identifier" 列的名称（可为 null）
		 */
		public static String SELF_REFERENCING_COL_NAME;
		/**
		 * String => 指定在 SELF_REFERENCING_COL_NAME 中创建值的方式。这些值为 "SYSTEM"、"USER"
		 * 和 "DERIVED"。（可能为 null）
		 */
		public static String REF_GENERATION;

	}

	/**
	 * 列配置入口
	 * 
	 * @author Administrator
	 * 
	 */
	public static class Column {

		/**
		 *列名
		 */
		public static String COLUMN_NAME = "COLUMN_NAME";

		/**
		 * 表类别（可为 null）
		 */
		public static String TABLE_CAT = "TABLE_CAT";
		/**
		 * 表模式（可为 null）
		 */
		public static String TABLE_SCHEM = "TABLE_SCHEM";
		/**
		 * 表名称
		 */
		public static String TABLE_NAME = "TABLE_NAME";
		/**
		 * 来自 java.sql.Types 的 SQL 类型
		 */
		public static String DATA_TYPE = "DATA_TYPE";
		/**
		 * 数据源依赖的类型名称，对于 UDT，该类型名称是完全限定的
		 */
		public static String TYPE_NAME = "TYPE_NAME";
		/**
		 * 列的大小。
		 */
		public static String COLUMN_SIZE = "COLUMN_SIZE";
		/**
		 * 未被使用。
		 */
		public static String BUFFER_LENGTH = "BUFFER_LENGTH";
		/**
		 * 小数部分的位数。对于DECIMAL_DIGITS不适用的数据类型，则返回 Null。
		 */
		public static String DECIMAL_DIGITS = "DECIMAL_DIGITS";
		/**
		 * 基数（通常为 10 或 2）
		 */
		public static String NUM_PREC_RADIX = "NUM_PREC_RADIX";
		/**
		 * 是否允许使用 NULL。 columnNoNulls - 可能不允许使用 NULL 值 columnNullable - 明确允许使用
		 * NULL 值 columnNullableUnknown - 不知道是否可使用 null
		 */
		public static String NULLABLE = "NULLABLE";

		public static String REMARKS = "REMARKS";
		/**
		 * 该列的默认值， 当值在单引号内时应被解释为一个字符串（可为 null）
		 */
		public static String COLUMN_DEF = "COLUMN_DEF";
		/**
		 * 未使用
		 */
		public static String SQL_DATA_TYPE = "SQL_DATA_TYPE";
		/**
		 * 未使用
		 */
		public static String SQL_DATETIME_SUB = "SQL_DATETIME_SUB";
		/**
		 * 对于 char 类型，该长度是列中的最大字节数
		 */
		public static String CHAR_OCTET_LENGTH;
		/**
		 * 表中的列的索引（从 1 开始）
		 */
		public static String ORDINAL_POSITION;
		/**
		 * ISO 规则用于确定列是否包括 null。 YES --- 如果参数可以包括 NULL NO --- 如果参数不可以包括 NULL
		 * 空字符串 --- 如果不知道参数是否可以包括 null
		 */
		public static String IS_NULLABLE="IS_NULLABLE";
		/**
		 * 表的类别，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
		 */
		public static String SCOPE_CATLOG = "SCOPE_CATLOG";
		/**
		 * 表的模式，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
		 */
		public static String SCOPE_SCHEMA = "SCOPE_SCHEMA";
		/**
		 * 表名称，它是引用属性的作用域（如果 DATA_TYPE 不是 REF，则为 null）
		 */
		public static String SCOPE_TABLE = "SCOPE_TABLE";
		/**
		 * 不同类型或用户生成 Ref 类型、来自 java .sql.Types 的 SQL 类型的源类型（如果 DATA_TYPE 不是
		 * DISTINCT 或用户生成的 REF，则为 null）
		 */
		public static String SOURCE_DATA_TYPE = "SOURCE_DATA_TYPE";
		/**
		 * 指示此列是否自动增加 YES --- 如果该列自动增加 NO --- 如果该列不自动增加 空字符串 ---
		 * 如果不能确定该列是否是自动增加参数
		 */
		public static String IS_AUTOINCREMENT = "IS_AUTOINCREMENT";

	}
	
	/**
	 * 主键
	 * @author Administrator
	 *
	 */
	public static class PrimaryKey{
		/**
		 * 列名称
		 */
		public static String COLUMN_NAME="COLUMN_NAME";  
		/**
		 * short 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）
		 */
		public static String KEY_SEQ="KEY_SEQ"; 
		/**
		 * 主键的名称（可为 null） 
		 */
		public static String PK_NAME="PK_NAME";

	}
}
