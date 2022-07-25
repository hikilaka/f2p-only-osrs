package org.sysdevs.f2ponly;

import com.google.common.collect.ImmutableList;
import net.runelite.api.MenuAction;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;

import java.util.List;

public class F2PConstants {
    public static final int ACHIEVEMENTS_TAB_GROUP_ID = 629;
    public static final int ACHIEVEMENT_DIARY_ID = 13;
    public static final int KOUREND_FAVOUR_ID = 18;

    public static final int MINIMAP_SPECIAL_ORB = 33;

    public static final List<MenuAction> NPC_MENU_ACTIONS = ImmutableList.of(
            MenuAction.NPC_FIRST_OPTION,
            MenuAction.NPC_SECOND_OPTION,
            MenuAction.NPC_THIRD_OPTION,
            MenuAction.NPC_FOURTH_OPTION,
            MenuAction.NPC_FIFTH_OPTION
    );

    public static final List<String> P2P_NPC_MENU_OPTIONS = ImmutableList.of(
            "pickpocket"
    );

    public static final ImmutableList<Integer> P2P_ACHIEVEMENT_CONTENT = ImmutableList.of(
            ACHIEVEMENT_DIARY_ID,
            KOUREND_FAVOUR_ID
    );

    public static final ImmutableList<WidgetInfo> P2P_PRAYER_WIDGET_INFO = ImmutableList.of(
            WidgetInfo.PRAYER_RETRIBUTION,
            WidgetInfo.PRAYER_REDEMPTION,
            WidgetInfo.PRAYER_SMITE,
            WidgetInfo.PRAYER_PRESERVE,
            WidgetInfo.PRAYER_CHIVALRY,
            WidgetInfo.PRAYER_PIETY,
            WidgetInfo.PRAYER_RIGOUR,
            WidgetInfo.PRAYER_AUGURY
    );

    public static final ImmutableList<Integer> P2P_SPELLS = ImmutableList.of(
            9,  // enchant bolt
            29, // teleport to house
            32, // teleport to camelot
            35, // iban's blast
            37, // magic dart
            38, // teleport to ardougne
            41, // charge water orb
            43, // teleport to watchtower
            45, // charge earth orb
            46, // bones to peaches
            47, // saradomin strike
            48, // flames of zamorak
            49, // claws of guthix
            50, // teleport to trollheim
            51, // wind wave
            52, // charge fire orb
            53, // teleport to ape atoll
            54, // water wave
            55, // charge air orb
            56, // vulnerability
            57, // lvl-5 enchant
            58, // teleport to kourend
            59, // earth wave
            60, // enfeable
            61, // teleother lumbridge
            62, // fire wave
            63, // entangle
            64, // stun
            65, // charge
            66, // wind surge
            67, // teleother falador
            68, // water surge
            70, // teleport to target
            71, // lvl-6 enchant
            72, // teleother camelot
            73, // earth surge
            74, // lvl-7 enchant
            75  // fire surge
    );
}
