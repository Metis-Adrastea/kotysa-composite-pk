import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    application
}

group = "kotysa"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}



tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}

dependencies {
    implementation("org.ufoss.kotysa:kotysa-r2dbc:2.1.1")
    implementation("org.postgresql:r2dbc-postgresql:0.9.2.RELEASE")
}
