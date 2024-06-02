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
    doLast {
        try {
            val webhook = Webhook(System.getenv("ENCHANTED_RELEASE_WEBHOOK"), "$mod_name Gradle Upload")

            val message = Message()
            message.username = "Elaina"
            message.avatarUrl = "https://i.imgur.com/I455GSr.jpeg"
            message.content = "<@&1246846443944149145> $mod_name $version for Minecraft $minecraft_version has been published!"

            val embed = Embed()
            embed.addField("Changelog", rootProject.file("changelog.txt").readText(Charsets.UTF_8), false)
            embed.color = 0x9C58B8

            message.addEmbed(embed)

            webhook.sendMessage(message)
        } catch (e: IOException) {
            project.logger.error("Failed to push CF Discord webhook.")
        }
    }
}

tasks.named<DefaultTask>("publish").configure {
    finalizedBy("postDiscord")
}