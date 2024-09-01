package org.irmc.industrialrevival.api.items.attributes;

public interface Limited extends ItemAttribute {
    int getLimit();

    void setLimit(int limit);

    int getCountLeft();

    void setCountLeft(int countLeft);
}
