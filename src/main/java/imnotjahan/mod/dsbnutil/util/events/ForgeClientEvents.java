package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.gui.StuffScreen;
import imnotjahan.mod.dsbnutil.gui.SwordScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.CreativeScreen;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ForgeClientEvents
{
    @SubscribeEvent
    public static void onClientTickEvent(TickEvent.ClientTickEvent event)
    {
        if(event.phase == TickEvent.Phase.START && event.side == LogicalSide.CLIENT)
        {
            if(ClientEvents.NAME.isDown()) Minecraft.getInstance().setScreen(new StuffScreen());
            if(ClientEvents.SWORDS.isDown()) Minecraft.getInstance().setScreen(new SwordScreen());
        }
    }

    @SubscribeEvent
    public static void stopSwordUse(PlayerInteractEvent.RightClickItem event)
    {
        ResourceLocation registryName = event.getItemStack().getItem().getRegistryName();
        if(registryName.getNamespace().equals("kimetsunoyaiba") && event.getItemStack().getItem() instanceof SwordItem)
        {
            if(!(event.getEntity().getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new).getUnlocked()
                    .contains(registryName.toString()))) event.setCanceled(true);
        }
    }
}