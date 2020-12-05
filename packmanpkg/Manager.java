package packmanpkg;

class notwall{int x,y;}

public class Manager {

	notwall[] n;
	int starnum;
	int x,y,a=49;
	char c;
	static int stun=3;
	static boolean isWall(char x) {
		if (x == '─' || x == '│' || x == '┌' || x == '┘' || x == '└' || x == '┐')
			return true;
		else
			return false;

	}
	void endAction() {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 1; i < Map.HEIGHT - 1; i++)
			for (int j = 1; j < 40; j++) {
				if (!isWall(Packman.arr[i][j]))
					Packman.arr[i][j] = ' ';
				Packman.print();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		System.out.println("최종 스코어 :"+Packman.score);
	}
	void howmanystar(){
		int num=0;
		for(int i=0;i<Map.HEIGHT;i++)
			for(int j=0;j<40;j++)
				if(Packman.arr[i][j]=='.')
					num++;
		starnum=num;
	}
	void setV(){
		howmanystar();
		n=new notwall[starnum];
		int a=0;
		for(int i=0;i<Map.HEIGHT;i++)
			for(int j=0;j<40;j++){
				if(Packman.arr[i][j]=='.'){
					n[a]=new notwall();
					n[a].x=i;
					n[a].y=j;
					a++;

				}
			}
	}
	void random(){

		int a;

		while(true){
			a=(int)((Math.random()*starnum));

			if(Packman.arr[n[a].x][n[a].y]!='P'){
				x=n[a].x;
				y=n[a].y;
				break;
			}
		}

	}
	void gage(){
		//50~ 79
		if(a==79)
			return;
		else
			a++;
		for(int i=50;i<=a;i++){
			if(i==59 || i==69 || i==79)
				Packman.arr[7][i]='*';
			else
				Packman.arr[7][i]='@';

		}
		for(int i=a+1;i<80;i++){
			Packman.arr[7][i]=' ';

		}
	}

	int random_h(int ex,int ey){
		int a;
		a=Astar.getH(ex,ey);
		if(a>=10)
			return 1;
		else return 0;
	}
	void left() {
		if (!Manager.isWall(Packman.arr[Packman.x][Packman.y - 1])) {
			Packman.c=Packman.arr[Packman.x][Packman.y - 1];
			Packman.arr[Packman.x][Packman.y] = ' ';
			if(Packman.x==17&&Packman.y-1==29){
				while(true){
					random();
					if(Packman.arr[x][y]!='E')
						break;
				}
				Packman.x=x; Packman.y=y;
				Packman.arr[Packman.x][Packman.y]=Packman.packman;
				return;
			}
			Packman.arr[Packman.x][(Packman.y--)- 1] = Packman.packman;
		}
	}

	void right() {
		if (!isWall(Packman.arr[Packman.x][Packman.y + 1])) {
			Packman.c=Packman.arr[Packman.x][Packman.y + 1];
			Packman.arr[Packman.x][Packman.y] = ' ';
			Packman.arr[Packman.x][(Packman.y++)+ 1] = Packman.packman;
		}
	}

	void down() {
		if (!isWall(Packman.arr[Packman.x + 1][Packman.y])) {
			Packman.c=Packman.arr[Packman.x+1][Packman.y];
			Packman.arr[Packman.x][Packman.y] = ' ';
			Packman.arr[(Packman.x++)+ 1][Packman.y] = Packman.packman;
		}
	}
	void up() {
		if (!isWall(Packman.arr[Packman.x - 1][Packman.y])) {
			Packman.c=Packman.arr[Packman.x-1][Packman.y];
			Packman.arr[Packman.x][Packman.y] = ' ';
			Packman.arr[(Packman.x--)- 1][Packman.y] = Packman.packman;
		}
	}



	void missile2(int x,int y){
		c=Packman.arr[x][y];
		if(Packman.arr[x][y]=='E'){
			this.x=x;
			this.y=y;
		}else if(Packman.arr[x][y]=='B')
			return;
		Packman.arr[x][y]='@';
		Packman.print();
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Packman.arr[x][y]=' ';
		Packman.print();
	}
	boolean missile(int n,int i){

		if(n==6){
			if(isWall(Packman.arr[Packman.x][Packman.y+i]))
				return true;
			missile2(Packman.x,Packman.y+i);
		}

		else if(n==4){
			if(isWall(Packman.arr[Packman.x][Packman.y-i]))
				return true;
			missile2(Packman.x,Packman.y-i);
		}


		else if(n==8){
			if(isWall(Packman.arr[Packman.x-i][Packman.y]))
				return true;
			missile2(Packman.x-i,Packman.y);
		}


		else if(n==2){
			if(isWall(Packman.arr[Packman.x+i][Packman.y]))
				return true;
			missile2(Packman.x+i,Packman.y);
		}
		return false;
	}


	void enemykill(int n){
		int a=33;
		for(int i=0;i<n;i++)
		{
			Packman.arr[19][a++]='@';

		}
	}
	void enemyreturn(){
		int a=33;
		for(int i=0;i<5;i++)
		{
			Packman.arr[19][a++]=' ';

		}
		this.a=78;
		gage();
	}



	boolean iswin(){
		for(int i=0;i<20;i++){
			for(int j=0;j<40;j++){
				if(Packman.arr[i][j]=='.')
					return false;
			}
		}
		return true;
	}
}
