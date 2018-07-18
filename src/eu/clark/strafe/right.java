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

public class right implements Listener {

	public Integer time = 60;

	public right(Main main) {
		this.plugin = main;
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

        if (!(iteminhand.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lStrafe Right"))) { //checkt für den displaynamen
            return;      
        }
        
        	if (event.getItem() != null){
        		if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) { //Tested links rechts clock
        			if (event.getItem() == null){
        			} else {
        				if(event.getItem() != null){
        					if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
        						if(event.getItem() == null);
        						if(!Main.used1.contains(p.getName()))  {
        							ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,60,(short) SkullType.PLAYER.ordinal());	
        							SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        							meta.setDisplayName("§a§lStrafe Right");
        							GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        							byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/665fa5148d14e3fc78cd40f76aa087f4836d73e3713f81b3b6e73d2bc84292d\"}}}", this.playerSkullTexture).getBytes());
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
        							p.getInventory().setItem(5, playerskull);     
				
        							double rot = (p.getLocation().getYaw() - 45) % 360; {
        								if (rot < 0) {
        									rot += 360.0;
        								}
        								if (-45.5 <= rot && rot < 90.5) {
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setZ(-2.5).setX(0);
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
        									Main.used1.add(p.getName());
        									Main.count2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {							
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												Main.used1.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Right");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/1f964dcff930faa47d6f9d78a413d0e0a93ecd4e9f36eb58426fc8bdbb2aed7\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(5, playerskull);       						     			
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");       						      					
        												Bukkit.getScheduler().cancelTask(Main.count2);   	
        											}          				
        											time--;      
        											return;
        										}       	      	
        									}   	        				   	        				
        									,0, 20);   	  		
        									time = 60;  //Facing West calculation N
        								} else { if (90.5 <= rot && rot < 180.5) {																//tests for rotation 
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setX(2.5).setZ(0);			//executes boost
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);				 
        									Main.used1.add(p.getName());		
				
        									Main.count2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {						
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												Main.used1.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Right");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/1f964dcff930faa47d6f9d78a413d0e0a93ecd4e9f36eb58426fc8bdbb2aed7\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(5, playerskull);				
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
        												Bukkit.getScheduler().cancelTask(Main.count2);              			            								        					 
        											}          				
        											time--;      
        											return;
        										}       	      	
        									}   	        				   	        				
        									,0, 20);         
	       		
        									time = 60;
       	             
        								} else { if (180.5 <= rot && rot <270.5) {
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setZ(2.5).setX(0);
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
        									Main.used1.add(p.getName());
				
        									Main.count2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {							
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												Main.used1.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Right");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/1f964dcff930faa47d6f9d78a413d0e0a93ecd4e9f36eb58426fc8bdbb2aed7\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(5, playerskull);	   			
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
        												Bukkit.getScheduler().cancelTask(Main.count2);              			            								        					 
        											}          				
        											time--;      
        											return;
        										}       	      	
        									}   	        				   	        				
        									,0, 20);             
		       			
        									time = 60;
       	        
        								} else { if (270.5 <= rot && rot < 360.0) {
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setX(-2.5).setZ(0);
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
			  
        									Main.used1.add(p.getName());

        									Main.count2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {						
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												for(int i = 5; i < p.getInventory().getSize(); i++){
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
        												Main.used1.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Right");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/1f964dcff930faa47d6f9d78a413d0e0a93ecd4e9f36eb58426fc8bdbb2aed7\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(5, playerskull);	   			
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
        												Bukkit.getScheduler().cancelTask(Main.count2);              			            								        					 
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
	
		}