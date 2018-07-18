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

public class backstrafeg implements Listener {
	
	private Object playerSkullTexture;

	public backstrafeg(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
	}
	
	@EventHandler
	public void join(PlayerJoinEvent event) {	
		Player p = event.getPlayer();		
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
		
		Bukkit.getScheduler().cancelTask(Main.count1);
		Main.used1.remove(p.getName());		
		
		}
	
	}