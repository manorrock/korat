package se.unlogic.standardutils.db.tableversionhandler;

import java.sql.SQLException;
import java.util.List;

import se.unlogic.standardutils.dao.TransactionHandler;
import se.unlogic.standardutils.settings.XMLSettingNode;


public class XMLDBScript implements DBScript {

	private XMLSettingNode dbScriptNode;
	
	public XMLDBScript(XMLSettingNode dbScriptNode) {

		this.dbScriptNode = dbScriptNode;
	}

	public void execute(TransactionHandler transactionHandler) throws SQLException {

		List<String> queries = dbScriptNode.getStrings("Query");
		
		if(queries == null){
			return;
		}
		
		for(String sqlQuery : queries){
			
			transactionHandler.getUpdateQuery(sqlQuery).executeUpdate();
		}
	}
}
