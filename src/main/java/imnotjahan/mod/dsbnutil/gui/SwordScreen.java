package imnotjahan.mod.dsbnutil.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import imnotjahan.mod.dsbnutil.networking.PacketHandler;
import imnotjahan.mod.dsbnutil.networking.message.ClientNameMessage;
import imnotjahan.mod.dsbnutil.networking.message.NameMessage;
import imnotjahan.mod.dsbnutil.util.events.ClientEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fml.network.PacketDistributor;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class SwordScreen extends Screen
{
    INameData cap;
    Minecraft mc;

    public SwordScreen()
    {
        super(new StringTextComponent("sword screen"));

        if(minecraft == null)
        {
            mc = ClientEvents.GetMinecraft();
        } else
        {
            mc = minecraft;
        }

        cap = mc.player.getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new);
    }

    final List<String> swords = new ArrayList<String>()
    {{
        add("kimetsunoyaiba:nichirinsword_kanawo");
        add("kimetsunoyaiba:nichirinsword_mist");
        add("kimetsunoyaiba:nichirinsword_bamboo");
        add("kimetsunoyaiba:nichirinsword_kanae");
        add("kimetsunoyaiba:nichirinsword_thunder");
        add("kimetsunoyaiba:nichirinsword_iguro");
        add("kimetsunoyaiba:nichirinsword_genya");
        add("kimetsunoyaiba:nichirinsword_water");
        add("kimetsunoyaiba:nichirinsword_tokito");
        add("kimetsunoyaiba:nichirinsword_inosuke");
        add("kimetsunoyaiba:nichirinsword_senior");
        add("kimetsunoyaiba:nichirinsword_shinazugawa");
        add("kimetsunoyaiba:nichirinsword_senior_2");
        add("kimetsunoyaiba:nichirinsword_zenitsu");
        add("kimetsunoyaiba:nichirinsword_kanroji");
        add("kimetsunoyaiba:nichirinsword_flame");
        add("kimetsunoyaiba:nichirinsword_cherry_blossom");
        add("kimetsunoyaiba:nichirinsword_black");
        add("kimetsunoyaiba:nichirinsword_wind");
        add("kimetsunoyaiba:nichirinsword_kaigaku");
        add("kimetsunoyaiba:nichirinsword_uzui");
        add("kimetsunoyaiba:nichirinsword_rengoku");
        add("kimetsunoyaiba:nichirinsword_tomioka");
        add("kimetsunoyaiba:nichirinsword_kocho");
        add("kimetsunoyaiba:nichirinsword_bamboo_2");
    }};

    final List<String> arts = new ArrayList<String>()
    {{
        add("kimetsunoyaiba:blooddemonart_nezuko");
        add("kimetsunoyaiba:blooddemonart_yahaba");
        add("kimetsunoyaiba:kemari");
        add("kimetsunoyaiba:drum");
        add("kimetsunoyaiba:blooddemonart_kamanue");
        add("kimetsunoyaiba:blooddemonart_rui");
        add("kimetsunoyaiba:blooddemonart_rui_sister");
        add("kimetsunoyaiba:rifle");
        add("kimetsunoyaiba:minigun");
        add("kimetsunoyaiba:sword_hairo");
        add("kimetsunoyaiba:blooddemonart_enmu");
        add("kimetsunoyaiba:chigama");
        add("kimetsunoyaiba:blooddemonart_gyokko");
        add("kimetsunoyaiba:khakkhara");
        add("kimetsunoyaiba:spear");
        add("kimetsunoyaiba:urogi_hand");
        add("kimetsunoyaiba:tengu_handfan");
        add("kimetsunoyaiba:blooddemonart_zohakuten");
        add("kimetsunoyaiba:blooddemonart_nakime");
        add("kimetsunoyaiba:blooddemonart_akaza");
        add("kimetsunoyaiba:handfan");
    }};

    double scrollOffsets = 0;
    int integerScrollOffsets = 0;
    float speed = 1000;
    int menu = 0;

    @Override
    public boolean mouseDragged(double p_231045_1_, double p_231045_3_, int delta, double p_231045_6_, double p_231045_8_)
    {
        scrollOffsets += delta * speed;
        integerScrollOffsets = (int) scrollOffsets;

        return true;
    }

    @Override
    public void render(MatrixStack stack, int p_230430_2_, int p_230430_3_, float p_230430_4_)
    {
        cap = mc.player.getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new);
        if(cap == null) return;
        if(mc.player == null) return;

        buttons.clear();

        renderDirtBackground(0);

        int buttonsHorizontally = (int) Math.floor(width / 150);

        final List<String> menuButtons;

        switch(menu)
        {
            case 0:
                menuButtons = swords;
                break;

            case 1:
                menuButtons = arts;
                break;

            default:
                menu = 0;
                menuButtons = swords;
        }

        for(int k = 0; k < menuButtons.size(); k++)
        {
            String menuButton = menuButtons.get(k);
            String prettyName = WordUtils.capitalize(menuButton.split(":")[1].replace('_', ' '));

            if (!cap.getUnlocked().contains(menuButton))
            {
                Button button = new Button((buttons.size() % buttonsHorizontally) * 150,
                        ((int) Math.floor(buttons.size() / buttonsHorizontally) * 20),
                        150, 20,
                        new StringTextComponent(prettyName), a ->
                {
                    if(mc.player.experienceLevel >= 30)
                    {
                        mc.player.experienceLevel -= 30;
                        cap.unlock(menuButton);
                        cap.refreshing();
                        PacketHandler.CTOS.sendToServer(new ClientNameMessage(cap));
                        cap.stopre();
                    }
                });
                button.active = mc.player.experienceLevel >= 30;

                addButton(button);
            }
        }

        Button button = new Button(0, height - 20,
                75, 20,
                new StringTextComponent("SWITCH"), a -> menu++);
        addButton(button);

        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }
}