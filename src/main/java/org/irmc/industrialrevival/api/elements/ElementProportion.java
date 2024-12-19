package org.irmc.industrialrevival.api.elements;

import lombok.Builder;

@Builder
public record ElementProportion(ElementType elementType, double proportion) {
}
