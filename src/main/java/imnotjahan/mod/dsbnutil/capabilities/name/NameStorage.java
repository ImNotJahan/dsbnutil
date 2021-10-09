package imnotjahan.mod.dsbnutil.capabilities.name;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.List;

public class NameStorage implements Capability.IStorage<INameData>
{
    @Override
    public INBT writeNBT(Capability<INameData> capability, INameData instance, Direction side)
    {
        CompoundNBT status = new CompoundNBT();
        status.putString("name", instance.getName());

        List<String> swords = instance.getUnlocked();

        status.putInt("swords", swords.size());

        for(int k = 0; k < swords.size(); k++)
        {
            status.putString("sword" + k, swords.get(k));
        }

        if(instance.refreshing()) status.putBoolean("refreshing", true);
        return status;
    }

    @Override
    public void readNBT(Capability<INameData> capability, INameData instance, Direction side, INBT nbt)
    {
        CompoundNBT tag = (CompoundNBT) nbt;

        instance.setName(tag.getString("name"));

        for(int k = 0; k < tag.getInt("swords"); k++)
        {
            instance.unlock(tag.getString("sword" + k));
        }

        if(tag.getBoolean("refreshing")) instance.refreshing();
    }
}