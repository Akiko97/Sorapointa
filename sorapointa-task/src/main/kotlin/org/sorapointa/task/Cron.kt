package org.sorapointa.task

import com.cronutils.model.Cron
import com.cronutils.model.CronType.UNIX
import com.cronutils.model.definition.CronDefinition
import com.cronutils.model.definition.CronDefinitionBuilder
import com.cronutils.model.time.ExecutionTime
import com.cronutils.parser.CronParser
import kotlinx.datetime.Instant
import kotlinx.datetime.toKotlinInstant
import org.sorapointa.utils.now
import org.sorapointa.utils.toZonedUtc
import org.sorapointa.utils.unwrap

private val logger = mu.KotlinLogging.logger { }

/**
 * Cron definition, UNIX-style
 * @see <a href="https://en.wikipedia.org/wiki/Cron">Wikipedia - Cron</a>
 */
private val cronDefinition: CronDefinition by lazy {
    CronDefinitionBuilder.instanceDefinitionFor(UNIX)
}

private val parser by lazy { CronParser(cronDefinition) }

/**
 * Parse UNIX-styled Cron String
 * @return [Cron] on success, null on failure
 * @see cronDefinition
 */
fun parseCron(cron: String): Cron = parser.parse(cron)

/**
 * Variety of [parseCron], return null when failed to parse
 */
@Suppress("unused")
fun parseCronOrNull(cron: String): Cron? = runCatching {
    parseCron(cron)
}.onFailure {
    logger.warn(it) { "Failed to parse cron expression $cron" }
}.getOrNull()

/**
 * Next execution time from [time] of [Cron] expression
 */
fun Cron.nextExecutionTime(time: Instant = now()): Instant? {
    val executionTime = ExecutionTime.forCron(this)
    return executionTime.nextExecution(time.toZonedUtc()).unwrap()
        ?.toInstant()?.toKotlinInstant()
}

/**
 * Last execution time from [time] of [Cron] expression
 */
@Suppress("unused")
fun Cron.lastExecutionTime(time: Instant = now()): Instant? {
    val executionTime = ExecutionTime.forCron(this)
    return executionTime.lastExecution(time.toZonedUtc()).unwrap()
        ?.toInstant()?.toKotlinInstant()
}
