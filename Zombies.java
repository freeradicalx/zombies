package freeradicalx.zombies;

import org.lwjgl.input.Keyboard;
import thehippomaster.AnimationExample.EntityTest;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import freeradicalx.vehicles.EntityBuick;
import freeradicalx.vehicles.VehiclesCommonProxy;
import freeradicalx.vehicles.packets.VehiclesPacketHandler;
import freeradicalx.zombies.lib.Reference;

@Mod(
		modid = Reference.MOD_ID,
		name = Reference.MOD_NAME,
		version = Reference.MOD_VERSION)

@NetworkMod(
		serverSideRequired = false,
		clientSideRequired = true)

public class Zombies {
	
	@SidedProxy
	(clientSide = "freeradicalx.zombies.ZombiesClientProxy", serverSide = "freeradicalx.zombies.ZombiesCommonProxy")
	public static ZombiesCommonProxy proxy;
	
	@EventHandler
	public void load(FMLInitializationEvent event){
		
		proxy.registerRenderers();
		proxy.registerSounds();
		proxy.registerItems();
		
		MinecraftForge.EVENT_BUS.register(new SpawnListener());
		
		EntityRegistry.registerGlobalEntityID(EntityWalker.class, "Walker", 1, 1000, 5000);
		EntityRegistry.registerGlobalEntityID(EntityCrawler.class, "Crawler", 2, 1000, 5000);
		//EntityRegistry.registerGlobalEntityID(EntityCrawler1.class, "Crawler 1", 3, 1000, 5000);
		
		for (int i = 0; i < BiomeGenBase.biomeList.length; i++)
		{
		    if (BiomeGenBase.biomeList[i] != null)
		    {
		        EntityRegistry.addSpawn(EntityWalker.class, 10, 1, 3, EnumCreatureType.monster, BiomeGenBase.biomeList[i]);
		    	//EntityRegistry.addSpawn(EntityZombie2.class, 10, 1, 3, EnumCreatureType.monster, BiomeGenBase.biomeList[i]);
		    }
		}
		
		LanguageRegistry.instance().addStringLocalization("entity.testmob2.name", "Test Mob 2");
		LanguageRegistry.instance().addStringLocalization("entity.zombie2.name", "Zombie 2");
		LanguageRegistry.instance().addStringLocalization("entity.crawler1.name", "Crawler 1");
	
	}

}
