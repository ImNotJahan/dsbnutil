package imnotjahan.mod.dsbnutil.networking.message;

import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.networking.ClientPacketHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        buffer.writeUtf(message.data.getName().isEmpty() ? "Filler Name" : message.data.getName());

        List<Integer> swords = message.data.getIntUnlocked();
        Integer[] array = swords.stream().toArray(Integer[]::new);
        int[] arr = new int[array.length];

        for(int k = 0; k < arr.length; k++)
        {
            arr[k] = array[k];
        }

        buffer.writeVarIntArray(arr);
    }

    public static NameMessage decode(PacketBuffer buffer)
    {
        INameData data = new NameData();
        data.setName(buffer.readUtf());
        int[] buf = buffer.readVarIntArray();
        List<Integer> fuckyou = new ArrayList<>();

        for(int k = 0; k < buf.length; k++)
        {
            fuckyou.add(buf[k]);
        }

        data.setIntUnlocked(fuckyou);
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
