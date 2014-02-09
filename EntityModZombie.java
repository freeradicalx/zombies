package freeradicalx.zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.world.World;
import thehippomaster.AnimationAPI.AnimationAPI;
import thehippomaster.AnimationAPI.IAnimatedEntity;

public class EntityModZombie extends EntityCreature implements IAnimals, IAnimatedEntity {

	//These two fields keep track of if the entity is "On the move" or resting. Modified by entity's Model class.
	public float lastF;
	public int restCounter;
	
	//Required fields to drive Animation API animations
	protected int animID;
	protected int animTick;
	
	//The type of zombie ie walker, crawler, runner, etc
	public static String zombieType;
	
	public int walkerType;
	public int crawlerType;
	
	public int textureNumber = 5000; //5000 indicates texture not yet set
	
	public EntityModZombie(World par1World) {
		super(par1World);
	}
	
	public boolean isAIEnabled() {
		return true;
	}

	//Implemented method from IAnimatedEntity.
	//Set the animID field to the id in the parameter.
	public void setAnimID(int id) {
		animID = id;
	}
	
	//Implemented method from IAnimatedEntity.
	//Set the animTick field to the tick in the parameter.
	public void setAnimTick(int tick) {
		animTick = tick;
	}
	
	//Implemented method from IAnimatedEntity.
	//Return the animID.
	public int getAnimID() {
		return animID;
	}
	
	//Implemented method from IAnimatedEntity.
	//Return the animTick.
	public int getAnimTick() {
		return animTick;
	}
	
	public void onUpdate() {
		super.onUpdate();
		//increment the animTick if there is an animation playing
		if(animID != 0) animTick++;
	}
	
	public boolean attackEntityAsMob(Entity entity) {
		if(animID == 0) AnimationAPI.sendAnimPacket(this, 3);
		return true;
	}

}
