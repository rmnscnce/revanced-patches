package app.revanced.patches.youtube.video.quality.fingerprints

import app.revanced.patcher.extensions.or
import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import org.jf.dexlib2.AccessFlags

object VideoQualitySetterFingerprint : MethodFingerprint(
    returnType = "V",
    accessFlags = AccessFlags.PUBLIC or AccessFlags.FINAL,
    parameters = listOf("L"),
    strings = listOf("VIDEO_QUALITIES_MENU_BOTTOM_SHEET_FRAGMENT")
)