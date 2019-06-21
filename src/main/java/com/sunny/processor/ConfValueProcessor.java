package com.sunny.processor;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;

import com.sunny.annotation.ConfPath;
import com.sunny.annotation.SystemConfPath;
import com.sunny.source.LoadResult;
import com.sunny.source.filter.ConfFilter;
import com.sunny.utils.PackageUtil;

/**
 * @author  zsunny data: 2018/8/11
 * @author junehappylove
 **/
public class ConfValueProcessor extends ConfProcessor {

	@Override
	public void process(String pack) {
		// 获取类
		Set<Class<?>> classSet = null;
		if (pack == null || pack.length() == 0) {
			classSet = PackageUtil.getAllClassSet();
		} else {
			String[] packs = pack.split(",");
			classSet = PackageUtil.getClasses(packs);
		}
		// 获取配置
		Object oo = LoadResult.getSource();
		// 执行操作
		classSet.forEach(clazz -> putInConf(oo, clazz));
	}

	/**
	 * 处理配置入属性
	 * 
	 * @param oo
	 * @param clazz
	 */
	private static void putInConf(Object oo, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Object o = oo;
			if (field.isAnnotationPresent(ConfPath.class)) {
				// static检查
				if ((field.getModifiers() & 8) == 0) {
					throw new RuntimeException("配置项必须为static变量");
				}
				ConfPath confPath = field.getAnnotation(ConfPath.class);
				String[] props = confPath.value().split("\\.");
				putInConfCore(o, props, field);
			} else if (field.isAnnotationPresent(SystemConfPath.class)) {
				// static检查
				if ((field.getModifiers() & 8) == 0) {
					throw new RuntimeException("配置项必须为static变量");
				}
				SystemConfPath systemConfPath = field.getAnnotation(SystemConfPath.class);
				String[] systemProps = systemConfPath.value().split("\\.");
				putInConfCore(ConfFilter.getSystemMap(), systemProps, field);
			}
		}
	}

	/**
	 * 配置入属性核心处理
	 * 
	 * @param o
	 *            配置数据
	 * @param props
	 *            配置属性key
	 * @param field
	 *            作用域
	 */
	private static void putInConfCore(Object o, String[] props, Field field) {
		int ind = 0;
		while (true) {
			if (ind < props.length && null != o && o instanceof Map) {
				o = ((Map<?, ?>) o).get(props[ind]);
			} else {
				break;
			}
			ind++;
		}
		try {
			field.setAccessible(true);
			// 适合且仅适合String字段 ，可以根据field类型进行转换[boolean int long double float
			// byte short char String]
			field.set(field, valueOf(String.valueOf(o), field.getType()));
		} catch (ExceptionInInitializerError e) {

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Object valueOf(String val, Class<?> type) {
		Object obj = null;
		if (val == null) {
			return null;
		}
		switch (type.getTypeName()) {
		case "java.lang.String":
			// 字符串
			obj = val;
			break;
		case "int":
			// 整数
			obj = Integer.parseInt(val);
			break;
		case "java.lang.Integer":
			// 整数
			obj = Integer.parseInt(val);
			break;
		case "long":
			// 整数
			obj = Long.parseLong(val);
			break;
		case "java.lang.Long":
			// 整数
			obj = Long.parseLong(val);
			break;
		case "double":
			// 小数
			obj = Double.parseDouble(val);
			break;
		case "java.lang.Double":
			// 小数
			obj = Double.parseDouble(val);
			break;
		case "boolean":
			// 布尔
			obj = Boolean.parseBoolean(val);
			break;
		case "java.lang.Boolean":
			// 布尔
			obj = Boolean.parseBoolean(val);
			break;
		case "java.math.BigDecimal":
			// 数字
			obj = BigDecimal.valueOf(Double.parseDouble(val));
			break;
		case "short":
			// 短整数
			obj = Short.parseShort(val);
			break;
		case "java.lang.Short":
			// 短整数
			obj = Short.parseShort(val);
			break;
		case "float":
			// 小数
			obj = Float.parseFloat(val);
			break;
		case "java.lang.Float":
			// 小数
			obj = Float.parseFloat(val);
			break;
		case "char":
			// 字符
			obj = Character.valueOf(val.charAt(0));
			break;
		case "java.lang.Character":
			// 字符
			obj = Character.valueOf(val.charAt(0));
			break;
		case "byte":
			// 字符
			obj = Byte.valueOf(val);
			break;
		case "java.lang.Byte":
			// 字符
			obj = Byte.valueOf(val);
			break;

		default:
			break;
		}
		return obj;
	}
}
