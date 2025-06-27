package org.irmc.industrialrevival.api;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.items.handlers.BlockTicker;
import org.irmc.industrialrevival.api.objects.IRBlockData;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.industrialrevival.utils.DataUtil;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.io.File;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@SuppressWarnings({"deprecation", "unused"})
public class ErrorReport<T extends Throwable> {
    private static final String FORMAT = """
            Error Generated at: {0}
            
            Java Environment:
            Operating System: {1}
            Java Version: {2}
            
            Server Software: {3}
            Build: {4}
            Minecraft v{5}
            
            IndustrialRevival Environment:
            IndustrialRevival v{6}
            Caused by: {7} v{8}
            
            Installed Addons ({9}):
            {10}
            
            Installed Plugins ({11}):
            {12}
            
            """;
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm", Locale.ROOT);
    private static final AtomicInteger count = new AtomicInteger(0);

    private final IndustrialRevivalAddon addon;
    private final T throwable;

    private File file;

    @ParametersAreNonnullByDefault
    public ErrorReport(T throwable, IndustrialRevivalAddon addon, Consumer<PrintStream> printer) {
        this.throwable = throwable;
        this.addon = addon;

        IndustrialRevival.runSync(() -> print(printer));
    }

    @ParametersAreNonnullByDefault
    public ErrorReport(T throwable, Location l, IndustrialRevivalItem item) {
        this(throwable, item.getAddon(), stream -> {
            stream.println("Block Info:");
            stream.println("  World: " + l.getWorld().getName());
            stream.println("  X: " + l.getBlockX());
            stream.println("  Y: " + l.getBlockY());
            stream.println("  Z: " + l.getBlockZ());
            stream.println("  Material: " + l.getBlock().getType());
            stream.println(
                    "  Block Data: " + l.getBlock().getBlockData().getClass().getName());
            stream.println("  State: " + l.getBlock().getState().getClass().getName());
            stream.println();

            BlockTicker ticker = item.getItemHandler(BlockTicker.class);
            if (ticker != null) {
                stream.println("Ticker-Info:");
                stream.println("  Type: " + (ticker.isSynchronized() ? "Synchronized" : "Asynchronous"));
                stream.println();
            }

            stream.println("Industrial Revival Data:");
            stream.println("  ID: " + item.getId());
            IRBlockData blockData = DataUtil.getBlockData(l);
            if (blockData == null) {
                stream.println("Block data is not presented.");
            } else {
                stream.println("  Inventory: " + (blockData.getMachineMenu() != null));
                stream.println("  Data: ");
                blockData.getMapData().forEach((k, v) -> stream.println("    " + k + ": " + v));
            }
            stream.println();
        });
    }

    @ParametersAreNonnullByDefault
    public ErrorReport(T throwable, IndustrialRevivalItem item) {
        this(throwable, item.getAddon(), stream -> {
            stream.println("Industrial Revival Item:");
            stream.println("  ID: " + item.getId());
            stream.println("  Plugin: "
                    + (item.getAddon() == null ? "Unknown" : item.getAddon().getPlugin().getName()));
            stream.println();
        });
    }

    public static int count() {
        return count.get();
    }

    private static @Nonnull File getNewFile() {
        String path = Constants.Files.ERROR_REPORTS_FOLDER + dateFormat.format(LocalDateTime.now());
        File newFile = new File(path + ".txt");

        if (newFile.exists()) {
            IntStream stream = IntStream.iterate(1, i -> i + 1).filter(i -> !new File(path + " (" + i + ").txt").exists());
            int id = 1;
            OptionalInt optionalInt = stream.findFirst();
            if (optionalInt.isPresent()) {
                id = optionalInt.getAsInt();
            }

            newFile = new File(path + " (" + id + ").txt");
        }

        return newFile;
    }

    public @Nonnull File getFile() {
        return file;
    }

    public @Nonnull T getThrown() {
        return throwable;
    }

    private void print(@Nonnull Consumer<PrintStream> printer) {
        this.file = getNewFile();
        count.incrementAndGet();

        try (PrintStream stream = new PrintStream(file, StandardCharsets.UTF_8)) {
            Stream<String> plugins = Arrays.stream(IRDock.getPlugin().getServer().getPluginManager().getPlugins()).map(plugin -> (plugin.isEnabled() ? " + " : " - ") + plugin.getName() + " v" + plugin.getDescription().getVersion());
            Stream<String> addons = IRDock.getPlugin().getAddons().stream().map(plugin -> (plugin.isEnabled() ? " + " : " - ") + plugin.getName() + " v" + plugin.getDescription().getVersion());
            List<String> pluginsList = plugins.toList();
            List<String> addonsList = addons.toList();
            int pluginsSize = pluginsList.size();
            String pluginString = String.join("\n", pluginsList);
            int addonsSize = addonsList.size();
            String addonString = String.join("\n", addonsList);

            stream.println(MessageFormat.format(
                    FORMAT,
                    dateFormat.format(LocalDateTime.now()),
                    System.getProperty("os.name"),
                    System.getProperty("java.version"),
                    "Paper or its fork",
                    Bukkit.getVersion(),
                    Bukkit.getBukkitVersion(),
                    IRDock.getPlugin().getVersion(),
                    addon.getName(),
                    addon.getVersion(),
                    pluginsSize,
                    pluginString,
                    addonsSize,
                    addonsList
            ));

            printer.accept(stream);

            stream.println("Stacktrace:");
            stream.println();
            throwable.printStackTrace(stream);

            addon.getLogger().log(Level.WARNING, "");
            addon.getLogger().log(Level.WARNING, "An Error occurred! It has been saved as: ");
            addon.getLogger().log(Level.WARNING, "/plugins/IndustrialRevival/error-reports/{0}", file.getName());
            addon.getLogger()
                    .log(
                            Level.WARNING,
                            "Please put this file on https://mclo.gs/ and report this to the developer(s).");

            if (addon.getIssueTrackerURL() != null) {
                addon.getLogger().log(Level.WARNING, "Issue Tracker: {0}", addon.getIssueTrackerURL());
            }

            addon.getLogger().log(Level.WARNING, "");
        } catch (Exception x) {
            addon.getLogger()
                    .log(
                            Level.SEVERE,
                            x,
                            () -> "An Error occurred while saving an Error-Report for IndustrialRevival "
                                    + IRDock.getPlugin().getVersion());
        }
    }
}
