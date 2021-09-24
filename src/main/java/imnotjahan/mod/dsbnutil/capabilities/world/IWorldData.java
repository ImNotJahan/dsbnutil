package imnotjahan.mod.dsbnutil.capabilities.world;

import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface IWorldData
{
    List<String> getDeathData();

    void addDeathData(String data);
    void setDeathData(List<String> data);

    void clearNames();
    void setNames(List<String> names);
    void addName(String name);
    List<String> getNames();

    /** PDL = Permadeath Location */
    void setPDL(BlockPos pos);
    BlockPos getPDL();

    boolean isPermadeath();
    void setPermadeath(boolean pd); // pd stands for permadeath
}
