package net.tucky143.curios;

import net.mcreator.blockly.data.BlocklyLoader;
import net.mcreator.generator.Generator;
import net.mcreator.generator.GeneratorFlavor;
import net.mcreator.plugin.JavaPlugin;
import net.mcreator.plugin.Plugin;
import net.mcreator.plugin.events.PreGeneratorsLoadingEvent;
import net.mcreator.plugin.events.WorkspaceBuildStartedEvent;
import net.mcreator.plugin.events.ui.ModElementGUIEvent;
import net.mcreator.plugin.events.workspace.MCreatorLoadedEvent;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.action.BasicAction;
import net.mcreator.ui.blockly.BlocklyEditorType;
import net.mcreator.ui.component.TransparentToolBar;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;
import net.mcreator.ui.modgui.BiomeGUI;
import net.mcreator.ui.modgui.ModElementGUI;
import net.mcreator.ui.workspace.AbstractWorkspacePanel;
import net.tucky143.curios.parts.PluginElementTypes;
import net.tucky143.curios.parts.PluginEventTriggers;
import net.tucky143.curios.ui.modgui.CuriosSlotGUI;

import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.mcreator.element.parts.IWorkspaceDependent.LOG;

public class Launcher extends JavaPlugin {

	public static Set<Plugin> PLUGIN_INSTANCE = new HashSet<>();

	//public static void disableComponent(ModElementGUI gui, Field field) throws Exception {
	//	field.setAccessible(true);
	//	((JComponent)field.get(gui)).setEnabled(false);
	//}

	public Launcher(Plugin plugin) {
		super(plugin);

		addListener(PreGeneratorsLoadingEvent.class, e -> {
			PluginElementTypes.load();
        });

		addListener(ModElementGUIEvent.BeforeLoading.class, event -> SwingUtilities.invokeLater(() -> {
			PluginEventTriggers.dependencyWarning(event.getMCreator(), event.getModElementGUI());
		}));

		addListener(WorkspaceBuildStartedEvent.class, event -> {
            Generator currentGenerator = event.getMCreator().getGenerator();
            if (currentGenerator.getGeneratorConfiguration().getGeneratorFlavor() == GeneratorFlavor.FORGE) {
				GradlePropertiesUpdater.main(event.getMCreator().getWorkspaceFolder().toString());
			} else {
				GradlePropertiesUpdater.SetTrue(event.getMCreator().getWorkspaceFolder().toString());
			}
		});

		addListener(MCreatorLoadedEvent.class, event -> {
            Generator currentGenerator = event.getMCreator().getGenerator();
            if (currentGenerator.getGeneratorConfiguration().getGeneratorFlavor() == GeneratorFlavor.FORGE) {
                GradlePropertiesUpdater.main(event.getMCreator().getWorkspaceFolder().toString());
            } else {
                GradlePropertiesUpdater.SetTrue(event.getMCreator().getWorkspaceFolder().toString());
            }
		});

        //addListener(ModElementGUIEvent.AfterLoading.class, event -> {
       //     if (event.getModElementGUI() instanceof CuriosSlotGUI slotGUI) {
       //             try {
       //                 disableComponent(slotGUI, slotGUI.getClass().getDeclaredField("items"));
       //             } catch (Exception e) {
      //                  e.printStackTrace();
       //             }
        //    }
     //   });

		LOG.info("Plugin was loaded");
	}
}