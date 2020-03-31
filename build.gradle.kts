import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel

/** -------------- import & apply plugins -------------- */

plugins {
	idea
}

idea {
	project {
		jdkName = "11"
		languageLevel = IdeaLanguageLevel(JavaVersion.VERSION_11)
	}
}

allprojects {
	/** -------------- project's properties -------------- */

	group = "com.github.anddd7"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		jcenter()
	}

	buildscript {
		repositories {
			jcenter()
		}
	}
}
