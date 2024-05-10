package net.twisterrob.astro.build.dsl

import org.gradle.api.Project

val Project.isIdea: Boolean
	get() =
		this
			.providers
			.systemProperty("idea.active")
			.getOrElse("false")
			.toBooleanStrict()

val Project.isIdeaSync: Boolean
	get() =
		this
			.providers
			.systemProperty("idea.sync.active")
			.getOrElse("false")
			.toBooleanStrict()
