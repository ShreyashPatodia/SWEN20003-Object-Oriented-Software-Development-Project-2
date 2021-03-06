/* SWEN20003 Object Oriented Software Development  
 * RPG Game Engine
 * Author: <Shreyassh Patodia> <spatodia>
 * Student Number : 767336
 * Email: spatodia@student.unimelb.edu.au
 */


import org.newdawn.slick.SlickException;

/** Class for Amulets */
public class Amulet extends Item implements Interactable
{
	/**
     * The HP boost to the player
     */
    private int hpBoost;

    /**
     * Default constructor
     */
    public Amulet() 
    throws SlickException
    {
    	super(Constant.AMULET_X, Constant.AMULET_Y, Constant.AMULET_PATH, Constant.AMULET);
    	hpBoost = Constant.AMULET_BOOST;
    }

    
    /**
     * Add item to the player's inventory
     * @param other - the Interactable that the item is interacting with
     */
    public void action(Interactable other)
 
    {
        if(other.identify() == InteractorTag.Player)
        {
        	
        	((Player)other).getInv().addItem(this);
        	((Player)other).setMaxHP(((Player)other).getMaxHP() + hpBoost);
        	((Player)other).setHP(((Player)other).getHP() + hpBoost);
        	
        }
    }


}