package com.next.jpatis.spring;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.spi.PersistenceProvider;

import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;

import com.next.jpatis.core.jpa.PersistenceProviderImpl;

public class JpatisVendorAdapter extends AbstractJpaVendorAdapter {
	private String[] entityPackages;
	public JpatisVendorAdapter()
	{
		this.entityPackages = new String[0];
	}
	public JpatisVendorAdapter(String[] packages) {
		this.entityPackages = packages;
	}

	@Override
	public PersistenceProvider getPersistenceProvider() {
		List<Class<?>> entityClass = scanEntities(this.entityPackages);
		return new PersistenceProviderImpl();
	}

	private List<Class<?>> scanEntities(String[] entityPackages) {
		List<Class<?>> rt = new ArrayList<>(); 
		try {
			for (String packName : entityPackages) {
				List<String> packageNames = SpringScanner.scan(packName, Entity.class);
				for(String className:packageNames)
				{
					Class<?> clazz = Class.forName(className);
					rt.add(clazz);
				}
				
			}
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return rt;
	}
}
