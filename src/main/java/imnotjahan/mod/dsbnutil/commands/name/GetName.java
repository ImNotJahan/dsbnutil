package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class GetName
{
    public GetName(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("getname").requires((p_198233_0_) ->
                        p_198233_0_.hasPermission(3))
                .executes((command) ->
                        CommandStuff(command.getSource())));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        INameData data = source.getEntity().getCapability(NameProvider.STATUS_CAP, NameData.capSide)
                .orElseThrow(ArithmeticException::new);

        source.sendSuccess(new StringTextComponent(data.getName()), false);

        return 0;
    }
}
