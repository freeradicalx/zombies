package freeradicalx.zombies;

import thehippomaster.AnimationAPI.AnimationAPI;
import thehippomaster.AnimationAPI.IAnimatedEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityCrawler1 extends EntityCreature implements IAnimals, IAnimatedEntity {
	
	private int animID;
	private int animTick;
	
	//These two fields keep track of if the entity is "On the move" or resting. Modified by entity's Model class.
	public float lastF;
	public int restCounter;
	
	public EntityCrawler1(World world) {
		super(world);
		//add some typical ai; remember not to use ai priority 2
		//this will be explained why later
		//tasks.addTask(1, new EntityAISwimming(this));
		
		tasks.addTask(1, new AIAnkleGrabAttack(this));
		
		tasks.addTask(2, new EntityAIAttackOnCollide(this, 1F, false));
		tasks.addTask(4, new EntityAIWander(this, 0.8F));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		//tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(16D); //max health
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.1); //move speed
	}
	
	public boolean isAIEnabled() {
		return true;
	}

	/*
	 * Implemented method from IAnimatedEntity.
	 * Set the animID field to the id in the parameter.
	 */
	public void setAnimID(int id) {
		animID = id;
	}
	
	/*
	 * Implemented method from IAnimatedEntity.
	 * Set the animTick field to the tick in the parameter.
	 */
	public void setAnimTick(int tick) {
		animTick = tick;
	}
	
	/*
	 * Implemented method from IAnimatedEntity.
	 * Return the animID.
	 */
	public int getAnimID() {
		return animID;
	}
	
	/*
	 * Implemented method from IAnimatedEntity.
	 * Return the animTick.
	 */
	public int getAnimTick() {
		return animTick;
	}
	
	public void onUpdate() {
		super.onUpdate();
		//increment the animTick if there is an animation playing
		if(animID != 0) animTick++;
		//System.out.println(getAnimID());
	}
	
	public boolean attackEntityAsMob(Entity entity) {
		if(animID == 0) AnimationAPI.sendAnimPacket(this, 3);
		return true;
	}
}