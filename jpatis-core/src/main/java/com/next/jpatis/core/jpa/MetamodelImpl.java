package com.next.jpatis.core.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.metamodel.EmbeddableType;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
@SuppressWarnings({"rawtypes","unchecked"})
public class MetamodelImpl implements Metamodel
{

	@Override
	public <X> EntityType<X> entity(Class<X> cls) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	
	@Override
	public <X> ManagedType<X> managedType(Class<X> cls) {
		return (ManagedType<X>) new ManagedTypeImpl(cls);
	}

	@Override
	public <X> EmbeddableType<X> embeddable(Class<X> cls) {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

	@Override
	public Set<ManagedType<?>> getManagedTypes() {
		return new HashSet();
	}

	@Override
	public Set<EntityType<?>> getEntities() {
		return new HashSet();
	}

	@Override
	public Set<EmbeddableType<?>> getEmbeddables() {
		throw new RuntimeException("NOT IMPLEMENTED");
	}

}
