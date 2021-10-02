package imnotjahan.mod.dsbnutil.networking.message;

import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.networking.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class WorldMessage
{
    public IWorldData data;

    public WorldMessage()
    {
        data = new WorldData();
    }

    public WorldMessage(IWorldData data)
    {
        this.data = data;
    }

    public static void encode(WorldMessage message, PacketBuffer buffer)
    {
        IWorldData data = message.data;

        buffer.writeInt(data.getDeathData().size());
        for(int k = 0; k < data.getDeathData().size(); k++)
        {
            buffer.writeUtf(data.getDeathData().get(k));
        }

        buffer.writeInt(data.getNames().size());
        for(int k = 0; k < data.getNames().size(); k++)
        {
            buffer.writeUtf(data.getNames().get(k));
        }

        buffer.writeBoolean(data.isPermadeath());
    }

    public static WorldMessage decode(PacketBuffer buffer)
    {
        IWorldData data = new WorldData();

        List<String> deaths = new ArrayList<>();
        for(int k = 0; k < buffer.readInt(); k++)
        {
            deaths.add(buffer.readUtf());
        }

        data.setDeathData(deaths);

        for(int k = 0; k < buffer.readInt(); k++)
        {
            data.clearNames();
            data.addName(buffer.readUtf());
        }

        data.setPermadeath(buffer.readBoolean());

        return new WorldMessage(data);
    }

    // For when the name is changed on the server
    public static void handle(WorldMessage msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
                DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handleWorld(msg, ctx)));
        ctx.get().setPacketHandled(true);
    }
}
