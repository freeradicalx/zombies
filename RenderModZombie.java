package freeradicalx.zombies;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderModZombie extends RenderLiving{
	
	static String[] texturesArray = {
		"freeradicalx-mobs:textures/entities/mobs/zombie1.png",
		"freeradicalx-mobs:textures/entities/mobs/zombie2.png",
		"freeradicalx-mobs:textures/entities/mobs/zombie3.png",
		"freeradicalx-mobs:textures/entities/mobs/zombie4.png",
		"freeradicalx-mobs:textures/entities/mobs/zombie5.png",
		"freeradicalx-mobs:textures/entities/mobs/zombie6.png",
		};
	
	public RenderModZombie(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
		
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		if (((EntityModZombie)entity).textureNumber == 5000){
			Random r = new Random();
			int textureSelector = r.nextInt(texturesArray.length);
			((EntityModZombie)entity).textureNumber = textureSelector;
		}
		ResourceLocation skin = new ResourceLocation(texturesArray[((EntityModZombie)entity).textureNumber]);
		return skin;
	}

}