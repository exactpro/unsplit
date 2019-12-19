plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "com.exactpro.unsplit"

allprojects {

    repositories {
        jcenter()
    }

    afterEvaluate {

        tasks {

            jar {
                enabled = false
            }
        }
    }
}

subprojects {

    apply(plugin = "java-library")
    apply(plugin = "com.github.johnrengelman.shadow")

    tasks {

        shadowJar {
            classifier = ""
        }

        build {
            finalizedBy(shadowJar)
        }
    }
}
