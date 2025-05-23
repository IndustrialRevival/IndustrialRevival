package org.irmc.industrialrevival.api.machines.process;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author baluagq
 */
@Data
public class Environment implements Cloneable {
    public static final double DEFAULT_VALUE = 0.0D;
    public static final String TEMPERATURE_KEY = "temperature";
    public static final String PRESSURE_KEY = "pressure";
    public static final String HUMIDITY_KEY = "humidity";
    public static final String RADIATION_KEY = "radiation";

    public final Map<String, Object> data;

    public Environment() {
        data = new HashMap<>();
    }

    public Environment(Map<String, Object> data) {
        this.data = data;
    }

    public double getTemperature() {
        return (double) data.getOrDefault(TEMPERATURE_KEY, DEFAULT_VALUE);
    }

    public double getPressure() {
        return (double) data.getOrDefault(PRESSURE_KEY, DEFAULT_VALUE);
    }

    public double getHumidity() {
        return (double) data.getOrDefault(HUMIDITY_KEY, DEFAULT_VALUE);
    }

    public double getRadiation() {
        return (double) data.getOrDefault(RADIATION_KEY, DEFAULT_VALUE);
    }

    @CanIgnoreReturnValue
    public Environment setTemperature(double temperature) {
        data.put(TEMPERATURE_KEY, temperature);
        return this;
    }

    @CanIgnoreReturnValue
    public Environment setPressure(double pressure) {
        data.put(PRESSURE_KEY, pressure);
        return this;
    }

    @CanIgnoreReturnValue
    public Environment setHumidity(double humidity) {
        data.put(HUMIDITY_KEY, humidity);
        return this;
    }

    public Environment setRadiation(double radiation) {
        data.put(RADIATION_KEY, radiation);
        return this;
    }

    public Environment clone() {
        return new Environment(new HashMap<>(data));
    }
}
