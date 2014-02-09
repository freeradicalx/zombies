package freeradicalx.zombies;

import thehippomaster.AnimationAPI.IAnimatedEntity;
import thehippomaster.AnimationAPI.client.Animator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCrawler extends ModelModZombie {
	
	public static String zombieType = "crawler";
	public int crawlerType;
	
	public ModelCrawler() {
		super();
		walkerType = ZombiesVariants.createModel(this);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity)entity, f, f1, f2, f3, f4, f5);
		this.body.render(f5);
	}
	
	public void setAngles() {
		ZombiesVariants.setAngles(this);
	}
	
	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {	
		animator.update(entity);
		setAngles();
		if (((EntityCrawler)entity).lastF == f){((EntityCrawler)entity).restCounter++;}
		else {((EntityCrawler)entity).restCounter = 0;}
		System.out.println(((Entity)entity).getUniqueID() + ": " +  ((EntityCrawler)entity).restCounter);
		
		switch (crawlerType){
		case 0:
			
			//Crawl cycle
			if (((EntityCrawler)entity).restCounter < 5){
				
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
			break;
		}
		
		((EntityCrawler)entity).lastF = f;
	}
	
}