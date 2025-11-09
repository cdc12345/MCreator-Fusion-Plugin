package net.tucky143.geckolib;

import net.mcreator.generator.Generator;
import net.mcreator.generator.GeneratorFlavor;
import net.mcreator.plugin.JavaPlugin;
import net.mcreator.plugin.Plugin;
import net.mcreator.plugin.events.PreGeneratorsLoadingEvent;
import net.mcreator.plugin.events.WorkspaceBuildStartedEvent;
import net.mcreator.plugin.events.ui.ModElementGUIEvent;
import net.mcreator.plugin.events.workspace.MCreatorLoadedEvent;
import net.tucky143.geckolib.parts.registry.PluginActions;
import net.tucky143.geckolib.parts.registry.PluginElementTypes;
import net.tucky143.geckolib.parts.registry.PluginEventTriggers;

import javax.swing.*;

import java.awt.*;

import static net.mcreator.element.parts.IWorkspaceDependent.LOG;

public class Launcher extends JavaPlugin {

	public static PluginActions ACTION_REGISTRY;

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
			ACTION_REGISTRY = new PluginActions(event.getMCreator());
            SwingUtilities.invokeLater(() -> {
                try {
                    PluginEventTriggers.modifyMenus(event.getMCreator());
                } catch (Exception ignored) {}
            });
            Generator currentGenerator = event.getMCreator().getGenerator();
            if (currentGenerator.getGeneratorConfiguration().getGeneratorFlavor() == GeneratorFlavor.FORGE) {
                GradlePropertiesUpdater.main(event.getMCreator().getWorkspaceFolder().toString());
            } else {
                GradlePropertiesUpdater.SetTrue(event.getMCreator().getWorkspaceFolder().toString());
            }
		});

		LOG.info("Plugin was loaded");
	}
}