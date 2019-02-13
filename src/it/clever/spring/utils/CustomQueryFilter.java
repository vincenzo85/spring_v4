/**
 * CustomQueryFilter.java
 *
 * robgion
 * www.2clever.it
 * 
 * 30 ott 2017
 * For further information please write to info@2clever.it
 */
package it.clever.spring.utils;

/**
 * @author robgion
 *
 */
public class CustomQueryFilter {

	private String fieldName;
	private Object fieldValue;
	
	private String joinFieldName;
	
	
	
	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(Object fieldValue) {
		this.fieldValue = fieldValue;
	}

}
