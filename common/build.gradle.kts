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
    compileOnly( libs.mixin )
    compileOnly( libs.mixinextras.common )
    compileOnly( libs.stateobserver.common )
    compileOnly( libs.geckolib.common )
    compileOnly( libs.forgeconfigapi.common )
    compileOnly( libs.iris.common )
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
                .setUsername("Elaina")
                .setAvatarUrl("https://i.imgur.com/I455GSr.jpeg")
                .setContent("<@&1246846443944149145> Enchanted $version for Minecraft $mcVersion has been published!")
                .addEmbed(Embed()
                    .addField("Changelog", rootProject.file("changelog.txt").readText(Charsets.UTF_8), false)
                    .setColor(0x9C58B8))

            webhook.sendMessage(message)
        } catch (e: IOException) {
            project.logger.error("Failed to push CF Discord webhook: " + e.message)
        }
    }
}

tasks.named<DefaultTask>("publish").configure {
    finalizedBy("postDiscord")
}