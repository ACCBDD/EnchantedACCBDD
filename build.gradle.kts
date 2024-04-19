plugins {
    alias(libs.plugins.minotaur) apply false
    alias(libs.plugins.curseforgegradle) apply false

    // Required for NeoGradle
    alias(libs.plugins.ideaext)
}

subprojects {

    repositories {
        flatDir {
            dirs("../lib")
        }

        maven("https://raw.githubusercontent.com/Fuzss/modresources/main/maven/") { name = "Fuzs Mod Resources" }
        maven("https://dl.cloudsmith.io/public/tslat/sbl/maven/") { name = "SmartBrainLib (SBL) Maven" }
        maven("https://maven.blamejared.com") { name = "BlameJared Maven" }
        maven("https://dvs1.progwml6.com/files/maven/") { name = "Progwml6 maven" }
        maven("https://maven.architectury.dev/") { name = "Architectury maven" }
        maven("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/") { name = "Geckolib maven" }
    }

}