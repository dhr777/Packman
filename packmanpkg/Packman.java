package packmanpkg;

import java.io.IOException;
import java.util.Scanner;

public class Packman {
	Scanner sc = new Scanner(System.in);
	Enemy enemy[] = new Enemy[4];
	Boss boss;
	Display display;
	Manager manager;
	static int score = 0; static char c;
	static int x = 9, y = 15; // 9 15
	static char arr[][];
	int catchenemy=0;
	static char packman = '♥';

	Packman() throws IOException {
		Map Map = new Map();
		arr = new char[packmanpkg.Map.HEIGHT][packmanpkg.Map.WIDTH];
		arr = Map.ReaderMap();
		manager=new Manager();
		display=new Display();
		manager.setV();
		boss = new Boss(18, 78);//18 78
		arr[boss.x][boss.y] = Boss.enemy;
		arr[x][y] = packman;
		for (int i = 0; i < 4; i++) //4
		{
			while(true){
				manager.random();
				if(manager.random_h(manager.x, manager.y)==1)
					break;

			}
			enemy[i]=new Enemy(manager.x,manager.y);
			arr[enemy[i].x][enemy[i].y] = enemy[i].enemy;
		}

	}


	void getScore(char c) {
		if (c == '.'){
			Packman.score++;
			Packman.c='q';
			manager.gage();
		}
		else if(c=='E')
			isCrash();
		display.setScore(score);
		display.printNumber();
	}
	void movement() {
		System.out.println("▲[w] ▼[s] ◀[a] ▶[d] ITEM[5]");


		String move;
		move = sc.next();
		if(move.equals("5")){
			item();
			print();
			return;
		}
		if (move.equals("w"))
			manager.up();
		if (move.equals("s"))
			manager.down();
		if (move.equals("a"))
			manager.left();
		if (move.equals("d"))
			manager.right();
		getScore(c);

		if(score>=230){//230
			if(manager.iswin())
				display.printwin();





		}

		if(Manager.stun==3){
			for (int i = 0; i < 4; i++){
				if(enemy[i].isdie()){
					if(enemy[i].time==5){
						while(true){
							manager.random();
							if(manager.random_h(manager.x, manager.y)==1)
								if(arr[manager.x][manager.y]=='.' ||arr[manager.x][manager.y]==' ')
									break;
						}
						enemy[i].x=manager.x;
						enemy[i].y=manager.y;
						Packman.arr[enemy[i].x][enemy[i].y]=enemy[i].enemy;
						enemy[i].die=false;
					}
				}
				else {
					enemy[i].move();
					isCrash();
				}
			}

		}else Manager.stun++;
		boss.move();
		isCrash();




	}
	void isCrash() {
		if (x == boss.x && y == boss.y) {
			arr[x][y]='B';
			display.printEnd();
			manager.endAction();
			System.exit(0);
		}
		for (int i = 0; i < 4; i++) //
			if (x == enemy[i].x && y == enemy[i].y) {
				arr[x][y]=enemy[i].enemy;
				display.printEnd();
				manager.endAction();
				System.exit(0);
			}
	}

	static void print() {
		for (char[] a : arr)
			System.out.println(a);
	}


	void item(){
		System.out.println("@[10] :3회스턴     @[20] :미사일    @[30] :보스 초기화");
		int a=sc.nextInt();
		if(a==1)
		{
			if(manager.a<59)
				return;

			if(manager.a>=79)
				manager.a=68;
			else manager.a-=11;



			manager.gage();
			Manager.stun=0;
		}
		if(a==3){
			if(manager.a>=79)
			{
				manager.a=48;
				Packman.arr[boss.x][boss.y]=boss.c;
				boss.x=18;
				boss.y=78;
				Packman.arr[boss.x][boss.y]=Boss.enemy;
				manager.gage();
			}
			return;
		}


		if(a==6 || a==8 || a==4 || a==2){
			if(manager.a<69)
				return;
			if(manager.a>=69)
				if(manager.a>=79)
					manager.a=58;
				else
					manager.a-=21;
			manager.gage();
			for(int i=1;i<6;i++){

				if(manager.missile(a,i))
					break;

				getScore(manager.c);
				ismedie();
			}

			return;
		}
	}

	void ismedie(){
		for(int i=0;i<4;i++){  //
			if(enemy[i].x==manager.x && enemy[i].y==manager.y){
				enemy[i].x=19; enemy[i].y=0;
				enemy[i].die=true; enemy[i].time=0;
				manager.x=1; manager.y=1;
				catchenemy++;
				score+=5;
				display.setScore(score);
				display.printNumber();
				arr[19][30]='E'; arr[19][32]=':';
				manager.enemykill(catchenemy);
				if(catchenemy==5){
					manager.enemyreturn();
					catchenemy=0;
				}
			}

		}
	}





}
