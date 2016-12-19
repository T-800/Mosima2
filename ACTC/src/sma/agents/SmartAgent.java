package sma.agents;

import env.jme.Environment;
import env.jme.Situation;
import sma.AbstractAgent;
import sma.actionsBehaviours.FollowBehaviour;
import sma.actionsBehaviours.ShootBehaviour;

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

        //this.listePointsInterets = new ListePoint();

        followBehaviour = new FollowBehaviour(this);
        shootBehaviour = new ShootBehaviour(this);
        addBehaviour(followBehaviour);
        addBehaviour(shootBehaviour);
        System.out.println("the player "+this.getLocalName()+ " is started. Tag (0==enemy): " + friendorFoe);

    }

    public void observe() {
        this.situation =  this.observeAgents();
        //PointInteret pi = new PointInteret(this.situation, this.getCurrentPosition());
        //listePointsInterets.addPoint(pi);
        //System.out.println(pi);
        //System.out.println(listePointsInterets);
        //addMaxAlt(this.situation.maxAltitude);
        if (! this.situation.agents.isEmpty()){
            //System.out.println("J'ai vue un agent " + this.situation.agents);
        }
    }


}
