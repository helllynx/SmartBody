package org.helllynx.smartbody.ui.settings

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceFragmentCompat
import dagger.hilt.android.AndroidEntryPoint
import org.helllynx.smartbody.R


@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.fragment_settings, rootKey)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkNotNull(findPreference(PERSONAL_INFO_PREF)).setOnPreferenceClickListener {
            findNavController().navigate(R.id.settings_fragment_dest)
            true
        }
    }

    companion object {
        private const val PERSONAL_INFO_PREF = "personal_info_pref"
    }
}
