import java.util.Timer;
import java.util.TimerTask;

public class Point {
    private static int scoore;
    private static int bonus ;
    private static int time  ;

    private int []  marge  ;
    private static  boolean doubleC; 
    private static  boolean rpg; 
     
    private FrameA frame;

    private Timer timer;

    public Point(FrameA frame) {
        this.frame   = frame;

        Point.bonus  = 1;
        Point.scoore = 0;
        Point.time   = 30000;

        Point.doubleC = false;
        Point.rpg = false;
        
        this.marge  = new int [5];
        this.initMarge();
    }

    private void initMarge()
    {
        this.marge[0] =50;
        this.marge[1] =500;
        this.marge[2] =3000;
        this.marge[3] =6000;
        this.marge[4] =10000;
    }

    public void ajouterPoint() 
    {
        if (Point.doubleC) 
        {
            Point.scoore =   Point.scoore + (Point.bonus * 2);
            this.frame.scoore(  Point.scoore);
        }else  
        {
            if (Point.rpg)
            {
                Point.scoore =   Point.scoore + (Point.bonus * 10);
                this.frame.scoore(  Point.scoore);
            }else
            {
                Point.scoore =   Point.scoore + Point.bonus;
                this.frame.scoore(  Point.scoore);
            }
        }
    }

    public void setBonus(int boost) 
    {
        if(Point.scoore >= this.marge[0])
        {
            Point.bonus = boost;
            this.décrémentation(0);
        }
    }

    public void newClique() {
        if (Point.scoore >= this.marge[1]) 
        {
            this.décrémentation(1);
            this.timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    Point.scoore += Point.bonus;
                    frame.scoore( Point.scoore); 
                }
            };
            // Planifie la tâche pour s'exécuter toutes les 10 secondes
            timer.schedule(task, 0, Point.time); // Démarrage immédiat, répétition toutes les n secondes
        }
    }

    public void reductionTime()
    {
        if (Point.scoore >= this.marge[2]) 
        {
            this.décrémentation(2);
            Point.time -= 300; // Réduction de la période d'exécution
            if (this.timer != null) {
                this.timer.cancel(); // Annulation de la tâche actuelle
                newClique(); // Réinitialisation de la tâche avec la nouvelle période d'exécution
            }
        }
    }

    public void doubleClique(int indice)
    {
        //Pour le 2 clique
        if (Point.scoore >=this.marge[3] && !Point.doubleC && indice == 1)
        {
            this.décrémentation(3); 
            Point.doubleC = true;
        }
        // Pour 10 clique
        if (Point.scoore >=this.marge[4] && !Point.rpg && indice == 2) 
        {
            this.décrémentation(4); 
            Point.rpg = true;
        }
    }

    //pour retiré les point a l'achat de bonus
    public void décrémentation(int indice)
    {
        Point.scoore = Point.scoore  - this.marge[indice];
        this.marge[indice]  = this.marge[indice] * 2  ;
        this.frame.scoore(  Point.scoore);
    }

    public static int     getScoore () { return scoore  ;}
    public static int     getBonus  () { return bonus   ;}
    public static int     getTime   () { return time    ;}
    public        int[]   getMarge  () { return marge   ;}
    public static boolean isDoubleC () { return doubleC ;}
    public static boolean isRpg     () { return rpg     ;}

    public static void setScoore  (int     scoore ) { Point.scoore  = scoore  ;}
    public static void setBonu    (int     bonus  ) { Point.bonus   = bonus   ;}
    public static void setTime    (int     time   ) { Point.time    = time    ;}
    public        void setMarge   (int[]   marge  ) { this .marge   = marge   ;}
    public static void setDoubleC (boolean doubleC) { Point.doubleC = doubleC ;}
    public static void setRpg     (boolean rpg    ) { Point.rpg     = rpg     ;}


}
