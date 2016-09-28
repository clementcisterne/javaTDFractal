class DessinFractale{
    private final Turtle bob;
    
    private final static int LARGEUR = 800;
    private final static int HAUTEUR = 600;
    //taille de la fenetre graphique

    public DessinFractale(){
	bob  = new Turtle();
	Turtle.setCanvasSize(LARGEUR,HAUTEUR);//à appeler APRES création de la tortue
    }

    public DessinFractale(int v){
	//attention, plus v est grand, plus bob va lentement !
	this();
	bob.speed(v);
    }

    public void close(){ bob.exit(); }

    public void reset(){
		bob.clear();
		bob.up();
		bob.setPosition(0,0);
		bob.setDirection(0);
		bob.down();

    }

    public void carre(double l){
		bob.forward(l);
		bob.left(90);
		bob.forward(l);
		bob.left(90);
		bob.forward(l);
		bob.left(90);
		bob.forward(l);
		bob.left(90);				
    }

    public void triangle(double l){
    	bob.forward(l);
		bob.left(120);
    	bob.forward(l);
		bob.left(120);
		bob.forward(l);
		bob.left(120);    
    }

    /**
     * double l : la longueur des traits
     * int n    : l'ordre de la diagonale
     */
    public void diagonale(double l,int n){
    	if(n != 0){
    		bob.forward(l);
			bob.left(90);
			bob.forward(l);
			bob.right(90);	
    		this.diagonale(l,n-1);
    	}
    }

    /**
     * double l : longueur du flocon
     * int n    : l'ordre du flocon
     */
    public void vonKoch(double l, int n){
    	if(n==1){
			bob.forward(l);
    	}
    	else{
    		this.vonKoch(l/3,n-1);
			bob.left(60);
    		this.vonKoch(l/3,n-1);
			bob.right(120);
    		this.vonKoch(l/3,n-1);
			bob.left(60);
    		this.vonKoch(l/3,n-1);
    	}
    }

    /**
     * double l : longueur de l'arbre
     * int n    : l'ordre du flocon
     */
    public void arbres(double l, int n){
    	if(n==1){
		bob.forward(l);
    		bob.left(180);	
		bob.forward(l);		
    		bob.left(150);	
    	}
    	else{
    		bob.forward(l);    	
    		bob.left(30);

    		this.arbres(l/2,n-1);
    		this.arbres(l/2,n-1);
    		this.arbres(l/2,n-1);
            this.arbres(l/2,n-1);
            this.arbres(l/2,n-1);            
		bob.right(120);	
		bob.forward(l/(13/10));
		bob.left(150);
    	}
    }

    public static void main(String[] args){
	DessinFractale d = new DessinFractale(10);

	d.arbres(2000,4);	
    }
    
}
