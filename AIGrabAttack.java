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

public class AIGrabAttack extends AIAnimation {
	
	private IAnimatedEntity entityTestMob2;
	private EntityLivingBase attackTarget;
	
	public AIGrabAttack(IAnimatedEntity test) {
		super(test);
		entityTestMob2 = test;
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
		attackTarget = ((EntityCreature)entityTestMob2).getAttackTarget();
	}
	
	public void updateTask() {
		//if(entityTestMob.getAnimTick() < 14)
		//	entityTestMob.getLookHelper().setLookPositionWithEntity(attackTarget, 30F, 30F);
		if(entityTestMob2.getAnimTick() == 15 && attackTarget != null)
			attackTarget.attackEntityFrom(DamageSource.causeMobDamage(((EntityCreature)entityTestMob2)), 1);
			attackTarget.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 1, 2));
			attackTarget.motionX = 0;
			attackTarget.motionY = 0;
			attackTarget.motionZ = 0;
	}
}