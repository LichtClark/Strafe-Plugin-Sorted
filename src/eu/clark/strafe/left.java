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


public class left implements Listener {
	
	public Integer time = 60;
	     
	public left(Main main) {
		this.plugin = main;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}	
	
	Plugin plugin;
		
	private Object playerSkullTexture;
	@SuppressWarnings("deprecation")
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
        
        if (!(iteminhand.getItemMeta().getDisplayName().equalsIgnoreCase("§a§lStrafe Left"))) { //checkt für den displaynamen
            return;               
        }       
        
        	if (event.getItem() != null){
        		if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
        			if (event.getItem() == null){
        			} else {
        				if(event.getItem() != null){
        					if ((event.getAction() == Action.RIGHT_CLICK_AIR) || (event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
        						if(event.getItem() == null);
            
        						if(!Main.used.contains(p.getName()))  {
        							ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,60,(short) SkullType.PLAYER.ordinal());	
        							SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        							meta.setDisplayName("§a§lStrafe Left");
        							GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        							byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/86d85fc4bc6c4ce97e4bbf9ce244ea6d1cc5037a6db9f89aab9a29c27bf060\"}}}", this.playerSkullTexture).getBytes());
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
        							p.getInventory().setItem(3, playerskull);
         
        							double rot = (p.getLocation().getYaw() - 45) % 360; {
        								if (rot < 0) {
        									rot += 360.0;
        								}   
        								if (-45.5 <= rot && rot < 90.5) {
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setZ(2.5).setX(0);
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
        									Main.used.add(p.getName());   	

        									Main.count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {						
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												Main.used.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Left");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/d8e372fa6a4564ec29059659a35a36fedad69fbed5e0596e7a217b8f6e11c\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(3, playerskull);   						     			
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");       						      					
        												Bukkit.getScheduler().cancelTask(Main.count);   	
        											}          				
        											time--;      
        											return;
        										}       	      	
        									}   	        				   	        				
        									,0, 20);   	
        									
        									time = 60;
 
        								} else { if (90.5 <= rot && rot < 180.5) {																//tests for rotation 
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setX(-2.5).setZ(0);			//executes boost
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
        									Main.used.add(p.getName());			   
       			
        									Main.count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {					
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												Main.used.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Left");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/d8e372fa6a4564ec29059659a35a36fedad69fbed5e0596e7a217b8f6e11c\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(3, playerskull);   					
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
        												Bukkit.getScheduler().cancelTask(Main.count);              			            								        					 
        											}          				
        											time--;      
        											return;
        										}       	      	
        									}   	        				   	        				
        									,0, 20);     
	
        									time = 60;
       			
        								} else { if (180.5 <= rot && rot <270.5) {
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setZ(-2.5).setX(0);
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
        									Main.used.add(p.getName()); 			   
       			
        									Main.count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {						
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												Main.used.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Left");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/d8e372fa6a4564ec29059659a35a36fedad69fbed5e0596e7a217b8f6e11c\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(3, playerskull);   			   			
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
        												Bukkit.getScheduler().cancelTask(Main.count);              			            								        					 
        											}          				
        											time--;      
        											return;
        										}       	      	
        									}   	        				   	        				
        									,0, 20);          
       			
        									time = 60;
       			
        								} else { if (270.5 <= rot && rot < 360.0) {
        									org.bukkit.util.Vector v1 = p.getLocation().getDirection().setY(0.3).setX(2.5).setZ(0);
        									p.setVelocity(v1);
        									p.playSound(p.getLocation(), Sound.CAT_HISS, 1, 1);
        									p.playEffect(p.getLocation(), Effect.HAPPY_VILLAGER, 2);
              	     	           
        									Main.used.add(p.getName());
            
        									Main.count = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {       	//#   
        										private Object playerSkullTexture;

        										@Override       	      		     	      		
        										public void run() {
        											if (time == 60) {
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §bStrafe used. §3Aviable again§f " + time + " §3seconds!");	
        											} else if(time <= 59 && time > 1) {					
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												for(int i = 3; i < p.getInventory().getSize(); i++){
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
        												Main.used.remove(p.getName());									
        												ItemStack playerskull = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        												SkullMeta meta = (SkullMeta) playerskull.getItemMeta();
        												meta.setDisplayName("§a§lStrafe Left");
        												GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        												byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/d8e372fa6a4564ec29059659a35a36fedad69fbed5e0596e7a217b8f6e11c\"}}}", this.playerSkullTexture).getBytes());
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
        												p.getInventory().setItem(3, playerskull);   			   			
        												p.sendMessage("§8▌ §cStrafePlugin§8│ §aStrafe aviable!");
        												Bukkit.getScheduler().cancelTask(Main.count);              			            								        					 
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