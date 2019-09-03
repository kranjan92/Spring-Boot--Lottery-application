package com.lottery.ticket.application.lotteryapplication;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.lottery.ticket.application.LotteryApplication;


@RunWith(SpringRunner.class)
@Rollback
@SpringBootTest(classes = LotteryApplication.class)
@ActiveProfiles("test")
class LotteryApplicationTests {

	@Test
	void contextLoads() {
	}

}
