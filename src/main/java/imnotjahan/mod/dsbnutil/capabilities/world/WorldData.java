package imnotjahan.mod.dsbnutil.capabilities.world;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class WorldData implements IWorldData
{
    public static final Direction capSide = Direction.UP;

    private List<String> deathData = new ArrayList<>();
    private boolean isPermadeath = false;

    BlockPos pdl = new BlockPos(0, 100, 0);

    List<String> names = new ArrayList<String>()
    {{
        add("Ubuyashiki");
        add("Tomioka");
        add("Megumin");
        add("Kanroji");
        add("Iguro");
        add("Shinazugawa");
        add("Himejima");
        add("Uzui");
        add("Tokito");
        add("Kocho");
        add("Rengoko");
        add("Kamado");
        add("Jin-Woo");
        add("Tsuyuri");
        add("Agatsuma");
        add("Hashibira");
        add("Shinazugawa");
        add("Wallenstein");
        add("Haganezuka");
        add("Kanamori");
        add("Saluja");
        add("Sengoku");
        add("Shinomiya");
        add("Emiya");
        add("Freed");
        add("Araragi");
        add("Oshino");
        add("Itadori");
        add("Satoru");
        add("Leywins");
        add("Tempest");
        add("Monkey.D");
        add("Midoriya");
        add("Ayanokoji");
        add("Tokisaki");
        add("Vermillion");
        add("Sarashina");
        add("Toudou");
        add("Arima");
        add("Bakugo");
        add("Mikoto");
        add("Ackerman");
        add("Kyrielight");
        add("Saten");
        add("Yaoyorozu");
        add("Marvell");
        add("dragnir");
        add("Uzumaki");
        add("Yggdmillennia");
        add("Yamanaka");
        add("Ashtoret");
        add("Kamado");
        add("Ichinose");
        add("Argento");
        add("Hiryute");
        add("Kashiwagi");
        add("Matou");
        add("Shirogane");
        add("Tohsaka");
        add("Pendragon");
        add("Uchiha");
        add("Hyuga");
        add("Setagaya");
        add("Alberona");
        add("Roronoa");
        add("Kirigaya");
        add("Gremory");
        add("Nico");
        add("Hatsume");
        add("Izanami");
        add("Emmot");
        add("von-Einzbern");
        add("Fujiwara");
        add("Uraraka");
        add("Joestar");
        add("Speedwagon");
        add("Kujo");
        add("Yatogami");
        add("Hayasaka");
        add("Finger");
        add("Nakano");
        add("Tsukuyomi");
        add("Toujou");
        add("Ashido");
        add("Himejima");
        add("Silva");
        add("Schwi");
        add("Mafuyu");
        add("Oze");
        add("Kusakabe");
        add("Scarlet");
        add("Nariyuki");
        add("Crescent");
        add("Belladonna");
        add("Rose");
        add("Nakiri");
        add("Xiao-Long");
        add("Tortura");
        add("Mieruko");
    }};

    // Death storage

    @Override
    public List<String> getDeathData()
    {
        return deathData;
    }

    @Override
    public void addDeathData(String data)
    {
        deathData.add(data);
    }

    @Override
    public void setDeathData(List<String> data)
    {
        deathData = data;
    }

    @Override
    public void addName(String name)
    {
        this.names.add(name);
    }

    @Override
    public List<String> getNames()
    {
        return names;
    }

    @Override
    public void clearNames()
    {
        names = new ArrayList<>();
    }

    @Override
    public void setNames(List<String> names)
    {
        this.names = names;
    }

    /**
     * PDL = Permadeath Location
     */
    @Override
    public void setPDL(BlockPos pos)
    {
        pdl = pos;
    }

    @Override
    public BlockPos getPDL()
    {
        return pdl;
    }

    // Permadeath

    @Override
    public boolean isPermadeath()
    {
        return isPermadeath;
    }

    @Override
    public void setPermadeath(boolean pd)
    {
        isPermadeath = pd;
    }
}