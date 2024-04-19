plugins {
    `java`
    `maven-publish`
    `idea`
    `eclipse`
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)

    withSourcesJar()
    withJavadocJar()
}

val libs = project.versionCatalogs.find("libs")

val mod_id: String by project
val mod_name: String by project
val author: String by project
val license: String by project
val description: String by project
val version = libs.get().findVersion("enchanted").get()
val minecraft_version = libs.get().findVersion("minecraft").get()
val forge_version = libs.get().findVersion("forge").get()
val forge_version_range = libs.get().findVersion("forge.range").get()
val fml_version_range = libs.get().findVersion("forge.fml.range").get()
val minecraft_version_range = libs.get().findVersion("minecraft.range").get()
val fapi_version = libs.get().findVersion("fabric.api").get()
val fabric_version = libs.get().findVersion("fabric").get()

val geckolib_forge_version = libs.get().findVersion("geckolib.forge").get()
val geckolib_fabric_version = libs.get().findVersion("geckolib.fabric").get()
val patchouli_forge_version = libs.get().findVersion("patchouli.forge").get()
val patchouli_fabric_version = libs.get().findVersion("patchouli.fabric").get()
val stateobserver_version = libs.get().findVersion("stateobserver").get()
val sbl_version = libs.get().findVersion("sbl").get()
val jei_version = libs.get().findVersion("jei").get()
val forgeconfigapi_version = libs.get().findVersion("forgeconfigapi").get()

tasks.withType<Jar>().configureEach {
    from(rootProject.file("LICENSE")) {
        rename { "${it}_${mod_name}" }
    }

    manifest {
        attributes(mapOf(
                "Specification-Title"     to mod_name,
                "Specification-Vendor"    to author,
                "Specification-Version"   to version,
                "Implementation-Title"    to mod_name,
                "Implementation-Version"  to version,
                "Implementation-Vendor"   to author,
                "Built-On-Minecraft"      to minecraft_version
        ))
    }
}

tasks.withType<JavaCompile>().configureEach {
    this.options.encoding = "UTF-8"
    this.options.getRelease().set(17)
}

tasks.withType<ProcessResources>().configureEach {
    val expandProps = mapOf(
            "version" to version,
            "group" to project.group, // Else we target the task's group.
            "minecraft_version" to minecraft_version,
            "forge_version" to forge_version,
            "forge_loader_range" to fml_version_range,
            "forge_version_range" to forge_version_range,
            "minecraft_version_range" to minecraft_version_range,
            "fabric_api_version" to fapi_version,
            "fabric_loader_version" to fabric_version,
            "mod_name" to mod_name,
            "author" to author,
            "mod_id" to mod_id,
            "license" to license,
            "description" to description,
            "geckolib_forge_version" to geckolib_forge_version,
            "geckolib_fabric_version" to geckolib_fabric_version,
            "patchouli_forge_version" to patchouli_forge_version,
            "patchouli_fabric_version" to patchouli_fabric_version,
            "stateobserver_version" to stateobserver_version,
            "sbl_version" to sbl_version,
            "jei_version" to jei_version,
            "forgeconfigapi_version" to forgeconfigapi_version
    )

    filesMatching(listOf("pack.mcmeta", "fabric.mod.json", "META-INF/mods.toml", "*.mixins.json")) {
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
        mavenLocal()
    }
}