package se.unlogic.standardutils.templates;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import se.unlogic.standardutils.annotations.Templated;
import se.unlogic.standardutils.annotations.UnsupportedFieldTypeException;
import se.unlogic.standardutils.object.ObjectUtils;
import se.unlogic.standardutils.reflection.ReflectionUtils;
import se.unlogic.standardutils.string.StringUtils;

public class TemplateUtils {
	
	
	public static void setTemplatedFields(Object bean, Object template) {
		
		Class<?> beanClass = bean.getClass();
		Class<?> templateClass = template.getClass();
		
		List<Field> fields = ReflectionUtils.getFields(beanClass);
		
		for (Field field : fields) {
			
			Templated annotation = field.getAnnotation(Templated.class);
			
			if (annotation != null) {
				
				if (field.getType().isPrimitive()) {
					throw new UnsupportedFieldTypeException("The annotated field " + field.getName() + " in " + beanClass + " is a primitive!", field, annotation.getClass(), beanClass);
				}
				
				if (Modifier.isFinal(field.getModifiers())) {
					
					throw new UnsupportedFieldTypeException("The annotated field " + field.getName() + " in " + beanClass + " is final!", field, annotation.getClass(), beanClass);
				}
				
				if (annotation.templateClass() != Object.class) {
					
					if (!annotation.templateClass().isAssignableFrom(templateClass)) {
						continue;
					}
				}
				
				String templateFieldName = field.getName();
				
				if (!StringUtils.isEmpty(annotation.fieldName())) {
					templateFieldName = annotation.fieldName();
				}
				
				ReflectionUtils.fixFieldAccess(field);
				
				try {
					Object value = field.get(bean);
					
					if (value == null) {
						
						Field templateField = ReflectionUtils.getField(templateClass, templateFieldName);
						ReflectionUtils.fixFieldAccess(templateField);
						
						Object templateValue = templateField.get(template);
						
						field.set(bean, templateValue);
					}
					
				} catch (IllegalAccessException e) {
					
					throw new RuntimeException(e);
					
				} catch (SecurityException e) {
					
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	public static void clearUnchangedTemplatedFields(Object bean, Object template) {
		
		Class<?> beanClass = bean.getClass();
		Class<?> templateClass = template.getClass();
		
		List<Field> fields = ReflectionUtils.getFields(beanClass);
		
		for (Field field : fields) {
			
			Templated annotation = field.getAnnotation(Templated.class);
			
			if (annotation != null) {
				
				if (field.getType().isPrimitive()) {
					throw new UnsupportedFieldTypeException("The annotated field " + field.getName() + " in " + beanClass + " is a primitive!", field, annotation.getClass(), beanClass);
				}
				
				if (Modifier.isFinal(field.getModifiers())) {
					
					throw new UnsupportedFieldTypeException("The annotated field " + field.getName() + " in " + beanClass + " is final!", field, annotation.getClass(), beanClass);
				}
				
				if (annotation.templateClass() != Object.class) {
					
					if (!annotation.templateClass().isAssignableFrom(templateClass)) {
						continue;
					}
				}
				
				String templateFieldName = field.getName();
				
				if (!StringUtils.isEmpty(annotation.fieldName())) {
					templateFieldName = annotation.fieldName();
				}
				
				ReflectionUtils.fixFieldAccess(field);
				
				try {
					Object value = field.get(bean);
					
					if (value == null) {
						continue;
					}
					
					Field templateField = ReflectionUtils.getField(templateClass, templateFieldName);
					ReflectionUtils.fixFieldAccess(templateField);
					
					Object templateValue = templateField.get(template);
					
					if (value instanceof String && templateValue instanceof String) {
						
						value = ((String) value).trim();
						templateValue = ((String) templateValue).trim();
					}
					
					if (ObjectUtils.compare(value, templateValue)) {
						
						field.set(bean, null);
					}
					
				} catch (IllegalAccessException e) {
					
					throw new RuntimeException(e);
					
				} catch (SecurityException e) {
					
					throw new RuntimeException(e);
				}
			}
		}
	}
}
