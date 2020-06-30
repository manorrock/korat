package se.unlogic.standardutils.db.tableversionhandler;

import javax.sql.DataSource;

public final class TableVersionMutex {

	private final DataSource dataSource;
	private final String tableGroupName;

	public TableVersionMutex(DataSource dataSource, String tableGroupName) {

		super();
		this.dataSource = dataSource;
		this.tableGroupName = tableGroupName;
	}

	public DataSource getDataSource() {

		return dataSource;
	}

	public String getTableGroupName() {

		return tableGroupName;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataSource == null) ? 0 : dataSource.hashCode());
		result = prime * result + ((tableGroupName == null) ? 0 : tableGroupName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TableVersionMutex other = (TableVersionMutex) obj;
		if (dataSource == null) {
			if (other.dataSource != null) {
				return false;
			}
		} else if (!dataSource.equals(other.dataSource)) {
			return false;
		}
		if (tableGroupName == null) {
			if (other.tableGroupName != null) {
				return false;
			}
		} else if (!tableGroupName.equals(other.tableGroupName)) {
			return false;
		}
		return true;
	}

}
