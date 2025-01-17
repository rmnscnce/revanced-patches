package app.revanced.patches.reddit.layout.premiumicon.patch

import app.revanced.extensions.toErrorResult
import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.annotation.Version
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResult
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.annotations.Patch
import app.revanced.patches.reddit.layout.premiumicon.fingerprints.PremiumIconFingerprint
import app.revanced.patches.reddit.utils.annotations.RedditCompatibility

@Patch
@Name("premium-icon-reddit")
@Description("Unlocks premium Reddit app icons.")
@RedditCompatibility
@Version("0.0.1")
class PremiumIconPatch : BytecodePatch(
    listOf(PremiumIconFingerprint)
) {
    override fun execute(context: BytecodeContext): PatchResult {

        PremiumIconFingerprint.result?.let {
            it.mutableMethod.apply {
                addInstructions(
                    0, """
                        const/4 v0, 0x1
                        return v0
                        """
                )
            }
        } ?: return PremiumIconFingerprint.toErrorResult()

        return PatchResultSuccess()
    }
}
