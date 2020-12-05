package packmanpkg;

public class Enemy {
	Astar astar;
	
Enemy(int x, int y) {
		this.x = x;
		this.y = y;
		die=false;
	}
int x, y;
	char enemy = 'E';
	char c=' '; int time=0;
boolean die;
         void move() {
        	 astar=new Astar(this.x,this.y);
        	 astar.astar();
        	Node node=astar.getNode();
        	if(Packman.arr[node.x][node.y]==Packman.packman)
    		{
        		Packman.arr[node.x][node.y]=enemy;
                Packman.arr[x][y]=c;
                x=node.x;
        		y=node.y;
                return;
            	
    		}
    			if(Packman.arr[node.x][node.y]=='E' ||Packman.arr[node.x][node.y]=='B')
    				return;
    		
    			
    		c=Packman.arr[node.x][node.y];
    		
    		Packman.arr[node.x][node.y]=enemy;
    		Packman.arr[x][y]=c;
    		x=node.x;
    		y=node.y;
	}
       boolean isdie(){
        	 if(die==true)
        		 time++;
        	 return die;
         }
}
