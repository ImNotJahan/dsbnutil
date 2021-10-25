package imnotjahan.mod.dsbnutil.commands.name;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;

public class AddSword
{
    public AddSword(CommandDispatcher<CommandSource> dispatcher)
    {
        dispatcher.register(Commands.literal("addsword").requires((p_198233_0_) ->
                p_198233_0_.hasPermission(3)).then(Commands.argument("sword", StringArgumentType.string())
                .executes((command) ->
                        CommandStuff(command.getSource(), command.getArgument("sword", String.class)))));
    }

    private int CommandStuff(CommandSource source, String name) throws CommandSyntaxException
    {
        INameData data = source.getEntity().getCapability(NameProvider.STATUS_CAP, NameData.capSide)
                .orElseThrow(ArithmeticException::new);

        data.unlock(name);
        System.out.println(name);
        source.sendSuccess(new StringTextComponent("Granted you the sword " + name), false);

        PacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) source.getEntity()), new NameMessage(data));
        return 0;
    }
}
