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

public class SetEventPos
{
    public SetEventPos(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("seteventpos").requires((p_198233_0_) ->
                        p_198233_0_.hasPermission(3))
                .then(Commands.argument("pos", BlockPosArgument.blockPos()))
                .executes((command) ->
                        CommandStuff(command.getSource(), command.getArgument("pos", BlockPos.class))));
    }

    private int CommandStuff(CommandSource source, BlockPos pos) throws CommandSyntaxException
    {
        IWorldData data = source.getEntity().getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        data.setPDL(pos);

        source.sendSuccess(new StringTextComponent("PD events will now happen at "
                + pos.toString()), false);

        return 0;
    }
}
