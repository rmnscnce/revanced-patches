package app.revanced.patches.youtube.overlaybutton.downloadbuttonhook.patch

import app.revanced.extensions.toErrorResult
import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.annotation.Version
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructionsWithLabels
import app.revanced.patcher.extensions.InstructionExtensions.getInstruction
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResult
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.util.smali.ExternalLabel
import app.revanced.patches.youtube.overlaybutton.downloadbuttonhook.fingerprints.DownloadActionsFingerprint
import app.revanced.patches.youtube.utils.annotations.YouTubeCompatibility
import app.revanced.util.integrations.Constants.UTILS_PATH

@Name("download-button-hook")
@Description("Replace download button with external download button.")
@YouTubeCompatibility
@Version("0.0.1")
class DownloadButtonHookPatch : BytecodePatch(
    listOf(DownloadActionsFingerprint)
) {
    override fun execute(context: BytecodeContext): PatchResult {
        DownloadActionsFingerprint.result?.let {
            it.mutableMethod.apply {
                val targetIndex = it.scanResult.patternScanResult!!.startIndex

                addInstructionsWithLabels(
                    targetIndex, """
                        invoke-static {}, $UTILS_PATH/HookDownloadButtonPatch;->shouldHookDownloadButton()Z
                        move-result v0
                        if-eqz v0, :default
                        invoke-static {}, $UTILS_PATH/HookDownloadButtonPatch;->startDownloadActivity()V
                        return-void
                        """, ExternalLabel("default", getInstruction(targetIndex))
                )
            }
        } ?: return DownloadActionsFingerprint.toErrorResult()

        return PatchResultSuccess()
    }
}
