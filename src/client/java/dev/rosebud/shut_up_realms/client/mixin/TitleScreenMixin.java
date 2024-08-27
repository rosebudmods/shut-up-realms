package dev.rosebud.shut_up_realms.client.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

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
	
	@Redirect(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;areRealmsNotificationsEnabled()Z"))
	public boolean areRealmsNotificationsEnabled(TitleScreen instance) {
		return true;
	}
}
