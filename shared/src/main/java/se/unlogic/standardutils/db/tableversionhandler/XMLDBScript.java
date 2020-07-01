package se.unlogic.standardutils.db.tableversionhandler;

import java.sql.SQLException;
import java.util.List;

import se.unlogic.standardutils.dao.TransactionHandler;
import se.unlogic.standardutils.string.StringUtils;
import se.unlogic.standardutils.xml.XMLParser;

public class XMLDBScript implements DBScript {

	private XMLParser dbScriptNode;

	public XMLDBScript(XMLParser dbScriptNode) {

		this.dbScriptNode = dbScriptNode;
	}

	@Override
	public void execute(TransactionHandler transactionHandler) throws SQLException {

		List<XMLParser> xmlQueries = dbScriptNode.getNodes("Query", true);

		if (xmlQueries.isEmpty()) {
			return;
		}

		for (XMLParser query : xmlQueries) {

			String precondition = query.getString("@precondition");
			
			if(!StringUtils.isEmpty(precondition) && !transactionHandler.getBooleanQuery(precondition).executeQuery()) {
				
				continue;
			}
			
			String invertedPrecondition = query.getString("@invertedPrecondition");
			
			if(!StringUtils.isEmpty(invertedPrecondition) && transactionHandler.getBooleanQuery(invertedPrecondition).executeQuery()) {
				
				continue;
			}
			
			transactionHandler.getUpdateQuery(query.getString(".")).executeUpdate();

			if (query.getPrimitiveBoolean("@forceCommit")) {

				transactionHandler.intermediateCommit();
			}
		}
	}
}