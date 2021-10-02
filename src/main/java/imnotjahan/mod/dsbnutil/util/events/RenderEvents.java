package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OutlineLayerBuffer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class RenderEvents
{
    /*@SubscribeEvent
    public static void onRenderLivingEvent(RenderLivingEvent.Pre<LivingEntity, EntityModel<LivingEntity>> event)
    {
        if(false)
        {
            event.getEntity().setGlowing(true);

            OutlineLayerBuffer buf = Minecraft.getInstance().renderBuffers().outlineBufferSource();
            buf.setColor(40, 40, 40, 255);
        }
    }*/
}
