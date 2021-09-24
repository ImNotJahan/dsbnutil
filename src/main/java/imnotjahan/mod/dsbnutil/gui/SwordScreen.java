package imnotjahan.mod.dsbnutil.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import imnotjahan.mod.dsbnutil.capabilities.name.INameData;
import imnotjahan.mod.dsbnutil.capabilities.name.NameProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.text.StringTextComponent;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;
import java.util.List;

public class SwordScreen extends Screen
{
    INameData cap;

    public SwordScreen()
    {
        super(new StringTextComponent("sword screen"));
        cap = minecraft.player.getCapability(NameProvider.STATUS_CAP).orElseThrow(ArithmeticException::new);
    }

    List<String> swords = new ArrayList<String>()
    {{
        add("kimetsunoyaiba:nichirinsword_kanawo");
        add("kimetsunoyaiba:nichirinsword_mist");
        add("kimetsunoyaiba:nichirinsword_bamboo");
        add("kimetsunoyaiba:nichirinsword_kanae");
        add("kimetsunoyaiba:nichirinsword_thunder");
        add("kimetsunoyaiba:nichirinsword_iguro");
        add("kimetsunoyaiba:nichirinsword_genya");
        add("kimetsunoyaiba:nichirinsword_water");
        add("kimetsunoyaiba:nichirinsword_yoriichi");
        add("kimetsunoyaiba:nichirinsword_tokito");
        add("kimetsunoyaiba:nichirinsword_inosuke");
        add("kimetsunoyaiba:nichirinsword_senior");
        add("kimetsunoyaiba:nichirinsword_tanjiro");
        add("kimetsunoyaiba:nichirinsword_shinazugawa");
        add("kimetsunoyaiba:nichirinsword_tanjiro_2");
        add("kimetsunoyaiba:nichirinsword");
        add("kimetsunoyaiba:nichirinswordmoon");
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
        add("kimetsunoyaiba:nichirinsword_senior_super");
        add("kimetsunoyaiba:nichirinsword_himejima_2");
        add("kimetsunoyaiba:nichirinsword_himejima_1");
        add("kimetsunoyaiba:nichirinsword_golden");
        add("kimetsunoyaiba:nichirinsword_kocho");
        add("kimetsunoyaiba:nichirinsword_bamboo_2");
    }};

    double scrollOffsets = 0;
    int integerScrollOffsets = 0;
    float speed = 1000;

    int screen = 0;

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
        buttons.clear();

        renderDirtBackground(0);

        int buttonsHorizontally = (int) Math.floor(width / 150);

        for(int k = 0; k < swords.size(); k++)
        {
            String prettyName = WordUtils.capitalize(swords.get(k).split(":")[1].replace('_', ' '));

            if(cap.getUnlocked().contains(swords.get(k))) return;

            Button button = new Button((buttons.size() % buttonsHorizontally) * 150,
                    ((int) Math.floor(buttons.size() / buttonsHorizontally) * 20),
                    150, 20,
                    new StringTextComponent(prettyName), a ->
            {
                minecraft.player.experienceLevel -= 30;
                cap.unlock(swords.get(buttons.size()));
            });
            button.active = minecraft.player.experienceLevel >= 30;

            addButton(button);
        }

        super.render(stack, p_230430_2_, p_230430_3_, p_230430_4_);
    }
}
