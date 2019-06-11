package com.next.jpatis.spring;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;



final public class SpringScanner
{
	@SafeVarargs
	static public List<String> scanAll(String scanPackage, Class<? extends Annotation> ... classes) throws ClassNotFoundException
	{
		SpringScanningProviderIncludeInterface provider = new SpringScanningProviderIncludeInterface();
	    for(Class<? extends Annotation> clazz:classes)
	    {
	    	provider.addIncludeFilter(new AnnotationTypeFilter((Class<? extends Annotation>) clazz));
	    }
	    List<String> rt  =new ArrayList<String>();
	    for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) 
	    {
	    	String beanClassName = beanDef.getBeanClassName();
	    	rt.add(beanClassName);
	    }
		return rt;
	}
	@SafeVarargs
	static public List<String> scan(String scanPackage, Class<? extends Annotation> ... classes) throws ClassNotFoundException
	{
	    ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
	    for(Class<? extends Annotation> clazz:classes)
	    {
	    	provider.addIncludeFilter(new AnnotationTypeFilter((Class<? extends Annotation>) clazz));
	    }
	    List<String> rt  =new ArrayList<String>();
	    for (BeanDefinition beanDef : provider.findCandidateComponents(scanPackage)) 
	    {
	    	String beanClassName = beanDef.getBeanClassName();
	    	rt.add(beanClassName);
	    }
		return rt;
	}
	
}
