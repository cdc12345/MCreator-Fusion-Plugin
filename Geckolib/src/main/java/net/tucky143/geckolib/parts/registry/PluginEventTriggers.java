package net.tucky143.geckolib.parts.registry;

import net.mcreator.plugin.PluginUpdateInfo;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;
import net.mcreator.ui.modgui.ModElementGUI;
import net.mcreator.ui.variants.modmaker.ModMaker;
import net.mcreator.util.image.ImageUtils;
import net.tucky143.geckolib.Launcher;
import net.tucky143.geckolib.elements.GeckolibElement;
import net.tucky143.geckolib.parts.PluginPanelGeckolib;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class PluginEventTriggers {

    public static void dependencyWarning(MCreator mcreator, ModElementGUI modElement) {
        if (!mcreator.getWorkspaceSettings().getDependencies().contains("geckolib") && modElement instanceof GeckolibElement) {
            JOptionPane.showMessageDialog(mcreator, L10N.t("dialog.geckolib.enable_geckolib"),
                    L10N.t("dialog.geckolib.error_no_dependency"), JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void modifyMenus(MCreator mcreator) {
        JMenu geckolib = L10N.menu("menubar.geckolib");
        geckolib.setMnemonic('R');
        geckolib.setIcon(new ImageIcon(ImageUtils.resizeAA(UIRES.get("16px.geckolibicon").getImage(), 17, 17)));
        geckolib.add(Launcher.ACTION_REGISTRY.importGeckoLibModel);
        geckolib.add(Launcher.ACTION_REGISTRY.importDisplaySettings);
        geckolib.addSeparator();
        geckolib.add(Launcher.ACTION_REGISTRY.convertion_to_geckolib);
        geckolib.add(Launcher.ACTION_REGISTRY.convertion_from_geckolib);
        geckolib.addSeparator();
        geckolib.add(Launcher.ACTION_REGISTRY.tutorial);

        if (mcreator instanceof ModMaker modmaker) {
            PluginPanelGeckolib panel = new PluginPanelGeckolib(modmaker.getWorkspacePanel());
            panel.setOpaque(false);

            modmaker.getWorkspacePanel().resourcesPan.addResourcesTab(L10N.t("menubar.geckolib", new Object[0]), panel);
            mcreator.getMainMenuBar().add(geckolib);
        }

    }

}