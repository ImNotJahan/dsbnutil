package imnotjahan.mod.dsbnutil.capabilities.name;
import net.minecraft.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class NameData implements INameData
{
    public static final Direction capSide = Direction.UP;

    private List<String> names = new ArrayList<String>()
    {{
        add("tanjiro");
    }};
    private String name = "Filler Name";


    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public String getRandomName()
    {
        return null;
    }

    @Override
    public boolean setRandomName()
    {
        name = getRandomName();
        return true;
    }

    @Override
    public boolean setName(String name)
    {
        this.name = name;
        return false;
    }
}