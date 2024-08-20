package org.irmc.industrialrevival.api.items.attributes;

public interface Limited extends ItemAttribute {
    int getLimit();
    int getCountLeft();
    void setLimit(int limit);
    void setCountLeft(int countLeft);
}
