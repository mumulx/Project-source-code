package com.atguigu.springdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepsotoryImpl implements PersonDao {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void test() {
		Person person = entityManager.find(Person.class, 11);
		System.out.println("-->" + person);
	}

}
