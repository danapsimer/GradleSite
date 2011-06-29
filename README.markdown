# Gradle Site Plugin
This plugin generates project websites as known from the Maven Site plugin does.
The supported file types are:
* APT
* XDOC

This plugin was originally written for a live demo at the GR8conf 2011 in Europe. 

At the moment this plugin is just a kind of a wrapper for the mvn site generation as some projects reported, that the missing gradle site support is a blocker for them. 

Later this plugin should move from a plain wrapper to a first class website generation of gradle (multi)project builds. 

## TASKS
* site - generates the project website to $buildDir/site
* siteClean - cleans up the site target directory

## Example usage:
			buildscript { 
			    repositories { 
					mavenCentral()
					mavenLocal()
				} 
			    dependencies {
			        classpath 'com.breskeby:GradleSite:0.1-SNAPSHOT'
			    } 
			}

			apply plugin:'groovy'
			apply plugin:'Site'

			sourceCompatibility = 1.5
			targetCompatibility = 1.5

			repositories {
			    mavenCentral()
			}

			deployDir = "build/customDir" as File // change the site output directory

			site{
				html.title = "Welcome at Codenarc" //set a custom browser title
			}

## TODO
* cleanup extensive and not used transitive doxia dependencies
* support FML file types
* deploy tasks
* add more documentation than this readme
* deploy this to a public repository

## Known Issues 

* tests with the codenarc project shows following error/warn messages:
			
			[warn] [APT Parser] Ambiguous link: 'codenarc-rules-serialization.html'. 			
			If this is a local link, prepend "./"!
 