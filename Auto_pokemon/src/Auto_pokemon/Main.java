package Auto_pokemon;

public class Main {

	public static void main(String[] args) {
		
		/* 로그인 및 회원가입 클래스
		String login_id = "";
		String login_password = "";
		String login_name = "";
		Account account = new Account(login_id,login_password,login_name); //아이디, 비밀번호, 이름 을 차례로 넣어줌
		account.login();
		account.join_membership();
		*/
		
		//유저의 큰 게임 수 저장 및 조회 클래스
		//GameCount gamecount = new GameCount(1); //유저의 PK값
		//gamecount.Input_Record();
		//gamecount.Result_Update("패");
		//gamecount.Show_Record();
		
		
		
		TurnInformation turninformation = new TurnInformation(1); //유저의 PK값
		//turninformation.Input_Turn(5, 0, 0, "패"); //턴수, 심장수, 이긴횟수, 결과 를 차례로 넣어줌
		turninformation.Show_Turn_Record(1); //게임 수를 넣어주면 그 게임수에 대한 정보가 나옴

	}

}
