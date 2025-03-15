package org.irmc.industrialrevival.core.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.NamespacedKeyArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.objects.TimingViewRequest;
import org.irmc.industrialrevival.implementation.IndustrialRevival;
import org.irmc.industrialrevival.implementation.guide.CheatGuideImplementation;
import org.irmc.industrialrevival.utils.Constants;
import org.irmc.pigeonlib.items.ItemUtils;
import org.irmc.pigeonlib.language.MessageReplacement;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class IRCommandGenerator {
    private static CommandAPICommand instance;

    public static void registerCommand(IndustrialRevival plugin) {
        instance = new CommandAPICommand("industrialrevival")
                .withAliases("ir")
                .withArguments(new TextArgument("subCommand"))
                .executes(info -> {
                    CommandSender sender = info.sender();
                    sendHelpMessage(sender);
                })
                .withSubcommand(new CommandAPICommand("help")
                        .executes(executionInfo -> {
                            CommandSender sender = executionInfo.sender();
                            sendHelpMessage(sender);
                        }))
                .withSubcommand(new CommandAPICommand("guide")
                        .withPermission(Constants.Permissions.COMMAND_GUIDE)
                        .executesPlayer(executionInfo -> {
                            Player player = executionInfo.sender();
                            player.getInventory().addItem(Constants.ItemStacks.GUIDE_BOOK_ITEM.clone());
                        }))
                .withSubcommand(new CommandAPICommand("reload")
                        .withPermission(Constants.Permissions.COMMAND_RELOAD)
                        .executes(executionInfo -> {
                            IndustrialRevival.getInstance().reloadConfig();
                            IndustrialRevival.getInstance()
                                    .getLanguageManager()
                                    .sendMessage(executionInfo.sender(), "command.reload");
                        }))
                .withSubcommand(new CommandAPICommand("info")
                        .withPermission(Constants.Permissions.COMMAND_INFO)
                        .executes(executionInfo -> {
                            CommandSender sender = executionInfo.sender();
                            sendInfoMessage(sender);
                        }))
                .withSubcommand(new CommandAPICommand("cheat")
                        .withPermission(Constants.Permissions.COMMAND_CHEAT)
                        .executesPlayer(executionInfo -> {
                            Player player = executionInfo.sender();
                            CheatGuideImplementation.INSTANCE.open(player);
                        }))
                .withSubcommand(new CommandAPICommand("give")
                        .withPermission(Constants.Permissions.COMMAND_GIVE)
                        .withArguments(new PlayerArgument("target"))
                        .withArguments(new NamespacedKeyArgument("id")
                                .replaceSuggestions(ArgumentSuggestions.stringsAsync(
                                        _ -> CompletableFuture.supplyAsync(() -> IndustrialRevival.getInstance()
                                                .getRegistry()
                                                .getItems()
                                                .keySet()
                                                .stream().map(NamespacedKey::toString)
                                                .toArray(String[]::new)))))
                        .withOptionalArguments(new IntegerArgument("amount"))
                        .executes((sender, args) -> {
                            Player target = (Player) args.get("target");
                            NamespacedKey itemID = (NamespacedKey) args.get("id");
                            Integer amount = (Integer) args.get("amount");

                            int finalAmount = amount == null ? 1 : amount;
                            IndustrialRevivalItem item = IndustrialRevival.getInstance()
                                    .getRegistry()
                                    .getItems()
                                    .get(itemID);

                            if (target == null) {
                                sender.sendMessage(IndustrialRevival.getInstance()
                                        .getLanguageManager()
                                        .getMsgComponent(sender, "command.give.target_not_found"));
                                return;
                            }

                            if (item == null) {
                                sender.sendMessage(IndustrialRevival.getInstance()
                                        .getLanguageManager()
                                        .getMsgComponent(sender, "command.give.item_not_found"));
                                return;
                            }

                            ItemStack iritem = item.getItemStack().clone();
                            iritem.setAmount(finalAmount);
                            target.getInventory().addItem(iritem);

                            MessageReplacement itemName = MessageReplacement.replace(
                                    "%item%",
                                    MiniMessage.miniMessage()
                                            .serialize(ItemUtils.getDisplayName(iritem)));
                            MessageReplacement itemAmount =
                                    MessageReplacement.replace("%amount%", String.valueOf(finalAmount));

                            target.sendMessage(IndustrialRevival.getInstance()
                                    .getLanguageManager()
                                    .getMsgComponent(sender, "command.give.success", itemName, itemAmount));
                        }))
                .withSubcommand(new CommandAPICommand("giveSilently")
                        .withPermission(Constants.Permissions.COMMAND_GIVE)
                        .withArguments(new PlayerArgument("target"))
                        .withArguments(new NamespacedKeyArgument("id")
                                .replaceSuggestions(ArgumentSuggestions.stringsAsync(
                                        _ -> CompletableFuture.supplyAsync(() -> IndustrialRevival.getInstance()
                                                .getRegistry()
                                                .getItems()
                                                .keySet()
                                                .stream().map(NamespacedKey::toString)
                                                .toArray(String[]::new)))))
                        .withOptionalArguments(new IntegerArgument("amount"))
                        .executes((sender, args) -> {
                            Player target = (Player) args.get("target");
                            NamespacedKey itemID = (NamespacedKey) args.get("id");
                            Integer amount = (Integer) args.getOrDefault("amount", 1);

                            IndustrialRevivalItem item = IndustrialRevival.getInstance()
                                    .getRegistry()
                                    .getItems()
                                    .get(itemID);

                            if (target == null) {
                                sender.sendMessage(IndustrialRevival.getInstance()
                                        .getLanguageManager()
                                        .getMsgComponent(sender, "command.give.target_not_found"));
                                return;
                            }

                            if (item == null) {
                                sender.sendMessage(IndustrialRevival.getInstance()
                                        .getLanguageManager()
                                        .getMsgComponent(sender, "command.give.item_not_found"));
                                return;
                            }

                            ItemStack iritem = item.getItemStack().clone();
                            iritem.setAmount(amount);
                            target.getInventory().addItem(iritem);
                        }))
                .withSubcommand(new CommandAPICommand("timings")
                        .withPermission(Constants.Permissions.COMMAND_TIMINGS)
                        .executes((sender, args) -> {
                            TimingViewRequest request = new TimingViewRequest(sender, true);
                            IndustrialRevival.getInstance().getProfilerService().requestTimingView(request);
                            sender.sendMessage(IndustrialRevival.getInstance()
                                    .getLanguageManager()
                                    .getMsgComponent(sender, "command.timings.waiting"));
                        }));

        instance.register(plugin);
    }

    private static void sendHelpMessage(CommandSender sender) {
        List<CommandAPICommand> subcommands = instance.getSubcommands();
        Component msg =
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(sender, "command.help.header");
        for (CommandAPICommand subcommand : subcommands) {
            String commandName = subcommand.getName();
            msg = msg.append(Component.newline());
            msg = msg.append(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(sender, "command.help." + commandName));
        }
        sender.sendMessage(msg);
    }

    private static void sendInfoMessage(CommandSender sender) {
        Component msg =
                IndustrialRevival.getInstance().getLanguageManager().getMsgComponent(sender, "command.info.header");
        msg = msg.append(Component.newline());

        MessageReplacement ver = MessageReplacement.replace(
                "%version%", IndustrialRevival.getInstance().getVersion());

        msg = msg.append(IndustrialRevival.getInstance()
                .getLanguageManager()
                .getMsgComponent(sender, "command.info.version", ver));

        msg = msg.append(Component.newline());

        MessageReplacement serverVer = MessageReplacement.replace("%server_version%", Bukkit.getVersion());

        msg = msg.append(IndustrialRevival.getInstance()
                .getLanguageManager()
                .getMsgComponent(sender, "command.info.server_version", serverVer));

        for (Plugin addon : IndustrialRevival.getInstance().getAddons()) {
            MessageReplacement name = MessageReplacement.replace("%addon_name%", addon.getName());
            MessageReplacement version = MessageReplacement.replace(
                    "%addon_version%", addon.getPluginMeta().getVersion());
            msg = msg.append(Component.newline());
            msg = msg.append(IndustrialRevival.getInstance()
                    .getLanguageManager()
                    .getMsgComponent(sender, "command.info.addon_item", name, version));
        }

        sender.sendMessage(msg);
    }
}
