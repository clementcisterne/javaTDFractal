import java.math.* ;

class DessinFractale{
    private final Turtle bob;
    
    private final static int LARGEUR = 1000;
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
		bob.left(180-(180/3));
    	bob.forward(l);
		bob.left(180-(180/3));
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

		double var1 = deuxPuissance(n)*l;
		double var2 = deuxPuissance(n-1)*l;
		double var3 = (deuxPuissance(n-1)-1)*l;


		if(n==0 || n==1){
			var1 = l;
			var2 = l;
			var3 = l;
		}

		double carre1 = auCarre(var1);
		double carre2 = auCarre(var2);
		double carre3 = auCarre(var3);


		double a = carre1-carre2;
		double racine1=Math.sqrt(a);
		double b = racine1+carre3;

		double racine2=Math.sqrt(b);

		System.out.println("\nvar1 = " + var1 +
				"\nvar2 = " + var2 +
				"\nvar3 = " + var3 +
				"\ncarre1 = " + carre1 +
				"\ncarre2 = " + carre2 +
				"\ncarre3 = " + carre3 +
				"\nracine1 = " + racine1 +
				"\nrésultat final : "+Double.toString(racine2)
		);


		return racine2;
	}


	/**
	 * double l : longueur
	 * int n    : ordre
	 *
	public void positionSierpinski(double l, int n){
		if(n==0){
			bob.forward(l);
		}
		if(n==1){
			bob.right(60/n);
			bob.forward(sqrt(auCarre(deuxPuissance(n)*l)-auCarre(l)));
			bob.left(60/n);
			bob.forward(sqrt(auCarre(deuxPuissance(n)*l)-auCarre(l)));
		}
		else{
			positionSierpinski(l,n-1);

			bob.right(60/n);
			bob.forward(sqrt(sqrt(auCarre(deuxPuissance(n)*l)-auCarre(deuxPuissance(n-1)*l))+auCarre((deuxPuissance(n-1)-1)*l)));

			positionSierpinski(l,n-1);

			bob.left(60/n);
			bob.forward(sqrt(sqrt(auCarre(deuxPuissance(n)*l)-auCarre(deuxPuissance(n-1)*l))+auCarre((deuxPuissance(n-1)-1)*l)));

			positionSierpinski(l,n-1);
		}
	}

	 */

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
	//DessinFractale d = new DessinFractale(500);

	//d.positionSierpinski(500,0);

	calculDeFouDeLaLongueur(100,0);
	calculDeFouDeLaLongueur(100,1);
	calculDeFouDeLaLongueur(100,2);
	calculDeFouDeLaLongueur(100,3);

    }
    
}
