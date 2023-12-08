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
import org.openmrs.module.files.File;
import org.openmrs.module.files.FileCategory;
import org.openmrs.module.files.api.dao.FileDAO;
import org.openmrs.module.files.api.service.FileService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class FileServiceImpl implements FileService {
  private FileDAO fileDAO;

  @Override
  public void setFileDAO(FileDAO fileDAO) {
    this.fileDAO = fileDAO;
  }

  @Override
  @Transactional(readOnly = true)
  public File getFile(Integer fileId) throws APIException {
    return fileDAO.getFile(fileId);
  }

  @Override
  @Transactional(readOnly = true)
  public File getFileByFilenameAndCategory(String filename, FileCategory category) throws APIException {
    return fileDAO.getFileByFilenameAndCategory(filename, category);
  }

  @Override
  @Transactional(readOnly = true)
  public File getFileByUuid(String uuid) throws APIException {
    return fileDAO.getFileByUuid(uuid);
  }

  @Override
  @Transactional(readOnly = true)
  public List<File> getAllFiles() throws APIException {
    return fileDAO.getAllFiles(false);
  }

  @Override
  @Transactional(readOnly = true)
  public List<File> getAllFiles(boolean includeVoided) throws APIException {
    return fileDAO.getAllFiles(includeVoided);
  }

  @Override
  @Transactional(readOnly = true)
  public long getFileCount(boolean includeVoided) throws APIException {
    return fileDAO.getFileCount(includeVoided);
  }

  @Override
  @Transactional
  public File saveFile(File file) throws APIException {
    return fileDAO.saveFile(file);
  }

  @Override
  @Transactional
  public File voidFile(File file, String reason) throws APIException {
    // Fields are set by org.openmrs.aop.RequiredDataAdvice
    return fileDAO.saveFile(file);
  }

  @Override
  @Transactional
  public File unvoidFile(File file) throws APIException {
    // Fields are set by org.openmrs.aop.RequiredDataAdvice
    return fileDAO.saveFile(file);
  }

  @Override
  @Transactional
  public void purgeFile(File file) throws APIException {
    fileDAO.deleteFile(file);
  }

  @Override
  public void onStartup() {

  }

  @Override
  public void onShutdown() {

  }
}
