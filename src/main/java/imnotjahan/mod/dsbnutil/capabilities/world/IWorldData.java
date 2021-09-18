package imnotjahan.mod.dsbnutil.capabilities.world;

import java.util.List;

public interface IWorldData
{
    List<String> getDeathData();

    void addDeathData(String data);
    void setDeathData(List<String> data);

    void addName(String name);
    List<String> getNames();

    // What the fuck does this dooooooooo

    boolean isPermadeath();
    void setPermadeath(boolean pd); // pd stands for permadeath
}
