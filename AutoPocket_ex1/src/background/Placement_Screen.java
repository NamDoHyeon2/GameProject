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
	private int life = 10; // 紐⑹닲
	private int coin = 100; // 肄붿씤
	private int badge = 0; // 諭껋�
	public int turnNum = 1;// �꽩 �닔
	Random random = new Random();
	private JPanel contentPane;
	private LinkedList<Pokemon> shopPokemon;
	private LinkedList<Obj_Panel2> shop_pokemon_panel; //�긽�젏 �뙣�꼸�쓣 ���옣�븯怨� �엳�쓬
	private Obj_Panel2 select_shop_pokemon; //�긽�젏�뿉�꽌 �꽑�깮�븳 �뙣�꼸�쓣 ���옣�븿
	private LinkedList<Place_Area2> place_area_panel; //諛곗튂 �뙣�꼸�쓣 ���옣�븯怨� �엳�쓬
	private Place_Area2 select_place; //�꽑�깮 �븳 諛곗튂 �뙣�꼸�쓣 媛�吏�怨좎엳�쓬
	private LinkedList<Obj_Panel2> place_pokemon_panel; //諛곗튂�뿉 �쐞移섑븳 �룷耳볥が �뙣�꼸�쓣 媛�吏�怨좎엳�쓬
	private Obj_Panel2 select_place_pokemon; 
	public LinkedList<Pokemon> placePokemon;
	private LinkedList<Pokemon> forReplace;
	private Status_Panel status_panel;
	setPokemon s = new setPokemon();
	private LinkedList<item> item;
	private LinkedList<Item_Panel> item_panel;
	public LinkedList<Pokemon> toBattle;
	setItem t = new setItem();
	boolean frozenPokemonNum[] = { false, false, false, false, false }; // �긽�젏 �룷耳볥が �뼹由ш린 �꽕�젙
	boolean frozenItemNum[] = { false, false };// �븘�씠�뀥 �뼹由ш린
	public ImagePanel placementbackground = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\placement_background.png").getImage());
	private Item_Panel select_item;
	boolean item24Effect = false;
	private StartBattle battle_screen;
	public ImagePanel next_btn;
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
	
	public void panel_setting() { //�긽�젏 �룷耳볥が 珥덇린�꽕�젙
		
		//---------------Item_1_panel------------------//
		Item_Panel item1 = new Item_Panel();
		ImagePanel item_location_1 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\select_location.png").getImage());
		item1.setBounds(1325, 600, 409, 245);
		item_location_1.setBounds(1328, 780, 184, 110);
		placementbackground.add(item1);
		placementbackground.add(item_location_1);
		item_panel.add(0, item1);
		//-------------------------------------------//	
			
		//---------------Item_2_panel------------------//
		Item_Panel item2 = new Item_Panel();
		ImagePanel item_location_2 = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\select_location.png").getImage());
		item2.setBounds(1564, 600, 356, 245);
		item_location_2.setBounds(1570, 780, 184, 110);
		placementbackground.add(item2);
		placementbackground.add(item_location_2);
		item_panel.add(1, item2);
		//-------------------------------------------//	
			
			
		
		//---------------shop_obj_panel--------------------//
		
		
			Obj_Panel2 shop_obj_1 = new Obj_Panel2();
			placementbackground.add(shop_obj_1);
			shop_pokemon_panel.add(0, shop_obj_1);
			shop_obj_1.setVisible(false); //�뵒�뤃�듃�뒗 �븞蹂댁씠寃�
				
			Obj_Panel2 shop_obj_2 = new Obj_Panel2();
			shop_obj_2.setLocation(373, 566);
			placementbackground.add(shop_obj_2);
			shop_pokemon_panel.add(1, shop_obj_2);
			shop_obj_2.setVisible(false);
				
			Obj_Panel2 shop_obj_3 = new Obj_Panel2();
			shop_obj_3.setLocation(585, 566);
			placementbackground.add(shop_obj_3);
			shop_pokemon_panel.add(2, shop_obj_3);
			shop_obj_3.setVisible(false);
				
			Obj_Panel2 shop_obj_4 = new Obj_Panel2();
			shop_obj_4.setLocation(797, 566);
			placementbackground.add(shop_obj_4);
			shop_pokemon_panel.add(3, shop_obj_4);
			shop_obj_4.setVisible(false);
				
			Obj_Panel2 shop_obj_5 = new Obj_Panel2();
			shop_obj_5.setLocation(1009, 566);
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
		next_btn = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\next_btn.png").getImage());
		next_btn.setLocation(1663, 911);
		placementbackground.add(next_btn);
		next_btn.setLocation(1663, 911);
		//-------------------------------------------//	
		
		//---------------next_panel------------------//
		ImagePanel sell_btn = new ImagePanel(new ImageIcon("C:\\ex1\\AutoPocket_ex1\\src\\Images\\sell_1.png").getImage());
		sell_btn.setBounds(1259, 916, 300, 145);
		placementbackground.add(sell_btn);
		
		
		//item 1踰덉㎏ �꽑�깮�떆
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
		//�븘�씠�뀥 �꽕紐�
		item1.get_item_panel().addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				item1.show_ex();
			}
			@Override
			public void mouseExited(MouseEvent e) {
				item1.no_show_ex();
			}
		
		 });
		
		//item 2踰덉㎏ �꽑�깮�떆
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
		
		//寃뚯엫 �솕硫� 吏꾪뻾
		next_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		        
				//諛고��떆�옉
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
		                toBattle.get(4), effectNum);		        
		        getContentPane().add(battle_screen);
		        placementbackground.setVisible(false);
		        battle_screen.setVisible(true);
			}
		});
			
		//�긽�젏 �룷耳볥が �븯�굹留� �꽑�깮�릺寃� �븯怨� �꽑�깮�븳 媛앹껜瑜� ���옣�븿
		for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
			shop_pokemon_panel.get_pokemon_panel().addMouseListener(new MouseAdapter() {
		        @Override
		        public void mouseClicked(MouseEvent e) {
		            shop_select_obj(shop_pokemon_panel); 	            
		        }
		    });
		}
		 //諛곗튂援ъ뿭�뿉 �엳�뒗 �룷耳볥が�쓽 �룞�옉�쓣 �븿(寃쏀뿕移� 利앷� �씠踰ㅽ듃)
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
				        			System.out.println("肄붿씤�씠 遺�議깊빀�땲�떎.");
				        		}
				        	}else {
				        		System.out.println("�룷耳볥が �젅踰⑥뾽 �븷�젮�뒗嫄� �븘�떂");
				        	}
			        	}
			        	//�븘�씠�뀥 �궗�슜 �씠踰ㅽ듃
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
		 
		//諛곗튂 �옣�냼 �꽑�깮�쓣 �븯�굹諛뽰뿉 �꽑�깮�븯寃� �븯怨� �꽑�깮�븳 媛앹껜�쓽 �쐞移섎�� ���옣�븿
		 for (Place_Area2 placeArea : place_area_panel) {
	            placeArea.get_no_select_place_panel().addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    selectPlaceArea(placeArea);
	                    
	                    if(select_shop_pokemon != null) { //select_shop_pokemon�뿉 媛앹껜 �뾾�쑝硫� �긽�젏 援щℓ x //�긽�젏 援щℓ
	                    	if(coin >= 3) {
		                    	int shop_select_location = select_shop_pokemon.get_location_num();  
			                	int place_select_location = select_place.get_location_num();
			                	shop_buy(shop_select_location, place_select_location);
			                	status_panel.set_coin_num(coin);
	                    	}else {
	                    		System.out.println("肄붿씤�씠 遺�議깊빀�땲�떎.");
	                    	}
	                    }else {
	                    	System.out.println("�룷耳볥が�쓣 援щℓ�븯�뒗 �뻾�룞 �븘�떂");
	                    }
		                     
	                    if(select_place_pokemon != null) { //select_place_pokemon�뿉 媛앹껜 �뾾�쑝硫� �쐞移� �씠�룞 x
			                int place_select_pokemon = select_place_pokemon.get_location_num(); //�룷耳볥が �쐞移� �삷湲곌린
			                int place_select = select_place.get_location_num();
			                place_replace(place_select_pokemon, place_select);
	                    }else {
	                    	//System.out.println("�쐞移섍뎄�뿭�뿉�꽌 �꽑�깮�븳 �룷耳볥が�씠 �뾾�뒿�땲�떎");
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
		
		//由щ· �겢由� �씠踰ㅽ듃
		reroll.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(coin > 0) {
					shop_reroll();
					coin = coin - 1;
					status_panel.set_coin_num(coin);
				}else {
					System.out.println("肄붿씤�씠 遺�議깊빀�땲�떎");
				}
			}
		});
		
		
		
		//�뼹由ш린 �겢由� �씠踰ㅽ듃
		frozen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(select_shop_pokemon != null) {
					int shop_select_location = select_shop_pokemon.get_location_num();
					select_shop_pokemon.no_check();
					pokemon_frozen(shop_select_location);
					select_shop_pokemon = null;
				}else {
					System.out.println("�뼹由ш린 �뻾�룞�븘�떂");
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
	
	void startPlacement() { // 諛곗튂 �떒怨� �떆�옉 �떆 援ы쁽
		shop_reroll();
		coin = 10;
		turnNum++;
	}
	
	int coinUp() {
		for (Pokemon p : placePokemon) {
			if (p != null && p.getPokemonNum() == 56) { // ��寃� �듅由� �떆 肄붿씤 1, 2, 3 利앷�
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
	
	
	void settingList() { // 洹몃깷 鍮꾩뼱�몢硫� �뿉�윭 �뼚�꽌 null濡� 吏��젙 �븿
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
	
	void shop_reroll() { // �긽�젏 �룷耳볥が 由щ·
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
	
	int randomNum() { // �룷耳볥が �닔 留뚰겮�쓽 �궃�닔
		if(turnNum<=6) {
			return random.nextInt(turnNum*10) + 1;
		}else {
			return random.nextInt(60) + 1;
		}
	}
	
	
	int shop_place_num() { // �긽�젏 湲곕Ъ �닔瑜� �쐞�븳 �븿�닔 �꽩�씠 �뒛�뼱 �궇�닔濡� 媛믪씠 而ㅼ쭊�떎.
		if (turnNum == 1) {
			return 3;
		} else if (turnNum <= 3) {
			return 4;
		} else {
			return 5;
		}
	}
	
	void shop_show(int shopPlaceNum) { // �긽�젏 �룷耳볥が �솗�씤
		System.out.println("<<<�긽�젏 援ъ뿭 異쒕젰>>>");
		int i = 0;
		while (i < shopPlaceNum) {
			if (shopPokemon.get(i) == null) {
				System.out.println((i + 1) + "踰� 鍮꾩뼱�엳�쓬");
			} else if (frozenPokemonNum[i]) {
				System.out.println((i + 1) + "踰� �뼹由�! �씠由� : " + shopPokemon.get(i).getName() + " / ���엯 : "
						+ shopPokemon.get(i).getType() + " / �벑湲� : " + shopPokemon.get(i).getGrade() + " / LV : "
						+ shopPokemon.get(i).getLv() + " / 泥대젰 : " + shopPokemon.get(i).getHealth() + " / 怨듦꺽�젰 : "
						+ shopPokemon.get(i).getPower() + " / �뒫�젰 : " + shopPokemon.get(i).getAbility());
			} else {
				System.out.println((i + 1) + "踰� �씠由� : " + shopPokemon.get(i).getName() + " / ���엯 : "
						+ shopPokemon.get(i).getType() + " / �벑湲� : " + shopPokemon.get(i).getGrade() + " / LV : "
						+ shopPokemon.get(i).getLv() + " / 泥대젰 : " + shopPokemon.get(i).getHealth() + " / 怨듦꺽�젰 : "
						+ shopPokemon.get(i).getPower() + " / �뒫�젰 : " + shopPokemon.get(i).getAbility());
				
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
	void shop_select_obj(Obj_Panel2 selectedPanel) { //�븯�굹�쓽 �긽�젏 �뙣�꼸�쓣 �꽑�깮�뻽�쓣�븣 �굹癒몄� �긽�젏 �뙣�꼸�� 泥댄겕 �빐�젣 �맖
	    for (Obj_Panel2 shop_pokemon_panel : shop_pokemon_panel) {
	        if (shop_pokemon_panel.equals(selectedPanel)) {
	            if (shop_pokemon_panel.ischecked()) {
	            	shop_pokemon_panel.no_check();
	                select_shop_pokemon = null; // �꽑�깮 �빐�젣 �떆 select_shop_pokemon�쓣 null濡� �꽕�젙
	            } else {
	            	shop_pokemon_panel.check();
	                select_shop_pokemon = shop_pokemon_panel;
	                System.out.println("�긽�젏�뿉�꽌 �꽑�깮�븳 �룷耳볥が �쐞移�" + select_shop_pokemon.get_location_num());
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
	                select_place_pokemon = null; // �꽑�깮 �빐�젣 �떆 select_shop_pokemon�쓣 null濡� �꽕�젙
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
	            	System.out.println("�꽑�깮�븳 諛곗튂援ъ뿭 �룷耳볥が �쐞移섎뒗" + select_place_pokemon.get_location_num() + "�엯�땲�떎.");
	            	
	            }
	        } else {
	        	place_pokemon_panel.no_check();
	        }
	    }
	}

	
    private void selectPlaceArea(Place_Area2 placeArea) { //�쐞移섎�� �꽑�깮�뻽�쓣�븣 泥섎━�빐二쇰뒗 �븿�닔
        placeArea.check();
        select_place = placeArea;
        System.out.println("�꽑�깮�븳 諛곗튂 �쐞移� 踰덊샇�뒗 " + select_place.get_location_num()+ "�엯�땲�떎.");
    }

    private void deselectPlaceArea(Place_Area2 placeArea) { //�쐞移섎━瑜� �빐�젣 �뻽�쓣�븣 泥섎━�빐二쇰뒗 �븿�닔
        placeArea.no_check();
        select_place = null;
    }
	

    void pokemon_frozen(int placeNum) { // �긽�젏 �룷耳볥が �뼹由ш린
        if (!validationShopPokemonNum(placeNum)) {
            System.out.println("<<<�엯�젰 �삤瑜�>>>");
            return;
        } else if (isNullShopPokemon(placeNum)) {
            System.out.println("<<<鍮꾩뼱�엳�뒿�땲�떎.>>>");
            return;
        } else if (frozenPokemonNum[placeNum] == false) {
            frozenPokemonNum[placeNum] = true;
            select_shop_pokemon.get_frozen_panel().setVisible(true);
            System.out.println("<<<" + (placeNum + 1) + "踰덉뿉 �뼹由ш린 �꽕�젙>>>");
            return;
        } else {
            frozenPokemonNum[placeNum] = false;
            select_shop_pokemon.get_frozen_panel().setVisible(false);
            System.out.println("<<<" + (placeNum + 1) + "踰덉뿉 �뼹由ш린 痍⑥냼 �꽕�젙>>>");
            return;
        }
    }
	
	void shop_buy(int shopPlaceNum, int myPlaceNum) { // �긽�젏�뿉�꽌 �룷耳볥が 援щℓ
		if (!validationShopNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("�엯�젰 �삤瑜�");
			return;
		} else if (isNullShopPokemon(shopPlaceNum)) { // �씠誘� 援щℓ�븳 �긽�젏 踰덊샇硫� 援щℓ 遺덇�
			System.out.println("<<<" + (shopPlaceNum + 1) + "踰� �긽�젏�씠 鍮꾩뼱�엳�뒿�땲�떎.>>>");
			return;
		} else if (!isNullPlacePokemon(myPlaceNum)) { // 諛곗튂 援ъ뿭�뿉 �룷耳볥が�씠 �엳�쑝硫� 援щℓ 遺덇�
			if (shop_buy_expUp(shopPlaceNum, myPlaceNum)) {
				shopPokemon.set(shopPlaceNum, null);
				frozenPokemonNum[shopPlaceNum] = false;
				System.out.println("<<<援щℓ �꽦怨�>>>");
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
				System.out.println("<<<" + (myPlaceNum + 1) + "踰덉뿉 �씠誘� �엳�뒿�땲�떎.>>>");
			{
				return;
			}
		} else { // 諛곗튂援ъ뿭�뿉 �룷耳볥が�씠 �뾾�쑝硫� 援щℓ�꽦怨�
			placePokemon.set(myPlaceNum, shopPokemon.get(shopPlaceNum));
			place_pokemon_panel.get(myPlaceNum).set_Lv(shop_pokemon_panel.get(shopPlaceNum).get_LV());
			place_pokemon_panel.get(myPlaceNum).set_exp(shop_pokemon_panel.get(shopPlaceNum).get_exp());
			place_pokemon_panel.get(myPlaceNum).set_grade(shop_pokemon_panel.get(shopPlaceNum).get_grade());
			place_pokemon_panel.get(myPlaceNum).set_heart(shop_pokemon_panel.get(shopPlaceNum).get_heart());
			place_pokemon_panel.get(myPlaceNum).set_damage(shop_pokemon_panel.get(shopPlaceNum).get_damage());
			place_pokemon_panel.get(myPlaceNum).set_location_num(myPlaceNum);
			place_pokemon_panel.get(myPlaceNum).set_pokemon_num(shop_pokemon_panel.get(shopPlaceNum).get_pokemon_num(),shop_pokemon_panel.get(shopPlaceNum).get_LV());
			place_pokemon_panel.get(myPlaceNum).set_ex(shop_pokemon_panel.get(shopPlaceNum).get_ex());
			place_pokemon_panel.get(myPlaceNum).setVisible(true); //�뙣�꼸�뿉 set�빐二쇨퀬 蹂댁씠寃� �븯湲�
			
	
			shopPokemon.set(shopPlaceNum, null); 
			shop_pokemon_panel.get(shopPlaceNum).setVisible(false); //shop_place_num�씠 null�맆�븣 �븞蹂댁씠寃� �븯湲�
			shop_pokemon_panel.get(shopPlaceNum).no_frozen();
			shop_pokemon_panel.get(myPlaceNum).get_frozen_panel().setVisible(false);
			select_place.no_check();
			
			frozenPokemonNum[shopPlaceNum] = false;
			System.out.println(select_shop_pokemon.get_location_num() + "踰� �긽�젏 �룷耳볥が�쓣");
        	System.out.println(select_place.get_location_num() + "踰덉뿉 諛곗튂�븯���뒿�땲�떎.");
			System.out.println("援щℓ �꽦怨�");
			
			select_place_pokemon = null;
			select_shop_pokemon = null; //�긽�젏 �꽑�깮 珥덇린�솕
			select_place = null; //�쐞移� �꽑�깮 珥덇린�솕
			
			buyAbility(myPlaceNum);
			coin = coin - 3;
			return;
		}
	}
	
	boolean validationShopNum(int placeNum) { // �긽�젏 援ъ뿭 踰덊샇 �쑀�슚�꽦 寃��궗
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean validationPlaceNum(int placeNum) { // 諛곗튂 援ъ뿭 踰덊샇 �쑀�슚�꽦 寃��궗
		if (4 < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
	
	boolean isNullShopPokemon(int placeNum) { // �긽�젏 援ъ뿭 �룷耳볥が�씠 null�씤吏� �솗�씤
		if (shopPokemon.get(placeNum) == null)
			return true;
		return false;
	}
	
	boolean isNullPlacePokemon(int placeNum) { // 諛곗튂 援ъ뿭 �룷耳볥が�씠 null�씤吏� �솗�씤
		if (placePokemon.get(placeNum) == null)
			return true;
		return false;
	}
	
	boolean shop_buy_expUp(int shopPlaceNum, int myPlaceNum) {
		if (placePokemon.get(myPlaceNum) != null) { // 媛숈� �룷耳볥が�씠硫� 寃쏀뿕移� 利앷�
			if (shopPokemon.get(shopPlaceNum).getPokemonNum() == placePokemon.get(myPlaceNum).getPokemonNum()) {
				if (placePokemon.get(myPlaceNum).getLv() == 3) {
					System.out.println("<< 3�젅踰� �엯�땲�떎!! >>");
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
				System.out.println("<<<寃쏀뿕移� 利앷�!>>>");
				return true;
			}
		}
		return false;
	}
	
	int checkRemainNum() { // �궓�븘�엳�뒗 諛곗튂 援ъ뿭 �룷耳볥が �닔 諛섑솚
		int i = 0;
		for (Pokemon p : placePokemon) {
			if (p != null) {
				i++;
			}
		}
		return i;
	}
	
	
	void buyAbility(int placeNum) { // 援щℓ �떆 �뒫�젰
		int lv = placePokemon.get(placeNum).getLv();
		switch (placePokemon.get(placeNum).getPokemonNum()) {
		case 8: { // 援щℓ �떆 �옖�뜡�븳 �븘援� 1, 2, 3紐� 怨듦꺽�젰 1 利앷�
			int remainNum = checkRemainNum();
			if ((lv == 1 && remainNum >= 1) || (lv == 2 && remainNum == 1) || (lv == 3 && remainNum == 1)) {
				while (true) {
					int i = random.nextInt(5);
					if (placePokemon.get(i) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
						placePowerAdjust(i, 1);
						return;
					}
				}
			} else if ((lv == 2 && remainNum >= 2) || (lv == 3 && remainNum == 2)) { // 援щℓ �떆 �옖�뜡�븳 �븘援� 2紐� 怨듦꺽�젰 1 利앷�
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					while (i == j) {
						i = random.nextInt(5);
						j = random.nextInt(5);
					}
					if (placePokemon.get(i) != null && placePokemon.get(j) != null) {
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
						placePowerAdjust(i, 1);
						placePowerAdjust(j, 1);
						return;
					}
				}
			} else if (remainNum >= 3) { // 援щℓ �떆 �옖�뜡�븳 �븘援� 3紐� 怨듦꺽�젰 1 利앷�
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
						System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
						placePowerAdjust(i, 1);
						placePowerAdjust(j, 1);
						placePowerAdjust(k, 1);
						System.out.println("<<<�뒫�젰 �궗�슜>>>");
						return;
					}
				}
			}
		}
		case 13: { // 援щℓ �떆 �뼇 �쁿�뿉 諛곗튂�맂 �븘援� 怨듦꺽�젰, 泥대젰 2利앷�
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
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
					placeHealthAdjust(placeNum + 1, abilityNum);
					placePowerAdjust(placeNum + 1, abilityNum);
					return;
				}
			}
			if (placeNum == 4) {
				if (placePokemon.get(placeNum - 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
					placeHealthAdjust(placeNum - 1, abilityNum);
					placePowerAdjust(placeNum - 1, abilityNum);
					return;
				}
			}
		}
		case 17: { // 援щℓ �떆 怨듦꺽�젰�씠 媛��옣 �쟻�� �븘援곗뿉寃� 怨듦꺽�젰 2諛� 利앷�
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
			System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
			placePowerAdjust(lowPowerPlaceNum, adjustNum);
			return;
		}
		case 33: { // 援щℓ �떆 �뼇 �쁿�뿉 諛곗튂�맂 �븘援� 怨듦꺽�젰 3 利앷�
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
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
					placeHealthAdjust(placeNum + 1, abilityNum);
					placePowerAdjust(placeNum + 1, abilityNum);
					return;
				}
			}
			if (placeNum == 4) {
				if (placePokemon.get(placeNum - 1) == null) {
					return;
				} else {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
					placeHealthAdjust(placeNum - 1, abilityNum);
					placePowerAdjust(placeNum - 1, abilityNum);
					return;
				}
			}
		}
		case 48: { // 援щℓ �떆 �옖�뜡�븳 �븘援곗뿉寃� 怨듦꺽�젰, 泥대젰 3 利앷�
			int abilityNum = 3;
			if (lv == 2) {
				abilityNum = 4;
			} else if (lv == 3) {
				abilityNum = 5;
			}
			while (true) {
				int i = random.nextInt(5);
				if (placePokemon.get(i) != null) {
					System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
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
	
	void placeHealthAdjust(int placeNum, int adjustNum) { // �븘援� 泥대젰 議곗젙
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth() + adjustNum);
			place_pokemon_panel.get(placeNum).set_heart(place_pokemon_panel.get(placeNum).get_heart() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 泥대젰 議곗젙 " + adjustNum + " >>>");
		}
	}

	void placePowerAdjust(int placeNum, int adjustNum) { // �븘援� 怨듦꺽�젰 議곗젙
		if (placePokemon.get(placeNum) != null) {
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower() + adjustNum);
			place_pokemon_panel.get(placeNum).set_damage(place_pokemon_panel.get(placeNum).get_damage() + adjustNum);
			System.out.println("<<< " + placePokemon.get(placeNum).getName() + " 怨듦꺽�젰 議곗젙 " + adjustNum + " >>>");
		}
	}
	
	
	void place_replace(int place1, int place2) { // 諛곗튂 援ъ뿭 �룷耳볥が �쐞移� 諛붽씀湲�
        if (place1 == place2 || !validationPlaceNum(place1) ||  !validationPlaceNum(place2)) {
            System.out.println("<<<�엯�젰 �삤瑜�>>>");
            return;
        } else if (isNullPlacePokemon(place1) && isNullPlacePokemon(place2)) {
            System.out.println("<<<�몮 �떎 鍮꾩뼱�엳�뒗 諛곗튂 踰덊샇瑜� �엯�젰�븯�뀲�뒿�땲�떎.>>>");
            return;
        } else if (placePokemon.get(place1) != null && placePokemon.get(place2) == null) { // place 1�씠 null�씠 �븘�땲硫�
            placePokemon.set(place2, placePokemon.get(place1));// place2 濡� 蹂듭궗 �썑 plcae1�� null濡�
            placePokemon.set(place1, null);
           place_pokemon_panel.get(place1).setVisible(false);
            
            place_pokemon_panel.get(place2).set_Lv(place_pokemon_panel.get(place1).get_LV());
            place_pokemon_panel.get(place2).set_exp(place_pokemon_panel.get(place1).get_exp());
            place_pokemon_panel.get(place2).set_grade(place_pokemon_panel.get(place1).get_grade());
            place_pokemon_panel.get(place2).set_heart(place_pokemon_panel.get(place1).get_heart());
            place_pokemon_panel.get(place2).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),place_pokemon_panel.get(place1).get_LV()); //�뿬湲� 臾몄젣 李얠븯�떎.
            place_pokemon_panel.get(place2).set_damage(place_pokemon_panel.get(place1).get_damage());
            place_pokemon_panel.get(place2).set_location_num(place2);
            place_pokemon_panel.get(place2).set_ex(place_pokemon_panel.get(place1).get_ex());
            place_pokemon_panel.get(place2).setVisible(true);
            
            System.out.println(place_pokemon_panel.get(place1).get_location_num() + "踰� �쐞移섏쓽 �룷耳볥が�쓣 " + place_pokemon_panel.get(place2).get_location_num() + "濡� �삷源�");
            
            place_area_panel.get(place2).no_check();
            place_pokemon_panel.get(place2).no_check();
            
            select_place_pokemon = null;
            select_place = null;
            
            return;
        } else if (placePokemon.get(place1) == null && placePokemon.get(place2) != null) { // place 2�씠 null�씠 �븘�땲硫�
            placePokemon.set(place1, placePokemon.get(place2));// place1 濡� 蹂듭궗 �썑 plcae2�� null濡�
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
            System.out.println("�젅踰⑥뾽 �꽦怨�");
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
            place_pokemon_panel.get(place1).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),place_pokemon_panel.get(place1).get_LV()); //�뿬湲� 臾몄젣 李얠븯�떎.
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
            place_pokemon_panel.get(place2).set_pokemon_num(placePokemon.get(place2).getPokemonNum(),placePokemon.get(place2).getLv()); //�뿬湲� 臾몄젣 李얠븯�떎.
            place_pokemon_panel.get(place2).set_damage(placePokemon.get(place2).getPower());
            place_pokemon_panel.get(place2).set_location_num(place2);
            place_pokemon_panel.get(place2).set_ex(placePokemon.get(place2).getAbility());
            place_pokemon_panel.get(place2).setVisible(true);
            place_pokemon_panel.get(place1).no_check();
            select_place_pokemon = null;
            
            //
            forReplace.remove();
            System.out.println("<<<�쐞移� 蹂�寃� �꽦怨�>>>");
            return;
        }
    }
	
	void isRankup(int placeNum) { // 寃쏀뿕移섍� 3�씠硫� �젅踰� �뾽, �젅踰� �뾽 �븯硫� 2/2 利앷�
		if (placePokemon.get(placeNum).getExp() == 3 && placePokemon.get(placeNum).getLv() == 1) {
			Pokemon p2 = s.getLV2Pokemon(placePokemon.get(placeNum).getPokemonNum());
			System.out.println(p2.getPokemonNum() + p2.getName());
			placePokemon.get(placeNum).setName(p2.getName());
			placePokemon.get(placeNum).setGrade(p2.getGrade());
			placePokemon.get(placeNum).setLv(2);
			placePokemon.get(placeNum).setExp(1);
			placePokemon.get(placeNum).setHealth(placePokemon.get(placeNum).getHealth()+2);
			placePokemon.get(placeNum).setPower(placePokemon.get(placeNum).getPower()+2);
			System.out.println("<<2�젅踰� �뾽!>>");
			
			//�뙣�꼸 �젅踰� 2濡� �뾽�뜲�씠�듃
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
			System.out.println("<<3�젅踰� �뾽!>>");
			
			//�뙣�꼸 �젅踰� 3�쑝濡� �뾽�뜲�씠�듃
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
	
	void place_sell(int placeNum) { // 諛곗튂 援ъ뿭 �룷耳볥が �뙏湲�
		if (!validationPlaceNum(placeNum)) {
			System.out.println("<<<�엯�젰 �삤瑜�>>>");
			return;
		} else if (isNullPlacePokemon(placeNum)) {
			System.out.println("<<<鍮꾩뼱�엳�뒿�땲�떎.>>>");
			return;
		} else if (placePokemon.get(placeNum).getLv() == 3) {
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin = coin + 3;
			status_panel.set_coin_num(coin);
			System.out.println("<<<�뙋留� �꽦怨�>>>");
			return;
		} else if (placePokemon.get(placeNum).getLv() == 2) {
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin = coin + 2;
			status_panel.set_coin_num(coin);
			System.out.println("<<<�뙋留� �꽦怨�>>>");
			return;
		} else {
			sellAbility(placeNum);
			placePokemon.set(placeNum, null);
			place_pokemon_panel.get(placeNum).setVisible(false);
			select_place_pokemon = null;
			coin++;
			status_panel.set_coin_num(coin);
			System.out.println("<<<�뙋留� �꽦怨�>>>");
			return;
		}
		
	}
	void sellAbility(int placeNum) { // �뙋留� �떆 �뒫�젰
		int lv = placePokemon.get(placeNum).getLv();
		switch (placePokemon.get(placeNum).getPokemonNum()) {
		case 7: { // �뙋留� �떆 �븘援� 泥대젰 1 利앷�
			int adjustNum = lv;
			System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
			for (int i = 1; i < 5; i++) {
				if (placePokemon.get(i) != null && i + 1 != placeNum) {
					placeHealthAdjust(i, adjustNum);
				}
			}
			return;
		}
		case 14: {// �뙋留� �떆 �옖�뜡�븳 �븘援� 1,2,3�뿉寃� 踰꾩꽢 �닾�뿬
			int remainNum = checkRemainNum();
			if ((lv == 1 && remainNum >= 1) || (lv == 2 && remainNum == 1) || (lv == 3 && remainNum == 1)) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
				while (true) {
					int i = random.nextInt(5);
					if (placePokemon.get(i) != null && i + 1 != placeNum) {
						effectNum[i] = 1; // �슚怨� 1�� 踰꾩꽢 �슚怨�
						System.out.println("<<<" + placePokemon.get(i).getName() + " 踰꾩꽢 �슚怨�! >>>");
						return;
					}
				}
			} else if ((lv == 2 && remainNum >= 2) || (lv == 3 && remainNum == 2)) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					if (placePokemon.get(i) != null && placePokemon.get(j) != null && i != j && i + 1 != placeNum) {
						effectNum[i] = 1;
						System.out.println("<<<" + placePokemon.get(i).getName() + " 踰꾩꽢 �슚怨�! >>>");
						effectNum[j] = 1;
						System.out.println("<<<" + placePokemon.get(j).getName() + " 踰꾩꽢 �슚怨�! >>>");
						return;
					}
				}
			} else {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
				while (true) {
					int i = random.nextInt(5);
					int j = random.nextInt(5);
					int k = random.nextInt(5);
					if (placePokemon.get(i) != null && placePokemon.get(j) != null && placePokemon.get(k) != null
							&& i != j && i != k && k != j && i + 1 != placeNum) {
						effectNum[i] = 1;
						System.out.println("<<<" + placePokemon.get(i).getName() + " 踰꾩꽢 �슚怨�! >>>");
						effectNum[j] = 1;
						System.out.println("<<<" + placePokemon.get(j).getName() + " 踰꾩꽢 �슚怨�! >>>");
						effectNum[k] = 1;
						System.out.println("<<<" + placePokemon.get(k).getName() + " 踰꾩꽢 �슚怨�! >>>");
						return;
					}
				}
			}

		}
		case 49: { // �뙋留� �떆 �븵�뿉 �엳�뒗 �븘援곗뿉寃� 怨듦꺽�젰,泥대젰�쓽 50%, 70%, 100%留뚰겮 利앷�
			if (placeNum == 4) {
				return;
			} else if (placePokemon.get(placeNum + 1) != null) {
				System.out.println("<<<" + placePokemon.get(placeNum).getName() + " �뒫�젰 �궗�슜 >>>");
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
	
	boolean isAllNull() { //諛곗튂 援ъ뿭�씠 �쟾遺� null�씤吏� �솗�씤
		for (int i = 0; i < 5; i++) {
			if (placePokemon.get(i) != null) {
				return false;
			}
		}
		return true;
	}
	
	int randomItemNum() { // �븘�씠�뀥 �닔 留뚰겮 �궃�닔
		if (turnNum <= 6) {
			return random.nextInt(turnNum * 4) + 1;
		} else {
			return random.nextInt(24) + 1;
		}
	}
	
	void shop_item_buy(int shopPlaceNum, int myPlaceNum) { // �긽�젏�뿉�꽌 �븘�씠�뀥 援щℓ
		if (!validationShopItemNum(shopPlaceNum) || !validationPlaceNum(myPlaceNum)) {
			System.out.println("�엯�젰 �삤瑜�");
			return;
		} else if (isNullShopItem(shopPlaceNum)) { // �씠誘� 援щℓ�븳 �긽�젏 踰덊샇硫� 援щℓ 遺덇�
			System.out.println("<<<" + (shopPlaceNum + 1) + "踰� �긽�젏�씠 鍮꾩뼱�엳�뒿�땲�떎.>>>");
			return;
		} else if (isNullPlacePokemon(myPlaceNum)) { // 諛곗튂 援ъ뿭�뿉 �룷耳볥が�씠 �뾾�쑝硫� 援щℓ 遺덇�
			System.out.println("<<<" + (myPlaceNum + 1) + "踰덉뿉 �뾾�뒿�땲�떎.>>>");
			return;
		} else {
			int itemNum = item.get(shopPlaceNum).getItemNum();
			int remainNum = checkRemainNum();
			if (itemNum == 8) {
				if (remainNum < 3) {
					select_item = null;
					select_place_pokemon.no_check();
					System.out.println("<<< �븘�씠�뀥�쓣 �벐湲곗뿉�뒗 �룷耳볥が�씠 �쟻�뒿�땲�떎! >>>");
					return;
				}
			}
			if (itemNum == 3 && itemNum == 4 && itemNum == 6 && itemNum == 14) {
				if (remainNum < 2) {
					select_item = null;
					select_place_pokemon.no_check();
					System.out.println("<<< �븘�씠�뀥�쓣 �벐湲곗뿉�뒗 �룷耳볥が�씠 �쟻�뒿�땲�떎! >>>");
					return;
				}
			}
			buyItemAbility(shopPlaceNum, myPlaceNum);
			item.set(shopPlaceNum, null);
			item_panel.get(shopPlaceNum).setVisible(false);
			select_item = null;
			select_place_pokemon.no_check();
			frozenItemNum[shopPlaceNum] = false;
			System.out.println("<<<援щℓ �꽦怨�>>>");
			coin = coin - 3;
			status_panel.set_coin_num(coin);
			return;
		}
	}
	
	boolean validationShopItemNum(int placeNum) { // �긽�젏 援ъ뿭 踰덊샇 �븘�씠�뀥 寃��궗
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
	
	void buyItemAbility(int shopPlaceNum, int placeNum) { // �븘�씠�뀥 援щℓ �떆 �뒫�젰
		switch (item.get(shopPlaceNum).getItemNum()) {
		case 1: { // 怨듦꺽�젰 1 利앷�
			placePowerAdjust(placeNum, 1);
			break;
		}
		case 2: { // 泥대젰 1 利앷�
			placeHealthAdjust(placeNum, 1);
			break;
		}
		case 3: { // �옖�뜡�쑝濡� 2 湲곕Ъ�뿉寃� 怨듦꺽�젰 1 利앷�
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
		case 4: { // �옖�뜡�쑝濡� 2 湲곕Ъ�뿉寃� 泥대젰 1 利앷�
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
		case 5: { // 1/1 利앷�
			placePowerAdjust(placeNum, 1);
			placeHealthAdjust(placeNum, 1);
			break;
		}
		case 6: { // �옖�뜡�쑝濡� 2 湲곕Ъ�뿉寃� 1/1 利앷�
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
		case 7: { // �옖�뜡�쑝濡� 1 湲곕Ъ�뿉寃� 2/2 利앷�
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
		case 8: { // �옖�뜡�쑝濡� 3 湲곕Ъ�뿉寃� 怨듦꺽�젰 1 利앷�
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
		case 9: { // �궗�슜�븷 湲곕Ъ �뼇 �쁿 湲곕Ъ�쓽 怨듦꺽�젰 2�쓣 媛��졇 �샃�땲�떎.
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
		case 10: { // �궗�슜�븳 �룷耳볥が�씠 ��寃곗뿉�꽌 �궡�븘�궓�쑝硫� 寃쏀뿕移� 1 利앷�
			effectNum[placeNum] = 2;
			break;
		}
		case 11: { // 泥섏쓬 留욎� 怨듦꺽 臾댁떆
			effectNum[placeNum] = 1;
			break;
		}
		case 12: { // 肄붿씤 + 1
			this.coin++;
			break;
		}
		case 13: { // 泥대젰 2 利앷�
			placeHealthAdjust(placeNum, 2);
			break;
		}
		case 14: { // �옖�뜡�쑝濡� 2 湲곕Ъ�뿉寃� 2/2 利앷�
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
		case 15: { // �궗�슜 湲곕Ъ怨� �뼇 �쁿�쓽 湲곕Ъ�뿉 1/2 利앷�
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
		case 16: { // �궗�슜 �슚怨쇰뒗 �뾾吏�留� �씠 �븘�씠�뀥�씠 �쟻�슜�맂 湲곕Ъ �뙋留� �떆 �뙋留ㅼ퐫�씤 1 利앷�
			effectNum[placeNum] = 3;
			break;
		}
		case 17: { // 2/2 利앷�
			placePowerAdjust(placeNum, 2);
			placeHealthAdjust(placeNum, 2);
			break;
		}
		case 18: { // �옖�뜡�쑝濡� 1湲곕Ъ�뿉寃� 寃쏀뿕移� 1 利앷�
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
		case 19: { // 50% �솗瑜좊줈 肄붿씤 2 利앷�
			int rNum = random.nextInt(2);
			if (rNum == 1) {
				System.out.println("<<< �꽦怨�! >>>");
				this.coin++;
				this.coin++;
				break;
			} else {
				System.out.println("<<< �떎�뙣! >>> ");
				break;
			}
		}
		case 20: { // 怨듦꺽�젰 3 利앷�
			placePowerAdjust(placeNum, 3);
			break;
		}
		case 21: { // 寃쏀뿕移� 1 利앷�
			placePokemon.get(placeNum).setExp(placePokemon.get(placeNum).getExp() + 1);
			break;
		}
		case 22: { // 3/3 利앷�
			placePowerAdjust(placeNum, 3);
			placeHealthAdjust(placeNum, 3);
			break;
		}
		case 23: { // 肄붿씤 + 2
			this.coin++;
			this.coin++;
			break;
		}
		case 24: { // ��寃곗뿉 �듅由� �떆 肄붿씤 2, �뙣諛� �떆 1 利앷� 000000000000
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
			System.out.println("<<<�엯�젰 �삤瑜�>>>");
			return;
		} else if (isNullShopItem(placeNum)) {
			System.out.println("<<<鍮꾩뼱�엳�뒿�땲�떎.>>>");
			return;
		} else if (frozenItemNum[placeNum] == false) {
			frozenItemNum[placeNum] = true;
			System.out.println("<<<" + (placeNum + 1) + "踰덉뿉 �뼹由ш린 �꽕�젙>>>");
			select_item.get_frozen_panel().setVisible(true);
			return;
		} else {
			frozenItemNum[placeNum] = false;
			System.out.println("<<<" + (placeNum + 1) + "踰덉뿉 �뼹由ш린 痍⑥냼 �꽕�젙>>>");
			select_item.get_frozen_panel().setVisible(false);
			return;
		}
	}
	
	boolean validationShopPokemonNum(int placeNum) { // �긽�젏 援ъ뿭 踰덊샇 �쑀�슚�꽦 寃��궗
		if (shop_place_num() < placeNum || placeNum < 0) {
			return false;
		} else
			return true;
	}
}

