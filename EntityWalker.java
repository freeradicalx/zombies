package freeradicalx.zombies;

import thehippomaster.AnimationAPI.IAnimatedEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityWalker extends EntityModZombie {
	
	public static String zombieType = "walker";
	public int textureNumber = 5000;
	
	int walkerType = (int) (Math.floor(Math.random() * 2));
	
	public EntityWalker(World world) {
		super(world);
		
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new AIGrabAttack(this));
		tasks.addTask(3, new EntityAIAttackOnCollide(this, 1F, false));
		tasks.addTask(4, new EntityAIWander(this, 0.8F));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setAttribute(20D - (8D * Math.random())); //max health
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setAttribute(0.25 - (.1 * Math.random())); //move speed
	}


	
}
