package dev.rosebud.shut_up_realms.client.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	/**
	 * @author ix0rai
	 * @reason shush!
	 */
	@Overwrite
	private boolean areRealmsNotificationsEnabled() {
		return false;
	}
}
