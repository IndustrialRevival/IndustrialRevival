package org.irmc.industrialrevival.api.items.attributes;

import org.bukkit.inventory.ItemStack;

public interface ComplexDataContainer {
    public interface DataContainer2<T1, T2>  {
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);

        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
    }

    public interface DataContainer3<T1, T2, T3> extends DataContainer2<T1, T2>{
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);
        T3 getData3(ItemStack itemStack);
        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
        void setData3(ItemStack itemStack, T3 data);
    }

    public interface DataContainer4<T1, T2, T3, T4> extends DataContainer3<T1, T2, T3>{
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);
        T3 getData3(ItemStack itemStack);
        T4 getData4(ItemStack itemStack);
        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
        void setData3(ItemStack itemStack, T3 data);
        void setData4(ItemStack itemStack, T4 data);
    }

    public interface DataContainer5<T1, T2, T3, T4, T5> extends DataContainer4<T1, T2, T3, T4>{
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);
        T3 getData3(ItemStack itemStack);
        T4 getData4(ItemStack itemStack);
        T5 getData5(ItemStack itemStack);
        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
        void setData3(ItemStack itemStack, T3 data);
        void setData4(ItemStack itemStack, T4 data);
        void setData5(ItemStack itemStack, T5 data);
    }

    public interface DataContainer6<T1, T2, T3, T4, T5, T6> extends DataContainer5<T1, T2, T3, T4, T5>{
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);
        T3 getData3(ItemStack itemStack);
        T4 getData4(ItemStack itemStack);
        T5 getData5(ItemStack itemStack);
        T6 getData6(ItemStack itemStack);
        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
        void setData3(ItemStack itemStack, T3 data);
        void setData4(ItemStack itemStack, T4 data);
        void setData5(ItemStack itemStack, T5 data);
        void setData6(ItemStack itemStack, T6 data);
    }

    public interface DataContainer7<T1, T2, T3, T4, T5, T6, T7> extends DataContainer6<T1, T2, T3, T4, T5, T6>{
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);
        T3 getData3(ItemStack itemStack);
        T4 getData4(ItemStack itemStack);
        T5 getData5(ItemStack itemStack);
        T6 getData6(ItemStack itemStack);
        T7 getData7(ItemStack itemStack);
        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
        void setData3(ItemStack itemStack, T3 data);
        void setData4(ItemStack itemStack, T4 data);
        void setData5(ItemStack itemStack, T5 data);
        void setData6(ItemStack itemStack, T6 data);
        void setData7(ItemStack itemStack, T7 data);
    }

    public interface DataContainer8<T1, T2, T3, T4, T5, T6, T7, T8> extends DataContainer7<T1, T2, T3, T4, T5, T6, T7> {
        T1 getData1(ItemStack itemStack);
        T2 getData2(ItemStack itemStack);
        T3 getData3(ItemStack itemStack);
        T4 getData4(ItemStack itemStack);
        T5 getData5(ItemStack itemStack);
        T6 getData6(ItemStack itemStack);
        T7 getData7(ItemStack itemStack);
        T8 getData8(ItemStack itemStack);
        void setData1(ItemStack itemStack, T1 data);
        void setData2(ItemStack itemStack, T2 data);
        void setData3(ItemStack itemStack, T3 data);
        void setData4(ItemStack itemStack, T4 data);
        void setData5(ItemStack itemStack, T5 data);
        void setData6(ItemStack itemStack, T6 data);
        void setData7(ItemStack itemStack, T7 data);
        void setData8(ItemStack itemStack, T8 data);
    }
}
