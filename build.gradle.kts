import org.gradle.util.GradleVersion

println("Gradle Version: " + GradleVersion.current().toString())
println("Java Version: " + JavaVersion.current().toString())

group = "com.linked-planet"
version = "0.1.0-SNAPSHOT"

ext.set("kotlinVersion", "1.4.32")
ext.set("jvmTarget", "1.8") // ktor prevents compile-time 11, see: https://youtrack.jetbrains.com/issue/KTOR-619

plugins {
    kotlin("jvm") version "1.4.32" apply false
    id("com.github.ben-manes.versions") version "0.38.0"
    java
    id("maven-publish")
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://jitpack.io") // wiremock-extension
    }
}

val jaxws: Configuration by configurations.creating
dependencies {
    // JavaEE dependencies removed in Java 11
    jaxws("javax.xml.bind", "jaxb-api", "2.3.1")
    jaxws("javax.activation", "javax.activation-api", "1.2.0")
    jaxws("javax.xml.ws", "jaxws-api", "2.3.1")
    implementation("com.sun.xml.ws", "jaxws-rt", "2.2.10")
    jaxws("javax.jws", "javax.jws-api", "1.1")

    jaxws("com.sun.xml.ws", "jaxws-tools", "2.1.4")
    jaxws("org.jvnet.jaxb2_commons", "jaxb2-basics", "0.9.5")
}

// https://www.brightec.co.uk/blog/wsdl-client-generation-with-kotlin-and-gradle (these guys are good)
task("wsimport-doxis") {
    doLast {
        val wsdlPaths = listOf(
            "OCPP_CentralSystemService_1.6.wsdl",
            "OCPP_ChargePointService_1.6.wsdl"
        )
        wsdlPaths.onEach { path ->
            ant.withGroovyBuilder {
                "taskdef"(
                    "name" to "wsimport",
                    "classname" to "com.sun.tools.ws.ant.WsImport",
                    "classpath" to jaxws.asPath
                )

                "wsimport"(
                    "sourcedestdir" to file("src/main/java"),
                    "destDir" to file("out/production/classes").apply { mkdirs() },
                    "extension" to true,
                    "wsdl" to file("src/main/resources/META-INF/$path"),
                    "wsdlLocation" to "http://localhost/$path" // prevents user-local file path
                ) {
                    // seems to be a generally recommended parameter to prevent case sensitivity issues
                    "xjcarg"("value" to "-XautoNameResolution")
                    "xjcarg"("value" to "-Xsetters")
                }
            }
        }
    }
}.let {
    tasks.build.get().dependsOn(it)
}


publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/linked-planet/doxis-api")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
