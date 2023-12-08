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

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.files.File;
import org.openmrs.module.files.FileCategory;
import org.openmrs.module.files.api.constant.PrivilegeConstants;
import org.openmrs.module.files.api.dao.FileDAO;

import java.util.List;

public interface FileService extends OpenmrsService {
  void setFileDAO(FileDAO fileDAO);

  File getFile(Integer fileId) throws APIException;

  File getFileByFilenameAndCategory(String filename, FileCategory category) throws APIException;

  File getFileByUuid(String uuid) throws APIException;

  List<File> getAllFiles() throws APIException;

  List<File> getAllFiles(boolean includeVoided) throws APIException;

  long getFileCount(boolean includeVoided) throws APIException;

  @Authorized(PrivilegeConstants.MANAGE_FILE_CATEGORIES)
  File saveFile(File file) throws APIException;

  @Authorized(PrivilegeConstants.MANAGE_FILE_CATEGORIES)
  File voidFile(File file, String reason) throws APIException;

  @Authorized(PrivilegeConstants.MANAGE_FILE_CATEGORIES)
  File unvoidFile(File file) throws APIException;

  @Authorized(PrivilegeConstants.PURGE_FILE_CATEGORIES)
  void purgeFile(File file) throws APIException;
}
