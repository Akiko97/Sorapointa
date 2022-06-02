@file:Suppress("GradlePackageUpdate")

plugins {
    kotlin("plugin.serialization")
    application
    `sorapointa-conventions`
    `sorapointa-publish`
}

dependencies {
    implementation(project(":sorapointa-dataprovider"))
    implementation(project(":sorapointa-i18n"))
    implementation(project(":sorapointa-event"))
    implementation(project(":sorapointa-proto"))
    implementation(project(":sorapointa-utils:sorapointa-utils-all"))

    implementation("io.ktor:ktor-server-core-jvm:_")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:_")
    implementation("io.ktor:ktor-server-call-logging-jvm:_")
    implementation("io.ktor:ktor-server-compression-jvm:_")
    implementation("io.ktor:ktor-server-netty-jvm:_")
    implementation("io.ktor:ktor-server-status-pages:_")
    implementation("io.ktor:ktor-server-html-builder:_")

    implementation("io.ktor:ktor-client-core:_")
    implementation("io.ktor:ktor-client-cio:_")
    implementation("io.ktor:ktor-client-logging:_")
    implementation("io.ktor:ktor-client-content-negotiation:_")

    implementation("io.ktor:ktor-network-tls-certificates:_")
    implementation("io.ktor:ktor-serialization-kotlinx-json:_")

    implementation("com.password4j:password4j:_")

    testImplementation("io.ktor:ktor-server-tests-jvm:_")
    testImplementation(project(":sorapointa-dataprovider", "test"))
    testImplementation("org.jetbrains.kotlinx:atomicfu:_")
}

configureLangsCopy()

application {
    applicationName = "sorapointa-dispatch"
    mainClass.set("org.sorapointa.dispatch.DispatchServerKt")
}
