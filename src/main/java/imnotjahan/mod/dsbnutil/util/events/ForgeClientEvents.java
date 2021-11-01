package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.gui.StuffScreen;
import imnotjahan.mod.dsbnutil.gui.SwordScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents
{
    public static boolean transparentWorld = false;

    @SubscribeEvent
    public static void onClientTickEvent(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.START && event.side == LogicalSide.CLIENT)
        {
            if(ClientEvents.NAME.isDown()) Minecraft.getInstance().setScreen(new StuffScreen());
            if(ClientEvents.SWORDS.isDown()) Minecraft.getInstance().setScreen(new SwordScreen());
            if(ClientEvents.TRANSPARENT_WORLD.isDown() &&
                    Minecraft.getInstance().player.hasPermissions(2)) transparentWorld = !transparentWorld;
        }
    }

    @SubscribeEvent
    public static void stopSwordUse(PlayerInteractEvent.RightClickItem event)
    {
        ResourceLocation registryName = event.getItemStack().getItem().getRegistryName();

        if(NameData.lockedSwords.contains(registryName.toString()))
        {
            if(event.getEntity().getDisplayName().getString().equals("GhostyZoomer")) return;

            if(!(event.getEntity().getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new).getUnlocked()
                    .contains(registryName.toString())))
            {
                event.setCanceled(true);
            }
        }
    }
}