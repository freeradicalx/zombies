package freeradicalx.zombies;

import java.util.Random;

import thehippomaster.AnimationAPI.AIAnimation;
import thehippomaster.AnimationAPI.AnimationAPI;
import thehippomaster.AnimationAPI.IAnimatedEntity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class AIWander extends AIAnimation {
	
    private IAnimatedEntity entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
	
	public AIWander(IAnimatedEntity entity) {
		super(entity);
	}
	
    public AIWander(IAnimatedEntity entityAnimated, double speed1)
    {
    	this(entityAnimated);
        this.entity = entityAnimated;
        this.speed = speed1;
        this.setMutexBits(1);
    }

	public int getAnimID() {
		return 1;
	}
	
	public boolean isAutomatic() {
		return true;
	}
	
	public int getDuration() {
		return 20;
	}
	
	public void startExecuting() {
		//remember to super!
		super.startExecuting();
        ((EntityCreature)this.entity).getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
	}
	
    public boolean shouldExecute()
    {
    	
    	Random r = new Random();
    	
        if (r.nextInt(120) != 0)
        {
            return false;
        }
        else
        {
            Vec3 vec3 = RandomPositionGenerator.findRandomTarget((EntityCreature)this.entity, 10, 7);

            if (vec3 == null)
            {
                return false;
            }
            else
            {
                this.xPosition = vec3.xCoord;
                this.yPosition = vec3.yCoord;
                this.zPosition = vec3.zCoord;
                if(entity.getAnimID() == 0) AnimationAPI.sendAnimPacket(entity, 1);
                return true;
            }
        }
    }
    
    public boolean shouldAnimate(){
    	return true;
    }
    
    public boolean continueExecuting()
    {
    	if (((EntityCreature)this.entity).getNavigator().noPath()){
    		return false;
    	}
    	else{
    		if (entity.getAnimTick() > 19){
    			entity.setAnimTick(0);
    			//System.out.println(entity.getAnimID());
    			//AnimationAPI.sendAnimPacket(entity, 1);
    		}
    		return true;
    	}
    	//System.out.println("CONTINUE EXECUTING");
    	/*if( entity.getAnimID() == 0 &&  !((EntityCreature)this.entity).getNavigator().noPath())*/
        
    	//return !((EntityCreature)this.entity).getNavigator().noPath();
    }
	
	
	
	

}
