package com.cherrypicks.boc.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:/test-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class TestPushNotificationService {

	/*@Autowired
	private IPushNotificationService pushNotificationService;
	
	@Test
	public void testAdd() {
		PushNotification pushNotification = new PushNotification();
		pushNotification = pushNotificationService.add(pushNotification);
		Assert.assertNotNull(pushNotification);
	}*/
}
