package freeradicalx.zombies;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

//Listens for spawn events, useful for silencing vanilla mob spawns
public class SpawnListener {
	
	//Cancels vanilla mobs
	@ForgeSubscribe
	public void onEntitySpawn(EntityJoinWorldEvent event)
	{
		if(event.entity instanceof EntitySkeleton ||
				event.entity instanceof EntityZombie ||
				event.entity instanceof EntitySpider ||
				event.entity instanceof EntityCreeper ||
				event.entity instanceof EntityEnderman ||
				event.entity instanceof EntitySlime) {
	    	event.setCanceled(true);
	    }
	}
}