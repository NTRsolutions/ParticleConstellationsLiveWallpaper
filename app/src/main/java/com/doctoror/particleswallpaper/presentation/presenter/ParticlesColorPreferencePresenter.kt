/*
 * Copyright (C) 2017 Yaroslav Mytkalyk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.doctoror.particleswallpaper.presentation.presenter

import com.doctoror.particleswallpaper.domain.execution.SchedulersProvider
import com.doctoror.particleswallpaper.domain.repository.MutableSettingsRepository
import com.doctoror.particleswallpaper.domain.repository.SettingsRepository
import com.doctoror.particleswallpaper.presentation.di.qualifiers.Default
import com.doctoror.particleswallpaper.presentation.di.scopes.PerPreference
import com.doctoror.particleswallpaper.presentation.view.ParticlesColorPreferenceView
import io.reactivex.disposables.Disposable
import javax.inject.Inject

/**
 * Created by Yaroslav Mytkalyk on 03.06.17.
 *
 * Presenter for [com.doctoror.particleswallpaper.presentation.preference.ParticlesColorPreference]
 */
@PerPreference
class ParticlesColorPreferencePresenter @Inject constructor(
        private val schedulers: SchedulersProvider,
        private val settings: MutableSettingsRepository,
        @Default private val defaults: SettingsRepository)
    : Presenter<ParticlesColorPreferenceView> {

    private lateinit var view: ParticlesColorPreferenceView

    private var disposable: Disposable? = null

    override fun onTakeView(view: ParticlesColorPreferenceView) {
        this.view = view
    }

    fun onPreferenceChange(v: Int?) {
        val color = v ?: defaults.getParticlesColor().blockingFirst()
        settings.setParticlesColor(color)
    }

    fun onClick() {
        view.showPreferenceDialog()
    }

    fun onStart() {
        disposable = settings.getParticlesColor()
                .observeOn(schedulers.mainThread())
                .subscribe { view.setColor(it) }
    }

    fun onStop() {
        disposable?.dispose()
    }
}
