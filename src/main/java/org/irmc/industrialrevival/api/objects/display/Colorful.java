package org.irmc.industrialrevival.api.objects.display;

import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.TextDisplay;
import org.irmc.industrialrevival.api.IndustrialRevivalAddon;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author balugaq
 */
public interface Colorful {
    default public Builder brush() {
        return new Builder().modelHandler(getModelHandler());
    }

    ModelHandler getModelHandler();

    DisplayGroup renderAt(Location center);

    /**
     * @author balugaq
     */
    @Getter
    @Setter
    public static class Builder {
        @Getter
        private IndustrialRevivalAddon plugin = null;
        @Getter
        private Color color = null;
        @Getter
        private Location center = null;
        @Getter
        private double offsetPX = 0D;
        @Getter
        private double offsetPY = 0D;
        @Getter
        private double offsetPZ = 0D;
        @Getter
        private double offsetNX = 0D;
        @Getter
        private double offsetNY = 0D;
        @Getter
        private double offsetNZ = 0D;
        @Getter
        private List<ColorBlock> render = new ArrayList<>(List.of(ColorBlock.DEFAULT_SURFACE));
        @Getter
        private TextureHandler textureHandler = null;
        @Getter
        private ModelHandler modelHandler = null;

        public Builder plugin(IndustrialRevivalAddon plugin) {
            this.plugin = plugin;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder color(TextColor color) {
            this.color = Color.fromRGB(color.red(), color.blue(), color.green());
            return this;
        }

        public Builder center(Block block) {
            return center(block.getLocation());
        }

        public Builder center(Location location) {
            this.center = location;
            return this;
        }

        public Builder center(World world, double x, double y, double z) {
            this.center = new Location(world, x, y, z);
            return this;
        }

        public Builder extend(double x, double y, double z) {
            if (x > 0) offsetPX += x;
            else offsetNX += x;
            if (y > 0) offsetPY += y;
            else offsetNY += y;
            if (z > 0) offsetPZ += z;
            else offsetNZ += z;
            return this;
        }

        public Builder extendX(double x) {
            return extend(x, 0, 0);
        }

        public Builder extendY(double y) {
            return extend(0, y, 0);
        }

        public Builder extendZ(double z) {
            return extend(0, 0, z);
        }

        public Builder x(double x) {
            return extendX(x);
        }

        public Builder y(double y) {
            return extendY(y);
        }

        public Builder z(double z) {
            return extendZ(z);
        }

        public Builder extendTo(double x, double y, double z) {
            if (x > 0) offsetPX = x;
            else offsetNX = x;
            if (y > 0) offsetPY = y;
            else offsetNY = y;
            if (z > 0) offsetPZ = z;
            else offsetNZ = z;
            return this;
        }

        public Builder extendXTo(double x) {
            if (x > 0) offsetPX = x;
            else offsetNX = x;
            return this;
        }

        public Builder extendYTo(double y) {
            if (y > 0) offsetPY = y;
            else offsetNY = y;
            return this;
        }

        public Builder extendZTo(double z) {
            if (z > 0) offsetPZ = z;
            else offsetNZ = z;
            return this;
        }

        public Builder render(ColorBlock... colorBlocks) {
            if (Objects.deepEquals(render.toArray(), ColorBlock.DEFAULT_SURFACE)) {
                render = new ArrayList<>(List.of(colorBlocks));
            } else {
                render.addAll(List.of(colorBlocks));
            }
            return this;
        }

        public Builder textureHandler(TextureHandler textureHandler) {
            this.textureHandler = textureHandler;
            return this;
        }

        public Builder modelHandler(ModelHandler modelHandler) {
            this.modelHandler = modelHandler;
            return this;
        }

        public DisplayGroup build() {
            Preconditions.checkNotNull(plugin, "Plugin is not set");
            Preconditions.checkNotNull(color, "Color is not set");
            Preconditions.checkNotNull(center, "Center is not set");
            Preconditions.checkArgument(render.size() != 0, "Render is not set");

            Corners corners = Corners.of(center);
            corners
                    .merge(Corners.of(center.clone().add(offsetPX, offsetPY, offsetPZ)))
                    .merge(Corners.of(center.clone().add(offsetNX, offsetNY, offsetNZ)));

            List<TextDisplay> displays = new ArrayList<>();

            if (Objects.deepEquals(render.toArray(), ColorBlock.DEFAULT_SURFACE)) {
                displays = ColorBlock.makeSurface(corners, color, textureHandler);
            } else {
                for (ColorBlock colorBlock : render) {
                    displays.add(colorBlock.make(corners, color, textureHandler));
                }
            }

            var group = new DisplayGroup(plugin);
            for (var display : displays) {
                group.addDirectly(display);
            }

            if (modelHandler != null) {
                modelHandler.addDisplayGroup(center, group);
            }

            return group;
        }

        public List<TextDisplay> build0() {
            Preconditions.checkNotNull(plugin, "Plugin is not set");
            Preconditions.checkNotNull(color, "Color is not set");
            Preconditions.checkNotNull(center, "Center is not set");
            Preconditions.checkArgument(render.size() != 0, "Render is not set");

            Corners corners = Corners.of(center);
            corners
                    .merge(Corners.of(center.clone().add(offsetPX, offsetPY, offsetPZ)))
                    .merge(Corners.of(center.clone().add(offsetNX, offsetNY, offsetNZ)));

            List<TextDisplay> displays = new ArrayList<>();

            if (Objects.deepEquals(render.toArray(), ColorBlock.DEFAULT_SURFACE)) {
                displays = ColorBlock.makeSurface(corners, color, textureHandler);
            } else {
                for (ColorBlock colorBlock : render) {
                    displays.add(colorBlock.make(corners, color, textureHandler));
                }
            }

            return displays;
        }
    }
}
