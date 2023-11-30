package Placement;

import java.util.LinkedList;

public class setItem {
	private LinkedList<item> item;

	public setItem() {
		this.item = new LinkedList<>();
		item_info();
	}

	void item_info() {
		item.add(new item(1, 1, "라즈열매", "공격력 1 증가"));
		item.add(new item(2, 1, "행복의알", "체력 1 증가"));
		item.add(new item(3, 1, "태양의돌", "랜덤으로 2 기물에게 공격력 1 증가"));
		item.add(new item(4, 1, "별의모래", "랜덤으로 2 기물에게 체력 1 증가"));
		item.add(new item(5, 2, "나나열매", "1/1 증가"));
		item.add(new item(6, 2, "상처약", "랜덤으로 2 기물에게 1/1 증가"));
		item.add(new item(7, 2, "포핀", "랜덤으로 1 기물에게 2/2 증가"));
		item.add(new item(8, 2, "신오의돌", "랜덤으로 3 기물에게 공격력 1 증가"));
		item.add(new item(9, 3, "파인열매", "사용할 기물 양 옆 기물의 공격력 2을 가져 옵니다."));
		item.add(new item(10, 3, "좋은상처약", "사용한 포켓몬이 대결에서 살아남으면 경험치 1 증가"));
		item.add(new item(11, 3, "용의비늘", "처음 맞은 공격 무시"));
		item.add(new item(12, 3, "사탕", "코인 + 5"));
		item.add(new item(13, 4, "황금라즈열매", "체력 2 증가"));
		item.add(new item(14, 4, "고급상처약", "랜덤으로 2 기물에게 2/2 증가"));
		item.add(new item(15, 4, "선물", "사용 기물과 양 옆의 기물에 1/2 증가"));
		item.add(new item(16, 4, "왕의징표석", "사용 효과는 없지만 이 아이템이 적용된 기물 판매 시 판매코인 1 증가"));
		item.add(new item(17, 5, "은파인열매", "2/2 증가"));
		item.add(new item(18, 5, "별의조각", "랜덤으로 1기물에게 경험치 1 증가"));
		item.add(new item(19, 5, "지가르데큐브", "50% 확률로 코인 7 증가"));
		item.add(new item(20, 5, "운석", "공격력 3 증가"));
		item.add(new item(21, 6, "이상한사탕", "경험치 1 증가"));
		item.add(new item(22, 6, "풀회복약", "3/3 증가"));
		item.add(new item(23, 6, "하나의돌", "코인 + 6"));
		item.add(new item(24, 6, "이상한사탕XL", "대결에 승리 시 코인 2, 패배 시 1 증가"));
	}

	item getItem(int itemNum) {
		for (item i : item) {
			if (i.getItemNum() == itemNum) {
				return i;
			}
		}
		return null;
	}
}
