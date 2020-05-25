repositories {
    jcenter()
}

plugins {
    java;
    id("org.springframework.boot") version "2.3.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    id("net.ltgt.apt") version "0.21"
    id("net.ltgt.apt-idea") version "0.21"
}

group = "com.jake.url.shortener"
version = "0.0.1-SNAPSHOT"

configure<JavaPluginExtension> {
    sourceCompatibility = JavaVersion.VERSION_13
    targetCompatibility = JavaVersion.VERSION_13
}

sourceSets.getByName("test").java.srcDir("src/integrationtest/java")
sourceSets.getByName("test").resources.srcDir("src/integrationtest/resources")

dependencies {
    annotationProcessor("org.projectlombok:lombok")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.3.0.Final")

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.data:spring-data-jpa")

    implementation("org.apache.commons:commons-lang3")
    implementation("com.google.guava:guava:28.0-jre")
    implementation("org.mapstruct:mapstruct:1.3.0.Final")

    implementation("com.squareup.retrofit2:retrofit:2.8.1")
    implementation("com.squareup.retrofit2:converter-jackson:2.8.1")

    implementation("io.jsonwebtoken:jjwt-api:0.11.1")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.1")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.1")

    implementation("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.mockito:mockito-core")
    testImplementation("org.mockito:mockito-junit-jupiter:2.23.4")

    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

}

tasks {
    "test"(Test::class) {
        useJUnitPlatform()
    }
}