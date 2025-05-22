package org.irmc.industrialrevival.api.items.attributes;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.machines.process.Environment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public interface EnvironmentHolder extends ItemAttribute {
    double AIR_PRESSURE = 101300;
    Map<Location, Environment> ENVIRONMENT_CONTAINER = new HashMap<>();

    @NotNull
    default Environment newEnvironment() {
        return new Environment();
    }

    @Nullable
    default Environment getEnvironment(@NotNull Location location) {
        return ENVIRONMENT_CONTAINER.get(location);
    }

    @ParametersAreNonnullByDefault
    @CanIgnoreReturnValue
    default Environment setEnvironment(Location location, Environment environment) {
        return ENVIRONMENT_CONTAINER.put(location, environment);
    }

    default Environment getOrNewEnvironment(@NotNull Location location) {
        var exist = getEnvironment(location);
        if (exist != null) {
            return exist;
        }

        var e = newEnvironment();
        setEnvironment(location, e);
        return e;
    }

    default double getTemperature(@NotNull Location location) {
        return getOrNewEnvironment(location).getTemperature();
    }

    default double getPressure(@NotNull Location location) {
        return getOrNewEnvironment(location).getPressure();
    }

    default double getHumidity(@NotNull Location location) {
        return getOrNewEnvironment(location).getHumidity();
    }

    default double getRadiation(@NotNull Location location) {
        return getOrNewEnvironment(location).getRadiation();
    }

    default EnvironmentHolder setTemperature(@NotNull Location location, double temperature) {
        getOrNewEnvironment(location).setTemperature(temperature);
        return this;
    }

    default EnvironmentHolder setPressure(@NotNull Location location, double pressure) {
        getOrNewEnvironment(location).setPressure(pressure);
        return this;
    }

    default EnvironmentHolder setHumidity(@NotNull Location location, double humidity) {
        getOrNewEnvironment(location).setHumidity(humidity);
        return this;
    }

    default EnvironmentHolder setRadiation(@NotNull Location location, double radiation) {
        getOrNewEnvironment(location).setRadiation(radiation);
        return this;
    }
}
