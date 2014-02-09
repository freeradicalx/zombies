package freeradicalx.zombies;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import thehippomaster.AnimationAPI.AIAnimation;
import thehippomaster.AnimationAPI.IAnimatedEntity;

public class AIAnkleGrabAttack extends AIAnimation {
	
	private IAnimatedEntity entityCrawler1;
	private EntityLivingBase attackTarget;
	
	public AIAnkleGrabAttack(IAnimatedEntity test) {
		super(test);
		entityCrawler1 = test;
		attackTarget = null;
	}
	
	public int getAnimID() {
		return 3;
	}
	
	public boolean isAutomatic() {
		return true;
	}
	
	public int getDuration() {
		return 35;
	}
	
	public void startExecuting() {
		//remember to super!
		super.startExecuting();
		attackTarget = ((EntityCreature)entityCrawler1).getAttackTarget();
	}
	
	public void updateTask() {
		
		((EntityCrawler1)entityCrawler1).restCounter = 0;
		if(entityCrawler1.getAnimTick() == 15 && attackTarget != null)
			attackTarget.attackEntityFrom(DamageSource.causeMobDamage(((EntityCreature)entityCrawler1)), 1);
			attackTarget.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 3));
			attackTarget.motionX = 0;
			attackTarget.motionY = 0;
			attackTarget.motionZ = 0;
			
	}
}