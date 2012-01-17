package com.alifi.jgenerator.common.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.beans.BeanUtils;

public class Table {
	private int columnCount;

	private String cat;
	private String name;
	private String className;
	private String schem;
	private String type;
	private String remarks;
	private String ownerSynonymName;
	private String tableSynonymName;
	
	private List<Column> columns = new ArrayList<Column>();
	private List<PrimaryKey> primaryKeys = new ArrayList<PrimaryKey>();
	private List<IndexInfo> indexInfos = new ArrayList<IndexInfo>();

	public void addPrimaryKey(PrimaryKey primaryKey) {
		Column c = this.columns.get(this.columns.indexOf(primaryKey));
		BeanUtils.copyProperties(c, primaryKey);
		this.primaryKeys.add(primaryKey);
	}

	public boolean isParimaryKey(String column) {
		return this.primaryKeys.contains(new Column(column));
	}
	

	public String getOwnerSynonymName() {
		return ownerSynonymName;
	}

	public void setOwnerSynonymName(String ownerSynonymName) {
		this.ownerSynonymName = ownerSynonymName;
	}

	public String getTableSynonymName() {
		return tableSynonymName;
	}

	public void setTableSynonymName(String tableSynonymName) {
		this.tableSynonymName = tableSynonymName;
	}

	public List<PrimaryKey> getPrimaryKeys() {
		return primaryKeys;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void addColumn(Column c) {
		columns.add(c);
	}

	public int getColumnCount() {
		return columnCount;
	}

	public void setColumnCount(int columnCount) {
		this.columnCount = columnCount;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchem() {
		return schem;
	}

	public void setSchem(String schem) {
		this.schem = schem;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}
}
