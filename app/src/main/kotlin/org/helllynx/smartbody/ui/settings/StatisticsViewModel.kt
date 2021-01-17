package org.helllynx.smartbody.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import org.helllynx.smartbody.repository.SettingsRepository

class SettingsViewModel @ViewModelInject constructor(
        private val settingsRepository: SettingsRepository
) : ViewModel() {


}
