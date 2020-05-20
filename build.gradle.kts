plugins {
    id("idea")
    kotlin("jvm") version "1.3.72"
    application
    id("at.jku.isse.gradient-gradle-plugin") version "0.8.6"
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
    implementation("at.jku.isse:gradient-java-client:0.9.0")
    implementation("io.github.microutils:kotlin-logging:1.7.9")
    implementation("ch.qos.logback:logback-classic:1.2.3")
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
    mainClassName = "at.jku.isse.clones.harness.MainKt"
}

gradient {
    groupName.set("at.jku.isse")
    projectName.set("semantic-clones")
    includes.set("at.jku.isse.clones..*")
}