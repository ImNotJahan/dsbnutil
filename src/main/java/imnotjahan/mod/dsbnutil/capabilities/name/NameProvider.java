package imnotjahan.mod.dsbnutil.capabilities.name;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class NameProvider implements ICapabilitySerializable<INBT>
{
    @CapabilityInject(INameData.class)
    public static final Capability<INameData> STATUS_CAP = null;
    private final LazyOptional<INameData> lazyStatus = LazyOptional.of(NameData::new);

    private INameData instance = STATUS_CAP.getDefaultInstance();

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction side)
    {
        return capability == STATUS_CAP ? lazyStatus.cast() : LazyOptional.empty();
    }

    @Override
    public INBT serializeNBT()
    {
        return STATUS_CAP.getStorage().writeNBT(STATUS_CAP, this.lazyStatus.orElseThrow(ArithmeticException::new), NameData.capSide);
    }

    @Override
    public void deserializeNBT(INBT nbt)
    {
        STATUS_CAP.getStorage().readNBT(STATUS_CAP, this.lazyStatus.orElseThrow(ArithmeticException::new), NameData.capSide, nbt);
    }
}