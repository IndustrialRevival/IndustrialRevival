package org.irmc.industrialrevival.api.objects.display;

import org.irmc.industrialrevival.api.objects.display.builder.TextModelBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author balugaq
 */
public interface TextureHandler {
    void apply(@Nonnull Corners corners, @Nullable TextModelBuilder extraHandler);
}
