package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid= Main.MODID)
public class Events
{
    @SubscribeEvent
    public static void playerDeath(LivingDeathEvent event)
    {
        if(!(event.getEntity() instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) event.getEntity();

        IWorldData data = player.getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP).orElseThrow(ArithmeticException::new);
        data.addDeathData(player.getDisplayName().getString() + " died at <" + (int)player.position().x + ", "
                + (int)player.position().y + ", " + (int)player.position().z + "> from " + event.getSource().msgId);

        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), new NameMessage(
                player.getCapability(NameProvider.STATUS_CAP, NameData.capSide).orElseThrow(ArithmeticException::new)));

        if(data.isPermadeath()) player.setGameMode(GameType.SPECTATOR);
    }

    @SubscribeEvent
    public static void attachWorldCapability(AttachCapabilitiesEvent<World> event)
    {
        event.addCapability(new ResourceLocation(Main.MODID, "world"),
                new WorldProvider());
    }

    @SubscribeEvent
    public static void attachPlayerCapability(AttachCapabilitiesEvent<Entity> event)
    {
        if(!(event.getObject() instanceof PlayerEntity)) return;

        event.addCapability(new ResourceLocation(Main.MODID, "name"),
                new NameProvider());
    }

    @SubscribeEvent
    public static void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) event.getPlayer();
        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new NameMessage(
                player.getCapability(NameProvider.STATUS_CAP, NameData.capSide).orElseThrow(ArithmeticException::new)));
    }
}
