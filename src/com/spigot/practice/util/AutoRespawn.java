package com.spigot.practice.util;

import com.spigot.practice.Practice;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class AutoRespawn {

    public static void autoRespawn(final EntityDeathEvent e){
        new BukkitRunnable() {
            public void run() {
                try {
                    Object nmsPlayer = e.getEntity().getClass().getMethod("getHandle").invoke(e.getEntity());
                    Object con = nmsPlayer.getClass().getDeclaredField("playerConnection").get(nmsPlayer);

                    Class< ? > EntityPlayer = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EntityPlayer");

                    Field minecraftServer = con.getClass().getDeclaredField("minecraftServer");
                    minecraftServer.setAccessible(true);
                    Object mcserver = minecraftServer.get(con);

                    Object playerlist = mcserver.getClass().getDeclaredMethod("getPlayerList").invoke(mcserver);
                    Method moveToWorld = playerlist.getClass().getMethod("moveToWorld" , EntityPlayer , int.class , boolean.class);
                    moveToWorld.invoke(playerlist , nmsPlayer , 0 , false);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }.runTaskLater(Practice.getInstance() , 2);
    }
}
