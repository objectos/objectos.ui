#
# Copyright (C) 2023-2025 Objectos Software LTDA.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

ifndef MAIN
$(error Required java-compile.mk was not included)
endif

define java_patched
#
# compilation
#

## source directory
$(1) := $(2)

## source files
$(1)_SOURCES0 := $$(SOURCES)
$(1)_SOURCES1 := $$(shell find $${$(1)} -type f -name '*.java' -print)

## source files modified since last compilation (dynamically evaluated)
$(1)_DIRTY0 :=
$(1)_DIRTY1 :=

## holds the list of files to be compiled 
$(1)_COMPILE_SOURCES := $$(WORK)/$(2)-compile-sources

## class output path
$(1)_CLASS_OUTPUT := $$(WORK)/$(2)

## compiled classes
$(1)_CLASSES0 := $$(SOURCES:$$(MAIN)/%.java=$$($(1)_CLASS_OUTPUT)/%.class)
$(1)_CLASSES1 := $$($(1)_SOURCES:$$($(1))/%.java=$$($(1)_CLASS_OUTPUT)/%.class)

## javac options
$(1)_JAVACX  = $$(JAVAC)
$(1)_JAVACX += -d $$($(1)_CLASS_OUTPUT)
$(1)_JAVACX += -Xlint:none
$(1)_JAVACX += -Xpkginfo:always
$(1)_JAVACX += --module-path @$$(COMPILE_PATH)
$(1)_JAVACX += --module-version $$(VERSION)
$(1)_JAVACX += $$(if $(1)_COMPILE_ADD_MODULES,$$(foreach x,$$($(1)_COMPILE_ADD_MODULES),--add-modules $$(x)))
$(1)_JAVACX += $$(if $(1)_COMPILE_ADD_READS,$$(foreach x,$$($(1)_COMPILE_ADD_READS),--add-reads $$(x)))
$(1)_JAVACX += --release $$(JAVA_RELEASE)
$(1)_JAVACX += --source-path $$(MAIN):$$($(1))
$(1)_JAVACX += @$$($(1)_COMPILE_SOURCES)

## compilation marker
$(1)_COMPILE_MARKER := $$(WORK)/$(2)-compile-marker

## compilation requirements
$(1)_COMPILE_REQS := $$($(1)_CLASSES0)
$(1)_COMPILE_REQS += $$($(1)_CLASSES1)
$(1)_COMPILE_REQS += $$(COMPILE_PATH)

.PHONY: $(2)-compile
$(2)-compile: $$($(1)_COMPILE_MARKER)

.PHONY: $(2)-compile-clean
$(2)-compile-clean:
	rm -rf $$($(1)_CLASS_OUTPUT) $$($(1)_COMPILE_MARKER) $$(COMPILE_PATH)

$$($(1)_CLASSES0): $$($(1)_CLASS_OUTPUT)/%.class: $$(MAIN)/%.java
	$$(eval $(1)_DIRTY0 += $$$$<)

$$($(1)_CLASSES1): $$($(1)_CLASS_OUTPUT)/%.class: $$($(1))/%.java
	$$(eval $(1)_DIRTY1 += $$$$<)

$$($(1)_COMPILE_MARKER): $$($(1)_COMPILE_REQS) | $$(WORK)
	$$(file > $$($(1)_COMPILE_SOURCES).tmp,$$(strip $$($(1)_DIRTY0)))
	$$(file >> $$($(1)_COMPILE_SOURCES).tmp,$$(strip $$($(1)_DIRTY1)))
	cat $$($(1)_COMPILE_SOURCES).tmp | tr -d '\n' > $$($(1)_COMPILE_SOURCES)
	if [ -s $$($(1)_COMPILE_SOURCES) ]; then \
		$$($(1)_JAVACX); \
	fi
	echo "$$($(1)_CLASS_OUTPUT)" > $$@
	$$(call uniq-resolution-files,$$(COMPILE_RESOLUTION_FILES)) >> $$@
	
#
# running
#

## main class
$$(if $$($(1)_MAIN),,$$(error $(1)_MAIN was not defined))

## module-path
$(1)_MODULE_PATH := $$(WORK)/$(2)-module-path

## java command
$(1)_JAVAX := $$(JAVA)
$(1)_JAVAX += --module-path @$$($(1)_MODULE_PATH)
$(1)_JAVAX += --module $$(MODULE)/$$($(1)_MAIN)

.PHONY: $(2)
$(2): $$($(1)_MODULE_PATH)
	$$($(1)_JAVAX)

.PHONY: $(2)-clean
$(2)-clean:
	rm -f $$($(1)_MODULE_PATH)

$$($(1)_MODULE_PATH): $$($(1)_COMPILE_MARKER)
	echo $$($(1)_CLASS_OUTPUT) > $$@.tmp
	cat $$(COMPILE_RESOLUTION_FILES) >> $$@.tmp
	cat $$@.tmp | paste --delimiter='$$(MODULE_PATH_SEPARATOR)' --serial > $$@
endef