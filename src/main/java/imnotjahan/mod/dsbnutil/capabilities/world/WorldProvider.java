package imnotjahan.mod.dsbnutil.capabilities.world;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class WorldProvider implements ICapabilitySerializable<INBT>
{
    @CapabilityInject(IWorldData.class)
    public static final Capability<IWorldData> STATUS_CAP = null;
    private static final LazyOptional<IWorldData> lazyStatus = LazyOptional.of(WorldData::new);

    private IWorldData instance = STATUS_CAP.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return capability == STATUS_CAP ? lazyStatus.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT()
    {
        return STATUS_CAP.getStorage().writeNBT(STATUS_CAP, this.instance, WorldData.capSide);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        STATUS_CAP.getStorage().readNBT(STATUS_CAP, this.instance, WorldData.capSide, nbt);
    }
}