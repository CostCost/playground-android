package sh.kau.playground.domain.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import sh.kau.playground.domain.shared.App

/**
 * Usage:
 * ```kotlin
 * @Composable
 * fun MyComposable() {
 *     val app: App = LocalContext.current.app()
 *     // Use app instance...
 * }
 * ```
 *
 * This provides a type-safe way to access the Application instance from within a Composable,
 * typically used when trying to get a DI component setup.
 */
@Composable
fun Context.app(): App {
  return remember(this) { applicationContext as App }
}
