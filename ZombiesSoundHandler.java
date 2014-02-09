package freeradicalx.zombies;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.*;

public class ZombiesSoundHandler {
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void load(SoundLoadEvent event) {
		
		for(String sound : ZombiesSounds.files){
			try{
				event.manager.soundPoolSounds.addSound(sound);
				System.out.println("<<<< LOADED SOUND: " + sound + " >>>>");
			}
			catch (Exception e){
				System.out.println("<<<< FAILED TO LOAD SOUND: " + sound + " >>>>");
			}
		}
		
	}


}
