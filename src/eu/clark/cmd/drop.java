package eu.clark.cmd;

import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.Plugin;

import eu.clark.main.Main;

public class drop implements Listener {

	public drop(Main plugin) {
			this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
			
	 }
	
		Plugin plugin;
		@EventHandler
		public void onPlayerDropItem(PlayerDropItemEvent event){
			if(event.getItemDrop().getItemStack().getType() == Material.SKULL_ITEM){
			event.setCancelled(true);
			}
			
			}
		@EventHandler
	    public void onClickSlot(InventoryClickEvent e) {
	        if(e.getSlot() == 3) {
	        } else if(e.getSlot() == 4) {
	        } else if(e.getSlot() == 5)
	            e.setResult(Result.DENY);
	            e.setCancelled(true);
	        	
				}
		
		}