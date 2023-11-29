package Placement;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;


public class Placement {
	private int life = 10; // 목숨
	private int coin = 10; // 코인
	private int badge = 0; // 뱃지
	private int turnNum = 1;// 턴 수
	Random random = new Random();
	Scanner sc = new Scanner(System.in);
	private LinkedList<Pokemon> shopPokemon;
	private LinkedList<Pokemon> placePokemon;
	private LinkedList<item> item;
	private Pokemon forReplace = null;
	private LinkedList<Pokemon> toBattle;
	setPokemon s = new setPokemon();
	setItem t = new setItem();
	boolean frozenPokemonNum[] = { false, false, false, false, false }; // 얼리기 설정
	boolean frozenItemNum[] = { false, false };
	int effectNum[] = { 0, 0, 0, 0, 0 };
	boolean item24Effect = false;

	Placement() {
		this.shopPokemon = new LinkedList<>();
		this.placePokemon = new LinkedList<>();
		this.item = new LinkedList<>();
		this.toBattle = new LinkedList<>();
		settingList();
	}

	void settingList() { // 그냥 비어두면 에러 떠서 null로 지정 함
		shopPokemon.add(0, null);
		shopPokemon.add(1, null);
		shopPokemon.add(2, null);
		shopPokemon.add(3, null);
		shopPokemon.add(4, null);
		item.add(0, null);
		item.add(1, null);
		placePokemon.add(0, null);
		placePokemon.add(1, null);
		placePokemon.add(2, null);
		placePokemon.add(3, null);
		placePokemon.add(4, null);
		toBattle.add(0, null);
		toBattle.add(1, null);
		toBattle.add(2, null);
		toBattle.add(3, null);
		toBattle.add(4, null);
	}

	void run() {
		shop_reroll();
		while (true) {
			System.out.println("현재 정보 확인 (0), 배치구역 (1), 상점구역(2), 전투구역(3)");
			System.out.print(">>");
			int chooseNum = sc.nextInt();
			switch (chooseNum) {
			case 0: {
				myInfo();
				break;
			}
			case 1: {
				place();
				break;
			}
			case 2: {
				shop();
				break;
			}
			case 3: {
                for (int i = 0; i < 5; i++) {
                    if (placePokemon.get(i) != null) {
                        int pokemonNum = placePokemon.get(i).getPokemonNum();
                        int Lv = placePokemon.get(i).getLv();
                        int exp = placePokemon.get(i).getExp();
                        String name = placePokemon.get(i).getName();
                        String type = placePokemon.get(i).getType();
                        int grade = placePokemon.get(i).getGrade();
                        int health = placePokemon.get(i).getHealth();
                        int power = placePokemon.get(i).getPower();
                        String ability = placePokemon.get(i).getAbility();
                        Pokemon p = new Pokemon(pokemonNum, Lv, exp, name, type, grade, health, power, ability);
                        toBattle.set(i, p);
                    } else
                        toBattle.set(i, null);
                }

                battle b = new battle(turnNum, toBattle.get(0), toBattle.get(1), toBattle.get(2), toBattle.get(3),
                        toBattle.get(4), effectNum);
                int resultNum = b.run();
                if (resultNum == -1) {
                    this.life -= 1;
                    startPlacement();
                    if (item24Effect) {
                        coin++;
                    }
                    break;
                } else if (resultNum == 1) {
                    this.badge += 1;
                    startPlacement();
                    this.coin += coinUp();
                    if (item24Effect) {
                        coin++;
                        coin++;
                    }
                    break;
                }
            }
			default:
				System.out.println("<<<입력 오류>>>");
				break;
			}
		}
	}

	void startPlacement() { // 배치 단계 시작 시 구현
		shop_reroll();
		coin = 10;
		turnNum++;
	}

	void myInfo() { // 현제 정보
		System.out.println("목숨 : " + life);
		System.out.println("코인 : " + coin);
		System.out.println("뱃지 : " + badge);
		System.out.println("턴 수 : " + turnNum);
	}

	int coinUp() {
		for (Pokemon p : placePokemon) {
			if (p != null && p.getPokemonNum() == 56) { // 대결 승리 시 코인 1, 2, 3 증가
				if (p.getLv() == 1) {
					return 1;
				} else if (p.getLv() == 2) {
					return 2;
				} else if (p.getLv() == 3) {
					return 3;
				}
			}
		}
		return 0;
	}

	void place() { // 배치 구역
		System.out.println("<<<배치구역 입니다>>>");

		while (true) {
			System.out.println("배치 포켓몬 확인 (1), 위치 바꾸기(2), 판매 (3), 뒤로가기(0)");
			System.out.print(">>");
			int chooseNum = sc.nextInt();
			switch (chooseNum) {
			case 1: {
				place_show();
				break;
			}
			case 2: {
				System.out.println("바꿀 위치 번호를 입력");
				System.out.print(">>");
				int place1 = sc.nextInt();
				System.out.print(">>");
				int place2 = sc.nextInt();
				place_replace(place1 - 1, place2 - 1);
				break;
			}
			case 3: {
				System.out.println("판매 할 포켓몬 배치 번호를 입력");
				System.out.print(">>");
				int sellNum = sc.nextInt();
				place_sell(sellNum - 1);
				break;
			}
			case 0: {
				return;
			}
			default:
				System.out.println("<<<입력 오류>>>");
				break;
			}
		}
	}

	boolean validationPlaceNum(int placeNum) { // 배치 구역 번호 유효성 검사
		if (4 < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}

	boolean isNullPlacePokemon(int placeNum) { // 배치 구역 포켓몬이 null인지 확인
		if (placePokemon.get(placeNum) == null)
			return true;
		return false;
	}

	void place_show() { // 배치구역 포케몬 확인
		System.out.println("<<<배치 구역 출력>>>");
		int i = 0;
		while (i < 5) {
			if (isNullPlacePokemon(i)) { // 없으면 비어있음 출력
				System.out.println((i + 1) + "번 비어있음");
			} else {
				System.out.println((i + 1) + "번 이름 : " + placePokemon.get(i).getName() + " / 타입 : "
						+ placePokemon.get(i).getType() + " / 등급 : " + placePokemon.get(i).getGrade() + " / LV : "
						+ placePokemon.get(i).getLv() + " / 경험치 : " + placePokemon.get(i).getExp() + " / 체력 : "
						+ placePokemon.get(i).getHealth() + " / 공격력 : " + placePokemon.get(i).getPower() + " / 능력 : "
						+ placePokemon.get(i).getAbility());
			}
			i++;
		}
	}

	void place_replace(int place1, int place2) { // 배치 구역 포켓몬 위치 바꾸기
		if (place1 == place2 || !validationPlaceNum(place1) || !validationPlaceNum(place2)) {
			System.out.println("<<<입력 오류>>>");
			return;
		} else if (isNullPlacePokemon(place1) && isNullPlacePokemon(place2)) {
			System.out.println("<<<둘 다 비어있는 배치 번호를 입력하셨습니다.>>>");
			return;
		} else if (placePokemon.get(place1) != null && placePokemon.get(place2) == null) { // place 1이 null이 아니면
			placePokemon.set(place2, placePokemon.get(place1));// place2 로 복사 후 plcae1은 null로
			placePokemon.set(place1, null);
			return;
		} else if (placePokemon.get(place1) == null && placePokemon.get(place2) != null) { // place 2이 null이 아니면
			placePokemon.set(place1, placePokemon.get(place2));// place1 로 복사 후 plcae2은 null로
			placePokemon.set(place2, null);
			return;
		} else if (placePokemon.get(place1).getPokemonNum() == placePokemon.get(place2).getPokemonNum()) {
			placePokemon.get(place1).setExp(placePokemon.get(place1).getExp() + 1);
			System.out.println("<<< " + placePokemon.get(place1).getName() + " 경헙치 증가! >>>");
			isRankup(place1);
			placePokemon.set(place2, null);
			return;
		} else {
			forReplace = placePokemon.get(place1);
			placePokemon.set(place1, placePokemon.get(place2));
			placePokemon.set(place2, forReplace);
			forReplace = null;
			System.out.println("<<<위치 변경 성공>>>");
			return;
		}
	}

	void place_sell(int placeNum) { // 배치 구역 포켓몬 팔기
		if (!validationPlaceNum(placeNum)) {
			System.out.println("<<<입력 오류>>>");
			return;
		} else if (isNullPlacePokemon(placeNum)) {
			System.out.println("<<<비어있습니다.>>>");
			return;
		} else {
			coin = coin + placePokemon.get(placeNum).getLv();
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			if (effectNum[placeNum] == 3) {
				coin++;
				System.out.println("<<< 아이템 효과로 코인 +1 >>>");
			}
			effectNum[placeNum] = 0;
			System.out.println("<<<판매 성공>>>");
			return;
		}
	}

	// -----------------------------------------------------------------------------------------------

	void shop() { // 상점 구역
		System.out.println("<<<상점구역 입니다.>>>");

		while (true) {
			System.out.println("상점 포켓몬 확인 (1), 포켓몬 구매 (2), 아이템 구매(3), 리롤(4), 포켓몬 동결(5), 아이템 동결(6) 뒤로가기(0)");
			System.out.println(">>");
			int chooseNum = sc.nextInt();
			switch (chooseNum) {
			case 1: {
				shop_show();
				break;
			}
			case 2: {
				if (coin < 3) {
					System.out.println("<<<코인이 부족합니다.>>>");
					break;
				} else {
					System.out.println("구매하고싶은 포켓몬 위치 번호를 입력");
					System.out.print(">>");
					int buyNum = sc.nextInt();
					System.out.println("배치할 포켓몬 위치 번호를 입력");
					System.out.print(">>");
					int placeNum = sc.nextInt();
					shop_pokemon_buy(buyNum - 1, placeNum - 1);
					break;
				}
			}
			case 3: {
				if (coin < 3) {
					System.out.println("<<<코인이 부족합니다.>>>");
					break;
				} else {
					System.out.println("구매하고싶은 아이템 위치 번호를 입력");
					System.out.print(">>");
					int buyNum = sc.nextInt();
					System.out.println("사용할 포켓몬 위치 번호를 입력");
					System.out.print(">>");
					int placeNum = sc.nextInt();
					shop_item_buy(buyNum - 1, placeNum - 1);
					break;
				}
			}
			case 4: {
				if (coin < 1) {
					System.out.println("<<<코인이 부족합니다.>>>");
					break;
				} else {
					shop_reroll();
					System.out.println("<<<리롤 성공!>>>");
					coin--;
					break;
				}
			}
			case 5: {
				System.out.println("얼리고싶은 포켓몬 위치 번호를 입력");
				System.out.print(">>");
				int placeNum = sc.nextInt();
				pokemon_frozen(placeNum - 1);
				break;
			}
			case 6: {
				System.out.println("얼리고싶은 아이템 위치 번호를 입력");
				System.out.print(">>");
				int placeNum = sc.nextInt();
				item_frozen(placeNum - 1);
				break;
			}
			case 0: {
				return;
			}
			default:
				System.out.println("<<<입력 오류>>>");
				break;
			}
		}
	}

	int shop_place_num() { // 상점 기물 수를 위한 함수 턴이 늘어 날수록 값이 커진다.
		if (turnNum == 1) {
			return 3;
		} else if (turnNum <= 3) {
			return 4;
		} else {
			return 5;
		}
	}

	boolean isNullShopPokemon(int placeNum) { // 상점 구역 포켓몬이 null인지 확인
		if (shopPokemon.get(placeNum) == null)
			return true;
		return false;
	}

	boolean isNullShopItem(int placeNum) {
		if (item.get(placeNum) == null)
			return true;
		return false;
	}

	boolean validationShopPokemonNum(int placeNum) { // 상점 구역 번호 유효성 검사
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}

	boolean validationShopItemNum(int placeNum) { // 상점 구역 번호 아이템 검사
		if (2 < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}

	void shop_show() { // 상점 포켓몬 확인
		int shop_pokemon_num = shop_place_num();
		System.out.println("<<<상점 구역 출력>>>");
		int i = 0;
		while (i < shop_pokemon_num) {
			if (shopPokemon.get(i) == null) {
				System.out.println((i + 1) + "번 비어있음");
			} else if (frozenPokemonNum[i]) {
				System.out.println((i + 1) + "번 얼림! 이름 : " + shopPokemon.get(i).getName() + " / 타입 : "
						+ shopPokemon.get(i).getType() + " / 등급 : " + shopPokemon.get(i).getGrade() + " / LV : "
						+ shopPokemon.get(i).getLv() + " / 체력 : " + shopPokemon.get(i).getHealth() + " / 공격력 : "
						+ shopPokemon.get(i).getPower() + " / 능력 : " + shopPokemon.get(i).getAbility());
			} else {
				System.out.println((i + 1) + "번 이름 : " + shopPokemon.get(i).getName() + " / 타입 : "
						+ shopPokemon.get(i).getType() + " / 등급 : " + shopPokemon.get(i).getGrade() + " / LV : "
						+ shopPokemon.get(i).getLv() + " / 체력 : " + shopPokemon.get(i).getHealth() + " / 공격력 : "
						+ shopPokemon.get(i).getPower() + " / 능력 : " + shopPokemon.get(i).getAbility());
			}
			i++;
		}
		i = 0;
		while (i < 2) {
			if (item.get(i) == null) {
				System.out.println((i + 1) + "번 비어있음");
			} else if (frozenItemNum[i]) {
				System.out.println((i + 1) + "번 얼림! 이름 : " + item.get(i).getName() + " / 등급 : " + item.get(i).getGrade()
						+ " / 능력 : " + item.get(i).getAbility());
			} else {
				System.out.println((i + 1) + "번 이름 : " + item.get(i).getName() + " / 등급 : " + item.get(i).getGrade()
						+ " / 능력 : " + item.get(i).getAbility());
			}
			i++;
		}
	}

	void shop_pokemon_buy(int shopPlaceNum, int myPlaceNum) { // 상점에서 포켓몬 구매
		if (!validationShopPokemonNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("입력 오류");
			return;
		} else if (isNullShopPokemon(shopPlaceNum)) { // 이미 구매한 상점 번호면 구매 불가
			System.out.println("<<<" + (shopPlaceNum + 1) + "번 상점이 비어있습니다.>>>");
			return;
		} else if (!isNullPlacePokemon(myPlaceNum)) { // 배치 구역에 포켓몬이 있으면 구매 불가
			if (shop_buy_expUp(shopPlaceNum, myPlaceNum)) {
				if (is3Grade(shopPlaceNum) && find53() != -1) {
					use53Ability(find53());
				}
				shopPokemon.set(shopPlaceNum, null);
				frozenPokemonNum[shopPlaceNum] = false;
				System.out.println("<<<구매 성공>>>");
				buyAbility(myPlaceNum);
				for (int i = 0; i < 5; i++) {
					if (placePokemon.get(i) != null) {
						if (placePokemon.get(i).getPokemonNum() == 53) {
							placePokemon.get(i).setPower(placePokemon.get(i).getPower() + 3);
							placePokemon.get(i).setHealth(placePokemon.get(i).getHealth() + 3);
						}
					}
				}
				coin = coin - 3;
				return;
			} else
				System.out.println("<<<" + (myPlaceNum + 1) + "번에 이미 있습니다.>>>");
			{
				return;
			}
		} else { // 배치구역에 포켓몬이 없으면 구매성공
			if (is3Grade(shopPlaceNum) && find53() != -1) {
				use53Ability(find53());
			}
			placePokemon.set(myPlaceNum, shopPokemon.get(shopPlaceNum));
			shopPokemon.set(shopPlaceNum, null);
			frozenPokemonNum[shopPlaceNum] = false;
			System.out.println("구매 성공");
			buyAbility(myPlaceNum);
			coin = coin - 3;
			return;
		}
	}

	void shop_item_buy(int shopPlaceNum, int myPlaceNum) { // 상점에서 아이템 구매
		if (!validationShopItemNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("입력 오류");
			return;
		} else if (isNullShopItem(shopPlaceNum)) { // 이미 구매한 상점 번호면 구매 불가
			System.out.println("<<<" + (shopPlaceNum + 1) + "번 상점이 비어있습니다.>>>");
			return;
		} else if (isNullPlacePokemon(myPlaceNum)) { // 배치 구역에 포켓몬이 없으면 구매 불가
			System.out.println("<<<" + (myPlaceNum + 1) + "번에 없습니다.>>>");
			return;
		} else {
			int itemNum = item.get(shopPlaceNum).getItemNum();
			int remainNum = checkRemainNum();
			if (itemNum == 8) {
				if (remainNum < 3) {
					System.out.println("<<< 아이템을 쓰기에는 포켓몬이 적습니다! >>>");
					return;
				}
			}
			if (itemNum == 3 && itemNum == 4 && itemNum == 6 && itemNum == 14) {
				if (remainNum < 2) {
					System.out.println("<<< 아이템을 쓰기에는 포켓몬이 적습니다! >>>");
					return;
				}
			}
			buyItemAbility(shopPlaceNum, myPlaceNum);
			item.set(shopPlaceNum, null);
			frozenItemNum[shopPlaceNum] = false;
			System.out.println("<<<구매 성공>>>");
			coin = coin - 3;
			return;
		}
	}

	boolean shop_buy_expUp(int shopPlaceNum, int myPlaceNum) {
		if (placePokemon.get(myPlaceNum) != null) { // 같은 포켓몬이면 경험치 증가
			if (shopPokemon.get(shopPlaceNum).getPokemonNum() == placePokemon.get(myPlaceNum).getPokemonNum()) {
				if (placePokemon.get(myPlaceNum).getLv() == 3) {
					System.out.println("<< 3레벨 입니다!! >>");
					return false;
				}
				placePokemon.get(myPlaceNum).setExp(placePokemon.get(myPlaceNum).getExp() + 1);
				System.out.println("<<<경험치 증가!>>>");
				isRankup(myPlaceNum);
				return true;
			}
		}
		return false;
	}

	void isRankup(int placeNum) { // 경험치가 3이면 레벨 업, 레벨 업 하면 2/2 증가
		if (placePokemon.get(placeNum).getExp() == 3 && placePokemon.get(placeNum).getLv() == 1) {
			System.out.println("<< " + placePokemon.get(placeNum).getName() + " 진화! >> ");
			int lvUpAbilitynum = setLvUpAbilityNum(placeNum);
			Pokemon p2 = s.getLV2Pokemon(placePokemon.get(placeNum).getPokemonNum());
			System.out.println(p2.getPokemonNum() + p2.getName());
			placePokemon.get(placeNum).setName(p2.getName());
			placePokemon.get(placeNum).setGrade(p2.getGrade());
			placePokemon.get(placeNum).setLv(2);
			placePokemon.get(placeNum).setExp(1);
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth() + 2 + lvUpAbilitynum);
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower() + 2 + lvUpAbilitynum);
			System.out.println("<<레벨 업!>>");
			return;
		} else if (placePokemon.get(placeNum).getExp() == 3 && placePokemon.get(placeNum).getLv() == 2) {
			System.out.println("<< " + placePokemon.get(placeNum).getName() + " 진화! >> ");
			int lvUpAbilitynum = setLvUpAbilityNum(placeNum);
			Pokemon p3 = s.getLV3Pokemon(placePokemon.get(placeNum).getPokemonNum());
			System.out.println(p3.getPokemonNum() + p3.getName());
			placePokemon.get(placeNum).setName(p3.getName());
			placePokemon.get(placeNum).setGrade(p3.getGrade());
			placePokemon.get(placeNum).setLv(3);
			placePokemon.get(placeNum).setExp(0);
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth() + 2 + lvUpAbilitynum);
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower() + 2 + lvUpAbilitynum);
			System.out.println("<<레벨 업!>>");
			return;
		}
	}

	int setLvUpAbilityNum(int placeNum) {
		int LvNum = placePokemon.get(placeNum).getLv();
		if (findLvUpAbility(placeNum)) {
			if (LvNum == 1) {
				System.out.println("<<< 추가 능력치 증가! (+1/+1) >>>");
				return 1;
			} else if (LvNum == 2) {
				System.out.println("<<< 추가 능력치 증가! (+2/+2) >>>");
				return 2;
			}
		}
		return 0;
	}

	boolean findLvUpAbility(int placeNum) {
		int pokemonNum = placePokemon.get(placeNum).getPokemonNum();
		if (pokemonNum == 38 || pokemonNum == 44 || pokemonNum == 50 || pokemonNum == 52 || pokemonNum == 55
				|| pokemonNum == 59 || pokemonNum == 60) {
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 능력 발동! >>>");
			return true;
		}
		return false;
	}

	void shop_reroll() { // 상점 포켓몬 리롤
		int i = shop_place_num() - 1;
		while (i >= 0) {
			if (frozenPokemonNum[i]) {

			} else {
				shopPokemon.set(i, s.getLV1Pokemon(randomPokemonNum()));
			}
			i--;
		}

		int j = 0;
		while (j < 2) {
			if (frozenItemNum[j]) {

			} else {
				item.set(j, t.getItem(randomItemNum()));
			}
			j++;
		}
	}

	int randomPokemonNum() { // 포켓몬 수 만큼의 난수
		if (turnNum <= 6) {
			return random.nextInt(turnNum * 10) + 1;
		} else {
			return random.nextInt(60) + 1;
		}
	}

	int randomItemNum() { // 아이템 수 만큼 난수
		if (turnNum <= 6) {
			return random.nextInt(turnNum * 4) + 1;
		} else {
			return random.nextInt(24) + 1;
		}
	}

	void pokemon_frozen(int placeNum) { // 상점 포켓몬 얼리기
		if (!validationShopPokemonNum(placeNum)) {
			System.out.println("<<<입력 오류>>>");
			return;
		} else if (isNullShopPokemon(placeNum)) {
			System.out.println("<<<비어있습니다.>>>");
			return;
		} else if (frozenPokemonNum[placeNum] == false) {
			frozenPokemonNum[placeNum] = true;
			System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 설정>>>");
			return;
		} else {
			frozenPokemonNum[placeNum] = false;
			System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 취소 설정>>>");
			return;
		}
	}

	void item_frozen(int placeNum) {
		if (!validationShopItemNum(placeNum)) {
			System.out.println("<<<입력 오류>>>");
			return;
		} else if (isNullShopItem(placeNum)) {
			System.out.println("<<<비어있습니다.>>>");
			return;
		} else if (frozenItemNum[placeNum] == false) {
			frozenItemNum[placeNum] = true;
			System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 설정>>>");
			return;
		} else {
			frozenPokemonNum[placeNum] = false;
			System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 취소 설정>>>");
			return;
		}
	}

	int checkRemainNum() { // 남아있는 배치 구역 포켓몬 수 반환
		int i = 0;
		for (Pokemon p : placePokemon) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}

	void buyAbility(int placeNum) { // 구매 시 능력
		int lv = placePokemon.get(placeNum).getLv();
		switch (placePokemon.get(placeNum).getPokemonNum()) {
		case 8: { // 구매 시 랜덤한 아군 1, 2, 3명 공격력 1 증가
			int remainNum = checkRemainNum();
			if ((lv == 1 && remainNum >= 1) || (lv == 2 && remainNum == 1) || (lv == 3 && remainNum == 1)) {
				while (true) {
					int i = random.nextInt(5);
					if (placePokemon.get(i) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
						placePowerAdjust(i, 1);
						return;
					}
				}
			} else if ((lv == 2 && remainNum >= 2) || (lv == 3 && remainNum == 2)) { // 구매 시 랜덤한 아군 2명 공격력 1 증가
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					while (i == j) {
						i = random.nextInt(5);
						j = random.nextInt(5);
					}
					if (placePokemon.get(i) != null && placePokemon.get(j) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
						placePowerAdjust(i, 1);
						placePowerAdjust(j, 1);
						return;
					}
				}
			} else if (remainNum >= 3) { // 구매 시 랜덤한 아군 3명 공격력 1 증가
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					int k = random.nextInt(5);
					while (i == j || j == k || i == k) {
						i = random.nextInt(5);
						j = random.nextInt(5);
						k = random.nextInt(5);
					}
					if (placePokemon.get(i) != null && placePokemon.get(j) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
						placePowerAdjust(i, 1);
						placePowerAdjust(j, 1);
						placePowerAdjust(k, 1);
						System.out.println("<<<능력 사용>>>");
						return;
					}
				}
			}
		}
		case 13: { // 구매 시 양 옆에 배치된 아군 공격력, 체력 2증가
			int abilityNum = 2;
			if (lv == 2) {
				abilityNum = 3;
			} else if (lv == 3) {
				abilityNum = 4;
			}
			if (placeNum == 0) {
				if (placePokemon.get(placeNum + 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
					placeHealthAdjust(placeNum + 1, abilityNum);
					placePowerAdjust(placeNum + 1, abilityNum);
					return;
				}
			}
			if (placeNum == 4) {
				if (placePokemon.get(placeNum - 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
					placeHealthAdjust(placeNum - 1, abilityNum);
					placePowerAdjust(placeNum - 1, abilityNum);
					return;
				}
			}
		}
		case 17: { // 구매 시 공격력이 가장 적은 아군에게 공격력 2배 증가
			int lowPowerPlaceNum = -1;
			int powerNumArr[] = { -1, -1, -1, -1, -1 };
			int min = -1;

			for (int i = 0; i < 5; i++) {
				if (placePokemon.get(i) != null) {
					powerNumArr[i] = placePokemon.get(i).getPower();
				}
				powerNumArr[i] = -1;
			}
			for (int i = 0; i < 5; i++) {
				if (powerNumArr[i] > -1) {
					if (min == -1) {
						min = powerNumArr[i];
						lowPowerPlaceNum = i;
					} else if (min > powerNumArr[i]) {
						min = powerNumArr[i];
						lowPowerPlaceNum = i;
					}
				}
			}
			int adjustNum = placePokemon.get(lowPowerPlaceNum).getPower();
			if (lv == 2) {
				adjustNum = adjustNum * 15 / 10;
			} else {
				adjustNum = adjustNum * 2;
			}
			System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
			placePowerAdjust(lowPowerPlaceNum, adjustNum);
			return;
		}
		case 33: { // 구매 시 양 옆에 배치된 아군 공격력 3 증가
			int abilityNum = 3;
			if (lv == 2) {
				abilityNum = 4;
			} else if (lv == 3) {
				abilityNum = 5;
			}
			if (placeNum == 0) {
				if (placePokemon.get(placeNum + 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
					placeHealthAdjust(placeNum + 1, abilityNum);
					placePowerAdjust(placeNum + 1, abilityNum);
					return;
				}
			}
			if (placeNum == 4) {
				if (placePokemon.get(placeNum - 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
					placeHealthAdjust(placeNum - 1, abilityNum);
					placePowerAdjust(placeNum - 1, abilityNum);
					return;
				}
			}
		}
		case 48: { // 구매 시 랜덤한 아군에게 공격력, 체력 3 증가
			int abilityNum = 3;
			if (lv == 2) {
				abilityNum = 4;
			} else if (lv == 3) {
				abilityNum = 5;
			}
			while (true) {
				int i = random.nextInt(5);
				if (placePokemon.get(i) != null) {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
					placeHealthAdjust(i, abilityNum);
					placePowerAdjust(i, abilityNum);
					return;
				}
			}
		}
		default: {
			return;
		}
		}
	}

	void buyItemAbility(int shopPlaceNum, int placeNum) { // 아이템 구매 시 능력
		switch (item.get(shopPlaceNum).getItemNum()) {
		case 1: { // 공격력 1 증가
			placePowerAdjust(placeNum, 1);
			break;
		}
		case 2: { // 체력 1 증가
			placeHealthAdjust(placeNum, 1);
			break;
		}
		case 3: { // 랜덤으로 2 기물에게 공격력 1 증가
			while (true) {
				int rNum1 = random.nextInt(5);
				int rNum2 = random.nextInt(5);
				if (placePokemon.get(rNum1) != null && placePokemon.get(rNum2) != null && rNum1 != rNum2) {
					placePowerAdjust(rNum1, 1);
					placePowerAdjust(rNum2, 1);
					break;
				}
			}
			break;

		}
		case 4: { // 랜덤으로 2 기물에게 체력 1 증가
			while (true) {
				int rNum1 = random.nextInt(5);
				int rNum2 = random.nextInt(5);
				if (placePokemon.get(rNum1) != null && placePokemon.get(rNum2) != null && rNum1 != rNum2) {
					placeHealthAdjust(rNum1, 1);
					placeHealthAdjust(rNum2, 1);
					break;
				}
			}
			break;
		}
		case 5: { // 1/1 증가
			placePowerAdjust(placeNum, 1);
			placeHealthAdjust(placeNum, 1);
			break;
		}
		case 6: { // 랜덤으로 2 기물에게 1/1 증가
			while (true) {
				int rNum1 = random.nextInt(5);
				int rNum2 = random.nextInt(5);
				if (placePokemon.get(rNum1) != null && placePokemon.get(rNum2) != null && rNum1 != rNum2) {
					placeHealthAdjust(rNum1, 1);
					placePowerAdjust(rNum1, 1);
					placeHealthAdjust(rNum2, 1);
					placePowerAdjust(rNum2, 1);
					break;
				}
			}
			break;
		}
		case 7: { // 랜덤으로 1 기물에게 2/2 증가
			while (true) {
				int rNum1 = random.nextInt(5);
				if (placePokemon.get(rNum1) != null) {
					placeHealthAdjust(rNum1, 2);
					placePowerAdjust(rNum1, 2);
					break;
				}
			}
			break;
		}
		case 8: { // 랜덤으로 3 기물에게 공격력 1 증가
			while (true) {
				int rNum1 = random.nextInt(5);
				int rNum2 = random.nextInt(5);
				int rNum3 = random.nextInt(5);
				if (placePokemon.get(rNum1) != null && placePokemon.get(rNum2) != null
						&& placePokemon.get(rNum3) != null && rNum1 != rNum2 && rNum1 != rNum3 && rNum3 != rNum2) {
					placeHealthAdjust(rNum1, 1);
					placeHealthAdjust(rNum2, 1);
					placeHealthAdjust(rNum3, 1);
					break;
				}
			}
			break;
		}
		case 9: { // 사용할 기물 양 옆 기물의 공격력 2을 가져 옵니다.
			if (placeNum == 0) {
				if (!isNullPlacePokemon(placeNum + 1)) {
					placePowerAdjust(placeNum + 1, -2);
					placePowerAdjust(placeNum, 2);
					break;
				}
			} else if (placeNum == 4) {
				if (!isNullPlacePokemon(placeNum - 1)) {
					placePowerAdjust(placeNum - 1, -2);
					placePowerAdjust(placeNum, 2);
					break;
				}
			} else {
				if (!isNullPlacePokemon(placeNum + 1)) {
					placePowerAdjust(placeNum + 1, -2);
					placePowerAdjust(placeNum, 2);
				}
				if (!isNullPlacePokemon(placeNum - 1)) {
					placePowerAdjust(placeNum - 1, -2);
					placePowerAdjust(placeNum, 2);
				}
				break;
			}
		}
		case 10: { // 사용한 포켓몬이 대결에서 살아남으면 경험치 1 증가
			effectNum[placeNum] = 2;
			break;
		}
		case 11: { // 처음 맞은 공격 무시
			effectNum[placeNum] = 1;
			break;
		}
		case 12: { // 코인 + 1
			this.coin++;
			break;
		}
		case 13: { // 체력 2 증가
			placeHealthAdjust(placeNum, 2);
			break;
		}
		case 14: { // 랜덤으로 2 기물에게 2/2 증가
			while (true) {
				int rNum1 = random.nextInt(5);
				int rNum2 = random.nextInt(5);
				if (placePokemon.get(rNum1) != null && placePokemon.get(rNum2) != null && rNum1 != rNum2) {
					placeHealthAdjust(rNum1, 2);
					placePowerAdjust(rNum1, 2);
					placeHealthAdjust(rNum2, 2);
					placePowerAdjust(rNum2, 2);
					break;
				}
			}
			break;
		}
		case 15: { // 사용 기물과 양 옆의 기물에 1/2 증가
			placePowerAdjust(placeNum, 1);
			placeHealthAdjust(placeNum, 2);
			if (placeNum == 0) {
				if (!isNullPlacePokemon(placeNum + 1)) {
					placePowerAdjust(placeNum + 1, 1);
					placeHealthAdjust(placeNum + 1, 2);
					break;
				}
			} else if (placeNum == 4) {
				if (!isNullPlacePokemon(placeNum - 1)) {
					placePowerAdjust(placeNum - 1, 1);
					placeHealthAdjust(placeNum - 1, 2);
					break;
				}
			} else {
				if (!isNullPlacePokemon(placeNum + 1)) {
					placePowerAdjust(placeNum + 1, 1);
					placeHealthAdjust(placeNum + 1, 2);
				}
				if (!isNullPlacePokemon(placeNum - 1)) {
					placePowerAdjust(placeNum - 1, 1);
					placeHealthAdjust(placeNum - 1, 2);
				}
			}
			break;
		}
		case 16: { // 사용 효과는 없지만 이 아이템이 적용된 기물 판매 시 판매코인 1 증가
			effectNum[placeNum] = 3;
			break;
		}
		case 17: { // 2/2 증가
			placePowerAdjust(placeNum, 2);
			placeHealthAdjust(placeNum, 2);
			break;
		}
		case 18: { // 랜덤으로 1기물에게 경험치 1 증가
			while (true) {
				int rNum = random.nextInt(5);
				if (!isNullPlacePokemon(rNum)) {
					placePokemon.get(rNum).setExp(placePokemon.get(rNum).getExp() + 1);
					isRankup(placeNum);
					break;
				}
			}
			break;
		}
		case 19: { // 50% 확률로 코인 2 증가
			int rNum = random.nextInt(2);
			if (rNum == 1) {
				System.out.println("<<< 성공! >>>");
				this.coin++;
				this.coin++;
				break;
			} else {
				System.out.println("<<< 실패! >>> ");
				break;
			}
		}
		case 20: { // 공격력 3 증가
			placePowerAdjust(placeNum, 3);
			break;
		}
		case 21: { // 경험치 1 증가
			placePokemon.get(placeNum).setExp(placePokemon.get(placeNum).getExp() + 1);
			break;
		}
		case 22: { // 3/3 증가
			placePowerAdjust(placeNum, 3);
			placeHealthAdjust(placeNum, 3);
			break;
		}
		case 23: { // 코인 + 2
			this.coin++;
			this.coin++;
			break;
		}
		case 24: { // 대결에 승리 시 코인 2, 패배 시 1 증가 000000000000
			item24Effect = true;
			break;
		}
		default: {
			break;
		}
		}

	}

	boolean is3Grade(int shopPlaceNum) { // 구매한 포켓몬이 3등급 이면 true
		if (shopPokemon.get(shopPlaceNum).getGrade() == 3) {
			return true;
		}
		return false;
	}

	int find53() {
		for (int i = 0; i < 5; i++) {
			if (placePokemon.get(i).getPokemonNum() == 53) {
				return i;
			}
		}
		return -1;
	}

	void use53Ability(int placeNum) { // 3등급 이하 포켓몬 구매 시 3/3 증가
		int lv = placePokemon.get(placeNum).getLv();
		int abilityNum = 3;
		if (lv == 2) {
			abilityNum = 4;
		} else if (lv == 3) {
			abilityNum = 5;
		}
		System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
		placePowerAdjust(placeNum, abilityNum);
		placeHealthAdjust(placeNum, abilityNum);
	}

	void sellAbility(int placeNum) { // 판매 시 능력
		int lv = placePokemon.get(placeNum).getLv();
		switch (placePokemon.get(placeNum).getPokemonNum()) {
		case 7: { // 판매 시 아군 체력 1 증가
			int adjustNum = lv;
			System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
			for (int i = 1; i < 5; i++) {
				if (placePokemon.get(i) != null && i + 1 != placeNum) {
					placeHealthAdjust(i, adjustNum);
				}
			}
			return;
		}
		case 14: {// 판매 시 랜덤한 아군 1,2,3에게 버섯 투여
			int remainNum = checkRemainNum();
			if ((lv == 1 && remainNum >= 1) || (lv == 2 && remainNum == 1) || (lv == 3 && remainNum == 1)) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
				while (true) {
					int i = random.nextInt(5);
					if (placePokemon.get(i) != null && i + 1 != placeNum) {
						effectNum[i] = 1; // 효과 1은 버섯 효과
						System.out.println("<<<" + placePokemon.get(i).getName() + " 버섯 효과! >>>");
						return;
					}
				}
			} else if ((lv == 2 && remainNum >= 2) || (lv == 3 && remainNum == 2)) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					if (placePokemon.get(i) != null && placePokemon.get(j) != null && i != j && i + 1 != placeNum) {
						effectNum[i] = 1;
						System.out.println("<<<" + placePokemon.get(i).getName() + " 버섯 효과! >>>");
						effectNum[j] = 1;
						System.out.println("<<<" + placePokemon.get(j).getName() + " 버섯 효과! >>>");
						return;
					}
				}
			} else {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					int k = random.nextInt(5);
					if (placePokemon.get(i) != null && placePokemon.get(j) != null && placePokemon.get(k) != null
							&& i != j && i != k && k != j && i + 1 != placeNum) {
						effectNum[i] = 1;
						System.out.println("<<<" + placePokemon.get(i).getName() + " 버섯 효과! >>>");
						effectNum[j] = 1;
						System.out.println("<<<" + placePokemon.get(j).getName() + " 버섯 효과! >>>");
						effectNum[k] = 1;
						System.out.println("<<<" + placePokemon.get(k).getName() + " 버섯 효과! >>>");
						return;
					}
				}
			}

		}
		case 49: { // 판매 시 앞에 있는 아군에게 공격력,체력의 50%, 70%, 100%만큼 증가
			if (placeNum == 4) {
				return;
			} else if (placePokemon.get(placeNum + 1) != null) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " 능력 사용 >>>");
				if (lv == 1) {
					int adjustNum = placePokemon.get(placeNum).getHealth() / 2;
					placeHealthAdjust(placeNum + 1, adjustNum);
					placePowerAdjust(placeNum + 1, adjustNum);
				} else if (lv == 2) {
					int adjustNum = placePokemon.get(placeNum).getHealth() / 10 * 7;
					placeHealthAdjust(placeNum + 1, adjustNum);
					placePowerAdjust(placeNum + 1, adjustNum);
				} else {
					int adjustNum = placePokemon.get(placeNum).getHealth();
					placeHealthAdjust(placeNum + 1, adjustNum);
					placePowerAdjust(placeNum + 1, adjustNum);
				}
				return;
			}
		}
		default: {
			return;
		}
		}
	}

	void placeHealthAdjust(int placeNum, int adjustNum) { // 아군 체력 조정
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 체력 조정 " + adjustNum + " >>>");
		}
	}

	void placePowerAdjust(int placeNum, int adjustNum) { // 아군 공격력 조정
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 공격력 조정 " + adjustNum + " >>>");
		}
	}

}

