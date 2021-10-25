package imnotjahan.mod.dsbnutil.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.capabilities.world.WorldProvider;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import imnotjahan.mod.dsbnutil.networking.message.ClientNameMessage;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import imnotjahan.mod.dsbnutil.util.events.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;

import java.util.List;

public class StuffScreen extends Screen
{
    public StuffScreen()
    {
        super(new StringTextComponent("stuff"));
        nameData = ClientEvents.GetMinecraft().player.getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new);
    }

    INameData nameData;
    TextFieldWidget firstNameInput;

    @Override
    public void init(Minecraft p_231158_1_, int p_231158_2_, int p_231158_3_)
    {
        super.init(p_231158_1_, p_231158_2_, p_231158_3_);

        firstNameInput = new TextFieldWidget(font, width / 2 - 50, height - 56, 100, 20,
                new StringTextComponent("First Name"));

        firstNameInput.setMaxLength(32);

        this.addWidget(firstNameInput);
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_)
    {
        renderDirtBackground(0);

        drawCenteredString(stack, font, new StringTextComponent("Your name is " +
                minecraft.player.getCapability(NameProvider.STATUS_CAP)
                        .orElseThrow(ArithmeticException::new)
                        .getName()), width/2, 8, 0xffffff);

        if(minecraft.player.hasPermissions(3))
        {
            List<String> deaths = minecraft.player.getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP)
                    .orElseThrow(ArithmeticException::new).getDeathData();

            for(int k = 0; k < deaths.size(); k++)
            {
                drawCenteredString(stack, font, new StringTextComponent(deaths.get(k)), width / 2,
                        24 + 16 * k, 0xffffff);
            }
        }

        String splitName[] = nameData.getName().split(" ");
        String lastName = splitName.length > 1 ? splitName[1] : "";

        nameData.setName((firstNameInput.getValue().equals("") ? splitName[0] : firstNameInput.getValue()) + " " + lastName);
        PacketHandler.CTOS.sendToServer(new ClientNameMessage(nameData));

        Button nameButton = new Button(width / 2 - 50, height - 28,
                100, 20, new StringTextComponent("Reroll Last Name"), (a) ->
        {
            if(minecraft.player.experienceLevel >= 5)
            {
                setRandomName(a);
                minecraft.player.experienceLevel -= 5;
            }
        });

        nameButton.active = minecraft.player.experienceLevel >= 5;

        addButton(nameButton);

        firstNameInput.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }

    void setRandomName(Button something)
    {
        nameData.setName(nameData.getName().split(" ")[0] + " " + getRandomName());

        PacketHandler.CTOS.sendToServer(new ClientNameMessage(nameData));
    }

    String getRandomName()
    {
        List<String> names = minecraft.player.getCommandSenderWorld().getCapability(WorldProvider.STATUS_CAP)
                .orElseThrow(ArithmeticException::new).getNames();

        return names.get((int)(Math.random() * names.size()));
    }
}
