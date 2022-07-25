package org.sysdevs.f2ponly;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class F2POnlyPluginTest {
    public static void main(String[] args) throws Exception {
        ExternalPluginManager.loadBuiltin(F2POnlyPlugin.class);
        RuneLite.main(args);
    }
}
