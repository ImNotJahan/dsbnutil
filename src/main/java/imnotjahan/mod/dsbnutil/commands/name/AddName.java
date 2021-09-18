package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class AddName
{
    public AddName(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("addname").requires((p_198233_0_) ->
                p_198233_0_.hasPermission(3)).then(Commands.argument("name", StringArgumentType.string())
                .executes((command) ->
                        CommandStuff(command.getSource(), command.getArgument("name", String.class)))));
    }

    private int CommandStuff(CommandSource source, String name) throws CommandSyntaxException
    {
        IWorldData data = source.getEntity().getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        data.addName(name);

        source.sendSuccess(new StringTextComponent("Added the name " + name), false);

        return 0;
    }
}
