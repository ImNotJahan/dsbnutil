package imnotjahan.mod.dsbnutil.networking.message;

import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.networking.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class NameMessage
{
    public INameData data;

    public NameMessage()
    {
        data = new NameData();
    }

    public NameMessage(INameData data)
    {
        this.data = data;
    }

    public static void encode(NameMessage message, PacketBuffer buffer)
    {
        buffer.writeUtf(message.data.getName());

        List<String> swords = message.data.getUnlocked();
        buffer.writeInt(swords.size());

        swords.forEach(buffer::writeUtf);
    }

    public static NameMessage decode(PacketBuffer buffer)
    {
        INameData data = new NameData();
        data.setName(buffer.readUtf());

        for(int k = 0; k < buffer.readInt(); k++) data.unlock(buffer.readUtf());

        return new NameMessage(data);
    }

    // For when the name is changed on the server
    public static void handle(NameMessage msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleName(msg, ctx)));
        ctx.get().setPacketHandled(true);
    }
}
