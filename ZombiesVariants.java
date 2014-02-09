package freeradicalx.zombies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import thehippomaster.AnimationAPI.client.Animator;

public class ZombiesVariants {
	
	public static int crawlerTypes = 1;
	public static int walkerTypes = 2;
	public static int textures = 3;
	
	public static float walkerAvgSpeed = 0.2F;
	public static float crawlerAvgSpeed = 0.1F;
	
	public static double walkerAvgHealth = 16D;
	public static double crawlerAvgHealth = 16D;
	
	private static Random r = new Random();
	
	private static String[] zombieTextures = new String[2];
	
	public static String getTexture(){
		
		zombieTextures[0] = "freeradicalx-mobs:textures/entities/mobs/zombie1.png";
		zombieTextures[1] = "freeradicalx-mobs:textures/entities/mobs/zombie2.png";
		zombieTextures[2] = "freeradicalx-mobs:textures/entities/mobs/zombie3.png";
		
		return zombieTextures[r.nextInt(textures - 1)];
	}
	/*
	public static double getHealth(EntityModZombie zombie){
		
		if(zombie.zombieType == "walker"){
			double modifier = (walkerAvgHealth / 4);
			System.out.println(walkerAvgHealth + (modifier / 2) - ((walkerAvgHealth / 4) * r.nextDouble()));
			return walkerAvgHealth + (modifier / 2) - ((walkerAvgHealth / 4) * r.nextDouble());
		}
		if(zombie.zombieType == "crawler"){
			double modifier = (crawlerAvgHealth / 4);
			return crawlerAvgHealth + (modifier / 2) - ((crawlerAvgHealth / 4) * r.nextDouble());
		}
		return 0D;
	}
	
	public static float getSpeed(EntityModZombie zombie){
		
		if(zombie.zombieType == "walker"){
			return 0.2F;
		}
		if(zombie.zombieType == "crawler"){
			return 0.1F;
		}
		return 0F;
	}
	*/
	
	public static int createModel(ModelModZombie zombie){

		if (zombie.zombieType == "crawler"){
			int type = r.nextInt(crawlerTypes - 1);
			zombie.crawlerType = type; 
			switch(zombie.crawlerType){
			case 0:
				
				zombie.textureWidth = 64;
				zombie.textureHeight = 32;
				zombie.head = new ModelRenderer(zombie, 0, 0);
				zombie.head.addBox(-4,-8,-4,8,8,8);
				zombie.head.setRotationPoint(0F, 0F, 0F);
				zombie.head.setTextureSize(64, 32);
				zombie.head.mirror = true;
				zombie.body = new ModelRenderer(zombie, 16, 16);
				zombie.body.addBox(-4F, 0F, -2F, 8, 12, 4);
				zombie.body.setRotationPoint(0F, 22F, -4F);
				zombie.body.setTextureSize(64, 32);
				zombie.body.mirror = true;
				zombie.entrails = new ModelRenderer(zombie, 32, 0);
				zombie.entrails.addBox(-4F, 0F, -2F, 8, 12, 3);
				zombie.entrails.setRotationPoint(0F, 12F, 0F);
				zombie.entrails.setTextureSize(64, 32);
				zombie.entrails.mirror = true;
				zombie.rightarm = new ModelRenderer(zombie, 40, 16);
				zombie.rightarm.addBox(-3, 0, -2, 4, 12, 4);
				zombie.rightarm.setRotationPoint(-5F, 2F, 0F);
				zombie.rightarm.setTextureSize(64, 32);
				zombie.rightarm.mirror = true;
				zombie.leftarm = new ModelRenderer(zombie, 40, 16);
				zombie.leftarm.addBox(-1, 0, -2, 4, 12, 4);
				zombie.leftarm.setRotationPoint(5F, 2F, 0F);
				zombie.leftarm.setTextureSize(64, 32);
				zombie.leftarm.mirror = true;
				zombie.body.addChild(zombie.leftarm);
				zombie.body.addChild(zombie.rightarm);
				zombie.body.addChild(zombie.head);
				zombie.body.addChild(zombie.entrails);
				return 0;
			}
		}
		
		return 0;
	}
	
	public static void setAngles(ModelModZombie zombie){
		
		if (zombie.zombieType == "walker"){
			switch (zombie.walkerType){
			case 0:
				
				zombie.setRotation(zombie.head, -.36F, 0F, .15F);
				zombie.setRotation(zombie.body, -.3F, 0F, 0F);
				zombie.setRotation(zombie.rightarm, .5F, 0F, 0F);
				zombie.setRotation(zombie.leftarm, .5F, 0F, 0F);
				zombie.setRotation(zombie.rightleg, 0F, 0F, 0F);
				zombie.setRotation(zombie.leftleg, 0.1115358F, 0F, 0F);	
				zombie.body.rotationPointZ = 3F;
				zombie.body.offsetX = 0F;
				break;
				
			case 1:
				
				zombie.setRotation(zombie.head, -.25F, 0F, -.35F);
				zombie.setRotation(zombie.body, 0.3346075F, 0F, -0.1487144F);
				zombie.setRotation(zombie.rightarm, -0.89F, 0F, 0F);
				zombie.setRotation(zombie.leftarm, -1.705F, -0.1858931F, 0F);
				zombie.setRotation(zombie.rightleg, 0F, 0F, 0F);
				zombie.setRotation(zombie.leftleg, 0.1115358F, 0F, 0F);	
				zombie.body.rotationPointZ = -4F;
				zombie.body.offsetX = 0F;
				break;
				
			}
		}
		if (zombie.zombieType == "crawler"){
			switch (zombie.crawlerType){
			case 0:
				
				zombie.setRotation(zombie.head, -1.2F, -.5F, 0F);
				zombie.setRotation(zombie.body, (float) Math.PI / 2, 0F, 0F);
				zombie.setRotation(zombie.entrails, 0F, 0F, 0F); // (float) (90 * (180 / Math.PI)));
				zombie.setRotation(zombie.rightarm, 0F,  0F, 2F);
				zombie.setRotation(zombie.leftarm, 0F,  0F, -2.8F);
				break;
			}
		}
		
		return;
	}
		
}