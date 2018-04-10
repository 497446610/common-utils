package cn.kuangxf.common.utils.converter;

import java.math.BigDecimal;

import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.converters.NumberConverter;

@SuppressWarnings({ "rawtypes" })
public class BigDecimalConverter extends NumberConverter {
	public BigDecimalConverter() {
		super(true);
	}

	public Object convert(Class type, Object value) {
		Class sourceType = (value != null) ? value.getClass() : null;
		Class targetType = _primitive((type != null) ? type : getDefaultType());

		if (value == null) {
			return null;
		}
		if (targetType.equals(String.class)) {
			return value.toString();
		}
		if (sourceType.equals(BigDecimal.class)) {
			BigDecimal decimal = (BigDecimal) value;
			if (targetType.equals(Double.class)) {
				return Double.valueOf(decimal.doubleValue());
			}
			if (targetType.equals(Integer.class)) {
				return Integer.valueOf(decimal.intValue());
			}
			if (targetType.equals(Long.class)) {
				return Long.valueOf(decimal.longValue());
			}
			if (targetType.equals(Short.class))
				return Short.valueOf(decimal.shortValue());
		} else {
			throw new ConversionException("value is not java.math.BigDecimal");
		}
		return value;
	}

	protected Class getDefaultType() {
		return BigDecimal.class;
	}

	Class _primitive(final Class type) {
		if ((type == null) || (!(type.isPrimitive())))
			return type;
		if (type == Integer.TYPE)
			return Integer.class;
		if (type == Double.TYPE)
			return Double.class;
		if (type == Long.TYPE)
			return Long.class;
		if (type == Boolean.TYPE)
			return Boolean.class;
		if (type == Float.TYPE)
			return Float.class;
		if (type == Short.TYPE)
			return Short.class;
		if (type == Byte.TYPE)
			return Byte.class;
		if (type == Character.TYPE) {
			return Character.class;
		}
		return type;
	}
}