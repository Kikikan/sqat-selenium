plugins {
    id("java")
}

group = "dev.gyongyosi"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.seleniumhq.selenium:selenium-java:2.52.0")
    testImplementation("org.seleniumhq.selenium:selenium-chrome-driver:3.141.59")
    testImplementation("org.hamcrest:hamcrest-library:1.3")
    testImplementation("org.slf4j:slf4j-simple:1.7.9")
}
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
}
