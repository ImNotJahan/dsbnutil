package imnotjahan.mod.dsbnutil.capabilities.name;

import java.util.List;

public interface INameData
{
    String getName();
    String getRandomName();

    boolean setRandomName();
    boolean setName(String name);

    List<String> getUnlocked();
    void setUnlocked(List<String> swords);
    void unlock(String sword);

    List<Integer> getUnlockedy();
    void setUnlockedy(List<Integer> swords);

    boolean refreshing();
    void makeRefreshing();
    boolean stopre();
}
