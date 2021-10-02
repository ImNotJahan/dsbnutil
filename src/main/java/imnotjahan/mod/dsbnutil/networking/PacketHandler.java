package imnotjahan.mod.dsbnutil.networking;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.networking.message.ClientNameMessage;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import imnotjahan.mod.dsbnutil.networking.message.WorldMessage;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class PacketHandler
{
    private static final String PROTOCOL_VERSION = "69";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Main.MODID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static final SimpleChannel WORLD = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Main.MODID, "world"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static final SimpleChannel CTOS = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Main.MODID, "client"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int id = 0;
    public static void init()
    {
        INSTANCE.registerMessage(0, NameMessage.class, NameMessage::encode, NameMessage::decode, NameMessage::handle);
        CTOS.registerMessage(0, ClientNameMessage.class, ClientNameMessage::encode, ClientNameMessage::decode, ClientNameMessage::handle);
        WORLD.registerMessage(0, WorldMessage.class, WorldMessage::encode, WorldMessage::decode, WorldMessage::handle);
    }
}
