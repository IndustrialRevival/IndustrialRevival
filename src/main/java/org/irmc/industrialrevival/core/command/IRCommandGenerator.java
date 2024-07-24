package org.irmc.industrialrevival.core.command;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.ArgumentSuggestions;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.arguments.TextArgument;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.core.IndustrialRevival;
import org.irmc.industrialrevival.core.message.MessageReplacement;
import org.irmc.industrialrevival.core.utils.Constants;

public class IRCommandGenerator {
    private static CommandAPICommand instance;

    public static void registerCommand(IndustrialRevival plugin) {
        instance = new CommandAPICommand("industrialrevival")
                .withAliases("ir")
                .withArguments(new TextArgument("subCommand"))
                .withSubcommand(new CommandAPICommand("help")
                        .withPermission("industrialrevival.cmd.help")
                        .executes(executionInfo -> {
                            CommandSender sender = executionInfo.sender();
                            sendHelpMessage(sender);
                        }))
                .withSubcommand(new CommandAPICommand("guide")
                        .withPermission("industrialrevival.cmd.guide")
                        .executesPlayer(executionInfo -> {
                            Player player = executionInfo.sender();
                            player.getInventory().addItem(Constants.GUIDE_BOOK_ITEM.clone());
                        }))
                .withSubcommand(new CommandAPICommand("info")
                        .withPermission("industrialrevival.cmd.info")
                        .executes(executionInfo -> {
                            CommandSender sender = executionInfo.sender();
                            sendInfoMessage(sender);
                        }))
                .withSubcommand(new CommandAPICommand("give")
                        .withPermission("industrialrevival.cmd.give")
                        .withArguments(new PlayerArgument("target"))
                        .withArguments(new TextArgument("id")
                                .replaceSuggestions(ArgumentSuggestions.stringsAsync(
                                        info -> CompletableFuture.supplyAsync(() -> IndustrialRevival.getInstance()
                                                .getRegistry()
                                                .getItems()
                                                .keySet()
                                                .toArray(new String[0])))))
                        .executes((sender, args) -> {
                            Player target = (Player) args.get("target");
                            String itemID = (String) args.get("id");
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
                            ItemStack iritem = item.getItem().clone();
                            target.getInventory().addItem(iritem);
                            target.sendMessage(IndustrialRevival.getInstance()
                                    .getLanguageManager()
                                    .getMsgComponent(
                                            sender,
                                            "command.give.success",
                                            new MessageReplacement(
                                                    "%item%",
                                                    MiniMessage.miniMessage()
                                                            .serialize(Objects.requireNonNull(iritem.getItemMeta()
                                                                    .displayName())))));
                        }))
                .withSubcommand(new CommandAPICommand("giveSilently")
                        .withPermission("industrialrevival.cmd.give")
                        .withArguments(new PlayerArgument("target"))
                        .withArguments(new TextArgument("id")
                                .replaceSuggestions(ArgumentSuggestions.stringsAsync(
                                        info -> CompletableFuture.supplyAsync(() -> IndustrialRevival.getInstance()
                                                .getRegistry()
                                                .getItems()
                                                .keySet()
                                                .toArray(new String[0])))))
                        .executes((sender, args) -> {
                            Player target = (Player) args.get("target");
                            String itemID = (String) args.get("id");
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
                            ItemStack iritem = item.getItem().clone();
                            target.getInventory().addItem(iritem);
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
        MessageReplacement ver = new MessageReplacement(
                "%version%", IndustrialRevival.getInstance().getPluginMeta().getVersion());
        msg = msg.append(IndustrialRevival.getInstance()
                .getLanguageManager()
                .getMsgComponent(sender, "command.info.version", ver));

        // TODO: add more info messages
        sender.sendMessage(msg);
    }
}
