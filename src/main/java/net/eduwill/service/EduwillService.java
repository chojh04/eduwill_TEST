package net.eduwill.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import net.eduwill.FailureMessageException;

@Service
public class EduwillService {

	/**
	 * 가위바위보 게임 Class
	 * @return
	 */
	public String game(Integer playerCount) throws Exception {
		try {
			if(playerCount==null || playerCount<2 || playerCount>10) {
				throw new FailureMessageException("게임 참여자수가 올바르지 않습니다.(2명 이상, 10명 이하)");
			}
			
			//게임참가자 리스트 생성
			List<HashMap<String, Object>> playerList = this.setPlayerList(playerCount);
			
			if(playerList.size()>1) {
				String result = playGame(playerList);
			
				System.out.println("게임 결과 : "+result);
			}else {
				System.out.println("게임에 참여자가없습니다.");
			}
		}
		catch(FailureMessageException fe) {
			System.out.println("진행중 Error 발생");
			throw fe;
		}
		catch(Exception e) {
			System.out.println("진행중 Exception 발생 " +e.toString());
			throw e;
		}
		return "게임종료";
	}
	
	
	/**
	 * 게임참여자 리스트 생성 Class
	 * @param playerCount
	 * @return
	 */
	private List<HashMap<String, Object>> setPlayerList(Integer playerCount) throws Exception {
		List<HashMap<String, Object>> playerList=  new ArrayList<>();
		int randomValue = 0;
		try {
			for(int i=0; i<playerCount; i++) {
				// randomValue = {"0":"주먹", "1":"가위", "2":"보자기"} 
				randomValue = (int)(Math.random()*10)%3;
				
				//게임참여자 리스트 추가 
				playerList.add(setPlayer("player"+(i+1), randomValue));	
			}			
			return playerList;
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * 게임 참여자 정보 생성 Class
	 * @param playerName
	 * @param randomValue
	 * @return
	 */
	private HashMap<String, Object> setPlayer(String playerName, int randomValue) throws Exception {
		HashMap<String, Object> player = new HashMap<>();
		try {
			player.put("playerName", playerName); //참여자 이름
			player.put("randomValue", randomValue); //참여자가 낼 값
			
			return player;
		}catch(Exception e) {
			throw e;
		}
	}
	
	
	/**
	 * 가위바위보 게임 결과 생성 Class
	 * @param playerList
	 * @return
	 * @throws Exception 
	 */
	private String playGame(List<HashMap<String, Object>> playerList) throws Exception {
		String resultValue = "";
		//플레이어들이 제출한 값 리스트 
		List<Integer> valueArr = new ArrayList<>();		
		//주먹, 가위, 보를 제출한 플레이어 리스트 
		List<String> value0Player = new ArrayList<>(), value1Player = new ArrayList<>(), value2Player = new ArrayList<>(), winPlayer = new ArrayList<>();
		
		try {
			for(int i=0; i<playerList.size(); i++) {			
				//Player들이 제출한 값 수집 
				if(!valueArr.contains(playerList.get(i).get("randomValue"))) valueArr.add(Integer.valueOf(playerList.get(i).get("randomValue").toString()));
				
				switch(Integer.parseInt(playerList.get(i).get("randomValue").toString())) {
					case 0:
						value0Player.add(playerList.get(i).get("playerName").toString());
						break;
					case 1:
						value1Player.add(playerList.get(i).get("playerName").toString());
						break;
					case 2:
						value2Player.add(playerList.get(i).get("playerName").toString());
						break;
				}
			}
			System.out.println("'주먹'을 낸 플레이어 : "+value0Player);
			System.out.println("'가위'를 낸 플레이어 : "+value1Player);
			System.out.println("'보자기'를 낸 플레이어 : "+value2Player);	
			
			if(valueArr.size()>2 || valueArr.size()==1) {
			//Player들이 제출한 값에 '주먹,가위,보'가 모두 포함되어있을 경우 
				resultValue = "무승부";
			}else if(valueArr.size()==0) {
			//제출값이 없을 경	
				resultValue = "무효";
			}else if(valueArr.size()==2) {
			//제출값의 종류가 2개여서 승패처리가 가능 할 경우 
				if(valueArr.contains(0) && valueArr.contains(1)) { 
					//주먹, 가위 = 주먹 승 
					winPlayer = value0Player;
				}else if(valueArr.contains(0) && valueArr.contains(2)) {
					//주먹, 보자기 = 보자기 승 
					winPlayer = value2Player;
				}else if(valueArr.contains(1) && valueArr.contains(2)) {
					//가위, 보자기 = 가위 승 
					winPlayer = value1Player;
				}
				
				for(String val : winPlayer) {
					resultValue+=val+",";
				}
				resultValue = resultValue.substring(0, (resultValue.length()-1))+" 승리 ";
			}
			return resultValue;
		} catch (Exception e) {
			throw e;
		}		
	}
}
