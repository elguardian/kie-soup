/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.kie.soup.project.datamodel.commons.imports;

import org.junit.Test;
import org.kie.soup.project.datamodel.imports.Imports;

import static org.junit.Assert.*;

/**
 * Tests for ImportsParser
 */
public class ImportsParserTest {

    @Test
    public void testNullContent() {
        final String content = null;

        final Imports imports = ImportsParser.parseImports(content);
        assertNotNull(imports);
        assertTrue(imports.getImports().isEmpty());
    }

    @Test
    public void testEmptyContent() {
        final String content = "";

        final Imports imports = ImportsParser.parseImports(content);
        assertNotNull(imports);
        assertTrue(imports.getImports().isEmpty());
    }

    @Test
    public void testCommentedContent() {
        final String content = "#This is a comment";

        final Imports imports = ImportsParser.parseImports(content);
        assertNotNull(imports);
        assertTrue(imports.getImports().isEmpty());
    }

    @Test
    public void testSingleImportContent() {
        final String content = "import java.lang.String;";

        final Imports imports = ImportsParser.parseImports(content);
        assertNotNull(imports);
        assertEquals(1,
                     imports.getImports().size());
        assertEquals("java.lang.String",
                     imports.getImports().get(0).getType());
    }

    @Test
    public void testMultipleImportsContent() {
        final String content = ""
                + "import java.lang.String;\n"
                + "import java.lang.Double;\n";

        final Imports imports = ImportsParser.parseImports(content);
        assertNotNull(imports);
        assertEquals(2,
                     imports.getImports().size());
        assertEquals("java.lang.String",
                     imports.getImports().get(0).getType());
        assertEquals("java.lang.Double",
                     imports.getImports().get(1).getType());
    }

    @Test
    public void testMixedContent() {
        final String content = ""
                + "import java.lang.String;\n"
                + "#This is a comment\n"
                + "import java.lang.Double;\n"
                + "\n"
                + "import java.lang.Byte;\n";

        final Imports imports = ImportsParser.parseImports(content);
        assertNotNull(imports);
        assertEquals(3,
                     imports.getImports().size());
        assertEquals("java.lang.String",
                     imports.getImports().get(0).getType());
        assertEquals("java.lang.Double",
                     imports.getImports().get(1).getType());
        assertEquals("java.lang.Byte",
                     imports.getImports().get(2).getType());
    }
}
