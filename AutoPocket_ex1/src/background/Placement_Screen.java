package background;

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
	private int badge = 0; // 뱃�?
	private int turnNum = 1;// ?�� ?��
	Random random = new Random();
	private JPanel contentPane;
	private LinkedList<Pokemon> shopPokemon;
	private LinkedList<Obj_Panel2> shop_pokemon_panel; //?��?�� ?��?��?�� ???��?���? ?��?��
	private Obj_Panel2 select_shop_pokemon; //?��?��?��?�� ?��?��?�� ?��?��?�� ???��?��
	private LinkedList<Place_Area2> place_area_panel; //배치 ?��?��?�� ???��?���? ?��?��
	private Place_Area2 select_place; //?��?�� ?�� 배치 ?��?��?�� �?�?고있?��
	private LinkedList<Obj_Panel2> place_pokemon_panel; //배치?�� ?��치한 ?��켓몬 ?��?��?�� �?�?고있?��
	private Obj_Panel2 select_place_pokemon; 
	public LinkedList<Pokemon> placePokemon;
	private LinkedList<Pokemon> forReplace;
	private Status_Panel status_panel;
	setPokemon s = new setPokemon();
	private LinkedList<item> item;
	private LinkedList<Item_Panel> item_panel;
	setItem t = new setItem();
	boolean frozenPokemonNum[] = { false, false, false, false, false }; // ?��?�� ?��켓몬 ?��리기 ?��?��
	boolean frozenItemNum[] = { false, false };// ?��?��?�� ?��리기
	public ImagePanel placementbackground = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\placement_background.png").getImage());
	private Item_Panel select_item;
	boolean item24Effect = false;
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
		
		placementbackground.setBounds(0, 0, 1920, 1080);
		placementbackground.setBackground(new Color(255,255,255));
		placementbackground.setLayout(null);
		
		
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
	
	public void panel_setting() { //?��?�� ?��켓몬 초기?��?��
		
			
		//---------------Item_1_panel------------------//
		Item_Panel item1 = new Item_Panel();
		ImagePanel item_location_1 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\select_location.png").getImage());
		item1.setBounds(1325, 625, 409, 245);
		item_location_1.setBounds(1328, 780, 184, 110);
		placementbackground.add(item1);
		placementbackground.add(item_location_1);
		item_panel.add(0, item1);
		//-------------------------------------------//	
			
		//---------------Item_2_panel------------------//
		Item_Panel item2 = new Item_Panel();
		ImagePanel item_location_2 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\select_location.png").getImage());
		item2.setBounds(1564, 625, 356, 245);
		item_location_2.setBounds(1570, 780, 184, 110);
		placementbackground.add(item2);
		placementbackground.add(item_location_2);
		item_panel.add(1, item2);
		//-------------------------------------------//	
			
			
		
		//---------------shop_obj_panel--------------------//
		
		
			Obj_Panel2 shop_obj_1 = new Obj_Panel2();
			placementbackground.add(shop_obj_1);
			shop_pokemon_panel.add(0, shop_obj_1);
			shop_obj_1.setBounds(161, 566, 486, 340);
			shop_obj_1.setVisible(false); //?��?��?��?�� ?��보이�?
				
			Obj_Panel2 shop_obj_2 = new Obj_Panel2();
			placementbackground.add(shop_obj_2);
			shop_obj_2.setBounds(373, 566, 486, 340);
			shop_pokemon_panel.add(1, shop_obj_2);
			shop_obj_2.setVisible(false);
				
			Obj_Panel2 shop_obj_3 = new Obj_Panel2();
			
			placementbackground.add(shop_obj_3);
			shop_pokemon_panel.add(2, shop_obj_3);
			shop_obj_3.setBounds(585, 566, 486, 340);
			shop_obj_3.setVisible(false);
				
			Obj_Panel2 shop_obj_4 = new Obj_Panel2();
			
			placementbackground.add(shop_obj_4);
			shop_pokemon_panel.add(3, shop_obj_4);
			shop_obj_4.setBounds(797, 566, 486, 340);
			shop_obj_4.setVisible(false);
				
			Obj_Panel2 shop_obj_5 = new Obj_Panel2();
			shop_obj_5.setBounds(1008, 566, 486, 340);
			placementbackground.add(shop_obj_5);
			shop_pokemon_panel.add(4, shop_obj_5);
			shop_obj_5.setVisible(false);
				
		//------------------------------------------//
			Shop_Area shop_area = new Shop_Area();
			shop_area.setBounds(150, 797, 1045, 113);
			placementbackground.add(shop_area);
				
		//---------------user_obj_panel------------------//
			Obj_Panel2 user_obj_1 = new Obj_Panel2();
			user_obj_1.setLocation(505, 190);
			placementbackground.add(user_obj_1);
			place_pokemon_panel.add(0, user_obj_1);
			user_obj_1.setVisible(false); 
				
			Obj_Panel2 user_obj_2 = new Obj_Panel2();
			user_obj_2.setLocation(717, 190);
			placementbackground.add(user_obj_2);
			place_pokemon_panel.add(1, user_obj_2);
			user_obj_2.setVisible(false); 
			
			Obj_Panel2 user_obj_3 = new Obj_Panel2();
			user_obj_3.setLocation(929, 190);
			placementbackground.add(user_obj_3);
			place_pokemon_panel.add(2, user_obj_3);
			user_obj_3.setVisible(false); 
				
			Obj_Panel2 user_obj_4 = new Obj_Panel2();
			user_obj_4.setLocation(1141, 190);
			placementbackground.add(user_obj_4);
			place_pokemon_panel.add(3, user_obj_4);
			user_obj_4.setVisible(false); 
			
			Obj_Panel2 user_obj_5 = new Obj_Panel2();
			user_obj_5.setLocation(1353, 190);
			placementbackground.add(user_obj_5);
			place_pokemon_panel.add(4, user_obj_5);
			user_obj_5.setVisible(false); 
		//------------------------------------------//
			
		//---------------user_place_panel------------------//
		Place_Area2 place_1 = new Place_Area2(0);
		placementbackground.add(place_1);
		place_area_panel.add(place_1);
			
		Place_Area2 place_2 = new Place_Area2(1);
		place_2.setLocation(707, 418);
		placementbackground.add(place_2);
		place_area_panel.add(place_2);
		
		Place_Area2 place_3 = new Place_Area2(2);
		place_3.setLocation(919, 418);
		placementbackground.add(place_3);
		place_area_panel.add(place_3);
		
		Place_Area2 place_4 = new Place_Area2(3);
		place_4.setLocation(1131, 418);
		placementbackground.add(place_4);
		place_area_panel.add(place_4);
		
		Place_Area2 place_5 = new Place_Area2(4);
		place_5.setLocation(1343, 418);
		placementbackground.add(place_5);
		place_area_panel.add(place_5);
		//------------------------------------------//
		
		//---------------status_panel------------------//
		status_panel = new Status_Panel(coin, life, badge);
		status_panel.setBounds(0, 0, 874, 168);
		placementbackground.add(status_panel);
		//-------------------------------------------//
		
		//---------------reroll_panel------------------//
		ImagePanel reroll = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\reroll.png").getImage());
		reroll.setLocation(12, 957);
		placementbackground.add(reroll);
		//-------------------------------------------//
			
		//---------------frozen_panel------------------//
		ImagePanel frozen = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\prozen.png").getImage());
		frozen.setLocation(253, 935);
		placementbackground.add(frozen);
		//-------------------------------------------//	
		
		//---------------next_panel------------------//
		ImagePanel next_btn = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\next_btn.png").getImage());
		next_btn.setLocation(1663, 911);
		placementbackground.add(next_btn);
		next_btn.setLocation(1663, 911);
		//-------------------------------------------//	
		
		//---------------next_panel------------------//
		ImagePanel sell_btn = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\sell.png").getImage());
		sell_btn.setBounds(1259, 916, 300, 145);
		placementbackground.add(sell_btn);
		
		
		//item 1번째 ?��?��?��
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
		//?��?��?�� ?���?
		item1.get_item_panel().addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				item1.show_ex();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				item1.no_show_ex();
			}
		
		 });
		
		//item 2번째 ?��?��?��
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
		
		//게임 ?���? 진행
		next_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				shop_reroll();
				coin = 10;
				turnNum++;
				status_panel.set_coin_num(coin);
				System.out.println("?��?��?��갔다?��");
			}
		});
			
		//?��?�� ?��켓몬 ?��?���? ?��?��?���? ?���? ?��?��?�� 객체�? ???��?��
		for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
			shop_pokemon_panel.get_pokemon_panel().addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            shop_select_obj(shop_pokemon_panel); 	            
		        }
		    });
		}
		 //배치구역?�� ?��?�� ?��켓몬?�� ?��?��?�� ?��(경험�? 증�? ?��벤트)
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
				        			System.out.println("코인?�� �?족합?��?��.");
				        		}
				        	}else {
				        		System.out.println("?��켓몬 ?��벨업 ?��?��?���? ?��?��");
				        	}
			        	}
			        	//?��?��?�� ?��?�� ?��벤트
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
		 
		//배치 ?��?�� ?��?��?�� ?��?��밖에 ?��?��?���? ?���? ?��?��?�� 객체?�� ?��치�?? ???��?��
		 for (Place_Area2 placeArea : place_area_panel) {
	            placeArea.get_no_select_place_panel().addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    selectPlaceArea(placeArea);
	                    
	                    if(select_shop_pokemon != null) { //select_shop_pokemon?�� 객체 ?��?���? ?��?�� 구매 x //?��?�� 구매
	                    	if(coin >= 3) {
		                    	int shop_select_location = select_shop_pokemon.get_location_num();  
			                	int place_select_location = select_place.get_location_num();
			                	shop_buy(shop_select_location, place_select_location);
			                	status_panel.set_coin_num(coin);
	                    	}else {
	                    		System.out.println("코인?�� �?족합?��?��.");
	                    	}
	                    }else {
	                    	System.out.println("?��켓몬?�� 구매?��?�� ?��?�� ?��?��");
	                    }
		                     
	                    if(select_place_pokemon != null) { //select_place_pokemon?�� 객체 ?��?���? ?���? ?��?�� x
			                int place_select_pokemon = select_place_pokemon.get_location_num(); //?��켓몬 ?���? ?��기기
			                int place_select = select_place.get_location_num();
			                place_replace(place_select_pokemon, place_select);
	                    }else {
	                    	//System.out.println("?��치구?��?��?�� ?��?��?�� ?��켓몬?�� ?��?��?��?��");
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
		
		//리롤 ?���? ?��벤트
		reroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(coin > 0) {
					shop_reroll();
					coin = coin - 1;
					status_panel.set_coin_num(coin);
				}else {
					System.out.println("코인?�� �?족합?��?��");
				}
			}
		});
		
		
		
		//?��리기 ?���? ?��벤트
		frozen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(select_shop_pokemon != null) {
					int shop_select_location = select_shop_pokemon.get_location_num();
					select_shop_pokemon.no_check();
					pokemon_frozen(shop_select_location);
					select_shop_pokemon = null;
				}else {
					System.out.println("?��리기 ?��?��?��?��");
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
	
	
	void settingList() { // 그냥 비어?���? ?��?�� ?��?�� null�? �??�� ?��
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
		
	}
	
	void shop_reroll() { // ?��?�� ?��켓몬 리롤
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
	
	int randomNum() { // ?��켓몬 ?�� 만큼?�� ?��?��
		if(turnNum<=6) {
			return random.nextInt(turnNum*10) + 1;
		}else {
			return random.nextInt(60) + 1;
		}
	}
	
	
	int shop_place_num() { // ?��?�� 기물 ?���? ?��?�� ?��?�� ?��?�� ?��?�� ?��?���? 값이 커진?��.
		if (turnNum == 1) {
			return 3;
		} else if (turnNum <= 3) {
			return 4;
		} else {
			return 5;
		}
	}
	
	void shop_show(int shopPlaceNum) { // ?��?�� ?��켓몬 ?��?��
		System.out.println("<<<?��?�� 구역 출력>>>");
		int i = 0;
		while (i < shopPlaceNum) {
			if (shopPokemon.get(i) == null) {
				System.out.println((i + 1) + "�? 비어?��?��");
			} else if (frozenPokemonNum[i]) {
				System.out.println((i + 1) + "�? ?���?! ?���? : " + shopPokemon.get(i).getName() + " / ???�� : "
						+ shopPokemon.get(i).getType() + " / ?���? : " + shopPokemon.get(i).getGrade() + " / LV : "
						+ shopPokemon.get(i).getLv() + " / 체력 : " + shopPokemon.get(i).getHealth() + " / 공격?�� : "
						+ shopPokemon.get(i).getPower() + " / ?��?�� : " + shopPokemon.get(i).getAbility());
			} else {
				System.out.println((i + 1) + "�? ?���? : " + shopPokemon.get(i).getName() + " / ???�� : "
						+ shopPokemon.get(i).getType() + " / ?���? : " + shopPokemon.get(i).getGrade() + " / LV : "
						+ shopPokemon.get(i).getLv() + " / 체력 : " + shopPokemon.get(i).getHealth() + " / 공격?�� : "
						+ shopPokemon.get(i).getPower() + " / ?��?�� : " + shopPokemon.get(i).getAbility());
				
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
	void shop_select_obj(Obj_Panel2 selectedPanel) { //?��?��?�� ?��?�� ?��?��?�� ?��?��?��?��?�� ?��머�? ?��?�� ?��?��?? 체크 ?��?�� ?��
	    for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
	        if (shop_pokemon_panel.equals(selectedPanel)) {
	            if (shop_pokemon_panel.ischecked()) {
	            	shop_pokemon_panel.no_check();
	                select_shop_pokemon = null; // ?��?�� ?��?�� ?�� select_shop_pokemon?�� null�? ?��?��
	            } else {
	            	shop_pokemon_panel.check();
	                select_shop_pokemon = shop_pokemon_panel;
	                System.out.println("?��?��?��?�� ?��?��?�� ?��켓몬 ?���?" + select_shop_pokemon.get_location_num());
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
	                select_place_pokemon = null; // ?��?�� ?��?�� ?�� select_shop_pokemon?�� null�? ?��?��
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
	            	System.out.println("?��?��?�� 배치구역 ?��켓몬 ?��치는" + select_place_pokemon.get_location_num() + "?��?��?��.");
	            	
	            }
	        } else {
	        	place_pokemon_panel.no_check();
	        }
	    }
	}

	
    private void selectPlaceArea(Place_Area2 placeArea) { //?��치�?? ?��?��?��?��?�� 처리?��주는 ?��?��
        placeArea.check();
        select_place = placeArea;
        System.out.println("?��?��?�� 배치 ?���? 번호?�� " + select_place.get_location_num()+ "?��?��?��.");
    }

    private void deselectPlaceArea(Place_Area2 placeArea) { //?��치리�? ?��?�� ?��?��?�� 처리?��주는 ?��?��
        placeArea.no_check();
        select_place = null;
    }
	

    void pokemon_frozen(int placeNum) { // ?��?�� ?��켓몬 ?��리기
        if (!validationShopPokemonNum(placeNum)) {
            System.out.println("<<<?��?�� ?���?>>>");
            return;
        } else if (isNullShopPokemon(placeNum)) {
            System.out.println("<<<비어?��?��?��?��.>>>");
            return;
        } else if (frozenPokemonNum[placeNum] == false) {
            frozenPokemonNum[placeNum] = true;
            select_shop_pokemon.get_frozen_panel().setVisible(true);
            System.out.println("<<<" + (placeNum + 1) + "번에 ?��리기 ?��?��>>>");
            return;
        } else {
            frozenPokemonNum[placeNum] = false;
            select_shop_pokemon.get_frozen_panel().setVisible(false);
            System.out.println("<<<" + (placeNum + 1) + "번에 ?��리기 취소 ?��?��>>>");
            return;
        }
    }
	
	void shop_buy(int shopPlaceNum, int myPlaceNum) { // ?��?��?��?�� ?��켓몬 구매
		if (!validationShopNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("?��?�� ?���?");
			return;
		} else if (isNullShopPokemon(shopPlaceNum)) { // ?���? 구매?�� ?��?�� 번호�? 구매 불�?
			System.out.println("<<<" + (shopPlaceNum + 1) + "�� ������ ��.>>>");
			return;
		} else if (!isNullPlacePokemon(myPlaceNum)) { // 배치 구역?�� ?��켓몬?�� ?��?���? 구매 불�?
			if (shop_buy_expUp(shopPlaceNum, myPlaceNum)) {
				shopPokemon.set(shopPlaceNum, null);
				frozenPokemonNum[shopPlaceNum] = false;
				System.out.println("<<<���� ����>>>");
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
				System.out.println("<<<" + (myPlaceNum + 1) + "번에 ?���? ?��?��?��?��.>>>");
			{
				return;
			}
		} else { // 배치구역?�� ?��켓몬?�� ?��?���? 구매?���?
			placePokemon.set(myPlaceNum, shopPokemon.get(shopPlaceNum));
			place_pokemon_panel.get(myPlaceNum).set_Lv(shop_pokemon_panel.get(shopPlaceNum).get_LV());
			place_pokemon_panel.get(myPlaceNum).set_exp(shop_pokemon_panel.get(shopPlaceNum).get_exp());
			place_pokemon_panel.get(myPlaceNum).set_grade(shop_pokemon_panel.get(shopPlaceNum).get_grade());
			place_pokemon_panel.get(myPlaceNum).set_heart(shop_pokemon_panel.get(shopPlaceNum).get_heart());
			place_pokemon_panel.get(myPlaceNum).set_damage(shop_pokemon_panel.get(shopPlaceNum).get_damage());
			place_pokemon_panel.get(myPlaceNum).set_location_num(myPlaceNum);
			place_pokemon_panel.get(myPlaceNum).set_pokemon_num(shop_pokemon_panel.get(shopPlaceNum).get_pokemon_num(),shop_pokemon_panel.get(shopPlaceNum).get_LV());
			place_pokemon_panel.get(myPlaceNum).set_ex(shop_pokemon_panel.get(shopPlaceNum).get_ex());
			place_pokemon_panel.get(myPlaceNum).setVisible(true); //?��?��?�� set?��주고 보이�? ?���?
			
	
			shopPokemon.set(shopPlaceNum, null); 
			shop_pokemon_panel.get(shopPlaceNum).setVisible(false); //shop_place_num?�� null?��?�� ?��보이�? ?���?
			shop_pokemon_panel.get(shopPlaceNum).no_frozen();
			shop_pokemon_panel.get(myPlaceNum).get_frozen_panel().setVisible(false);
			select_place.no_check();
			
			frozenPokemonNum[shopPlaceNum] = false;
			System.out.println(select_shop_pokemon.get_location_num() + "�? ?��?�� ?��켓몬?��");
        	System.out.println(select_place.get_location_num() + "번에 배치?��???��?��?��.");
			System.out.println("구매 ?���?");
			
			select_place_pokemon = null;
			select_shop_pokemon = null; //?��?�� ?��?�� 초기?��
			select_place = null; //?���? ?��?�� 초기?��
			
			buyAbility(myPlaceNum);
			coin = coin - 3;
			return;
		}
	}
	
	boolean validationShopNum(int placeNum) { // ?��?�� 구역 번호 ?��?��?�� �??��
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean validationPlaceNum(int placeNum) { // 배치 구역 번호 ?��?��?�� �??��
		if (4 < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean isNullShopPokemon(int placeNum) { // ?��?�� 구역 ?��켓몬?�� null?���? ?��?��
		if (shopPokemon.get(placeNum) == null)
			return true;
		return false;
	}
	
	boolean isNullPlacePokemon(int placeNum) { // 배치 구역 ?��켓몬?�� null?���? ?��?��
		if (placePokemon.get(placeNum) == null)
			return true;
		return false;
	}
	
	boolean shop_buy_expUp(int shopPlaceNum, int myPlaceNum) {
		if (placePokemon.get(myPlaceNum) != null) { // 같�? ?��켓몬?���? 경험�? 증�?
			if (shopPokemon.get(shopPlaceNum).getPokemonNum() == placePokemon.get(myPlaceNum).getPokemonNum()) {
				if (placePokemon.get(myPlaceNum).getLv() == 3) {
					System.out.println("<< 3?���? ?��?��?��!! >>");
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
				System.out.println("<<<경험�? 증�?!>>>");
				return true;
			}
		}
		return false;
	}
	
	int checkRemainNum() { // ?��?��?��?�� 배치 구역 ?��켓몬 ?�� 반환
		int i = 0;
		for (Pokemon p : placePokemon) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}
	
	
	void buyAbility(int placeNum) { // 구매 ?�� ?��?��
		int lv = placePokemon.get(placeNum).getLv();
		switch (placePokemon.get(placeNum).getPokemonNum()) {
		case 8: { // 구매 ?�� ?��?��?�� ?���? 1, 2, 3�? 공격?�� 1 증�?
			int remainNum = checkRemainNum();
			if ((lv == 1 && remainNum >= 1) || (lv == 2 && remainNum == 1) || (lv == 3 && remainNum == 1)) {
				while (true) {
					int i = random.nextInt(5);
					if (placePokemon.get(i) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
						placePowerAdjust(i, 1);
						return;
					}
				}
			} else if ((lv == 2 && remainNum >= 2) || (lv == 3 && remainNum == 2)) { // 구매 ?�� ?��?��?�� ?���? 2�? 공격?�� 1 증�?
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					while (i == j) {
						i = random.nextInt(5);
						j = random.nextInt(5);
					}
					if (placePokemon.get(i) != null && placePokemon.get(j) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
						placePowerAdjust(i, 1);
						placePowerAdjust(j, 1);
						return;
					}
				}
			} else if (remainNum >= 3) { // 구매 ?�� ?��?��?�� ?���? 3�? 공격?�� 1 증�?
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
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
						placePowerAdjust(i, 1);
						placePowerAdjust(j, 1);
						placePowerAdjust(k, 1);
						System.out.println("<<<?��?�� ?��?��>>>");
						return;
					}
				}
			}
		}
		case 13: { // 구매 ?�� ?�� ?��?�� 배치?�� ?���? 공격?��, 체력 2증�?
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
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
					placeHealthAdjust(placeNum + 1, abilityNum);
					placePowerAdjust(placeNum + 1, abilityNum);
					return;
				}
			}
			if (placeNum == 4) {
				if (placePokemon.get(placeNum - 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
					placeHealthAdjust(placeNum - 1, abilityNum);
					placePowerAdjust(placeNum - 1, abilityNum);
					return;
				}
			}
		}
		case 17: { // 구매 ?�� 공격?��?�� �??�� ?��?? ?��군에�? 공격?�� 2�? 증�?
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
			System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
			placePowerAdjust(lowPowerPlaceNum, adjustNum);
			return;
		}
		case 33: { // ���� �� �� ���� ��ġ�� �Ʊ� ���ݷ� 3 ����
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
                    System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �ɷ� ��� >>>");
                    placePowerAdjust(placeNum + 1, abilityNum);
                    return;
                }
            } else if (placeNum == 4) {
                if (placePokemon.get(placeNum - 1) == null) {
                    return;
                } else {
                    System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �ɷ� ��� >>>");
                    placePowerAdjust(placeNum - 1, abilityNum);
                    return;
                }
            } else {
                System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �ɷ� ��� >>>");
                if (placePokemon.get(placeNum + 1) == null) {
                    return;
                } else {
                    placePowerAdjust(placeNum + 1, abilityNum);
                }
                if (placeNum == 4) {
                    if (placePokemon.get(placeNum - 1) == null) {
                        return;
                    } else {
                        placePowerAdjust(placeNum - 1, abilityNum);
                        return;
                    }
                }
            }
        }
		case 48: { // 구매 ?�� ?��?��?�� ?��군에�? 공격?��, 체력 3 증�?
			int abilityNum = 3;
			if (lv == 2) {
				abilityNum = 4;
			} else if (lv == 3) {
				abilityNum = 5;
			}
			while (true) {
				int i = random.nextInt(5);
				if (placePokemon.get(i) != null) {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
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
	
	void placeHealthAdjust(int placeNum, int adjustNum) { // ?���? 체력 조정
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth() + adjustNum);
			place_pokemon_panel.get(placeNum).set_heart(place_pokemon_panel.get(placeNum).get_heart() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 체력 조정 " + adjustNum + " >>>");
		}
	}

	void placePowerAdjust(int placeNum, int adjustNum) { // ?���? 공격?�� 조정
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower() + adjustNum);
			place_pokemon_panel.get(placeNum).set_damage(place_pokemon_panel.get(placeNum).get_damage() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 공격?�� 조정 " + adjustNum + " >>>");
		}
	}
	
	
	void place_replace(int place1, int place2) { // 배치 구역 ?��켓몬 ?���? 바꾸�?
        if (place1 == place2 || !validationPlaceNum(place1) ||  !validationPlaceNum(place2)) {
            System.out.println("<<<?��?�� ?���?>>>");
            return;
        } else if (isNullPlacePokemon(place1) && isNullPlacePokemon(place2)) {
            System.out.println("<<<?�� ?�� 비어?��?�� 배치 번호�? ?��?��?��?��?��?��?��.>>>");
            return;
        } else if (placePokemon.get(place1) != null && placePokemon.get(place2) == null) { // place 1?�� null?�� ?��?���?
            placePokemon.set(place2, placePokemon.get(place1));// place2 �? 복사 ?�� plcae1?? null�?
            placePokemon.set(place1, null);
           place_pokemon_panel.get(place1).setVisible(false);
            
            place_pokemon_panel.get(place2).set_Lv(place_pokemon_panel.get(place1).get_LV());
            place_pokemon_panel.get(place2).set_exp(place_pokemon_panel.get(place1).get_exp());
            place_pokemon_panel.get(place2).set_grade(place_pokemon_panel.get(place1).get_grade());
            place_pokemon_panel.get(place2).set_heart(place_pokemon_panel.get(place1).get_heart());
            place_pokemon_panel.get(place2).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),place_pokemon_panel.get(place1).get_LV()); //?���? 문제 찾았?��.
            place_pokemon_panel.get(place2).set_damage(place_pokemon_panel.get(place1).get_damage());
            place_pokemon_panel.get(place2).set_location_num(place2);
            place_pokemon_panel.get(place2).set_ex(place_pokemon_panel.get(place1).get_ex());
            place_pokemon_panel.get(place2).setVisible(true);
            
            System.out.println(place_pokemon_panel.get(place1).get_location_num() + "�? ?��치의 ?��켓몬?�� " + place_pokemon_panel.get(place2).get_location_num() + "�? ?���?");
            
            place_area_panel.get(place2).no_check();
            place_pokemon_panel.get(place2).no_check();
            
            select_place_pokemon = null;
            select_place = null;
            
            return;
        } else if (placePokemon.get(place1) == null && placePokemon.get(place2) != null) { // place 2?�� null?�� ?��?���?
            placePokemon.set(place1, placePokemon.get(place2));// place1 �? 복사 ?�� plcae2?? null�?
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
            System.out.println("?��벨업 ?���?");
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
            place_pokemon_panel.get(place1).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),place_pokemon_panel.get(place1).get_LV()); //?���? 문제 찾았?��.
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
            place_pokemon_panel.get(place2).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),placePokemon.get(place2).getLv()); //?���? 문제 찾았?��.
            place_pokemon_panel.get(place2).set_damage(placePokemon.get(place2).getPower());
            place_pokemon_panel.get(place2).set_location_num(place2);
            place_pokemon_panel.get(place2).set_ex(placePokemon.get(place2).getAbility());
            place_pokemon_panel.get(place2).setVisible(true);
            place_pokemon_panel.get(place1).no_check();
            select_place_pokemon = null;
            
            //
            forReplace.remove();
            System.out.println("<<<?���? �?�? ?���?>>>");
            return;
        }
    }
	
	void isRankup(int placeNum) { // 경험치�? 3?���? ?���? ?��, ?���? ?�� ?���? 2/2 증�?
		if (placePokemon.get(placeNum).getExp() == 3 && placePokemon.get(placeNum).getLv() == 1) {
			Pokemon p2 = s.getLV2Pokemon(placePokemon.get(placeNum).getPokemonNum());
			System.out.println(p2.getPokemonNum() + p2.getName());
			placePokemon.get(placeNum).setName(p2.getName());
			placePokemon.get(placeNum).setGrade(p2.getGrade());
			placePokemon.get(placeNum).setLv(2);
			placePokemon.get(placeNum).setExp(1);
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth()+2);
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower()+2);
			System.out.println("<<2?���? ?��!>>");
			
			//?��?�� ?���? 2�? ?��?��?��?��
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
			System.out.println("<<3?���? ?��!>>");
			
			//?��?�� ?���? 3?���? ?��?��?��?��
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
	
	void place_sell(int placeNum) { // 배치 구역 ?��켓몬 ?���?
		if (!validationPlaceNum(placeNum)) {
			System.out.println("<<<?��?�� ?���?>>>");
			return;
		} else if (isNullPlacePokemon(placeNum)) {
			System.out.println("<<<비어?��?��?��?��.>>>");
			return;
		} else if (placePokemon.get(placeNum).getLv() == 3) {
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin = coin + 3;
			status_panel.set_coin_num(coin);
			System.out.println("<<<?���? ?���?>>>");
			sellAbility(placeNum);
			return;
		} else if (placePokemon.get(placeNum).getLv() == 2) {
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin = coin + 2;
			status_panel.set_coin_num(coin);
			System.out.println("<<<?���? ?���?>>>");
			sellAbility(placeNum);
			return;
		} else {
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin++;
			status_panel.set_coin_num(coin);
			System.out.println("<<<?���? ?���?>>>");
			sellAbility(placeNum);
			return;
		}
		
	}
	
	void sellAbility(int placeNum) { // ?���? ?�� ?��?��
		int lv = placePokemon.get(placeNum).getLv();
		switch (placePokemon.get(placeNum).getPokemonNum()) {
		case 7: { // ?���? ?�� ?���? 체력 1 증�?
			int adjustNum = lv;
			System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
			for (int i = 1; i < 5; i++) {
				if (placePokemon.get(i) != null && i + 1 != placeNum) {
					placeHealthAdjust(i, adjustNum);
				}
			}
			return;
		}
		case 14: {// ?���? ?�� ?��?��?�� ?���? 1,2,3?���? 버섯 ?��?��
			int remainNum = checkRemainNum();
			if ((lv == 1 && remainNum >= 1) || (lv == 2 && remainNum == 1) || (lv == 3 && remainNum == 1)) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
				while (true) {
					int i = random.nextInt(5);
					if (placePokemon.get(i) != null && i + 1 != placeNum) {
						effectNum[i] = 1; // ?���? 1?? 버섯 ?���?
						System.out.println("<<<" + placePokemon.get(i).getName() + " 버섯 ?���?! >>>");
						return;
					}
				}
			} else if ((lv == 2 && remainNum >= 2) || (lv == 3 && remainNum == 2)) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					if (placePokemon.get(i) != null && placePokemon.get(j) != null && i != j && i + 1 != placeNum) {
						effectNum[i] = 1;
						System.out.println("<<<" + placePokemon.get(i).getName() + " 버섯 ?���?! >>>");
						effectNum[j] = 1;
						System.out.println("<<<" + placePokemon.get(j).getName() + " 버섯 ?���?! >>>");
						return;
					}
				}
			} else {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					int k = random.nextInt(5);
					if (placePokemon.get(i) != null && placePokemon.get(j) != null && placePokemon.get(k) != null
							&& i != j && i != k && k != j && i + 1 != placeNum) {
						effectNum[i] = 1;
						System.out.println("<<<" + placePokemon.get(i).getName() + " 버섯 ?���?! >>>");
						effectNum[j] = 1;
						System.out.println("<<<" + placePokemon.get(j).getName() + " 버섯 ?���?! >>>");
						effectNum[k] = 1;
						System.out.println("<<<" + placePokemon.get(k).getName() + " 버섯 ?���?! >>>");
						return;
					}
				}
			}

		}
		case 49: { // ?���? ?�� ?��?�� ?��?�� ?��군에�? 공격?��,체력?�� 50%, 70%, 100%만큼 증�?
			if (placeNum == 4) {
				return;
			} else if (placePokemon.get(placeNum + 1) != null) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " ?��?�� ?��?�� >>>");
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
	
	boolean isAllNull() { //배치 구역?�� ?���? null?���? ?��?��
		for (int i = 0; i < 5; i++) {
			if (placePokemon.get(i) != null) {
				return false;
			}
		}
		return true;
	}
	
	int randomItemNum() { // ?��?��?�� ?�� 만큼 ?��?��
		if (turnNum <= 6) {
			return random.nextInt(turnNum * 4) + 1;
		} else {
			return random.nextInt(24) + 1;
		}
	}
	
	void shop_item_buy(int shopPlaceNum, int myPlaceNum) { // ?��?��?��?�� ?��?��?�� 구매
		if (!validationShopItemNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("?��?�� ?���?");
			return;
		} else if (isNullShopItem(shopPlaceNum)) { // ?���? 구매?�� ?��?�� 번호�? 구매 불�?
			System.out.println("<<<" + (shopPlaceNum + 1) + "�? ?��?��?�� 비어?��?��?��?��.>>>");
			return;
		} else if (isNullPlacePokemon(myPlaceNum)) { // 배치 구역?�� ?��켓몬?�� ?��?���? 구매 불�?
			System.out.println("<<<" + (myPlaceNum + 1) + "번에 ?��?��?��?��.>>>");
			return;
		} else {
			int itemNum = item.get(shopPlaceNum).getItemNum();
			int remainNum = checkRemainNum();
			if (itemNum == 8) {
				if (remainNum < 3) {
					select_item = null;
					select_place_pokemon.no_check();
					System.out.println("<<< ?��?��?��?�� ?��기에?�� ?��켓몬?�� ?��?��?��?��! >>>");
					return;
				}
			}
			if (itemNum == 3 && itemNum == 4 && itemNum == 6 && itemNum == 14) {
				if (remainNum < 2) {
					select_item = null;
					select_place_pokemon.no_check();
					System.out.println("<<< ?��?��?��?�� ?��기에?�� ?��켓몬?�� ?��?��?��?��! >>>");
					return;
				}
			}
			buyItemAbility(shopPlaceNum, myPlaceNum);
			item.set(shopPlaceNum, null);
			item_panel.get(shopPlaceNum).setVisible(false);
			select_item = null;
			select_place_pokemon.no_check();
			frozenItemNum[shopPlaceNum] = false;
			System.out.println("<<<구매 ?���?>>>");
			coin = coin - 3;
			status_panel.set_coin_num(coin);
			return;
		}
	}
	
	boolean validationShopItemNum(int placeNum) { // ?��?�� 구역 번호 ?��?��?�� �??��
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
	
	void buyItemAbility(int shopPlaceNum, int placeNum) { // ?��?��?�� 구매 ?�� ?��?��
		switch (item.get(shopPlaceNum).getItemNum()) {
		case 1: { // 공격?�� 1 증�?
			placePowerAdjust(placeNum, 1);
			break;
		}
		case 2: { // 체력 1 증�?
			placeHealthAdjust(placeNum, 1);
			break;
		}
		case 3: { // ?��?��?���? 2 기물?���? 공격?�� 1 증�?
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
		case 4: { // ?��?��?���? 2 기물?���? 체력 1 증�?
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
		case 5: { // 1/1 증�?
			placePowerAdjust(placeNum, 1);
			placeHealthAdjust(placeNum, 1);
			break;
		}
		case 6: { // ?��?��?���? 2 기물?���? 1/1 증�?
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
		case 7: { // ?��?��?���? 1 기물?���? 2/2 증�?
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
		case 8: { // ?��?��?���? 3 기물?���? 공격?�� 1 증�?
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
		case 9: { // ?��?��?�� 기물 ?�� ?�� 기물?�� 공격?�� 2?�� �??�� ?��?��?��.
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
		case 10: { // ?��?��?�� ?��켓몬?�� ??결에?�� ?��?��?��?���? 경험�? 1 증�?
			effectNum[placeNum] = 2;
			break;
		}
		case 11: { // 처음 맞�? 공격 무시
			effectNum[placeNum] = 1;
			break;
		}
		case 12: { // 코인 + 1
			this.coin++;
			break;
		}
		case 13: { // 체력 2 증�?
			placeHealthAdjust(placeNum, 2);
			break;
		}
		case 14: { // ?��?��?���? 2 기물?���? 2/2 증�?
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
		case 15: { // ?��?�� 기물�? ?�� ?��?�� 기물?�� 1/2 증�?
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
		case 16: { // ?��?�� ?��과는 ?���?�? ?�� ?��?��?��?�� ?��?��?�� 기물 ?���? ?�� ?��매코?�� 1 증�?
			effectNum[placeNum] = 3;
			break;
		}
		case 17: { // 2/2 증�?
			placePowerAdjust(placeNum, 2);
			placeHealthAdjust(placeNum, 2);
			break;
		}
		case 18: { // ?��?��?���? 1기물?���? 경험�? 1 증�?
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
		case 19: { // 50% ?��률로 코인 2 증�?
			int rNum = random.nextInt(2);
			if (rNum == 1) {
				System.out.println("<<< ?���?! >>>");
				this.coin++;
				this.coin++;
				break;
			} else {
				System.out.println("<<< ?��?��! >>> ");
				break;
			}
		}
		case 20: { // 공격?�� 3 증�?
			placePowerAdjust(placeNum, 3);
			break;
		}
		case 21: { // 경험�? 1 증�?
			placePokemon.get(placeNum).setExp(placePokemon.get(placeNum).getExp() + 1);
			break;
		}
		case 22: { // 3/3 증�?
			placePowerAdjust(placeNum, 3);
			placeHealthAdjust(placeNum, 3);
			break;
		}
		case 23: { // 코인 + 2
			this.coin++;
			this.coin++;
			break;
		}
		case 24: { // ??결에 ?���? ?�� 코인 2, ?���? ?�� 1 증�? 000000000000
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
			System.out.println("<<<?��?�� ?���?>>>");
			return;
		} else if (isNullShopItem(placeNum)) {
			System.out.println("<<<비어?��?��?��?��.>>>");
			return;
		} else if (frozenItemNum[placeNum] == false) {
			frozenItemNum[placeNum] = true;
			System.out.println("<<<" + (placeNum + 1) + "번에 ?��리기 ?��?��>>>");
			select_item.get_frozen_panel().setVisible(true);
			return;
		} else {
			frozenItemNum[placeNum] = false;
			System.out.println("<<<" + (placeNum + 1) + "번에 ?��리기 취소 ?��?��>>>");
			select_item.get_frozen_panel().setVisible(false);
			return;
		}
	}
	
	boolean validationShopPokemonNum(int placeNum) { // ?��?�� 구역 번호 ?��?��?�� �??��
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
}

