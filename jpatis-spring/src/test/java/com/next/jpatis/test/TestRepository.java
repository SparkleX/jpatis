package com.next.jpatis.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.next.jpatis.test.domain.Orders;
import com.next.jpatis.test.repository.OrderRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestRepository {
	
	@Autowired
	protected OrderRepository orderRepo;

	@Test
	public void testExample() throws Exception {
		List<Orders> rt = this.orderRepo.findAll();
		assertThat(rt.size()).isEqualTo(0);
	}
}
