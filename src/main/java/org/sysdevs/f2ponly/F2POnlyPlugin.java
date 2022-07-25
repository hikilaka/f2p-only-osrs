package org.sysdevs.f2ponly;

import com.google.inject.Inject;
import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.MenuOpened;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.ArrayList;

@Slf4j
@PluginDescriptor(
        name = "F2P Only",
        description = "Removes P2P content from F2P worlds",
        tags = { "f2p", "free to play" }
)
public class F2POnlyPlugin extends Plugin {
    @Inject
    private Client client;

    @Provides
    F2POnlyConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(F2POnlyConfig.class);
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event) {
        if (isOnP2PWorld()) {
            return;
        }

        removeP2PContentFromWidget(event.getGroupId());
    }

    @Subscribe
    public void onMenuOpened(MenuOpened event) {
        if (isOnP2PWorld()) {
            return;
        }

        MenuEntry[] entries = client.getMenuEntries();
        ArrayList<MenuEntry> validEntries = new ArrayList<>();

        for (MenuEntry entry : entries) {
            MenuAction action = MenuAction.of(entry.getType().getId());

            if (!isP2PMenuAction(entry, action)) {
                validEntries.add(entry);
            }
        }

        client.setMenuEntries(validEntries.toArray(new MenuEntry[0]));
    }

    private void removeP2PContentFromWidget(int groupId) {
        switch (groupId) {
            case F2PConstants.ACHIEVEMENTS_TAB_GROUP_ID:
                removeAchievementContent();
                break;
            case WidgetID.PRAYER_GROUP_ID:
                removePrayerContent();
                break;
            case WidgetID.SPELLBOOK_GROUP_ID:
                removeSpellbookContent();
                break;
            case WidgetID.MINIMAP_GROUP_ID:
                removeMinimapContent();
                break;
        }
    }

    @Subscribe
    public void onClientTick(ClientTick tick) {
        removeSpellbookContent();
    }

    private void removeAchievementContent() {
        for (int tab : F2PConstants.P2P_ACHIEVEMENT_CONTENT) {
            Widget widget = client.getWidget(F2PConstants.ACHIEVEMENTS_TAB_GROUP_ID, tab);

            hide(widget);
        }
    }

    private void removePrayerContent() {
        for (WidgetInfo info : F2PConstants.P2P_PRAYER_WIDGET_INFO) {
            Widget widget = client.getWidget(info.getGroupId(), info.getChildId());

            hide(widget);
        }
    }

    private void removeSpellbookContent() {
        for (int spellId : F2PConstants.P2P_SPELLS) {
            Widget widget = client.getWidget(WidgetID.SPELLBOOK_GROUP_ID, spellId);

            hide(widget);
        }
    }

    private void removeMinimapContent() {
        Widget widget = client.getWidget(WidgetID.MINIMAP_GROUP_ID, F2PConstants.MINIMAP_SPECIAL_ORB);

        hide(widget);
    }

    private boolean isP2PMenuAction(MenuEntry entry, MenuAction action) {
        return isP2PNpcOption(entry, action);
    }

    private boolean isP2PNpcOption(MenuEntry entry, MenuAction action) {
        for (MenuAction npcAction : F2PConstants.NPC_MENU_ACTIONS) {
            if (action.equals(npcAction)) {
                for (String option : F2PConstants.P2P_NPC_MENU_OPTIONS) {
                    if (entry.getOption().equalsIgnoreCase(option)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private void hide(Widget widget) {
        if (widget != null) {
            widget.setHidden(true);
        }
    }

    private boolean isOnP2PWorld() {
        return client.getWorldType().stream().anyMatch(worldType -> worldType == WorldType.MEMBERS);
    }
}
