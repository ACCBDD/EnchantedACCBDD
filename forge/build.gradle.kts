import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.minecraftforge.gradle.userdev.tasks.JarJar

plugins {
    id("enchanted-convention")

    alias(libs.plugins.minotaur)
    alias(libs.plugins.curseforgegradle)
    alias(libs.plugins.forgegradle)
    alias(libs.plugins.mixin)
    alias(libs.plugins.parchmentforgegradle)
}

val mod_id: String by project
version = libs.versions.enchanted.get()
val minecraft_version = libs.versions.minecraft.asProvider().get()
val mappings_minecraft_version = libs.versions.parchment.minecraft.get()
val parchment_version = libs.versions.parchment.asProvider().get()
val forge_version = libs.versions.forge.asProvider().get()

base {
    archivesName = "${mod_id}-forge-${minecraft_version}"
}

jarJar.enable()

minecraft {
    mappings("parchment", "${mappings_minecraft_version}-${parchment_version}-${minecraft_version}")
    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

    copyIdeResources = true

    runs {
        sourceSets.forEach {
            val dir = layout.buildDirectory.dir("sourcesSets/${it}.name")

            it.output.setResourcesDir(dir)
            it.java.destinationDirectory = dir
        }

        create("client") {
            workingDirectory(project.file("runs/$name"))
            ideaModule("${rootProject.name}.${project.name}.main")
            isSingleInstance = true
            taskName("Client")
            args("--username", "Favouriteless", "--uuid", "9410df73-6be3-41d5-a620-51b2e9be667b")

            property("forge.logging.console.level", "debug")
            property("mixin.env.remapRefMap", "true")

            property("mixin.env.refMapRemappingFile", "${project.projectDir}/build/createSrgToMcp/output.srg")

            mods {
                create(mod_id) {
                    source(sourceSets.getByName("main"))
                    source(project(":common").sourceSets.getByName("main"))
                }
            }
        }

        create("server") {
            workingDirectory(project.file("runs/$name"))
            ideaModule("${rootProject.name}.${project.name}.main")
            isSingleInstance = true
            taskName("Server")

            property("forge.logging.console.level", "debug")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${project.projectDir}/build/createSrgToMcp/output.srg")

            mods {
                create(mod_id) {
                    source(project(":common").sourceSets.main.get())
                    source(sourceSets.main.get())
                }
            }
        }

        create("data") {
            workingDirectory(project.file("runs/$name"))
            ideaModule("${rootProject.name}.${project.name}.main")
            isSingleInstance = true
            taskName("Data")

            property("forge.logging.console.level", "debug")
            property("mixin.env.remapRefMap", "true")
            property("mixin.env.refMapRemappingFile", "${project.projectDir}/build/createSrgToMcp/output.srg")
            args("--mod", mod_id, "--all",
                "--output", project(":common").file("src/generated/resources/").absolutePath,
                "--existing", project(":common").file("src/main/resources/").absolutePath
            )

            mods {
                create(mod_id) {
                    source(project(":common").sourceSets.main.get())
                    source(sourceSets.main.get())
                }
            }
        }
    }
}

dependencies {
    compileOnly( project(":common") )
    minecraft(libs.forge)
    implementation( libs.jsr305 )

    if (System.getProperty("idea.sync.active") != "true")
        annotationProcessor(variantOf(libs.mixin) { classifier("processor") })

    compileOnly( libs.mixinextras.common )
    annotationProcessor( libs.mixinextras.common )
    testCompileOnly( libs.mixinextras.common )

    runtimeOnly(libs.mixinextras.forge)
    jarJar(libs.mixinextras.forge) { jarJar.ranged(this, libs.versions.mixinextras.range.get()) }

    implementation( fg.deobf(libs.geckolib.forge.get()) )
    implementation( fg.deobf(libs.patchouli.forge.get()) )
    implementation( fg.deobf(libs.sbl.forge.get()) )
    implementation( fg.deobf(libs.stateobserver.forge.get()) )
    implementation( fg.deobf(libs.jei.forge.get()) )
}

mixin {
    add(sourceSets.getByName("main"), "${mod_id}.refmap.json")
    config("${mod_id}.mixins.json")
    config("${mod_id}.forge.mixins.json")
}

//Make the result of the jarJar task the one with no classifier instead of no classifier and "all"
tasks.named<Jar>("jar").configure {
    archiveClassifier.set("slim")
}

tasks.named<JarJar>("jarJar").configure {
    archiveClassifier.set("")
}

tasks.withType<JavaCompile>().configureEach {
    source(project(":common").sourceSets.getByName("main").allSource)
}

tasks.named<Jar>("sourcesJar").configure {
    from(project(":common").sourceSets.getByName("main").allSource)
}

tasks.withType<Javadoc>().configureEach {
    source(project(":common").sourceSets.getByName("main").allJava)
}

tasks.withType<ProcessResources>().configureEach {
    from(project(":common").sourceSets.getByName("main").resources)
}

reobf {
    jarJar { }
}

tasks.jarJar.get().finalizedBy("reobfJarJar")

modrinth {
    token = System.getenv("MODRINTH_TOKEN") ?: "Invalid/No API Token Found"

    projectId = "HsbpdVo9"
    versionName = "Forge ${minecraft_version}"
    versionNumber.set(project.version.toString())

    uploadFile.set(tasks.jarJar)
    changelog.set(rootProject.file("changelog.txt").readText(Charsets.UTF_8))

    loaders.set(listOf("forge", "neoforge"))
    gameVersions.set(listOf(minecraft_version))

    dependencies {
        required.project("geckolib")
        required.project("patchouli")
        required.project("smartbrainlib")
        required.project("stateobserver")
    }

    //debugMode = true
    //https://github.com/modrinth/minotaur#available-properties
}

tasks.register<TaskPublishCurseForge>("publishToCurseForge") {
    group = "publishing"
    apiToken = System.getenv("CURSEFORGE_TOKEN") ?: "Invalid/No API Token Found"

    val mainFile = upload(560363, tasks.jarJar)
    mainFile.releaseType = "release"
    mainFile.addModLoader("Forge", "NeoForge")
    mainFile.addGameVersion(minecraft_version)
    mainFile.addJavaVersion("Java 17")
    mainFile.changelog = rootProject.file("changelog.txt").readText(Charsets.UTF_8)

    mainFile.addRequirement(
        "geckolib",
        "patchouli",
        "smartbrainlib",
        "stateobserver"
    )

    //debugMode = true
    //https://github.com/Darkhax/CurseForgeGradle#available-properties
}

publishing {
    publications {
        create<MavenPublication>(mod_id) {
            from(components["java"])
            jarJar.component(this)
            artifactId = base.archivesName.get()
        }
    }
}

tasks.named<DefaultTask>("publish").configure {
    finalizedBy("modrinth")
    finalizedBy("publishToCurseForge")
}



