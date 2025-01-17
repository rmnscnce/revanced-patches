package app.revanced.patches.youtube.player.suggestactions.fingerprints

import app.revanced.patcher.fingerprint.method.impl.MethodFingerprint
import app.revanced.patches.youtube.utils.resourceid.patch.SharedResourceIdPatch.Companion.SuggestedAction
import app.revanced.util.bytecode.isWideLiteralExists
import org.jf.dexlib2.Opcode

object SuggestedActionsFingerprint : MethodFingerprint(
    returnType = "V",
    opcodes = listOf(
        Opcode.CONST,
        Opcode.CONST_4,
        Opcode.INVOKE_VIRTUAL,
        Opcode.MOVE_RESULT_OBJECT
    ),
    customFingerprint = { methodDef, _ -> methodDef.isWideLiteralExists(SuggestedAction) }
)