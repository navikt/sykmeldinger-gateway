plugins {
	kotlin("jvm") version "2.1.0"
	kotlin("plugin.spring") version "2.1.0"
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

val logbacksyslog4jVersion = "1.0.0"
val logstashLogbackEncoderVersion = "8.0"
val kotlinxVersion = "0.11.0"

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.papertrailapp:logback-syslog4j:$logbacksyslog4jVersion")
	implementation("net.logstash.logback:logstash-logback-encoder:$logstashLogbackEncoderVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxVersion")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.named<Jar>("jar") {
	enabled = false
}

tasks {

	shadowJar {
		archiveBaseName.set("app")
		archiveClassifier.set("")
		isZip64 = true
		manifest {
			attributes["Main-Class"] = "no.nav.sykmeldinger.ApplicationKt"
		}
	}

	bootJar {
		archiveFileName = "app.jar"
	}
}

