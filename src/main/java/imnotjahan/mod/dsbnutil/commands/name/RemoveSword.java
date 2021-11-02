package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.arguments.EntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.List;

public class RemoveSword
{
    public RemoveSword(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("removesword").requires((p_198233_0_) ->
                p_198233_0_.hasPermission(3)).then(Commands.argument("sword", StringArgumentType.string())
                .then(Commands.argument("player", EntityArgument.player())
                .executes((command) ->
                        CommandStuff(command.getSource(), command.getArgument("sword", String.class),
                                command.getArgument("player", EntitySelector.class))))));
    }

    private int CommandStuff(CommandSource source, String name, EntitySelector selector) throws CommandSyntaxException
    {
        ServerPlayerEntity player = selector.findSinglePlayer(source);
        INameData data = player.getCapability(NameProvider.STATUS_CAP, NameData.capSide)
                .orElseThrow(ArithmeticException::new);

        List<String> fixedSwords = data.getUnlocked();
        fixedSwords.remove(name);
        data.setUnlocked(fixedSwords);
        
        source.sendSuccess(new StringTextComponent(
                "Removed the sword " + name + " from " + player.getDisplayName().getString()), false);

        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(
                () -> (ServerPlayerEntity) source.getEntity()), new NameMessage(data));
        return 0;
    }
}
