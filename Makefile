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
VERSION := 0.2.7-SNAPSHOT
MODULE := $(ARTIFACT_ID)

## javac --release option
JAVA_RELEASE := 21

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
# ui@carbon-gen
#

## carbon-gen target 
CARBON_GEN_SCRIPT := CarbonGen.java

## carbon-gen src
CARBON_GEN_JAVA := main/objectos/ui/XCarbonGen.java

## html page containing main.css
CARBON_HTML := https://react.carbondesignsystem.com/iframe.html

## carbon gen command
CARBON_GENX := $(JAVA)
CARBON_GENX += $(CARBON_GEN_SCRIPT)
CARBON_GENX += --html $(CARBON_HTML) 

.PHONY: carbon-gen
carbon-gen: $(CARBON_GEN_SCRIPT)
	$(CARBON_GENX)
	
$(CARBON_GEN_SCRIPT): $(CARBON_GEN_JAVA)
	sed '/\/\/ SED_REMOVE/d' $< > $@

#
# ui@compile
#

## Compile deps
COMPILE_DEPS := $(WAY)

include make/java-compile.mk

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

include make/java-test.mk

#
# ui@docs-compile
#

## docs source directory
DOCS := docs

## docs source files
DOCS_SOURCES0 := $(SOURCES)
DOCS_SOURCES1 := $(shell find ${DOCS} -type f -name '*.java' -print)

## docs source files modified since last compilation (dynamically evaluated)
DOCS_DIRTY0 :=
DOCS_DIRTY1 :=

## docs holds the list of files to be compiled 
DOCS_COMPILE_SOURCES := $(WORK)/docs-compile-sources

## docs class output path
DOCS_CLASS_OUTPUT := $(WORK)/docs

## docs compiled classes
DOCS_CLASSES0 := $(SOURCES:$(MAIN)/%.java=$(DOCS_CLASS_OUTPUT)/%.class)
DOCS_CLASSES1 := $(DOCS_SOURCES:$(DOCS)/%.java=$(DOCS_CLASS_OUTPUT)/%.class)

## docs javac options
DOCS_JAVACX  = $(JAVAC)
DOCS_JAVACX += -d $(DOCS_CLASS_OUTPUT)
DOCS_JAVACX += -Xlint:none
DOCS_JAVACX += -Xpkginfo:always
DOCS_JAVACX += --module-path @$(COMPILE_PATH)
DOCS_JAVACX += --module-version $(VERSION)
DOCS_JAVACX += --release $(JAVA_RELEASE)
DOCS_JAVACX += --source-path $(MAIN):$(DOCS)
DOCS_JAVACX += @$(COMPILE_SOURCES)

## docs compilation marker
DOCS_COMPILE_MARKER := $(WORK)/docs-compile-marker

## compilation requirements
DOCS_COMPILE_REQS := $(DOCS_CLASSES0)
DOCS_COMPILE_REQS += $(DOCS_CLASSES1)
DOCS_COMPILE_REQS += $(COMPILE_PATH)

#
# docs compilation targets
#

.PHONY: docs-compile
docs-compile: $(DOCS_COMPILE_MARKER)

.PHONY: docs-compile-clean
docs-compile-clean:
	rm -rf $(DOCS_CLASS_OUTPUT) $(DOCS_COMPILE_MARKER) $(COMPILE_PATH)

$(DOCS_CLASSES0): $(DOCS_CLASS_OUTPUT)/%.class: $(MAIN)/%.java
	$(eval DOCS_DIRTY0 += $$<)

$(DOCS_CLASSES1): $(DOCS_CLASS_OUTPUT)/%.class: $(DOCS)/%.java
	$(eval DOCS_DIRTY1 += $$<)

$(DOCS_COMPILE_MARKER): $(DOCS_COMPILE_REQS) | $(WORK)
	$(file > $(DOCS_COMPILE_SOURCES).tmp,$(strip $(DOCS_DIRTY0)))
	$(file >> $(DOCS_COMPILE_SOURCES).tmp,$(strip $(DOCS_DIRTY1)))
	cat $(DOCS_COMPILE_SOURCES).tmp | tr -d '\n' > $(DOCS_COMPILE_SOURCES)
	if [ -s $(DOCS_COMPILE_SOURCES) ]; then \
		$(DOCS_JAVACX); \
	fi
	echo "$(DOCS_CLASS_OUTPUT)" > $@
	$(call uniq-resolution-files,$(COMPILE_RESOLUTION_FILES)) >> $@

#
# ui@docs
#

## docs main class
DOCS_MAIN := objectos.ui.Docs

## docs module-path
DOCS_MODULE_PATH := $(WORK)/docs-module-path

## docs java command
DOCS_JAVAX := $(JAVA)
DOCS_JAVAX += --module-path @$(DOCS_MODULE_PATH)
DOCS_JAVAX += --module $(MODULE)/$(DOCS_MAIN)

.PHONY: docs
docs: $(DOCS_MODULE_PATH)
	$(DOCS_JAVAX)

.PHONY: docs-clean
docs-clean:
	rm -f $(DOCS_MODULE_PATH)

$(DOCS_MODULE_PATH): $(DOCS_COMPILE_MARKER)
	echo $(DOCS_CLASS_OUTPUT) > $@.tmp
	cat $(COMPILE_RESOLUTION_FILES) >> $@.tmp
	cat $@.tmp | paste --delimiter='$(MODULE_PATH_SEPARATOR)' --serial > $@

#
# ui@javadoc
#

include make/java-javadoc.mk

#
# ui@jar
#

## do not add the X* classes
JAR_EXCLUDES := X*.class

include make/java-jar.mk

#
# ui@install
#

include make/java-install.mk
