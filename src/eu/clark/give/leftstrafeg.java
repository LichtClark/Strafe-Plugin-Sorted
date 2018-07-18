package eu.clark.give;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import eu.clark.main.Main;

public class leftstrafeg implements Listener {
	
	private Object playerSkullTexture;

	public leftstrafeg(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void join(PlayerJoinEvent event) {			
		Player p = event.getPlayer();		
		p.getInventory().clear();
		p.setGameMode(GameMode.ADVENTURE);
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
		
		Bukkit.getScheduler().cancelTask(Main.count);
		Main.used.remove(p.getName());		

		}
		
	}

		
	
	   
	