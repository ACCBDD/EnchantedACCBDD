import com.diluv.schoomp.Webhook
import com.diluv.schoomp.message.Message
import com.diluv.schoomp.message.embed.Embed
import java.io.IOException

plugins {
    id("enchanted-convention")
    alias(libs.plugins.vanillagradle)
}

val mod_name: String by project
val mod_id: String by project
version = libs.versions.enchanted.get()
val minecraft_version = libs.versions.minecraft.asProvider().get()

base {
    archivesName = "${mod_id}-common-${minecraft_version}"
}

minecraft {
    version(minecraft_version)
    accessWideners(file("src/main/resources/${mod_id}.accesswidener"))
}

dependencies {
    implementation( libs.jsr305 )
    compileOnly( libs.mixin )
    compileOnly( libs.mixinextras.common )

    compileOnly( libs.geckolib.forge ) // Geckolib doesn't have common
    compileOnly( libs.patchouli.forge ) // Patchouli doesn't have common

    compileOnly( libs.stateobserver.common )
    compileOnly( libs.sbl.common )
    compileOnly( libs.jei.common.api )
    compileOnly( libs.forgeconfigapi.common )
}

publishing {
    publishing {
        publications {
            create<MavenPublication>(mod_id) {
                from(components["java"])
                artifactId = base.archivesName.get()
            }
        }
    }
}

sourceSets.main.get().resources.srcDir(project(":common").file("src/generated/resources"))

tasks.create("postDiscord") {
    group = "publishing"
    doLast {
        try {
            if(System.getenv("ENCHANTED_RELEASE_WEBHOOK") != null) {
                val webhook = Webhook(System.getenv("ENCHANTED_RELEASE_WEBHOOK"), "Enchanted Gradle Upload")

                val message = Message()
                message.username = "Elaina"
                message.avatarUrl = "https://i.imgur.com/I455GSr.jpeg"
                message.content = "<@&1246846443944149145> Enchanted $version for Minecraft $minecraft_version has been published!"

                val changeString = rootProject.file("changelog.txt").readText(Charsets.UTF_8).replace("\r", "")

                val embed = Embed()
                embed.title = "Changelog"
                embed.description = if (changeString.length < 4096) changeString else "The changelog was too long to embed, you can view it on the " +
                        "[GitHub Release](https://github.com/Favouriteless/Enchanted/releases/tag/v$version-release), " +
                        "[CurseForge](https://www.curseforge.com/minecraft/mc-mods/enchanted-witchcraft/files/all) or " +
                        "[Modrinth](https://modrinth.com/mod/enchanted-witchcraft/versions)."
                embed.color = 0x9C58B8

                message.addEmbed(embed)
                webhook.sendMessage(message)
            }
        } catch (e: IOException) {
            project.logger.error("Failed to push CF Discord webhook: " + e.message)
        }
    }
}

tasks.named<DefaultTask>("publish").configure {
    finalizedBy("postDiscord")
}