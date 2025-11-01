package dev.trindadedev.thecatapi

import android.app.Application
import com.google.android.material.color.DynamicColors

class TheCatApiApp: Application ()
{
  init
  {
    DynamicColors.applyToActivitiesIfAvailable (this);
  }
}