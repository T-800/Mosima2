<<<<<<< HEAD
package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.Situation;
import jade.core.behaviours.TickerBehaviour;
import sma.AbstractAgent;
import sma.agents.SmartAgent;

//J'ai laisse tickbehaviour pour tester a chaque fois si on arrive a voir un ennemi si oui et isVisible on peut tirer
public class ShootBehaviour extends TickerBehaviour {
	  SmartAgent agent;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShootBehaviour(final AbstractAgent myagent) {
		// TODO Auto-generated constructor stub
		super(myagent, 1);
		agent = ((SmartAgent)this.myAgent);
	}

	@Override
	protected void onTick() {
		
	//	int i=0;
	//	System.out.println("Here");
		// TODO Auto-generated method stub
		//Vector3f currentpos  = ((AbstractAgent)this.myAgent).getCurrentPosition();
		//Situation        st  = ((AbstractAgent)this.myAgent).observeAgents();
		//String enemy
		//if (st.agents.size()> 0){		
			//for (i=0; i< st.agents.size();i++){
			//((AbstractAgent)this.myAgent).shoot(st.agents.get(i).toString());
			//}
		
	    Vector3f currentpos  = agent.getCurrentPosition();
        //Vector3f dest = agent.getDestination();

        agent.observe();
        Situation situation = agent.situation;
        String enemy;
        Situation        st  = ((AbstractAgent)this.myAgent).observeAgents();
        
            	System.out.println("Personne en vue");
                ((AbstractAgent)this.myAgent).randomMove();
               
                if (this.myAgent.getLocalName().equals("Player1")) {
                    enemy = "Player2";
                }
                else {
                    enemy = "Player1";
                }
                ((AbstractAgent)this.myAgent).randomAction(enemy);
        	
        
         
        	if (st.agents.size()> 0){	
        		System.out.println("je vois un ennemi");
			for (int i=0; i< st.agents.size();i++){				 
				System.out.println("je suis" + this.myAgent.getLocalName() + "jevois"+ enemy + "jetire");
			((AbstractAgent)this.myAgent).shoot(enemy);
			}
        	}
        }
        	

		
		
	
	 private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
	        return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
	    }
	
	
private boolean approximativeEquals(float a, float b) {
    return b-2.5 <= a && a <= b+2.5;
}
}


=======
package sma.actionsBehaviours;

import com.jme3.math.Vector3f;

import env.jme.Situation;
import jade.core.behaviours.TickerBehaviour;
import sma.agents.AbstractAgent;
import sma.agents.SmartAgent;

//J'ai laisse tickbehaviour pour tester a chaque fois si on arrive a voir un ennemi si oui et isVisible on peut tirer
public class ShootBehaviour extends TickerBehaviour {
	  SmartAgent agent;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ShootBehaviour(final AbstractAgent myagent) {
		// TODO Auto-generated constructor stub
		super(myagent, 1);
		agent = ((SmartAgent)this.myAgent);
	}

	@Override
	protected void onTick() {
		
	//	int i=0;
	//	System.out.println("Here");
		// TODO Auto-generated method stub
		//Vector3f currentpos  = ((AbstractAgent)this.myAgent).getCurrentPosition();
		//Situation        st  = ((AbstractAgent)this.myAgent).observeAgents();
		//String enemy
		//if (st.agents.size()> 0){		
			//for (i=0; i< st.agents.size();i++){
			//((AbstractAgent)this.myAgent).shoot(st.agents.get(i).toString());
			//}
		
	    Vector3f currentpos  = agent.getCurrentPosition();
        //Vector3f dest = agent.getDestination();

        agent.observe();
        Situation situation = SmartAgent.lastSituation;
        String enemy;
        Situation st  = ((AbstractAgent)this.myAgent).observeAgents();
        
            	System.out.println("Personne en vue");
                ((AbstractAgent)this.myAgent).randomMove();
               
                if (this.myAgent.getLocalName().equals("Player1")) {
                    enemy = "Player2";
                }
                else {
                    enemy = "Player1";
                }
                ((AbstractAgent)this.myAgent).randomAction(enemy);
        	
        
         
        	if (st.agents.size()> 0){	
        		System.out.println("je vois un ennemi");
			for (int i=0; i< st.agents.size();i++){				 
				System.out.println("je suis" + this.myAgent.getLocalName() + "jevois"+ enemy + "jetire");
			((AbstractAgent)this.myAgent).shoot(enemy);
			}
        	}
        }
        	

		
		
	
	 private boolean approximativeEqualsCoordinates(Vector3f a, Vector3f b) {
	        return approximativeEquals(a.x, b.x) && approximativeEquals(a.z, b.z);
	    }
	
	
private boolean approximativeEquals(float a, float b) {
    return b-2.5 <= a && a <= b+2.5;
}
}


>>>>>>> 6336919b30103b2c2313fd3cf17d0ee6f09c0121
