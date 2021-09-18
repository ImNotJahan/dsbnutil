package imnotjahan.mod.dsbnutil.capabilities.name;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public class NameStorage implements Capability.IStorage<INameData>
{
    @Override
    public INBT writeNBT(Capability<INameData> capability, INameData instance, Direction side)
    {
        if(NameData.capSide == side)
        {
            CompoundNBT status = new CompoundNBT();
            status.putString("name", instance.getName());

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
            }
        }
    }
}