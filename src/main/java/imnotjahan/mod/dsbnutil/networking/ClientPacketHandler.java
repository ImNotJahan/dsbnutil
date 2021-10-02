package imnotjahan.mod.dsbnutil.networking;

import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import imnotjahan.mod.dsbnutil.networking.message.WorldMessage;
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
            sender.getCapability(NameProvider.STATUS_CAP, NameData.capSide).orElseThrow(ArithmeticException::new)
                    .getUnlocked().forEach(sword -> msg.data.unlock(sword));
        });
        ctx.get().setPacketHandled(true);
    }

    public static void handleWorld(WorldMessage msg, Supplier<NetworkEvent.Context> ctx)
    {
        ctx.get().enqueueWork(() ->
        {
            IWorldData data = Minecraft.getInstance().player.getCommandSenderWorld()
                    .getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                    .orElseThrow(ArithmeticException::new);

            data.setPermadeath(msg.data.isPermadeath());
            data.setNames(msg.data.getNames());
            data.setDeathData(msg.data.getDeathData());
            data.setPDL(msg.data.getPDL());
        });
        ctx.get().setPacketHandled(true);
    }
}
