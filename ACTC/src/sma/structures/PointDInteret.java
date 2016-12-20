package sma.structures;


import com.jme3.math.Vector3f;
import env.jme.Situation;
import sma.agents.SmartAgent;

public class PointDInteret {

    public Vector3f position;
    public Vector3f minAltitude;
    public Vector3f maxAltitude;
    public float avgAltitude;
    public int fieldOfView;
    public float maxDepth;



    public PointDInteret(Situation s, SmartAgent a){
        this.position = new Vector3f(a.getCurrentPosition().getX() + 64, a.getCurrentPosition().getY() + SmartAgent.TAILLE_Y, a.getCurrentPosition().getZ() + 64);
        this.minAltitude = s.minAltitude != null ?  new Vector3f(s.minAltitude.getX() + 64, s.minAltitude.getY() + SmartAgent.TAILLE_Y, s.minAltitude.getZ() + 64) : null;
        this.maxAltitude = s.maxAltitude != null ?  new Vector3f(s.maxAltitude.getX() + 64, s.maxAltitude.getY() + SmartAgent.TAILLE_Y, s.maxAltitude.getZ() + 64) : null;
        this.avgAltitude = s.avgAltitude + SmartAgent.TAILLE_Y;
        this.fieldOfView = s.fieldOfView;
        this.maxDepth = s.maxDepth;
    }

    public String toString(){
        return "Position : " + this.position.getY() +
                " MaxAlt : " + this.maxAltitude.getY() +
                " MinAlt : " + this.minAltitude.getY() +
                " AvgAlt : " + this.avgAltitude +
                " Fov : " + this.fieldOfView +
                " Depth : " + this.maxDepth;

    }


}
