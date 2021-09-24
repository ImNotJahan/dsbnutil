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
        if(NameData.capSide == side)
        {
            CompoundNBT status = new CompoundNBT();
            status.putString("name", instance.getName());

            List<String> swords = instance.getUnlocked();

            status.putInt("swords", swords.size());

            for(int k = 0; k < swords.size(); k++)
            {
                status.putString("sword" + k, swords.get(k));
            }

            return status;
        }

        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<INameData> capability, INameData instance, Direction side, INBT nbt)
    {
        if(NameData.capSide == side)
        {
            if(nbt instanceof CompoundNBT)
            {
                CompoundNBT tag = (CompoundNBT)nbt;

                instance.setName(tag.getString("name"));

                for(int k = 0; k < tag.getInt("swords"); k++)
                {
                    instance.unlock(tag.getString("sword" + k));
                }
            }
        }
    }
}