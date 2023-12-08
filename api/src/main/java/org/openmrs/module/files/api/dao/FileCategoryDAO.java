/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.files.api.dao;

import org.openmrs.module.files.FileCategory;

import java.util.List;

public class FileCategoryDAO extends BaseFilesOpenmrsMetadataDAO<FileCategory> {
  FileCategoryDAO() {
    super(FileCategory.class);
  }

  public FileCategory getFileCategory(Integer fileCategoryId) {
    return internalRead(fileCategoryId);
  }

  public FileCategory getFileCategory(String name) {
    return internalReadByName(name);
  }

  public FileCategory getFileCategoryByUuid(String uuid) {
    return internalReadByUuid(uuid);
  }

  public List<FileCategory> getAllFileCategories(boolean includeRetired) {
    return internalReadAll(includeRetired);
  }

  public long getFileCategoryCount(boolean includeRetired) {
    return internalCountAll(includeRetired);
  }

  public List<FileCategory> getFileCategoryByNameFragment(String nameFragment) {
    return internalReadByNameFragment(nameFragment);
  }

  public FileCategory saveFileCategory(FileCategory fileCategory) {
    return internalSave(fileCategory);
  }

  public void deleteFileCategory(FileCategory fileCategory) {
    internalDelete(fileCategory);
  }
}
