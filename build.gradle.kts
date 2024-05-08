// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.3.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    // Add the dependency for the Google services Gradle plugin
    id("com.google.gms.google-services") version "4.4.1" apply false
    kotlin("jvm") version "1.3.21"
}

//tasks.register<Copy>("copyPreCommitHook") {
//    description = "Copy pre-commit hook from scripts to the .git/hooks directory"
//    group = "git hooks"
//    outputs.upToDateWhen { false }
//    from("$rootDir/scripts/pre-commit")
//    into("$rootDir/.git/hooks/")
//}
//
//tasks.build {
//    dependsOn("copyPreCommitHook")
//}

tasks.register("copyPreCommitHook", Copy::class.java) {
    description = "Copies the git hooks from /git-hooks to the .git folder."
    group = "git hooks"
    from("$rootDir/scripts/pre-commit")
    into("$rootDir/.git/hooks/")
}

afterEvaluate {
    tasks.getByPath(":app:preBuild").dependsOn(":copyPreCommitHook")
}
