package imnotjahan.mod.dsbnutil.commands.death;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;

public class ClearDeaths
{
    public ClearDeaths(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("cleardeaths").requires((p_198233_0_) ->
                        p_198233_0_.hasPermission(3))
                                .executes((command) ->
                                        CommandStuff(command.getSource())));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        IWorldData data = source.getEntity().getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        data.setDeathData(new ArrayList<>());

        source.sendSuccess(new StringTextComponent("Cleared them all"), false);

        return 0;
    }
}