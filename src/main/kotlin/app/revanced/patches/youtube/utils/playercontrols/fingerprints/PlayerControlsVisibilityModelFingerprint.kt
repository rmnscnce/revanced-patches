package app.revanced.patches.youtube.utils.playercontrols.fingerprints

import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import org.jf.dexlib2.Opcode

object PlayerControlsVisibilityModelFingerprint : MethodFingerprint(
    opcodes = listOf(Opcode.IGET_BOOLEAN),
    strings = listOf("Missing required properties:", "hasNext", "hasPrevious")
)