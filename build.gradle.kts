plugins {
    id("java")
    id("io.freefair.lombok") version ("8.11")
}

group = "com.azwpayne"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // unidbg
    implementation("com.github.zhkl0228:unidbg-android:0.+")
    implementation("com.github.zhkl0228:unidbg-api:0.+")

    // unidbg backend
    implementation("com.github.zhkl0228:unidbg-dynarmic:0.9.8")
    implementation("com.github.zhkl0228:unidbg-hypervisor:0.9.8")
    implementation("com.github.zhkl0228:unidbg-kvm:0.9.8")
    implementation("com.github.zhkl0228:unidbg-unicorn2:0.9.8")

    // default
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.slf4j:slf4j-api:2.0.16")
    implementation("org.slf4j:slf4j-reload4j:2.0.16")

}

tasks.test {
    useJUnitPlatform()
}