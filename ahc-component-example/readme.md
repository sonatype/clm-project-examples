# Analysing components with Insight Application Health Check

[Application Health Check](http://links.sonatype.com/products/insight/ac/home) helps you figure out what is in your Java application and uncover potential security, licensing, and quality problems.

Application Health Check is intended to analyse application bundles (war, ear, zip) not individual components (jar) and their dependencies from a Maven pom.  You can analyze a components dependencies by simulating the assembly of an application bundle using the `dependency-copy` goal of the [Maven dependency plugin](http://maven.apache.org/plugins/maven-dependency-plugin/copy-dependencies-mojo.html).  Once the dependencies have been collected they can be analysed with the Application Health Check [GUI scanner](http://links.sonatype.com/products/insight/ac/download) or the [Maven plugin](https://support.sonatype.com/entries/22022541-how-do-i-use-the-application-health-check-maven-plugin).

# Analysing components using the Insight for CI plugin

[Insight for CI](http://links.sonatype.com/products/insight/ci/home) is used to catch problems at build time within your Continious Integration server.  Like Application Health Check it is meant to analyze application bundles, not individual components and their transitive dependencies.

If you are running a CI build for a component instead of an application the same approach can be taken where the `dependency-copy` goal is used to collect dependencies and configure the Insight for CI plugin to analyse this directory, or the whole target directory.

# Example with AHC Maven plugin

An example of using AHC with your component build is shown in [fibonacci-calculator/pom.xml](https://github.com/basil3whitehouse/insight-ahc-component-example/blob/master/fibonacci-calculator/pom.xml).  The dependencies are collected into `target/all-your-dependencies/` and the AHC Maven plugin is configured to analyse that directory with the `ahc.input` parameter.  Run the build from the fibonacci-calculator project:

    cd fibonacci-calculator
    mvn clean package com.sonatype.insight:ahc:run

## Build configuration

You could specify the copy-dependencies parameters on the command line or add them to your build definition in the pom.  The example uses the pom:

      <!-- Could be configured in a profile instead. -->
      <build>
        <plugins>
          <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-dependency-plugin</artifactId>
            <version>2.5.1</version>
            <executions>
              <execution>
                <id>copy-dependencies</id>
                <phase>package</phase>
                <goals>
                  <goal>copy-dependencies</goal>
                </goals>
                <configuration>
                  <outputDirectory>${ahc.input}</outputDirectory>
                  <overWriteReleases>false</overWriteReleases>
                  <overWriteSnapshots>true</overWriteSnapshots>
                </configuration>
              </execution>
            </executions>
          </plugin>
        </plugins>
      </build>

      <properties>
        <ahc.input>${project.build.directory}/all-your-dependencies</ahc.input>
        <ahc.reportLabel>${project.artifactId}</ahc.reportLabel>
      </properties>

Difference of target directory with dependency collection:

![Diff of target with dependency collection](https://raw.github.com/basil3whitehouse/insight-ahc-component-example/master/images/target-diff-with-dep-collection.png)

## Limiting scope of analysis

By default `copy-dependencies` collects all dependencies required for the build, which often includes some needed only for testing (you are writing tests right =).  As seen in  a report of the example project:

![Analysis report of all dependencies](https://raw.github.com/basil3whitehouse/insight-ahc-component-example/master/images/all-scopes.png)

You may want to limit analysis to runtime only dependencies to better represent what users of your component will be consuming.  This can be accomplished by specifying what scopes to include when collecting dependencies.  Add `includeScope` to the copy-dependencies configuration:

                <configuration>
                  <outputDirectory>${ahc.input}</outputDirectory>
                  <overWriteReleases>false</overWriteReleases>
                  <overWriteSnapshots>true</overWriteSnapshots>
                  <!-- Only collect runtime dependencies for AHC analysis. -->
                  <includeScope>runtime</includeScope>
                </configuration>

After analyzing the build output with the AHC Maven plugin the report now reflects just the runtime components:

![Analysis report of runtime dependencies](https://raw.github.com/basil3whitehouse/insight-ahc-component-example/master/images/runtime-scope.png)
