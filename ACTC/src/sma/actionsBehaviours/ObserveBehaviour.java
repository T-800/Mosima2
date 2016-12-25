package sma.actionsBehaviours;

import com.jme3.math.Vector3f;
import env.jme.Situation;
import jade.core.behaviours.TickerBehaviour;
import org.jpl7.Query;
import sma.agents.AbstractAgent;
import sma.agents.SmartAgent;

import java.util.Random;


public class ObserveBehaviour  extends TickerBehaviour {
    SmartAgent agent;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ObserveBehaviour(final AbstractAgent myagent) {
        // TODO Auto-generated constructor stub
        super(myagent, 1000);
        agent = ((SmartAgent)this.myAgent);
    }

    @Override
    protected void onTick() {
        /**
         * C'est cette classe ou on va mettre les appel à prolog :-)
         */

        Vector3f currentpos  = agent.getCurrentPosition();
        Vector3f dest = agent.getDestination();

        agent.observe();
        Situation situation = SmartAgent.lastSituation;
        if(dest == null || approximativeEqualsCoordinates(currentpos, dest)){
            if (situation.agents.isEmpty()){
                if (explo()){
                    System.out.println("Exploration");
                    agent.randomMove();

                    agent.randomAction(agent.getEnemy());
                }
                else{
                    System.out.println("Move to max : " + agent.getMaxAlt());
                    agent.addBehaviour(new MoveToBehaviour(agent, dest));
                }
            }
            else {
                agent.addBehaviour(new FollowBehaviour(agent));
            }
        }
        else {
            System.out.println("Dest : move to " + dest + " : " + currentpos);
            agent.moveTo(dest);
        }
    }

    private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
        return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
    }

    private boolean approximativeEquals(float a, float b) {
        return b-5 <= a && a <= b+5;
    }

    public static boolean explor(){
        Random r=new Random();
        int x=r.nextInt(100);
        return (x < SmartAgent.ALPHA);
    }


    public boolean explo(){
        String query = "consult('./ressources/prolog/test/position.pl')";
        System.out.println(query+" ?: "+ Query.hasSolution(query));
        query="exploration(tom,maurice)";
        //System.out.println(query+" ?: "+Query.hasSolution(query));
        return Query.hasSolution(query);
    }
}
