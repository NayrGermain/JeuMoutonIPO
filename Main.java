public class Main {

    Terrain terrain;
    static int sortis = 0;
    public static boolean fini=false;
    public static void increSortis(){
        sortis++;
    }
    /* Initialisation d'un jeu avec le terrain initial décrit dans
       le fichier [f] donné en paramètre */
    public Jeu(String f) {
        this.terrain = new Terrain(f);
    }

    public void tour(){
        terrain.startFullEntities();
        if(terrain.partiFini())
            fini = true;
        for(int i = 0; i < terrain.getSize();i++) {
            Case c = terrain.getCaseCouranteMob(i);
            if( !(((CaseTraversable) c).getContenu() instanceof Joueur) )
                ((EntiteMobile) ((CaseTraversable) c).getContenu()).action(c, terrain.getCaseCibleMob(i));
            else {
                ((Joueur) ((CaseTraversable) c).getContenu()).mortDuJoueur();
            }

        }
        //terrain.print();
        //System.out.println("-----------------------------------");

    }

    public boolean partieFinie(){return fini;}
    public static void main(String[] args) {
        Main j = new Main("laby2.txt");
        //j.terrain.print();
        while(!fini){
            j.tour();
        }

        System.out.println("Nombre de Personnages sortis : " + sortis); 
    }


}
