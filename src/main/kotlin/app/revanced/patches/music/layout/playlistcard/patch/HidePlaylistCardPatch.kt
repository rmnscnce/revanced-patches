package app.revanced.patches.music.layout.playlistcard.patch

import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.annotation.Version
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.PatchResult
import app.revanced.patcher.patch.PatchResultSuccess
import app.revanced.patcher.patch.annotations.DependsOn
import app.revanced.patcher.patch.annotations.Patch
import app.revanced.patches.music.utils.annotations.MusicCompatibility
import app.revanced.patches.music.utils.litho.patch.LithoFilterPatch
import app.revanced.patches.music.utils.settings.resource.patch.SettingsPatch
import app.revanced.util.enum.CategoryType
import app.revanced.util.integrations.Constants.MUSIC_ADS_PATH

@Patch
@Name("hide-playlist-card")
@Description("Hides the playlist card from homepage.")
@DependsOn(
    [
        LithoFilterPatch::class,
        SettingsPatch::class
    ]
)
@MusicCompatibility
@Version("0.0.1")
class HidePlaylistCardPatch : BytecodePatch() {
    override fun execute(context: BytecodeContext): PatchResult {

        SettingsPatch.addMusicPreference(
            CategoryType.LAYOUT,
            "revanced_hide_playlist_card",
            "false"
        )

        LithoFilterPatch.addFilter(FILTER_CLASS_DESCRIPTOR)

        return PatchResultSuccess()
    }

    private companion object {
        private const val FILTER_CLASS_DESCRIPTOR =
            "$MUSIC_ADS_PATH/PlaylistCardFilter;"
    }
}
