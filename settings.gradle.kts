pluginManagement {
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
        maven(mavenAzure)
        gradlePluginPortal()
    }
}