package org.irmc.industrialrevival.core.services;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.irmc.pigeonlib.language.LanguageManager;

import java.util.List;
import java.util.function.Consumer;

public class LanguageTextService {
    private static final List<LanguageManager> languageManagers;

    static {
        languageManagers = new ObjectArrayList<>();
    }

    public static void register(LanguageManager languageManager) {
        languageManagers.add(languageManager);
    }

    public static void translate(Consumer<LanguageManager> consumer) {
        for (LanguageManager languageManager : languageManagers) {
            consumer.accept(languageManager);
        }
    }
}
