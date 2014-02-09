package freeradicalx.zombies;

import org.lwjgl.input.Keyboard;

import thehippomaster.AnimationExample.EntityTest;
import thehippomaster.AnimationExample.client.ModelTest;
import thehippomaster.AnimationExample.client.RenderTest;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ZombiesClientProxy extends ZombiesCommonProxy {

	@Override
	public void registerRenderers(){
		  
		float shadowSize = 0.5F;

		RenderingRegistry.registerEntityRenderingHandler(EntityWalker.class, new RenderModZombie(new ModelWalker(), shadowSize));
		RenderingRegistry.registerEntityRenderingHandler(EntityCrawler.class, new RenderModZombie(new ModelCrawler(), shadowSize));
		//RenderingRegistry.registerEntityRenderingHandler(EntityCrawler1.class, new RenderCrawler1(new ModelCrawler1(), shadowSize));
		//RenderingRegistry.registerEntityRenderingHandler(EntityTest.class, new RenderTest());
	}
	
	public void registerSounds(){
	
		MinecraftForge.EVENT_BUS.register(new ZombiesSoundHandler());

	}
	
	public void registerKeys(){
		
		KeyBinding[] keys = {new KeyBinding("gas", Keyboard.KEY_W),
				new KeyBinding("brake", Keyboard.KEY_S),
				new KeyBinding("left", Keyboard.KEY_A),
				new KeyBinding("right", Keyboard.KEY_D)};
		
		boolean[] repeats = {false, false, false, false};   
		
	}
	
}
