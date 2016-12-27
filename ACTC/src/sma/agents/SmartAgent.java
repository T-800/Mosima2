package sma.agents;

import com.jme3.math.Vector3f;
import env.jme.Environment;
import env.jme.Situation;
import org.jpl7.Query;
import sma.agents.AbstractAgent;
import sma.actionsBehaviours.FollowBehaviour;
<<<<<<< HEAD
import sma.actionsBehaviours.ShootBehaviour;
=======
import sma.structures.PointDInteret;
>>>>>>> origin/master

public class SmartAgent extends AbstractAgent {
    /**
     *
     */
    private static final long serialVersionUID = 7545160765928961044L;

    /**
     * True to create a friend, false otherwise
     */
    public boolean friendorFoe;

    public FollowBehaviour followBehaviour;
    public ShootBehaviour shootBehaviour;
    public Situation situation;

    public static float TAILLE_Y = -1;
    private double avgAlt = 0;
    private int  n = 0;
    private Vector3f maxAlt = new Vector3f();

    public static final float ALPHA = 50;


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

        followBehaviour = new FollowBehaviour(this);
        shootBehaviour = new ShootBehaviour(this);
        addBehaviour(followBehaviour);
        addBehaviour(shootBehaviour);
        System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);

    }

    public void observe() {
        this.situation =  this.observeAgents();
        PointDInteret pi = new PointDInteret(this.situation, this);

        avgAlt += pi.position.getY();
        n+=1;
        //isAltSupAvg();
        if(maxAlt.getY() < pi.maxAltitude.getY()) {
            maxAlt = pi.maxAltitude;
        }
        if (! this.situation.agents.isEmpty()){
            //System.out.println("J'ai vue un agent " + this.situation.agents);
        }
    }

    public double getAvgAlt() {
        return avgAlt/n;
    }

    public Vector3f getMaxAlt() {
        return new Vector3f(maxAlt.getX() - 64 , maxAlt.getY() - TAILLE_Y, maxAlt.getZ() - 64);
    }

    public boolean isAltSupAvg(){
        String query = "consult('./ressources/prolog/test/position.pl')";
        //String query = "consult('./ressources/prolog/test/fishing.pl')";
        System.out.println(query+" ?: "+ Query.hasSolution(query));
        query="interessant(X, Y)";
        //query="caught(tom,maurice)";
        System.out.println(query+" ?: "+Query.hasSolution(query));
        return Query.hasSolution(query);
    }

    public static boolean isAltSupAvg(String s , String avg){
        System.out.println(s + " : " + avg);
        return true;
    }

}
