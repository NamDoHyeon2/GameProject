package Placement;

import java.util.LinkedList;

public class setEnemy {
	private LinkedList<Pokemon> enemy;
	private int turnNum;

	public setEnemy(int turnNum) {
		this.enemy = new LinkedList<>();
		this.turnNum = turnNum;

		setting();
	}

	LinkedList<Pokemon> settingEnemy() {
		return enemy;
	}

	void setting() {
		switch (turnNum) {
		case 1: {
			enemy.add(new Pokemon(1, 1, 1, "캐터피", "벌레", 1, 3, 3, "기절 시 능력 없는 2/2단데기 1 소환"));
			enemy.add(new Pokemon(2, 1, 1, "뿔충이", "벌레", 1, 3, 3, "아군 소환 시 체력 2 증가"));
			enemy.add(null);
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 2: {
			enemy.add(new Pokemon(18, 1, 1, "고라파덕", "물", 2, 3, 4, "기절 시 가장 앞에 있는 적군 2 피해"));
			enemy.add(new Pokemon(10, 1, 1, "식스테일", "불꽃", 1, 3, 3, "대결 시작 시 가장 뒤에 있는 적군 1 피해"));
			enemy.add(new Pokemon(9, 1, 1, "삐", "페어리", 1, 3, 3, "앞의 아군이 공격 시 앞의 아군 체력 2 증가"));
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 3: {
			enemy.add(new Pokemon(24, 1, 1, "피츄", "전기", 3, 4, 4, "공격시 25% 확률로 추가 공격"));
			enemy.add(new Pokemon(19, 2, 1, "성원숭", "격투", 2, 3, 4, "기절 시 능력 없는 망키 2 소환"));
			enemy.add(new Pokemon(4, 1, 1, "꼬렛", "노말", 1, 3, 3, "공격에 맞을 시 공격한 대상에게 1 피해"));
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 4: {
			enemy.add(new Pokemon(30, 2, 1, "데구리", "바위", 3, 4, 4, "공격에 맞을 시 체력 5 증가"));
			enemy.add(new Pokemon(31, 2, 1, "날생마", "불꽃", 4, 4, 5, "대결 시작 시 모든 유닛 2 피해"));
			enemy.add(new Pokemon(20, 1, 1, "가디", "불꽃", 2, 3, 4, "앞의 아군 기절 시 모든 적군 1 피해"));
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 5: {
			enemy.add(new Pokemon(28, 2, 1, "우츠동", "풀", 3, 4, 4, "기절 시 체력 2.5배인 능력 없는 모다피 2 소환"));
			enemy.add(new Pokemon(21, 2, 1, "이상해풀", "풀", 3, 4, 4, "공격에 맞을 시 공격력,체력 3 증가"));
			enemy.add(new Pokemon(1, 2, 1, "단데기", "벌레", 1, 3, 3, "기절 시 능력 없는 2/2단데기 2 소환"));
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 6: {
			enemy.add(new Pokemon(30, 3, 1, "딱구리", "바위", 3, 4, 4, "공격에 맞을 시 체력 6 증가"));
			enemy.add(new Pokemon(27, 3, 1, "괴력몬", "격투", 3, 4, 4, "공격 시 공격력 6 증가"));
			enemy.add(null);
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 7: {
			enemy.add(new Pokemon(12, 3, 1, "크로벳", "독", 2, 3, 4, "대결 시작 시 모든 적군 3 피해"));
			enemy.add(new Pokemon(43, 2, 1, "나시", "풀", 5, 5, 5, "적군 소환 시 랜덤한 적군 2명 에게 3 피해"));
			enemy.add(new Pokemon(39, 1, 1, "고오스", "독", 4, 4, 5, "공격에 맞을 시 공격한 적군에게 1 독 피해"));
			enemy.add(new Pokemon(42, 1, 1, "찌리리공", "전기", 5, 5, 5, "앞에 있는 아군 공격에 맞을 시 20% 대신 맞아줍니다."));
			enemy.add(null);
			break;
		}
		case 8: {
			enemy.add(new Pokemon(5, 2, 1, "깨비드릴조", "비행", 1, 3, 3, "공격 시 공격력 3 증가"));
			enemy.add(new Pokemon(9, 2, 1, "삐삐", "페어리", 1, 3, 3, "앞의 아군이 공격 시 앞의 아군 체력 3 증가"));
			enemy.add(new Pokemon(32, 2, 1, "야도란", "에스퍼", 4, 4, 5, "20% 확률로 적군의 공격을 무시"));
			enemy.add(new Pokemon(55, 2, 1, "켄타로스", "격투", 6, 6, 6, "앞의 아군 공격 시 1번 더 공격시킵니다. (레벨업 시 3/3증가)"));
			enemy.add(null);
			break;
		}
		case 9: {
			enemy.add(new Pokemon(1, 2, 1, "단데기", "벌레", 1, 10, 10, "기절 시 능력 없는 2/2단데기 2 소환"));
			enemy.add(new Pokemon(19, 2, 1, "성원숭", "격투", 2, 10, 10, "기절 시 능력 없는 망키 2 소환"));
			enemy.add(new Pokemon(2, 3, 1, "독침봉", "벌레", 1, 10, 10, "아군 소환 시 체력 4 증가"));
			enemy.add(new Pokemon(41, 2, 1, "킹크랩", "물", 5, 10, 10, "아군 소환 시 랜덤으로 아군 공격력,체력 3 증가"));
			enemy.add(new Pokemon(25, 2, 1, "슈룩챙이", "격투", 3, 10, 10, "아군 공격력 증가 시 추가로 2 증가"));
			break;
		}
		case 10: {
			enemy.add(new Pokemon(28, 2, 1, "우츠동", "풀", 3, 10, 10, "기절 시 체력 2.5배인 능력 없는 모다피 2 소환"));
			enemy.add(new Pokemon(39, 2, 1, "고우스트", "독", 4, 10, 10, "공격에 맞을 시 공격한 적군에게 2 독 피해"));
			enemy.add(new Pokemon(15, 3, 1, "도나리", "벌레", 2, 10, 10, "아군 소환시 소환된 아군 공격력,체력 4증가"));
			enemy.add(new Pokemon(55, 2, 1, "켄타로스", "격투", 6, 10, 10, "앞의 아군 공격 시 1번 더 공격시킵니다. (레벨업 시 3/3증가)"));
			enemy.add(new Pokemon(31, 2, 1, "날생마", "불꽃", 4, 10, 10, "대결 시작 시 모든 유닛 2 피해"));
			break;
		}
		case 11: {
			enemy.add(new Pokemon(27, 2, 1, "근육몬", "격투", 3, 20, 15, "공격 시 공격력 5 증가"));
			enemy.add(new Pokemon(40, 2, 1, "강철톤", "바위", 4, 20, 15, "공격 시 공격력, 체력 4 증가"));
			enemy.add(new Pokemon(46, 2, 1, "또도가스", "노말", 5, 20, 15, "공격 시 적군의 공격력 30%만큼 감소합니다"));
			enemy.add(new Pokemon(57, 2, 1, "라프라스", "물", 6, 20, 15, "공격에 맞을 시 체력의 30% 만큼 체력 증가"));
			enemy.add(null);
			break;
		}
		case 12: {
			enemy.add(new Pokemon(19, 1, 1, "망키", "격투", 2, 15, 15, "기절 시 능력 없는 망키 소환"));
			enemy.add(new Pokemon(27, 1, 1, "알통몬", "격투", 3, 15, 15, "공격 시 공격력 4 증가"));
			enemy.add(new Pokemon(16, 1, 1, "디그다", "땅", 2, 15, 15, "공격 시 뒤에 있는 아군 체력 2 증가"));
			enemy.add(new Pokemon(9, 1, 1, "삐", "페어리", 1, 15, 15, "앞의 아군이 공격 시 앞의 아군 체력 2 증가"));
			enemy.add(new Pokemon(24, 1, 1, "피츄", "전기", 3, 15, 15, "공격시 25% 확률로 추가 공격"));
			break;
		}
		case 13: {
			enemy.add(new Pokemon(57, 3, 1, "라프라스", "물", 6, 15, 30, "공격에 맞을 시 체력의 50% 만큼 체력 증가"));
			enemy.add(new Pokemon(52, 3, 1, "아쿠스타", "물", 6, 15, 30, "뒤에 있는 아군의 피해를 대신 받습니다. (레벨업 시 4/4증가)"));
			enemy.add(new Pokemon(53, 3, 1, "마임맨(가라르)", "에스퍼", 6, 25, 25, "3등급 이하 포켓몬 구매 시 5/5 증가"));
			enemy.add(null);
			enemy.add(null);
			break;
		}
		case 14: {
			enemy.add(new Pokemon(28, 2, 1, "우츠동", "풀", 3, 15, 15, "기절 시 체력 2.5배인 능력 없는 모다피 2 소환"));
			enemy.add(new Pokemon(47, 2, 1, "코뿌리", "땅", 5, 15, 15, "적군 처치 시 공격력,체력 5 증가"));
			enemy.add(new Pokemon(12, 2, 1, "골벳", "독", 2, 15, 15, "대결 시작 시 모든 적군 2 피해"));
			enemy.add(new Pokemon(34, 2, 1, "파오리(가라르)", "노말", 4, 15, 15, "아군 소환 시 소환된 유닛 공격력 3 증가"));
			enemy.add(new Pokemon(41, 2, 1, "킹크랩", "물", 5, 15, 15, "아군 소환 시 랜덤으로 아군 공격력,체력 3 증가"));
			break;
		}
		case 15: {
			enemy.add(new Pokemon(27, 2, 1, "근육몬", "격투", 3, 15, 15, "공격 시 공격력 5 증가"));
			enemy.add(new Pokemon(35, 2, 1, "두트리오", "노말", 4, 15, 15, "앞에 있는 아군 기절 시 모든 유닛에게 3 피해"));
			enemy.add(new Pokemon(45, 2, 1, "시라소몬", "격투", 5, 15, 15, "공격에 맞을 시 피해량 30%만큼 돌려줍니다"));
			enemy.add(new Pokemon(18, 2, 1, "골덕", "물", 2, 15, 15, "기절 시 가장 앞에 있는 적군 3 피해"));
			enemy.add(new Pokemon(24, 2, 1, "피카츄", "전기", 3, 15, 15, "공격시 35% 확률로 추가 공격"));
			break;
		}
		case 16: {
			enemy.add(new Pokemon(59, 2, 1, "잠만보", "노말", 6, 15, 20, "받는 피해가 절반이 됩니다. (레벨업 시 3/3증가)"));
			enemy.add(new Pokemon(32, 2, 1, "야도란", "에스퍼", 4, 20, 25, "20% 확률로 적군의 공격을 무시"));
			enemy.add(new Pokemon(37, 2, 1, "질뻐기", "독", 4, 15, 20, "대결 시작 시 모든 아군의 체력 40%만큼 증가"));
			enemy.add(new Pokemon(43, 2, 1, "나시", "풀", 5, 20, 20, "적군 소환 시 랜덤한 적군 2명 에게 3 피해"));
			enemy.add(new Pokemon(44, 2, 1, "텅구리", "땅", 5, 20, 20, "이 포켓몬은 2번 공격 합니다 (레벨업 시 3/3증가)"));
			break;
		}
		case 17: {
			enemy.add(new Pokemon(4, 3, 1, "레트라(알로라)", "노말", 1, 20, 20, "공격에 맞을 시 공격한 대상에게 3 피해"));
			enemy.add(new Pokemon(19, 3, 1, "저승갓승", "격투", 2, 20, 20, "기절 시 능력 없는 망키 3 소환"));
			enemy.add(new Pokemon(50, 2, 1, "시드라", "물", 5, 30, 20, "대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다. (레벨업 시 3/3증가)"));
			enemy.add(new Pokemon(54, 2, 1, "루주라", "에스퍼", 6, 25, 20, "대결 시작 시 능력의 공격력을 2 추가 합니다."));
			enemy.add(new Pokemon(55, 2, 1, "켄타로스", "격투", 6, 20, 20, "앞의 아군 공격 시 1번 더 공격시킵니다. (레벨업 시 3/3증가)"));
			break;
		}
		case 18: {
			enemy.add(new Pokemon(1, 3, 1, "버터플", "벌레", 1, 20, 20, "기절 시 능력 없는 2/2단데기 3 소환"));
			enemy.add(new Pokemon(20, 2, 1, "윈디", "불꽃", 2, 20, 20, "앞의 아군 기절 시 모든 적군 2 피해"));
			enemy.add(new Pokemon(24, 2, 1, "피카츄", "전기", 3, 20, 20, "공격시 35% 확률로 추가 공격"));
			enemy.add(new Pokemon(40, 3, 1, "메가강철톤", "바위", 4, 20, 20, "공격 시 공격력, 체력 5 증가"));
			enemy.add(new Pokemon(37, 3, 1, "질뻐기(알로라)", "독", 4, 20, 20, "대결 시작 시 모든 아군의 체력 50%만큼 증가"));
			break;
		}
		case 19: {
			enemy.add(new Pokemon(30, 3, 1, "딱구리", "바위", 3, 25, 20, "공격에 맞을 시 체력 6 증가"));
			enemy.add(new Pokemon(35, 2, 1, "두트리오", "노말", 4, 20, 25, "앞에 있는 아군 기절 시 모든 유닛에게 3 피해"));
			enemy.add(new Pokemon(32, 3, 1, "야도킹", "에스퍼", 4, 20, 30, "40% 확률로 적군의 공격을 무시"));
			enemy.add(new Pokemon(31, 2, 1, "날생마", "불꽃", 4, 20, 30, "대결 시작 시 모든 유닛 2 피해"));
			enemy.add(new Pokemon(44, 3, 1, "텅구리(알로라)", "땅", 5, 30, 20, "이 포켓몬은 2번 공격 합니다 (레벨업 시 4/4증가)"));
			break;
		}
		case 20: {
			enemy.add(new Pokemon(59, 3, 1, "잠만보", "노말", 6, 25, 30, "받는 피해가 절반이 됩니다. (레벨업 시 4/4증가)"));
			enemy.add(new Pokemon(55, 3, 1, "켄타로스", "격투", 6, 30, 30, "앞의 아군 공격 시 1번 더 공격시킵니다. (레벨업 시 4/4증가)"));
			enemy.add(new Pokemon(37, 3, 1, "질뻐기(알로라)", "독", 4, 20, 25, "대결 시작 시 모든 아군의 체력 50%만큼 증가"));
			enemy.add(new Pokemon(54, 3, 1, "루주라", "에스퍼", 6, 30, 30, "대결 시작 시 능력의 공격력을 3 추가 합니다."));
			enemy.add(new Pokemon(50, 3, 1, "킹드라", "물", 5, 30, 30, "대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다. (레벨업 시 4/4증가)"));
			break;
		}
		default: {
			break;
		}
		}
	}
}

