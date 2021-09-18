package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.gui.StuffScreen;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
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
        }
    }
}