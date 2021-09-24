package imnotjahan.mod.dsbnutil.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

public class GetEventPos
{
    public GetEventPos(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("geteventpos").requires((p_198233_0_) ->
                        p_198233_0_.hasPermission(3)).executes((command) ->
                        CommandStuff(command.getSource())));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        IWorldData data = source.getEntity().getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        source.sendSuccess(new StringTextComponent("PD events teleport eligible players to "
                + data.getPDL().toString()), false);

        return 0;
    }
}
