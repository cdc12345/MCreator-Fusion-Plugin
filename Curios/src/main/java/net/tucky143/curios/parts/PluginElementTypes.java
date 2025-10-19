package net.tucky143.curios.parts;

import net.mcreator.element.ModElementType;
import net.mcreator.generator.GeneratorFlavor;
import net.tucky143.curios.ui.modgui.*;
import net.tucky143.curios.elements.*;

import static net.mcreator.element.ModElementTypeLoader.register;
import static net.mcreator.generator.GeneratorFlavor.BaseLanguage.JAVA;

public class PluginElementTypes {
    public static ModElementType<?> CURIOSBAUBLE;
    public static ModElementType<?> CURIOSSLOT;

    public static void load() {

        CURIOSBAUBLE = register(
                new ModElementType<>("curiosbauble", (Character) 'B', CuriosBaubleGUI::new, CuriosBauble.class)
        );

        CURIOSSLOT = register(
                new ModElementType<>("curiosslot", (Character) 'S', CuriosSlotGUI::new, CuriosSlot.class)
        );
    }
}
