package sh.kau.playground.common.log

import logcat.LogPriority
import logcat.LogcatLogger

class CompositeLogger(
    private val loggers: Set<LogcatLogger>,
) : LogcatLogger {

  override fun isLoggable(priority: LogPriority) = true

  override fun log(priority: LogPriority, tag: String, message: String) {
    loggers.forEach { logger ->
      if (logger.isLoggable(priority)) {
        logger.log(priority, tag, message)
      }
    }
  }

  companion object {
    fun install(loggers: Set<LogcatLogger>) {
      if (!LogcatLogger.isInstalled) {
        LogcatLogger.install(CompositeLogger(loggers))
      }
    }
  }
}
