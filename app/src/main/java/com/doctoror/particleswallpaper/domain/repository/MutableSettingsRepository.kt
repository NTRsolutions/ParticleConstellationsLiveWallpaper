/*
 * Copyright (C) 2018 Yaroslav Mytkalyk
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
package com.doctoror.particleswallpaper.domain.repository

import android.support.annotation.ColorInt

/**
 * Created by Yaroslav Mytkalyk on 31.05.17.
 *
 * [SettingsRepository] with mutators for settings
 */
interface MutableSettingsRepository: SettingsRepository {

    fun setNumDots(numDots: Int)
    fun setFrameDelay(frameDelay: Int)
    fun setStepMultiplier(stepMultiplier: Float)
    fun setDotScale(dotScale: Float)
    fun setLineScale(lineScale: Float)
    fun setLineDistance(lineDistance: Float)
    fun setParticlesColor(@ColorInt color: Int)
    fun setBackgroundUri(uri: String)
    fun setBackgroundColor(@ColorInt color: Int)
}
