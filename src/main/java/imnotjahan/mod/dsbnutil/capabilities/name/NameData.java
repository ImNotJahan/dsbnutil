package imnotjahan.mod.dsbnutil.capabilities.name;
import net.minecraft.util.Direction;

import java.util.ArrayList;
import java.util.List;

public class NameData implements INameData
{
    public static final Direction capSide = Direction.UP;

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

    private List<String> unlockedSwords = new ArrayList<>();

    @Override
    public List<String> getUnlocked()
    {
        return unlockedSwords;
    }

    @Override
    public void setUnlocked(List<String> swords)
    {
        unlockedSwords = swords;
    }

    @Override
    public void unlock(String sword)
    {
        unlockedSwords.add(sword);
    }
}