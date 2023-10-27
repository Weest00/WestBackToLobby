package br.com.west.backtolobby.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;

public class ItemBuilder {
    public static ItemStack add(String owner, String title, String... lore) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1);
        item.setDurability((short) 3);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(title);
        skull.setOwner(owner);
        skull.setLore(Arrays.asList(lore));
        item.setItemMeta((ItemMeta) skull);
        return item;

    }

    public static ItemStack add(Material m, String title, String... lore) {
        ItemStack item = new ItemStack(m);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Arrays.asList(lore));
        meta.setDisplayName(title);
        item.setItemMeta(meta);
        return item;
    }

    public static ItemStack addSkull(String url, String title, String... lore) {
        ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        if (url == null || url.isEmpty())
            return skull;
        SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
        skullMeta.setDisplayName(title);
        skullMeta.setLore(Arrays.asList(lore));
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64
                .encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[]{url}).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
        Field profileField = null;
        try {
            profileField = skullMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        profileField.setAccessible(true);
        try {
            profileField.set(skullMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        skull.setItemMeta((ItemMeta) skullMeta);
        return skull;
    }
}
