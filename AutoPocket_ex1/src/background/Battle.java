package background;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Battle {
	Scanner sc = new Scanner(System.in);
	private LinkedList<Pokemon> friendly; // �븘援� 由ъ뒪�듃
	private LinkedList<Pokemon> enemy; // �쟻援� 由ъ뒪�듃
	private LinkedList<Pokemon> forreplace; //
	Random random = new Random();
	int turnNum; // �꽩 �닔
	int friendlyMaxPalceNum = 5; // �븘援� �궓�븘�엳�뒗 �닔
	int enemyMaxPalceNum = 5; // �쟻援� �궓�븘�엳�뒗 �닔

	boolean friendlyFirstHit38 = true; // 38踰� �룷耳볥が�씠 泥섏쓬 留욎븯�뒗吏� �솗�씤 true�씠硫� �븞 留욎쓬
	boolean friendlyFirstHit[] = { false, false, false, false, false }; // 踰꾩꽢 �슚怨쇱슜 泥섏쓬�쑝濡� 留욎븯�뒗吏� �솗�씤 1�씠硫� �븞 留욎쓬
	int findEffect[] = { 0, 0, 0, 0, 0 }; // 踰꾩꽢 �슚怨� 李얘린�슜
	boolean replaceFirstHit[] = { false, false, false, false, false }; // 踰꾩꽢�슚怨� �옄由� 諛붽씀湲곗슜

	boolean enemyFirstHit38 = true; // 38踰� �룷耳볥が�씠 泥섏쓬 留욎븯�뒗吏� �솗�씤 true�씠硫� �븞 留욎쓬
	boolean enemyFirstHit[] = { false, false, false, false, false }; // 踰꾩꽢 �슚怨� �슜 泥섏쓬�쑝濡� 留욎븯�뒗吏� �솗�씤 1�씠硫� �븞 留욎쓬

	int friendlyHP45 = 0; // 45踰� �룷耳볥が 泥대젰 ���옣 �뵾�빐�웾 20% �룎�젮以섏빞�븿
	int enemyHP45 = 0;

	int replaceHitNum[] = { -1, -1, -1, -1, -1 };
	int whoHitEnemy[] = { -1, -1, -1, -1, -1 }; // �쟻援� 怨듦꺽�븳 �븘援� �쐞移� ���옣
	int whoHitFriendly[] = { -1, -1, -1, -1, -1 }; // �븘援� 怨듦꺽�븳 �쟻援� �쐞移� ���옣

	int replacePoisonNum[] = { 0, 0, 0, 0, 0 };
	int friendlyPoisonNum[] = { 0, 0, 0, 0, 0 }; // �븘援� �룆 �뵾�빐�웾 ���옣
	int enemyPoisonNum[] = { 0, 0, 0, 0, 0 }; // �쟻援� �룆 �뵾�빐�웾 ���옣

	Pokemon dan = new Pokemon(61, 2, 1, "�떒�뜲湲�", "踰뚮젅", 1, 2, 2, "湲곗젅 �떆 �뒫�젰 �뾾�뒗 2/2�떒�뜲湲� 2 �냼�솚");
	Pokemon mang = new Pokemon(62, 1, 1, "留앺궎", "寃⑺닾", 2, 3, 2, "湲곗젅 �떆 �뒫�젰 �뾾�뒗 留앺궎 �냼�솚");
	Pokemon mo = new Pokemon(63, 1, 1, "紐⑤떎�뵾", "��", 3, 3, 3, "湲곗젅 �떆 泥대젰 2諛곗씤 �뒫�젰 �뾾�뒗 紐⑤떎�뵾 2 �냼�솚");

	public Battle(int turnNum, Pokemon f1, Pokemon f2, Pokemon f3, Pokemon f4, Pokemon f5, int findEffect[]) {
		this.friendly = new LinkedList<>();
		this.enemy = new LinkedList<>();
		this.forreplace = new LinkedList<>();
		this.findEffect = findEffect;
		this.turnNum = turnNum;
		forreplace.add(null);
		forreplace.add(null);
		forreplace.add(null);
		forreplace.add(null);
		forreplace.add(null);
		friendly.add(0, null);
		friendly.add(1, null);
		friendly.add(2, null);
		friendly.add(3, null);
		friendly.add(4, null);
		friendly.set(0, f5);
		friendly.set(1, f4);
		friendly.set(2, f3);
		friendly.set(3, f2);
		friendly.set(4, f1);
		settingEnemy();
		findEffect();
	}

	int run() {
		friendlyGoFront(); // �븘援� 諛곗튂 議곗젙 ( �븵�쑝濡� �븸湲곌린 )
		enemyGoFront(); // �쟻援� 諛곗튂 議곗젙 ( �븵�쑝濡� �븸湲곌린 )
		settingFriendlySummonModapi(); // �븘援� 紐⑤떎�뵾 �뒫�젰 援ы쁽
		settingEnemySummonModapi(); // �쟻援� 紐⑤떎�뵾 �뒫�젰 援ы쁽
		show();
		next();
		useStartBattleAbility(); // ��寃� �떆�옉 �떆 �뒫�젰
		next();
		isDownFriendly(); // �븘援� 湲곗젅 �솗�씤
		next();
		isDownEnemy(); // �쟻援� 湲곗젅 �솗�씤
		next();
		show();
		next();
		if (winFriendly()) { // �듅由� �솗�씤
			return 1;
		} else if (winEnemy()) {
			return -1;
		}
		useFriendlyHitAbility(); // �븘援곗씠 怨듦꺽�뿉 留욎쓣 �떆 �뒫�젰 諛쒕룞
		next();
		useEnemyHitAbility(); // �쟻援곗씠 怨듦꺽�뿉 留욎쓣 �떆 �뒫�젰 諛쒕룞
		next();
		isDownFriendly();
		next();
		isDownEnemy();
		next();
		show();
		while (true) {
			if (winFriendly()) {
				return 1;
			} else if (winEnemy()) {
				return -1;
			}
			hitEnemy();
			next();
			hitFriendly();
			next();
			isDownFriendly();
			next();
			isDownEnemy();
			next();
			if (winFriendly()) {
				return 1;
			} else if (winEnemy()) {
				return -1;
			}
			show();
			next();
			friendlyAttackAbility(0);
			next();
			enemyAttackAbility(0);
			next();
			isDownFriendly();
			next();
			isDownEnemy();
			next();
			if (winFriendly()) {
				return 1;
			} else if (winEnemy()) {
				return -1;
			}
			show();
			next();
			useFriendlyHitAbility();
			next();
			useEnemyHitAbility();
			next();
			isDownFriendly();
			next();
			isDownEnemy();
			next();
			if (winFriendly()) {
				return 1;
			} else if (winEnemy()) {
				return -1;
			}
			show();
			next();
		}
	}

	void next() {
		System.out.println("�븘臾닿굅�굹 �엯�젰");
		System.out.print(">>");
		String s = sc.next();
	}

	void settingEnemy() {
		setEnemy e = new setEnemy(turnNum);
		enemy = e.settingEnemy();
	}

	void findEffect() { // 諛곗튂 �떒怨꾩쓽 �슚怨쇰�� 媛��졇�� 1�씠硫� 踰꾩꽢�슚怨� ���옣
		for (int i = 0; i < 5; i++) {
			if (findEffect[i] == 1) {
				friendlyFirstHit[i] = true;
				findEffect[i] = 0;
			} else {
				friendlyFirstHit[i] = false;
				findEffect[i] = 0;
			}
		}
	}

	void hitFriendly() {
		System.out.println("<<< �쟻援곗쓽 怨듦꺽!! >>>");
		int adjustNum = enemy.get(0).getPower();
		if (friendly.get(0).getPokemonNum() == 38 && friendlyFirstHit38) { // 泥섏쓬�쑝濡� 怨듦꺽�뿉 留욎쓣 �떆 臾댁떆 �빀�땲�떎
			System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
			System.out.println("<<< 怨듦꺽�씠 臾댁떆 �릺�뿀�뒿�땲�떎! >>>");
			friendlyFirstHit38 = false;
			return;
		}
		if (friendlyFirstHit[0]) {
			System.out.println("<<< 踰꾩꽢 �슚怨�! 怨듦꺽�씠 臾댁떆 �릺�뿀�뒿�땲�떎! >>>");
			friendlyFirstHit[0] = false;
			return;
		}
		if (friendly.get(0).getPokemonNum() == 32) { // 10% �솗瑜좊줈 �쟻援곗쓽 怨듦꺽�쓣 臾댁떆
			int rNum = random.nextInt(10);
			if (rNum == 7) {
				System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
				System.out.println("<<< 怨듦꺽�씠 臾댁떆 �릺�뿀�뒿�땲�떎! >>>");
				return;
			}
		}
		if (friendly.get(0).getPokemonNum() == 59) { // 諛쏅뒗 �뵾�빐媛� �젅諛섏씠 �맗�땲�떎.
			System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
			System.out.println("<<< �뵾�빐瑜� �젅諛섎쭔 諛쏆뒿�땲�떎! >>>");
			friendlyHealthAdjust(0, -(adjustNum / 2));
			System.out.println("<<< " + friendly.get(0).getName() + "�뿉寃� " + (adjustNum / 2) + "留뚰겮 �뵾�빐! >>> ");
			return;
		} else {
			friendlyHealthAdjust(0, -(adjustNum));
			System.out.println("<<< " + friendly.get(0).getName() + "�뿉寃� " + adjustNum + "留뚰겮 �뵾�빐! >>> ");
			if (friendlyPoisonNum[0] > 0) {
				System.out.println("<<< �룆 異붽� �뵾�빐! >>>");
				friendlyHealthAdjust(0, -1);
				friendlyPoisonNum[0]--;
			}
			return;
		}
	}

	void hitEnemy() {
		System.out.println("<<< �븘援곗쓽 怨듦꺽!! >>>");
		int adjustNum = friendly.get(0).getPower();
		if (enemy.get(0).getPokemonNum() == 38 && enemyFirstHit38) { // 泥섏쓬�쑝濡� 怨듦꺽�뿉 留욎쓣 �떆 臾댁떆 �빀�땲�떎
			System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
			System.out.println("<<< 怨듦꺽�씠 臾댁떆 �릺�뿀�뒿�땲�떎! >>>");
			enemyFirstHit38 = false;
			return;
		}
		if (enemyFirstHit[0]) {
			System.out.println("<<< 踰꾩꽢 �슚怨�! 怨듦꺽�씠 臾댁떆 �릺�뿀�뒿�땲�떎! >>>");
			enemyFirstHit[0] = false;
			return;
		}
		if (enemy.get(0).getPokemonNum() == 32) { // 10% �솗瑜좊줈 �쟻援곗쓽 怨듦꺽�쓣 臾댁떆
			int rNum = random.nextInt(10);
			if (rNum == 7) {
				System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
				System.out.println("<<< 怨듦꺽�씠 臾댁떆 �릺�뿀�뒿�땲�떎! >>>");
				return;
			}
		}
		if (enemy.get(0).getPokemonNum() == 59) { // 諛쏅뒗 �뵾�빐媛� �젅諛섏씠 �맗�땲�떎.
			System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
			System.out.println("<<< �뵾�빐瑜� �젅諛섎쭔 諛쏆뒿�땲�떎! >>>");
			enemyHealthAdjust(0, -(adjustNum / 2));
			System.out.println("<<< " + enemy.get(0).getName() + "�뿉寃� " + (adjustNum / 2) + "留뚰겮 �뵾�빐! >>> ");
			return;
		} else {
			enemyHealthAdjust(0, -(adjustNum));
			System.out.println("<<< " + enemy.get(0).getName() + "�뿉寃� " + adjustNum + "留뚰겮 �뵾�빐! >>> ");
			if (enemyPoisonNum[0] > 0) {
				System.out.println("<<< �룆 異붽� �뵾�빐! >>>");
				enemyHealthAdjust(0, -1);
				enemyPoisonNum[0]--;
				return;
			}
			return;
		}
	}

	void useStartBattleAbility() { // ��寃� �떆�옉 �떆 �뒫�젰 �궗�슜
		System.out.println("<<< ��寃� �떆�옉 �떆 �뒫�젰 諛쒕룞 >>>");
		int i = 0;
		for (Pokemon p : friendly) {
			if (p != null) {
				friendlyStartBattleAbility(i);
				i++;
			} else {
				i++;
			}
		}
		i = 0;
		for (Pokemon p : enemy) {
			if (p != null) {
				enemyStartBattleAbility(i);
				i++;
			} else {
				i++;
			}
		}
	}

	void useFriendlyHitAbility() { // 怨듦꺽�뿉 留욎쓣 �떆 �뒫�젰 �궗�슜
		System.out.println("<<< �븘援곗씠 怨듦꺽�뿉 留욎쓣 �떆 �뒫�젰 諛쒕룞 >>>");
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (whoHitFriendly[i] != -1) {
				friendlyHitAbility(i);
				whoHitFriendly[i] = -1;
			}
		}
	}

	void useEnemyHitAbility() { // 怨듦꺽�뿉 留욎쓣 �떆 �뒫�젰 �궗�슜
		System.out.println("<<< �쟻援곗씠 怨듦꺽�뿉 留욎쓣 �떆 �뒫�젰 諛쒕룞 >>>");
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (whoHitEnemy[i] != 0) {
				enemyHitAbility(i);
				whoHitEnemy[i] = -1;
			}
		}
	}

	int remainFriendlyNum() { // �븘援곗쓽 �쐞移섏� �븘援곗쓽 �궓�� �닔瑜� 諛섑솚
		int i = 0;
		for (Pokemon p : friendly) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}

	int remainEnemyNum() { // �쟻援곗쓽 �쐞移섏� �븘援곗쓽 �궓�� �닔瑜� 諛섑솚
		int i = 0;
		for (Pokemon p : enemy) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}

	void show() { // �쁽�젣 �긽�솴 蹂댁뿬二쇨린
		System.out.println("<<<�븘援� vs �쟻援�>>>");
		for (int i = remainFriendlyNum() - 1; i >= 0; i--) {
			if (friendly.get(i) != null) {
				System.out.print(" // " + (i + 1) + "踰� " + friendly.get(i).getName() + " 怨듦꺽�젰 : "
						+ friendly.get(i).getPower() + " 泥대젰 : " + friendly.get(i).getHealth());
			}

		}
		System.out.print(" // VS ");
		for (int i = 0; i < remainEnemyNum(); i++) {
			System.out.print(" // " + (i + 1) + "踰� " + enemy.get(i).getName() + " 怨듦꺽�젰 : " + enemy.get(i).getPower()
					+ " 泥대젰 : " + enemy.get(i).getHealth());
		}
		System.out.println("");
	}

	void friendlyGoFront() { // �븵�뿉 �룷耳볥が�씠 �뾾�쑝硫� �븵�쑝濡� 梨꾩슫�떎. �뮘媛� null�씠怨� 5�씠�긽�씠硫� �궘�젣 ------
		System.out.println("<<< �븘援� 諛곗튂 議곗젙 >>>");
		int j = 0;
		for (int i = 0; i < friendlyMaxPalceNum; i++) {
			if (friendly.get(i) != null) {
				forreplace.add(j, friendly.get(i));
				replaceHitNum[j] = whoHitFriendly[i];
				replacePoisonNum[j] = friendlyPoisonNum[i];
				replaceFirstHit[j] = friendlyFirstHit[i];
				j++;
				if (i > 4) {
					friendly.remove(i);
				} else {
					friendly.set(i, null);
				}
				whoHitFriendly[i] = -1;
				friendlyPoisonNum[i] = 0;
				friendlyFirstHit[i] = false;
			}
			whoHitFriendly[i] = -1;
			friendlyPoisonNum[i] = 0;
			friendlyFirstHit[i] = false;
		}
		for (int i = 0; i < j; i++) {
			friendly.set(i, forreplace.get(i));
			whoHitFriendly[i] = replaceHitNum[i];
			friendlyPoisonNum[i] = replacePoisonNum[i];
			friendlyFirstHit[i] = replaceFirstHit[i];
			forreplace.set(i, null);
		}
		friendlyMaxPalceNum = remainFriendlyNum();
	}

	void friendlyGoBack() { // �뮘濡� �븳 移몄뵫 誘몃，�떎.
		System.out.println("<<< �븘援� 諛곗튂 議곗젙 >>>");
		int number = remainFriendlyNum();
		enemy.add(0, null);
		for (int i = number; i > 0; i--) {
			whoHitFriendly[i] = whoHitFriendly[i - 1];
			friendlyPoisonNum[i] = friendlyPoisonNum[i - 1];
			friendlyFirstHit[i] = friendlyFirstHit[i - 1];
		}
		whoHitFriendly[0] = -1;
		friendlyPoisonNum[0] = 0;
		friendlyFirstHit[0] = false;
		friendlyMaxPalceNum++;
	}

	void enemyGoFront() { // �븵�뿉 �룷耳볥が�씠 �뾾�쑝硫� �븵�쑝濡� 梨꾩슫�떎. �뮘媛� null�씠怨� 5�씠�긽�씠硫� �궘�젣
		System.out.println("<<< �쟻援� 諛곗튂 議곗젙 >>>");
		int j = 0;
		for (int i = 0; i < enemyMaxPalceNum; i++) {
			if (enemy.get(i) != null) {
				forreplace.add(j, enemy.get(i));
				replaceHitNum[j] = whoHitEnemy[i];
				replacePoisonNum[j] = enemyPoisonNum[i];
				replaceFirstHit[j] = enemyFirstHit[i];
				j++;
				if (i > 4) {
					enemy.remove(i);
				} else {
					enemy.set(i, null);
				}
				whoHitEnemy[i] = -1;
				enemyPoisonNum[i] = 0;
				enemyFirstHit[i] = false;
			}
			whoHitEnemy[i] = -1;
			enemyPoisonNum[i] = 0;
			enemyFirstHit[i] = false;
		}
		for (int i = 0; i <= j; i++) {
			enemy.set(i, forreplace.get(i));
			whoHitEnemy[i] = replaceHitNum[i];
			enemyPoisonNum[i] = replacePoisonNum[i];
			enemyFirstHit[i] = replaceFirstHit[i];
			forreplace.set(i, null);
		}
		enemyMaxPalceNum = remainEnemyNum();
	}

	void enemyGoBack() { // �뮘濡� �븳 移몄뵫 誘몃，�떎.
		System.out.println("<<< �쟻援� 諛곗튂 議곗젙 >>>");
		int number = remainEnemyNum();
		enemy.add(0, null);
		for (int i = number; i > 0; i--) {
			whoHitEnemy[i] = whoHitEnemy[i - 1];
			enemyPoisonNum[i] = enemyPoisonNum[i - 1];
			enemyFirstHit[i] = enemyFirstHit[i - 1];
		}
		whoHitEnemy[0] = -1;
		enemyPoisonNum[0] = 0;
		enemyFirstHit[0] = false;
		enemyMaxPalceNum++;
	}

	boolean winEnemy() {
		if (remainFriendlyNum() == 0) {
			System.out.println("<<< �쟻援� �듅由�! >>>");
			return true;
		}
		return false;
	}

	boolean winFriendly() {
		if (remainEnemyNum() == 0) {
			System.out.println("<<< �븘援� �듅由�! >>>");
			return true;
		}
		return false;
	}

	void isDownFriendly() { // 湲곗젅 �떆 �뾾�븻�떎
		System.out.println("<<< �븘援� 湲곗젅 �솗�씤 >>>");
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getHealth() <= 0) {
					System.out.println("<<< " + friendly.get(i).getName() + " 湲곗젅! >>>");
					friendlyFrontDownAbility(i);
					friendlyDownAbility(i);
					friendlyGoFront();
				}
			}

		}
	}

	void isDownEnemy() { // 湲곗젅 �떆 �뾾�븻�떎
		System.out.println("<<< �쟻援� 湲곗젅 �솗�씤 >>>");
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getHealth() <= 0) {
					System.out.println("<<< " + enemy.get(i).getName() + " 湲곗젅! >>>");
					enemyFrontDownAbility(i);
					enemyDownAbility(i);
					enemyGoFront();
				}
			}

		}
	}

	void friendlyHealthAdjust(int placeNum, int adjustNum) { // �븘援� 泥대젰 議곗젙
		if (friendly.get(placeNum) != null) {
			if (placeNum < 4 && friendly.get(placeNum + 1) != null) {
				if (friendly.get(placeNum + 1).getPokemonNum() == 42) {
					System.out.println("<<< " + friendly.get(placeNum + 1).getName() + " �뒫�젰 �궗�슜! >>>");
					friendly.get(placeNum).setHealth(friendly.get(placeNum).getHealth() + (adjustNum / 10 * 8));
					friendly.get(placeNum + 1).setHealth(friendly.get(placeNum + 1).getHealth() + (adjustNum / 10 * 2));
					System.out.println("<<< " + friendly.get(placeNum + 1).getName() + " 媛� "
							+ (friendly.get(placeNum).getHealth() + adjustNum) / 10 * 2 + " 留뚰겮 ���떊 留욎븘以ъ뒿�땲�떎! >>>");
					return;
				}
			}
			friendly.get(placeNum).setHealth(friendly.get(placeNum).getHealth() + adjustNum);
			return;
		}
	}

	void friendlyPowerAdjust(int placeNum, int adjustNum) { // �븘援� 怨듦꺽�젰 議곗젙
		if (friendly.get(placeNum) != null)
			friendly.get(placeNum).setPower(friendly.get(placeNum).getPower() + adjustNum);
	}

	void enemyHealthAdjust(int placeNum, int adjustNum) { // �쟻援� 泥대젰 議곗젙
		if (enemy.get(placeNum) != null) {
			if (placeNum < 4 && enemy.get(placeNum + 1) != null) {
				if (enemy.get(placeNum + 1).getPokemonNum() == 42) { // �븵�뿉 �엳�뒗 �븘援� 怨듦꺽�뿉 留욎쓣 �떆 // 20% ���떊 留욎븘以띾땲�떎.
					System.out.println("<<< " + enemy.get(placeNum + 1).getName() + " �뒫�젰 �궗�슜! >>>");
					enemy.get(placeNum).setHealth(enemy.get(placeNum).getHealth() + (adjustNum / 10 * 8));
					enemy.get(placeNum + 1).setHealth(enemy.get(placeNum + 1).getHealth() + (adjustNum / 10 * 2));
					System.out.println("<<< " + enemy.get(placeNum + 1).getName() + " 媛� "
							+ (enemy.get(placeNum).getHealth() + adjustNum) / 10 * 2 + " 留뚰겮 ���떊 留욎븘以ъ뒿�땲�떎! >>>");
					return;
				}
			}
			enemy.get(placeNum).setHealth(enemy.get(placeNum).getHealth() + adjustNum);
			return;
		}
	}

	void enemyPowerAdjust(int placeNum, int adjustNum) { // �쟻援� 怨듦꺽�젰 議곗젙
		if (enemy.get(placeNum) != null)
			enemy.get(placeNum).setPower(enemy.get(placeNum).getPower() + adjustNum);
	}

	void findHitEnemy(int hit, int who) { // �쟻援곗쓣 �븣由� �븘援곗쓽 �쐞移섎�� ���옣
		if (whoHitEnemy[hit] == -1) {
			whoHitEnemy[hit] = who;
		}
	}

	void findHitfriendly(int hit, int who) {// �븘援곗쓣 �븣由� �쟻援곗쓽 �쐞移섎�� ���옣
		if (whoHitFriendly[hit] == -1) {
			whoHitFriendly[hit] = who;
		}
	}

	void friendlyAbillity45() {// 45踰� �뒫�젰�쓣 �쐞�빐 HP ���옣
		for (Pokemon p : friendly) {
			if (p.getPokemonNum() == 45) {
				friendlyHP45 = p.getHealth();
				return;
			}
		}
	}

	void enemyAbillity45() {// 45踰� �뒫�젰�쓣 �쐞�빐 HP ���옣
		for (Pokemon p : enemy) {
			if (p.getPokemonNum() == 45) {
				enemyHP45 = p.getHealth();
				return;
			}
		}
	}

	void settingFriendlySummonModapi() { // 紐⑤떎�뵾 �뒫�젰 �꽭�똿
		System.out.println("<<< �븘援� 紐⑤떎�뵾 �뒫�젰 援ы쁽 >>>");
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i).getPokemonNum() == 28) {
				mo.setHealth(friendly.get(i).getHealth() * 2);
			}
		}
		mo.setHealth(3);
	}

	void settingEnemySummonModapi() { // 紐⑤떎�뵾 �뒫�젰 �꽭�똿
		System.out.println("<<< �쟻援� 紐⑤떎�뵾 �뒫�젰 援ы쁽 >>>");
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i).getPokemonNum() == 28) {
				mo.setHealth(enemy.get(i).getHealth() * 2);
			}
		}
		mo.setHealth(3);
	}

	// ------------ �뒫�젰 �븿�닔 -----------------

	int findFriendlylv(int pokemonNum) {
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == pokemonNum) {
					return friendly.get(i).getLv();
				}
			}
		}
		return -1;
	}

	void friendlyStartBattleAbility(int placeNum) { // ��寃� �떆�옉 �떆 �뒫�젰
		int lv54 = findFriendlylv(54);
		int lv = friendly.get(placeNum).getLv();
		switch (friendly.get(placeNum).getPokemonNum()) {
		case 3: { // ��寃� �떆�옉 �떆 �쟻援� �옖�뜡 �븯�굹�뿉寃� 2, 3, 4 �뵾�빐
			int abilityNum = lv + 1;
			while (true) {
				int rNum = random.nextInt(remainEnemyNum());
				if (enemy.get(rNum) != null) {
					System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					System.out.println("<<< " + friendly.get(placeNum).getAbility() + " >>>");
					if (friendlyFindPokemon(54)) {
						if (rNum - 1 >= 0 && enemy.get(rNum - 1) != null) {
							if (enemy.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(rNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								rNum -= 1;
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							}
						}
						System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyHealthAdjust(rNum, -(abilityNum + lv54));
						findHitEnemy(rNum, placeNum);
						System.out
								.println("<<< " + enemy.get(rNum).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
					} else {
						if (rNum - 1 >= 0 && enemy.get(rNum - 1) != null) {
							if (enemy.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(rNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								rNum -= 1;
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							}
						}
						enemyHealthAdjust(rNum, -abilityNum);
						findHitEnemy(rNum, placeNum);
						System.out.println("<<< " + enemy.get(rNum).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
					}
					return;
				}
			}
		}
		case 10: { // ��寃� �떆�옉 �떆 媛��옣 �뮘�뿉 �엳�뒗 �쟻援� 1, 2, 3 �뵾�빐
			int enemyPlaceNum = remainEnemyNum() - 1;
			int abilityNum = lv;
			if (enemyPlaceNum < 0) {
				return;
			}
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			if (friendlyFindPokemon(54)) {
				if (enemyPlaceNum - 1 >= 0 && enemy.get(enemyPlaceNum - 1) != null) {
					if (enemy.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(enemyPlaceNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
					}
				}
				System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
				enemyHealthAdjust(enemyPlaceNum, -(abilityNum + lv54));
				findHitEnemy(enemyPlaceNum, placeNum);
				System.out.println(
						"<<< " + enemy.get(enemyPlaceNum).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
			} else {
				if (enemyPlaceNum - 1 >= 0 && enemy.get(enemyPlaceNum - 1) != null) {
					if (enemy.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(enemyPlaceNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
					}
				}
				enemyHealthAdjust(enemyPlaceNum, -abilityNum);
				findHitEnemy(enemyPlaceNum, placeNum);
				System.out.println("<<< " + enemy.get(enemyPlaceNum).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
			}
			return;

		}
		case 12: { // ��寃� �떆�옉 �떆 紐⑤뱺 �쟻援� 1, 2, 3 �뵾�빐
			int enemyPlaceNum = remainEnemyNum();
			int abilityNum = lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜>>>");
			for (int i = 0; i < enemyPlaceNum; i++) {
				if (friendlyFindPokemon(54)) {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52 || i - 1 >= 0) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
							enemyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitEnemy(enemyPlaceNum - 1, placeNum);
							System.out.println(
									"<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
						}
					} else {
						System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyHealthAdjust(i, -(abilityNum + lv54));
						findHitEnemy(enemyPlaceNum, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
					}
				} else {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							enemyHealthAdjust(i - 1, -abilityNum);
							findHitEnemy(enemyPlaceNum - 1, placeNum);
							System.out.println("<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
						}
					} else {
						enemyHealthAdjust(i, -abilityNum);
						findHitEnemy(enemyPlaceNum, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
					}
				}
			}
			return;
		}
		case 23: { // ��寃� �떆�옉 �떆 �븵�뿉 �엳�뒗 �븘援곗뿉寃� 泥대젰 50%, 70%, 100% 留뚰겮 利앷�
			if (placeNum != 0) {
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = friendly.get(placeNum).getHealth() / 2;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getHealth() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getHealth();
				}
				friendlyHealthAdjust(placeNum - 1, adjustNum);
				System.out
						.println("<<< " + friendly.get(placeNum - 1).getName() + "�뿉寃�  泥대젰 " + adjustNum + "留뚰겮 利앷�! >>>");
				return;
			}
			return;
		}
		case 26: { // ��寃� �떆�옉 �떆 �븵�뿉 �엳�뒗 �븘援곗뿉寃� 怨듦꺽�젰 50%, 70%, 100% 留뚰겮 異붽�
			int lv25 = findFriendlylv(25);
			if (placeNum != 0) {
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = friendly.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getPower() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getPower();
				}
				friendlyPowerAdjust(placeNum - 1, adjustNum);
				System.out
						.println("<<< " + friendly.get(placeNum - 1).getName() + "�뿉寃� 怨듦꺽�젰 " + adjustNum + "留뚰겮 利앷�! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(placeNum - 1, lv25);
					System.out
							.println("<<< " + friendly.get(placeNum - 1).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			return;
		}
		case 29: {// ��寃� �떆�옉 �떆 媛��옣 �뮘�뿉 �엳�뒗 �쟻援곗뿉寃� 1, 2, 3 �룆 �뵾�빐
			int abilityNum = lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			int enemyPlaceNum = remainEnemyNum() - 1;
			if (enemyPlaceNum - 1 >= 0 && enemy.get(enemyPlaceNum - 1) != null) {
				if (enemy.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
					System.out.println("<<< " + enemy.get(enemyPlaceNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
					enemyPlaceNum -= 1;
					System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
				}
			}
			friendlyPoisonNum[enemyPlaceNum] += abilityNum;
			System.out.println("<<< " + enemy.get(enemyPlaceNum).getName() + "�뿉寃� " + abilityNum + " �룆 �뵾�빐! >>>");
			return;
		}
		case 31: { // ��寃� �떆�옉 �떆 紐⑤뱺 �쑀�떅 1, 2, 3 �뵾�빐
			int abilityNum = lv;
			int friendlyNum = remainFriendlyNum();
			int enemyNum = remainEnemyNum();
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			for (int i = 0; i < friendlyNum; i++) {
				if (i - 1 >= 0 && friendly.get(i - 1) != null) {
					if (friendly.get(i - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
						friendlyHealthAdjust(i - 1, -abilityNum);
						System.out.println("<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
						findHitfriendly(i - 1, -1);
					}
				} else {
					friendlyHealthAdjust(i, -abilityNum);
					System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
					findHitfriendly(i, -1);
				}

			}
			if (friendlyFindPokemon(54)) {
				System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < enemyNum; i++) {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							enemyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitEnemy(i - 1, placeNum);
							System.out.println(
									"<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
						}
					} else {
						enemyHealthAdjust(i, -(lv54 + abilityNum));
						findHitEnemy(i, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
					}
				}
			} else {
				for (int i = 0; i < enemyNum; i++) {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							enemyHealthAdjust(i - 1, -abilityNum);
							findHitEnemy(i - 1, placeNum);
							System.out.println("<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
						}
					} else {
						enemyHealthAdjust(i, -abilityNum);
						findHitEnemy(i, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
					}
				}
			}
			return;
		}
		case 37: { // ��寃� �떆�옉 �떆 紐⑤뱺 �븘援곗쓽 泥대젰 30%, 40%, 50% 留뚰겮 利앷�
			int friendlyNum = remainFriendlyNum();
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			int adjustNum = friendly.get(placeNum).getHealth() * 3 / 10;
			if (lv == 2) {
				adjustNum = friendly.get(placeNum).getHealth() * 4 / 10;
			} else if (lv == 3) {
				adjustNum = friendly.get(placeNum).getHealth() * 5 / 10;
			}
			for (int i = 0; i < friendlyNum; i++) {
				friendlyHealthAdjust(i, adjustNum);
				System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� 泥대젰 " + adjustNum + "留뚰겮 利앷�! >>>");
			}
			return;
		}
		case 50: { // ��寃� �떆�옉 �떆 媛숈� �쐞移섏뿉 �엳�뒗 �쟻援곗뿉寃� �뵾�빐 �엯�옓�땲�떎.
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			int adjustNum = friendly.get(placeNum).getPower();
			if (friendlyFindPokemon(54)) {
				if (placeNum - 1 >= 0 && enemy.get(placeNum - 1) != null) {
					if (enemy.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(placeNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
						System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyHealthAdjust(placeNum - 1, -(adjustNum + lv54));
						findHitEnemy(placeNum - 1, placeNum);
						System.out.println(
								"<<< " + enemy.get(placeNum - 1).getName() + "�뿉寃� " + (adjustNum + lv54) + " �뵾�빐! >>>");
					}
				} else {
					System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyHealthAdjust(placeNum, -(adjustNum + lv54));
					findHitEnemy(placeNum, placeNum);
					System.out
							.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� " + (adjustNum + lv54) + " �뵾�빐! >>>");
				}

				return;
			} else {
				if (placeNum - 1 >= 0 && enemy.get(placeNum - 1) != null) {
					if (enemy.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(placeNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
						enemyHealthAdjust(placeNum - 1, -friendly.get(placeNum).getPower());
						findHitEnemy(placeNum - 1, placeNum);
						System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
					}
				} else {
					enemyHealthAdjust(placeNum, -friendly.get(placeNum).getPower());
					findHitEnemy(placeNum, placeNum);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
				}
				return;
			}
		}
		default: {
			return;
		}
		}
	}

	void friendlyAttackAbility(int placeNum) { // 怨듦꺽 �떆 �뒫�젰
		System.out.println("<<< �븘援� 怨듦꺽 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv25 = findFriendlylv(25);
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 5: { // 怨듦꺽 �떆 怨듦꺽�젰 2, 3, 4 利앷�
				int abilityNum = 1 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + "留뚰겮 利앷�! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 16: { // 怨듦꺽 �떆 �뮘�뿉 �엳�뒗 �븘援� 泥대젰 2, 3, 4 利앷�
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				if (friendly.get(placeNum + 1) != null) {
					friendlyHealthAdjust(placeNum + 1, lv + 1);
					System.out.println(
							"<<< " + friendly.get(placeNum - 1).getName() + "�뿉寃� 泥대젰 " + (lv + 1) + "留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 22: { // 怨듦꺽 �떆 �옖�뜡�븳 �쟻援곗뿉寃� 怨듦꺽�젰 50%, 70%, 100% �쓽 �뵾�빐
				while (true) {
					int rNum = random.nextInt(5);
					if (enemy.get(rNum) != null) {
						System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
						int adjustNum = friendly.get(placeNum).getPower() / 2;
						if (lv == 2) {
							adjustNum = friendly.get(placeNum).getPower() * 7 / 10;
						} else if (lv == 3) {
							adjustNum = friendly.get(placeNum).getPower();
						}
						enemyHealthAdjust(rNum, adjustNum);
						System.out.println("<<< " + enemy.get(rNum).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
						whoHitEnemy[rNum] = placeNum;
						return;
					}
				}
			}
			case 24: { // 怨듦꺽�떆 25%, 35%, 50% �솗瑜좊줈 異붽� 怨듦꺽
				if (lv == 1) {
					int rNum = random.nextInt(4);
					if (rNum == 0) {
						System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
						hitEnemy();
					}
					return;
				} else if (lv == 2) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
						hitEnemy();
					}
					return;
				} else if (lv == 3) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
						hitEnemy();
					}
					return;
				}

			}
			case 27: { // 怨듦꺽 �떆 怨듦꺽�젰 4, 5, 6 利앷�
				int abilityNum = 3 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 40: { // 怨듦꺽 �떆 怨듦꺽�젰, 泥대젰 3, 4, 5 利앷�
				int abilityNum = 2 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 44: { // �씠 �룷耳볥が�� 2踰� 怨듦꺽 �빀�땲�떎
				System.out.println("<<< " + friendly.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
				hitEnemy();
				return;

			}
			case 46: { // 怨듦꺽 �떆 �쟻援곗쓽 怨듦꺽�젰 20%, 30%, 50% 留뚰겮 媛먯냼�빀�땲�떎
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = enemy.get(0).getPower() / 5;
				if (lv == 2) {
					adjustNum = enemy.get(0).getPower() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(0).getPower() / 2;
				}
				enemyPowerAdjust(0, -(adjustNum));
				System.out.println("<<< " + enemy.get(0).getName() + " 怨듦꺽�젰 " + adjustNum + " 留뚰겮 媛먯냼! >>>");
				return;
			}
			case 58: { // 怨듦꺽 �떆 怨듦꺽�젰�쓽 50%, 60%, 75%留뚰겮 洹� �뮘�뿉�엳�뒗 �쟻援곗뿉寃� �뵾�빐
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = friendly.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getPower() * 6 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getPower() * 75 / 100;
				}
				enemyPowerAdjust(1, -(adjustNum));
				System.out.println("<<< " + enemy.get(1).getName() + "�뿉寃� " + adjustNum + " 留뚰겮 異붽� �뵾�빐! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyFrontFightAbillity(int placeNum) { // �븵�쓽 �븘援� 怨듦꺽 �떆 �뒫�젰
		System.out.println("<<< �븵�뿉 �엳�뒗 �븘援� 怨듦꺽 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum + 1) != null) {
			switch (friendly.get(placeNum + 1).getPokemonNum()) {
			case 9: { // �븵�쓽 �븘援곗씠 怨듦꺽 �떆 �븵�쓽 �븘援� 泥대젰 2, 3, 4 利앷�
				int abilityNum = 1 + lv;
				System.out.println("<<< " + friendly.get(placeNum + 1).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				return;
			}
			case 55: { // �븵�쓽 �븘援� 怨듦꺽 �떆 1踰� �뜑 怨듦꺽�떆�궢�땲�떎.
				System.out.println("<<< " + friendly.get(1).getName() + " �뒫�젰 �궗�슜! >>>");
				hitEnemy();
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyHitAbility(int placeNum) { // 留욎쓣 �떆 �뒫�젰
		System.out.println("<<< �븘援곗씠 留욎쓣 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = friendly.get(placeNum).getLv();
		int lv25 = findFriendlylv(25);
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 4: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�븳 ���긽�뿉寃� 1, 2, 3 �뵾�빐
				int enemyNum = whoHitFriendly[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					if (enemyNum > 0) {
						if (enemy.get(enemyNum - 1) != null) {
							if (enemy.get(enemyNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(enemyNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								enemyNum -= 1;
							}
						}
					}
					enemyHealthAdjust(enemyNum, -lv);
					System.out.println("<<< " + enemy.get(enemyNum).getName() + "�뿉寃� " + lv + " �뵾�빐! >>>");
					whoHitFriendly[placeNum] = -1;
					return;
				}
				return;
			}
			case 11: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�븳 �쟻援� 怨듦꺽�젰 1, 2, 3 媛먯냼
				if (whoHitFriendly[placeNum] != -1) {
					int enemyNum = whoHitFriendly[placeNum];
					System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(enemyNum, -lv);
					System.out.println("<<< " + enemy.get(enemyNum).getName() + " 怨듦꺽�젰 " + lv + " 媛먯냼! >>>");
					whoHitFriendly[placeNum] = -1;
					return;
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 21: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�젰,泥대젰 2, 3, 4 利앷�
				int abilityNum = 1 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 30: { // 怨듦꺽�뿉 留욎쓣 �떆 泥대젰 4, 5, 6 利앷�
				int abilityNum = 3 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + abilityNum + "留뚰겮 利앷�! >>>");
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 39: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�븳 �쟻援곗뿉寃� 1, 2, 3 �룆 �뵾�빐
				int enemyNum = whoHitFriendly[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					friendlyPoisonNum[enemyNum] += lv;
					System.out.println("<<< " + enemy.get(enemyNum).getName() + "�뿉寃� " + lv + " �룆 �뵾�빐! >>>");
					whoHitFriendly[placeNum] = -1;
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 45: { // 怨듦꺽�뿉 留욎쓣 �떆 �뵾�빐�웾 20%, 30%, 50%留뚰겮 �룎�젮以띾땲�떎
				int enemyNum = whoHitFriendly[placeNum];
				if (enemyNum != -1) {
					int adjustNum = (friendlyHP45 - friendly.get(placeNum).getHealth()) / 5;
					if (lv == 2) {
						adjustNum = (friendlyHP45 - friendly.get(placeNum).getHealth()) * 3 / 10;
					} else if (lv == 3) {
						adjustNum = (friendlyHP45 - friendly.get(placeNum).getHealth()) / 2;
					}
					System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					if (enemy.get(enemyNum - 1) != null) {
						if (enemy.get(enemyNum - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(enemyNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							enemyNum -= 1;
						}
					}
					enemyHealthAdjust(enemyNum, adjustNum);
					System.out.println("<<< " + enemy.get(enemyNum).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
					friendlyAbillity45();
					whoHitFriendly[placeNum] = -1;
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 57: { // 怨듦꺽�뿉 留욎쓣 �떆 泥대젰�쓽 20%, 30%, 50% 留뚰겮 泥대젰 利앷�
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = friendly.get(placeNum).getHealth() / 5;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getHealth() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getHealth() / 2;
				}
				friendlyHealthAdjust(placeNum, adjustNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + adjustNum + "留뚰겮 利앷�! >>>");
				whoHitFriendly[placeNum] = -1;
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyDownAbility(int placeNum) { // 湲곗젅 �떆 �뒫�젰
		System.out.println("<<< �븘援� 湲곗젅 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 1: { // 湲곗젅 �떆 �뒫�젰 �뾾�뒗 2/2�떒�뜲湲� 1,2, 3 �냼�솚
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < lv; i++) {
					friendlyGoBack();
					friendly.set(0, dan);
					System.out.println("<<< " + "�떒�뜲湲�" + " �냼�솚! >>>");
					friendlyUseToSummon();
					friendlyUseSummonAbility();
					enemyFriendlySummonAbility();
				}
				friendly.set(placeNum + lv, null);
				return;
			}
			case 6: { // 湲곗젅 �떆 媛��옣 �뮘�뿉 �엳�뒗 �쟻援� 1, 2, 3 �룆 �뵾�빐
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int i = remainEnemyNum() - 1;
				friendlyPoisonNum[i] += lv;
				System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + lv + " �룆 �뵾�빐! >>>");
				friendly.set(placeNum, null);
				return;
			}
			case 18: { // 湲곗젅 �떆 媛��옣 �븵�뿉 �엳�뒗 �쟻援� 2, 3, 4 �뵾�빐
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int abilityNum = 1 + lv;
				if (enemy.get(0) == null) {
					return;
				}
				enemyHealthAdjust(0, abilityNum);
				System.out.println("<<< " + enemy.get(0).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
				findHitEnemy(0, placeNum);
				friendly.set(placeNum, null);
				return;
			}
			case 19: { // 湲곗젅 �떆 �뒫�젰 �뾾�뒗 留앺궎 1, 2, 3 �냼�솚
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < lv; i++) {
					friendlyGoBack();
					friendly.set(0, mang);
					System.out.println("<<< " + "留앺궎" + " �냼�솚! >>>");
					friendlyUseToSummon();
					friendlyUseSummonAbility();
					enemyFriendlySummonAbility();
				}
				friendly.set(placeNum + lv, null);
				return;
			}

			case 28: { // 湲곗젅 �떆 泥대젰 2, 2.5, 3諛곗씤 �뒫�젰 �뾾�뒗 紐⑤떎�뵾 2 �냼�솚
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				if (lv == 2) {
					mo.setHealth(mo.getHealth() / 2 * 25 / 10);
				} else if (lv == 3) {
					mo.setHealth(mo.getHealth() / 2 * 3);
				}
				friendlyGoBack();
				friendly.set(0, mo);
				friendly.set(placeNum, null);
				System.out.println("<<< " + "紐⑤떎�뵾" + " �냼�솚! >>>");
				friendlyUseToSummon();
				friendlyUseSummonAbility();
				enemyFriendlySummonAbility();
				friendlyGoBack();
				friendly.set(0, mo);
				friendly.set(placeNum, null);
				System.out.println("<<< " + "紐⑤떎�뵾" + " �냼�솚! >>>");
				friendlyUseToSummon();
				friendlyUseSummonAbility();
				enemyFriendlySummonAbility();
				return;
			}
			default: {
				friendly.set(placeNum, null);
				return;
			}
			}
		}
	}

	void friendlyFrontDownAbility(int placeNum) { // �븵�쓽 �븘援� 湲곗젅 �떆
		System.out.println("<<< �븵�쓽 �븘援� 湲곗젅 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum + 1) != null) {
			switch (friendly.get(placeNum + 1).getPokemonNum()) {
			case 20: { // �븵�쓽 �븘援� 湲곗젅 �떆 紐⑤뱺 �쟻援� 1, 2, 3 �뵾�빐
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < remainEnemyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && enemy.get(i - 1) != null) {
							if (enemy.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								enemyHealthAdjust(i - 1, -lv);
								System.out.println("<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + lv + " �뵾�빐! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -lv);
							System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + lv + " �뵾�빐! >>>");
							findHitEnemy(i, -1);
						}

					}
				}
				return;
			}
			case 35: { // �븵�뿉 �엳�뒗 �븘援� 湲곗젅 �떆 紐⑤뱺 �쑀�떅�뿉寃� 2, 3, 4 �뵾�빐
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int abilityNum = 1 + lv;
				for (int i = 0; i < remainEnemyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && enemy.get(i - 1) != null) {
							if (enemy.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								enemyHealthAdjust(i - 1, -abilityNum);
								System.out
										.println("<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
							findHitEnemy(i, -1);
						}

					}
				}
				for (int i = 0; i < remainFriendlyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && friendly.get(i - 1) != null) {
							if (friendly.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								enemyHealthAdjust(i - 1, -abilityNum);
								System.out.println(
										"<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
							findHitfriendly(i, -1);
						}

					}
				}
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyDownEnemyAbility(int placeNum) { // �쟻 湲곗젅 �떆 �뒫�젰
		System.out.println("<<< �쟻援� 湲곗젅 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = friendly.get(placeNum).getLv();
		int abilityNum = 3 + lv;
		int lv25 = findFriendlylv(25);
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 47: {
				System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
				}
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + abilityNum + "留뚰겮 利앷�! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyUseToSummon() { // �냼�솚 �떆 �뒫�젰 �냼�솚愿� �븘援곗뿉寃� �벐�뒗 �뒫�젰
		System.out.println("<<< �븘援� �냼�솚 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv15 = findFriendlylv(15);
		int lv34 = findFriendlylv(34);
		int lv51 = findFriendlylv(51);
		int lv25 = findFriendlylv(25);
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == 15) { // �븘援� �냼�솚�떆 �냼�솚�맂 �븘援� 怨듦꺽�젰,泥대젰 2, 3, 4 利앷�
					int abilityNum = 1 + lv15;
					System.out.println("<<< 肄섑뙜 �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 怨듦꺽�젰 " + abilityNum + " 利앷�! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyPowerAdjust(0, lv25);
						System.out.println("<<< " + friendly.get(0).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
					}
					friendlyHealthAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 泥대젰 " + abilityNum + " 利앷�! >>>");
				} else if (friendly.get(i).getPokemonNum() == 34) { // �븘援� �냼�솚 �떆 �냼�솚�맂 �쑀�떅 怨듦꺽�젰 2, 3, 4 利앷�
					int abilityNum = 1 + lv34;
					System.out.println("<<< �뙆�삤由� �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 怨듦꺽�젰 " + abilityNum + " 利앷�! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyPowerAdjust(0, lv25);
						System.out.println("<<< " + friendly.get(0).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
					}
				} else if (friendly.get(i).getPokemonNum() == 51) { // �냼�솚�맂 �븘援� 怨듦꺽�젰, 泥대젰 3, 4, 5利앷�
					int abilityNum = 2 + lv51;
					System.out.println("<<< 肄섏튂 �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 怨듦꺽�젰 " + abilityNum + " 利앷�! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyPowerAdjust(0, lv25);
						System.out.println("<<< " + friendly.get(0).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
					}
					friendlyHealthAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 泥대젰 " + abilityNum + " 利앷�! >>>");
				}
			}
		}
	}

	void friendlyUseSummonAbility() { // �븘援� �냼�솚 �떆 �뒫�젰 �궗�슜
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == 2 || friendly.get(i).getPokemonNum() == 41) {
					friendlySummonAbility(i);
				}
			}
		}
	}

	void friendlySummonAbility(int placeNum) { // �븘援� �냼�솚 �떆 �뒫�젰
		System.out.println("<<< �븘援� �냼�솚 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = friendly.get(placeNum).getLv();
		int lv25 = findFriendlylv(25);
		switch (friendly.get(placeNum).getPokemonNum()) {
		case 2: { // �븘援� �냼�솚 �떆 泥대젰 2, 3, 4 利앷�
			int abilityNum = 1 + lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			friendlyHealthAdjust(placeNum, abilityNum);
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
			return;
		}

		case 41: { // �븘援� �냼�솚 �떆 �옖�뜡�쑝濡� �븘援� 怨듦꺽�젰,泥대젰 2, 3, 4 利앷�
			int abilityNum = 1 + lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			while (true) {
				int rNum = random.nextInt(5);
				if (friendly.get(rNum) != null) {
					friendlyHealthAdjust(rNum, abilityNum);
					System.out.println("<<< " + friendly.get(rNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
					friendlyPowerAdjust(rNum, abilityNum);
					System.out.println("<<< " + friendly.get(rNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyPowerAdjust(rNum, lv25);
						System.out.println("<<< " + friendly.get(rNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
					}
					return;
				}
			}
		}

		default: {
			return;
		}
		}
	}

	void friendlyEnemySummonAbility() { // �쟻援� �냼�솚 �떆
		System.out.println("<<< �쟻援� �냼�솚 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = findFriendlylv(43);
		int abilityNum = 2 + lv;
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == 43) { // �쟻援� �냼�솚 �떆 �옖�뜡�븳 �쟻援� 1紐� �뿉寃� 3 �뵾�빐
					System.out.println("<<< " + friendly.get(i).getName() + " �뒫�젰 �궗�슜! >>>");
					while (true) {
						int rNum = random.nextInt();
						if (enemy.get(rNum) != null) {
							if (rNum - 1 >= 0 && enemy.get(rNum - 1) != null) {
								if (enemy.get(rNum - 1).getPokemonNum() == 52) {
									System.out.println("<<< " + enemy.get(rNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
									System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
									rNum -= 1;
								}
							}
							enemyHealthAdjust(rNum, abilityNum);
							System.out.println("<<< " + enemy.get(rNum).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
							return;
						}
					}
				}
			}

		}
	}

	boolean friendlyFindPokemon(int pokemonNum) { // �븘援곗뿉 �엳�뒗吏� �솗�씤
		for (Pokemon p : friendly) {
			if (p != null) {
				if (p.getPokemonNum() == pokemonNum) {
					return true;
				}
			}
		}
		return false;
	}

	// ------------- �쟻援� �뒫�젰 �븿�닔 --------------------

	// ------------ �뒫�젰 �븿�닔 -----------------

	int findEnemylv(int pokemonNum) {
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == pokemonNum) {
					return enemy.get(i).getLv();
				}
			}
		}
		return -1;
	}

	void enemyStartBattleAbility(int placeNum) { // ��寃� �떆�옉 �떆 �뒫�젰
		int lv54 = findEnemylv(54);
		int lv = enemy.get(placeNum).getLv();
		switch (enemy.get(placeNum).getPokemonNum()) {
		case 3: { // ��寃� �떆�옉 �떆 �쟻援� �옖�뜡 �븯�굹�뿉寃� 2, 3, 4 �뵾�빐
			int abilityNum = lv + 1;
			while (true) {
				int rNum = random.nextInt(remainFriendlyNum());
				if (friendly.get(rNum) != null) {
					System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					System.out.println("<<< " + enemy.get(placeNum).getAbility() + " >>>");
					if (enemyFindPokemon(54)) {
						if (rNum - 1 >= 0 && friendly.get(rNum - 1) != null) {
							if (friendly.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(rNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								rNum -= 1;
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							}
						}
						System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyHealthAdjust(rNum, -(abilityNum + lv54));
						findHitfriendly(rNum, placeNum);
						System.out.println(
								"<<< " + friendly.get(rNum).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
					} else {
						if (rNum - 1 >= 0 && friendly.get(rNum - 1) != null) {
							if (friendly.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(rNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								rNum -= 1;
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							}
						}
						friendlyHealthAdjust(rNum, -abilityNum);
						findHitfriendly(rNum, placeNum);
						System.out.println("<<< " + friendly.get(rNum).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
					}
					return;
				}
			}
		}
		case 10: { // ��寃� �떆�옉 �떆 媛��옣 �뮘�뿉 �엳�뒗 �쟻援� 1, 2, 3 �뵾�빐
			int enemyPlaceNum = remainFriendlyNum() - 1;
			if (enemyPlaceNum < 0) {
				return;
			}
			int abilityNum = lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			if (enemyFindPokemon(54)) {
				if (enemyPlaceNum - 1 >= 0 && friendly.get(enemyPlaceNum - 1) != null) {
					if (friendly.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(enemyPlaceNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
					}
				}
				System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
				friendlyHealthAdjust(enemyPlaceNum, -(abilityNum + lv54));
				findHitfriendly(enemyPlaceNum, placeNum);
				System.out.println(
						"<<< " + friendly.get(enemyPlaceNum).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
			} else {
				if (enemyPlaceNum - 1 >= 0 && friendly.get(enemyPlaceNum - 1) != null) {
					if (friendly.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(enemyPlaceNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
					}
				}
				friendlyHealthAdjust(enemyPlaceNum, -abilityNum);
				findHitfriendly(enemyPlaceNum, placeNum);
				System.out.println("<<< " + friendly.get(enemyPlaceNum).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
			}
			return;

		}
		case 12: { // ��寃� �떆�옉 �떆 紐⑤뱺 �쟻援� 1, 2, 3 �뵾�빐
			int enemyPlaceNum = remainEnemyNum();
			int abilityNum = lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜>>>");
			for (int i = 0; i < enemyPlaceNum; i++) {
				if (enemyFindPokemon(54)) {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52 || i - 1 >= 0) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
							friendlyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitfriendly(enemyPlaceNum - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
						}
					} else {
						System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyHealthAdjust(i, -(abilityNum + lv54));
						findHitfriendly(enemyPlaceNum, placeNum);
						System.out
								.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
					}
				} else {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							friendlyHealthAdjust(i - 1, -abilityNum);
							findHitfriendly(enemyPlaceNum - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
						}
					} else {
						friendlyHealthAdjust(i, -abilityNum);
						findHitfriendly(enemyPlaceNum, placeNum);
						System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
					}
				}
			}
			return;
		}
		case 23: { // ��寃� �떆�옉 �떆 �븵�뿉 �엳�뒗 �븘援곗뿉寃� 泥대젰 50%, 70%, 100% 留뚰겮 利앷�
			if (placeNum != 0) {
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = enemy.get(placeNum).getHealth() / 2;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getHealth() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getHealth();
				}
				enemyHealthAdjust(placeNum - 1, adjustNum);
				System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "�뿉寃�  泥대젰 " + adjustNum + "留뚰겮 利앷�! >>>");
				return;
			}
			return;
		}
		case 26: { // ��寃� �떆�옉 �떆 �븵�뿉 �엳�뒗 �븘援곗뿉寃� 怨듦꺽�젰 50%, 70%, 100% 留뚰겮 異붽�
			int lv25 = findEnemylv(25);
			if (placeNum != 0) {
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = enemy.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getPower() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getPower();
				}
				enemyPowerAdjust(placeNum - 1, adjustNum);
				System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "�뿉寃� 怨듦꺽�젰 " + adjustNum + "留뚰겮 利앷�! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(placeNum - 1, lv25);
					System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			return;
		}
		case 29: {// ��寃� �떆�옉 �떆 媛��옣 �뮘�뿉 �엳�뒗 �쟻援곗뿉寃� 1, 2, 3 �룆 �뵾�빐
			int abilityNum = lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			int enemyPlaceNum = remainFriendlyNum() - 1;
			if (enemyPlaceNum - 1 >= 0 && friendly.get(enemyPlaceNum - 1) != null) {
				if (friendly.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
					System.out.println("<<< " + friendly.get(enemyPlaceNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
					enemyPlaceNum -= 1;
					System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
				}
			}
			friendlyPoisonNum[enemyPlaceNum] += abilityNum;
			System.out.println("<<< " + friendly.get(enemyPlaceNum).getName() + "�뿉寃� " + abilityNum + " �룆 �뵾�빐! >>>");
			return;
		}
		case 31: { // ��寃� �떆�옉 �떆 紐⑤뱺 �쑀�떅 1, 2, 3 �뵾�빐
			int abilityNum = lv;
			int friendlyNum = remainFriendlyNum();
			int enemyNum = remainEnemyNum();
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			for (int i = 0; i < enemyNum; i++) {
				if (i - 1 >= 0 && enemy.get(i - 1) != null) {
					if (enemy.get(i - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
						enemyHealthAdjust(i - 1, -abilityNum);
						System.out.println("<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
						findHitEnemy(i - 1, -1);
					}
				} else {
					enemyHealthAdjust(i, -abilityNum);
					System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
					findHitEnemy(i, -1);
				}

			}
			if (enemyFindPokemon(54)) {
				System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < friendlyNum; i++) {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							friendlyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitfriendly(i - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
						}
					} else {
						friendlyHealthAdjust(i, -(lv54 + abilityNum));
						findHitfriendly(i, placeNum);
						System.out
								.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + (abilityNum + lv54) + " �뵾�빐! >>>");
					}
				}
			} else {
				for (int i = 0; i < friendlyNum; i++) {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							friendlyHealthAdjust(i - 1, -abilityNum);
							findHitfriendly(i - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
						}
					} else {
						friendlyHealthAdjust(i, -abilityNum);
						findHitfriendly(i, placeNum);
						System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + (abilityNum) + " �뵾�빐! >>>");
					}
				}
			}
			return;
		}
		case 37: { // ��寃� �떆�옉 �떆 紐⑤뱺 �븘援곗쓽 泥대젰 30%, 40%, 50% 留뚰겮 利앷�
			int enemtyNum = remainEnemyNum();
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			int adjustNum = enemy.get(placeNum).getHealth() * 3 / 10;
			if (lv == 2) {
				adjustNum = enemy.get(placeNum).getHealth() * 4 / 10;
			} else if (lv == 3) {
				adjustNum = enemy.get(placeNum).getHealth() * 5 / 10;
			}
			for (int i = 0; i < enemtyNum; i++) {
				enemyHealthAdjust(i, adjustNum);
				System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� 泥대젰 " + adjustNum + "留뚰겮 利앷�! >>>");
			}
			return;
		}
		case 50: { // ��寃� �떆�옉 �떆 媛숈� �쐞移섏뿉 �엳�뒗 �쟻援곗뿉寃� �뵾�빐 �엯�옓�땲�떎.
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			int adjustNum = enemy.get(placeNum).getPower();
			if (enemyFindPokemon(54)) {
				if (placeNum - 1 >= 0 && friendly.get(placeNum - 1) != null) {
					if (friendly.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(placeNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
						System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						friendlyHealthAdjust(placeNum - 1, -(adjustNum + lv54));
						findHitfriendly(placeNum - 1, placeNum);
						System.out.println("<<< " + friendly.get(placeNum - 1).getName() + "�뿉寃� " + (adjustNum + lv54)
								+ " �뵾�빐! >>>");
					}
				} else {
					System.out.println("<<< " + "戮�戮��씪" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					friendlyHealthAdjust(placeNum, -(adjustNum + lv54));
					findHitfriendly(placeNum, placeNum);
					System.out.println(
							"<<< " + friendly.get(placeNum).getName() + "�뿉寃� " + (adjustNum + lv54) + " �뵾�빐! >>>");
				}

				return;
			} else {
				if (placeNum - 1 >= 0 && friendly.get(placeNum - 1) != null) {
					if (friendly.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(placeNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
						System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
						friendlyHealthAdjust(placeNum - 1, -adjustNum);
						findHitfriendly(placeNum - 1, placeNum);
						System.out.println(
								"<<< " + friendly.get(placeNum - 1).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
					}
				} else {
					friendlyHealthAdjust(placeNum, -adjustNum);
					findHitfriendly(placeNum, placeNum);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
				}
				return;
			}
		}
		default: {
			return;
		}
		}
	}

	void enemyAttackAbility(int placeNum) { // 怨듦꺽 �떆 �뒫�젰
		System.out.println("<<< �쟻援� 怨듦꺽 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv25 = findEnemylv(25);
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 5: { // 怨듦꺽 �떆 怨듦꺽�젰 2, 3, 4 利앷�
				int abilityNum = 1 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + "留뚰겮 利앷�! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 16: { // 怨듦꺽 �떆 �뮘�뿉 �엳�뒗 �븘援� 泥대젰 2, 3, 4 利앷�
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				if (enemy.get(placeNum + 1) != null) {
					enemyHealthAdjust(placeNum + 1, lv + 1);
					System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "�뿉寃� 泥대젰 " + (lv + 1) + "留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 22: { // 怨듦꺽 �떆 �옖�뜡�븳 �쟻援곗뿉寃� 怨듦꺽�젰 50%, 70%, 100% �쓽 �뵾�빐
				while (true) {
					int rNum = random.nextInt(5);
					if (friendly.get(rNum) != null) {
						System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
						int adjustNum = enemy.get(placeNum).getPower() / 2;
						if (lv == 2) {
							adjustNum = enemy.get(placeNum).getPower() * 7 / 10;
						} else if (lv == 3) {
							adjustNum = enemy.get(placeNum).getPower();
						}
						enemyHealthAdjust(rNum, adjustNum);
						System.out.println("<<< " + friendly.get(rNum).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
						whoHitFriendly[rNum] = placeNum;
						return;
					}
				}
			}
			case 24: { // 怨듦꺽�떆 25%, 35%, 50% �솗瑜좊줈 異붽� 怨듦꺽
				if (lv == 1) {
					int rNum = random.nextInt(4);
					if (rNum == 0) {
						System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
						hitFriendly();
					}
					return;
				} else if (lv == 2) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
						hitFriendly();
					}
					return;
				} else if (lv == 3) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
						hitFriendly();
					}
					return;
				}

			}
			case 27: { // 怨듦꺽 �떆 怨듦꺽�젰 4, 5, 6 利앷�
				int abilityNum = 3 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 40: { // 怨듦꺽 �떆 怨듦꺽�젰, 泥대젰 3, 4, 5 利앷�
				int abilityNum = 2 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
				}
				return;
			}
			case 44: { // �씠 �룷耳볥が�� 2踰� 怨듦꺽 �빀�땲�떎
				System.out.println("<<< " + enemy.get(0).getName() + " �뒫�젰 �궗�슜! >>>");
				hitFriendly();
				return;

			}
			case 46: { // 怨듦꺽 �떆 �쟻援곗쓽 怨듦꺽�젰 20%, 30%, 50% 留뚰겮 媛먯냼�빀�땲�떎
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = friendly.get(0).getPower() / 5;
				if (lv == 2) {
					adjustNum = friendly.get(0).getPower() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(0).getPower() / 2;
				}
				friendlyPowerAdjust(0, -(adjustNum));
				System.out.println("<<< " + friendly.get(0).getName() + " 怨듦꺽�젰 " + adjustNum + " 留뚰겮 媛먯냼! >>>");
				return;
			}
			case 58: { // 怨듦꺽 �떆 怨듦꺽�젰�쓽 50%, 60%, 75%留뚰겮 洹� �뮘�뿉�엳�뒗 �쟻援곗뿉寃� �뵾�빐
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = enemy.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getPower() * 6 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getPower() * 75 / 100;
				}
				friendlyPowerAdjust(1, -(adjustNum));
				System.out.println("<<< " + friendly.get(1).getName() + "�뿉寃� " + adjustNum + " 留뚰겮 異붽� �뵾�빐! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyFrontFightAbillity(int placeNum) { // �븵�쓽 �븘援� 怨듦꺽 �떆 �뒫�젰
		System.out.println("<<< �븵�뿉 �엳�뒗 �쟻援� 怨듦꺽 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum + 1) != null) {
			switch (enemy.get(placeNum + 1).getPokemonNum()) {
			case 9: { // �븵�쓽 �븘援곗씠 怨듦꺽 �떆 �븵�쓽 �븘援� 泥대젰 2, 3, 4 利앷�
				int abilityNum = 1 + lv;
				System.out.println("<<< " + enemy.get(placeNum + 1).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				return;
			}
			case 55: { // �븵�쓽 �븘援� 怨듦꺽 �떆 1踰� �뜑 怨듦꺽�떆�궢�땲�떎.
				System.out.println("<<< " + enemy.get(1).getName() + " �뒫�젰 �궗�슜! >>>");
				hitFriendly();
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyHitAbility(int placeNum) { // 留욎쓣 �떆 �뒫�젰
		System.out.println("<<< �쟻援� 留욎쓣 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = enemy.get(placeNum).getLv();
		int lv25 = findEnemylv(25);
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 4: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�븳 ���긽�뿉寃� 1, 2, 3 �뵾�빐
				int enemyNum = whoHitEnemy[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					if (enemyNum > 0) {
						if (friendly.get(enemyNum - 1) != null) {
							if (friendly.get(enemyNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(enemyNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								enemyNum -= 1;
							}
						}
					}
					friendlyHealthAdjust(enemyNum, -lv);
					System.out.println("<<< " + friendly.get(enemyNum).getName() + "�뿉寃� " + lv + " �뵾�빐! >>>");
					whoHitEnemy[placeNum] = -1;
					return;
				}
				return;
			}
			case 11: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�븳 �쟻援� 怨듦꺽�젰 1, 2, 3 媛먯냼
				if (whoHitEnemy[placeNum] != -1) {
					int enemyNum = whoHitEnemy[placeNum];
					System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					friendlyPowerAdjust(enemyNum, -lv);
					System.out.println("<<< " + friendly.get(enemyNum).getName() + " 怨듦꺽�젰 " + lv + " 媛먯냼! >>>");
					whoHitEnemy[placeNum] = -1;
					return;
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 21: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�젰,泥대젰 2, 3, 4 利앷�
				int abilityNum = 1 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 30: { // 怨듦꺽�뿉 留욎쓣 �떆 泥대젰 4, 5, 6 利앷�
				int abilityNum = 3 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + abilityNum + "留뚰겮 利앷�! >>>");
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 39: { // 怨듦꺽�뿉 留욎쓣 �떆 怨듦꺽�븳 �쟻援곗뿉寃� 1, 2, 3 �룆 �뵾�빐
				int enemyNum = whoHitEnemy[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					friendlyPoisonNum[enemyNum] += lv;
					System.out.println("<<< " + friendly.get(enemyNum).getName() + "�뿉寃� " + lv + " �룆 �뵾�빐! >>>");
					whoHitEnemy[placeNum] = -1;
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 45: { // 怨듦꺽�뿉 留욎쓣 �떆 �뵾�빐�웾 20%, 30%, 50%留뚰겮 �룎�젮以띾땲�떎
				int enemyNum = whoHitEnemy[placeNum];
				if (enemyNum != -1) {
					int adjustNum = (enemyHP45 - enemy.get(placeNum).getHealth()) / 5;
					if (lv == 2) {
						adjustNum = (enemyHP45 - enemy.get(placeNum).getHealth()) * 3 / 10;
					} else if (lv == 3) {
						adjustNum = (enemyHP45 - enemy.get(placeNum).getHealth()) / 2;
					}
					System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
					if (friendly.get(enemyNum - 1) != null) {
						if (friendly.get(enemyNum - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(enemyNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
							System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
							enemyNum -= 1;
						}
					}
					friendlyHealthAdjust(enemyNum, adjustNum);
					System.out.println("<<< " + friendly.get(enemyNum).getName() + "�뿉寃� " + adjustNum + " �뵾�빐! >>>");
					friendlyAbillity45();
					whoHitEnemy[placeNum] = -1;
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 57: { // 怨듦꺽�뿉 留욎쓣 �떆 泥대젰�쓽 20%, 30%, 50% 留뚰겮 泥대젰 利앷�
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int adjustNum = enemy.get(placeNum).getHealth() / 5;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getHealth() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getHealth() / 2;
				}
				enemyHealthAdjust(placeNum, adjustNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + adjustNum + "留뚰겮 利앷�! >>>");
				whoHitEnemy[placeNum] = -1;
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyDownAbility(int placeNum) { // 湲곗젅 �떆 �뒫�젰
		System.out.println("<<< �쟻援� 湲곗젅 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 1: { // 湲곗젅 �떆 �뒫�젰 �뾾�뒗 2/2�떒�뜲湲� 1,2, 3 �냼�솚
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < lv; i++) {
					enemyGoBack();
					enemy.set(0, dan);
					System.out.println("<<< " + "�떒�뜲湲�" + " �냼�솚! >>>");
					enemyUseToSummon();
					enemyUseSummonAbility();
					friendlyEnemySummonAbility();
				}
				enemy.set(placeNum + lv, null);
				return;
			}
			case 6: { // 湲곗젅 �떆 媛��옣 �뮘�뿉 �엳�뒗 �쟻援� 1, 2, 3 �룆 �뵾�빐
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int i = remainFriendlyNum() - 1;
				enemyPoisonNum[i] += lv;
				System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + lv + " �룆 �뵾�빐! >>>");
				enemy.set(placeNum, null);
				return;
			}
			case 18: { // 湲곗젅 �떆 媛��옣 �븵�뿉 �엳�뒗 �쟻援� 2, 3, 4 �뵾�빐
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int abilityNum = 1 + lv;
				if (friendly.get(0) == null) {
					return;
				}
				friendlyHealthAdjust(0, abilityNum);
				System.out.println("<<< " + friendly.get(0).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
				findHitfriendly(0, placeNum);
				enemy.set(placeNum, null);
				return;
			}
			case 19: { // 湲곗젅 �떆 �뒫�젰 �뾾�뒗 留앺궎 1, 2, 3 �냼�솚
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < lv; i++) {
					enemyGoBack();
					enemy.set(0, mang);
					System.out.println("<<< " + "留앺궎" + " �냼�솚! >>>");
					enemyUseToSummon();
					enemyUseSummonAbility();
					friendlyEnemySummonAbility();
				}
				enemy.set(placeNum + lv, null);
				return;
			}

			case 28: { // 湲곗젅 �떆 泥대젰 2, 2.5, 3諛곗씤 �뒫�젰 �뾾�뒗 紐⑤떎�뵾 2 �냼�솚
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				if (lv == 2) {
					mo.setHealth(mo.getHealth() / 2 * 25 / 10);
				} else if (lv == 3) {
					mo.setHealth(mo.getHealth() / 2 * 3);
				}
				enemyGoBack();
				enemy.set(0, mo);
				System.out.println("<<< " + "紐⑤떎�뵾" + " �냼�솚! >>>");
				enemyUseToSummon();
				enemyUseSummonAbility();
				friendlyEnemySummonAbility();
				enemyGoBack();
				enemy.set(0, mo);
				enemy.set(placeNum + 2, null);
				System.out.println("<<< " + "紐⑤떎�뵾" + " �냼�솚! >>>");
				enemyUseToSummon();
				enemyUseSummonAbility();
				friendlyEnemySummonAbility();
				return;
			}
			default: {
				enemy.set(placeNum, null);
				return;
			}
			}
		}
	}

	void enemyFrontDownAbility(int placeNum) { // �븵�쓽 �븘援� 湲곗젅 �떆
		System.out.println("<<< �븵�뿉 �엳�뒗 �쟻援� 湲곗젅 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum + 1) != null) {
			switch (enemy.get(placeNum + 1).getPokemonNum()) {
			case 20: { // �븵�쓽 �븘援� 湲곗젅 �떆 紐⑤뱺 �쟻援� 1, 2, 3 �뵾�빐
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				for (int i = 0; i < remainFriendlyNum(); i++) {
					if (friendly.get(i) != null) {
						if (i - 1 >= 0 && friendly.get(i - 1) != null) {
							if (friendly.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								friendlyHealthAdjust(i - 1, -lv);
								System.out.println("<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + lv + " �뵾�빐! >>>");
								findHitfriendly(i - 1, -1);
							}
						} else {
							friendlyHealthAdjust(i, -lv);
							System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + lv + " �뵾�빐! >>>");
							findHitfriendly(i, -1);
						}

					}
				}
				return;
			}
			case 35: { // �븵�뿉 �엳�뒗 �븘援� 湲곗젅 �떆 紐⑤뱺 �쑀�떅�뿉寃� 2, 3, 4 �뵾�빐
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				int abilityNum = 1 + lv;
				for (int i = 0; i < remainFriendlyNum(); i++) {
					if (friendly.get(i) != null) {
						if (i - 1 >= 0 && friendly.get(i - 1) != null) {
							if (friendly.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								friendlyHealthAdjust(i - 1, -abilityNum);
								System.out.println(
										"<<< " + friendly.get(i - 1).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
								findHitfriendly(i - 1, -1);
							}
						} else {
							friendlyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + friendly.get(i).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
							findHitfriendly(i, -1);
						}

					}
				}
				for (int i = 0; i < remainEnemyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && enemy.get(i - 1) != null) {
							if (enemy.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(i - 1).getName() + " �뒫�젰 �궗�슜! >>>");
								System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
								enemyHealthAdjust(i - 1, -abilityNum);
								System.out
										.println("<<< " + enemy.get(i - 1).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + enemy.get(i).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
							findHitEnemy(i, -1);
						}

					}
				}
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyDownEnemyAbility(int placeNum) { // �쟻 湲곗젅 �떆 �뒫�젰
		System.out.println("<<< �븘援� 湲곗젅 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = enemy.get(placeNum).getLv();
		int abilityNum = 3 + lv;
		int lv25 = findEnemylv(25);
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 47: {
				System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
				}
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + abilityNum + "留뚰겮 利앷�! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyUseToSummon() { // �냼�솚 �떆 �뒫�젰 �냼�솚愿� �븘援곗뿉寃� �벐�뒗 �뒫�젰
		System.out.println("<<< �쟻援� �냼�솚 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv15 = findEnemylv(15);
		int lv34 = findEnemylv(34);
		int lv51 = findEnemylv(51);
		int lv25 = findEnemylv(25);
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == 15) { // �븘援� �냼�솚�떆 �냼�솚�맂 �븘援� 怨듦꺽�젰,泥대젰 2, 3, 4 利앷�
					int abilityNum = 1 + lv15;
					System.out.println("<<< 肄섑뙜 �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 怨듦꺽�젰 " + abilityNum + " 利앷�! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyPowerAdjust(0, lv25);
						System.out.println("<<< " + enemy.get(0).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
					}
					enemyHealthAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 泥대젰 " + abilityNum + " 利앷�! >>>");
				} else if (enemy.get(i).getPokemonNum() == 34) { // �븘援� �냼�솚 �떆 �냼�솚�맂 �쑀�떅 怨듦꺽�젰 2, 3, 4 利앷�
					int abilityNum = 1 + lv34;
					System.out.println("<<< �뙆�삤由� �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 怨듦꺽�젰 " + abilityNum + " 利앷�! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyPowerAdjust(0, lv25);
						System.out.println("<<< " + enemy.get(0).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
					}
				} else if (enemy.get(i).getPokemonNum() == 51) { // �냼�솚�맂 �븘援� 怨듦꺽�젰, 泥대젰 3, 4, 5利앷�
					int abilityNum = 2 + lv51;
					System.out.println("<<< 肄섏튂 �뒫�젰 �궗�슜! >>>");
					enemyPowerAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 怨듦꺽�젰 " + abilityNum + " 利앷�! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyPowerAdjust(0, lv25);
						System.out.println("<<< " + enemy.get(0).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + "留뚰겮 利앷�! >>>");
					}
					enemyHealthAdjust(0, abilityNum);
					System.out.println("<<< �냼�솚�맂 �룷耳볥が 泥대젰 " + abilityNum + " 利앷�! >>>");
				}
			}
		}
	}

	void enemyUseSummonAbility() { // �븘援� �냼�솚 �떆 �뒫�젰 �궗�슜
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == 2 || enemy.get(i).getPokemonNum() == 41) {
					enemySummonAbility(i);
				}
			}

		}
	}

	void enemySummonAbility(int placeNum) { // �븘援� �냼�솚 �떆 �뒫�젰
		System.out.println("<<< �쟻援� �냼�솚 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = enemy.get(placeNum).getLv();
		int lv25 = findEnemylv(25);
		switch (enemy.get(placeNum).getPokemonNum()) {
		case 2: { // �븘援� �냼�솚 �떆 泥대젰 2, 3, 4 利앷�
			int abilityNum = 1 + lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			enemyHealthAdjust(placeNum, abilityNum);
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
			return;
		}

		case 41: { // �븘援� �냼�솚 �떆 �옖�뜡�쑝濡� �븘援� 怨듦꺽�젰,泥대젰 2, 3, 4 利앷�
			int abilityNum = 1 + lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " �뒫�젰 �궗�슜! >>>");
			while (true) {
				int rNum = random.nextInt(5);
				if (enemy.get(rNum) != null) {
					enemyHealthAdjust(rNum, abilityNum);
					System.out.println("<<< " + enemy.get(rNum).getName() + " 泥대젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
					enemyPowerAdjust(rNum, abilityNum);
					System.out.println("<<< " + enemy.get(rNum).getName() + " 怨듦꺽�젰 " + abilityNum + " 留뚰겮 利앷�! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "諛쒖콡�씠" + "�쓽 異붽� �뒫�젰 �궗�슜! >>>");
						enemyPowerAdjust(rNum, lv25);
						System.out.println("<<< " + enemy.get(rNum).getName() + "�뿉寃� 怨듦꺽�젰 " + lv25 + " 留뚰겮 利앷�! >>>");
					}
					return;
				}
			}
		}

		default: {
			return;
		}
		}
	}

	void enemyFriendlySummonAbility() { // �쟻援� �냼�솚 �떆
		System.out.println("<<< �븘援� �냼�솚 �떆 �뒫�젰 諛쒕룞 >>>");
		int lv = findEnemylv(43);
		int abilityNum = 2 + lv;
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == 43) { // �쟻援� �냼�솚 �떆 �옖�뜡�븳 �쟻援� 1紐� �뿉寃� 3 �뵾�빐
					System.out.println("<<< " + enemy.get(i).getName() + " �뒫�젰 �궗�슜! >>>");
					while (true) {
						int rNum = random.nextInt();
						if (friendly.get(rNum) != null) {
							if (rNum - 1 >= 0 && friendly.get(rNum - 1) != null) {
								if (friendly.get(rNum - 1).getPokemonNum() == 52) {
									System.out.println("<<< " + friendly.get(rNum - 1).getName() + " �뒫�젰 �궗�슜! >>>");
									System.out.println("<<< ���떊 留욎뒿�땲�떎! >>>");
									rNum -= 1;
								}
							}
							friendlyHealthAdjust(rNum, abilityNum);
							System.out.println("<<< " + friendly.get(rNum).getName() + "�뿉寃� " + abilityNum + " �뵾�빐! >>>");
							return;
						}
					}
				}
			}
		}
	}

	boolean enemyFindPokemon(int pokemonNum) { // �븘援곗뿉 �엳�뒗吏� �솗�씤
		for (Pokemon p : enemy) {
			if (p != null) {
				if (p.getPokemonNum() == pokemonNum) {
					return true;
				}
			}
		}
		return false;
	}
}
