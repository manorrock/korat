package se.unlogic.standardutils.dao;

import java.util.List;

import javax.sql.DataSource;

import se.unlogic.standardutils.populators.BeanStringPopulator;
import se.unlogic.standardutils.populators.QueryParameterPopulator;
import se.unlogic.standardutils.populators.annotated.AnnotatedResultSetPopulator;

public interface AnnotatedDAOInstantiator {
	
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory);
	
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory, AnnotatedResultSetPopulator<T> populator, QueryParameterPopulator<?>... queryParameterPopulators);
	
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory, List<? extends QueryParameterPopulator<?>> queryParameterPopulators, List<? extends BeanStringPopulator<?>> typePopulators);
	
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory, AnnotatedResultSetPopulator<T> populator, List<? extends QueryParameterPopulator<?>> queryParameterPopulators, List<? extends BeanStringPopulator<?>> typePopulators);
}
