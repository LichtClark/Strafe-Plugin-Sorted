package eu.clark.main;
import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import eu.clark.cmd.Strafe;
import eu.clark.cmd.drop;
import eu.clark.give.backstrafeg;
import eu.clark.give.leftstrafeg;
import eu.clark.give.rightstrafeg;
import eu.clark.strafe.back;
import eu.clark.strafe.left;
import eu.clark.strafe.right;


public class Main extends JavaPlugin {

	public static int count;
	public static int count1;
	public static int count2;
	public static ArrayList<String> used = new ArrayList<>();
	public static ArrayList<String> used2 = new ArrayList<>();
	public static ArrayList<String> used1 = new ArrayList<>();
	
	private static Main Instance ;
	public Main() {		
		Instance=this;			}
	public static Main getInstance() {
		return Instance;			}
	public static Main main;

	@Override
	public void onEnable() {
		main = this;
		new drop(this);
		 new leftstrafeg(this);
		  new left(this);
		  new backstrafeg(this);
		  new back(this);  
		  new rightstrafeg(this);
		  new right(this);
		System.out.println("[Strafe] Plugin is now loaded!");
		registerCommands();
		
	}

	@Override
	public void onDisable() {
		System.out.println("[Strafe] Plugin us now deloaded!");
			
	}
	
	private void registerCommands() {
		getCommand("strafereset").setExecutor(new Strafe());
		
		}
	
	}
