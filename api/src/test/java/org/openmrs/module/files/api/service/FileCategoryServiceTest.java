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
import org.openmrs.module.files.FileCategory;
import org.openmrs.test.BaseModuleContextSensitiveTest;

import java.util.List;

public class FileCategoryServiceTest extends BaseModuleContextSensitiveTest {
  @Before
  public void setupTest() throws Exception {
    executeDataSet("datasets/FileCategoryServiceTest.xml");
  }

  @Test
  public void shouldCreateNew() {
    final FileCategory fileCategory = new FileCategory();
    fileCategory.setName("NewCategory");

    final FileCategoryService fileCategoryService = Context.getService(FileCategoryService.class);
    final FileCategory savedFileCategory = fileCategoryService.saveFileCategory(fileCategory);

    Assert.assertEquals(fileCategory.getName(), savedFileCategory.getName());
  }

  @Test
  public void shouldPurgeExisting() {
    final FileCategoryService fileCategoryService = Context.getService(FileCategoryService.class);
    final FileCategory toDelete = fileCategoryService.getFileCategory(1);
    fileCategoryService.purgeFileCategory(toDelete);

    long totalCount = fileCategoryService.getFileCategoryCount(true);

    Assert.assertEquals(2, totalCount);
  }

  @Test
  public void shouldUpdateExisting() {
    final String newDescription = "Updated!";
    final FileCategoryService fileCategoryService = Context.getService(FileCategoryService.class);
    final FileCategory toUpdate = fileCategoryService.getFileCategory(1);

    toUpdate.setDescription(newDescription);

    final FileCategory updated = fileCategoryService.getFileCategory(toUpdate.getId());

    Assert.assertNotNull(updated);
    Assert.assertEquals(newDescription, updated.getDescription());
  }

  @Test
  public void shouldReturnNotRetired() {
    final FileCategoryService fileCategoryService = Context.getService(FileCategoryService.class);
    final List<FileCategory> fileCategories = fileCategoryService.getAllFileCategories();

    Assert.assertEquals(2, fileCategories.size());
    Assert.assertFalse(fileCategories.get(0).getRetired());
    Assert.assertFalse(fileCategories.get(1).getRetired());
  }
}
