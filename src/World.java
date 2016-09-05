/* 433-294 Object Oriented Software Development
 * RPG Game Engine
 * Author: <Shreyash Patodia> <spatodia>
 * Student Number : 767336.
 * 
 * This file contains the World class for our game. 
 */

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/** Represents the entire game world.
 * (Designed to be instantiated just once for the whole game).
 */

public class World
{
	/**************** Attributes *********************/
	
	/** The only player in the current version of our game. */
	private Player player = null; 
	
	/** The map for our game. */
	private TiledMap map = null;
	
	/** The camera that follows the player. */
	private Camera cam = null;

	/***************** Methods ***********************/
	
    /** Create a new World object. */
    public World()
    throws SlickException
    {
    	/* Define the objects in the map. */
    	this.player = new Player(RPG.PLAYER_IMG);
    	this.map = new TiledMap(RPG.MAP, RPG.ASSETS); 
    	this.cam = new Camera(player, RPG.screenwidth, RPG.screenheight); 
    }
    
    /** Update the game state for a frame.
     * @param dir_x The player's movement in the x axis (-1, 0 or 1).
     * @param dir_y The player's movement in the y axis (-1, 0 or 1).
     * @param delta Time passed since last frame (milliseconds).
     * @return void.
     */
    public void update(double dir_x, double dir_y, int delta)
    throws SlickException
    {
    	/* Updating our camera and player. */
        player.update_position(this, dir_x, dir_y, delta);
        cam.update();
       
        return; 
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param g The Slick graphics object, used for drawing.
     * @return void.
     */
    public void render(Graphics g)
    throws SlickException
    {
    	/* Finding screen width and height in tiles. */
    	int screen_width = (int)(RPG.screenwidth/map.getTileWidth() + 2);
    	int screen_height = (int)(RPG.screenheight/map.getTileWidth() + 2);
    	
    	/* Finding co-ordinates of tile to start at. */
    	int start_tile_x = (int)(this.cam.getMinX()/map.getTileWidth()); 
    	int start_tile_y = (int)(this.cam.getMinY()/map.getTileHeight()); 
    	
    	/* Finding coordinates of pixels we should offset by. */
    	int start_pixel_x = (int)(this.cam.getMinX()%map.getTileWidth());
    	int start_pixel_y  = (int)(this.cam.getMinY()%map.getTileHeight());
    	
    	/* Rendering map and player. */
    	map.render(-start_pixel_x, -start_pixel_y, start_tile_x, start_tile_y, screen_width, screen_height);
    	player.render(g, this.cam);
        
    }
    /** Checks if the tile corresponding to the given pixels blocks
     * or not. 
     * @param player_x - the current x-coordinate of the player.
     * @param player_y - the current y-coordinate of the player.
     * @return isBlocking - true if the current tile is block 
     * else false.
     */
    public boolean blocks(double player_x, double player_y)
    {
    	/* Finding coordinates of tile which we check blocking for. */
    	int x_tile = (int)(player_x/map.getTileWidth()); 
    	int y_tile = (int)(player_y/map.getTileHeight()); 
    	
    	/* Check and return if the tile is blocking. */
    	int tileID = map.getTileId(x_tile, y_tile, 0); 
    	boolean isBlocking = false;
    	if(map.getTileProperty(tileID, "block", "0").equals("1"))
    	{
    		isBlocking = true; 
    	}
    	return isBlocking; 
    	
    }
}
