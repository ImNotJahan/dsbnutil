package imnotjahan.mod.dsbnutil.networking;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Main.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;
    public static void init()
    {
        INSTANCE.registerMessage(id++, NameMessage.class, NameMessage::encode, NameMessage::decode, NameMessage::handle);
    }
}
