/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.files.api.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.files.File;
import org.openmrs.module.files.FileCategory;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Random;

public class FileServiceTest extends BaseModuleContextSensitiveTest {
  @Before
  public void setupTest() throws Exception {
    executeDataSet("datasets/FileServiceTest.xml");
  }

  @Test
  public void shouldCreateNew() {
    final FileCategoryService fileCategoryService = Context.getService(FileCategoryService.class);
    final FileCategory firstCategory = fileCategoryService.getFileCategory(1);
    final byte[] fileData = generateRandomByteArray(1024);

    final File newFile = new File();
    newFile.setFilename("test");
    newFile.setMimeType("text/plain");
    newFile.setFileCategory(firstCategory);
    newFile.setFileData(fileData);

    final FileService fileService = Context.getService(FileService.class);
    final File savedFile = fileService.saveFile(newFile);

    Assert.assertNotNull(savedFile);
    Assert.assertNotNull(savedFile.getId());
    Assert.assertEquals(newFile.getFilename(), savedFile.getFilename());
    Assert.assertEquals(newFile.getFileCategory(), savedFile.getFileCategory());
    Assert.assertEquals(newFile.getFileData(), savedFile.getFileData());
  }

  @Test
  public void shouldPurgeExisting() {
    final FileService fileService = Context.getService(FileService.class);
    final File toDelete = fileService.getFile(1);
    fileService.purgeFile(toDelete);

    long totalCount = fileService.getFileCount(true);

    Assert.assertEquals(2, totalCount);
  }

  @Test
  public void shouldUpdateExisting() {
    final byte[] fileData = generateRandomByteArray(1024);

    final FileService fileService = Context.getService(FileService.class);
    final File toUpdate = fileService.getFile(1);

    toUpdate.setFileData(fileData);
    fileService.saveFile(toUpdate);

    final File updated = fileService.getFile(toUpdate.getId());

    Assert.assertNotNull(updated);
    Assert.assertEquals(fileData, updated.getFileData());
  }

  @Test
  public void shouldReturnNotVoided() {
    final FileService fileService = Context.getService(FileService.class);
    final List<File> files = fileService.getAllFiles();

    Assert.assertEquals(2, files.size());
    Assert.assertFalse(files.get(0).getVoided());
    Assert.assertFalse(files.get(1).getVoided());
  }

  @Test
  public void shouldFindExisting() {
    final FileCategoryService fileCategoryService = Context.getService(FileCategoryService.class);
    final FileCategory firstCategory = fileCategoryService.getFileCategory(1);

    final FileService fileService = Context.getService(FileService.class);
    final File file = fileService.getFileByFilenameAndCategory("/test.test", firstCategory);

    Assert.assertNotNull(file);
    Assert.assertEquals("/test.test", file.getFilename());
    Assert.assertEquals(firstCategory, file.getFileCategory());
    Assert.assertEquals("Hello world!", new String(file.getFileData(), StandardCharsets.UTF_8));
  }

  private byte[] generateRandomByteArray(int length) {
    final byte[] data = new byte[length];
    new Random().nextBytes(data);
    return data;
  }
}
