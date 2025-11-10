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

include make/java-compile.mk

#
# ui@carbon-gen
#

## carbon-gen dependencies
CARBON_GEN_DEPS := $(PLAYWRIGHT)

## carbon-gen resolution files
CARBON_GEN_RESOLUTION_FILES := $(call to-resolution-files,$(CARBON_GEN_DEPS))

## carbon-gen path
CARBON_GEN_PATH := $(WORK)/carbon-gen-path

## carbon-gen target 
CARBON_GEN_SCRIPT := CarbonGen.java

## carbon-gen src
CARBON_GEN_JAVA := main/objectos/ui/XCarbonGen.java

## html page (cds)
CARBON_CDS := https://react.carbondesignsystem.com/iframe.html

## html page (c4p)
CARBON_C4P := https://ibm-products.carbondesignsystem.com/iframe.html

## carbon gen command
CARBON_GENX := $(JAVA)
CARBON_GENX += -cp @$(CARBON_GEN_PATH)
CARBON_GENX += $(CARBON_GEN_SCRIPT)
CARBON_GENX += --cds-iframe $(CARBON_CDS) 
CARBON_GENX += --c4p-iframe $(CARBON_C4P) 

.PHONY: carbon-gen
carbon-gen: $(CARBON_GEN_SCRIPT) $(CARBON_GEN_PATH)
	$(CARBON_GENX)

.PHONY: carbon-gen-clean
carbon-gen-clean:
	rm -f $(CARBON_GEN_SCRIPT) $(CARBON_GEN_PATH)
	
$(CARBON_GEN_SCRIPT): $(CARBON_GEN_JAVA)
	sed '/\/\/ SED_REMOVE/d' $< > $@

$(CARBON_GEN_PATH): $(CARBON_GEN_RESOLUTION_FILES) | $(WORK)
	$(call uniq-resolution-files,$(CARBON_GEN_RESOLUTION_FILES)) > $@.tmp
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
DEV_MAIN := objectos.ui.DevStart

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
JAR_EXCLUDES := Dev*.class
JAR_EXCLUDES += X*.class

include make/java-jar.mk

#
# ui@install
#

include make/java-install.mk
