package com.next.jpatis.core;

import java.util.Map;

public interface DynamicFieldsEntity 
{
	public void setUserField(String field, Object value);
	public Object getUserField(String field) throws NoSuchMethodException;
	Map<String, Object> getUserFields();
}
