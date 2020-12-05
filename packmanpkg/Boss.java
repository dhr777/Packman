package packmanpkg;



class Boss extends Enemy{

	static char enemy='B';

	Boss(int x, int y) {
		super(x, y);
	}
	@Override
	void move() {

			
			 astar=new Astar(this.x,this.y);
	    	 astar.astar();
	    	Node node=astar.getBosstNode();
	    	
				
	    
	    	
	    	
	    	if(Packman.arr[node.x][node.y]==Packman.packman)
			{
	    		Packman.arr[node.x][node.y]=enemy;
	            Packman.arr[x][y]=c;
	            x=node.x;
	    		y=node.y;
	            return;
	        	
			}
				
				
	    	if(Packman.arr[node.x][node.y]=='E')
			{
				Packman.arr[node.x][node.y]=enemy;
				Packman.arr[x][y]=c;
				x=node.x;
				y=node.y;
				return;
			}
	    	
			c=Packman.arr[node.x][node.y];
	  
				
			Packman.arr[node.x][node.y]=enemy;
	           
		
			Packman.arr[x][y]=c;
	              
			x=node.x;
			y=node.y;
			
			

			
	}

	
}