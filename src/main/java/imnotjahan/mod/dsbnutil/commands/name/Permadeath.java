package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.world.IWorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldData;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.GameType;

public class Permadeath
{
    public Permadeath(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("pd").requires((p_198233_0_) ->
                p_198233_0_.hasPermission(3))
                .executes((command) ->
                        CommandStuff(command.getSource())));
    }

    private int CommandStuff(CommandSource source) throws CommandSyntaxException
    {
        IWorldData data = source.getEntity().getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP, WorldData.capSide)
                .orElseThrow(ArithmeticException::new);

        data.setPermadeath(!data.isPermadeath());

        source.sendSuccess(new StringTextComponent("Permadeath: " + data.isPermadeath()), false);

        if(!data.isPermadeath())
        {
            for(PlayerEntity player : source.getEntity().getCommandSenderWorld().players())
            {
                if(player.isSpectator())
                {
                    player.setGameMode(GameType.SURVIVAL);

                    BlockPos pdl = data.getPDL();
                    player.teleportTo(pdl.getX(), pdl.getY(), pdl.getZ());
                }
            }
        }

        return 0;
    }
}
