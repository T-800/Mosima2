package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.Situation;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import org.jpl7.Query;
import sma.agents.AbstractAgent;
import sma.agents.SmartAgent;

import java.util.Random;

public class FollowBehaviour extends OneShotBehaviour {
    SmartAgent agent;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FollowBehaviour(final AbstractAgent myagent) {
        // TODO Auto-generated constructor stub
        super(myagent);
        agent = ((SmartAgent)this.myAgent);
    }


    @Override
    public void action() {
        Vector3f dest;
        Situation situation = SmartAgent.lastSituation;
        dest = situation.agents.get(0).getFirst();
        System.out.println("J'ai follow une cible : " + dest);
        agent.moveTo(dest);
    }


}
