plugins {
    id("enchanted-convention")

    alias(libs.plugins.vanillagradle)
}

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
    compileOnly( libs.mixin )
    compileOnly( libs.mixinextras.common )

    // Use gecko/patchouli forge to compile because they don't have common yet
    compileOnly( libs.geckolib.forge )
    compileOnly( libs.patchouli.forge )

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