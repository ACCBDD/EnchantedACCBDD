plugins {
    alias(libs.plugins.minotaur) apply false
    alias(libs.plugins.curseforgegradle) apply false

    // Required for NeoGradle
    alias(libs.plugins.ideaext)
}

subprojects {

    repositories {
        maven("https://maven.favouriteless.net/releases") { name = "Favouriteless Maven" }
        maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/") { name = "Fuzs Mod Resources" }
        maven("https://api.modrinth.com/maven") { name = "Modrinth" }
        maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") {
            name = "GeckoLib"
            content {
                includeGroup("software.bernie.geckolib")
            }
        }
        maven("https://maven.blamejared.com/") { name = "Jared's maven" }
    }

}