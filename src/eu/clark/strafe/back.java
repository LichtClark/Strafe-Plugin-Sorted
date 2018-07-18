package eu.clark.strafe;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import eu.clark.main.Main;


public class back implements Listener {

	public Integer time = 60;

	 public back(Main plugin) {
			this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
		Plugin plugin;

		private Object playerSkullTexture;
	@SuppressWarnings({ "deprecation" })
	@EventHandler(priority=EventPriority.HIGH)
	public void onPlayerInteract(PlayerInteractEvent event) { //testet für eine interaktion
		
		Player p = event.getPlayer();
		
	
		if (!(p.getInventory().getItemInHand().getType() == Material.SKULL_ITEM)) { //checkt ob das item ein kopf item ist
            return;            
        }
		
        ItemStack iteminhand = p.getInventory().getItemInHand(); //checkt für item in der hand
        if (!(iteminhand.hasItemMeta())) {
            return;
        }

        if (!(iteminhand.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lStrafe Back"))) { //checkt für den displaynamen
            return;
        }
        
        if (event.getItem() != null){
            if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) { //Tested links rechts clock
            	   if (event.getItem() == null){
                      	//Tested links rechts clock
                      } else {
                      	if(event.getItem() != null){
             	        	if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
             	        		if(event.getItem() == null);
             	        			if(!Main.used2.contains(p.getName()))  {
             	        				ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,60,(short) SkullType.PLAYER.ordinal());	
             	        				SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
             	        				meta.setDisplayName("§a§lStrafe Back");
             	        				GameProfile profile = new GameProfile(UUID.randomUUID(), null);
             	        				byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/5e361016217f88fd42bcf9cbed13b47e1dceb1e6475bcff88893fbd6ee448cb3\"}}}", this.playerSkullTexture).getBytes());
             	        				profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
             	        				Field profileField = null;
             	        				try {
             	        					profileField = meta.getClass().getDeclaredField("profile");
             	        					profileField.setAccessible(true);
             	        					profileField.set(meta, profile);
             	        				} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
             	        					e1.printStackTrace();
             	        				}
             	        				playerskull.setItemMeta(meta);
             	        				p.getInventory().setItem(4, playerskull);
                  		
             	        				double rot = (p.getLocation().getYaw() - 45) % 360;
             	        				if (rot < 0) {
             	        					rot += 360.0;
             	        				}
             	        				if (-45.5 <= rot && rot < 90.5) {
             	        					org.bukkit.util.Vector v2 = p.getLocation().getDirection().setY(0.3).setX(2.5).setZ(0);
             	        					p.setVelocity(v2);
             	        					p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
             	        					p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
             	        					Main.used2.add(p.getName());
			   
             	        					Main.count1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
             	        						private Object playerSkullTexture;

             	        						@Override       	      		     	      		
             	        						public void run() {
             	        							if (time == 60) {
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
             	        							} else if(time <= 59 && time > 1) {					
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        						         									
             	        							} else if(time == 1) {								
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        	
             	        							} else if(time == 0) {           	
             	        								Main.used2.remove(p.getName());									
             	        								ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
             	        								SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
             	        								meta.setDisplayName("§a§lStrafe Back");
             	        								GameProfile profile = new GameProfile(UUID.randomUUID(), null);
             	        								byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/9fdfc66314675bd63bc8d2af17c85f2dfcce196e97ecf42b78f93cdef253b23\"}}}", this.playerSkullTexture).getBytes());
             	        								profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
             	        								Field profileField = null;
             	        								try {
             	        									profileField = meta.getClass().getDeclaredField("profile");
             	        									profileField.setAccessible(true);
             	        									profileField.set(meta, profile);
             	        								} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
             	        									e1.printStackTrace();
             	        								}
             	        								playerskull.setItemMeta(meta);
             	        								p.getInventory().setItem(4, playerskull);	       						     			
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");       						      					
             	        								Bukkit.getScheduler().cancelTask(Main.count1);   	
             	        							}          				
             	        							time--;      
             	        							return;
             	        						}       	      	
             	        					}   	        				   	        				
             	        					,0, 20);   	       		
             	        					time = 60;
       	 
             	        				} else { if (90.5 <= rot && rot < 180.5) {																//tests for rotation 
             	        					org.bukkit.util.Vector v2 = p.getLocation().getDirection().setY(0.3).setZ(2.5).setX(0);			//executes boost
             	        					p.setVelocity(v2);
             	        					p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
             	        					p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
             	        					Main.used2.add(p.getName());		
             	        					Main.count1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
             	        						private Object playerSkullTexture;

             	        						@Override       	      		     	      		
             	        						public void run() {
             	        							if (time == 60) {
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
             	        							} else if(time <= 59 && time > 1) {						
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        						         									
             	        							} else if(time == 1) {					
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        	
             	        							} else if(time == 0) {           	
             	        								Main.used2.remove(p.getName());									
             	        								ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
             	        								SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
             	        								meta.setDisplayName("§a§lStrafe Back");
             	        								GameProfile profile = new GameProfile(UUID.randomUUID(), null);
             	        								byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/9fdfc66314675bd63bc8d2af17c85f2dfcce196e97ecf42b78f93cdef253b23\"}}}", this.playerSkullTexture).getBytes());
             	        								profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
             	        								Field profileField = null;
             	        								try {
             	        									profileField = meta.getClass().getDeclaredField("profile");
             	        									profileField.setAccessible(true);
             	        									profileField.set(meta, profile);
             	        								} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
             	        									e1.printStackTrace();
             	        								}
             	        								playerskull.setItemMeta(meta);
             	        								p.getInventory().setItem(4, playerskull);				
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
             	        								Bukkit.getScheduler().cancelTask(Main.count1);              			            								        					 
             	        							}          				
             	        							time--;      
             	        							return;
             	        						}       	      	
             	        					}   	        				   	        				
             	        					,0, 20);   	         
			      
             	        					time = 60;
 
             	        				} else { if (180.5 <= rot && rot <270.5) {
             	        					org.bukkit.util.Vector v2 = p.getLocation().getDirection().setY(0.3).setX(-2.5).setZ(0);
             	        					p.setVelocity(v2);
             	        					p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
             	        					p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
             	        					Main.used2.add(p.getName());
				
             	        					Main.count1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
             	        						private Object playerSkullTexture;

             	        						@Override       	      		     	      		
             	        						public void run() {
             	        							if (time == 60) {
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
             	        							} else if(time <= 59 && time > 1) {					
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        						         									
             	        							} else if(time == 1) {					
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        	
             	        							} else if(time == 0) {           	
             	        								Main.used2.remove(p.getName());									
             	        								ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
             	        								SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
             	        								meta.setDisplayName("§a§lStrafe Back");
             	        								GameProfile profile = new GameProfile(UUID.randomUUID(), null);
             	        								byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/9fdfc66314675bd63bc8d2af17c85f2dfcce196e97ecf42b78f93cdef253b23\"}}}", this.playerSkullTexture).getBytes());
             	        								profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
             	        								Field profileField = null;
             	        								try {
             	        									profileField = meta.getClass().getDeclaredField("profile");
             	        									profileField.setAccessible(true);
             	        									profileField.set(meta, profile);
             	        									} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
             	        										e1.printStackTrace();
             	        									}
             	        								playerskull.setItemMeta(meta);
             	        								p.getInventory().setItem(4, playerskull);	   			
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
             	        								Bukkit.getScheduler().cancelTask(Main.count1);              			            								        					 
             	        							}          				
             	        							time--;      
             	        							return;
             	        						}       	      	
             	        					}   	        				   	        				
             	        					,0, 20);   	           

             	        					time = 60;
       	         
             	        				} else { if (270.5 <= rot && rot < 360.0) {
             	        					org.bukkit.util.Vector v2 = p.getLocation().getDirection().setY(0.3).setZ(-2.5).setX(0);
             	        					p.setVelocity(v2);
             	        					p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
             	        					p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
             	        					Main.used2.add(p.getName());
		
             	        					Main.count1 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
             	        						private Object playerSkullTexture;

             	        						@Override       	      		     	      		
             	        						public void run() {
             	        							if (time == 60) {
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
             	        							} else if(time <= 59 && time > 1) {						
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        						         									
             	        							} else if(time == 1) {					
             	        								for(int i = 4; i < p.getInventory().getSize(); i++){
             	        									ItemStack itm = p.getInventory().getItem(i);
             	        									if(itm != null && itm.getType().equals(Material.SKULL_ITEM)){
             	        										int amt = itm.getAmount() - 1;
             	        										itm.setAmount(amt);
             	        										p.getInventory().setItem(i, amt > 0 ? itm : null);
             	        										p.updateInventory();
             	        										break;
             	        									}
             	        								}        	
             	        							} else if(time == 0) {           	
             	        								Main.used2.remove(p.getName());									
             	        								ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
             	        								SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
             	        								meta.setDisplayName("§a§lStrafe Back");
             	        								GameProfile profile = new GameProfile(UUID.randomUUID(), null);
             	        								byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/9fdfc66314675bd63bc8d2af17c85f2dfcce196e97ecf42b78f93cdef253b23\"}}}", this.playerSkullTexture).getBytes());
             	        								profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
             	        								Field profileField = null;
             	        								try {
             	        									profileField = meta.getClass().getDeclaredField("profile");
             	        									profileField.setAccessible(true);
             	        									profileField.set(meta, profile);
             	        								} catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
             	        									e1.printStackTrace();
             	        								}
             	        								playerskull.setItemMeta(meta);
             	        								p.getInventory().setItem(4, playerskull);   			
             	        								p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
             	        								Bukkit.getScheduler().cancelTask(Main.count1);              			            								        					 
             	        							}          				
             	        							time--;      
             	        							return;
             	        						}       	      	
             	        					}   	        				   	        				
             	        					,0, 20);     
             	        					
             	        					time = 60;
	       		   
             	        							}   
       	   	
             	        						}
 	        	        
             	        					}
       	    			 				 
             	        				}
             	        				
             	        			}
             	        			
     	    					}
     	    
     	    				}
	
     	    			}
     	    
       	   			}
      
      			}
	
			}
	
		}