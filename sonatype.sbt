// Your profile name of the sonatype account. The default is the same with the organization value
sonatypeProfileName := "com.github.paulporu"

// To sync with Maven central, you need to supply the following information:
pomExtra in Global := {
  <url>https://github.com/paulporu/flexible-user-queue</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>https://opensource.org/licenses/MIT</url>
    </license>
  </licenses>
  <scm>
    <connection>scm:git:github.com:paulporu/flexible-user-queue.git</connection>
    <developerConnection>scm:git:git@github.com:paulporu/flexible-user-queue.git</developerConnection>
    <url>git@github.com:paulporu/flexible-user-queue.git</url>
  </scm>
  <developers>
    <developer>
      <id>paulporu</id>
      <name>Paul Lavery</name>
      <url>https://github.com/paulporu</url>
    </developer>
  </developers>
}
