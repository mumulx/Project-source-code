package com.atguigu.springdata.commonrepositorymethod;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public class CommonMethodTestImpl<T, ID extends Serializable> 
	extends SimpleJpaRepository<T, ID> implements CommonMethodTest<T, ID> {

	public CommonMethodTestImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	@Override
	public void method() {
		System.out.println("...METHOD TEST...");
	}

}
