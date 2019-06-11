package com.next.jpatis.core;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;

public class JpaUtils {
	static String getFieldName(Field field)
	{
		Column c = field.getAnnotation(Column.class);
		if(c!=null)
		{
			String name = c.name();
			if(StringUtils.isEmpty(name)==false) {
				return name;
			}			
		}
		return field.getName();
	}
	static public List<Field> getFields(Class<?> cls) {
		List<Field> rt = new ArrayList<>();
		Field[] fields = FieldUtils.getAllFields(cls);
		for (Field field : fields) {
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			Column aColumn = field.getAnnotation(Column.class);
			if (aColumn == null) {
				Transient aTrans = field.getAnnotation(Transient.class);
				if (aTrans == null) {
					throw new RuntimeException(String.format("%s require @Column or @Transient", field.toString()));
				}
				continue;
			}
			field.setAccessible(true);
			rt.add(field);
		}
		return rt;
	}	
}
