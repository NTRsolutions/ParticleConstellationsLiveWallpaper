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
package com.doctoror.particleswallpaper.data.file

import android.content.Context
import android.net.Uri
import android.support.v4.content.FileProvider
import com.doctoror.particleswallpaper.R
import java.io.File

class FileUriResolver(private val context: Context) {

    /**
     * Resolve [File] [Uri] for default [FileProvider]
     */
    fun getUriForFile(file: File): Uri? = FileProvider
            .getUriForFile(context, context.getString(R.string.file_provider_authority), file)
}
