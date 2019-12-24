plugins {
    `java-library`
    `maven-publish`
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
    apply(plugin = "maven-publish")
    apply(plugin = "com.github.johnrengelman.shadow")

    tasks {

        shadowJar {
            classifier = ""
        }

        build {
            finalizedBy(shadowJar)
        }
    }

    publishing {

        publications {

            create<MavenPublication>("maven") {
                project.shadow.component(this)
            }
        }

        repositories {

            maven {

                name = "GitHubPackages"
                url = uri("https://maven.pkg.github.com/exactpro/unsplit")

                authentication {
                    create<HttpHeaderAuthentication>("aouth") {
                    }
                }

                credentials(HttpHeaderCredentials::class.java) {
                    name = "Authorization"
                    value = "Bearer ${ System.getProperty("token") }"
                }
            }
        }
    }
}
