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
import com.doctoror.particleswallpaper.domain.interactor.ResetToDefaultsUseCase
import com.doctoror.particleswallpaper.presentation.di.scopes.PerPreference
import com.doctoror.particleswallpaper.presentation.view.ResetToDefaultsPreferenceView
import javax.inject.Inject

/**
 * Created by Yaroslav Mytkalyk on 03.06.17.
 *
 * Presenter for [com.doctoror.particleswallpaper.presentation.preference.ResetToDefaultsPreference]
 */
@PerPreference
class ResetToDefaultsPreferencePresenter @Inject constructor(
        private val schedulers: SchedulersProvider,
        private val useCase: ResetToDefaultsUseCase)
    : Presenter<ResetToDefaultsPreferenceView> {

    private lateinit var view: ResetToDefaultsPreferenceView

    override fun onTakeView(view: ResetToDefaultsPreferenceView) {
        this.view = view
    }

    fun onClick() {
        view.showWarningDialog()
    }

    fun onResetClick() {
        useCase.useCase()
                .subscribeOn(schedulers.io())
                .subscribe()
    }
}
