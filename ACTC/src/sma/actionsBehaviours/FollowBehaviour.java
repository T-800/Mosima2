package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.PlayerControl;
import env.jme.Situation;
import jade.core.behaviours.TickerBehaviour;
import org.lwjgl.Sys;
import sma.AbstractAgent;
import sma.agents.SmartAgent;

public class FollowBehaviour extends TickerBehaviour {
    SmartAgent agent;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FollowBehaviour(final AbstractAgent myagent) {
        // TODO Auto-generated constructor stub
        super(myagent, 200);
        agent = ((SmartAgent)this.myAgent);
    }

    @Override
    protected void onTick() {
        // TODO Auto-generated method stub
        Vector3f currentpos  = agent.getCurrentPosition();
        Vector3f dest = agent.getDestination();
        Situation situation = agent.observeAgents();

        if (situation.agents != null){
            if (dest==null || approximativeEqualsCoordinates(currentpos, dest)) {
                ((AbstractAgent)this.myAgent).randomMove();
                String enemy = "";
                if (this.myAgent.getLocalName().equals("Player1")) {
                    enemy = "Player2";
                }
                else {
                    enemy = "Player1";
                }
                ((AbstractAgent)this.myAgent).randomAction(enemy);
            }
        }
        else{

            dest = situation.agents.get(0).getFirst();
            agent.moveTo(dest);
            System.out.println("MOVE TO " + dest);
        }



    }

    private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
        return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
    }

    private boolean approximativeEquals(float a, float b) {
        return b-2.5 <= a && a <= b+2.5;
    }

}
