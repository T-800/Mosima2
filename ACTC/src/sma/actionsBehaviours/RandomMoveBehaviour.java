package sma.actionsBehaviours;

import com.jme3.math.Vector3f;
import jade.core.behaviours.OneShotBehaviour;
import org.lwjgl.Sys;
import sma.agents.AbstractAgent;
import sma.agents.SmartAgent;


public class RandomMoveBehaviour  extends OneShotBehaviour {
    SmartAgent agent;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RandomMoveBehaviour(final AbstractAgent myagent) {
        // TODO Auto-generated constructor stub
        super(myagent);
        agent = ((SmartAgent)this.myAgent);
    }


    @Override
    public void action() {
        System.out.println("Random move");
        agent.randomMove();
    }


}