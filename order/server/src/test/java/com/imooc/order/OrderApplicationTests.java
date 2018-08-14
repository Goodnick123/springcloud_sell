package com.imooc.order;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderApplicationTests {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void send(){
		//amqpTemplate.convertAndSend("myQueue","now"+new Date().toString());
		List<ServiceInstance> list = discoveryClient.getInstances("PRODUCT");
		if (list != null && list.size() > 0 ) {
			System.out.println(list.get(0).getUri());
		}
		Assert.assertTrue(list.size()>0);
	}



}
