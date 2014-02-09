package freeradicalx.zombies;

import thehippomaster.AnimationAPI.client.Animator;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelModZombie extends ModelBase {
	
    public ModelRenderer head, body, rightarm, leftarm, rightleg, leftleg, entrails;
	protected Animator animator;
	public static String zombieType;
	public int walkerType;
	public int crawlerType;
	
	public ModelModZombie(){
		animator = new Animator(this);
	}
	
    public void setRotation(ModelRenderer model, float x, float y, float z)
    {
    	model.rotateAngleX = x;
    	model.rotateAngleY = y;
    	model.rotateAngleZ = z;
    }
	

}
