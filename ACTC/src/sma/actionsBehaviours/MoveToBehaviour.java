package sma.actionsBehaviours;


import com.jme3.math.Vector3f;
import jade.core.behaviours.OneShotBehaviour;
import sma.agents.AbstractAgent;
import sma.agents.SmartAgent;

public class MoveToBehaviour extends OneShotBehaviour {
    SmartAgent agent;
    Vector3f dest;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MoveToBehaviour(final AbstractAgent myagent, Vector3f dest) {
        // TODO Auto-generated constructor stub
        super(myagent);
        agent = ((SmartAgent)this.myAgent);
        this.dest = dest;
    }


    @Override
    public void action() {
        agent.moveTo(dest);
    }


}