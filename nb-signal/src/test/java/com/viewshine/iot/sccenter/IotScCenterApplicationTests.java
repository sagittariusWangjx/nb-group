package com.viewshine.iot.sccenter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IotScCenterApplicationTests {

	@Test
	public void contextLoads() {
		UUID uuid = UUID.randomUUID();
		System.out.println(uuid);


	}

}
