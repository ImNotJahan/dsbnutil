package imnotjahan.mod.dsbnutil.networking.message;

import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.ClientPacketHandler;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.network.NetworkEvent;

import java.io.BufferedWriter;
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
    }

    public static NameMessage decode(PacketBuffer buffer)
    {
        INameData data = new NameData();
        data.setName(buffer.readUtf());

        return new NameMessage(data);
    }

    // For when the name is changed on the server
    public static void handle(NameMessage msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleName(msg, ctx)));
        ctx.get().setPacketHandled(true);
    }
}
