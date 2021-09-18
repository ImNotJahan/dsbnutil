package imnotjahan.mod.dsbnutil;

import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameStorage;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldStorage;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("dsbnutil")
public class Main
{
    public static final String MODID = "dsbnutil";

    public Main()
    {
        final IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        eventBus.addListener(this::SetupCommon);
    }

    public void SetupCommon(final FMLCommonSetupEvent event)
    {
        CapabilityManager.INSTANCE.register(IWorldData.class, new WorldStorage(), WorldData::new);
        CapabilityManager.INSTANCE.register(INameData.class, new NameStorage(), NameData::new);

        PacketHandler.init();
    }
}
