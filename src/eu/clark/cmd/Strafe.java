package eu.clark.cmd;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import eu.clark.main.Main;

public class Strafe implements CommandExecutor {
	
	private Object playerSkullTexture;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage("Invalid Executor.");
			return true;
		
		}
	
		if(args.length == 0) {
			
			start();
			
			Player p = (Player)sender;
			
			p.setGameMode(GameMode.ADVENTURE);
			p.getInventory().clear();
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
        		
        		Main.used.remove(p.getName());		
        		Bukkit.getScheduler().cancelTask(Main.count);
    			
        		ItemStack playerskull1 = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        		SkullMeta meta1 = (SkullMeta) playerskull1.getItemMeta();
        		meta1.setDisplayName("§a§lStrafe Back");
        		GameProfile profile1 = new GameProfile(UUID.randomUUID(), null);
                byte[] encodedData1 = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/9fdfc66314675bd63bc8d2af17c85f2dfcce196e97ecf42b78f93cdef253b23\"}}}", this.playerSkullTexture).getBytes());
                profile1.getProperties().put("textures", new Property("textures", new String(encodedData1)));
                Field profileField1 = null;
                try {
                    profileField1 = meta1.getClass().getDeclaredField("profile");
                    profileField1.setAccessible(true);
                    profileField1.set(meta1, profile1);
                } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                    e1.printStackTrace();
                }
        		playerskull1.setItemMeta(meta1);
        		p.getInventory().setItem(4, playerskull1);
        		
        		Main.used2.remove(p.getName());		
        		Bukkit.getScheduler().cancelTask(Main.count1);
    			
        		ItemStack playerskull2 = new ItemStack(Material.SKULL_ITEM,1,(short) SkullType.PLAYER.ordinal());	
        		SkullMeta meta2 = (SkullMeta) playerskull2.getItemMeta();
        		meta2.setDisplayName("§a§lStrafe Right");
        		GameProfile profile2 = new GameProfile(UUID.randomUUID(), null);
                byte[] encodedData2 = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%shttp://textures.minecraft.net/texture/1f964dcff930faa47d6f9d78a413d0e0a93ecd4e9f36eb58426fc8bdbb2aed7\"}}}", this.playerSkullTexture).getBytes());
                profile2.getProperties().put("textures", new Property("textures", new String(encodedData2)));
                Field profileField2 = null;
                try {
                    profileField2 = meta2.getClass().getDeclaredField("profile");
                    profileField2.setAccessible(true);
                    profileField2.set(meta2, profile2);
                } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e1) {
                    e1.printStackTrace();                 
                }
        		playerskull2.setItemMeta(meta2);
        		p.getInventory().setItem(5, playerskull2);
        		
        		Bukkit.getScheduler().cancelTask(Main.count2);
    			Main.used1.remove(p.getName());		
    			sender.sendMessage("§8▌ §cStrafePlugin§8│ §7§aStrafes got reseted!");
    			
		}else{
			
			sender.sendMessage("§cinvalid exeption!");
    			
        		}
		
		return true;
        	
        	}
	
	private void start() {
				
			}
	
	}