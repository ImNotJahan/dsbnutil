package imnotjahan.mod.dsbnutil.capabilities.name;

import imnotjahan.mod.dsbnutil.util.events.ForgeClientEvents;
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

    @Override
    public List<Integer> getUnlockedy()
    {
        List<Integer> list = new ArrayList<>();
        unlockedSwords.forEach(sword ->
                list.add(ForgeClientEvents.swords.indexOf(sword)));
        return list;
    }

    @Override
    public void setUnlockedy(List<Integer> swords)
    {
        swords.forEach(sword -> unlockedSwords.add(ForgeClientEvents.swords.get(sword)));
    }

    boolean refreshin = false;

    @Override
    public boolean refreshing()
    {
        int refreshina = refreshin ? 1: 0;
        refreshin = true;
        return refreshina == 1;
    }

    @Override
    public boolean stopre()
    {
        refreshin = false;
        return false;
    }
}