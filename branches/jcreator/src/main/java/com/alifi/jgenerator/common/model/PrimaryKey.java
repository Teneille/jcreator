package com.alifi.jgenerator.common.model;

public class PrimaryKey extends Column {
	private short seq;// short => 主键中的序列号（值 1 表示主键中的第一列，值 2 表示主键中的第二列）。
	private String pkName;// String => 主键的名称（可为 null）

	public short getSeq() {
		return seq;
	}

	public void setSeq(short seq) {
		this.seq = seq;
	}

	public String getPkName() {
		return pkName;
	}

	public void setPkName(String pkName) {
		this.pkName = pkName;
	}

}
