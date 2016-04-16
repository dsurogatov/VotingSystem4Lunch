/**
 * 
 */
package org.dsu.common;

/**
 * @author nescafe
 * Define exception's type 
 */
public enum ExceptionType {
	INTERNAL_ERROR,
	CONVERTER_NOT_FINDED,
	ENTITY_NOT_FINDED,
	DECODE_URI_FAILED,
	CONVERT_FROM_JSON_FAILED,
	PARSE_DATE_FAILED, 
	WRONG_DATA_DB,
	MAX_DISH_IN_RESTAURANT,
	VALIDATE_ERROR, 
	DENY_REMOVE_ENTITY_WITH_RELATIVES
}
