package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import imnotjahan.mod.dsbnutil.commands.death.ClearDeaths;
import imnotjahan.mod.dsbnutil.commands.name.*;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = Main.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents
{
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event)
    {
        new ClearDeaths(event.getDispatcher());
        new GetName(event.getDispatcher());
        new GetNameList(event.getDispatcher());
        new AddName(event.getDispatcher());
        new SetName(event.getDispatcher());
        new Permadeath(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }
}
