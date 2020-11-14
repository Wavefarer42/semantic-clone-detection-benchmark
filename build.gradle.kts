plugins {
    id("idea")
    kotlin("jvm") version "1.3.72"
    application
}

group = "at.jku.isse"
version = "0.1.0"

repositories {
    mavenLocal()
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("fr.inria.gforge.spoon:spoon-core:8.3.0")
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
    testImplementation("io.kotest:kotest-runner-junit5:4.0.5")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.5")
    testImplementation("io.kotest:kotest-property-jvm:4.0.5")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }
    withType<Test> {
        useJUnitPlatform()
    }
}

application {
    mainClassName = "at.jku.isse.clones.MainKt"
}
