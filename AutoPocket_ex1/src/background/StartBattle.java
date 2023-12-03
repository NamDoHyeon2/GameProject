package background;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class StartBattle extends JPanel {
	private ImagePanel background;
	private ImagePanel next_btn;
	private ImagePanel PlacementScreen;
	public Win_or_Lose win_or_lose;
	Scanner sc = new Scanner(System.in);
	private LinkedList<Pokemon> friendly; // 아군 리스트
	private LinkedList<Obj_Panel3> friendly_panel;
	private LinkedList<Obj_Panel3> enemy_panel;
	private LinkedList<Pokemon> enemy; // 적군 리스트
	private LinkedList<Pokemon> forreplace; //
	private LinkedList <Button> buttons;
	private boolean isnext;
	private ImagePanel ballImg;
	private Status_Panel status_panel;
	
	Random random = new Random();
	int turnNum; // 턴 수
	int friendlyMaxPalceNum = 5; // 아군 남아있는 수
	int enemyMaxPalceNum = 5; // 적군 남아있는 수

	boolean friendlyFirstHit38 = true; // 38번 포켓몬이 처음 맞았는지 확인 true이면 안 맞음
	boolean friendlyFirstHit[] = { false, false, false, false, false }; // 버섯 효과용 처음으로 맞았는지 확인 1이면 안 맞음
	int findEffect[] = { 0, 0, 0, 0, 0 }; // 버섯 효과 찾기용
	boolean replaceFirstHit[] = { false, false, false, false, false }; // 버섯효과 자리 바꾸기용

	boolean enemyFirstHit38 = true; // 38번 포켓몬이 처음 맞았는지 확인 true이면 안 맞음
	boolean enemyFirstHit[] = { false, false, false, false, false }; // 버섯 효과 용 처음으로 맞았는지 확인 1이면 안 맞음

	int friendlyHP45 = 0; // 45번 포켓몬 체력 저장 피해량 20% 돌려줘야함
	int enemyHP45 = 0;

	int replaceHitNum[] = { -1, -1, -1, -1, -1 ,-1, -1, -1, -1, -1, -1};
	int whoHitEnemy[] = { -1, -1, -1, -1, -1 ,-1, -1, -1, -1, -1, -1}; // 적군 공격한 아군 위치 저장
	int whoHitFriendly[] = { -1, -1, -1, -1, -1 ,-1, -1, -1, -1, -1, -1}; // 아군 공격한 적군 위치 저장

	int replacePoisonNum[] = { 0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0};
	int friendlyPoisonNum[] = {  0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0}; // 아군 독 피해량 저장
	int enemyPoisonNum[] = {  0, 0, 0, 0, 0 ,0, 0, 0, 0, 0, 0 }; // 적군 독 피해량 저장

	Pokemon dan = new Pokemon(61, 2, 1, "단데기", "벌레", 1, 2, 2, "기절 시 능력 없는 2/2단데기 2 소환");
	Obj_Panel3 dan_panel = new Obj_Panel3();
	Pokemon mang = new Pokemon(62, 1, 1, "망키", "격투", 2, 3, 2, "기절 시 능력 없는 망키 소환");
	Pokemon mo = new Pokemon(63, 1, 1, "모다피", "풀", 3, 3, 3, "기절 시 체력 2배인 능력 없는 모다피 2 소환");
	/**
	 * Create the panel.
	 */
	public StartBattle(int turnNum, Pokemon f1, Pokemon f2, Pokemon f3, Pokemon f4, Pokemon f5, 
			int findEffect[], ImagePanel placement, Status_Panel status) {
		this.status_panel = status;
		this.PlacementScreen = placement;
		this.friendly_panel = new LinkedList<>();
		this.friendly = new LinkedList<>();
		this.enemy_panel = new LinkedList<>();
		this.enemy = new LinkedList<>();
		this.forreplace = new LinkedList<>();
		this.buttons = new LinkedList<>();
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
		
		Placement_Screen ps = new Placement_Screen();
		
		//--------단대기------------------
		dan_panel.set_pokemon_num(1, 2);
		dan_panel.set_exp(1);
		dan_panel.set_damage(2);
		dan_panel.set_heart(2);
		dan_panel.set_Lv(2);
		//------------------------------
		
		this.setLayout(null);
		this.setBounds(0, 0, 1920, 1080);
		this.background = new ImagePanel(new ImageIcon("C:\\Placement_2\\src\\Image\\combat_background.png").getImage());
		background.setBounds(0, 0, 1920, 1080);
		background.setLayout(null);
		this.add(background);
		isnext = false;
		
		
		//---------------------다음 버튼-------------------
		this.next_btn = new ImagePanel (new ImageIcon("C:\\Placement_2\\src\\Image\\Next_Battle.png").getImage());
		this.background.add(next_btn);
		next_btn.setLocation(910, 200);
		
		for(int i = 0; i < 100; i ++) {	
			Button button = new Button();
			this.background.add(button);
			button.setBounds(910,200,101,116);
			button.setVisible(false);
			buttons.add(i, button);
		}
		
		this.ballImg = new ImagePanel(new ImageIcon("C:\\Placement_2\\src\\Image\\ball.png").getImage());
		background.add(ballImg);
		ballImg.setVisible(false);
		//----------------------------------------------
		
		//---------------------아군 진영-------------------
		
		Obj_Panel3 friendly_panel_1 = new Obj_Panel3();
		background.add(friendly_panel_1);
		friendly_panel.add(0, friendly_panel_1);
		friendly_panel_1.setLocation(750, 391);
		friendly_panel_1.setVisible(false); //디폴트는 안보이게
		if(friendly.get(0) != null) {
			friendly_panel_1.set_pokemon_num(friendly.get(0).getPokemonNum(), friendly.get(0).getLv());
			friendly_panel_1.set_heart(friendly.get(0).getHealth());
			friendly_panel_1.set_damage(friendly.get(0).getPower());
			friendly_panel_1.set_exp(friendly.get(0).getExp());
			friendly_panel_1.set_Lv(friendly.get(0).getLv());
			friendly_panel_1.set_ex(friendly.get(0).getAbility());
			friendly_panel_1.setVisible(true);
		}
	
		Obj_Panel3 friendly_panel_2 = new Obj_Panel3();
		background.add(friendly_panel_2);
		friendly_panel.add(1, friendly_panel_2);
		friendly_panel_2.setLocation(570, 391);
		friendly_panel_2.setVisible(false); //디폴트는 안보이게
		if(friendly.get(1) != null) {
			friendly_panel_2.set_pokemon_num(friendly.get(1).getPokemonNum(), friendly.get(1).getLv());
			friendly_panel_2.set_heart(friendly.get(1).getHealth());
			friendly_panel_2.set_damage(friendly.get(1).getPower());
			friendly_panel_2.set_exp(friendly.get(1).getExp());
			friendly_panel_2.set_Lv(friendly.get(1).getLv());
			friendly_panel_2.set_ex(friendly.get(1).getAbility());
			friendly_panel_2.setVisible(true);
		}
		
		Obj_Panel3 friendly_panel_3 = new Obj_Panel3();
		background.add(friendly_panel_3);
		friendly_panel.add(2, friendly_panel_3);
		friendly_panel_3.setLocation(390, 391);
		friendly_panel_3.setVisible(false); //디폴트는 안보이게
		if(friendly.get(2)!= null) {
			friendly_panel_3.set_pokemon_num(friendly.get(2).getPokemonNum(), friendly.get(2).getLv());
			friendly_panel_3.set_heart(friendly.get(2).getHealth());
			friendly_panel_3.set_damage(friendly.get(2).getPower());
			friendly_panel_3.set_exp(friendly.get(2).getExp());
			friendly_panel_3.set_Lv(friendly.get(2).getLv());
			friendly_panel_3.set_ex(friendly.get(2).getAbility());
			friendly_panel_3.setVisible(true);
		}
		
		
		Obj_Panel3 friendly_panel_4 = new Obj_Panel3();
		background.add(friendly_panel_4);
		friendly_panel.add(3, friendly_panel_4);
		friendly_panel_4.setLocation(210, 391);
		friendly_panel_4.setVisible(false); //디폴트는 안보이게
		if(friendly.get(3) != null) {
			friendly_panel_4.set_pokemon_num(friendly.get(3).getPokemonNum(), friendly.get(3).getLv());
			friendly_panel_4.set_heart(friendly.get(3).getHealth());
			friendly_panel_4.set_damage(friendly.get(3).getPower());
			friendly_panel_4.set_exp(friendly.get(3).getExp());
			friendly_panel_4.set_Lv(friendly.get(3).getLv());
			friendly_panel_4.set_ex(friendly.get(3).getAbility());
			friendly_panel_4.setVisible(true);
		}
		
		Obj_Panel3 friendly_panel_5 = new Obj_Panel3();
		background.add(friendly_panel_5);
		friendly_panel.add(4, friendly_panel_5);
		friendly_panel_5.setLocation(30, 391);
		friendly_panel_5.setVisible(false); //디폴트는 안보이게
		if(friendly.get(4) != null) {
			friendly_panel_5.set_pokemon_num(friendly.get(4).getPokemonNum(), friendly.get(4).getLv());
			friendly_panel_5.set_heart(friendly.get(4).getHealth());
			friendly_panel_5.set_damage(friendly.get(4).getPower());
			friendly_panel_5.set_exp(friendly.get(4).getExp());
			friendly_panel_5.set_Lv(friendly.get(4).getLv());
			friendly_panel_5.set_ex(friendly.get(4).getAbility());
			friendly_panel_5.setVisible(true);
		}
		//----------------------------------------------
		
		//---------------------적군 진영-------------------
		Obj_Panel3 enemy_panel_1 = new Obj_Panel3();
		background.add(enemy_panel_1);
		enemy_panel.add(0, enemy_panel_1);
		enemy_panel_1.setLocation(1000, 391);
		enemy_panel_1.setVisible(false); //디폴트는 안보이게
		if(enemy.get(0) != null) {
			enemy_panel_1.set_pokemon_num(enemy.get(0).getPokemonNum(), enemy.get(0).getLv());
			enemy_panel_1.set_heart(enemy.get(0).getHealth());
			enemy_panel_1.set_damage(enemy.get(0).getPower());
			enemy_panel_1.set_exp(enemy.get(0).getExp());
			enemy_panel_1.set_Lv(enemy.get(0).getLv());
			enemy_panel_1.set_ex(enemy.get(0).getAbility());
			enemy_panel_1.setVisible(true);
		}
		
		Obj_Panel3 enemy_panel_2 = new Obj_Panel3();
		background.add(enemy_panel_2);
		enemy_panel.add(1, enemy_panel_2);
		enemy_panel_2.setLocation(1180, 391);
		enemy_panel_2.setVisible(false); //디폴트는 안보이게
		if(enemy.get(1) != null) {
			enemy_panel_2.set_pokemon_num(enemy.get(1).getPokemonNum(), enemy.get(1).getLv());
			enemy_panel_2.set_heart(enemy.get(1).getHealth());
			enemy_panel_2.set_damage(enemy.get(1).getPower());
			enemy_panel_2.set_exp(enemy.get(1).getExp());
			enemy_panel_2.set_Lv(enemy.get(1).getLv());
			enemy_panel_2.set_ex(enemy.get(1).getAbility());
			enemy_panel_2.setVisible(true);
		}
		
		Obj_Panel3 enemy_panel_3 = new Obj_Panel3();
		background.add(enemy_panel_3);
		enemy_panel.add(2, enemy_panel_3);
		enemy_panel_3.setLocation(1360, 391);
		enemy_panel_3.setVisible(false); //디폴트는 안보이게
		if(enemy.get(2) != null) {
			enemy_panel_3.set_pokemon_num(enemy.get(2).getPokemonNum(), enemy.get(2).getLv());
			enemy_panel_3.set_heart(enemy.get(2).getHealth());
			enemy_panel_3.set_damage(enemy.get(2).getPower());
			enemy_panel_3.set_exp(enemy.get(2).getExp());
			enemy_panel_3.set_Lv(enemy.get(2).getLv());
			enemy_panel_3.set_ex(enemy.get(2).getAbility());
			enemy_panel_3.setVisible(true);
		}
		
		Obj_Panel3 enemy_panel_4 = new Obj_Panel3();
		background.add(enemy_panel_4);
		enemy_panel.add(3, enemy_panel_4);
		enemy_panel_4.setLocation(1540, 391);
		enemy_panel_4.setVisible(false); //디폴트는 안보이게
		if(enemy.get(3) != null) {
			enemy_panel_4.set_pokemon_num(enemy.get(3).getPokemonNum(), enemy.get(3).getLv());
			enemy_panel_4.set_heart(enemy.get(3).getHealth());
			enemy_panel_4.set_damage(enemy.get(3).getPower());
			enemy_panel_4.set_exp(enemy.get(3).getExp());
			enemy_panel_4.set_Lv(enemy.get(3).getLv());
			enemy_panel_4.set_ex(enemy.get(3).getAbility());
			enemy_panel_4.setVisible(true);
		}
		
		Obj_Panel3 enemy_panel_5 = new Obj_Panel3();
		background.add(enemy_panel_5);
		enemy_panel.add(4, enemy_panel_5);
		enemy_panel_5.setLocation(1720, 391);
		enemy_panel_5.setVisible(false); //디폴트는 안보이게
		if(enemy.get(4) != null) {
			enemy_panel_5.set_pokemon_num(enemy.get(4).getPokemonNum(), enemy.get(4).getLv());
			enemy_panel_5.set_heart(enemy.get(4).getHealth());
			enemy_panel_5.set_damage(enemy.get(4).getPower());
			enemy_panel_5.set_exp(enemy.get(4).getExp());
			enemy_panel_5.set_Lv(enemy.get(4).getLv());
			enemy_panel_5.set_ex(enemy.get(4).getAbility());
			enemy_panel_5.setVisible(true);
		}
		
		win_or_lose = new Win_or_Lose(PlacementScreen, status_panel);
		
		//----------------------------------------------
        next_btn.addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	friendlyGoFront(); // 아군 배치 조정 ( 앞으로 땡기기 )
	    		enemyGoFront(); // 적군 배치 조정 ( 앞으로 땡기기 )
	    		settingFriendlySummonModapi(); // 아군 모다피 능력 구현
	    		settingEnemySummonModapi(); // 적군 모다피 능력 구현
	    		next_btn.setVisible(false);
	    		show1();
	    		System.out.println("");
	        }
	    });
		buttons.get(0).setVisible(true);
		buttons.get(0).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ballImg.setVisible(false);
				useStartBattleAbility(); //시작시 능력 사용
				buttons.get(0).setVisible(false);
				show1();
				System.out.println("");
			}
		});
		buttons.get(1).setVisible(true);
		buttons.get(1).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				ballImg.setVisible(false);
				isDownFriendly();  // 아군 기절 확인
				isDownEnemy(); // 적군 기절 확인
				buttons.get(1).setVisible(false);
				show1();
				System.out.println("");
			}
		});
		buttons.get(3).setVisible(true);
		buttons.get(3).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (winFriendly()) {
					PlacementScreen.remove(status_panel);
	            	status_panel.up_win();
	            	PlacementScreen.add(status_panel);
	            	background.setVisible(false);
	            	win_or_lose.placementbackground.setVisible(true);      	
	            }else if(winEnemy()) {
	            	PlacementScreen.remove(status_panel);
	            	status_panel.down_heart();
	            	PlacementScreen.add(status_panel);
	            	background.setVisible(false);
	            	win_or_lose.placementbackground.setVisible(true);
	            }
	            buttons.get(3).setVisible(false);
	            show1();
	            System.out.println("");
			}
		});
		buttons.get(4).setVisible(true);
		buttons.get(4).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				useFriendlyHitAbility(); //아군 맞았을 때 능력발동
				useEnemyHitAbility();  //적군 맞았을 때 능력 발동
				buttons.get(4).setVisible(false);
				show1();
				System.out.println("");
			}
		});
		buttons.get(7).setVisible(true);
		buttons.get(7).addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				isDownFriendly(); //아군 다운됐는지 확인
				isDownEnemy(); //적군 다운됐는지 확인
				buttons.get(7).setVisible(false);
				show1();
				System.out.println("");
			}
		});
		

		for (int i = 8; i <= 60; i += 3) {
		    final int index = i;

		    buttons.get(index).setVisible(true);
		    buttons.get(index).addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            if (winFriendly()) {
		            	PlacementScreen.remove(status_panel);
		            	status_panel.up_win();
		            	win_or_lose.get_win_label().setText(Integer.toString(status_panel.get_bedge_num()));
		            	win_or_lose.get_lose_label().setText(Integer.toString(status_panel.get_life_num()));
		            	win_or_lose.get_title_label().setText("승리");
		            	PlacementScreen.add(status_panel);
		            	background.setVisible(false);
		            	win_or_lose.placementbackground.setVisible(true);
		            	
		            	
		            	
		            }else if(winEnemy()) {
		            	PlacementScreen.remove(status_panel);
		            	status_panel.down_heart();
		            	win_or_lose.get_win_label().setText(Integer.toString(status_panel.get_bedge_num()));
		            	win_or_lose.get_lose_label().setText(Integer.toString(status_panel.get_life_num()));
		            	win_or_lose.get_title_label().setText("패배");
		            	PlacementScreen.add(status_panel);
		            	background.setVisible(false);
		            	win_or_lose.placementbackground.setVisible(true);
		            }
		            buttons.get(index).setVisible(false);
		            show1();
		            System.out.println("");
		        }
		    });

		    buttons.get(index + 1).setVisible(true);
		    buttons.get(index + 1).addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            hitEnemy();
		            hitFriendly();
		        	friendlyAttackAbility(0);
		        	enemyAttackAbility(0);
		        	useFriendlyHitAbility();
		        	useEnemyHitAbility();
		            pokemonfight(friendly_panel.get(0), enemy_panel.get(0));
		            buttons.get(index + 1).setVisible(false);
		            show1();
		            System.out.println("");
		        }
		    });

		    buttons.get(index + 2).setVisible(true);
		    buttons.get(index + 2).addMouseListener(new MouseAdapter() {
		        public void mouseClicked(MouseEvent e) {
		            isDownFriendly();
		            isDownEnemy();
		            buttons.get(index + 2).setVisible(false);
		            show1();
		            System.out.println("");
		        }
		    });
		    
			for (Obj_Panel3 friendly_panels : friendly_panel) {
				friendly_panels.get_pokemon_panel().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						friendly_panels.show_ex();
					}

					@Override
					public void mouseExited(MouseEvent e) {
						friendly_panels.no_show_ex();
					}
				});
				
			}
				for (Obj_Panel3 enemy_panels : enemy_panel) {
					enemy_panels.get_pokemon_panel().addMouseListener(new MouseAdapter() {
						@Override
						public void mouseEntered(MouseEvent e) {
							enemy_panels.show_ex();
					}

						@Override
						public void mouseExited(MouseEvent e) {
							enemy_panels.no_show_ex();
					}
				});	
			}	
		}
	}
	
	
	
	
	//--------------------------------------------------------------
	 private <T extends Component> T findPanel(Container container, Class<T> panelClass) {
	        for (Component component : container.getComponents()) {
	            if (panelClass.isInstance(component)) {
	                return (T) component;  // 찾았을 경우 해당 컴포넌트 반환
	            }
	            if (component instanceof Container) {
	                T foundPanel = findPanel((Container) component, panelClass);
	                if (foundPanel != null) {
	                    return foundPanel;  // 내부 컨테이너에서 찾았을 경우 해당 컴포넌트 반환
	                }
	            }
	        }
	        return null;  // 찾지 못한 경우 null 반환
	    }
	
	void pokemonfight(Obj_Panel3 friendly_panel,Obj_Panel3 enemy_panel) {
		int targetXEnemy = enemy_panel.getX();
        int targetYEnemy = enemy_panel.getY();

        int targetXFriendly = friendly_panel.getX();
        int targetYFriendly = friendly_panel.getY();

        int steps = 60;

        int deltaXEnemy = (targetXFriendly - enemy_panel.getX()) / steps;
        int deltaYEnemy = (targetYFriendly - enemy_panel.getY()) / steps;

        int deltaXFriendly = (targetXEnemy - friendly_panel.getX()) / steps;
        int deltaYFriendly = (targetYEnemy - friendly_panel.getY()) / steps;

        Timer timer = new Timer(10, new ActionListener() {
            int stepCount = 52;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (stepCount < steps) {
                    
                    int newXEnemy = enemy_panel.getX() + deltaXEnemy - 5;
                    int newYEnemy = enemy_panel.getY() + deltaYEnemy;
                    enemy_panel.setLocation(newXEnemy, newYEnemy);

                    int newXFriendly = friendly_panel.getX() + deltaXFriendly + 5;
                    int newYFriendly = friendly_panel.getY() + deltaYFriendly;
                    friendly_panel.setLocation(newXFriendly, newYFriendly);

                    stepCount++;
                } else {
                    
                	enemy_panel.setLocation(targetXEnemy, targetYEnemy);
                	friendly_panel.setLocation(targetXFriendly, targetYFriendly);

                    ((Timer) e.getSource()).stop(); 
                }
            }
        });

        timer.start();
    }
	
	//적이 쏜다 아군한테
	void EnemydrawParabola(Obj_Panel3 friendly_panel, Obj_Panel3 enemy_panel) {
	    int enemygetX = enemy_panel.getX();
	    int enemygetY = enemy_panel.getY();

	    int centerX = (friendly_panel.getX() + enemy_panel.getX() + 50) / 2; // 포물선 중앙

	    int sX = (enemy_panel.getX() - friendly_panel.getX()) / 40; // 더 빨리 가게 하고 싶으면 30을 줄이면됨
	    
	    ballImg.setLocation(enemygetX, enemygetY);
	    ballImg.setVisible(true);

	    Timer timer = new Timer(15, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	double a = 40.0;
	    	    double x = 1.5;
	    	    
	    	    double cal = (a / 100) * ((x / 2) - a) * x + 5;
	            
	            int enemyX = ballImg.getX() - sX;
	            int enemyY = ballImg.getY() + (int) cal; // cal을 Y 위치 계산에 사용
	            
	            if (enemyX >= centerX) {
	                ballImg.setLocation(enemyX, enemyY);
	            }else if(enemyX >= (friendly_panel.getX() + 50)) { // 제약조건을 걸어야함 현재 friendly_panel_3, enemy_panel_2 기준 
	            	enemyY = ballImg.getY() - (int) cal; // - 8 값 , x값 같이 건드려서 설정하면 됨
	            	ballImg.setLocation(enemyX , enemyY);
	            }
	            else {
	            	ballImg.setVisible(false);
	            	
	            	((Timer) e.getSource()).stop();
	            }
	        }
	    });

	    timer.start();
	}
	
	void FriendlydrawParabola(Obj_Panel3 friendly_panel, Obj_Panel3 enemy_panel) {
	    int friendlygetX = friendly_panel.getX();
	    int friendlygetY = friendly_panel.getY();

	    int centerX = (friendly_panel.getX() + enemy_panel.getX()) / 2; // 포물선 중앙

	    int sX = (enemy_panel.getX() - friendly_panel.getX()) / 40; // 더 빨리 가게 하고 싶으면 30을 줄이면됨
	    
	    ballImg.setLocation(friendlygetX, friendlygetY);
	    ballImg.setVisible(true);

	    Timer timer = new Timer(15, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	double a = 40.0;
	    	    double x = 1.5;
	    	    
	    	    double cal = (a / 100) * ((x / 2) - a) * x + 5;
	            
	            int friendlyX = ballImg.getX() + sX;
	            int friendlyY = ballImg.getY() + (int) cal; // cal을 Y 위치 계산에 사용
	            
	            if (friendlyX <= centerX) {
	                ballImg.setLocation(friendlyX, friendlyY);
	            }else if(friendlyX <= enemy_panel.getX()) { // 제약조건을 걸어야함 현재 friendly_panel_3, enemy_panel_2 기준 
	            	friendlyY = ballImg.getY() - (int) cal; // - 8 값 , x값 같이 건드려서 설정하면 됨
	            	ballImg.setLocation(friendlyX , friendlyY);
	            }
	            else {
	            	ballImg.setVisible(false);
	            	
	            	((Timer) e.getSource()).stop();
	            }
	        }
	    });

	    timer.start();
	}
	
	void friendly_change_pokemon_panel(int location, int pokemon_num,
			int pokemon_Lv, int pokemon_Health, int pokemon_power, int pokemon_exp, String pokemon_ex) {
		
		friendly_panel.get(location).set_pokemon_num(pokemon_num, pokemon_Lv);
		friendly_panel.get(location).set_heart(pokemon_Health);
		friendly_panel.get(location).set_damage(pokemon_power);
		friendly_panel.get(location).set_exp(pokemon_exp);
		friendly_panel.get(location).set_Lv(pokemon_Lv);
		friendly_panel.get(location).set_ex(pokemon_ex);
		friendly_panel.get(location).setVisible(true);
	}
	
	void enemy_change_pokemon_panel(int location, int pokemon_num,
			int pokemon_Lv, int pokemon_Health, int pokemon_power, int pokemon_exp, String pokemon_ex) {
		
		enemy_panel.get(location).set_pokemon_num(pokemon_num, pokemon_Lv);
		enemy_panel.get(location).set_heart(pokemon_Health);
		enemy_panel.get(location).set_damage(pokemon_power);
		enemy_panel.get(location).set_exp(pokemon_exp);
		enemy_panel.get(location).set_Lv(pokemon_Lv);
		enemy_panel.get(location).set_ex(pokemon_ex);
		enemy_panel.get(location).setVisible(true);
	}
	
    void show_ex(JPanel panel, int seconds) {
        panel.setVisible(true);

        Timer timer = new Timer(seconds * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.setVisible(false);
            }
        });
        timer.setRepeats(false); // 한 번만 실행되도록 설정
        timer.start();
    }
	
	


	//---------------------------------------------------------------
	int run() {
		friendlyGoFront(); // 아군 배치 조정 ( 앞으로 땡기기 )
		enemyGoFront(); // 적군 배치 조정 ( 앞으로 땡기기 )
		settingFriendlySummonModapi(); // 아군 모다피 능력 구현
		settingEnemySummonModapi(); // 적군 모다피 능력 구현
		show1();
		next();
		useStartBattleAbility(); // 대결 시작 시 능력
		next();
		isDownFriendly(); // 아군 기절 확인
		next();
		isDownEnemy(); // 적군 기절 확인
		next();
		show1();
		next();
		if (winFriendly()) { // 승리 확인
			return 1;
		} else if (winEnemy()) {
			return -1;
		}
		useFriendlyHitAbility(); // 아군이 공격에 맞을 시 능력 발동
		next();
		useEnemyHitAbility(); // 적군이 공격에 맞을 시 능력 발동
		next();
		isDownFriendly();
		next();
		isDownEnemy();
		next();
		show1();
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
			show1();
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
			show1();
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
			show1();
			next();
		}
	}

	void next() {
		System.out.println("아무거나 입력");
		System.out.print(">>");
		String s = sc.next();
	}

	void settingEnemy() {
		setEnemy e = new setEnemy(turnNum);
		enemy = e.settingEnemy();
		
	}

	void findEffect() { // 배치 단계의 효과를 가져와 1이면 버섯효과 저장
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
		System.out.println("<<< 적군의 공격!! >>>");
		int adjustNum = enemy.get(0).getPower();
		if (friendly.get(0).getPokemonNum() == 38 && friendlyFirstHit38) { // 처음으로 공격에 맞을 시 무시 합니다
			System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
			System.out.println("<<< 공격이 무시 되었습니다! >>>");
			friendlyFirstHit38 = false;
			return;
		}
		if (friendlyFirstHit[0]) {
			System.out.println("<<< 버섯 효과! 공격이 무시 되었습니다! >>>");
			friendlyFirstHit[0] = false;
			return;
		}
		if (friendly.get(0).getPokemonNum() == 32) { // 10% 확률로 적군의 공격을 무시
			int rNum = random.nextInt(10);
			if (rNum == 7) {
				System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
				System.out.println("<<< 공격이 무시 되었습니다! >>>");
				return;
			}
		}
		if (friendly.get(0).getPokemonNum() == 59) { // 받는 피해가 절반이 됩니다.
			System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
			System.out.println("<<< 피해를 절반만 받습니다! >>>");
			friendlyHealthAdjust(0, -(adjustNum / 2));
			System.out.println("<<< " + friendly.get(0).getName() + "에게 " + (adjustNum / 2) + "만큼 피해! >>> ");
			return;
		} else {
			friendlyHealthAdjust(0, -(adjustNum));
			System.out.println("<<< " + friendly.get(0).getName() + "에게 " + adjustNum + "만큼 피해! >>> ");
			if (friendlyPoisonNum[0] > 0) {
				System.out.println("<<< 독 추가 피해! >>>");
				friendlyHealthAdjust(0, -1);
				friendlyPoisonNum[0]--;
			}
			return;
		}
	}

	void hitEnemy() {
		System.out.println("<<< 아군의 공격!! >>>");
		int adjustNum = friendly.get(0).getPower();
		if (enemy.get(0).getPokemonNum() == 38 && enemyFirstHit38) { // 처음으로 공격에 맞을 시 무시 합니다
			System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
			System.out.println("<<< 공격이 무시 되었습니다! >>>");
			enemyFirstHit38 = false;
			return;
		}
		if (enemyFirstHit[0]) {
			System.out.println("<<< 버섯 효과! 공격이 무시 되었습니다! >>>");
			enemyFirstHit[0] = false;
			return;
		}
		if (enemy.get(0).getPokemonNum() == 32) { // 10% 확률로 적군의 공격을 무시
			int rNum = random.nextInt(10);
			if (rNum == 7) {
				System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
				System.out.println("<<< 공격이 무시 되었습니다! >>>");
				return;
			}
		}
		if (enemy.get(0).getPokemonNum() == 59) { // 받는 피해가 절반이 됩니다.
			System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
			System.out.println("<<< 피해를 절반만 받습니다! >>>");
			enemyHealthAdjust(0, -(adjustNum / 2));
			System.out.println("<<< " + enemy.get(0).getName() + "에게 " + (adjustNum / 2) + "만큼 피해! >>> ");
			return;
		} else {
			enemyHealthAdjust(0, -(adjustNum));
			System.out.println("<<< " + enemy.get(0).getName() + "에게 " + adjustNum + "만큼 피해! >>> ");
			if (enemyPoisonNum[0] > 0) {
				System.out.println("<<< 독 추가 피해! >>>");
				enemyHealthAdjust(0, -1);
				enemyPoisonNum[0]--;
				return;
			}
			return;
		}
	}

	void useStartBattleAbility() { // 대결 시작 시 능력 사용
		System.out.println("<<< 대결 시작 시 능력 발동 >>>");
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

	void useFriendlyHitAbility() { // 공격에 맞을 시 능력 사용
		System.out.println("<<< 아군이 공격에 맞을 시 능력 발동 >>>");
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (whoHitFriendly[i] != -1) {
				friendlyHitAbility(i);
				whoHitFriendly[i] = -1;
			}
		}
	}

	void useEnemyHitAbility() { // 공격에 맞을 시 능력 사용
		System.out.println("<<< 적군이 공격에 맞을 시 능력 발동 >>>");
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (whoHitEnemy[i] != -1) {
				enemyHitAbility(i);
				whoHitEnemy[i] = -1;
			}
		}
	}

	int remainFriendlyNum() { // 아군의 위치와 아군의 남은 수를 반환
		int i = 0;
		for (Pokemon p : friendly) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}

	int remainEnemyNum() { // 적군의 위치와 아군의 남은 수를 반환
		int i = 0;
		for (Pokemon p : enemy) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}

	void show1() { // 현제 상황 보여주기
		System.out.println("<<<아군 vs 적군>>>");
		for (int i = remainFriendlyNum() - 1; i >= 0; i--) {
			if (friendly.get(i) != null) {
				System.out.print(" // " + (i + 1) + "번 " + friendly.get(i).getName() + " 공격력 : "
						+ friendly.get(i).getPower() + " 체력 : " + friendly.get(i).getHealth());
			}

		}
		System.out.print(" // VS ");
		for (int i = 0; i < remainEnemyNum(); i++) {
			System.out.print(" // " + (i + 1) + "번 " + enemy.get(i).getName() + " 공격력 : " + enemy.get(i).getPower()
					+ " 체력 : " + enemy.get(i).getHealth());
		}
		System.out.println("");
	}

	void friendlyGoFront() { // 앞에 포켓몬이 없으면 앞으로 채운다. 뒤가 null이고 5이상이면 삭제 ------
		System.out.println("<<< 아군 배치 조정 >>>");
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
					friendly_panel.get(i).setVisible(false);
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
			friendly.set(i, forreplace.get(i)); //friendly 에 앞으로 땡김 지태 코드에 맞게 내 패널들도 앞으로 땡겨짐
			switch(forreplace.get(i).getPokemonNum()) {
			case 61:
				friendly_change_pokemon_panel(i,1,2,forreplace.get(i).getHealth(),
						forreplace.get(i).getPower(), forreplace.get(i).getExp(), forreplace.get(i).getAbility());
				break;
			case 62:
				friendly_change_pokemon_panel(i,19,2,forreplace.get(i).getHealth(),
						forreplace.get(i).getPower(), forreplace.get(i).getExp(), forreplace.get(i).getAbility());
				break;
			case 63:
				friendly_change_pokemon_panel(i,28,1,forreplace.get(i).getHealth(),
						forreplace.get(i).getPower(), forreplace.get(i).getExp(),forreplace.get(i).getAbility());
				break;
			default:
				friendly_change_pokemon_panel(i,forreplace.get(i).getPokemonNum(),forreplace.get(i).getLv(),forreplace.get(i).getHealth(),
						forreplace.get(i).getPower(), forreplace.get(i).getExp(),forreplace.get(i).getAbility());
				break;
		}
			whoHitFriendly[i] = replaceHitNum[i];
			friendlyPoisonNum[i] = replacePoisonNum[i];
			friendlyFirstHit[i] = replaceFirstHit[i];
			forreplace.set(i, null);
		}
		friendlyMaxPalceNum = remainFriendlyNum();
	}

	void friendlyGoBack() { // 뒤로 한 칸씩 미룬다.
		System.out.println("<<< 아군 배치 조정 >>>");
		int number = remainFriendlyNum();
		friendly.add(0, null);
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

	void enemyGoFront() { // 앞에 포켓몬이 없으면 앞으로 채운다. 뒤가 null이고 5이상이면 삭제
		System.out.println("<<< 적군 배치 조정 >>>");
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
					enemy_panel.get(i).setVisible(false);
				}
				whoHitEnemy[i] = -1;
				enemyPoisonNum[i] = 0;
				enemyFirstHit[i] = false;
			}
			whoHitEnemy[i] = -1;
			enemyPoisonNum[i] = 0;
			enemyFirstHit[i] = false;
		}
		for (int i = 0; i < j; i++) {
			enemy.set(i, forreplace.get(i));
			whoHitEnemy[i] = replaceHitNum[i];
			enemyPoisonNum[i] = replacePoisonNum[i];
			enemyFirstHit[i] = replaceFirstHit[i];
			switch(forreplace.get(i).getPokemonNum()) {
				case 61:
					enemy_change_pokemon_panel(i,1,2,forreplace.get(i).getHealth(),
							forreplace.get(i).getPower(), forreplace.get(i).getExp(),forreplace.get(i).getAbility());
					break;
				case 62:
					enemy_change_pokemon_panel(i,19,2,forreplace.get(i).getHealth(),
							forreplace.get(i).getPower(), forreplace.get(i).getExp(),forreplace.get(i).getAbility());
					break;
				case 63:
					enemy_change_pokemon_panel(i,28,1,forreplace.get(i).getHealth(),
							forreplace.get(i).getPower(), forreplace.get(i).getExp(),forreplace.get(i).getAbility());
					break;
				default:
					enemy_change_pokemon_panel(i,forreplace.get(i).getPokemonNum(),forreplace.get(i).getLv(),forreplace.get(i).getHealth(),
							forreplace.get(i).getPower(), forreplace.get(i).getExp(),forreplace.get(i).getAbility());
					break;
			}
			forreplace.set(i, null);
			
		}
		enemyMaxPalceNum = remainEnemyNum();
	}

	void enemyGoBack() { // 뒤로 한 칸씩 미룬다.
		System.out.println("<<< 적군 배치 조정 >>>");
		int number = remainEnemyNum();
		enemy.add(0, null);
		//enemy_panel.add(0, null);
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
			System.out.println("<<< 적군 승리! >>>");
			return true;
		}
		return false;
	}

	boolean winFriendly() {
		if (remainEnemyNum() == 0) {
			System.out.println("<<< 아군 승리! >>>");
			return true;
		}
		return false;
	}

	void isDownFriendly() { // 기절 시 없앤다
		System.out.println("<<< 아군 기절 확인 >>>");
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getHealth() <= 0) {
					System.out.println("<<< " + friendly.get(i).getName() + " 기절! >>>");
					friendlyFrontDownAbility(i);
					friendlyDownAbility(i);
					friendlyGoFront();
				}
			}

		}
	}

	void isDownEnemy() { // 기절 시 없앤다
		System.out.println("<<< 적군 기절 확인 >>>");
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getHealth() <= 0) {
					System.out.println("<<< " + enemy.get(i).getName() + " 기절! >>>");
					enemyFrontDownAbility(i);
					enemyDownAbility(i);
					enemyGoFront();
				}
			}

		}
	}

	void friendlyHealthAdjust(int placeNum, int adjustNum) { // 아군 체력 조정
		if (friendly.get(placeNum) != null) {
			if (placeNum < 4 && friendly.get(placeNum + 1) != null) {
				if (friendly.get(placeNum + 1).getPokemonNum() == 42) {
					System.out.println("<<< " + friendly.get(placeNum + 1).getName() + " 능력 사용! >>>");
					friendly.get(placeNum).setHealth(friendly.get(placeNum).getHealth() + (adjustNum / 10 * 8));
					friendly_panel.get(placeNum).set_heart(friendly_panel.get(placeNum).get_heart() + (adjustNum / 10 * 8));
					friendly.get(placeNum + 1).setHealth(friendly.get(placeNum + 1).getHealth() + (adjustNum / 10 * 2));
					friendly_panel.get(placeNum + 1).set_heart(enemy_panel.get(placeNum + 1).get_heart() + (adjustNum / 10 * 2));
					System.out.println("<<< " + friendly.get(placeNum + 1).getName() + " 가 "
							+ (friendly.get(placeNum).getHealth() + adjustNum) / 10 * 2 + " 만큼 대신 맞아줬습니다! >>>");
					return;
				}
			}
			friendly.get(placeNum).setHealth(friendly.get(placeNum).getHealth() + adjustNum);
			friendly_panel.get(placeNum).set_heart(friendly_panel.get(placeNum).get_heart() + adjustNum);
			return;
		}
	}

	void friendlyPowerAdjust(int placeNum, int adjustNum) { // 아군 공격력 조정
		if (friendly.get(placeNum) != null)
			friendly.get(placeNum).setPower(friendly.get(placeNum).getPower() + adjustNum);
			friendly_panel.get(placeNum).set_damage(friendly_panel.get(placeNum).get_damage() + adjustNum);
	}

	void enemyHealthAdjust(int placeNum, int adjustNum) { // 적군 체력 조정
		if (enemy.get(placeNum) != null) {
			if (placeNum < 4 && enemy.get(placeNum + 1) != null) {
				if (enemy.get(placeNum + 1).getPokemonNum() == 42) { // 앞에 있는 아군 공격에 맞을 시 // 20% 대신 맞아줍니다.
					System.out.println("<<< " + enemy.get(placeNum + 1).getName() + " 능력 사용! >>>");
					enemy.get(placeNum).setHealth(enemy.get(placeNum).getHealth() + (adjustNum / 10 * 8));
					enemy_panel.get(placeNum).set_heart(enemy_panel.get(placeNum).get_heart() + (adjustNum / 10 * 8));
					enemy.get(placeNum + 1).setHealth(enemy.get(placeNum + 1).getHealth() + (adjustNum / 10 * 2));
					enemy_panel.get(placeNum + 1).set_heart(enemy_panel.get(placeNum + 1).get_heart() + (adjustNum / 10 * 2));
					
					System.out.println("<<< " + enemy.get(placeNum + 1).getName() + " 가 "
							+ (enemy.get(placeNum).getHealth() + adjustNum) / 10 * 2 + " 만큼 대신 맞아줬습니다! >>>");
					return;
				}
			}
			enemy.get(placeNum).setHealth(enemy.get(placeNum).getHealth() + adjustNum);
			enemy_panel.get(placeNum).set_heart(enemy_panel.get(placeNum).get_heart() + adjustNum);
			return;
		}
	}

	void enemyPowerAdjust(int placeNum, int adjustNum) { // 적군 공격력 조정
		if (enemy.get(placeNum) != null)
			enemy.get(placeNum).setPower(enemy.get(placeNum).getPower() + adjustNum);
			enemy_panel.get(placeNum).set_damage(enemy_panel.get(placeNum).get_damage() + adjustNum);
	}

	void findHitEnemy(int hit, int who) { // 적군을 때린 아군의 위치를 저장
		if (whoHitEnemy[hit] == -1) {
			whoHitEnemy[hit] = who;
		}
	}

	void findHitfriendly(int hit, int who) {// 아군을 때린 적군의 위치를 저장
		if (whoHitFriendly[hit] == -1) {
			whoHitFriendly[hit] = who;
		}
	}

	void friendlyAbillity45() {// 45번 능력을 위해 HP 저장
		for (Pokemon p : friendly) {
			if (p.getPokemonNum() == 45) {
				friendlyHP45 = p.getHealth();
				return;
			}
		}
	}

	void enemyAbillity45() {// 45번 능력을 위해 HP 저장
		for (Pokemon p : enemy) {
			if (p.getPokemonNum() == 45) {
				enemyHP45 = p.getHealth();
				return;
			}
		}
	}

	void settingFriendlySummonModapi() { // 모다피 능력 세팅
		System.out.println("<<< 아군 모다피 능력 구현 >>>");
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i).getPokemonNum() == 28) {
				mo.setHealth(friendly.get(i).getHealth() * 2);
				friendly_panel.get(i).set_heart(friendly.get(i).getHealth() * 2);
			}
		}
		mo.setHealth(3);
	}

	void settingEnemySummonModapi() { // 모다피 능력 세팅
		System.out.println("<<< 적군 모다피 능력 구현 >>>");
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i).getPokemonNum() == 28) {
				mo.setHealth(enemy.get(i).getHealth() * 2);
				enemy_panel.get(i).set_heart(enemy.get(i).getHealth() * 2);
			}
		}
		mo.setHealth(3);
	}

	// ------------ 능력 함수 -----------------

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

	void friendlyStartBattleAbility(int placeNum) { // 대결 시작 시 능력
		int lv54 = findFriendlylv(54);
		int lv = friendly.get(placeNum).getLv();
		switch (friendly.get(placeNum).getPokemonNum()) {
		case 3: { // 대결 시작 시 적군 랜덤 하나에게 2, 3, 4 피해
			int abilityNum = lv + 1;
			while (true) {
				int rNum = random.nextInt(remainEnemyNum());
				if (enemy.get(rNum) != null) {
					System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
					show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
					System.out.println("<<< " + friendly.get(placeNum).getAbility() + " >>>");
					if (friendlyFindPokemon(54)) {
						if (rNum - 1 >= 0 && enemy.get(rNum - 1) != null) {
							if (enemy.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(rNum - 1).getName() + " 능력 사용! >>>");
								rNum -= 1;
								System.out.println("<<< 대신 맞습니다! >>>");
							}
						}
						System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
						enemyHealthAdjust(rNum, -(abilityNum + lv54));
						findHitEnemy(rNum, placeNum);
						System.out
								.println("<<< " + enemy.get(rNum).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
					} else {
						if (rNum - 1 >= 0 && enemy.get(rNum - 1) != null) {
							if (enemy.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(rNum - 1).getName() + " 능력 사용! >>>");
								rNum -= 1;
								System.out.println("<<< 대신 맞습니다! >>>");
							}
						}
						enemyHealthAdjust(rNum, -abilityNum);
						findHitEnemy(rNum, placeNum);
						System.out.println("<<< " + enemy.get(rNum).getName() + "에게 " + (abilityNum) + " 피해! >>>");
						EnemydrawParabola(friendly_panel.get(placeNum) , enemy_panel.get(rNum));
						
					}
					return;
				}
			}
		}
		case 10: { // 대결 시작 시 가장 뒤에 있는 적군 1, 2, 3 피해
			int enemyPlaceNum = remainEnemyNum() - 1;
			int abilityNum = lv;
			if (enemyPlaceNum < 0) {
				return;
			}
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
			if (friendlyFindPokemon(54)) {
				if (enemyPlaceNum - 1 >= 0 && enemy.get(enemyPlaceNum - 1) != null) {
					if (enemy.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(enemyPlaceNum - 1).getName() + " 능력 사용! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< 대신 맞습니다! >>>");
					}
				}
				System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
				enemyHealthAdjust(enemyPlaceNum, -(abilityNum + lv54));
				findHitEnemy(enemyPlaceNum, placeNum);
				System.out.println(
						"<<< " + enemy.get(enemyPlaceNum).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
			} else {
				if (enemyPlaceNum - 1 >= 0 && enemy.get(enemyPlaceNum - 1) != null) {
					if (enemy.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(enemyPlaceNum - 1).getName() + " 능력 사용! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< 대신 맞습니다! >>>");
					}
				}
				enemyHealthAdjust(enemyPlaceNum, -abilityNum);
				findHitEnemy(enemyPlaceNum, placeNum);
				System.out.println("<<< " + enemy.get(enemyPlaceNum).getName() + "에게 " + (abilityNum) + " 피해! >>>");
			}
			return;

		}
		case 12: { // 대결 시작 시 모든 적군 1, 2, 3 피해
			int enemyPlaceNum = remainEnemyNum();
			int abilityNum = lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용>>>");
			show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
			for (int i = 0; i < enemyPlaceNum; i++) {
				if (friendlyFindPokemon(54)) {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52 || i - 1 >= 0) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
							enemyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitEnemy(enemyPlaceNum - 1, placeNum);
							System.out.println(
									"<<< " + enemy.get(i - 1).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
						}
					} else {
						System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
						enemyHealthAdjust(i, -(abilityNum + lv54));
						findHitEnemy(enemyPlaceNum, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
					}
				} else {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							enemyHealthAdjust(i - 1, -abilityNum);
							findHitEnemy(enemyPlaceNum - 1, placeNum);
							System.out.println("<<< " + enemy.get(i - 1).getName() + "에게 " + (abilityNum) + " 피해! >>>");
						}
					} else {
						enemyHealthAdjust(i, -abilityNum);
						findHitEnemy(enemyPlaceNum, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "에게 " + (abilityNum) + " 피해! >>>");
					}
				}
			}
			return;
		}
		case 23: { // 대결 시작 시 앞에 있는 아군에게 체력 50%, 70%, 100% 만큼 증가
			if (placeNum != 0) {
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = friendly.get(placeNum).getHealth() / 2;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getHealth() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getHealth();
				}
				friendlyHealthAdjust(placeNum - 1, adjustNum);
				System.out
						.println("<<< " + friendly.get(placeNum - 1).getName() + "에게  체력 " + adjustNum + "만큼 증가! >>>");
				return;
			}
			return;
		}
		case 26: { // 대결 시작 시 앞에 있는 아군에게 공격력 50%, 70%, 100% 만큼 추가
			int lv25 = findFriendlylv(25);
			if (placeNum != 0) {
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = friendly.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getPower() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getPower();
				}
				friendlyPowerAdjust(placeNum - 1, adjustNum);
				System.out
						.println("<<< " + friendly.get(placeNum - 1).getName() + "에게 공격력 " + adjustNum + "만큼 증가! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					friendlyPowerAdjust(placeNum - 1, lv25);
					System.out
							.println("<<< " + friendly.get(placeNum - 1).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			return;
		}
		case 29: {// 대결 시작 시 가장 뒤에 있는 적군에게 1, 2, 3 독 피해
			int abilityNum = lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
			int enemyPlaceNum = remainEnemyNum() - 1;
			if (enemyPlaceNum - 1 >= 0 && enemy.get(enemyPlaceNum - 1) != null) {
				if (enemy.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
					System.out.println("<<< " + enemy.get(enemyPlaceNum - 1).getName() + " 능력 사용! >>>");
					enemyPlaceNum -= 1;
					System.out.println("<<< 대신 맞습니다! >>>");
				}
			}
			friendlyPoisonNum[enemyPlaceNum] += abilityNum;
			System.out.println("<<< " + enemy.get(enemyPlaceNum).getName() + "에게 " + abilityNum + " 독 피해! >>>");
			return;
		}
		case 31: { // 대결 시작 시 모든 유닛 1, 2, 3 피해
			int abilityNum = lv;
			int friendlyNum = remainFriendlyNum();
			int enemyNum = remainEnemyNum();
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
			for (int i = 0; i < friendlyNum; i++) {
				if (i - 1 >= 0 && friendly.get(i - 1) != null) {
					if (friendly.get(i - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
						System.out.println("<<< 대신 맞습니다! >>>");
						friendlyHealthAdjust(i - 1, -abilityNum);
						System.out.println("<<< " + friendly.get(i - 1).getName() + "에게 " + abilityNum + " 피해! >>>");
						findHitfriendly(i - 1, -1);
					}
				} else {
					friendlyHealthAdjust(i, -abilityNum);
					System.out.println("<<< " + friendly.get(i).getName() + "에게 " + abilityNum + " 피해! >>>");
					findHitfriendly(i, -1);
				}

			}
			if (friendlyFindPokemon(54)) {
				System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
				for (int i = 0; i < enemyNum; i++) {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							enemyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitEnemy(i - 1, placeNum);
							System.out.println(
									"<<< " + enemy.get(i - 1).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
						}
					} else {
						enemyHealthAdjust(i, -(lv54 + abilityNum));
						findHitEnemy(i, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
					}
				}
			} else {
				for (int i = 0; i < enemyNum; i++) {
					if (i - 1 >= 0 && enemy.get(i - 1) != null) {
						if (enemy.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							enemyHealthAdjust(i - 1, -abilityNum);
							findHitEnemy(i - 1, placeNum);
							System.out.println("<<< " + enemy.get(i - 1).getName() + "에게 " + (abilityNum) + " 피해! >>>");
						}
					} else {
						enemyHealthAdjust(i, -abilityNum);
						findHitEnemy(i, placeNum);
						System.out.println("<<< " + enemy.get(i).getName() + "에게 " + (abilityNum) + " 피해! >>>");
					}
				}
			}
			return;
		}
		case 37: { // 대결 시작 시 모든 아군의 체력 30%, 40%, 50% 만큼 증가
			int friendlyNum = remainFriendlyNum();
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
			int adjustNum = friendly.get(placeNum).getHealth() * 3 / 10;
			if (lv == 2) {
				adjustNum = friendly.get(placeNum).getHealth() * 4 / 10;
			} else if (lv == 3) {
				adjustNum = friendly.get(placeNum).getHealth() * 5 / 10;
			}
			for (int i = 0; i < friendlyNum; i++) {
				friendlyHealthAdjust(i, adjustNum);
				System.out.println("<<< " + friendly.get(i).getName() + "에게 체력 " + adjustNum + "만큼 증가! >>>");
			}
			return;
		}
		case 50: { // 대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다.
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
			int adjustNum = friendly.get(placeNum).getPower();
			if (friendlyFindPokemon(54)) {
				if (placeNum - 1 >= 0 && enemy.get(placeNum - 1) != null) {
					if (enemy.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(placeNum - 1).getName() + " 능력 사용! >>>");
						System.out.println("<<< 대신 맞습니다! >>>");
						System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
						enemyHealthAdjust(placeNum - 1, -(adjustNum + lv54));
						findHitEnemy(placeNum - 1, placeNum);
						System.out.println(
								"<<< " + enemy.get(placeNum - 1).getName() + "에게 " + (adjustNum + lv54) + " 피해! >>>");
					}
				} else {
					System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
					enemyHealthAdjust(placeNum, -(adjustNum + lv54));
					findHitEnemy(placeNum, placeNum);
					System.out
							.println("<<< " + enemy.get(placeNum).getName() + "에게 " + (adjustNum + lv54) + " 피해! >>>");
				}

				return;
			} else {
				if (placeNum - 1 >= 0 && enemy.get(placeNum - 1) != null) {
					if (enemy.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(placeNum - 1).getName() + " 능력 사용! >>>");
						System.out.println("<<< 대신 맞습니다! >>>");
						enemyHealthAdjust(placeNum - 1, -friendly.get(placeNum).getPower());
						findHitEnemy(placeNum - 1, placeNum);
						System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "에게 " + adjustNum + " 피해! >>>");
					}
				} else {
					enemyHealthAdjust(placeNum, -friendly.get(placeNum).getPower());
					findHitEnemy(placeNum, placeNum);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "에게 " + adjustNum + " 피해! >>>");
				}
				return;
			}
		}
		default: {
			return;
		}
		}
	}

	void friendlyAttackAbility(int placeNum) { // 공격 시 능력
		System.out.println("<<< 아군 공격 시 능력 발동 >>>");
		int lv25 = findFriendlylv(25);
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 5: { // 공격 시 공격력 2, 3, 4 증가
				int abilityNum = 1 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 공격력 " + abilityNum + "만큼 증가! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			case 16: { // 공격 시 뒤에 있는 아군 체력 2, 3, 4 증가
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				if (friendly.get(placeNum + 1) != null) {
					friendlyHealthAdjust(placeNum + 1, lv + 1);
					System.out.println(
							"<<< " + friendly.get(placeNum + 1).getName() + "에게 체력 " + (lv + 1) + "만큼 증가! >>>");
				}
				return;
			}
			case 22: { // 공격 시 랜덤한 적군에게 공격력 50%, 70%, 100% 의 피해
				while (true) {
					int rNum = random.nextInt(5);
					if (enemy.get(rNum) != null) {
						System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
						show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
						int adjustNum = friendly.get(placeNum).getPower() / 2;
						if (lv == 2) {
							adjustNum = friendly.get(placeNum).getPower() * 7 / 10;
						} else if (lv == 3) {
							adjustNum = friendly.get(placeNum).getPower();
						}
						enemyHealthAdjust(rNum, adjustNum);
						System.out.println("<<< " + enemy.get(rNum).getName() + "에게 " + adjustNum + " 피해! >>>");
						whoHitEnemy[rNum] = placeNum;
						return;
					}
				}
			}
			case 24: { // 공격시 25%, 35%, 50% 확률로 추가 공격
				if (lv == 1) {
					int rNum = random.nextInt(4);
					if (rNum == 0) {
						System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
						show_ex(friendly_panel.get(0).get_expanel(), 2);
						hitEnemy();
					}
					return;
				} else if (lv == 2) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
						show_ex(friendly_panel.get(0).get_expanel(), 2);
						hitEnemy();
					}
					return;
				} else if (lv == 3) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
						show_ex(friendly_panel.get(0).get_expanel(), 2);
						hitEnemy();
					}
					return;
				}

			}
			case 27: { // 공격 시 공격력 4, 5, 6 증가
				int abilityNum = 3 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			case 40: { // 공격 시 공격력, 체력 3, 4, 5 증가
				int abilityNum = 2 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			case 44: { // 이 포켓몬은 2번 공격 합니다
				System.out.println("<<< " + friendly.get(0).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(0).get_expanel(), 2);
				hitEnemy();
				return;

			}
			case 46: { // 공격 시 적군의 공격력 20%, 30%, 50% 만큼 감소합니다
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = enemy.get(0).getPower() / 5;
				if (lv == 2) {
					adjustNum = enemy.get(0).getPower() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(0).getPower() / 2;
				}
				enemyPowerAdjust(0, -(adjustNum));
				System.out.println("<<< " + enemy.get(0).getName() + " 공격력 " + adjustNum + " 만큼 감소! >>>");
				return;
			}
			case 58: { // 공격 시 공격력의 50%, 60%, 75%만큼 그 뒤에있는 적군에게 피해
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = friendly.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getPower() * 6 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getPower() * 75 / 100;
				}
				enemyPowerAdjust(1, -(adjustNum));
				System.out.println("<<< " + enemy.get(1).getName() + "에게 " + adjustNum + " 만큼 추가 피해! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyFrontFightAbillity(int placeNum) { // 앞의 아군 공격 시 능력
		System.out.println("<<< 앞에 있는 아군 공격 시 능력 발동 >>>");
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum + 1) != null) {
			switch (friendly.get(placeNum + 1).getPokemonNum()) {
			case 9: { // 앞의 아군이 공격 시 앞의 아군 체력 2, 3, 4 증가
				int abilityNum = 1 + lv;
				System.out.println("<<< " + friendly.get(placeNum + 1).getName() + " 능력 사용! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
				return;
			}
			case 55: { // 앞의 아군 공격 시 1번 더 공격시킵니다.
				System.out.println("<<< " + friendly.get(1).getName() + " 능력 사용! >>>");
				hitEnemy();
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyHitAbility(int placeNum) { // 맞을 시 능력
		System.out.println("<<< 아군이 맞을 시 능력 발동 >>>");
		int lv = friendly.get(placeNum).getLv();
		int lv25 = findFriendlylv(25);
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 4: { // 공격에 맞을 시 공격한 대상에게 1, 2, 3 피해
				int enemyNum = whoHitFriendly[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
					if (enemyNum > 0) {
						if (enemy.get(enemyNum - 1) != null) {
							if (enemy.get(enemyNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(enemyNum - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								enemyNum -= 1;
							}
						}
					}
					enemyHealthAdjust(enemyNum, -lv);
					System.out.println("<<< " + enemy.get(enemyNum).getName() + "에게 " + lv + " 피해! >>>");
					whoHitFriendly[placeNum] = -1;
					return;
				}
				return;
			}
			case 11: { // 공격에 맞을 시 공격한 적군 공격력 1, 2, 3 감소
				if (whoHitFriendly[placeNum] != -1) {
					int enemyNum = whoHitFriendly[placeNum];
					System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
					enemyPowerAdjust(enemyNum, -lv);
					System.out.println("<<< " + enemy.get(enemyNum).getName() + " 공격력 " + lv + " 감소! >>>");
					whoHitFriendly[placeNum] = -1;
					return;
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 21: { // 공격에 맞을 시 공격력,체력 2, 3, 4 증가
				int abilityNum = 1 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 30: { // 공격에 맞을 시 체력 4, 5, 6 증가
				int abilityNum = 3 + lv;
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + abilityNum + "만큼 증가! >>>");
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 39: { // 공격에 맞을 시 공격한 적군에게 1, 2, 3 독 피해
				int enemyNum = whoHitFriendly[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
					friendlyPoisonNum[enemyNum] += lv;
					System.out.println("<<< " + enemy.get(enemyNum).getName() + "에게 " + lv + " 독 피해! >>>");
					whoHitFriendly[placeNum] = -1;
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 45: { // 공격에 맞을 시 피해량 20%, 30%, 50%만큼 돌려줍니다
				int enemyNum = whoHitFriendly[placeNum];
				if (enemyNum != -1) {
					int adjustNum = (friendlyHP45 - friendly.get(placeNum).getHealth()) / 5;
					if (lv == 2) {
						adjustNum = (friendlyHP45 - friendly.get(placeNum).getHealth()) * 3 / 10;
					} else if (lv == 3) {
						adjustNum = (friendlyHP45 - friendly.get(placeNum).getHealth()) / 2;
					}
					System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
					if (enemy.get(enemyNum - 1) != null) {
						if (enemy.get(enemyNum - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + enemy.get(enemyNum - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							enemyNum -= 1;
						}
					}
					enemyHealthAdjust(enemyNum, adjustNum);
					System.out.println("<<< " + enemy.get(enemyNum).getName() + "에게 " + adjustNum + " 피해! >>>");
					friendlyAbillity45();
					whoHitFriendly[placeNum] = -1;
				}
				whoHitFriendly[placeNum] = -1;
				return;
			}
			case 57: { // 공격에 맞을 시 체력의 20%, 30%, 50% 만큼 체력 증가
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				int adjustNum = friendly.get(placeNum).getHealth() / 5;
				if (lv == 2) {
					adjustNum = friendly.get(placeNum).getHealth() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(placeNum).getHealth() / 2;
				}
				friendlyHealthAdjust(placeNum, adjustNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + adjustNum + "만큼 증가! >>>");
				whoHitFriendly[placeNum] = -1;
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyDownAbility(int placeNum) { // 기절 시 능력
		System.out.println("<<< 아군 기절 시 능력 발동 >>>");
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 1: { // 기절 시 능력 없는 2/2단데기 1,2, 3 소환
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				for (int i = 0; i < lv; i++) {
					friendlyGoBack();
					friendly.set(0, dan);
					friendly_panel.get(0).set_pokemon_num(1, 2);
					friendly_panel.get(0).set_exp(1);
					friendly_panel.get(0).set_damage(2);
					friendly_panel.get(0).set_heart(2);
					friendly_panel.get(0).set_Lv(2);
					friendly_panel.get(0).setVisible(true);
					System.out.println("<<< " + "단데기" + " 소환! >>>");
					friendlyUseToSummon();
					friendlyUseSummonAbility();
					enemyFriendlySummonAbility();
				}
				friendly.set(placeNum + lv, null);   
				return;
			}
			case 6: { // 기절 시 가장 뒤에 있는 적군 1, 2, 3 독 피해
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				int i = remainEnemyNum() - 1;
				friendlyPoisonNum[i] += lv;
				System.out.println("<<< " + enemy.get(i).getName() + "에게 " + lv + " 독 피해! >>>");
				friendly.set(placeNum, null);
				friendly_panel.get(placeNum).setVisible(false);
				return;
			}
			case 18: { // 기절 시 가장 앞에 있는 적군 2, 3, 4 피해
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				int abilityNum = 1 + lv;
				if (enemy.get(0) == null) {
					return;
				}
				enemyHealthAdjust(0, abilityNum);
				System.out.println("<<< " + enemy.get(0).getName() + "에게 " + abilityNum + " 피해! >>>");
				findHitEnemy(0, placeNum);
				friendly.set(placeNum, null);
				friendly_panel.get(placeNum).setVisible(false);
				return;
			}
			case 19: { // 기절 시 능력 없는 망키 1, 2, 3 소환
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				for (int i = 0; i < lv; i++) {
					friendlyGoBack();
					friendly.set(0, mang);
					System.out.println("<<< " + "망키" + " 소환! >>>");
					friendlyUseToSummon();
					friendlyUseSummonAbility();
					enemyFriendlySummonAbility();
				}
				friendly.set(placeNum + lv, null);
				return;
			}

			case 28: { // 기절 시 체력 2, 2.5, 3배인 능력 없는 모다피 2 소환
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(friendly_panel.get(placeNum).get_expanel(), 2);
				if (lv == 2) {
					mo.setHealth(mo.getHealth() / 2 * 25 / 10);
				} else if (lv == 3) {
					mo.setHealth(mo.getHealth() / 2 * 3);
				}
				friendlyGoBack();
				friendly.set(0, mo);
				friendly.set(placeNum, null);
				System.out.println("<<< " + "모다피" + " 소환! >>>");
				friendlyUseToSummon();
				friendlyUseSummonAbility();
				enemyFriendlySummonAbility();
				friendlyGoBack();
				friendly.set(0, mo);
				friendly.set(placeNum, null);
				System.out.println("<<< " + "모다피" + " 소환! >>>");
				friendlyUseToSummon();
				friendlyUseSummonAbility();
				enemyFriendlySummonAbility();
				return;
			}
			default: {
				friendly.set(placeNum, null);
				friendly_panel.get(placeNum).setVisible(false);
				return;
			}
			}
		}
	}

	void friendlyFrontDownAbility(int placeNum) { // 앞의 아군 기절 시
		System.out.println("<<< 앞의 아군 기절 시 능력 발동 >>>");
		int lv = friendly.get(placeNum).getLv();
		if (friendly.get(placeNum + 1) != null) {
			switch (friendly.get(placeNum + 1).getPokemonNum()) {
			case 20: { // 앞의 아군 기절 시 모든 적군 1, 2, 3 피해
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				for (int i = 0; i < remainEnemyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && enemy.get(i - 1) != null) {
							if (enemy.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								enemyHealthAdjust(i - 1, -lv);
								System.out.println("<<< " + enemy.get(i - 1).getName() + "에게 " + lv + " 피해! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -lv);
							System.out.println("<<< " + enemy.get(i).getName() + "에게 " + lv + " 피해! >>>");
							findHitEnemy(i, -1);
						}

					}
				}
				return;
			}
			case 35: { // 앞에 있는 아군 기절 시 모든 유닛에게 2, 3, 4 피해
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				int abilityNum = 1 + lv;
				for (int i = 0; i < remainEnemyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && enemy.get(i - 1) != null) {
							if (enemy.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								enemyHealthAdjust(i - 1, -abilityNum);
								System.out
										.println("<<< " + enemy.get(i - 1).getName() + "에게 " + abilityNum + " 피해! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + enemy.get(i).getName() + "에게 " + abilityNum + " 피해! >>>");
							findHitEnemy(i, -1);
						}

					}
				}
				for (int i = 0; i < remainFriendlyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && friendly.get(i - 1) != null) {
							if (friendly.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								enemyHealthAdjust(i - 1, -abilityNum);
								System.out.println(
										"<<< " + friendly.get(i - 1).getName() + "에게 " + abilityNum + " 피해! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + friendly.get(i).getName() + "에게 " + abilityNum + " 피해! >>>");
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

	void friendlyDownEnemyAbility(int placeNum) { // 적 기절 시 능력
		System.out.println("<<< 적군 기절 시 능력 발동 >>>");
		int lv = friendly.get(placeNum).getLv();
		int abilityNum = 3 + lv;
		int lv25 = findFriendlylv(25);
		if (friendly.get(placeNum) != null) {
			switch (friendly.get(placeNum).getPokemonNum()) {
			case 47: {
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
				friendlyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				if (friendlyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					friendlyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
				}
				friendlyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + abilityNum + "만큼 증가! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void friendlyUseToSummon() { // 소환 시 능력 소환괸 아군에게 쓰는 능력
		System.out.println("<<< 아군 소환 시 능력 발동 >>>");
		int lv15 = findFriendlylv(15);
		int lv34 = findFriendlylv(34);
		int lv51 = findFriendlylv(51);
		int lv25 = findFriendlylv(25);
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == 15) { // 아군 소환시 소환된 아군 공격력,체력 2, 3, 4 증가
					int abilityNum = 1 + lv15;
					System.out.println("<<< 콘팡 능력 사용! >>>");
					friendlyPowerAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 공격력 " + abilityNum + " 증가! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						friendlyPowerAdjust(0, lv25);
						System.out.println("<<< " + friendly.get(0).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
					}
					friendlyHealthAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 체력 " + abilityNum + " 증가! >>>");
				} else if (friendly.get(i).getPokemonNum() == 34) { // 아군 소환 시 소환된 유닛 공격력 2, 3, 4 증가
					int abilityNum = 1 + lv34;
					System.out.println("<<< 파오리 능력 사용! >>>");
					friendlyPowerAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 공격력 " + abilityNum + " 증가! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						friendlyPowerAdjust(0, lv25);
						System.out.println("<<< " + friendly.get(0).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
					}
				} else if (friendly.get(i).getPokemonNum() == 51) { // 소환된 아군 공격력, 체력 3, 4, 5증가
					int abilityNum = 2 + lv51;
					System.out.println("<<< 콘치 능력 사용! >>>");
					friendlyPowerAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 공격력 " + abilityNum + " 증가! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						friendlyPowerAdjust(0, lv25);
						System.out.println("<<< " + friendly.get(0).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
					}
					friendlyHealthAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 체력 " + abilityNum + " 증가! >>>");
				}
			}
		}
	}

	void friendlyUseSummonAbility() { // 아군 소환 시 능력 사용
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == 2 || friendly.get(i).getPokemonNum() == 41) {
					friendlySummonAbility(i);
				}
			}
		}
	}

	void friendlySummonAbility(int placeNum) { // 아군 소환 시 능력
		System.out.println("<<< 아군 소환 시 능력 발동 >>>");
		int lv = friendly.get(placeNum).getLv();
		int lv25 = findFriendlylv(25);
		switch (friendly.get(placeNum).getPokemonNum()) {
		case 2: { // 아군 소환 시 체력 2, 3, 4 증가
			int abilityNum = 1 + lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			friendlyHealthAdjust(placeNum, abilityNum);
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
			return;
		}

		case 41: { // 아군 소환 시 랜덤으로 아군 공격력,체력 2, 3, 4 증가
			int abilityNum = 1 + lv;
			System.out.println("<<< " + friendly.get(placeNum).getName() + " 능력 사용! >>>");
			while (true) {
				int rNum = random.nextInt(5);
				if (friendly.get(rNum) != null) {
					friendlyHealthAdjust(rNum, abilityNum);
					System.out.println("<<< " + friendly.get(rNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
					friendlyPowerAdjust(rNum, abilityNum);
					System.out.println("<<< " + friendly.get(rNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
					if (friendlyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						friendlyPowerAdjust(rNum, lv25);
						System.out.println("<<< " + friendly.get(rNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
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

	void friendlyEnemySummonAbility() { // 적군 소환 시
		System.out.println("<<< 적군 소환 시 능력 발동 >>>");
		int lv = findFriendlylv(43);
		int abilityNum = 2 + lv;
		for (int i = 0; i < remainFriendlyNum(); i++) {
			if (friendly.get(i) != null) {
				if (friendly.get(i).getPokemonNum() == 43) { // 적군 소환 시 랜덤한 적군 1명 에게 3 피해
					System.out.println("<<< " + friendly.get(i).getName() + " 능력 사용! >>>");
					while (true) {
						int rNum = random.nextInt();
						if (enemy.get(rNum) != null) {
							if (rNum - 1 >= 0 && enemy.get(rNum - 1) != null) {
								if (enemy.get(rNum - 1).getPokemonNum() == 52) {
									System.out.println("<<< " + enemy.get(rNum - 1).getName() + " 능력 사용! >>>");
									System.out.println("<<< 대신 맞습니다! >>>");
									rNum -= 1;
								}
							}
							enemyHealthAdjust(rNum, abilityNum);
							System.out.println("<<< " + enemy.get(rNum).getName() + "에게 " + abilityNum + " 피해! >>>");
							return;
						}
					}
				}
			}

		}
	}

	boolean friendlyFindPokemon(int pokemonNum) { // 아군에 있는지 확인
		for (Pokemon p : friendly) {
			if (p != null) {
				if (p.getPokemonNum() == pokemonNum) {
					return true;
				}
			}
		}
		return false;
	}

	// ------------- 적군 능력 함수 --------------------

	// ------------ 능력 함수 -----------------

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

	void enemyStartBattleAbility(int placeNum) { // 대결 시작 시 능력
		int lv54 = findEnemylv(54);
		int lv = enemy.get(placeNum).getLv();
		switch (enemy.get(placeNum).getPokemonNum()) {
		case 3: { // 대결 시작 시 적군 랜덤 하나에게 2, 3, 4 피해
			int abilityNum = lv + 1;
			while (true) {
				int rNum = random.nextInt(remainFriendlyNum());
				if (friendly.get(rNum) != null) {
					System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
					show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
					System.out.println("<<< " + enemy.get(placeNum).getAbility() + " >>>");
					if (enemyFindPokemon(54)) {
						if (rNum - 1 >= 0 && friendly.get(rNum - 1) != null) {
							if (friendly.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(rNum - 1).getName() + " 능력 사용! >>>");
								rNum -= 1;
								System.out.println("<<< 대신 맞습니다! >>>");
							}
						}
						System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
						friendlyHealthAdjust(rNum, -(abilityNum + lv54));
						findHitfriendly(rNum, placeNum);
						System.out.println(
								"<<< " + friendly.get(rNum).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
					} else {
						if (rNum - 1 >= 0 && friendly.get(rNum - 1) != null) {
							if (friendly.get(rNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(rNum - 1).getName() + " 능력 사용! >>>");
								rNum -= 1;
								System.out.println("<<< 대신 맞습니다! >>>");
							}
						}
						friendlyHealthAdjust(rNum, -abilityNum);
						findHitfriendly(rNum, placeNum);
						System.out.println("<<< " + friendly.get(rNum).getName() + "에게 " + (abilityNum) + " 피해! >>>");
					}
					return;
				}
			}
		}
		case 10: { // 대결 시작 시 가장 뒤에 있는 적군 1, 2, 3 피해
			int enemyPlaceNum = remainFriendlyNum() - 1;
			if (enemyPlaceNum < 0) {
				return;
			}
			int abilityNum = lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			
			show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
			if (enemyFindPokemon(54)) {
				if (enemyPlaceNum - 1 >= 0 && friendly.get(enemyPlaceNum - 1) != null) {
					if (friendly.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(enemyPlaceNum - 1).getName() + " 능력 사용! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< 대신 맞습니다! >>>");
					}
				}
				System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
				friendlyHealthAdjust(enemyPlaceNum, -(abilityNum + lv54));
				findHitfriendly(enemyPlaceNum, placeNum);
				System.out.println(
						"<<< " + friendly.get(enemyPlaceNum).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
			} else {
				if (enemyPlaceNum - 1 >= 0 && friendly.get(enemyPlaceNum - 1) != null) {
					if (friendly.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(enemyPlaceNum - 1).getName() + " 능력 사용! >>>");
						enemyPlaceNum -= 1;
						System.out.println("<<< 대신 맞습니다! >>>");
					}
				}
				friendlyHealthAdjust(enemyPlaceNum, -abilityNum);
				findHitfriendly(enemyPlaceNum, placeNum);
				System.out.println("<<< " + friendly.get(enemyPlaceNum).getName() + "에게 " + (abilityNum) + " 피해! >>>");
			}
			EnemydrawParabola(friendly_panel.get(enemyPlaceNum), enemy_panel.get(2));
			
			return;

		}
		case 12: { // 대결 시작 시 모든 적군 1, 2, 3 피해
			int enemyPlaceNum = remainEnemyNum();
			int abilityNum = lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용>>>");
			show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
			for (int i = 0; i < enemyPlaceNum; i++) {
				if (enemyFindPokemon(54)) {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52 || i - 1 >= 0) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
							friendlyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitfriendly(enemyPlaceNum - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
						}
					} else {
						System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
						friendlyHealthAdjust(i, -(abilityNum + lv54));
						findHitfriendly(enemyPlaceNum, placeNum);
						System.out
								.println("<<< " + friendly.get(i).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
					}
				} else {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							friendlyHealthAdjust(i - 1, -abilityNum);
							findHitfriendly(enemyPlaceNum - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "에게 " + (abilityNum) + " 피해! >>>");
						}
					} else {
						friendlyHealthAdjust(i, -abilityNum);
						findHitfriendly(enemyPlaceNum, placeNum);
						System.out.println("<<< " + friendly.get(i).getName() + "에게 " + (abilityNum) + " 피해! >>>");
					}
				}
			}
			return;
		}
		case 23: { // 대결 시작 시 앞에 있는 아군에게 체력 50%, 70%, 100% 만큼 증가
			if (placeNum != 0) {
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = enemy.get(placeNum).getHealth() / 2;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getHealth() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getHealth();
				}
				enemyHealthAdjust(placeNum - 1, adjustNum);
				System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "에게  체력 " + adjustNum + "만큼 증가! >>>");
				return;
			}
			return;
		}
		case 26: { // 대결 시작 시 앞에 있는 아군에게 공격력 50%, 70%, 100% 만큼 추가
			int lv25 = findEnemylv(25);
			if (placeNum != 0) {
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = enemy.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getPower() * 7 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getPower();
				}
				enemyPowerAdjust(placeNum - 1, adjustNum);
				System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "에게 공격력 " + adjustNum + "만큼 증가! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					enemyPowerAdjust(placeNum - 1, lv25);
					System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			return;
		}
		case 29: {// 대결 시작 시 가장 뒤에 있는 적군에게 1, 2, 3 독 피해
			int abilityNum = lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
			int enemyPlaceNum = remainFriendlyNum() - 1;
			if (enemyPlaceNum - 1 >= 0 && friendly.get(enemyPlaceNum - 1) != null) {
				if (friendly.get(enemyPlaceNum - 1).getPokemonNum() == 52) {
					System.out.println("<<< " + friendly.get(enemyPlaceNum - 1).getName() + " 능력 사용! >>>");
					enemyPlaceNum -= 1;
					System.out.println("<<< 대신 맞습니다! >>>");
				}
			}
			friendlyPoisonNum[enemyPlaceNum] += abilityNum;
			System.out.println("<<< " + friendly.get(enemyPlaceNum).getName() + "에게 " + abilityNum + " 독 피해! >>>");
			return;
		}
		case 31: { // 대결 시작 시 모든 유닛 1, 2, 3 피해
			int abilityNum = lv;
			int friendlyNum = remainFriendlyNum();
			int enemyNum = remainEnemyNum();
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
			for (int i = 0; i < enemyNum; i++) {
				if (i - 1 >= 0 && enemy.get(i - 1) != null) {
					if (enemy.get(i - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
						System.out.println("<<< 대신 맞습니다! >>>");
						enemyHealthAdjust(i - 1, -abilityNum);
						System.out.println("<<< " + enemy.get(i - 1).getName() + "에게 " + abilityNum + " 피해! >>>");
						findHitEnemy(i - 1, -1);
					}
				} else {
					enemyHealthAdjust(i, -abilityNum);
					System.out.println("<<< " + enemy.get(i).getName() + "에게 " + abilityNum + " 피해! >>>");
					findHitEnemy(i, -1);
				}

			}
			if (enemyFindPokemon(54)) {
				System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
				for (int i = 0; i < friendlyNum; i++) {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							friendlyHealthAdjust(i - 1, -(abilityNum + lv54));
							findHitfriendly(i - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
						}
					} else {
						friendlyHealthAdjust(i, -(lv54 + abilityNum));
						findHitfriendly(i, placeNum);
						System.out
								.println("<<< " + friendly.get(i).getName() + "에게 " + (abilityNum + lv54) + " 피해! >>>");
					}
				}
			} else {
				for (int i = 0; i < friendlyNum; i++) {
					if (i - 1 >= 0 && friendly.get(i - 1) != null) {
						if (friendly.get(i - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							friendlyHealthAdjust(i - 1, -abilityNum);
							findHitfriendly(i - 1, placeNum);
							System.out.println(
									"<<< " + friendly.get(i - 1).getName() + "에게 " + (abilityNum) + " 피해! >>>");
						}
					} else {
						friendlyHealthAdjust(i, -abilityNum);
						findHitfriendly(i, placeNum);
						System.out.println("<<< " + friendly.get(i).getName() + "에게 " + (abilityNum) + " 피해! >>>");
					}
				}
			}
			return;
		}
		case 37: { // 대결 시작 시 모든 아군의 체력 30%, 40%, 50% 만큼 증가
			int enemtyNum = remainEnemyNum();
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
			int adjustNum = enemy.get(placeNum).getHealth() * 3 / 10;
			if (lv == 2) {
				adjustNum = enemy.get(placeNum).getHealth() * 4 / 10;
			} else if (lv == 3) {
				adjustNum = enemy.get(placeNum).getHealth() * 5 / 10;
			}
			for (int i = 0; i < enemtyNum; i++) {
				enemyHealthAdjust(i, adjustNum);
				System.out.println("<<< " + enemy.get(i).getName() + "에게 체력 " + adjustNum + "만큼 증가! >>>");
			}
			return;
		}
		case 50: { // 대결 시작 시 같은 위치에 있는 적군에게 피해 입힙니다.
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
			int adjustNum = enemy.get(placeNum).getPower();
			if (enemyFindPokemon(54)) {
				if (placeNum - 1 >= 0 && friendly.get(placeNum - 1) != null) {
					if (friendly.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(placeNum - 1).getName() + " 능력 사용! >>>");
						System.out.println("<<< 대신 맞습니다! >>>");
						System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
						friendlyHealthAdjust(placeNum - 1, -(adjustNum + lv54));
						findHitfriendly(placeNum - 1, placeNum);
						System.out.println("<<< " + friendly.get(placeNum - 1).getName() + "에게 " + (adjustNum + lv54)
								+ " 피해! >>>");
					}
				} else {
					System.out.println("<<< " + "뽀뽀라" + "의 추가 능력 사용! >>>");
					friendlyHealthAdjust(placeNum, -(adjustNum + lv54));
					findHitfriendly(placeNum, placeNum);
					System.out.println(
							"<<< " + friendly.get(placeNum).getName() + "에게 " + (adjustNum + lv54) + " 피해! >>>");
				}

				return;
			} else {
				if (placeNum - 1 >= 0 && friendly.get(placeNum - 1) != null) {
					if (friendly.get(placeNum - 1).getPokemonNum() == 52) {
						System.out.println("<<< " + friendly.get(placeNum - 1).getName() + " 능력 사용! >>>");
						System.out.println("<<< 대신 맞습니다! >>>");
						friendlyHealthAdjust(placeNum - 1, -adjustNum);
						findHitfriendly(placeNum - 1, placeNum);
						System.out.println(
								"<<< " + friendly.get(placeNum - 1).getName() + "에게 " + adjustNum + " 피해! >>>");
					}
				} else {
					friendlyHealthAdjust(placeNum, -adjustNum);
					findHitfriendly(placeNum, placeNum);
					System.out.println("<<< " + friendly.get(placeNum).getName() + "에게 " + adjustNum + " 피해! >>>");
				}
				return;
			}
		}
		default: {
			return;
		}
		}
	}

	void enemyAttackAbility(int placeNum) { // 공격 시 능력
		System.out.println("<<< 적군 공격 시 능력 발동 >>>");
		int lv25 = findEnemylv(25);
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 5: { // 공격 시 공격력 2, 3, 4 증가
				int abilityNum = 1 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 공격력 " + abilityNum + "만큼 증가! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			case 16: { // 공격 시 뒤에 있는 아군 체력 2, 3, 4 증가
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				if (enemy.get(placeNum + 1) != null) {
					enemyHealthAdjust(placeNum + 1, lv + 1);
					System.out.println("<<< " + enemy.get(placeNum - 1).getName() + "에게 체력 " + (lv + 1) + "만큼 증가! >>>");
				}
				return;
			}
			case 22: { // 공격 시 랜덤한 적군에게 공격력 50%, 70%, 100% 의 피해
				while (true) {
					int rNum = random.nextInt(5);
					if (friendly.get(rNum) != null) {
						System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
						show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
						int adjustNum = enemy.get(placeNum).getPower() / 2;
						if (lv == 2) {
							adjustNum = enemy.get(placeNum).getPower() * 7 / 10;
						} else if (lv == 3) {
							adjustNum = enemy.get(placeNum).getPower();
						}
						enemyHealthAdjust(rNum, adjustNum);
						System.out.println("<<< " + friendly.get(rNum).getName() + "에게 " + adjustNum + " 피해! >>>");
						whoHitFriendly[rNum] = placeNum;
						return;
					}
				}
			}
			case 24: { // 공격시 25%, 35%, 50% 확률로 추가 공격
				if (lv == 1) {
					int rNum = random.nextInt(4);
					if (rNum == 0) {
						System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
						show_ex(enemy_panel.get(0).get_expanel(), 2);
						hitFriendly();
					}
					return;
				} else if (lv == 2) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
						show_ex(enemy_panel.get(0).get_expanel(), 2);
						hitFriendly();
					}
					return;
				} else if (lv == 3) {
					int rNum = random.nextInt(100);
					if (rNum < 35) {
						System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
						show_ex(enemy_panel.get(0).get_expanel(), 2);
						hitFriendly();
					}
					return;
				}

			}
			case 27: { // 공격 시 공격력 4, 5, 6 증가
				int abilityNum = 3 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			case 40: { // 공격 시 공격력, 체력 3, 4, 5 증가
				int abilityNum = 2 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
				}
				return;
			}
			case 44: { // 이 포켓몬은 2번 공격 합니다
				System.out.println("<<< " + enemy.get(0).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(0).get_expanel(), 2);
				hitFriendly();
				return;

			}
			case 46: { // 공격 시 적군의 공격력 20%, 30%, 50% 만큼 감소합니다
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = friendly.get(0).getPower() / 5;
				if (lv == 2) {
					adjustNum = friendly.get(0).getPower() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = friendly.get(0).getPower() / 2;
				}
				friendlyPowerAdjust(0, -(adjustNum));
				System.out.println("<<< " + friendly.get(0).getName() + " 공격력 " + adjustNum + " 만큼 감소! >>>");
				return;
			}
			case 58: { // 공격 시 공격력의 50%, 60%, 75%만큼 그 뒤에있는 적군에게 피해
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				int adjustNum = enemy.get(placeNum).getPower() / 2;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getPower() * 6 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getPower() * 75 / 100;
				}
				friendlyPowerAdjust(1, -(adjustNum));
				System.out.println("<<< " + friendly.get(1).getName() + "에게 " + adjustNum + " 만큼 추가 피해! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyFrontFightAbillity(int placeNum) { // 앞의 아군 공격 시 능력
		System.out.println("<<< 앞에 있는 적군 공격 시 능력 발동 >>>");
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum + 1) != null) {
			switch (enemy.get(placeNum + 1).getPokemonNum()) {
			case 9: { // 앞의 아군이 공격 시 앞의 아군 체력 2, 3, 4 증가
				int abilityNum = 1 + lv;
				System.out.println("<<< " + enemy.get(placeNum + 1).getName() + " 능력 사용! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
				return;
			}
			case 55: { // 앞의 아군 공격 시 1번 더 공격시킵니다.
				System.out.println("<<< " + enemy.get(1).getName() + " 능력 사용! >>>");
				hitFriendly();
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyHitAbility(int placeNum) { // 맞을 시 능력
		System.out.println("<<< 적군 맞을 시 능력 발동 >>>");
		int lv = enemy.get(placeNum).getLv();
		int lv25 = findEnemylv(25);
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 4: { // 공격에 맞을 시 공격한 대상에게 1, 2, 3 피해
				int enemyNum = whoHitEnemy[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
					if (enemyNum > 0) {
						if (friendly.get(enemyNum - 1) != null) {
							if (friendly.get(enemyNum - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(enemyNum - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								enemyNum -= 1;
							}
						}
					}
					friendlyHealthAdjust(enemyNum, -lv);
					System.out.println("<<< " + friendly.get(enemyNum).getName() + "에게 " + lv + " 피해! >>>");
					whoHitEnemy[placeNum] = -1;
					return;
				}
				return;
			}
			case 11: { // 공격에 맞을 시 공격한 적군 공격력 1, 2, 3 감소
				if (whoHitEnemy[placeNum] != -1) {
					int enemyNum = whoHitEnemy[placeNum];
					System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
					friendlyPowerAdjust(enemyNum, -lv);
					System.out.println("<<< " + friendly.get(enemyNum).getName() + " 공격력 " + lv + " 감소! >>>");
					whoHitEnemy[placeNum] = -1;
					return;
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 21: { // 공격에 맞을 시 공격력,체력 2, 3, 4 증가
				int abilityNum = 1 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 30: { // 공격에 맞을 시 체력 4, 5, 6 증가
				int abilityNum = 3 + lv;
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + abilityNum + "만큼 증가! >>>");
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 39: { // 공격에 맞을 시 공격한 적군에게 1, 2, 3 독 피해
				int enemyNum = whoHitEnemy[placeNum];
				if (enemyNum != -1) {
					System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
					friendlyPoisonNum[enemyNum] += lv;
					System.out.println("<<< " + friendly.get(enemyNum).getName() + "에게 " + lv + " 독 피해! >>>");
					whoHitEnemy[placeNum] = -1;
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 45: { // 공격에 맞을 시 피해량 20%, 30%, 50%만큼 돌려줍니다
				int enemyNum = whoHitEnemy[placeNum];
				if (enemyNum != -1) {
					int adjustNum = (enemyHP45 - enemy.get(placeNum).getHealth()) / 5;
					if (lv == 2) {
						adjustNum = (enemyHP45 - enemy.get(placeNum).getHealth()) * 3 / 10;
					} else if (lv == 3) {
						adjustNum = (enemyHP45 - enemy.get(placeNum).getHealth()) / 2;
					}
					System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
					if (friendly.get(enemyNum - 1) != null) {
						if (friendly.get(enemyNum - 1).getPokemonNum() == 52) {
							System.out.println("<<< " + friendly.get(enemyNum - 1).getName() + " 능력 사용! >>>");
							System.out.println("<<< 대신 맞습니다! >>>");
							enemyNum -= 1;
						}
					}
					friendlyHealthAdjust(enemyNum, adjustNum);
					System.out.println("<<< " + friendly.get(enemyNum).getName() + "에게 " + adjustNum + " 피해! >>>");
					friendlyAbillity45();
					whoHitEnemy[placeNum] = -1;
				}
				whoHitEnemy[placeNum] = -1;
				return;
			}
			case 57: { // 공격에 맞을 시 체력의 20%, 30%, 50% 만큼 체력 증가
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				int adjustNum = enemy.get(placeNum).getHealth() / 5;
				if (lv == 2) {
					adjustNum = enemy.get(placeNum).getHealth() * 3 / 10;
				} else if (lv == 3) {
					adjustNum = enemy.get(placeNum).getHealth() / 2;
				}
				enemyHealthAdjust(placeNum, adjustNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + adjustNum + "만큼 증가! >>>");
				whoHitEnemy[placeNum] = -1;
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyDownAbility(int placeNum) { // 기절 시 능력
		System.out.println("<<< 적군 기절 시 능력 발동 >>>");
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 1: { // 기절 시 능력 없는 2/2단데기 1,2, 3 소환
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				for (int i = 0; i < lv; i++) {
					enemyGoBack();
					enemy.set(0, dan);
					System.out.println("<<< " + "단데기" + " 소환! >>>");
					enemyUseToSummon();
					enemyUseSummonAbility();
					friendlyEnemySummonAbility();
				}
				enemy.set(placeNum + lv, null);
				return;
			}
			case 6: { // 기절 시 가장 뒤에 있는 적군 1, 2, 3 독 피해
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				int i = remainFriendlyNum() - 1;
				enemyPoisonNum[i] += lv;
				System.out.println("<<< " + friendly.get(i).getName() + "에게 " + lv + " 독 피해! >>>");
				enemy.set(placeNum, null);
				enemy_panel.get(placeNum).setVisible(false);
				return;
			}
			case 18: { // 기절 시 가장 앞에 있는 적군 2, 3, 4 피해
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				int abilityNum = 1 + lv;
				if (friendly.get(0) == null) {
					return;
				}
				friendlyHealthAdjust(0, abilityNum);
				System.out.println("<<< " + friendly.get(0).getName() + "에게 " + abilityNum + " 피해! >>>");
				findHitfriendly(0, placeNum);
				enemy.set(placeNum, null);
				enemy_panel.get(placeNum).setVisible(false);
				return;
			}
			case 19: { // 기절 시 능력 없는 망키 1, 2, 3 소환
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				for (int i = 0; i < lv; i++) {
					enemyGoBack();
					enemy.set(0, mang);
					System.out.println("<<< " + "망키" + " 소환! >>>");
					enemyUseToSummon();
					enemyUseSummonAbility();
					friendlyEnemySummonAbility();
				}
				enemy.set(placeNum + lv, null);
				enemy_panel.get(placeNum + lv).setVisible(false);
				return;
			}

			case 28: { // 기절 시 체력 2, 2.5, 3배인 능력 없는 모다피 2 소환
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				show_ex(enemy_panel.get(placeNum).get_expanel(), 2);
				if (lv == 2) {
					mo.setHealth(mo.getHealth() / 2 * 25 / 10);
				} else if (lv == 3) {
					mo.setHealth(mo.getHealth() / 2 * 3);
				}
				enemyGoBack();
				enemy.set(0, mo);
				System.out.println("<<< " + "모다피" + " 소환! >>>");
				enemyUseToSummon();
				enemyUseSummonAbility();
				friendlyEnemySummonAbility();
				enemyGoBack();
				enemy.set(0, mo);
				enemy.set(placeNum + 2, null);
				System.out.println("<<< " + "모다피" + " 소환! >>>");
				enemyUseToSummon();
				enemyUseSummonAbility();
				friendlyEnemySummonAbility();
				return;
			}
			default: {
				enemy.set(placeNum, null);
				enemy_panel.get(placeNum).setVisible(false);
				return;
			}
			}
		}
	}

	void enemyFrontDownAbility(int placeNum) { // 앞의 아군 기절 시
		System.out.println("<<< 앞에 있는 적군 기절 시 능력 발동 >>>");
		int lv = enemy.get(placeNum).getLv();
		if (enemy.get(placeNum + 1) != null) {
			switch (enemy.get(placeNum + 1).getPokemonNum()) {
			case 20: { // 앞의 아군 기절 시 모든 적군 1, 2, 3 피해
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				for (int i = 0; i < remainFriendlyNum(); i++) {
					if (friendly.get(i) != null) {
						if (i - 1 >= 0 && friendly.get(i - 1) != null) {
							if (friendly.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								friendlyHealthAdjust(i - 1, -lv);
								System.out.println("<<< " + friendly.get(i - 1).getName() + "에게 " + lv + " 피해! >>>");
								findHitfriendly(i - 1, -1);
							}
						} else {
							friendlyHealthAdjust(i, -lv);
							System.out.println("<<< " + friendly.get(i).getName() + "에게 " + lv + " 피해! >>>");
							findHitfriendly(i, -1);
						}

					}
				}
				return;
			}
			case 35: { // 앞에 있는 아군 기절 시 모든 유닛에게 2, 3, 4 피해
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				int abilityNum = 1 + lv;
				for (int i = 0; i < remainFriendlyNum(); i++) {
					if (friendly.get(i) != null) {
						if (i - 1 >= 0 && friendly.get(i - 1) != null) {
							if (friendly.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + friendly.get(i - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								friendlyHealthAdjust(i - 1, -abilityNum);
								System.out.println(
										"<<< " + friendly.get(i - 1).getName() + "에게 " + abilityNum + " 피해! >>>");
								findHitfriendly(i - 1, -1);
							}
						} else {
							friendlyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + friendly.get(i).getName() + "에게 " + abilityNum + " 피해! >>>");
							findHitfriendly(i, -1);
						}

					}
				}
				for (int i = 0; i < remainEnemyNum(); i++) {
					if (enemy.get(i) != null) {
						if (i - 1 >= 0 && enemy.get(i - 1) != null) {
							if (enemy.get(i - 1).getPokemonNum() == 52) {
								System.out.println("<<< " + enemy.get(i - 1).getName() + " 능력 사용! >>>");
								System.out.println("<<< 대신 맞습니다! >>>");
								enemyHealthAdjust(i - 1, -abilityNum);
								System.out
										.println("<<< " + enemy.get(i - 1).getName() + "에게 " + abilityNum + " 피해! >>>");
								findHitEnemy(i - 1, -1);
							}
						} else {
							enemyHealthAdjust(i, -abilityNum);
							System.out.println("<<< " + enemy.get(i).getName() + "에게 " + abilityNum + " 피해! >>>");
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

	void enemyDownEnemyAbility(int placeNum) { // 적 기절 시 능력
		System.out.println("<<< 아군 기절 시 능력 발동 >>>");
		int lv = enemy.get(placeNum).getLv();
		int abilityNum = 3 + lv;
		int lv25 = findEnemylv(25);
		if (enemy.get(placeNum) != null) {
			switch (enemy.get(placeNum).getPokemonNum()) {
			case 47: {
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
				enemyPowerAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
				if (enemyFindPokemon(25)) {
					System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
					enemyPowerAdjust(placeNum, lv25);
					System.out.println("<<< " + enemy.get(placeNum).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
				}
				enemyHealthAdjust(placeNum, abilityNum);
				System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + abilityNum + "만큼 증가! >>>");
				return;
			}
			default: {
				return;
			}
			}
		}
	}

	void enemyUseToSummon() { // 소환 시 능력 소환괸 아군에게 쓰는 능력
		System.out.println("<<< 적군 소환 시 능력 발동 >>>");
		int lv15 = findEnemylv(15);
		int lv34 = findEnemylv(34);
		int lv51 = findEnemylv(51);
		int lv25 = findEnemylv(25);
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == 15) { // 아군 소환시 소환된 아군 공격력,체력 2, 3, 4 증가
					int abilityNum = 1 + lv15;
					System.out.println("<<< 콘팡 능력 사용! >>>");
					enemyPowerAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 공격력 " + abilityNum + " 증가! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						enemyPowerAdjust(0, lv25);
						System.out.println("<<< " + enemy.get(0).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
					}
					enemyHealthAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 체력 " + abilityNum + " 증가! >>>");
				} else if (enemy.get(i).getPokemonNum() == 34) { // 아군 소환 시 소환된 유닛 공격력 2, 3, 4 증가
					int abilityNum = 1 + lv34;
					System.out.println("<<< 파오리 능력 사용! >>>");
					enemyPowerAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 공격력 " + abilityNum + " 증가! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						enemyPowerAdjust(0, lv25);
						System.out.println("<<< " + enemy.get(0).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
					}
				} else if (enemy.get(i).getPokemonNum() == 51) { // 소환된 아군 공격력, 체력 3, 4, 5증가
					int abilityNum = 2 + lv51;
					System.out.println("<<< 콘치 능력 사용! >>>");
					enemyPowerAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 공격력 " + abilityNum + " 증가! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						enemyPowerAdjust(0, lv25);
						System.out.println("<<< " + enemy.get(0).getName() + "에게 공격력 " + lv25 + "만큼 증가! >>>");
					}
					enemyHealthAdjust(0, abilityNum);
					System.out.println("<<< 소환된 포켓몬 체력 " + abilityNum + " 증가! >>>");
				}
			}
		}
	}

	void enemyUseSummonAbility() { // 아군 소환 시 능력 사용
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == 2 || enemy.get(i).getPokemonNum() == 41) {
					enemySummonAbility(i);
				}
			}

		}
	}

	void enemySummonAbility(int placeNum) { // 아군 소환 시 능력
		System.out.println("<<< 적군 소환 시 능력 발동 >>>");
		int lv = enemy.get(placeNum).getLv();
		int lv25 = findEnemylv(25);
		switch (enemy.get(placeNum).getPokemonNum()) {
		case 2: { // 아군 소환 시 체력 2, 3, 4 증가
			int abilityNum = 1 + lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			enemyHealthAdjust(placeNum, abilityNum);
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
			return;
		}

		case 41: { // 아군 소환 시 랜덤으로 아군 공격력,체력 2, 3, 4 증가
			int abilityNum = 1 + lv;
			System.out.println("<<< " + enemy.get(placeNum).getName() + " 능력 사용! >>>");
			while (true) {
				int rNum = random.nextInt(5);
				if (enemy.get(rNum) != null) {
					enemyHealthAdjust(rNum, abilityNum);
					System.out.println("<<< " + enemy.get(rNum).getName() + " 체력 " + abilityNum + " 만큼 증가! >>>");
					enemyPowerAdjust(rNum, abilityNum);
					System.out.println("<<< " + enemy.get(rNum).getName() + " 공격력 " + abilityNum + " 만큼 증가! >>>");
					if (enemyFindPokemon(25)) {
						System.out.println("<<< " + "발챙이" + "의 추가 능력 사용! >>>");
						enemyPowerAdjust(rNum, lv25);
						System.out.println("<<< " + enemy.get(rNum).getName() + "에게 공격력 " + lv25 + " 만큼 증가! >>>");
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

	void enemyFriendlySummonAbility() { // 적군 소환 시
		System.out.println("<<< 아군 소환 시 능력 발동 >>>");
		int lv = findEnemylv(43);
		int abilityNum = 2 + lv;
		for (int i = 0; i < remainEnemyNum(); i++) {
			if (enemy.get(i) != null) {
				if (enemy.get(i).getPokemonNum() == 43) { // 적군 소환 시 랜덤한 적군 1명 에게 3 피해
					System.out.println("<<< " + enemy.get(i).getName() + " 능력 사용! >>>");
					while (true) {
						int rNum = random.nextInt();
						if (friendly.get(rNum) != null) {
							if (rNum - 1 >= 0 && friendly.get(rNum - 1) != null) {
								if (friendly.get(rNum - 1).getPokemonNum() == 52) {
									System.out.println("<<< " + friendly.get(rNum - 1).getName() + " 능력 사용! >>>");
									System.out.println("<<< 대신 맞습니다! >>>");
									rNum -= 1;
								}
							}
							friendlyHealthAdjust(rNum, abilityNum);
							System.out.println("<<< " + friendly.get(rNum).getName() + "에게 " + abilityNum + " 피해! >>>");
							return;
						}
					}
				}
			}
		}
	}

	boolean enemyFindPokemon(int pokemonNum) { // 아군에 있는지 확인
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
