import com.diluv.schoomp.Webhook
import com.diluv.schoomp.message.Message
import com.diluv.schoomp.message.embed.Embed
import java.io.IOException

plugins {
    id("enchanted-convention")
    alias(libs.plugins.moddevgradle)
}

version = libs.versions.enchanted.get()
val mcVersion = libs.versions.minecraft.asProvider().get()

base {
    archivesName = "enchanted-common-${mcVersion}"
}

neoForge {
    neoFormVersion = libs.versions.neoform.get()

    accessTransformers.files.setFrom("src/main/resources/META-INF/accesstransformer.cfg")

    parchment {
        minecraftVersion = libs.versions.parchment.minecraft.get()
        mappingsVersion = libs.versions.parchment.asProvider().get()
    }
}

dependencies {
    compileOnly(libs.mixinextras.common)
}

publishing {
    publications {
        create<MavenPublication>("enchanted") {
            from(components["java"])
            artifactId = base.archivesName.get()
        }
    }
}

sourceSets.main.get().resources.srcDir(project(":common").file("src/generated/resources"))

tasks.create("postDiscord") {
    doLast {
        try {
            val webhook = Webhook(System.getenv("ENCHANTED_RELEASE_WEBHOOK"), "Enchanted Gradle Upload")

            val message = Message()
            message.username = "Elaina"
            message.avatarUrl = "https://i.imgur.com/I455GSr.jpeg"
            message.content = "<@&1246846443944149145> Enchanted $version for Minecraft $mcVersion has been published!"

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