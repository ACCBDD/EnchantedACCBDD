plugins {
    `java`
    `maven-publish`
    `idea`
    `eclipse`
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)

    withSourcesJar()
    withJavadocJar()
}

idea {
    module {
        isDownloadSources = true
        isDownloadJavadoc = true
    }
}

val libs = project.versionCatalogs.find("libs")
val version = libs.get().findVersion("enchanted").get()

val minecraft_version = libs.get().findVersion("minecraft").get()
val minecraft_version_range = libs.get().findVersion("minecraft.range").get()
val neoforge_version = libs.get().findVersion("neoforge").get()
val neoforge_version_range = libs.get().findVersion("neoforge.range").get()
val neoforge_loader_range = libs.get().findVersion("neoforge.loader.range").get()
val fapi_version = libs.get().findVersion("fabric.api").get()
val fabric_version = libs.get().findVersion("fabric").get()

tasks.withType<Jar>().configureEach {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_Enchanted" }
    }

    manifest {
        attributes(mapOf(
            "Specification-Title"     to "Enchanted",
            "Specification-Vendor"    to "Favouriteless",
            "Specification-Version"   to version,
            "Implementation-Title"    to "Enchanted",
            "Implementation-Version"  to version,
            "Implementation-Vendor"   to "Favouriteless",
            "Built-On-Minecraft"      to minecraft_version
        ))
    }
}

tasks.withType<JavaCompile>().configureEach {
    this.options.encoding = "UTF-8"
    this.options.getRelease().set(21)
}

tasks.withType<ProcessResources>().configureEach {
    val expandProps = mapOf(
        "version" to version,
        "minecraft_version" to minecraft_version,
        "neoforge_version" to neoforge_version,
        "neoforge_version_range" to neoforge_version_range,
        "neoforge_loader_range" to neoforge_loader_range,
        "minecraft_version_range" to minecraft_version_range,
        "fabric_api_version" to fapi_version,
        "fabric_loader_version" to fabric_version,
    )

    filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/neoforge.mods.toml", "*.mixins.json")) {
        expand(expandProps)
    }

    inputs.properties(expandProps)
}

// Disables Gradle's custom module metadata from being published to maven. The
// metadata includes mapped dependencies which are not reasonably consumable by
// other mod developers.
tasks.withType<GenerateModuleMetadata>().configureEach {
    enabled = false
}

publishing {
    repositories {
        if (System.getenv("FAVOURITELESS_MAVEN_USER") == null && System.getenv("FAVOURITELESS_MAVEN_PASS") == null) {
            mavenLocal()
        }
        else maven {
            name = "FavouritelessReleases"
            url = uri("https://maven.favouriteless.net/releases")

            credentials {
                username = System.getenv("FAVOURITELESS_MAVEN_USER")
                password = System.getenv("FAVOURITELESS_MAVEN_PASS")
            }
        }
    }
}