package net.eduwill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.eduwill.service.EduwillService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EduwillApplicationTests {

	@Autowired
	EduwillService service;
	
	
	@Test
	public void PlayGameTest() throws Exception {
		//게임 참가자 수 설정(2~10)
		Integer playerCount=2;
		System.out.println("가위바위보 게임시작 - 참가인원 : "+playerCount);
		System.out.println(service.game(playerCount));
	}

}
