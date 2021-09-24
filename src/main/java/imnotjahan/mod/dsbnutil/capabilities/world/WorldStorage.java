package imnotjahan.mod.dsbnutil.capabilities.world;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;

import java.util.ArrayList;
import java.util.List;

public class WorldStorage implements Capability.IStorage<IWorldData>
{
    @Override
    public INBT writeNBT(Capability<IWorldData> capability, IWorldData instance, Direction side)
    {
        if(WorldData.capSide == side)
        {
            CompoundNBT status = new CompoundNBT();

            List<String> deathData = instance.getDeathData();

            status.putInt("deaths", deathData.size());

            for(int k = 0; k < deathData.size(); k++)
            {
                status.putString("death" + k, deathData.get(k));
            }

            List<String> nameData = instance.getNames();

            status.putInt("names", nameData.size());

            for(int k = 0; k < nameData.size(); k++)
            {
                status.putString("name" + k, nameData.get(k));
            }

            status.putBoolean("pd", instance.isPermadeath()); // PD = permadeath

            status.putInt("pdlX", instance.getPDL().getX()); // PDL = permadeath location
            status.putInt("pdlY", instance.getPDL().getY()); // PDL = permadeath location
            status.putInt("pdlZ", instance.getPDL().getZ()); // PDL = permadeath location

            return status;
        }

        return new CompoundNBT();
    }

    @Override
    public void readNBT(Capability<IWorldData> capability, IWorldData instance, Direction side, INBT nbt)
    {
        if(WorldData.capSide == side)
        {
            if(nbt instanceof CompoundNBT)
            {
                CompoundNBT tag = (CompoundNBT)nbt;

                List<String> deathData = new ArrayList<>();

                for(int k = 0; k < tag.getInt("deaths"); k++)
                {
                    deathData.add(tag.getString("death" + k));
                }

                instance.clearNames();
                for(int k = 0; k < tag.getInt("names"); k++)
                {
                    instance.addName(tag.getString("name" + k));
                }

                instance.setPermadeath(tag.getBoolean("pd"));

                instance.setPDL(new BlockPos(
                        tag.getInt("pdlX"), tag.getInt("pdlY"), tag.getInt("pdlZ")));

                instance.setDeathData(deathData);
            }
        }
    }
}