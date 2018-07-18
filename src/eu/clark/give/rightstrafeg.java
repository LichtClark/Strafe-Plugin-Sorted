package eu.clark.give;

import java.lang.reflect.Field;
import java.util.Base64;
import java.util.UUID;

import org.bukkit.Bukkit;
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

public class rightstrafeg implements Listener {
	
	private Object playerSkullTexture;

	public rightstrafeg(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	
	}
	
	@EventHandler
	public void join(PlayerJoinEvent event) {	
		Player p = event.getPlayer();		
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
		
		Bukkit.getScheduler().cancelTask(Main.count2);
		Main.used2.remove(p.getName());		
				
		p.sendMessage("§8▌ §cStrafePlugin§8│ §7➽ §8[§7beta-version:0.0.9§8] §7Strafes created!");
		
		}

	}



