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
package com.doctoror.particleswallpaper.presentation.preference

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.preference.Preference
import android.util.AttributeSet
import com.doctoror.particleswallpaper.domain.interactor.OpenChangeWallpaperIntentProvider
import com.doctoror.particleswallpaper.domain.interactor.OpenChangeWallpaperIntentUseCase
import com.doctoror.particleswallpaper.presentation.actions.FragmentStartActivityForResultAction
import com.doctoror.particleswallpaper.presentation.di.components.AppComponentProvider
import com.doctoror.particleswallpaper.presentation.di.components.DaggerPreferenceComponent
import com.doctoror.particleswallpaper.presentation.presenter.PreviewPreferencePresenter
import com.doctoror.particleswallpaper.presentation.view.MvpView
import javax.inject.Inject

/**
 * Created by Yaroslav Mytkalyk on 31.05.17.
 *
 * Preference for showing the wallpaper preview.
 */
class PreviewPreference @JvmOverloads constructor
(contextParam: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : Preference(contextParam, attrs), MvpView, FragmentHolder {

    @Inject
    lateinit var intentProvider: OpenChangeWallpaperIntentProvider

    private val presenter: PreviewPreferencePresenter

    override var fragment: Fragment? = null
        set(f) {
            presenter.useCase = if (f != null) newOpenChangeWallpaperIntentUseCase(f) else null
            presenter.host = f
            field = f
        }

    init {
        isPersistent = false
        DaggerPreferenceComponent.builder()
                .appComponent(AppComponentProvider.provideAppComponent(context))
                .build()
                .inject(this)

        presenter = PreviewPreferencePresenter(contextParam as Activity)
        presenter.onTakeView(this)
    }

    override fun onClick() {
        presenter.onClick()
    }

    private fun newOpenChangeWallpaperIntentUseCase(host: Fragment) =
            OpenChangeWallpaperIntentUseCase(
                    intentProvider,
                    FragmentStartActivityForResultAction(host))
}
