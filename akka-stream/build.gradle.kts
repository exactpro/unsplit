group = "${rootProject.group}.com.typesafe.akka"
version = "2.6.1"

val scalaVersion = "2.13"

dependencies {

    implementation("com.typesafe.akka:akka-actor_${scalaVersion}:${version}") {
        isTransitive = false
    }

    implementation("com.typesafe.akka:akka-stream_${scalaVersion}:${version}") {
        isTransitive = false
    }
}

tasks {

    shadowJar {

        baseName = "${project.name}_${scalaVersion}"

        manifest {

            attributes(
                mapOf(

                    "Automatic-Module-Name"     to "akka.stream",

                    "Bundle-Description"        to "Akka is a toolkit for building highly concurrent, " +
                        "distributed, and resilient message-driven applications for Java and Scala.",

                    "Bundle-DocURL"             to "https://doc.akka.io/api/akka/${project.version}",

                    "Bundle-License"            to "http://www.apache.org/licenses/LICENSE-2.0;" +
                        "description=Apache License, Version 2.0",

                    "Bundle-Name"               to "akka-stream",
                    "Bundle-SymbolicName"       to "com.typesafe.akka.stream",
                    "Bundle-Vendor"             to "Exactpro Systems Ltd",
                    "Bundle-Version"            to "${project.version}",

                    "Implementation-Title"      to "akka-stream",
                    "Implementation-URL"        to "https://akka.io/",
                    "Implementation-Vendor-Id"  to "com.typesafe.akka",
                    "Implementation-Vendor"     to "Lightbend Inc.",
                    "Implementation-Version"    to "${project.version}",

                    "Specification-Title"       to "akka-stream",
                    "Specification-Vendor"      to "Lightbend Inc.",
                    "Specification-Version"     to "${project.version}",

                    "Tool"                      to "Gradle ${gradle.gradleVersion}"
                )
            )
        }
    }

    publishing {

        publications {

            getByName<MavenPublication>("maven") {
                artifactId = "${project.name}_${scalaVersion}"
            }
        }
    }
}
