plugins {
    kotlin("jvm") version "1.9.23"
}

group = "puk3p.tuiasi.ro"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}