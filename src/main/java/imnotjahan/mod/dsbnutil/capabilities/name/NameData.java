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

    @Override
    public List<Integer> getIntUnlocked()
    {
        List<Integer> list = new ArrayList<>();
        unlockedSwords.forEach(sword ->
        {
            if(!lockedSwords.contains(sword)) return;
            list.add(lockedSwords.indexOf(sword));

            System.out.println("Sword wanted to be unlocked: " + sword);
            System.out.println("If the sword was in the list: " + lockedSwords.contains(sword));
            System.out.println("Its index: " + lockedSwords.indexOf(sword));
        });
        return list;
    }

    public static final List<String> lockedSwords = new ArrayList<String>()
    {{
        // nichirin swords
        add("kimetsunoyaiba:nichirinsword_kanawo");
        add("kimetsunoyaiba:nichirinsword_mist");
        add("kimetsunoyaiba:nichirinsword_bamboo");
        add("kimetsunoyaiba:nichirinsword_kanae");
        add("kimetsunoyaiba:nichirinsword_thunder");
        add("kimetsunoyaiba:nichirinsword_iguro");
        add("kimetsunoyaiba:nichirinsword_genya");
        add("kimetsunoyaiba:nichirinsword_water");
        add("kimetsunoyaiba:nichirinsword_yoriichi"); // Pluto
        add("kimetsunoyaiba:nichirinsword_tokito");
        add("kimetsunoyaiba:nichirinsword_inosuke");
        add("kimetsunoyaiba:nichirinsword_senior");
        add("kimetsunoyaiba:nichirinsword_tanjiro");
        add("kimetsunoyaiba:nichirinsword_shinazugawa");
        add("kimetsunoyaiba:nichirinsword_tanjiro_2");
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
        add("kimetsunoyaiba:sword_kokushibo_1");
        add("kimetsunoyaiba:sword_kokushibo_2");

        // demon arts
        add("kimetsunoyaiba:blooddemonart_nezuko");
        add("kimetsunoyaiba:blooddemonart_yahaba");
        add("kimetsunoyaiba:kemari");
        add("kimetsunoyaiba:drum");
        add("kimetsunoyaiba:blooddemonart_kamanue");
        add("kimetsunoyaiba:blooddemonart_rui");
        add("kimetsunoyaiba:blooddemonart_rui_sister");
        add("kimetsunoyaiba:rifle");
        add("kimetsunoyaiba:minigun");
        add("kimetsunoyaiba:sword_hairo");
        add("kimetsunoyaiba:blooddemonart_enmu");
        add("kimetsunoyaiba:chigama");
        add("kimetsunoyaiba:blooddemonart_gyokko");
        add("kimetsunoyaiba:khakkhara");
        add("kimetsunoyaiba:spear");
        add("kimetsunoyaiba:urogi_hand");
        add("kimetsunoyaiba:tengu_handfan");
        add("kimetsunoyaiba:blooddemonart_zohakuten");
        add("kimetsunoyaiba:blooddemonart_nakime");
        add("kimetsunoyaiba:blooddemonart_akaza");
        add("kimetsunoyaiba:handfan");
    }};

    @Override
    public void setIntUnlocked(List<Integer> swords)
    {
        try
        {
            swords.forEach(sword ->
            {
                if(!(lockedSwords.size() > sword && sword > -1)) return;
                unlockedSwords.add(lockedSwords.get(sword));
            });
        } catch(Exception e)
        {
            System.out.println("Failed adding a sword to the unlockedSwords list \n" +
                    "(setIntUnlocked dsbnutil.capabilities.name.NameData)");
            System.out.print("Sword numbers: " + swords);

            System.out.println("\nError: " + e);
        }
    }

    boolean isRefreshing = false;

    @Override
    public boolean isRefreshing()
    {
        return isRefreshing;
    }

    @Override
    public void makeRefreshing()
    {
        isRefreshing = true;
    }

    @Override
    public boolean stopRefreshing()
    {
        isRefreshing = false;
        return false;
    }
}