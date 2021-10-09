package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
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
    public static final List<String> swords = new ArrayList<String>()
    {{
        // nichirin swords
        add("kimetsunoyaiba:nichirinsword_kanawo");
        add("kimetsunoyaiba:nichirinsword_mist");
        add("kimetsunoyaiba:nichirinsword_bamboo");
        add("kimetsunoyaiba:nichirinsword_kanae");
        add("kimetsunoyaiba:nichirinsword_thunder");
        add("kimetsunoyaiba:nichirinsword_iguro");
        add("kimetsunoyaiba:nichirinsword_genya");
        add("kimetsunoyaiba:nichirinsword_water");
        add("kimetsunoyaiba:nichirinsword_yoriichi");
        add("kimetsunoyaiba:nichirinsword_tokito");
        add("kimetsunoyaiba:nichirinsword_inosuke");
        add("kimetsunoyaiba:nichirinsword_senior");
        add("kimetsunoyaiba:nichirinsword_tanjiro");
        add("kimetsunoyaiba:nichirinsword_shinazugawa");
        add("kimetsunoyaiba:nichirinsword_tanjiro_2");
        add("kimetsunoyaiba:nichirinswordmoon");
        add("kimetsunoyaiba:nichirinsword_senior_2");
        add("kimetsunoyaiba:nichirinsword_zenitsu");
        add("kimetsunoyaiba:nichirinsword_kanroji");
        add("kimetsunoyaiba:nichirinsword_flame");
        add("kimetsunoyaiba:nichirinsword_cherry_blossom");
        add("kimetsunoyaiba:nichirinsword_black");
        add("kimetsunoyaiba:nichirinsword_wind");
        add("kimetsunoyaiba:nichirinsword_kaigaku");
        add("kimetsunoyaiba:nichirinsword_uzui");
        add("kimetsunoyaiba:nichirinsword_rengoku");
        add("kimetsunoyaiba:nichirinsword_tomioka");
        add("kimetsunoyaiba:nichirinsword_senior_super");
        add("kimetsunoyaiba:nichirinsword_himejima_2");
        add("kimetsunoyaiba:nichirinsword_himejima_1");
        add("kimetsunoyaiba:nichirinsword_golden");
        add("kimetsunoyaiba:nichirinsword_kocho");
        add("kimetsunoyaiba:nichirinsword_bamboo_2");

        // demon arts
        add("kimetsunoyaiba:blooddemonart_nezuko");
        add("kimetsunoyaiba:blooddemonart_yahaba");
        add("kimetsunoyaiba:kemari");
        add("kimetsunoyaiba:drum");
        add("kimetsunoyaiba:blooddemonart_kamanue");
        add("kimetsunoyaiba:blooddemonart_rui");
        add("kimetsunoyaiba:blooddemonart_rui_sister");
        add("kimetsunoyaiba:rifle");
        add("kimetsunoyaiba:minigun");
        add("kimetsunoyaiba:sword_hairo");
        add("kimetsunoyaiba:blooddemonart_enmu");
        add("kimetsunoyaiba:chigama");
        add("kimetsunoyaiba:blooddemonart_gyokko");
        add("kimetsunoyaiba:khakkhara");
        add("kimetsunoyaiba:spear");
        add("kimetsunoyaiba:urogi_hand");
        add("kimetsunoyaiba:tengu_handfan");
        add("kimetsunoyaiba:blooddemonart_zohakuten");
        add("kimetsunoyaiba:blooddemonart_nakime");
        add("kimetsunoyaiba:blooddemonart_akaza");
        add("kimetsunoyaiba:handfan");
    }};

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

        if(swords.contains(registryName.toString()))
        {
            if(!(event.getEntity().getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new).getUnlocked()
                    .contains(registryName.toString())))
            {
                event.setCanceled(true);
            }
        }
    }
}