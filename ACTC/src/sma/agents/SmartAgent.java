package sma.agents;

import com.jme3.math.Vector3f;
import env.jme.Environment;
import env.jme.Situation;
import org.jpl7.Query;
import sma.actionsBehaviours.ObserveBehaviour;
import sma.agents.AbstractAgent;
import sma.actionsBehaviours.FollowBehaviour;
<<<<<<< HEAD
import sma.actionsBehaviours.ShootBehaviour;
=======
import sma.structures.PointDInteret;
>>>>>>> origin/master

import java.util.Random;

public class SmartAgent extends AbstractAgent {
    /**
     *
     */
    private static final long serialVersionUID = 7545160765928961044L;

    /**
     * True to create a friend, false otherwise
     */
    public boolean friendorFoe;

<<<<<<< HEAD
    public FollowBehaviour followBehaviour;
    public ShootBehaviour shootBehaviour;
    public Situation situation;
=======
    public ObserveBehaviour observeBehaviour;

    /* J'ai la derniere situation en static pour pouvoir l'avoir dans prolog
     * Mais c'est possible qu'on le retire
    */
    public static Situation lastSituation;
>>>>>>> 6336919b30103b2c2313fd3cf17d0ee6f09c0121

    public static float TAILLE_Y = -1;
    private static double avgAlt = 0;
    private static double avgFov = 0;
    private static double avgMaxDepth = 0;
    private static int  n = 0;
    private static Vector3f maxAlt = new Vector3f();
    private String enemy;

    private static final float ALPHA = 50;


    //ListePoint listePointsInterets;

    protected void setup(){
        super.setup();

        //get the parameters given into the object[]. In the current case, the environment where the agent will evolve
        final Object[] args = getArguments();
        if(args[0]!=null && args[1]!=null){

            this.friendorFoe = ((boolean)args[1]);

            if (friendorFoe) {
                deployAgent((Environment) args[0]);
            } else {
                deployEnemy((Environment) args[0]);
            }

        }else{
            System.err.println("Malfunction during parameter's loading of agent"+ this.getClass().getName());
            System.exit(-1);
        }
        observe();

        TAILLE_Y = 150;
        System.out.println(TAILLE_Y);

<<<<<<< HEAD
        followBehaviour = new FollowBehaviour(this);
        shootBehaviour = new ShootBehaviour(this);
        addBehaviour(followBehaviour);
        addBehaviour(shootBehaviour);
=======
        observeBehaviour = new ObserveBehaviour(this);
        addBehaviour(observeBehaviour);

>>>>>>> 6336919b30103b2c2313fd3cf17d0ee6f09c0121
        System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);

        if (this.getLocalName().equals("Player1")) {
            enemy = "Player2";
        }
        else {
            enemy = "Player1";
        }
    }
    public static boolean observe_prolog(){
        return true;
    }
    public void observe() {
        lastSituation =  this.observeAgents();
        PointDInteret pi = new PointDInteret(lastSituation, this);

        avgAlt += pi.position.getY();
        avgFov += pi.fieldOfView;
        avgMaxDepth += pi.maxDepth;
        n+=1;
        if(maxAlt.getY() < pi.maxAltitude.getY()) {
            maxAlt = pi.maxAltitude;
        }
    }




    public double getAvgAlt() {
        return avgAlt/n;
    }

    public Vector3f getMaxAlt() {
        return new Vector3f(maxAlt.getX() - 64 , maxAlt.getY() - TAILLE_Y, maxAlt.getZ() - 64);
    }



    public static boolean isAltSupAvg(){
        return lastSituation.agentAltitude.getY()  + TAILLE_Y > avgAlt/n;
    }


    public String getEnemy() {
        return enemy;
    }


    /*******************************************
     ******** PROLOG FONCTIONS *****************
     *******************************************/


    public static boolean seeEnnemy(){
        return !lastSituation.agents.isEmpty();
    }

    public static boolean supAvgAlt(){
        return lastSituation.agentAltitude.getY() + TAILLE_Y > avgAlt/n;
    }

    public static boolean supAvgAlt(String s){
        return new Double(s) + TAILLE_Y > avgAlt/n;
    }

    public static boolean supAvgFov(){
        return lastSituation.fieldOfView > avgFov/n;
    }
    public static boolean supAvgFov(String s){
        return new Double(s) > avgFov/n;
    }

    public static boolean supAvgMaxDepth(){
        return lastSituation.maxDepth> avgMaxDepth/n;
    }
    public static boolean supAvgMaxDepth(String s){
        return new Double(s) > avgMaxDepth/n;
    }

    public static boolean exploration(){
        /**
         * renvoi la proba que l'agent parte en exploration plutot que exploitation
         */
        Random r=new Random();
        int x=r.nextInt(100);
        return (x < SmartAgent.ALPHA);
    }



}
