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

    List<Integer> getIntUnlocked();
    void setIntUnlocked(List<Integer> swords);

    boolean isRefreshing();
    void makeRefreshing();
    boolean stopRefreshing();

    int getSwordsBought();
    void setSwordsBought(int amount);
    void increaseSwordsBought();
}
