package Placement;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Placement_Screen extends JFrame {
	
	int effectNum[] = { 0, 0, 0, 0, 0 };
	private int life = 10; // 목숨
	private int coin = 100; // 코인
	private int badge = 0; // 뱃지
	private int turnNum = 1;// 턴 수
	Random random = new Random();
	private JPanel contentPane;
	private LinkedList<Pokemon> shopPokemon;
	private LinkedList<Obj_Panel2> shop_pokemon_panel; //상점 패널을 저장하고 있음
	private Obj_Panel2 select_shop_pokemon; //상점에서 선택한 패널을 저장함
	private LinkedList<Place_Area2> place_area_panel; //배치 패널을 저장하고 있음
	private Place_Area2 select_place; //선택 한 배치 패널을 가지고있음
	private LinkedList<Obj_Panel2> place_pokemon_panel; //배치에 위치한 포켓몬 패널을 가지고있음
	private Obj_Panel2 select_place_pokemon; 
	public LinkedList<Pokemon> placePokemon;
	private LinkedList<Pokemon> forReplace;
	private Status_Panel status_panel;
	setPokemon s = new setPokemon();
	private LinkedList<item> item;
	private LinkedList<Item_Panel> item_panel;
	private LinkedList<Pokemon> toBattle;
	setItem t = new setItem();
	boolean frozenPokemonNum[] = { false, false, false, false, false }; // 상점 포켓몬 얼리기 설정
	boolean frozenItemNum[] = { false, false };// 아이템 얼리기
	private ImagePanel background = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\placement_background.png").getImage());
	private Item_Panel select_item;
	boolean item24Effect = false;
	private StartBattle battle_screen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Placement_Screen frame = new Placement_Screen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Placement_Screen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setTitle("SHOP_SCREEN");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);        
		background.setBounds(0, 0, 1920, 1080);
		contentPane.add(background);
		background.setBackground(new Color(255,255,255));
		background.setLayout(null);
		
		this.toBattle = new LinkedList<>();
		this.shopPokemon = new LinkedList<>();
		this.placePokemon = new LinkedList<>();
		this.forReplace = new LinkedList<>();
		this.shop_pokemon_panel = new LinkedList<>();
		this.place_area_panel = new LinkedList<>();
		this.place_pokemon_panel = new LinkedList<>();
		this.item = new LinkedList<>();
		this.item_panel = new LinkedList<>();
		settingList();
		panel_setting();
		shop_reroll();
		
	}
	
	public void panel_setting() { //상점 포켓몬 초기설정
		
		//---------------Item_1_panel------------------//
		Item_Panel item1 = new Item_Panel();
		ImagePanel item_location_1 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\select_location.png").getImage());
		item1.setBounds(1325, 600, 409, 245);
		item_location_1.setBounds(1328, 780, 184, 110);
		background.add(item1);
		background.add(item_location_1);
		item_panel.add(0, item1);
		//-------------------------------------------//	
			
		//---------------Item_2_panel------------------//
		Item_Panel item2 = new Item_Panel();
		ImagePanel item_location_2 = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\select_location.png").getImage());
		item2.setBounds(1564, 600, 356, 245);
		item_location_2.setBounds(1570, 780, 184, 110);
		background.add(item2);
		background.add(item_location_2);
		item_panel.add(1, item2);
		//-------------------------------------------//	
			
			
		
		//---------------shop_obj_panel--------------------//
		
		
			Obj_Panel2 shop_obj_1 = new Obj_Panel2();
			background.add(shop_obj_1);
			shop_pokemon_panel.add(0, shop_obj_1);
			shop_obj_1.setVisible(false); //디폴트는 안보이게
				
			Obj_Panel2 shop_obj_2 = new Obj_Panel2();
			shop_obj_2.setLocation(373, 566);
			background.add(shop_obj_2);
			shop_pokemon_panel.add(1, shop_obj_2);
			shop_obj_2.setVisible(false);
				
			Obj_Panel2 shop_obj_3 = new Obj_Panel2();
			shop_obj_3.setLocation(585, 566);
			background.add(shop_obj_3);
			shop_pokemon_panel.add(2, shop_obj_3);
			shop_obj_3.setVisible(false);
				
			Obj_Panel2 shop_obj_4 = new Obj_Panel2();
			shop_obj_4.setLocation(797, 566);
			background.add(shop_obj_4);
			shop_pokemon_panel.add(3, shop_obj_4);
			shop_obj_4.setVisible(false);
				
			Obj_Panel2 shop_obj_5 = new Obj_Panel2();
			shop_obj_5.setLocation(1009, 566);
			background.add(shop_obj_5);
			shop_pokemon_panel.add(4, shop_obj_5);
			shop_obj_5.setVisible(false);
				
		//------------------------------------------//
			Shop_Area shop_area = new Shop_Area();
			shop_area.setBounds(150, 797, 1045, 113);
			background.add(shop_area);
				
		//---------------user_obj_panel------------------//
			Obj_Panel2 user_obj_1 = new Obj_Panel2();
			user_obj_1.setLocation(505, 190);
			background.add(user_obj_1);
			place_pokemon_panel.add(0, user_obj_1);
			user_obj_1.setVisible(false); 
				
			Obj_Panel2 user_obj_2 = new Obj_Panel2();
			user_obj_2.setLocation(717, 190);
			background.add(user_obj_2);
			place_pokemon_panel.add(1, user_obj_2);
			user_obj_2.setVisible(false); 
			
			Obj_Panel2 user_obj_3 = new Obj_Panel2();
			user_obj_3.setLocation(929, 190);
			background.add(user_obj_3);
			place_pokemon_panel.add(2, user_obj_3);
			user_obj_3.setVisible(false); 
				
			Obj_Panel2 user_obj_4 = new Obj_Panel2();
			user_obj_4.setLocation(1141, 190);
			background.add(user_obj_4);
			place_pokemon_panel.add(3, user_obj_4);
			user_obj_4.setVisible(false); 
			
			Obj_Panel2 user_obj_5 = new Obj_Panel2();
			user_obj_5.setLocation(1353, 190);
			background.add(user_obj_5);
			place_pokemon_panel.add(4, user_obj_5);
			user_obj_5.setVisible(false); 
		//------------------------------------------//
			
		//---------------user_place_panel------------------//
		Place_Area2 place_1 = new Place_Area2(0);
		background.add(place_1);
		place_area_panel.add(place_1);
			
		Place_Area2 place_2 = new Place_Area2(1);
		place_2.setLocation(707, 418);
		background.add(place_2);
		place_area_panel.add(place_2);
		
		Place_Area2 place_3 = new Place_Area2(2);
		place_3.setLocation(919, 418);
		background.add(place_3);
		place_area_panel.add(place_3);
		
		Place_Area2 place_4 = new Place_Area2(3);
		place_4.setLocation(1131, 418);
		background.add(place_4);
		place_area_panel.add(place_4);
		
		Place_Area2 place_5 = new Place_Area2(4);
		place_5.setLocation(1343, 418);
		background.add(place_5);
		place_area_panel.add(place_5);
		//------------------------------------------//
		
		//---------------status_panel------------------//
		status_panel = new Status_Panel(coin, life, badge);
		status_panel.setBounds(0, 0, 874, 168);
		background.add(status_panel);
		//-------------------------------------------//
		
		//---------------reroll_panel------------------//
		ImagePanel reroll = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\reroll.png").getImage());
		reroll.setLocation(12, 957);
		background.add(reroll);
		//-------------------------------------------//
			
		//---------------frozen_panel------------------//
		ImagePanel frozen = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\prozen.png").getImage());
		frozen.setLocation(253, 935);
		background.add(frozen);
		//-------------------------------------------//	
		
		//---------------next_panel------------------//
		ImagePanel next_btn = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\next_btn.png").getImage());
		next_btn.setLocation(1663, 911);
		background.add(next_btn);
		next_btn.setLocation(1663, 911);
		//-------------------------------------------//	
		
		//---------------next_panel------------------//
		ImagePanel sell_btn = new ImagePanel(new ImageIcon("C:\\Project\\GameProject-DB_feature\\Image\\src\\Image\\sell_1.png").getImage());
		sell_btn.setBounds(1259, 916, 300, 145);
		background.add(sell_btn);
		
		
		//item 1번째 선택시
		item1.get_item_panel().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	        	if(item1.ischecked() == false) {
	        		item1.check();
	        		select_item = item1;
	        		item2.no_check();
	        	}else {
	        		item1.no_check();
	        		select_item = null;
	        	}
	        }
	    });
		//아이템 설명
		item1.get_item_panel().addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				item1.show_ex();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				item1.no_show_ex();
			}
		
		 });
		
		//item 2번째 선택시
		item2.get_item_panel().addMouseListener(new MouseAdapter() {
	        @Override
	        public void mouseClicked(MouseEvent e) {
	             if(item2.ischecked() == false) {
	            	 item2.check();
	            	 select_item = item2;
	            	 item1.no_check();
	             }else {
	            	 item2.no_check();
	            	 select_item = null;
	             }
	        }
	    });
		
		item2.get_item_panel().addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				item2.show_ex();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				item2.no_show_ex();
			}
		
		 });
		
		sell_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(select_place_pokemon != null) {
					place_sell(select_place_pokemon.get_location_num());
				}
			}
		});
		
		
		for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
			shop_pokemon_panel.get_pokemon_panel().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					shop_pokemon_panel.show_ex();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					shop_pokemon_panel.no_show_ex();
				}

		    });
		}
		
		for (Obj_Panel2 place_pokemon_panel : place_pokemon_panel) {
			place_pokemon_panel.get_pokemon_panel().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					place_pokemon_panel.show_ex();
				}
				@Override
				public void mouseExited(MouseEvent e) {
					place_pokemon_panel.no_show_ex();
				}

		    });
		}
		
		//게임 화면 진행
		next_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        
				//배틀시작
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
		        
		        StartBattle battle_screen = new StartBattle(turnNum, toBattle.get(0), toBattle.get(1), toBattle.get(2), toBattle.get(3),
		                toBattle.get(4), effectNum, background);		        
		        getContentPane().add(battle_screen);
		        background.setVisible(false);
		        battle_screen.setVisible(true);
			}
		});
			
		//상점 포켓몬 하나만 선택되게 하고 선택한 객체를 저장함
		for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
			shop_pokemon_panel.get_pokemon_panel().addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            shop_select_obj(shop_pokemon_panel); 	            
		        }
		    });
		}
		 //배치구역에 있는 포켓몬의 동작을 함(경험치 증가 이벤트)
		 for (Obj_Panel2 place_pokemon_panel : place_pokemon_panel) {
			 place_pokemon_panel.get_pokemon_panel().addMouseListener(new MouseAdapter() {
			        @Override
			        public void mouseClicked(MouseEvent e) {
			        	place_select_obj(place_pokemon_panel);		        	
			        	if(select_shop_pokemon != null && select_place_pokemon != null) {
				        	if(select_shop_pokemon.get_pokemon_num() == select_place_pokemon.get_pokemon_num()) {
				        		if(coin >= 3) {
					        		int select_shop_pokemon_location = select_shop_pokemon.get_location_num();
							        int select_place_pokemon_location = select_place_pokemon.get_location_num();
							        buyAbility(select_place_pokemon_location);
							        shop_buy_expUp(select_shop_pokemon_location,select_place_pokemon_location);
							        coin = coin - 3;
							        status_panel.set_coin_num(coin);
				        		}else {
				        			System.out.println("코인이 부족합니다.");
				        		}
				        	}else {
				        		System.out.println("포켓몬 레벨업 할려는거 아님");
				        	}
			        	}
			        	//아이템 사용 이벤트
			        	if(select_item != null && select_place_pokemon != null) {
			        		if (coin > 3) {
				        		shop_item_buy(select_item.getItemLocation(), select_place_pokemon.get_location_num());
				        		select_item = null;
				        		select_place_pokemon = null;
			        		}
			        	}
			        }
			    });
			}
		 
		//배치 장소 선택을 하나밖에 선택하게 하고 선택한 객체의 위치를 저장함
		 for (Place_Area2 placeArea : place_area_panel) {
	            placeArea.get_no_select_place_panel().addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    selectPlaceArea(placeArea);
	                    
	                    if(select_shop_pokemon != null) { //select_shop_pokemon에 객체 없으면 상점 구매 x //상점 구매
	                    	if(coin >= 3) {
		                    	int shop_select_location = select_shop_pokemon.get_location_num();  
			                	int place_select_location = select_place.get_location_num();
			                	shop_buy(shop_select_location, place_select_location);
			                	status_panel.set_coin_num(coin);
	                    	}else {
	                    		System.out.println("코인이 부족합니다.");
	                    	}
	                    }else {
	                    	System.out.println("포켓몬을 구매하는 행동 아님");
	                    }
		                     
	                    if(select_place_pokemon != null) { //select_place_pokemon에 객체 없으면 위치 이동 x
			                int place_select_pokemon = select_place_pokemon.get_location_num(); //포켓몬 위치 옮기기
			                int place_select = select_place.get_location_num();
			                place_replace(place_select_pokemon, place_select);
	                    }else {
	                    	//System.out.println("위치구역에서 선택한 포켓몬이 없습니다");
	                    }
	                }
	            });

	            placeArea.get_select_place_panel().addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    deselectPlaceArea(placeArea);
	                }
	            });
	        }
		
		//리롤 클릭 이벤트
		reroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(coin > 0) {
					shop_reroll();
					coin = coin - 1;
					status_panel.set_coin_num(coin);
				}else {
					System.out.println("코인이 부족합니다");
				}
			}
		});
		
		
		
		//얼리기 클릭 이벤트
		frozen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(select_shop_pokemon != null) {
					int shop_select_location = select_shop_pokemon.get_location_num();
					select_shop_pokemon.no_check();
					pokemon_frozen(shop_select_location);
					select_shop_pokemon = null;
				}else {
					System.out.println("얼리기 행동아님");
				}
				if(select_item != null) {
					int select_item_location = select_item.getItemLocation();
					select_item.no_check();
					item_frozen(select_item_location);
					select_item = null;
				}
			}
		});
	
	}
	
	void startPlacement() { // 배치 단계 시작 시 구현
		shop_reroll();
		coin = 10;
		turnNum++;
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
	
	
	void settingList() { // 그냥 비어두면 에러 떠서 null로 지정 함
		shopPokemon.add(0, null);
		shopPokemon.add(1, null);
		shopPokemon.add(2, null);
		shopPokemon.add(3, null);
		shopPokemon.add(4, null);
		placePokemon.add(0, null);
		placePokemon.add(1, null);
		placePokemon.add(2, null);
		placePokemon.add(3, null);
		placePokemon.add(4, null);
		item.add(0, null);
		item.add(1, null);
		toBattle.add(0, null);
		toBattle.add(1, null);
		toBattle.add(2, null);
		toBattle.add(3, null);
		toBattle.add(4, null);
		
	}
	
	void shop_reroll() { // 상점 포켓몬 리롤
		int i = shop_place_num() - 1;

		while (i >= 0) {
			if (frozenPokemonNum[i]) {

			} else {
				int randomnum = randomNum();
				shopPokemon.set(i, s.getLV1Pokemon(randomnum));
				
				int pokemonnum = s.getLV1Pokemon(randomnum).getPokemonNum();
				int Lv = s.getLV1Pokemon(randomnum).getLv();
				int exp = s.getLV1Pokemon(randomnum).getExp();
				int grade = s.getLV1Pokemon(randomnum).getGrade();
				int health = s.getLV1Pokemon(randomnum).getHealth();
				int power = s.getLV1Pokemon(randomnum).getPower();
				String ability = s.getLV1Pokemon(randomnum).getAbility();
						
				
				shop_pokemon_panel.get(i).set_pokemon_num(pokemonnum,Lv);
				shop_pokemon_panel.get(i).set_Lv(Lv);
				shop_pokemon_panel.get(i).set_exp(exp);
				shop_pokemon_panel.get(i).set_grade(grade);
				shop_pokemon_panel.get(i).set_heart(health);
				shop_pokemon_panel.get(i).set_damage(power);
				shop_pokemon_panel.get(i).set_location_num(i);
				shop_pokemon_panel.get(i).set_ex(ability);
				shop_pokemon_panel.get(i).setVisible(true);
				
				select_place_pokemon = null;
				for(Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
					shop_pokemon_panel.no_check();
				}
				select_shop_pokemon = null;
				for(Obj_Panel2 place_pokemon_panel : place_pokemon_panel) {
					place_pokemon_panel.no_check();
				}
				select_place = null;
				for(Place_Area2 place_panel : place_area_panel) {
					place_panel.no_check();
				}
			}
			i--;
		}
		int j = 0;
		while (j < 2) {
			if (frozenItemNum[j]) {
			} else {
				item.set(j, t.getItem(randomItemNum()));
				item_panel.get(j).setItemNum(item.get(j).getItemNum());
				item_panel.get(j).setItemLocation(j);
				item_panel.get(j).set_ex(item.get(j).getAbility());
				item_panel.get(j).setVisible(true);
				item_panel.get(j).no_check();
				System.out.println(item_panel.get(j).getItemNum());
				System.out.println(item.get(j).getItemNum());
			}
			j++;
		}
	}
	
	int randomNum() { // 포켓몬 수 만큼의 난수
		if(turnNum<=6) {
			return random.nextInt(turnNum*10) + 1;
		}else {
			return random.nextInt(60) + 1;
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
	
	void shop_show(int shopPlaceNum) { // 상점 포켓몬 확인
		System.out.println("<<<상점 구역 출력>>>");
		int i = 0;
		while (i < shopPlaceNum) {
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
				
				/*
				if(shop_pokemon_panel.get(i).get_pokemon_num() != 0) {
					shop_pokemon_panel.get(i).setVisible(true);
					shop_pokemon_panel.get(i).repaint();
				}
				*/
			}
			i++;
		}
	}
	void shop_select_obj(Obj_Panel2 selectedPanel) { //하나의 상점 패널을 선택했을때 나머지 상점 패널은 체크 해제 됨
	    for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
	        if (shop_pokemon_panel.equals(selectedPanel)) {
	            if (shop_pokemon_panel.ischecked()) {
	            	shop_pokemon_panel.no_check();
	                select_shop_pokemon = null; // 선택 해제 시 select_shop_pokemon을 null로 설정
	            } else {
	            	shop_pokemon_panel.check();
	                select_shop_pokemon = shop_pokemon_panel;
	                System.out.println("상점에서 선택한 포켓몬 위치" + select_shop_pokemon.get_location_num());
	            }
	        } else {
	            shop_pokemon_panel.no_check();
	        }
	    }
	}
	
	void place_select_obj(Obj_Panel2 selectedPanel) { 
	    for (Obj_Panel2 place_pokemon_panel : place_pokemon_panel) {
	        if (place_pokemon_panel.equals(selectedPanel)) {
	            if (place_pokemon_panel.ischecked()) {
	            	place_pokemon_panel.no_check();
	                select_place_pokemon = null; // 선택 해제 시 select_shop_pokemon을 null로 설정
	            } else {
	                place_pokemon_panel.check();
	            	if(select_place_pokemon != null) {
		        		if(select_place_pokemon.get_pokemon_num() == place_pokemon_panel.get_pokemon_num()) {
		        			int place_pokemon = place_pokemon_panel.get_location_num();
		        			int select_pokemon = select_place_pokemon.get_location_num();
		        			System.out.println(select_pokemon);
		        			System.out.println(place_pokemon);
		        		    place_replace(select_pokemon, place_pokemon);
		        		}else {
		        			int place_pokemon = place_pokemon_panel.get_location_num();
		        			int select_pokemon = select_place_pokemon.get_location_num();
		        			place_replace(select_pokemon, place_pokemon);
		        			place_pokemon_panel.no_check();
		        			select_place_pokemon = null;
		        		}
		        	}
	            	select_place_pokemon = selectedPanel;
	            	System.out.println("선택한 배치구역 포켓몬 위치는" + select_place_pokemon.get_location_num() + "입니다.");
	            	
	            }
	        } else {
	        	place_pokemon_panel.no_check();
	        }
	    }
	}

	
    private void selectPlaceArea(Place_Area2 placeArea) { //위치를 선택했을때 처리해주는 함수
        placeArea.check();
        select_place = placeArea;
        System.out.println("선택한 배치 위치 번호는 " + select_place.get_location_num()+ "입니다.");
    }

    private void deselectPlaceArea(Place_Area2 placeArea) { //위치리를 해제 했을때 처리해주는 함수
        placeArea.no_check();
        select_place = null;
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
            select_shop_pokemon.get_frozen_panel().setVisible(true);
            System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 설정>>>");
            return;
        } else {
            frozenPokemonNum[placeNum] = false;
            select_shop_pokemon.get_frozen_panel().setVisible(false);
            System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 취소 설정>>>");
            return;
        }
    }
	
	void shop_buy(int shopPlaceNum, int myPlaceNum) { // 상점에서 포켓몬 구매
		if (!validationShopNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("입력 오류");
			return;
		} else if (isNullShopPokemon(shopPlaceNum)) { // 이미 구매한 상점 번호면 구매 불가
			System.out.println("<<<" + (shopPlaceNum + 1) + "번 상점이 비어있습니다.>>>");
			return;
		} else if (!isNullPlacePokemon(myPlaceNum)) { // 배치 구역에 포켓몬이 있으면 구매 불가
			if (shop_buy_expUp(shopPlaceNum, myPlaceNum)) {
				shopPokemon.set(shopPlaceNum, null);
				frozenPokemonNum[shopPlaceNum] = false;
				System.out.println("<<<구매 성공>>>");
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
			placePokemon.set(myPlaceNum, shopPokemon.get(shopPlaceNum));
			place_pokemon_panel.get(myPlaceNum).set_Lv(shop_pokemon_panel.get(shopPlaceNum).get_LV());
			place_pokemon_panel.get(myPlaceNum).set_exp(shop_pokemon_panel.get(shopPlaceNum).get_exp());
			place_pokemon_panel.get(myPlaceNum).set_grade(shop_pokemon_panel.get(shopPlaceNum).get_grade());
			place_pokemon_panel.get(myPlaceNum).set_heart(shop_pokemon_panel.get(shopPlaceNum).get_heart());
			place_pokemon_panel.get(myPlaceNum).set_damage(shop_pokemon_panel.get(shopPlaceNum).get_damage());
			place_pokemon_panel.get(myPlaceNum).set_location_num(myPlaceNum);
			place_pokemon_panel.get(myPlaceNum).set_pokemon_num(shop_pokemon_panel.get(shopPlaceNum).get_pokemon_num(),shop_pokemon_panel.get(shopPlaceNum).get_LV());
			place_pokemon_panel.get(myPlaceNum).set_ex(shop_pokemon_panel.get(shopPlaceNum).get_ex());
			place_pokemon_panel.get(myPlaceNum).setVisible(true); //패널에 set해주고 보이게 하기
			
	
			shopPokemon.set(shopPlaceNum, null); 
			shop_pokemon_panel.get(shopPlaceNum).setVisible(false); //shop_place_num이 null될때 안보이게 하기
			shop_pokemon_panel.get(shopPlaceNum).no_frozen();
			shop_pokemon_panel.get(myPlaceNum).get_frozen_panel().setVisible(false);
			select_place.no_check();
			
			frozenPokemonNum[shopPlaceNum] = false;
			System.out.println(select_shop_pokemon.get_location_num() + "번 상점 포켓몬을");
        	System.out.println(select_place.get_location_num() + "번에 배치하였습니다.");
			System.out.println("구매 성공");
			
			select_place_pokemon = null;
			select_shop_pokemon = null; //상점 선택 초기화
			select_place = null; //위치 선택 초기화
			
			buyAbility(myPlaceNum);
			coin = coin - 3;
			return;
		}
	}
	
	boolean validationShopNum(int placeNum) { // 상점 구역 번호 유효성 검사
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean validationPlaceNum(int placeNum) { // 배치 구역 번호 유효성 검사
		if (4 < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean isNullShopPokemon(int placeNum) { // 상점 구역 포켓몬이 null인지 확인
		if (shopPokemon.get(placeNum) == null)
			return true;
		return false;
	}
	
	boolean isNullPlacePokemon(int placeNum) { // 배치 구역 포켓몬이 null인지 확인
		if (placePokemon.get(placeNum) == null)
			return true;
		return false;
	}
	
	boolean shop_buy_expUp(int shopPlaceNum, int myPlaceNum) {
		if (placePokemon.get(myPlaceNum) != null) { // 같은 포켓몬이면 경험치 증가
			if (shopPokemon.get(shopPlaceNum).getPokemonNum() == placePokemon.get(myPlaceNum).getPokemonNum()) {
				if (placePokemon.get(myPlaceNum).getLv() == 3) {
					System.out.println("<< 3레벨 입니다!! >>");
					return false;
				}	
				placePokemon.get(myPlaceNum).setExp(placePokemon.get(myPlaceNum).getExp() + 1);
				place_pokemon_panel.get(myPlaceNum).set_exp(place_pokemon_panel.get(myPlaceNum).get_exp() + 1);
				
				shop_pokemon_panel.get(shopPlaceNum).setVisible(false);
				select_place_pokemon.no_check();
				select_shop_pokemon.no_check();
				
				select_place_pokemon = null;
				select_shop_pokemon = null;
				
				isRankup(myPlaceNum);
				System.out.println("<<<경험치 증가!>>>");
				return true;
			}
		}
		return false;
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
	
	void placeHealthAdjust(int placeNum, int adjustNum) { // 아군 체력 조정
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth() + adjustNum);
			place_pokemon_panel.get(placeNum).set_heart(place_pokemon_panel.get(placeNum).get_heart() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 체력 조정 " + adjustNum + " >>>");
		}
	}

	void placePowerAdjust(int placeNum, int adjustNum) { // 아군 공격력 조정
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower() + adjustNum);
			place_pokemon_panel.get(placeNum).set_damage(place_pokemon_panel.get(placeNum).get_damage() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 공격력 조정 " + adjustNum + " >>>");
		}
	}
	
	
	void place_replace(int place1, int place2) { // 배치 구역 포켓몬 위치 바꾸기
        if (place1 == place2 || !validationPlaceNum(place1) ||  !validationPlaceNum(place2)) {
            System.out.println("<<<입력 오류>>>");
            return;
        } else if (isNullPlacePokemon(place1) && isNullPlacePokemon(place2)) {
            System.out.println("<<<둘 다 비어있는 배치 번호를 입력하셨습니다.>>>");
            return;
        } else if (placePokemon.get(place1) != null && placePokemon.get(place2) == null) { // place 1이 null이 아니면
            placePokemon.set(place2, placePokemon.get(place1));// place2 로 복사 후 plcae1은 null로
            placePokemon.set(place1, null);
           place_pokemon_panel.get(place1).setVisible(false);
            
            place_pokemon_panel.get(place2).set_Lv(place_pokemon_panel.get(place1).get_LV());
            place_pokemon_panel.get(place2).set_exp(place_pokemon_panel.get(place1).get_exp());
            place_pokemon_panel.get(place2).set_grade(place_pokemon_panel.get(place1).get_grade());
            place_pokemon_panel.get(place2).set_heart(place_pokemon_panel.get(place1).get_heart());
            place_pokemon_panel.get(place2).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),place_pokemon_panel.get(place1).get_LV()); //여기 문제 찾았다.
            place_pokemon_panel.get(place2).set_damage(place_pokemon_panel.get(place1).get_damage());
            place_pokemon_panel.get(place2).set_location_num(place2);
            place_pokemon_panel.get(place2).set_ex(place_pokemon_panel.get(place1).get_ex());
            place_pokemon_panel.get(place2).setVisible(true);
            
            System.out.println(place_pokemon_panel.get(place1).get_location_num() + "번 위치의 포켓몬을 " + place_pokemon_panel.get(place2).get_location_num() + "로 옮김");
            
            place_area_panel.get(place2).no_check();
            place_pokemon_panel.get(place2).no_check();
            
            select_place_pokemon = null;
            select_place = null;
            
            return;
        } else if (placePokemon.get(place1) == null && placePokemon.get(place2) != null) { // place 2이 null이 아니면
            placePokemon.set(place1, placePokemon.get(place2));// place1 로 복사 후 plcae2은 null로
            placePokemon.set(place2, null);
            return;
        } else if (placePokemon.get(place1).getPokemonNum() == placePokemon.get(place2).getPokemonNum()) {
        	if(placePokemon.get(place1).getLv() == 3 || placePokemon.get(place2).getLv() == 3) {
        		place_pokemon_panel.get(place1).no_check();
                place_pokemon_panel.get(place2).no_check();
        		return;
        	}
            placePokemon.get(place2).setExp(placePokemon.get(place2).getExp() + 1);
            place_pokemon_panel.get(place2).set_exp(place_pokemon_panel.get(place2).get_exp() + 1);
            isRankup(place2);
            System.out.println("레벨업 성공");
            placePokemon.set(place1, null);
            place_pokemon_panel.get(place1).setVisible(false);
            select_place_pokemon = null;
            place_pokemon_panel.get(place1).no_check();
            place_pokemon_panel.get(place2).no_check();
            return;
        } else {
            forReplace.add(0, placePokemon.get(place1));
            placePokemon.set(place1, placePokemon.get(place2));
            
            place_pokemon_panel.get(place1).set_Lv(place_pokemon_panel.get(place2).get_LV());
            place_pokemon_panel.get(place1).set_exp(place_pokemon_panel.get(place2).get_exp());
            place_pokemon_panel.get(place1).set_grade(place_pokemon_panel.get(place2).get_grade());
            place_pokemon_panel.get(place1).set_heart(place_pokemon_panel.get(place2).get_heart());
            place_pokemon_panel.get(place1).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),place_pokemon_panel.get(place1).get_LV()); //여기 문제 찾았다.
            place_pokemon_panel.get(place1).set_damage(place_pokemon_panel.get(place2).get_damage());
            place_pokemon_panel.get(place1).set_location_num(place1);
            place_pokemon_panel.get(place1).set_ex(place_pokemon_panel.get(place2).get_ex());
            place_pokemon_panel.get(place1).setVisible(true);
            place_pokemon_panel.get(place1).no_check();
            select_place_pokemon = null;
            
            placePokemon.set(place2, forReplace.get(0));
            
            place_pokemon_panel.get(place2).set_Lv(placePokemon.get(place2).getLv());
            place_pokemon_panel.get(place2).set_exp(placePokemon.get(place2).getExp());
            place_pokemon_panel.get(place2).set_grade(placePokemon.get(place2).getGrade());
            place_pokemon_panel.get(place2).set_heart(placePokemon.get(place2).getHealth());
            place_pokemon_panel.get(place2).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),placePokemon.get(place2).getLv()); //여기 문제 찾았다.
            place_pokemon_panel.get(place2).set_damage(placePokemon.get(place2).getPower());
            place_pokemon_panel.get(place2).set_location_num(place2);
            place_pokemon_panel.get(place2).set_ex(placePokemon.get(place2).getAbility());
            place_pokemon_panel.get(place2).setVisible(true);
            place_pokemon_panel.get(place1).no_check();
            select_place_pokemon = null;
            
            //
            forReplace.remove();
            System.out.println("<<<위치 변경 성공>>>");
            return;
        }
    }
	
	void isRankup(int placeNum) { // 경험치가 3이면 레벨 업, 레벨 업 하면 2/2 증가
		if (placePokemon.get(placeNum).getExp() == 3 && placePokemon.get(placeNum).getLv() == 1) {
			Pokemon p2 = s.getLV2Pokemon(placePokemon.get(placeNum).getPokemonNum());
			System.out.println(p2.getPokemonNum() + p2.getName());
			placePokemon.get(placeNum).setName(p2.getName());
			placePokemon.get(placeNum).setGrade(p2.getGrade());
			placePokemon.get(placeNum).setLv(2);
			placePokemon.get(placeNum).setExp(1);
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth()+2);
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower()+2);
			System.out.println("<<2레벨 업!>>");
			
			//패널 레벨 2로 업데이트
			place_pokemon_panel.get(placeNum).set_Lv(2);
			place_pokemon_panel.get(placeNum).set_exp(1);
			place_pokemon_panel.get(placeNum).set_grade(p2.getGrade());
			place_pokemon_panel.get(placeNum).set_ex(p2.getAbility());
			place_pokemon_panel.get(placeNum).set_heart(place_pokemon_panel.get(placeNum).get_heart() + 2);
			place_pokemon_panel.get(placeNum).set_damage(place_pokemon_panel.get(placeNum).get_damage() + 2);
			place_pokemon_panel.get(placeNum).set_pokemon_num(place_pokemon_panel.get(placeNum).get_pokemon_num(),place_pokemon_panel.get(placeNum).get_LV());
			
			return;
		} else if (placePokemon.get(placeNum).getExp() == 3 && placePokemon.get(placeNum).getLv() == 2) {
			Pokemon p3 = s.getLV3Pokemon(placePokemon.get(placeNum).getPokemonNum());
			System.out.println(p3.getPokemonNum() + p3.getName());
			placePokemon.get(placeNum).setName(p3.getName());
			placePokemon.get(placeNum).setGrade(p3.getGrade());
			placePokemon.get(placeNum).setLv(3);
			placePokemon.get(placeNum).setExp(1);
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth()+2);
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower()+2);
			System.out.println("<<3레벨 업!>>");
			
			//패널 레벨 3으로 업데이트
			place_pokemon_panel.get(placeNum).set_Lv(3);
			place_pokemon_panel.get(placeNum).set_exp(1);
			place_pokemon_panel.get(placeNum).set_ex(p3.getAbility());
			place_pokemon_panel.get(placeNum).set_grade(p3.getGrade());
			place_pokemon_panel.get(placeNum).set_heart(place_pokemon_panel.get(placeNum).get_heart() + 2);
			place_pokemon_panel.get(placeNum).set_damage(place_pokemon_panel.get(placeNum).get_damage() + 2);
			place_pokemon_panel.get(placeNum).set_pokemon_num(place_pokemon_panel.get(placeNum).get_pokemon_num(),place_pokemon_panel.get(placeNum).get_LV());
			
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
		} else if (placePokemon.get(placeNum).getLv() == 3) {
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin = coin + 3;
			status_panel.set_coin_num(coin);
			System.out.println("<<<판매 성공>>>");
			return;
		} else if (placePokemon.get(placeNum).getLv() == 2) {
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin = coin + 2;
			status_panel.set_coin_num(coin);
			System.out.println("<<<판매 성공>>>");
			return;
		} else {
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin++;
			status_panel.set_coin_num(coin);
			System.out.println("<<<판매 성공>>>");
			return;
		}
		
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
	
	boolean isAllNull() { //배치 구역이 전부 null인지 확인
		for (int i = 0; i < 5; i++) {
			if (placePokemon.get(i) != null) {
				return false;
			}
		}
		return true;
	}
	
	int randomItemNum() { // 아이템 수 만큼 난수
		if (turnNum <= 6) {
			return random.nextInt(turnNum * 4) + 1;
		} else {
			return random.nextInt(24) + 1;
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
					select_item = null;
					select_place_pokemon.no_check();
					System.out.println("<<< 아이템을 쓰기에는 포켓몬이 적습니다! >>>");
					return;
				}
			}
			if (itemNum == 3 && itemNum == 4 && itemNum == 6 && itemNum == 14) {
				if (remainNum < 2) {
					select_item = null;
					select_place_pokemon.no_check();
					System.out.println("<<< 아이템을 쓰기에는 포켓몬이 적습니다! >>>");
					return;
				}
			}
			buyItemAbility(shopPlaceNum, myPlaceNum);
			item.set(shopPlaceNum, null);
			item_panel.get(shopPlaceNum).setVisible(false);
			select_item = null;
			select_place_pokemon.no_check();
			frozenItemNum[shopPlaceNum] = false;
			System.out.println("<<<구매 성공>>>");
			coin = coin - 3;
			status_panel.set_coin_num(coin);
			return;
		}
	}
	
	boolean validationShopItemNum(int placeNum) { // 상점 구역 번호 아이템 검사
		if (2 < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean isNullShopItem(int placeNum) {
		if (item.get(placeNum) == null)
			return true;
		return false;
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
			select_item.get_frozen_panel().setVisible(true);
			return;
		} else {
			frozenItemNum[placeNum] = false;
			System.out.println("<<<" + (placeNum + 1) + "번에 얼리기 취소 설정>>>");
			select_item.get_frozen_panel().setVisible(false);
			return;
		}
	}
	
	boolean validationShopPokemonNum(int placeNum) { // 상점 구역 번호 유효성 검사
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
}

