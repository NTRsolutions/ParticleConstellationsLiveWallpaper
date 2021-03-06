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

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.util.AttributeSet
import com.doctoror.particleswallpaper.presentation.di.components.AppComponentProvider
import com.doctoror.particleswallpaper.presentation.di.components.DaggerPreferenceComponent
import com.doctoror.particleswallpaper.presentation.di.scopes.PerPreference
import com.doctoror.particleswallpaper.presentation.presenter.BackgroundColorPreferencePresenter
import com.doctoror.particleswallpaper.presentation.view.BackgroundColorPreferenceView
import javax.inject.Inject

/**
 * Created by Yaroslav Mytkalyk on 31.05.17.
 *
 * Preference for setting background color
 */
class BackgroundColorPreference @JvmOverloads constructor
(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0)
    : ColorPreferenceNoPreview(context, attrs), BackgroundColorPreferenceView, LifecycleObserver {

    @Inject
    @PerPreference
    lateinit var presenter: BackgroundColorPreferencePresenter

    private var value: Int? = null

    init {
        isPersistent = false

        DaggerPreferenceComponent.builder()
                .appComponent(AppComponentProvider.provideAppComponent(context))
                .build()
                .inject(this)

        presenter.onTakeView(this)

        setOnPreferenceChangeListener { _, v ->
            presenter.onPreferenceChange(v as Int?)
            true
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        presenter.onStart()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        presenter.onStop()
    }

    override fun getPersistedInt(defaultReturnValue: Int) = this.value ?: defaultReturnValue

    override fun setColor(color: Int) {
        super.setColor(color)
        value = color
    }
}
