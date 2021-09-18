package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class GetNameList
{
    public GetNameList(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("getnamelist").requires((p_198233_0_) ->
                        p_198233_0_.hasPermission(3))
                .executes((command) ->
                        CommandStuff(command.getSource())));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        IWorldData data = source.getEntity().getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        String names = "";
        for(String name : data.getNames()) names += name + ", ";

        source.sendSuccess(new StringTextComponent(names), false);

        return 0;
    }
}
