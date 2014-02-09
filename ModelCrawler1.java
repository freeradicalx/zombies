package freeradicalx.zombies;

import thehippomaster.AnimationAPI.IAnimatedEntity;
import thehippomaster.AnimationAPI.client.Animator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCrawler1 extends ModelBase {
	
    ModelRenderer head, body, entrails, rightarm, leftarm;
	private Animator animator;
	
	public static final float PI = (float)Math.PI;
	
	public ModelCrawler1() {
	
		
        textureWidth = 64;
        textureHeight = 32;
        
          head = new ModelRenderer(this, 0, 0);
          head.addBox(-4,-8,-4,8,8,8);
          head.setRotationPoint(0F, 0F, 0F);
          head.setTextureSize(64, 32);
          head.mirror = true;
          body = new ModelRenderer(this, 16, 16);
          body.addBox(-4F, 0F, -2F, 8, 12, 4);
          body.setRotationPoint(0F, 22F, -4F);
          body.setTextureSize(64, 32);
          body.mirror = true;
          entrails = new ModelRenderer(this, 32, 0);
          entrails.addBox(-4F, 0F, -2F, 8, 12, 3);
          entrails.setRotationPoint(0F, 12F, 0F);
          entrails.setTextureSize(64, 32);
          entrails.mirror = true;
          rightarm = new ModelRenderer(this, 40, 16);
          rightarm.addBox(-3, 0, -2, 4, 12, 4);
          rightarm.setRotationPoint(-5F, 2F, 0F);
          rightarm.setTextureSize(64, 32);
          rightarm.mirror = true;
          leftarm = new ModelRenderer(this, 40, 16);
          leftarm.addBox(-1, 0, -2, 4, 12, 4);
          leftarm.setRotationPoint(5F, 2F, 0F);
          leftarm.setTextureSize(64, 32);
          leftarm.mirror = true;
          this.body.addChild(this.leftarm);
          this.body.addChild(this.rightarm);
          this.body.addChild(this.head);
          this.body.addChild(this.entrails);

		animator = new Animator(this);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity)entity, f, f1, f2, f3, f4, f5);
		body.render(f5);
	}
	
	public void setAngles() {
		
		setRotation(head, -1.2F, -.5F, 0F);
		setRotation(body, (float) Math.PI / 2, 0F, 0F);
		setRotation(entrails, 0F, 0F, 0F); // (float) (90 * (180 / Math.PI)));
		setRotation(rightarm, 0F,  0F, 2F);
		setRotation(leftarm, 0F,  0F, -2.8F);
		
		//body.rotationPointZ = 3F;
        //body.offsetX = 0F;
	}
	
    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
	
	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animator.update(entity);
		setAngles();
		
		System.out.println(((EntityCrawler1)entity).restCounter);
		//System.out.println("f: " + f + " f1: " +f + " f2: " + f2 + " f3: " + f3 + " f4: " + f4 + " f5: " + f5);
		
		if (((EntityCrawler1)entity).lastF == f){((EntityCrawler1)entity).restCounter++;}
		else {((EntityCrawler1)entity).restCounter = 0;}
		
		//Walk cycle
		if (((EntityCrawler1)entity).restCounter < 5){  
	        
			//Arms pulling weight of body alternately
			rightarm.rotateAngleZ = 2 + MathHelper.cos(f);
			if (MathHelper.cos(f) > MathHelper.cos(((EntityCrawler1)entity).lastF)){
				//Left right arm off ground to begin new pull
				rightarm.rotateAngleX = 1.0F - Math.abs(MathHelper.cos(f));
				leftarm.rotateAngleZ = -1.8F + MathHelper.cos(f);
			}
			//Left arm raises toward target when not pulling weight
			if (MathHelper.cos(f) < MathHelper.cos(((EntityCrawler1)entity).lastF)){
				leftarm.rotateAngleZ = (float) (Math.PI - (f3 * (Math.PI / 180)));
				leftarm.rotateAngleX = (float) -(f4 * (Math.PI / 180)) + .4F;
				if (leftarm.rotateAngleZ < 2.9F){ leftarm.rotateAngleZ = 2.9F; }
			}
	        
	        //Head's target tracking
	        head.rotateAngleZ = -(f3 / (180F / (float)Math.PI));
	        head.rotateAngleX = (f4 / (180F / (float)Math.PI)) + (head.rotateAngleY * .15F) - (float)(Math.PI / 2); //Last part compensates for rotation of parent (body)
		}
		
        
        //System.out.println(head.rotateAngleY);      
		
		//Head bang
		animator.setAnim(1);
		animator.startPhase(10);
			animator.rotate(head, -PI / 6F, 0F, 0F);
		animator.endPhase();
		animator.startPhase(4);
		animator.endPhase();
		animator.setStationaryPhase(6);
		animator.resetPhase(10);
		
		//Two-arm attack
		animator.setAnim(2);
			animator.startPhase(10);
				animator.rotate(rightarm, 0F, .7F, .5F);
				animator.rotate(leftarm, 0, .7F, 0F);
			animator.endPhase();
			animator.startPhase(10);
				animator.rotate(rightarm, 0F, -.3F, 0F);
				animator.rotate(leftarm, 0, -.3F, 0F);
			animator.endPhase();
		animator.resetPhase(10);
		
		//Grab to bite
		animator.setAnim(3);
			animator.startPhase(5);
				animator.rotate(rightarm, 0F, .7F, .5F);
				animator.rotate(leftarm, 0, .7F, 0F);
				animator.rotate(head, .3F, 0F, 0F);
			animator.endPhase();
			animator.startPhase(10);
				animator.rotate(rightarm, 0F, -.3F, 0F);
				animator.rotate(leftarm, 0, -.3F, 0F);
				animator.rotate(head, -.3F, 0F, -.6F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, .3F, 0F, .6F);
			animator.endPhase();
			animator.startPhase(5);
				animator.rotate(head, -.6F, 0F, -.3F);
			animator.endPhase();
		animator.resetPhase(10);			
		
		((EntityCrawler1)entity).lastF = f;		
	}
	
}