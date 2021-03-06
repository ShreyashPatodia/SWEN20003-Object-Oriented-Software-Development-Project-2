/* SWEN20003 Object Oriented Software Development 
 * RPG Game Engine
 * Author: <Shreyassh Patodia> <spatodia>
 * Student Number : 767336
 * Email: spatodia@student.unimelb.edu.au
 */

import org.newdawn.slick.SlickException;


/**
 * Sword is a collectable that increase attack damage
 */
public class Sword extends Item implements Interactable
{
	/**
     * the boost to the damage the player can make
     */
    private int damageBoost;

    /**
     * Default constructor
     */
    public Sword() 
    throws SlickException
    {
    	super(Constant.SWORD_X, Constant.SWORD_Y, Constant.SWORD_PATH, Constant.SWORD);
    	damageBoost = Constant.SWORD_BOOST;
    }

    
    /**
     * Add item to the player's inventory and affect the player
     * @param other - the Interactable that the item is interacting with
     */
    public void action(Interactable other) 
    {
        if(other.identify() == InteractorTag.Player)
        {
        	
        	((Player)other).getInv().addItem(this);
        	((Player)other).setMaxDamage(((Player)other).getMaxDamage() + damageBoost);
       
        	
        }
    }



}