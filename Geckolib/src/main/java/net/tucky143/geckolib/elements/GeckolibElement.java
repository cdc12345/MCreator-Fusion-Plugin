package net.tucky143.geckolib.elements;

import net.mcreator.ui.validation.AggregatedValidationResult;

public interface GeckolibElement {
    AggregatedValidationResult validatePage(int page);
}
