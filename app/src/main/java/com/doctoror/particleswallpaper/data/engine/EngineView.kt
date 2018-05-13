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
package com.doctoror.particleswallpaper.data.engine

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.VisibleForTesting
import android.view.SurfaceHolder
import com.doctoror.particlesdrawable.GLParticlesSceneRenderer

class EngineView(private val renderer: GLParticlesSceneRenderer) {

    val backgroundPaint = Paint()

    var background: Drawable? = null

    @VisibleForTesting
    var width = 0
        private set

    @VisibleForTesting
    var height = 0
        private set

    @JvmField
    @VisibleForTesting
    var surfaceHolder: SurfaceHolder? = null

    init {
        backgroundPaint.style = Paint.Style.FILL
        backgroundPaint.color = Color.BLACK
    }

    fun setBackgroundColor(@ColorInt color: Int) {
        backgroundPaint.color = color
        renderer.setBackgroundColor(color)
    }

    fun setDimensions(width: Int, height: Int) {
        this.width = width
        this.height = height
        renderer.setDimensions(width, height)
        background?.setBounds(0, 0, width, height)
        surfaceHolder = null
    }

    fun start() {
        surfaceHolder = null
        renderer.start()
    }

    fun stop() {
        renderer.stop()
        surfaceHolder = null
    }

    fun resetSurfaceCache() {
        surfaceHolder = null
    }

    // Inline for avoiding extra method call in draw
    @Suppress("NOTHING_TO_INLINE")
    private inline fun drawBackground(c: Canvas) {
        val background = background
        if (background == null) {
            drawBackgroundColor(c)
        } else {
            if (background is BitmapDrawable) {
                background.bitmap?.let {
                    if (it.hasAlpha()) {
                        drawBackgroundColor(c)
                    }
                }
            }
            background.draw(c)
        }
    }

    private fun drawBackgroundColor(c: Canvas) {
        c.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
    }
}
