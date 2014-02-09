package freeradicalx.zombies;

import java.util.Random;

import thehippomaster.AnimationAPI.IAnimatedEntity;
import thehippomaster.AnimationAPI.client.Animator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelWalker extends ModelModZombie {
	
	Random r = new Random();
	public static String zombieType = "walker";
	//public int walkerType;
	ModelRenderer head, body, leftarm, rightarm, leftleg, rightleg;
	
	public ModelWalker() {
		super();
			
		textureWidth = 64;
		textureHeight = 32;
		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4,-8,-4,8,8,8);
		head.setRotationPoint(0F, 0F, 0F);
		head.setTextureSize(64, 32);
		head.mirror = true;
		body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0F, -2F, 8, 12, 4);
        body.setRotationPoint(0F, 0F, 3F);
		body.setTextureSize(64, 32);
		body.mirror = true;
		rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-4, 0, -2, 4, 12, 4);
        rightarm.setRotationPoint(-4F, 0F, 0F);
        rightarm.setTextureSize(64, 32);
		rightarm.mirror = true;
		leftarm = new ModelRenderer(this, 40, 16);
		leftarm.addBox(0, 0, -2, 4, 12, 4);
        leftarm.setRotationPoint(4F, 0F, 0F);
		leftarm.setTextureSize(64, 32);
		leftarm.mirror = true;
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        rightleg.setRotationPoint(-2F, 12F, 0F);
        rightleg.setTextureSize(64, 32);
        rightleg.mirror = true;
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0F, -2F, 4, 12, 4);
        leftleg.setRotationPoint(2F, 12F, 0F);
        leftleg.setTextureSize(64, 32);
        leftleg.mirror = true;
		body.addChild(leftarm);
		body.addChild(rightarm);
		body.addChild(head);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity)entity, f, f1, f2, f3, f4, f5);
		body.render(f5);
		rightleg.render(f5);
		leftleg.render(f5);
	}
	
	public void setAngles(EntityWalker entity) {
		
		//System.out.println(entity.walkerType);
		
		switch(entity.walkerType){
		//switch(4){
		
		//Leaning backward, arms limp, staring slightly up
		case 0:
			
			body.offsetX = 0F; //-4
			body.rotationPointZ = 3F;
			setRotation(head, -.36F, 0F, .15F);
			setRotation(body, -.3F, 0F, 0F);
			setRotation(rightarm, .3F, 0F, 0F);
			setRotation(leftarm, .3F, 0F, 0F);
			setRotation(rightleg, 0F, 0F, 0F);
			setRotation(leftleg, 0.1115358F, 0F, 0F);	
			break;
		
		//Leaning forward and a bit to the right, arms outstretched, left arm more than right
		case 1:
			
	        body.offsetX = -.0625F;
	        body.rotationPointX = -1F;
	        body.rotationPointY = 1F;
			body.rotationPointZ = -4F;
			setRotation(head, -.25F, 0F, -.35F);
			setRotation(body, 0.3346075F, 0F, -0.1487144F);
			setRotation(rightarm, -0.89F, 0F, 0F);
			setRotation(leftarm, -1.705F, -0.1858931F, 0F);
			setRotation(rightleg, 0F, 0F, 0F);
			setRotation(leftleg, 0.1115358F, 0F, 0F);	
			break;
			
		case 2:
			
	        body.offsetX = 0F;
	        body.rotationPointY = 3F;
			body.rotationPointZ = -8F;
			setRotation(head, .3F, 0F, 0F);
			setRotation(body, 0.7F, 0F, 0F);
			setRotation(rightarm, -.7F, 0F, 0F);
			setRotation(leftarm, -.7F, 0F, 0F);
			setRotation(rightleg, 0F, 0F, 0F);
			setRotation(leftleg, 0.1115358F, 0F, 0F);	
			break;
			
		case 3:
			
			setOffset(body, -.45F, .03125F, -1.5F);
			setOffset(leftleg, -.25F, 0F, -.25F);
			leftleg.rotationPointY = 13F;
			setRotation(head, -.3F, 0F, -.3F);
			setRotation(body, -.15F, 0F, 0F);
			setRotation(rightarm, .1F, 0F, .4F);
			setRotation(leftarm, .1F, 0F, -.4F);
			setRotation(leftleg, 0.4F, 0F, 0F);	
			break;
			
		case 4:
			
			setOffset(body, -.45F, .03125F, .25F);
			setRotation(head, -.3F, 0F, .3F);
			setRotation(body, -.3F, 0F, 0F);
			setRotation(rightarm, -.3F, 0F, 0F);
			setRotation(leftarm, .2F, 0F, 0F);
			setRotation(rightleg, 0F, 0F, 0F);	
			setRotation(leftleg, 0F, 0F, 0F);	
			break;
		}
			
	}
	
	public void setOffset(ModelRenderer box, float x, float y, float z){
		box.offsetX = x / 16;
		box.offsetY = y / 16;
		box.offsetZ = z / 16;
	}
	
	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {	
		animator.update(entity);
		setAngles(((EntityWalker)entity));
		if (((EntityWalker)entity).lastF == f){((EntityWalker)entity).restCounter++;}
		else {((EntityWalker)entity).restCounter = 0;}
		//System.out.println(((Entity)entity).getUniqueID() + ": " +  ((EntityWalker)entity).restCounter);
		
		switch (((EntityWalker)entity).walkerType){
		//switch (4){
		case 0:
			
			//Walk cycle
			if (((EntityWalker)entity).restCounter < 5){
				
				//Leg swing
		        rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		        leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		        
		        //Crooked body sway
		        body.rotateAngleZ = MathHelper.cos(f) * 0.1F;
		        body.offsetX = MathHelper.cos(f) * .0625F;    
		        
		        //Arms animation, grabbing forward at air with left hand
		        rightarm.rotateAngleX = -0.8F + MathHelper.cos(f) * 0.3F;
		        leftarm.rotateAngleX = -0.8F - MathHelper.cos(f - 1.5F) * 0.3F;
		        
		        //Head's target tracking
		        head.rotateAngleY = (f3 / (180F / (float)Math.PI));
		        head.rotateAngleX = (f4 / (180F / (float)Math.PI)) + (head.rotateAngleY * .15F); //Last part compensates for rotation of parent (body)
			}
			
			//Grab to bite
			animator.setAnim(3);
				animator.startPhase(5);
					animator.rotate(rightarm, -1.5F, .4F, 0F);
					animator.rotate(leftarm, -.2F, -.4F, 0F);
					animator.rotate(head, .3F, 0F, 0F);
				animator.endPhase();
				animator.startPhase(10);
					animator.rotate(body, .2F, 0F, 0F);
					animator.move(body, 0F, 0F, -3F);
					animator.rotate(rightarm, .2F, -.6F, 0F);
					animator.rotate(leftarm, .2F, .6F, 0F);
					animator.rotate(head, -.3F, 0F, -.6F);
				animator.endPhase();
				animator.startPhase(5);
					animator.rotate(body, -.2F, 0F, 0F);
					animator.move(body, 0F, 0F, 3F);
					animator.rotate(head, .3F, 0F, .6F);
				animator.endPhase();
				animator.startPhase(5);
					animator.rotate(head, -.6F, 0F, -.3F);
				animator.endPhase();
			animator.resetPhase(10);			
			break;
		
		case 1:
			//Walk cycle
			if (((EntityWalker)entity).restCounter < 5){
		        rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		        leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		        
		        //Limp leg
		        leftleg.rotateAngleZ = -.1F + MathHelper.cos(f) * .1F;
		        
		        //Crooked body sway
		        body.rotateAngleZ = -0.335F + MathHelper.cos(f) * 0.1F;
		        body.offsetX = -.2F + MathHelper.cos(f) * .0625F; //
		        
		        //Arms animation, grabbing forward at air with left hand
		        rightarm.rotateAngleX = -0.89F + MathHelper.cos(f) * 0.2F;
		        leftarm.rotateAngleX = -1.705F - MathHelper.cos(f - 1.5F) * 0.2F;
		        
		        //Head's target tracking
		        head.rotateAngleY = (f3 / (180F / (float)Math.PI)) + 0.1487144F;
		        head.rotateAngleX = (f4 / (180F / (float)Math.PI)) - (head.rotateAngleY * .15F); //Last part compensates for rotation of parent (body)
			}
			
			animator.setAnim(3);
			animator.startPhase(5);
				animator.rotate(rightarm, -1.5F, .4F, 0F);
				animator.rotate(leftarm, -.2F, -.4F, 0F);
				animator.rotate(head, .3F, 0F, 0F);
			animator.endPhase();
			animator.startPhase(10);
				animator.rotate(body, .2F, 0F, 0F);
				animator.move(body, 0F, 0F, -3F);
				animator.rotate(rightarm, .2F, -.6F, 0F);
				animator.rotate(leftarm, .2F, .6F, 0F);
				animator.rotate(head, -.3F, 0F, -.6F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(body, -.2F, 0F, 0F);
				animator.move(body, 0F, 0F, 3F);
				animator.rotate(head, .3F, 0F, .6F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, -.6F, 0F, -.3F);
			animator.endPhase();
			animator.resetPhase(10);
			break;
			
		case 2:
			//Walk cycle
			if (((EntityWalker)entity).restCounter < 5){
		        rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		        leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		        
		        //Limp leg
		        leftleg.rotateAngleZ = -.1F + MathHelper.cos(f) * .1F;
		        
		        //Crooked body sway
		        body.rotateAngleZ = MathHelper.cos(f) * 0.15F;
		        body.offsetX = MathHelper.cos(f) * .125F;
		        
		        //Arms animation, grabbing forward at air with left hand
		        rightarm.rotateAngleZ = MathHelper.cos(f + 1.57F) * 0.2F;
		        leftarm.rotateAngleZ = MathHelper.cos(f + 1.57F) * 0.2F;
		        
		        //Head's target tracking
		        head.rotateAngleY = (f3 / (180F / (float)Math.PI));
		        head.rotateAngleX = (f4 / (180F / (float)Math.PI)) - (head.rotateAngleY * .15F) - .7F; //Last part compensates for rotation of parent (body)
		        head.rotateAngleZ = ((f4 / (180F / (float)Math.PI)) + MathHelper.cos(f - 1F)) * .2F;
			}
			
			animator.setAnim(3);
			animator.startPhase(5);
				animator.rotate(rightarm, -1.7F, 0F, 0F);
				animator.rotate(leftarm, -1.7F, 0F, 0F);
				animator.rotate(head, -.3F, 0F, 0F);
			animator.endPhase();
			animator.startPhase(10);
				animator.rotate(rightarm, -1.7F, 0F, 0F);
				animator.rotate(leftarm, -1.7F, 0F, 0F);
				animator.rotate(body, -.2F, 0F, 0F);
				animator.move(body, 0F, 0F, 1F);
				//animator.rotate(rightarm, .2F, -.6F, 0F);
				//animator.rotate(leftarm, .2F, .6F, 0F);
				animator.rotate(head, .3F, 0F, -.6F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(rightarm, -1.7F, 0F, 0F);
				animator.rotate(leftarm, -1.7F, 0F, 0F);
				//animator.rotate(body, -.2F, 0F, 0F);
				//animator.move(body, 0F, 0F, 0F);
				//animator.rotate(head, .3F, 0F, .6F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(rightarm, -1.7F, 0F, 0F);
				animator.rotate(leftarm, -1.7F, 0F, 0F);
				animator.rotate(head, -.6F, 0F, -.3F);
				animator.rotate(body, .2F, 0F, 0F);
				animator.move(body, 0F, 0F, -1F);
			animator.endPhase();
			animator.resetPhase(10);
			break;
			
		case 3:
			
			//Walk cycle
			if (((EntityWalker)entity).restCounter < 5){
		        rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1 * 1.3F;
		        
		        body.rotateAngleZ = (-rightleg.rotateAngleX * 0.15F) + .15F;
		        body.offsetX = (-rightleg.rotateAngleX * .125F) + .125F;
				
		        leftleg.offsetY = (body.rotateAngleZ * .5F) - .125F;
		        leftleg.offsetZ = (body.rotateAngleZ) - .125F;
		        leftleg.rotateAngleX = (body.rotateAngleZ * 3) + .05F;
		        
		        rightarm.rotateAngleX = ((((MathHelper.cos((f * 0.6662F) - .75F) * f1) * 0.2F) + .2F) * 4) - .5F;
		        leftarm.rotateAngleX = MathHelper.cos((f*.6662F) + 1.57F) * 0.2F;
		        leftarm.rotateAngleZ = ((MathHelper.cos((f * 0.6662F) - 1.57F) * f1) * .15F) - .15F;
		        
		        head.rotateAngleZ = ((MathHelper.cos((f * 0.6662F) + .75F) * f1 * 1.3F) * + .3F) - .3F;
		        //System.out.println(body.rotateAngleZ);
			}
			
			animator.setAnim(3);
			animator.startPhase(5);
				animator.rotate(head, 0F, 0, -.3F);
				animator.rotate(leftarm, -.4F, 0, -.7F);
				animator.rotate(rightarm, -.7F, 0, .4F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, .3F, 0, 0F);
				animator.rotate(leftarm, -1.4F, 0, -1.3F);
				animator.rotate(rightarm, -1.4F, 0, 1.3F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, .6F, 0, .3F);
				animator.rotate(leftarm, -1.6F, .3F, -1.8F);
				animator.rotate(rightarm, -1.8F, -.3F, 1.3F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, .6F, 0, 0F);
				animator.rotate(leftarm, -1.6F, .5F, -1.8F);
				animator.rotate(rightarm, -1.8F, -.3F, 1.3F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, 0F, 0, -.3F);
				animator.rotate(leftarm, -1.6F, 0F, -1.8F);
				animator.rotate(rightarm, -1.8F, -.7F, 1.3F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, 0F, 0, 0F);
				animator.rotate(leftarm, -1.6F, 0, -1.8F);
				animator.rotate(rightarm, -1.8F, 0, 1.3F);
			animator.endPhase();
			break;
			
		case 4:
			
			if (((EntityWalker)entity).restCounter < 5){
				//Leg swing
		        rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		        leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * f1;
		        
		        body.rotateAngleX = MathHelper.cos(f * 0.6662F) * f1;
		        body.offsetZ = (-(MathHelper.cos(f * 0.6662F) * f1) * .8F) - .2F;
		        System.out.println(body.offsetZ);
				
		        leftarm. rotateAngleX = MathHelper.cos((f * 0.6662F) + .75F);
		        rightarm. rotateAngleX = -MathHelper.cos((f * 0.6662F) + .75F);
		        
		        head.rotateAngleX = MathHelper.cos((f * 0.6662F) - .75F) * .75F;
			}
			
			animator.setAnim(3);
			animator.startPhase(10);
				animator.rotate(head, -.5F, 0, -.2F);
				animator.rotate(leftarm, -2.5F, 0, 0F);
				animator.rotate(rightarm, -2.5F, 0, 0F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, 0F, 0, .2F);
				animator.rotate(leftarm, -.5F, 0, 0F);
				animator.rotate(rightarm, -.5F, 0, 0F);
			animator.endPhase();
			animator.startPhase(10);
				animator.rotate(head, -.5F, 0, -.2F);
				animator.rotate(leftarm, -2.5F, 0, 0F);
				animator.rotate(rightarm, -2.5F, 0, 0F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, 0F, 0, .2F);
				animator.rotate(leftarm, -.5F, 0, 0F);
				animator.rotate(rightarm, -.5F, 0, 0F);
			animator.endPhase();
			animator.resetPhase(5);
			break;
		}
		
		((EntityWalker)entity).lastF = f;
	}
	
}