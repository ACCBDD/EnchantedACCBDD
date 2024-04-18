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
    compileOnly(libs.mixin)
    compileOnly(libs.mixinextras.common)
    //implementation("com.google.code.findbugs:jsr305:3.0.1")
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