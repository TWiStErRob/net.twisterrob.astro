dependencyResolutionManagement {
	versionCatalogs {
		create("libs") {
			from(files("../libs.versions.toml"))
		}
	}
	@Suppress("UnstableApiUsage")
	repositories {
		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
		google()
		mavenCentral()
	}
}
