package org.irmc.industrialrevival.api.items.groups;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.irmc.industrialrevival.api.items.IndustrialRevivalItem;
import org.irmc.industrialrevival.api.menu.SimpleMenu;
import org.irmc.industrialrevival.api.player.PlayerProfile;
import org.irmc.industrialrevival.core.guide.GuideHistory;
import org.irmc.industrialrevival.core.guide.IRGuideImplementation;
import org.irmc.industrialrevival.core.utils.CleanedItemGetter;
import org.irmc.industrialrevival.core.utils.Constants;
import org.irmc.industrialrevival.implementation.guide.SurvivalGuideImplementation;

/**
 * An item group class that can nest other tree item groups.<br>
 * You can use it to nest three times or more levels of other tree item groups.<br>
 * You can only rewrite {@link #onClicked(Player, SimpleMenu, int)} to customize the view of the group.
 */
public class TreeItemGroup extends ItemGroup {
    private final List<ItemObject> itemObjects = new ArrayList<>();
    private TreeItemGroup parent;

    public TreeItemGroup(NamespacedKey key, ItemStack icon) {
        super(key, icon);

        this.parent = this;
    }

    public TreeItemGroup(NamespacedKey key, ItemStack icon, int tier) {
        super(key, icon, tier);

        this.parent = this;
    }

    @Override
    public final void addItem(IndustrialRevivalItem item) {
        itemObjects.add(new ItemObject(item));
    }

    @Override
    public final void register() {
        for (ItemObject i : itemObjects) {
            i.tryRegister();
        }
    }

    public final void addItemGroup(TreeItemGroup i) {
        i.parent = this;
        itemObjects.add(new ItemObject(i));

        tryResort();
    }

    public void onClicked(Player p, SimpleMenu sm, int page) {
        boolean onlyPageOne = false;
        IRGuideImplementation guide = SurvivalGuideImplementation.INSTANCE;

        if (!getItems().isEmpty()) {
            List<List<ItemObject>> items = Lists.partition(itemObjects.stream().toList(), 36);

            if (items.size() == 1) {
                onlyPageOne = true;
            }

            List<ItemObject> itemList = items.get(page - 1);

            for (int i = 9; i < 36; i++) {
                if ((i - 9) >= itemList.size()) {
                    break;
                }

                ItemObject item = itemList.get(i - 9);
                if (item != null) {
                    sm.setItem(
                            i,
                            CleanedItemGetter.getCleanedItem(item.getIcon()),
                            (slot, player, item1, menu, clickType) -> {
                                if (item.group != null) {
                                    guide.onGroupClicked(player, item.group, 1);
                                } else {
                                    guide.onItemClicked(player, item.item, clickType);
                                }
                                return false;
                            });
                }
            }
        } else {
            onlyPageOne = true;
        }

        for (int b : Constants.BOARDER_SLOT) {
            sm.setItem(b, Constants.BACKGROUND_ITEM);
        }

        ItemStack backButton = Constants.BACK_BUTTON.apply(p);
        sm.setItem(2, backButton, ((slot, player, item, menu, clickType) -> {
            guide.open(player);
            return false;
        }));

        ItemStack previousButton = Constants.PREVIOUS_BUTTON.apply(p);
        SimpleMenu.ClickHandler previousClickHandler = (slot, player, item, menu, clickType) -> {
            guide.onGroupClicked(player, this, page - 1);
            return false;
        };

        if (page == 1) {
            previousButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            previousButton.editMeta(m -> m.displayName(Component.space()));
            previousClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(47, previousButton, previousClickHandler);

        ItemStack nextButton = Constants.NEXT_BUTTON.apply(p);
        SimpleMenu.ClickHandler nextClickHandler = (slot, player, item, menu, clickType) -> {
            guide.onGroupClicked(player, this, page + 1);
            return false;
        };

        if (onlyPageOne) {
            nextButton.setType(Material.BLACK_STAINED_GLASS_PANE);
            nextButton.editMeta(m -> m.displayName(Component.space()));
            nextClickHandler = SimpleMenu.ClickHandler.DEFAULT;
        }

        sm.setItem(51, nextButton, nextClickHandler);

        ItemStack searchButton = Constants.SEARCH_BUTTON.apply(p);
        sm.setItem(6, searchButton, SimpleMenu.ClickHandler.DEFAULT); // do nothing now

        sm.setSize(54);

        GuideHistory history = PlayerProfile.getOrRequestProfile(p.getName()).getGuideHistory();
        history.addItemGroup(this, page);

        sm.open(p);
    }

    @Override
    public void setTier(int tier) {
        super.setTier(tier);

        parent.tryResort();
    }

    private void tryResort() {
        List<ItemObject> beingSorted = Lists.newArrayList(itemObjects);
        beingSorted.sort((o, d) -> {
            if (o.group != null && d.group != null) {
                return Comparator.<TreeItemGroup>comparingInt(ItemGroup::getTier)
                        .compare(o.group, d.group);
            } else {
                // if first is group, then it should be first
                // if second is group, then it should be first too
                return o.group != null
                        ? 1
                        : d.group != null ? -1 : o.item != null && d.item != null ? 0 : o.item != null ? -1 : 1;
            }
        });
        itemObjects.clear();
        itemObjects.addAll(beingSorted);
    }

    private static final class ItemObject {
        private final IndustrialRevivalItem item;

        @Getter
        private final TreeItemGroup group;

        ItemObject(IndustrialRevivalItem item) {
            this.item = item;
            this.group = null;
        }

        ItemObject(TreeItemGroup group) {
            this.item = null;
            this.group = group;
        }

        public ItemStack getIcon() {
            if (item != null) {
                return CleanedItemGetter.getCleanedItem(item.getItem());
            } else {
                if (group != null) {
                    return group.getIcon();
                }
            }
            return null; // should never happen
        }

        public void tryRegister() {
            if (group != null && !group.isRegistered()) {
                group.register();
            }
        }
    }
}
