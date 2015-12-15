package com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.hash;

import java.beans.Transient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanMap;

import com.yyang.project.ecommerce.products.review.persistence.nosql.redis.model.BaseModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor
public abstract class HASHBaseModel implements BaseModel {
	
	private String id;
	
	public HASHBaseModel(String id) {
		this.id = id.toString();
	}

	private static final String CLASS_METHOD_NAME = "class";
	
	
	public Optional<Map<String, String>> toMap() {
			BeanMap beanMap = new BeanMap(this);
			
			Map<String, String> resultMap = new HashMap<String, String>();
			beanMap.forEach((k, v) -> {
				if(v != null) {
					Method readMethod = beanMap.getReadMethod(k.toString());
					if(shouldtoRedis(readMethod, k.toString())) {
						resultMap.put(k.toString(), v.toString());
					}
				}
			});
			return Optional.of(resultMap);
	}
	
	private boolean shouldtoRedis(Method readMethod, String readMethodName) {
		if (CLASS_METHOD_NAME.equals(readMethodName))
			return false;
		Annotation[] declaredAnnotations = readMethod.getDeclaredAnnotations();
		if (declaredAnnotations != null) {
			for(int i = 0; i < declaredAnnotations.length; i++) {
				Annotation annotation = declaredAnnotations[i];
				if(annotation.annotationType().equals(Transient.class))
					return false;
				
			}
		}
		return true;
		
	}
}
