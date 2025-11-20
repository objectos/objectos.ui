#
# Objectos UI
# Copyright (C) 2025 Objectos Software LTDA.
#
# This program is free software: you can redistribute it and/or modify
# it under the terms of the GNU Affero General Public License as
# published by the Free Software Foundation, either version 3 of the
# License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU Affero General Public License for more details.
#
# You should have received a copy of the GNU Affero General Public License
# along with this program.  If not, see <https://www.gnu.org/licenses/>.
#

#
# Objectos UI
#

## Coordinates
GROUP_ID := br.com.objectos
ARTIFACT_ID := objectos.ui
VERSION := 0.3.0-SNAPSHOT
MODULE := $(ARTIFACT_ID)

## javac --release option
JAVA_RELEASE := 25

## Maven interop
REMOTE_REPOS := https://repo.maven.apache.org/maven2

## Dependencies
WAY := br.com.objectos/objectos.way/$(VERSION)
SLF4J_NOP := org.slf4j/slf4j-nop/2.0.17
TESTNG := org.testng/testng/7.11.0
PLAYWRIGHT := com.microsoft.playwright/playwright/1.53.0

# Delete the default suffixes
.SUFFIXES:

#
# ui
#

.PHONY: all
all: test

include make/java-core.mk

#
# ui@clean
#

include make/common-clean.mk

#
# ui@compile
#

## Compile deps
COMPILE_DEPS := $(WAY)
COMPILE_DEPS += $(PLAYWRIGHT)

## Compile opts
COMPILE_OPTS := --add-modules playwright
COMPILE_OPTS += --add-reads objectos.ui=playwright

## resources
RESOURCES := main-resources

include make/java-compile.mk

#
# ui@gen
#

## gen dependencies
GEN_DEPS := $(PLAYWRIGHT)

## gen resolution files
GEN_RESOLUTION_FILES := $(call to-resolution-files,$(GEN_DEPS))

## gen path
GEN_PATH := $(WORK)/gen-path

## gen target 
GEN_SCRIPT := UiGen.java

## gen src
GEN_JAVA := main/objectos/ui/gen/XUiGen.java

## html page (cds)
CARBON_CDS := https://react.carbondesignsystem.com/iframe.html

## html page (c4p)
CARBON_C4P := https://ibm-products.carbondesignsystem.com/iframe.html

## plex pacakges
carbon_plex_sans := https://github.com/IBM/plex/releases/download/%40ibm%2Fplex-sans%401.1.0/ibm-plex-sans.zip

## carbon gen command
GENX := $(JAVA)
GENX += -cp @$(GEN_PATH)
GENX += $(GEN_SCRIPT)
GENX += --cds-iframe $(CARBON_CDS) 
GENX += --c4p-iframe $(CARBON_C4P) 
GENX += --plex-sans $(carbon_plex_sans) 

.PHONY: gen
gen: $(GEN_SCRIPT) $(GEN_PATH)
	$(GENX)

.PHONY: gen-clean
gen-clean:
	rm -f $(GEN_SCRIPT) $(GEN_PATH)
	
$(GEN_SCRIPT): $(GEN_JAVA)
	sed '/\/\/ SED_REMOVE/d' $< > $@

$(GEN_PATH): $(GEN_RESOLUTION_FILES) | $(WORK)
	$(call uniq-resolution-files,$(GEN_RESOLUTION_FILES)) > $@.tmp
	cat $@.tmp | paste --delimiter='$(CLASS_PATH_SEPARATOR)' --serial > $@

#
# ui@carbon-gen-test
#

## carbon-gen-test script
CARBON_GEN_TEST_SCRIPT := CarbonGenRes.java

## carbon-gen-test src
CARBON_GEN_TEST_JAVA := main/objectos/ui/XCarbonGenRes.java

## carbon-gen-test command
CARBON_GEN_TESTX := $(JAVA)
CARBON_GEN_TESTX += -cp @$(CARBON_GEN_PATH)
CARBON_GEN_TESTX += $(CARBON_GEN_TEST_SCRIPT)

.PHONY: carbon-gen-test
carbon-gen-test: $(CARBON_GEN_TEST_SCRIPT) $(CARBON_GEN_PATH)
	$(CARBON_GEN_TESTX)

.PHONY: carbon-gen-test-clean
carbon-gen-test-clean:
	rm -f $(CARBON_GEN_SCRIPT)
	
$(CARBON_GEN_TEST_SCRIPT): $(CARBON_GEN_TEST_JAVA)
	sed '/\/\/ SED_REMOVE/d' $< > $@

#
# ui@dev
#

## dev main class
DEV_MAIN := objectos.ui.dev.DevStart

## dev jvm opts
ifeq ($(ENABLE_DEBUG),1)
DEV_JVM_OPTS += -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=localhost:7000
endif

include make/java-dev.mk

#
# ui@test-compile
#

## test compile deps
TEST_COMPILE_DEPS := $(TESTNG)
TEST_COMPILE_DEPS += $(PLAYWRIGHT)

include make/java-test-compile.mk

#
# ui@test
#

## test main class
TEST_MAIN := objectos.ui.StartTest

## www test runtime dependencies
TEST_RUNTIME_DEPS := $(SLF4J_NOP)

## test JVM opts
TEST_JVM_OPTS := -Dplaywright.headless=true

## test --add-modules
TEST_ADD_MODULES := org.testng
TEST_ADD_MODULES += org.slf4j
TEST_ADD_MODULES += java.desktop
TEST_ADD_MODULES += com.google.gson
TEST_ADD_MODULES += jdk.unsupported

## test --add-exports
TEST_ADD_EXPORTS := objectos.ui/objectos.ui.gen=org.testng

## test --add-reads
TEST_ADD_READS := objectos.ui=org.testng
TEST_ADD_READS += objectos.ui=playwright
TEST_ADD_READS += objectos.ui=java.desktop

include make/java-test.mk


#
# ui@docs
#

## docs main class
DOCS_MAIN := objectos.ui.Docs

## docs add-modules
DOCS_COMPILE_ADD_MODULES := playwright 

## docs add-reads
DOCS_COMPILE_ADD_READS := objectos.ui=playwright 

$(eval $(call java_patched,DOCS,docs))

#
# ui@javadoc
#

include make/java-javadoc.mk

#
# ui@jar
#

## do not add dev and script classes
JAR_EXCLUDES := objectos/ui/DevBoot.class
JAR_EXCLUDES += objectos/ui/dev
JAR_EXCLUDES += objectos/ui/dev/*
JAR_EXCLUDES += objectos/ui/gen
JAR_EXCLUDES += objectos/ui/gen/*

include make/java-jar.mk

#
# ui@install
#

include make/java-install.mk
