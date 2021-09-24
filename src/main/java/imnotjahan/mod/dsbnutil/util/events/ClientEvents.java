package imnotjahan.mod.dsbnutil.util.events;

import imnotjahan.mod.dsbnutil.Main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(modid = Main.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents
{
    public static final KeyBinding NAME = new KeyBinding("key.stuff", GLFW.GLFW_KEY_L,
            "key.categories.dsbn");
    public static final KeyBinding SWORDS = new KeyBinding("key.swords", GLFW.GLFW_KEY_P,
            "key.categories.dsbn");

    static
    {
        ClientRegistry.registerKeyBinding(NAME);
        ClientRegistry.registerKeyBinding(SWORDS);
    }
}
