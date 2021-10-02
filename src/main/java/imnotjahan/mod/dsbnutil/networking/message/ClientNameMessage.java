package imnotjahan.mod.dsbnutil.networking.message;

import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.ClientPacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class ClientNameMessage
{
    public INameData data;

    public ClientNameMessage()
    {
        data = new NameData();
    }

    public ClientNameMessage(INameData data)
    {
        this.data = data;
    }

    public static void encode(ClientNameMessage message, PacketBuffer buffer)
    {
        buffer.writeUtf(message.data.getName());

        List<String> swords = message.data.getUnlocked();
        buffer.writeInt(swords.size());

        swords.forEach(buffer::writeUtf);
    }

    public static ClientNameMessage decode(PacketBuffer buffer)
    {
        INameData data = new NameData();
        data.setName(buffer.readUtf());

        for(int k = 0; k < buffer.readInt(); k++) data.unlock(buffer.readUtf());

        return new ClientNameMessage(data);
    }

    public static void handle(ClientNameMessage msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ServerPlayerEntity sender = ctx.get().getSender();

            if(sender == null) return;

            INameData status = sender.getCapability(NameProvider.STATUS_CAP, NameData.capSide)
                    .orElseThrow(ArithmeticException::new);

            status.setName(msg.data.getName());
            msg.data.getUnlocked().forEach(sword -> status.unlock(sword));
        });
        ctx.get().setPacketHandled(true);
    }
}
