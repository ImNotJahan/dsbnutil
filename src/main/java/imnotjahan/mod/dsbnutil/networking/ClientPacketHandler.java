package imnotjahan.mod.dsbnutil.networking;

import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class ClientPacketHandler // Only call on the client
{
    public static void handleName(NameMessage msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            ClientPlayerEntity sender = Minecraft.getInstance().player;
            sender.getCapability(NameProvider.STATUS_CAP, NameData.capSide).orElseThrow(ArithmeticException::new)
                    .setName(msg.data.getName());
        });
        ctx.get().setPacketHandled(true);
    }
}
