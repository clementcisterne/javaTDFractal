import java.math.* ;

class DessinFractale{
    private final Turtle bob;
    
    private final static int LARGEUR = 800;
    private final static int HAUTEUR = 800;
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

	public void positionSierpinski(){

		bob.setDirection(0);
		bob.right(120);
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
		positionTriangle();

    	bob.forward(l);
		bob.left(120);
    	bob.forward(l);
		bob.left(120);
		bob.forward(l);
		bob.left(120);

		positionSierpinski();
    }

	public void positionTriangle(){
		bob.setDirection(0);
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
          	
		bob.right(120);	
		bob.forward(l/(13/10));
		bob.left(150);
    	}
    }

	public static double auCarre(double n){
		return n*n;
	}

	public static int deuxPuissance(int n){
		int result = 1;
		if(n>0){
			for( int i = 0 ; i < n ; i++ ){
				result = result*2;
			}
		}
		if(n<=0){
			return 1;
		}
		return result;
	}

	public static double calculDeFouDeLaLongueur(double l, int n){
		double var1, var2, var3;
		if(n==0){
			var1 = l;
			var2 = l;
			var3 = l;
		}
		if(n==1){
			var1 = 2*l;
			var2 = l;
			var3 = l;
		}else{
			var1 = deuxPuissance(n)*l;
			var2 = deuxPuissance(n-1)*l;
			var3 = (deuxPuissance(n-1)-1)*l;
		}

		double carre1 = auCarre(var1);
		double carre2 = auCarre(var2);
		double carre3 = auCarre(var3);

		double a = carre1-carre2;
		double racine1=(float)Math.sqrt(a);
		double b = auCarre(racine1)+carre3;


		double racine2=(float)Math.sqrt(b);

		if(n==1){ racine2 = racine1; }

		System.out.println("\nn = "+n+
				"\nvar1 = " + var1 +
				"\nvar2 = " + var2 +
				"\nvar3 = " + var3 +
				"\ncarre1 = " + carre1 +
				"\ncarre2 = " + carre2 +
				"\ncarre3 = " + carre3 +
				"\nracine1 = " + racine1 +
				"\nresultat final : "+Double.toString(racine2)
		);
		return (float) racine2;
	}

	public static float calculDeFouDeLangle(int n){
		float angle=0;
		for (int i=0; i<n;i++){
			angle=angle+(float)60/deuxPuissance(i+1);
			System.out.println("a"+i+" = "+angle);
		}

		return angle;
	}

	public static float calculDeFouDeLangle2(int n){

		if(n == 1 || n == 0){return 0;}
		return (float)90+((float)30-((float)60/deuxPuissance(n-1)));
	}

	/**
	 * double l : longueur
	 * int n    : ordre
	 */
	public void positionSierpinski(double l, int n){

		if(n==1){


			bob.left(60);
			bob.forward(calculDeFouDeLaLongueur(l,n));


			bob.right(60*2);
			bob.forward(calculDeFouDeLaLongueur(l,n));

			positionSierpinski();
		}
		else{
			positionSierpinski(l,n-1);					// cas de base
			bob.left(calculDeFouDeLangle2(n));

			bob.forward(calculDeFouDeLaLongueur(l,n));  // +1
			//bob.right(calculDeFouDeLangle(n));
			positionSierpinski();

			positionSierpinski(l,n-1);					// cas de base
			bob.right((float)120-((float)60/deuxPuissance(n-1)));

			bob.forward(calculDeFouDeLaLongueur(l,n));  // +1
			//bob.right(180-(60/deuxPuissance(n-1)));
			positionSierpinski();
			//bob.left(120-calculDeFouDeLangle2(n));

			positionSierpinski(l,n-1);					// cas de base
		}
	}



	/**
	 * double l : longueur de l'arbre
	 * int n    : l'ordre du triangle
	 * */
    public void Sierpinski(double l, int n){
		if (n==0) {
			triangle(l);
		}
		else {
			while(n<0)

			Sierpinski(l/2,n-1);

			/**repositionnement*/
			bob.right(60);
			bob.left(60);
			Sierpinski(l/2,n-1);
			bob.left(120);
			bob.forward(2l);
			bob.left(60);
			Sierpinski(l/2,n-1);


		}
	}



/*____________________________________________________________________________ 
*/

    public static void main(String[] args){
	DessinFractale d = new DessinFractale(1);
	d.positionSierpinski();
	d.positionSierpinski(100,4);

		calculDeFouDeLaLongueur(100,0);
		calculDeFouDeLaLongueur(100,1);
		calculDeFouDeLaLongueur(100,2);
		calculDeFouDeLaLongueur(100,3);
		calculDeFouDeLaLongueur(100,4);
		calculDeFouDeLaLongueur(100,5);

    }
    
}
