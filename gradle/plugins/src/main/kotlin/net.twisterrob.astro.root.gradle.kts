import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

val detektReportMergeSarif: TaskProvider<ReportMergeTask> = tasks
	.register("detektReportMergeSarif", ReportMergeTask::class.java) {
		output = project.layout.buildDirectory.file("reports/detekt/merge.sarif")
	}

subprojects {
	plugins.withId("io.gitlab.arturbosch.detekt") {

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
			input.from(tasks.withType<Detekt>().map { it.sarifReportFile })
		}
	}
}
