package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.PlayerControl;
import env.jme.Situation;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import org.lwjgl.Sys;
import sma.AbstractAgent;
import sma.agents.SmartAgent;

import java.util.Random;

import static sma.actionsBehaviours.LegalActions.LegalAction.*;

public class FollowBehaviour extends TickerBehaviour {
    SmartAgent agent;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FollowBehaviour(final AbstractAgent myagent) {
        // TODO Auto-generated constructor stub
        super(myagent, 1000);
        agent = ((SmartAgent)this.myAgent);
    }

    @Override
    protected void onTick() {
        // TODO Auto-generated method stub
        Vector3f currentpos  = agent.getCurrentPosition();
        Vector3f dest = agent.getDestination();

        agent.observe();
        Situation situation = agent.situation;
        //System.out.println("Avg : " + agent.getAvgAlt());
        //System.out.println("Max : " + agent.getMaxAlt());
        if(dest == null || approximativeEqualsCoordinates(currentpos, dest)){
            if (situation.agents.isEmpty()){
                if (exploration()){
                    System.out.println("Exploration");
                    ((AbstractAgent)this.myAgent).randomMove();
                    String enemy;
                    if (this.myAgent.getLocalName().equals("Player1")) {
                        enemy = "Player2";
                    }
                    else {
                        enemy = "Player1";
                    }
                    ((AbstractAgent)this.myAgent).randomAction(enemy);
                }
                else{
                    System.out.println("Move to max : " + agent.getMaxAlt());
                    dest = agent.getMaxAlt();
                    agent.moveTo(dest);
                }
            }
            else {

                dest = situation.agents.get(0).getFirst();
                System.out.println("J'ai une cible : " + dest);
                agent.moveTo(dest);
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

    //@Override
    //public void action() {
        //agent.observe();
        //Situation situation = agent.situation;
        //System.out.println(situation.minAltitude.getY());
        //agent.cardinalMove(MOVE_NORTH);
        //agent.moveTo(situation.minAltitude);
        //onTick();
    //}

    public static boolean exploration(){
        Random r=new Random();
        //theoretically, the function should check in the environment that the conditions for the fish to be hooked are met.
        int x=r.nextInt(100);
        //System.out.println("Hooked function triggered; succesRate = "+succesRate+"; v= "+x);
        return (x < 50);
    }
}
