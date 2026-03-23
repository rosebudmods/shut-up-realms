package dev.rosebud.shut_up_realms.client.mixin;

import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
	/**
	 * @author eristhea
	 * @reason shush!
	 */
	@Overwrite
	private boolean realmsNotificationsEnabled() {
		return false;
	}

	// note: required in order to properly initialize the realms screen and prevent null pointers
	@Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;realmsNotificationsEnabled()Z"))
	public boolean realmsNotificationsEnabled(TitleScreen instance) {
		return true;
	}
}
