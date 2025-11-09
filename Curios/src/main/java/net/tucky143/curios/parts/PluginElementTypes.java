package net.tucky143.curios.parts;

import net.mcreator.element.ModElementType;
import net.mcreator.generator.GeneratorFlavor;
import net.tucky143.curios.elements.v1201.CuriosBauble;
import net.tucky143.curios.elements.v1201.CuriosSlot;
import net.tucky143.curios.ui.modgui.v1201.CuriosBaubleGUI;
import net.tucky143.curios.ui.modgui.v1201.CuriosSlotGUI;

import static net.mcreator.element.ModElementTypeLoader.register;

public class PluginElementTypes {
    public static ModElementType<?> CURIOSBAUBLE_1201;
    public static ModElementType<?> CURIOSSLOT_1201;

    public static void load() {

        CURIOSBAUBLE_1201 = register(
                new ModElementType<>("curiosbauble", (Character) 'B', CuriosBaubleGUI::new, CuriosBauble.class)
        ).coveredOn(GeneratorFlavor.FORGE);

        CURIOSSLOT_1201 = register(
                new ModElementType<>("curiosslot", (Character) 'S', CuriosSlotGUI::new, CuriosSlot.class)
        ).coveredOn(GeneratorFlavor.FORGE);
    }
}
