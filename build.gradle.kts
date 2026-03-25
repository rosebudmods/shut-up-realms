plugins {
    id("net.fabricmc.fabric-loom") version "1.14-SNAPSHOT"
    id("maven-publish")
    id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

val minecraftVersion = property("minecraft_version").toString()
val loaderVersion = property("loader_version").toString()
val modVersion = property("mod_version").toString()

version = "${modVersion}+${minecraftVersion}"
group = property("maven_group").toString()

base {
    archivesName = property("archives_base_name").toString()
}

loom {
    splitEnvironmentSourceSets()

    mods {
        create("shut-up-realms") {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }
}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:$minecraftVersion")
    implementation("net.fabricmc:fabric-loader:$loaderVersion")
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    targetCompatibility = JavaVersion.VERSION_25
    sourceCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
    from(project.file("LICENSE"))
}

tasks.processResources {
    val map = mapOf(
        "version" to version,
        "loader_version" to loaderVersion,
    )

    inputs.properties(map)

    filesMatching("fabric.mod.json") {
        expand(map)
    }
}

publishMods {
    displayName = "shut up realms $modVersion for $minecraftVersion"
    file = tasks.jar.get().archiveFile
    changelog = project.file("CHANGELOG.md").readText()
    type = STABLE

    modLoaders.add("quilt")
    modLoaders.add("fabric")

    dryRun = !providers.environmentVariable("MODRINTH_TOKEN").isPresent()
            || !providers.environmentVariable("CURSEFORGE_TOKEN").isPresent()
            || property("pub.should_publish") == "false"

    modrinth {
        projectId = "esvER4Ln"
        accessToken = providers.environmentVariable("MODRINTH_TOKEN")

        minecraftVersionRange {
            start = minecraftVersion
            end = "latest"
        }
    }

    curseforge {
        accessToken = providers.environmentVariable("CURSEFORGE_TOKEN")
        projectId = "1090348"

        minecraftVersionRange {
            start = minecraftVersion
            end = "latest"
        }

        clientRequired = true
        serverRequired = false
    }
}
