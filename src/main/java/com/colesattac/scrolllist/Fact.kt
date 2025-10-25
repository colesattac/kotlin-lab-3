package com.colesattac.scrolllist

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Fact(
    @StringRes val titleRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)
