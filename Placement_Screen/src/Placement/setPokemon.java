package Placement;

import java.util.LinkedList;

public class setPokemon {
	int pokemonNum;
	private LinkedList<Pokemon> Lv1pokemon;
	private LinkedList<Pokemon> Lv2pokemon;
	private LinkedList<Pokemon> Lv3pokemon;

	public setPokemon() {
		this.Lv1pokemon = new LinkedList<>();
		this.Lv2pokemon = new LinkedList<>();
		this.Lv3pokemon = new LinkedList<>();
		setLV1Pokemon_info();
		setLV2Pokemon_info();
		setLV3Pokemon_info();
	}

	void setLV1Pokemon_info() {
		Lv1pokemon.add(new Pokemon(1, 1, 1, "캐터피", "벌레", 1, 3, 3, "기절 시 능력 없는 2/2단데기 1 소환"));
		Lv1pokemon.add(new Pokemon(2, 1, 1, "뿔충이", "벌레", 1, 3, 3, "아군 소환 시 체력 2 증가"));
		Lv1pokemon.add(new Pokemon(3, 1, 1, "구구", "비행", 1, 3, 3, "대결 시작 시 적군 랜덤 하나에게 2 피해"));
		Lv1pokemon.add(new Pokemon(4, 1, 1, "꼬렛", "노말", 1, 3, 3, "공격에 맞을 시 공격한 대상에게 1 피해"));
		Lv1pokemon.add(new Pokemon(5, 1, 1, "꺠비참", "비행", 1, 3, 3, "공격 시 공격력 2 증가"));
		Lv1pokemon.add(new Pokemon(6, 1, 1, "아보", "독", 1, 3, 3, "기절 시 가장 뒤에 있는 적군 1 독 피해"));
		Lv1pokemon.add(new Pokemon(7, 1, 1, "모래두지", "땅", 1, 3, 3, "판매 시 아군 체력 1 증가"));
		Lv1pokemon.add(new Pokemon(8, 1, 1, "니드런", "독", 1, 3, 3, "구매 시 랜덤한 아군 1명 공격력 1 증가"));
		Lv1pokemon.add(new Pokemon(9, 1, 1, "삐", "페어리", 1, 3, 3, "앞의 아군이 공격 시 앞의 아군 체력 2 증가"));
		Lv1pokemon.add(new Pokemon(10, 1, 1, "식스테일", "불꽃", 1, 3, 3, "대결 시작 시 가장 뒤에 있는 적군 1 피해"));
		Lv1pokemon.add(new Pokemon(11, 1, 1, "푸푸린", "페어리", 2, 3, 4, "공격에 맞을 시 공격한 적군 공격력 1 감소"));
		Lv1pokemon.add(new Pokemon(12, 1, 1, "주벳", "독", 2, 3, 4, "대결 시작 시 모든 적군 1 피해"));
		Lv1pokemon.add(new Pokemon(13, 1, 1, "뚜벅쵸", "풀", 2, 3, 4, "구매 시 양 옆에 배치된 아군 공격력, 체력 2증가"));
		Lv1pokemon.add(new Pokemon(14, 1, 1, "파라스", "벌레", 2, 3, 4, "판매 시 랜덤한 아군 1에게 버섯 투여"));
		Lv1pokemon.add(new Pokemon(15, 1, 1, "콘팡", "벌레", 2, 3, 4, "아군 소환시 소환된 아군 공격력,체력 2 증가"));
		Lv1pokemon.add(new Pokemon(16, 1, 1, "디그다", "땅", 2, 3, 4, "공격 시 뒤에 있는 아군 체력 2 증가"));
		Lv1pokemon.add(new Pokemon(17, 1, 1, "냐옹", "노말", 2, 3, 4, "구매 시 공격력이 가장 적은 아군에게 공격력 2배 증가"));
		Lv1pokemon.add(new Pokemon(18, 1, 1, "고라파덕", "물", 2, 3, 4, "기절 시 가장 앞에 있는 적군 2 피해"));
		Lv1pokemon.add(new Pokemon(19, 1, 1, "망키", "격투", 2, 3, 4, "기절 시 능력 없는 망키 소환"));
		Lv1pokemon.add(new Pokemon(20, 1, 1, "가디", "불꽃", 2, 3, 4, "앞의 아군 기절 시 모든 적군 1 피해"));
		Lv1pokemon.add(new Pokemon(21, 1, 1, "이상해씨", "풀", 3, 4, 4, "공격에 맞을 시 공격력,체력 2 증가"));
		Lv1pokemon.add(new Pokemon(22, 1, 1, "파이리", "불꽃", 3, 4, 4, "공격 시 랜덤한 적군에게 공격력 50%의 피해"));
		Lv1pokemon.add(new Pokemon(23, 1, 1, "꼬부기", "물", 3, 4, 4, "대결 시작 시 앞에 있는 아군에게 체력 50%만큼 증가"));
		Lv1pokemon.add(new Pokemon(24, 1, 1, "피츄", "전기", 3, 4, 4, "공격시 25% 확률로 추가 공격"));
		Lv1pokemon.add(new Pokemon(25, 1, 1, "발챙이", "격투", 3, 4, 4, "아군 공격력 증가 시 추가로 1 증가"));
		Lv1pokemon.add(new Pokemon(26, 1, 1, "캐이시", "에스퍼", 3, 4, 4, "대결 시작 시 앞에 있는 아군에게 공격력 50%만큼 추가"));
		Lv1pokemon.add(new Pokemon(27, 1, 1, "알통몬", "격투", 3, 4, 4, "공격 시 공격력 4 증가"));
		Lv1pokemon.add(new Pokemon(28, 1, 1, "모다피", "풀", 3, 4, 4, "기절 시 체력 2배인 능력 없는 모다피 2 소환"));
		Lv1pokemon.add(new Pokemon(29, 1, 1, "왕눈해", "독", 3, 4, 4, "대결 시작 시 가장 뒤에 있는 적군에게 1 독 피해"));
		Lv1pokemon.add(new Pokemon(30, 1, 1, "꼬마돌", "바위", 3, 4, 4, "공격에 맞을 시 체력 4 증가"));
		Lv1pokemon.add(new Pokemon(31, 1, 1, "포니타", "불꽃", 4, 4, 5, "대결 시작 시 모든 유닛 1 피해"));
		Lv1pokemon.add(new Pokemon(32, 1, 1, "야돈", "에스퍼", 4, 4, 5, "10% 확률로 적군의 공격을 무시"));
		Lv1pokemon.add(new Pokemon(33, 1, 1, "코일", "전기", 4, 4, 5, "구매 시 양 옆에 배치된 아군 공격력 3 증가"));
		Lv1pokemon.add(new Pokemon(34, 1, 1, "파오리", "노말", 4, 4, 5, "아군 소환 시 소환된 유닛 공격력 2 증가"));
		Lv1pokemon.add(new Pokemon(35, 1, 1, "두두", "노말", 4, 4, 5, "앞에 있는 아군 기절 시 모든 유닛에게 2 피해"));
		Lv1pokemon.add(new Pokemon(36, 1, 1, "쥬쥬", "물", 4, 4, 5, "아이템 사용 시 공격력, 체력 1 증가"));
		Lv1pokemon.add(new Pokemon(37, 1, 1, "질퍽이", "독", 4, 4, 5, "대결 시작 시 모든 아군의 체력 30%만큼 증가"));
		Lv1pokemon.add(new Pokemon(38, 1, 1, "셀러", "물", 4, 4, 5, "처음으로 공격에 맞을 시 무시 합니다"));
		Lv1pokemon.add(new Pokemon(39, 1, 1, "고오스", "독", 4, 4, 5, "공격에 맞을 시 공격한 적군에게 1 독 피해"));
		Lv1pokemon.add(new Pokemon(40, 1, 1, "롱스톤", "바위", 4, 4, 5, "공격 시 공격력, 체력 3 증가"));
		Lv1pokemon.add(new Pokemon(41, 1, 1, "크랩", "물", 5, 5, 5, "아군 소환 시 랜덤으로 아군 공격력,체력 2 증가"));
		Lv1pokemon.add(new Pokemon(42, 1, 1, "찌리리공", "전기", 5, 5, 5, "앞에 있는 아군 공격에 맞을 시 20% 대신 맞아줍니다."));
		Lv1pokemon.add(new Pokemon(43, 1, 1, "아리리", "풀", 5, 5, 5, "적군 소환 시 랜덤한 적군 1명 에게 3 피해"));
		Lv1pokemon.add(new Pokemon(44, 1, 1, "탕구리", "땅", 5, 5, 5, "이 포켓몬은 2번 공격 합니다"));
		Lv1pokemon.add(new Pokemon(45, 1, 1, "배루키", "격투", 5, 5, 5, "공격에 맞을 시 피해량 20%만큼 돌려줍니다"));
		Lv1pokemon.add(new Pokemon(46, 1, 1, "또가스", "노말", 5, 5, 5, "공격 시 적군의 공격력 20%만큼 감소합니다"));
		Lv1pokemon.add(new Pokemon(47, 1, 1, "뿔카노", "땅", 5, 5, 5, "적군 처치 시 공격력,체력 4 증가"));
		Lv1pokemon.add(new Pokemon(48, 1, 1, "덩쿠리", "풀", 5, 5, 5, "구매 시 랜덤한 아군에게 공격력, 체력 3 증가"));
		Lv1pokemon.add(new Pokemon(49, 1, 1, "캥카", "노말", 5, 5, 5, "판매 시 앞에 있는 아군에게 공격력,체력의 50%만큼 증가"));
		Lv1pokemon.add(new Pokemon(50, 1, 1, "쏘드라", "물", 5, 5, 5, "대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다."));
		Lv1pokemon.add(new Pokemon(51, 1, 1, "콘치", "물", 6, 6, 6, "소환된 아군 공격력, 체력 3증가"));
		Lv1pokemon.add(new Pokemon(52, 1, 1, "별가사리", "물", 6, 6, 6, "뒤에 있는 아군의 피해를 대신 받습니다."));
		Lv1pokemon.add(new Pokemon(53, 1, 1, "흉내내", "에스퍼", 6, 6, 6, "3등급 이하 포켓몬 구매 시 3/3 증가"));
		Lv1pokemon.add(new Pokemon(54, 1, 1, "뽀뽀라", "에스퍼", 6, 6, 6, "대결 시작 시 능력의 공격력을 1 추가 합니다."));
		Lv1pokemon.add(new Pokemon(55, 1, 1, "켄타로스", "격투", 6, 6, 6, "앞의 아군 공격 시 1번 더 공격시킵니다."));
		Lv1pokemon.add(new Pokemon(56, 1, 1, "잉어킹", "물", 6, 6, 6, "대결 승리 시 코인 1 증가"));
		Lv1pokemon.add(new Pokemon(57, 1, 1, "라프라스", "물", 6, 6, 6, "공격에 맞을 시 체력의 20% 만큼 체력 증가"));
		Lv1pokemon.add(new Pokemon(58, 1, 1, "암나이트", "벌레", 6, 6, 6, "공격 시 공격력의 50%만큼 그 뒤에있는 적군에게 피해"));
		Lv1pokemon.add(new Pokemon(59, 1, 1, "먹고자", "노말", 6, 6, 6, "받는 피해가 절반이 됩니다."));
		Lv1pokemon.add(new Pokemon(60, 1, 1, "미뇽", "비행", 6, 6, 6, "아이템 사용 시 아이템 효과 +1"));
	}

	void setLV2Pokemon_info() {
		Lv2pokemon.add(new Pokemon(1, 2, 1, "단데기", "벌레", 1, 3, 3, "기절 시 능력 없는 2/2단데기 2 소환"));
		Lv2pokemon.add(new Pokemon(2, 2, 1, "딱충이", "벌레", 1, 3, 3, "아군 소환 시 체력 3 증가"));
		Lv2pokemon.add(new Pokemon(3, 2, 1, "피죤", "비행", 1, 3, 3, "대결 시작 시 적군 랜덤 하나에게 3 피해"));
		Lv2pokemon.add(new Pokemon(4, 2, 1, "레트라", "노말", 1, 3, 3, "공격에 맞을 시 공격한 대상에게 2 피해"));
		Lv2pokemon.add(new Pokemon(5, 2, 1, "깨비드릴조", "비행", 1, 3, 3, "공격 시 공격력 3 증가"));
		Lv2pokemon.add(new Pokemon(6, 2, 1, "아보크", "독", 1, 3, 3, "기절 시 가장 뒤에 있는 적군 2 독 피해"));
		Lv2pokemon.add(new Pokemon(7, 2, 1, "고지", "땅", 1, 3, 3, "판매 시 아군 체력 2 증가"));
		Lv2pokemon.add(new Pokemon(8, 2, 1, "니드리노", "독", 1, 3, 3, "구매 시 랜덤한 아군 2명 공격력 1 증가"));
		Lv2pokemon.add(new Pokemon(9, 2, 1, "삐삐", "페어리", 1, 3, 3, "앞의 아군이 공격 시 앞의 아군 체력 3 증가"));
		Lv2pokemon.add(new Pokemon(10, 2, 1, "나인테일", "불꽃", 1, 3, 3, "대결 시작 시 가장 뒤에 있는 적군 2 피해"));
		Lv2pokemon.add(new Pokemon(11, 2, 1, "푸린", "페어리", 2, 3, 4, "공격에 맞을 시 공격한 적군 공격력 2감소"));
		Lv2pokemon.add(new Pokemon(12, 2, 1, "골벳", "독", 2, 3, 4, "대결 시작 시 모든 적군 2 피해"));
		Lv2pokemon.add(new Pokemon(13, 2, 1, "냄새꼬", "풀", 2, 3, 4, "구매 시 양 옆에 배치된 아군 공격력, 체력 3증가"));
		Lv2pokemon.add(new Pokemon(14, 2, 1, "파라섹트", "벌레", 2, 3, 4, "판매 시 랜덤한 아군 2에게 버섯 투여"));
		Lv2pokemon.add(new Pokemon(15, 2, 1, "도나리", "벌레", 2, 3, 4, "아군 소환시 소환된 아군 공격력,체력 3증가"));
		Lv2pokemon.add(new Pokemon(16, 2, 1, "닥트리오", "땅", 2, 3, 4, "공격 시 뒤에 있는 아군 체력 3 증가"));
		Lv2pokemon.add(new Pokemon(17, 2, 1, "냐옹(알로라)", "노말", 2, 3, 4, "구매 시 공격력이 가장 적은 아군에게 공격력 2.5배 증가"));
		Lv2pokemon.add(new Pokemon(18, 2, 1, "골덕", "물", 2, 3, 4, "기절 시 가장 앞에 있는 적군 3 피해"));
		Lv2pokemon.add(new Pokemon(19, 2, 1, "성원숭", "격투", 2, 3, 4, "기절 시 능력 없는 망키 2 소환"));
		Lv2pokemon.add(new Pokemon(20, 2, 1, "윈디", "불꽃", 2, 3, 4, "앞의 아군 기절 시 모든 적군 2 피해"));
		Lv2pokemon.add(new Pokemon(21, 2, 1, "이상해풀", "풀", 3, 4, 4, "공격에 맞을 시 공격력,체력 3 증가"));
		Lv2pokemon.add(new Pokemon(22, 2, 1, "리자드", "불꽃", 3, 4, 4, "공격 시 랜덤한 적군에게 공격력 70%의 피해"));
		Lv2pokemon.add(new Pokemon(23, 2, 1, "어니부기", "물", 3, 4, 4, "대결 시작 시 앞에 있는 아군에게 체력 70%만큼 증가"));
		Lv2pokemon.add(new Pokemon(24, 2, 1, "피카츄", "전기", 3, 4, 4, "공격시 35% 확률로 추가 공격"));
		Lv2pokemon.add(new Pokemon(25, 2, 1, "슈룩챙이", "격투", 3, 4, 4, "아군 공격력 증가 시 추가로 2 증가"));
		Lv2pokemon.add(new Pokemon(26, 2, 1, "윤겔라", "에스퍼", 3, 4, 4, "대결 시작 시 앞에 있는 아군에게 공격력 70%만큼 추가"));
		Lv2pokemon.add(new Pokemon(27, 2, 1, "근육몬", "격투", 3, 4, 4, "공격 시 공격력 5 증가"));
		Lv2pokemon.add(new Pokemon(28, 2, 1, "우츠동", "풀", 3, 4, 4, "기절 시 체력 2.5배인 능력 없는 모다피 2 소환"));
		Lv2pokemon.add(new Pokemon(29, 2, 1, "독파리", "독", 3, 4, 4, "대결 시작 시 가장 뒤에 있는 적군에게 2 독 피해"));
		Lv2pokemon.add(new Pokemon(30, 2, 1, "데구리", "바위", 3, 4, 4, "공격에 맞을 시 체력 5 증가"));
		Lv2pokemon.add(new Pokemon(31, 2, 1, "날생마", "불꽃", 4, 4, 5, "대결 시작 시 모든 유닛 2 피해"));
		Lv2pokemon.add(new Pokemon(32, 2, 1, "야도란", "에스퍼", 4, 4, 5, "20% 확률로 적군의 공격을 무시"));
		Lv2pokemon.add(new Pokemon(33, 2, 1, "레어코일", "전기", 4, 4, 5, "구매 시 양 옆에 배치된 아군 공격력 4 증가"));
		Lv2pokemon.add(new Pokemon(34, 2, 1, "파오리(가라르)", "노말", 4, 4, 5, "아군 소환 시 소환된 유닛 공격력 3 증가"));
		Lv2pokemon.add(new Pokemon(35, 2, 1, "두트리오", "노말", 4, 4, 5, "앞에 있는 아군 기절 시 모든 유닛에게 3 피해"));
		Lv2pokemon.add(new Pokemon(36, 2, 1, "쥬레곤", "물", 4, 4, 5, "아이템 사용 시 공격력, 체력 2 증가"));
		Lv2pokemon.add(new Pokemon(37, 2, 1, "질뻐기", "독", 4, 4, 5, "대결 시작 시 모든 아군의 체력 40%만큼 증가"));
		Lv2pokemon.add(new Pokemon(38, 2, 1, "파르셀", "물", 4, 4, 5, "처음으로 공격에 맞을 시 무시 합니다 (레벨업 시 3/3증가)"));
		Lv2pokemon.add(new Pokemon(39, 2, 1, "고우스트", "독", 4, 4, 5, "공격에 맞을 시 공격한 적군에게 2 독 피해"));
		Lv2pokemon.add(new Pokemon(40, 2, 1, "강철톤", "바위", 4, 4, 5, "공격 시 공격력, 체력 4 증가"));
		Lv2pokemon.add(new Pokemon(41, 2, 1, "킹크랩", "물", 5, 5, 5, "아군 소환 시 랜덤으로 아군 공격력,체력 3 증가"));
		Lv2pokemon.add(new Pokemon(42, 2, 1, "봄볼", "전기", 5, 5, 5, "앞에 있는 아군 공격에 맞을 시 30% 대신 맞아줍니다."));
		Lv2pokemon.add(new Pokemon(43, 2, 1, "나시", "풀", 5, 5, 5, "적군 소환 시 랜덤한 적군 2명 에게 3 피해"));
		Lv2pokemon.add(new Pokemon(44, 2, 1, "텅구리", "땅", 5, 5, 5, "이 포켓몬은 2번 공격 합니다 (레벨업 시 3/3증가)"));
		Lv2pokemon.add(new Pokemon(45, 2, 1, "시라소몬", "격투", 5, 5, 5, "공격에 맞을 시 피해량 30%만큼 돌려줍니다"));
		Lv2pokemon.add(new Pokemon(46, 2, 1, "또도가스", "노말", 5, 5, 5, "공격 시 적군의 공격력 30%만큼 감소합니다"));
		Lv2pokemon.add(new Pokemon(47, 2, 1, "코뿌리", "땅", 5, 5, 5, "적군 처치 시 공격력,체력 5 증가"));
		Lv2pokemon.add(new Pokemon(48, 2, 1, "덩쿠림보", "풀", 5, 5, 5, "구매 시 랜덤한 아군에게 공격력, 체력 4 증가"));
		Lv2pokemon.add(new Pokemon(49, 2, 1, "메가캥카", "노말", 5, 5, 5, "판매 시 앞에 있는 아군에게 공격력,체력의 70%만큼 증가"));
		Lv2pokemon.add(new Pokemon(50, 2, 1, "시드라", "물", 5, 5, 5, "대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다. (레벨업 시 3/3증가)"));
		Lv2pokemon.add(new Pokemon(51, 2, 1, "왕콘치", "물", 6, 6, 6, "소환된 아군 공격력, 체력 4증가"));
		Lv2pokemon.add(new Pokemon(52, 2, 1, "아쿠스타", "물", 6, 6, 6, "뒤에 있는 아군의 피해를 대신 받습니다. (레벨업 시 3/3증가)"));
		Lv2pokemon.add(new Pokemon(53, 2, 1, "마임맨", "에스퍼", 6, 6, 6, "3등급 이하 포켓몬 구매 시 4/4 증가"));
		Lv2pokemon.add(new Pokemon(54, 2, 1, "루주라", "에스퍼", 6, 6, 6, "대결 시작 시 능력의 공격력을 2 추가 합니다."));
		Lv2pokemon.add(new Pokemon(55, 2, 1, "켄타로스", "격투", 6, 6, 6, "앞의 아군 공격 시 1번 더 공격시킵니다. (레벨업 시 3/3증가)"));
		Lv2pokemon.add(new Pokemon(56, 2, 1, "갸랴도스", "물", 6, 6, 6, "대결 승리 시 코인 2 증가"));
		Lv2pokemon.add(new Pokemon(57, 2, 1, "라프라스", "물", 6, 6, 6, "공격에 맞을 시 체력의 30% 만큼 체력 증가"));
		Lv2pokemon.add(new Pokemon(58, 2, 1, "암스타", "벌레", 6, 6, 6, "공격 시 공격력의 60%만큼 그 뒤에있는 적군에게 피해"));
		Lv2pokemon.add(new Pokemon(59, 2, 1, "잠만보", "노말", 6, 6, 6, "받는 피해가 절반이 됩니다. (레벨업 시 3/3증가)"));
		Lv2pokemon.add(new Pokemon(60, 2, 1, "신뇽", "비행", 6, 6, 6, "아이템 사용 시 아이템 효과 +1 (레벨업 시 3/3증가)"));
	}

	void setLV3Pokemon_info() {
		Lv3pokemon.add(new Pokemon(1, 3, 1, "버터플", "벌레", 1, 3, 3, "기절 시 능력 없는 2/2단데기 3 소환"));
		Lv3pokemon.add(new Pokemon(2, 3, 1, "독침봉", "벌레", 1, 3, 3, "아군 소환 시 체력 4 증가"));
		Lv3pokemon.add(new Pokemon(3, 3, 1, "피죤투", "비행", 1, 3, 3, "대결 시작 시 적군 랜덤 하나에게 4 피해"));
		Lv3pokemon.add(new Pokemon(4, 3, 1, "레트라(알로라)", "노말", 1, 3, 3, "공격에 맞을 시 공격한 대상에게 3 피해"));
		Lv3pokemon.add(new Pokemon(5, 3, 1, "깨비드릴조", "비행", 1, 3, 3, "공격 시 공격력 4 증가"));
		Lv3pokemon.add(new Pokemon(6, 3, 1, "아보크", "독", 1, 3, 3, "기절 시 가장 뒤에 있는 적군 3 독 피해"));
		Lv3pokemon.add(new Pokemon(7, 3, 1, "고지", "땅", 1, 3, 3, "판매 시 아군 체력 3 증가"));
		Lv3pokemon.add(new Pokemon(8, 3, 1, "니드킹", "독", 1, 3, 3, "구매 시 랜덤한 아군 3명 공격력 1 증가"));
		Lv3pokemon.add(new Pokemon(9, 3, 1, "픽시", "페어리", 1, 3, 3, "앞의 아군이 공격 시 앞의 아군 체력 4 증가"));
		Lv3pokemon.add(new Pokemon(10, 3, 1, "나인테일", "불꽃", 1, 3, 3, "대결 시작 시 가장 뒤에 있는 적군 3 피해"));
		Lv3pokemon.add(new Pokemon(11, 3, 1, "푸크린", "페어리", 2, 3, 4, "공격에 맞을 시 공격한 적군 공격력 3감소"));
		Lv3pokemon.add(new Pokemon(12, 3, 1, "크로벳", "독", 2, 3, 4, "대결 시작 시 모든 적군 3 피해"));
		Lv3pokemon.add(new Pokemon(13, 3, 1, "라플레시아", "풀", 2, 3, 4, "구매 시 양 옆에 배치된 아군 공격력, 체력 4증가"));
		Lv3pokemon.add(new Pokemon(14, 3, 1, "파라섹트", "벌레", 2, 3, 4, "판매 시 랜덤한 아군 3에게 버섯 투여"));
		Lv3pokemon.add(new Pokemon(15, 3, 1, "도나리", "벌레", 2, 3, 4, "아군 소환시 소환된 아군 공격력,체력 4증가"));
		Lv3pokemon.add(new Pokemon(16, 3, 1, "닥트리오(알로라)", "땅", 2, 3, 4, "공격 시 뒤에 있는 아군 체력 4 증가"));
		Lv3pokemon.add(new Pokemon(17, 3, 1, "페르시온", "노말", 2, 3, 4, "구매 시 공격력이 가장 적은 아군에게 공격력 3배 증가"));
		Lv3pokemon.add(new Pokemon(18, 3, 1, "골덕", "물", 2, 3, 4, "기절 시 가장 앞에 있는 적군 4 피해"));
		Lv3pokemon.add(new Pokemon(19, 3, 1, "저승갓승", "격투", 2, 3, 4, "기절 시 능력 없는 망키 3 소환"));
		Lv3pokemon.add(new Pokemon(20, 3, 1, "윈디(히스이)", "불꽃", 2, 3, 4, "앞의 아군 기절 시 모든 적군 3 피해"));
		Lv3pokemon.add(new Pokemon(21, 3, 1, "이상해꽃", "풀", 3, 4, 4, "공격에 맞을 시 공격력,체력 4 증가"));
		Lv3pokemon.add(new Pokemon(22, 3, 1, "리자몽", "불꽃", 3, 4, 4, "공격 시 랜덤한 적군에게 공격력 100%의 피해"));
		Lv3pokemon.add(new Pokemon(23, 3, 1, "거북왕", "물", 3, 4, 4, "대결 시작 시 앞에 있는 아군에게 체력 100%만큼 증가"));
		Lv3pokemon.add(new Pokemon(24, 3, 1, "라이츄", "전기", 3, 4, 4, "공격시 50% 확률로 추가 공격"));
		Lv3pokemon.add(new Pokemon(25, 3, 1, "강챙이", "격투", 3, 4, 4, "아군 공격력 증가 시 추가로 3 증가"));
		Lv3pokemon.add(new Pokemon(26, 3, 1, "후딘", "에스퍼", 3, 4, 4, "대결 시작 시 앞에 있는 아군에게 공격력 100%만큼 추가"));
		Lv3pokemon.add(new Pokemon(27, 3, 1, "괴력몬", "격투", 3, 4, 4, "공격 시 공격력 6 증가"));
		Lv3pokemon.add(new Pokemon(28, 3, 1, "우츠보트", "풀", 3, 4, 4, "기절 시 체력 3배인 능력 없는 모다피 2 소환"));
		Lv3pokemon.add(new Pokemon(29, 3, 1, "독파리", "독", 3, 4, 4, "대결 시작 시 가장 뒤에 있는 적군에게 3 독 피해"));
		Lv3pokemon.add(new Pokemon(30, 3, 1, "딱구리", "바위", 3, 4, 4, "공격에 맞을 시 체력 6 증가"));
		Lv3pokemon.add(new Pokemon(31, 3, 1, "날생마", "불꽃", 4, 4, 5, "대결 시작 시 모든 유닛 3 피해"));
		Lv3pokemon.add(new Pokemon(32, 3, 1, "야도킹", "에스퍼", 4, 4, 5, "40% 확률로 적군의 공격을 무시"));
		Lv3pokemon.add(new Pokemon(33, 3, 1, "자포코일", "전기", 4, 4, 5, "구매 시 양 옆에 배치된 아군 공격력 5 증가"));
		Lv3pokemon.add(new Pokemon(34, 3, 1, "창파나이트", "노말", 4, 4, 5, "아군 소환 시 소환된 유닛 공격력 4 증가"));
		Lv3pokemon.add(new Pokemon(35, 3, 1, "두트리오", "노말", 4, 4, 5, "앞에 있는 아군 기절 시 모든 유닛에게 4 피해"));
		Lv3pokemon.add(new Pokemon(36, 3, 1, "쥬레곤", "물", 4, 4, 5, "아이템 사용 시 공격력, 체력 3 증가"));
		Lv3pokemon.add(new Pokemon(37, 3, 1, "질뻐기(알로라)", "독", 4, 4, 5, "대결 시작 시 모든 아군의 체력 50%만큼 증가"));
		Lv3pokemon.add(new Pokemon(38, 3, 1, "파르셀", "물", 4, 4, 5, "처음으로 공격에 맞을 시 무시 합니다 (레벨업 시 4/4증가)"));
		Lv3pokemon.add(new Pokemon(39, 3, 1, "팬텀", "독", 4, 4, 5, "공격에 맞을 시 공격한 적군에게 3 독 피해"));
		Lv3pokemon.add(new Pokemon(40, 3, 1, "메가강철톤", "바위", 4, 4, 5, "공격 시 공격력, 체력 5 증가"));
		Lv3pokemon.add(new Pokemon(41, 3, 1, "킹크랩", "물", 5, 5, 5, "아군 소환 시 랜덤으로 아군 공격력,체력 4 증가"));
		Lv3pokemon.add(new Pokemon(42, 3, 1, "봄볼(히스이)", "전기", 5, 5, 5, "앞에 있는 아군 공격에 맞을 시 50% 대신 맞아줍니다."));
		Lv3pokemon.add(new Pokemon(43, 3, 1, "나시(알로라)", "풀", 5, 5, 5, "적군 소환 시 랜덤한 적군 3명 에게 3 피해"));
		Lv3pokemon.add(new Pokemon(44, 3, 1, "텅구리(알로라)", "땅", 5, 5, 5, "이 포켓몬은 2번 공격 합니다 (레벨업 시 4/4증가)"));
		Lv3pokemon.add(new Pokemon(45, 3, 1, "홍수몬", "격투", 5, 5, 5, "공격에 맞을 시 피해량 50%만큼 돌려줍니다"));
		Lv3pokemon.add(new Pokemon(46, 3, 1, "또도가스(가라르)", "노말", 5, 5, 5, "공격 시 적군의 공격력 50%만큼 감소합니다"));
		Lv3pokemon.add(new Pokemon(47, 3, 1, "거대코뿌리", "땅", 5, 5, 5, "적군 처치 시 공격력,체력 6 증가"));
		Lv3pokemon.add(new Pokemon(48, 3, 1, "덩쿠림보", "풀", 5, 5, 5, "구매 시 랜덤한 아군에게 공격력, 체력 5 증가"));
		Lv3pokemon.add(new Pokemon(49, 3, 1, "메가캥카", "노말", 5, 5, 5, "판매 시 앞에 있는 아군에게 공격력,체력의 100%만큼 증가"));
		Lv3pokemon.add(new Pokemon(50, 3, 1, "킹드라", "물", 5, 5, 5, "대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다. (레벨업 시 4/4증가)"));
		Lv3pokemon.add(new Pokemon(51, 3, 1, "왕콘치", "물", 6, 6, 6, "소환된 아군 공격력, 체력 5증가"));
		Lv3pokemon.add(new Pokemon(52, 3, 1, "아쿠스타", "물", 6, 6, 6, "뒤에 있는 아군의 피해를 대신 받습니다. (레벨업 시 4/4증가)"));
		Lv3pokemon.add(new Pokemon(53, 3, 1, "마임맨(가라르)", "에스퍼", 6, 6, 6, "3등급 이하 포켓몬 구매 시 5/5 증가"));
		Lv3pokemon.add(new Pokemon(54, 3, 1, "루주라", "에스퍼", 6, 6, 6, "대결 시작 시 능력의 공격력을 3 추가 합니다."));
		Lv3pokemon.add(new Pokemon(55, 3, 1, "켄타로스", "격투", 6, 6, 6, "앞의 아군 공격 시 1번 더 공격시킵니다. (레벨업 시 4/4증가)"));
		Lv3pokemon.add(new Pokemon(56, 3, 1, "메가갸랴도스", "물", 6, 6, 6, "대결 승리 시 코인 3 증가"));
		Lv3pokemon.add(new Pokemon(57, 3, 1, "라프라스", "물", 6, 6, 6, "공격에 맞을 시 체력의 50% 만큼 체력 증가"));
		Lv3pokemon.add(new Pokemon(58, 3, 1, "암스타", "벌레", 6, 6, 6, "공격 시 공격력의 75%만큼 그 뒤에있는 적군에게 피해"));
		Lv3pokemon.add(new Pokemon(59, 3, 1, "잠만보", "노말", 6, 6, 6, "받는 피해가 절반이 됩니다. (레벨업 시 4/4증가)"));
		Lv3pokemon.add(new Pokemon(60, 3, 1, "망나뇽", "비행", 6, 6, 6, "아이템 사용 시 아이템 효과 +1 (레벨업 시 4/4증가)"));
	}

	Pokemon getLV1Pokemon(int pokemonNum) {
        for (Pokemon item : Lv1pokemon) {
            if (item.getPokemonNum() == pokemonNum) {
                int pokemonNum1 = item.getPokemonNum();
                int Lv = item.getLv();
                int exp = item.getExp();
                String name = item.getName();
                String type = item.getType();
                int grade = item.getGrade();
                int health = item.getHealth();
                int power = item.getPower();
                String ability = item.getAbility();
                Pokemon p = new Pokemon(pokemonNum1, Lv, exp, name, type, grade, health, power, ability);
                return p;
            }

        }
        return null;
    }

	Pokemon getLV2Pokemon(int pokemonNum) {
		for (Pokemon item : Lv2pokemon) {
			if (item.getPokemonNum() == pokemonNum)
				return item;
		}
		return null;
	}

	Pokemon getLV3Pokemon(int pokemonNum) {
		for (Pokemon item : Lv3pokemon) {
			if (item.getPokemonNum() == pokemonNum)
				return item;
		}
		return null;
	}
}
