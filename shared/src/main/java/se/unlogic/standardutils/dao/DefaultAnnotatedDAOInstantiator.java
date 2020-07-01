package se.unlogic.standardutils.dao;

import java.util.List;

import javax.sql.DataSource;

import se.unlogic.standardutils.populators.BeanStringPopulator;
import se.unlogic.standardutils.populators.QueryParameterPopulator;
import se.unlogic.standardutils.populators.annotated.AnnotatedResultSetPopulator;

public class DefaultAnnotatedDAOInstantiator implements AnnotatedDAOInstantiator {
	
	@Override
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory) {
		return new AnnotatedDAO<T>(dataSource, beanClass, daoFactory);
	}
	
	@Override
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory, AnnotatedResultSetPopulator<T> populator, QueryParameterPopulator<?>... queryParameterPopulators) {
		return new AnnotatedDAO<T>(dataSource, beanClass, daoFactory, populator, queryParameterPopulators);
	}
	
	@Override
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory, List<? extends QueryParameterPopulator<?>> queryParameterPopulators, List<? extends BeanStringPopulator<?>> typePopulators) {
		return new AnnotatedDAO<T>(dataSource, beanClass, daoFactory, queryParameterPopulators, typePopulators);
	}
	
	@Override
	public <T> AnnotatedDAO<T> instantiate(DataSource dataSource, Class<T> beanClass, AnnotatedDAOFactory daoFactory, AnnotatedResultSetPopulator<T> populator, List<? extends QueryParameterPopulator<?>> queryParameterPopulators, List<? extends BeanStringPopulator<?>> typePopulators) {
		return new AnnotatedDAO<T>(dataSource, beanClass, daoFactory, populator, queryParameterPopulators, typePopulators);
	}
	
}
