package net.twisterrob.astro.build

import dev.detekt.gradle.Detekt
import dev.detekt.gradle.report.ReportMergeTask
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.withType

val detektReportMergeSarif: TaskProvider<ReportMergeTask> = tasks
	.register("detektReportMergeSarif", ReportMergeTask::class.java) {
		output = project.layout.buildDirectory.file("reports/detekt/merge.sarif")
	}

subprojects {
	plugins.withId("dev.detekt") {

		tasks.named("check").configure {
			dependsOn(tasks.withType<Detekt>())
		}

		tasks.named("detekt").configure {
			enabled = false
		}

		tasks.withType<Detekt>().configureEach {
			reports.sarif.required = true
			finalizedBy(detektReportMergeSarif)
		}
		detektReportMergeSarif.configure {
			input.from(tasks.withType<Detekt>().map { it.reports.sarif.outputLocation })
		}
	}
}
