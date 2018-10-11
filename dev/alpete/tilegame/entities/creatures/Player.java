package dev.alpete.tilegame.entities.creatures;

//import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//import dev.alpete.tilegame.Game;
import dev.alpete.tilegame.Handler;
import dev.alpete.tilegame.entities.Entity;
import dev.alpete.tilegame.gfx.Animation;
import dev.alpete.tilegame.gfx.Assets;
import dev.alpete.tilegame.inventory.Inventory;

public class Player extends Creature {
	
	//Animation
	private Animation animRight, animLeft, animUp, animDown, lastAnim;
	private Animation animAtkRight, animAtkDown, animAtkLeft, animAtkUp;
	private int direction = 1;
	
	//Attack Timer
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;
	
	//Inventory
	private Inventory inventory;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		
		//Establishing player's collision box. DEFAULT IS FULL TILE.
		bounds.x = 24;
		bounds.y = 20;
		bounds.width = 12;
		bounds.height = 39;
		
		//Animation
		animRight = new Animation(250, Assets.plRight);
		animLeft = new Animation(250, Assets.plLeft);
		animUp = new Animation(250, Assets.plUp);
		animDown = new Animation(250, Assets.plDown);
		lastAnim = animRight;
		
		int attackAnimSpeed = (int)(attackCooldown / 3);
		animAtkRight = new Animation(attackAnimSpeed, Assets.plAtkRight);
		animAtkDown = new Animation(attackAnimSpeed, Assets.plAtkDown);
		animAtkLeft = new Animation(attackAnimSpeed, Assets.plAtkLeft);
		animAtkUp = new Animation(attackAnimSpeed, Assets.plAtkUp);
		
		//Inventory
		inventory = new Inventory(handler);
		
	}

	public void tick() {
		//Animation
		animRight.tick();
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		
		animAtkRight.tick();
		animAtkDown.tick();
		animAtkLeft.tick();
		animAtkUp.tick();
		

		
		//Movement		
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkAttacks();
		
		//Inventory
		inventory.tick();

	}
	
	private void checkAttacks() {
		//Timer
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown)
			return;
		
		//Check if inventory screen is active
		if(inventory.isActive())
			return;
		
		//Collision Bounds
		Rectangle cb = getCollisionBounds(0, 0);
		
		//Attack area
		Rectangle ar = new Rectangle();
		int arSize = 20;
		ar.width = arSize;
		ar.height = arSize;
		
		//ATTACK COMMAND
		if(handler.getKeyManager().space) {
			//Check Direction.
			if(direction == 1) { //Right
				ar.x = cb.x + cb.width;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
				
			} else if(direction == 2) { //Down
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y + arSize;
				
			} else if(direction == 3) { //Left
				ar.x = cb.x - arSize;
				ar.y = cb.y + cb.height / 2 - arSize / 2;
				
			} else if(direction == 4){ //Up
				ar.x = cb.x + cb.width / 2 - arSize / 2;
				ar.y = cb.y - arSize;
			} else {
				return;
			}
			
			attackTimer = 0;
			
			for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
				
				if(e.equals(this)) {
					continue;
				}
				
				if(e.getCollisionBounds(0, 0).intersects(ar)) {
					e.hurt(1);
					return;
				}
			}
		}
		
	}
	
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
		//Check if inventory screen is active
		if(inventory.isActive())
			return;
		
		if(handler.getKeyManager().up)
			yMove = -speed;
		if(handler.getKeyManager().down)
			yMove = speed;
		if(handler.getKeyManager().left)
			xMove = -speed;
		if(handler.getKeyManager().right)
			xMove = speed;
	}

	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int)(x - handler.getGameCamera().getxOffset()), 
				(int)(y - handler.getGameCamera().getyOffset()), width, height, null);
		
		//Draw Collision Box
		//g.setColor(Color.red);
		//g.fillRect((int)(bounds.x + x - handler.getGameCamera().getxOffset()),
		//		(int)(bounds.y + y - handler.getGameCamera().getyOffset()),
		//		bounds.width, bounds.height);
	}
	
	public void postRender(Graphics g) {
		inventory.render(g);
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(!handler.getKeyManager().space) {
			if(xMove < 0) {
				direction = 3;
				lastAnim = animLeft;
				return animLeft.getCurrentFrame();
			
			} else if(xMove > 0) {
				direction = 1;
				lastAnim = animRight;
				return animRight.getCurrentFrame();
			
			} else if(yMove < 0) {
				direction = 4;
				lastAnim = animUp;
				return animUp.getCurrentFrame();
			
			} else if(yMove > 0) {
				direction = 2;
				lastAnim = animDown;
				return animDown.getCurrentFrame();
			}
			return lastAnim.getStillFrame();
		} else if(handler.getKeyManager().space) {
			if(direction == 1) {
				return animAtkRight.getCurrentFrame();
			}
			if(direction == 2) {
				return animAtkDown.getCurrentFrame();
			}
			if(direction == 3) {
				return animAtkLeft.getCurrentFrame();
			}
			if(direction == 4) {
				return animAtkUp.getCurrentFrame();
			}
		}
		return null;
	}

	@Override
	public void die() {
		System.out.println("You died");
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}


}
