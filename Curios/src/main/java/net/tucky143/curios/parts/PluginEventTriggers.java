package net.tucky143.curios.parts;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import net.mcreator.io.FileIO;
import net.mcreator.io.OS;
import net.mcreator.io.net.WebIO;
import net.mcreator.plugin.MCREvent;
import net.mcreator.plugin.PluginLoader;
import net.mcreator.plugin.PluginUpdateInfo;
import net.mcreator.plugin.events.ui.BlocklyPanelRegisterJSObjects;
import net.mcreator.preferences.PreferencesManager;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.MCreatorApplication;
import net.mcreator.ui.blockly.BlocklyEditorType;
import net.mcreator.ui.blockly.BlocklyPanel;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.component.util.ThreadUtil;
import net.mcreator.ui.dialogs.MCreatorDialog;
import net.mcreator.ui.init.BlocklyJavaScriptsLoader;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;
import net.mcreator.ui.laf.themes.Theme;
import net.mcreator.ui.modgui.ModElementGUI;
import net.mcreator.ui.modgui.ProcedureGUI;
import net.mcreator.ui.variants.modmaker.ModMaker;
import net.mcreator.util.DesktopUtils;
import net.mcreator.util.image.ImageUtils;
import net.mcreator.workspace.elements.VariableTypeLoader;
import net.tucky143.curios.Launcher;
import net.tucky143.curios.ui.modgui.CuriosSlotGUI;
import net.tucky143.curios.ui.modgui.CuriosBaubleGUI;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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