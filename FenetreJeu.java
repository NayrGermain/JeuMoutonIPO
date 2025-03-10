import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreJeu extends JPanel implements KeyListener {
    private Terrain terrain;
    private int tailleCase = 24;
    private int hauteur, largeur;
    private JFrame frame;
    private Joueur j;
    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        j = terrain.getJoueur();
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int x = 0; x < terrain.getHauteur();x++)
            for(int y = 0; y < terrain.getLargeur();y++){
                Case c = terrain.getCase(x,y);
                if (c instanceof CaseIntraversable){
                    g.setColor( Color.BLACK);
                    g.fillRect(y*tailleCase,x*tailleCase,tailleCase,tailleCase);
                }else {
                    if (c instanceof Sortie){
                        g.setColor(Color.BLUE);
                        g.fillOval(y*tailleCase,x*tailleCase,tailleCase-5,tailleCase-5);
                    }

                    if(!c.estLibre()){
                        if( ((CaseTraversable)c).getContenu() instanceof Personnage){
                            g.setColor(Color.YELLOW);
                            g.fillOval(y*tailleCase,x*tailleCase,tailleCase-5,tailleCase-5);
                        }
                        else if( ((CaseTraversable)c).getContenu() instanceof Monstre){
                            g.setColor(Color.RED);
                            g.fillOval(y*tailleCase,x*tailleCase,tailleCase-5,tailleCase-5);
                        }
                        else if( ((CaseTraversable)c).getContenu() instanceof Joueur){
                            g.setColor(Color.CYAN);
                            g.fillOval(y*tailleCase,x*tailleCase,tailleCase-5,tailleCase-5);
                        }
                        else if( ((CaseTraversable)c).getContenu() instanceof Obstacle){
                            g.setColor(new Color(0,255,0,((CaseTraversable)c).getContenu().getResistance() *50));
                            g.fillRect(y*tailleCase,x*tailleCase,tailleCase,tailleCase);
                        }
                    }
                }
            }
    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }
    public void ecranFinal2(int n) {
        frame.remove(this);
        JLabel lab = new JLabel("Loser ");
        lab.setFont(new Font("Verdana", 1, 50));
        lab.setSize(this.getSize());
        frame.getContentPane().add(lab);
        frame.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.ouest));
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Case c = terrain.getCaseJoueur();
            j.action(c, terrain.getCaseCibleJoueur(Direction.est));
        }if(e.getKeyCode() == KeyEvent.VK_UP) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.nord));
        }if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.sud));
        }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.exit(1);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.ouest));
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Case c = terrain.getCaseJoueur();
            j.action(c, terrain.getCaseCibleJoueur(Direction.est));
        }if(e.getKeyCode() == KeyEvent.VK_UP) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.nord));
        }if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.sud));
        }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.exit(1);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
     return;}
}
