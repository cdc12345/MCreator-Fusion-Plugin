package net.tucky143.curios.parts;

import net.mcreator.plugin.PluginUpdateInfo;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.modgui.ModElementGUI;
import net.tucky143.curios.ui.modgui.v1201.CuriosSlotGUI;
import net.tucky143.curios.ui.modgui.v1201.CuriosBaubleGUI;

import javax.swing.*;
import java.util.*;

public class PluginEventTriggers {

    private static final Set<PluginUpdateInfo> pluginUpdates = new HashSet();

    public static void dependencyWarning(MCreator mcreator, ModElementGUI modElement) {
        if (!mcreator.getWorkspaceSettings().getDependencies().contains("curios_api") && (modElement instanceof CuriosSlotGUI || modElement instanceof CuriosBaubleGUI)) {
            StringBuilder stringBuilder = new StringBuilder(L10N.t("dialog.curios_api.enable_curios"));
            JOptionPane.showMessageDialog(mcreator, stringBuilder.toString(),
                    L10N.t("dialog.curios_api.error_no_dependency"), JOptionPane.ERROR_MESSAGE);
        }
    }
}