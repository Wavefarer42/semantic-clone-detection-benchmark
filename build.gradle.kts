plugins {
    id("idea")
    kotlin("jvm") version "1.3.72"
}

group = "at.jku.isse"
version = "0.1.0"

val mavenAzure = Action<MavenArtifactRepository> {
    name = "at.jku.isse"
    url = uri("https://pkgs.dev.azure.com/hannes-thaller/_packaging/hannes-thaller/maven/v1")
    credentials {
        username = "hannes-thaller"
        password = System.getenv("GRADIENT_COMMUNITY_AZURE_KEY")
    }
    content {
        includeGroup("at.jku.isse")
    }
}

repositories {
    mavenLocal()
    mavenCentral()
    maven(mavenAzure)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("at.jku.isse:gradient-java-annotations:0.1.5")
    testImplementation("io.kotest:kotest-runner-junit5:4.0.5")
    testImplementation("io.kotest:kotest-assertions-core-jvm:4.0.5")
    testImplementation("io.kotest:kotest-property-jvm:4.0.5")
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.name
    }
    withType<Test> {
        useJUnitPlatform()
    }
}