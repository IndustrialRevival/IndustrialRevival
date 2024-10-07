package org.irmc.industrialrevival.api;

import lombok.Data;
import org.irmc.industrialrevival.implementation.IndustrialRevival;

import java.io.File;

@Data
public class ErrorReport {
    private String message;

    public ErrorReport(String message) {
        this.message = message;
    }

    public void report() {
        if (message == null) {
            throw new IllegalArgumentException("Error message cannot be null");
        }
        //TODO: implement error reporting
    }
}
