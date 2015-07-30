package searchengine.helper;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import searchengine.model.NewsModel;;

public class BeanHelper {
	
	
	public static void main(String[] args) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		NewsModel model = new NewsModel();
//		model.setContent("content");
//		model.setData("time");
//		model.setTitle("title");
//		model.setUrl("url");
//		model.setShortContent("shortContent");
		BeanInfo info = Introspector.getBeanInfo(NewsModel.class);
//		BeanDescriptor beanDescriptor = info.getBeanDescriptor();//model的名字...吧
//		MethodDescriptor[] methodDescriptors = info.getMethodDescriptors();//获取所有model中申明的方法  父类也算
        Field[] fields = model.getClass().getDeclaredFields();
        for (Field field : fields) {
        	System.out.println(field.getName());
		}
		PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			Method readMethod = propertyDescriptor.getReadMethod();
			Object invoke = readMethod.invoke(model);
			System.out.println(invoke);
		}
	}

}
