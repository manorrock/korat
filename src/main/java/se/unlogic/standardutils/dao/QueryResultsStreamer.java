package se.unlogic.standardutils.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import se.unlogic.standardutils.collections.CollectionUtils;
import se.unlogic.standardutils.enums.Order;

public class QueryResultsStreamer<T,I> {

	protected final AnnotatedDAO<T> annotatedDAO;
	protected final Class<I> indexColumnType;
	protected final Order order;
	protected final HighLevelQuery<T> baseQuery;
	protected Connection connection;
	
	protected final QueryParameterFactory<T, I> parameterFactory;
	protected final Column<T, I> indexColumn;
	
	protected I lastMaxValue;
	
	@SuppressWarnings("unchecked")
	public QueryResultsStreamer(AnnotatedDAO<T> annotatedDAO, Field indexColumnField, Class<I> indexColumnType, Order order, HighLevelQuery<T> baseQuery) {

		super();
		this.annotatedDAO = annotatedDAO;
		this.indexColumnType = indexColumnType;
		this.order = order;
		this.baseQuery = baseQuery;
		
		parameterFactory = annotatedDAO.getParamFactory(indexColumnField, indexColumnType);
		
		baseQuery.addOrderByCriteria(annotatedDAO.getOrderByCriteria(indexColumnField, order));
		
		indexColumn = (Column<T, I>) annotatedDAO.getColumn(indexColumnField);
	}
	
	public QueryResultsStreamer(AnnotatedDAO<T> annotatedDAO, Field indexColumnField, Class<I> indexColumnType, Order order, HighLevelQuery<T> baseQuery, Connection connection) {

		this(annotatedDAO, indexColumnField, indexColumnType, order, baseQuery);
		
		this.connection = connection;
	}

	@SuppressWarnings("unchecked")
	public List<T> getBeans() throws SQLException{
		
		QueryParameter<T, I> queryParameter = null;
		
		try {
			
			if (lastMaxValue != null) {
				
				if (order == Order.ASC) {
					
					queryParameter = parameterFactory.getParameter(lastMaxValue, QueryOperators.BIGGER_THAN);
					
				} else {
					
					queryParameter = parameterFactory.getParameter(lastMaxValue, QueryOperators.SMALLER_THAN);
				}
				
				baseQuery.addParameter(queryParameter);
			}
			
			List<T> beans;
			
			if (connection == null) {
				
				beans = annotatedDAO.getAll(baseQuery);
				
			} else {
				
				beans = annotatedDAO.getAll(baseQuery, connection);
			}
			
			if (beans != null) {
				
				T lastBean = CollectionUtils.getLastValue(beans);
				
				lastMaxValue = (I) indexColumn.getBeanValue(lastBean);
			}
			
			return beans;
			
		} finally {
			
			if (queryParameter != null) {
				
				CollectionUtils.removeLastValue(baseQuery.getParameters());
			}
		}
	}
}
