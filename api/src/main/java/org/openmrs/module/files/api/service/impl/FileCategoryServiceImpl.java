/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 *  graphic logo is a trademark of OpenMRS Inc.
 */

package org.openmrs.module.files.api.service.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.files.FileCategory;
import org.openmrs.module.files.api.dao.FileCategoryDAO;
import org.openmrs.module.files.api.service.FileCategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FileCategoryServiceImpl implements FileCategoryService {
  private FileCategoryDAO fileCategoryDAO;

  @Override
  public void setFileCategoryDAO(FileCategoryDAO fileCategoryDAO) {
    this.fileCategoryDAO = fileCategoryDAO;
  }

  @Override
  @Transactional(readOnly = true)
  public FileCategory getFileCategory(Integer fileCategoryId) throws APIException {
    return fileCategoryDAO.getFileCategory(fileCategoryId);
  }

  @Override
  @Transactional(readOnly = true)
  public FileCategory getFileCategory(String name) throws APIException {
    return fileCategoryDAO.getFileCategory(name);
  }

  @Override
  @Transactional(readOnly = true)
  public FileCategory getFileCategoryByUuid(String uuid) throws APIException {
    return fileCategoryDAO.getFileCategoryByUuid(uuid);
  }

  @Override
  @Transactional(readOnly = true)
  public List<FileCategory> getAllFileCategories() throws APIException {
    return fileCategoryDAO.getAllFileCategories(false);
  }

  @Override
  @Transactional(readOnly = true)
  public List<FileCategory> getAllFileCategories(boolean includeRetired) throws APIException {
    return fileCategoryDAO.getAllFileCategories(includeRetired);
  }

  @Override
  @Transactional(readOnly = true)
  public long getFileCategoryCount(boolean includeRetired) throws APIException {
    return fileCategoryDAO.getFileCategoryCount(includeRetired);
  }

  @Override
  @Transactional
  public FileCategory saveFileCategory(FileCategory fileCategory) throws APIException {
    return fileCategoryDAO.saveFileCategory(fileCategory);
  }

  @Override
  @Transactional
  public FileCategory retireFileCategory(FileCategory fileCategory, String reason) throws APIException {
    // Fields are set by org.openmrs.aop.RequiredDataAdvice
    return fileCategoryDAO.saveFileCategory(fileCategory);
  }

  @Override
  @Transactional
  public FileCategory unretireFileCategory(FileCategory fileCategory) throws APIException {
    // Fields are set by org.openmrs.aop.RequiredDataAdvice
    return fileCategoryDAO.saveFileCategory(fileCategory);
  }

  @Override
  @Transactional
  public void purgeFileCategory(FileCategory fileCategory) throws APIException {
    fileCategoryDAO.deleteFileCategory(fileCategory);
  }

  @Override
  public void onStartup() {

  }

  @Override
  public void onShutdown() {

  }
}
