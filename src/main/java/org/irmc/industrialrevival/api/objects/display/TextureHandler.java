package org.irmc.industrialrevival.api.objects.display;

import org.irmc.industrialrevival.api.objects.display.builder.TextModelBuilder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.BiConsumer;

/**
 * @author balugaq
 */
public interface TextureHandler extends BiConsumer<Corners, TextModelBuilder> {
    @Override
    void accept(@Nonnull Corners corners, @Nullable TextModelBuilder extraHandler);
}
