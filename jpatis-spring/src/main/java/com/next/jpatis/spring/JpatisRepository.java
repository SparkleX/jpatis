package com.next.jpatis.spring;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpatisRepository<T,ID> extends JpaRepository<T,ID>{

	void insert(T entity);

	void update(ID key, T entity);

	void delete(T entity);

}
