package imnotjahan.mod.dsbnutil.networking.message;

import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.ClientPacketHandler;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ClientNameMessage
{
    public INameData data;

    public boolean stuff;
    public int a = 0;

    public ClientNameMessage(INameData data, boolean thing)
    {
        this.data = data;
        this.stuff = true;
    }

    public ClientNameMessage(INameData data, int thing)
    {
        this.data = data;
        a = thing;
        stuff = false;
    }


    public ClientNameMessage(INameData data)
    {
        this.data = data;
        stuff = false;
    }

    public static void encode(ClientNameMessage message, PacketBuffer buffer)
    {
        buffer.writeUtf(message.data.getName().isEmpty() ? "Filler Name" : message.data.getName());

        List<Integer> swords = message.data.getUnlockedy();
        Integer[] array = swords.stream().toArray(Integer[]::new);
        int[] arr = new int[array.length];

        for(int k = 0; k < arr.length; k++)
        {
            arr[k] = array[k];
        }

        buffer.writeVarIntArray(arr);

        buffer.writeBoolean(message.data.refreshing());
    }

    public static ClientNameMessage decode(PacketBuffer buffer)
    {
        INameData data = new NameData();
        data.setName(buffer.readUtf());

        int[] buf = buffer.readVarIntArray();
        List<Integer> fuckyou = new ArrayList<>();

        for(int k = 0; k < buf.length; k++)
        {
            fuckyou.add(buf[k]);
        }

        data.setUnlockedy(fuckyou);

        if(buffer.readBoolean()) data.refreshing();

        return new ClientNameMessage(data);
    }

    public static void handle(ClientNameMessage msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ServerPlayerEntity sender = ctx.get().getSender();
            if(sender == null) return;

            if(msg.a == 1)
            {
                PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> sender), new NameMessage(
                        sender.getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new)));
            } else
            {
                INameData status = sender.getCapability(NameProvider.STATUS_CAP, NameData.capSide)
                        .orElseThrow(ArithmeticException::new);

                status.setName(msg.data.getName());
                status.setUnlocked(msg.data.getUnlocked());

                if (msg.data.refreshing())
                {
                    sender.setExperienceLevels(sender.experienceLevel - 30);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
