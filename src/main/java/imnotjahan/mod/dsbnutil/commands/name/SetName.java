package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;

public class SetName
{
    public SetName(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("setname").requires((p_198233_0_) ->
                p_198233_0_.hasPermission(3)).then(Commands.argument("name", StringArgumentType.string())
                .executes((command) ->
                        CommandStuff(command.getSource(), command.getArgument("name", String.class)))));
    }

    private int CommandStuff(CommandSource source, String name) throws CommandSyntaxException
    {
        INameData data = source.getEntity().getCapability(NameProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        data.setName(data.getName().split(" ")[0] + " " + name);

        source.sendSuccess(new StringTextComponent("Set your name to " + name), false);

        return 0;
    }
}
