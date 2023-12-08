package org.openmrs.module.files.api.service;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.files.FileCategory;
import org.openmrs.module.files.api.constant.PrivilegeConstants;
import org.openmrs.module.files.api.dao.FileCategoryDAO;

import java.util.List;

public interface FileCategoryService extends OpenmrsService {
  void setFileCategoryDAO(FileCategoryDAO fileCategoryDAO);

  FileCategory getFileCategory(Integer fileCategoryId) throws APIException;

  FileCategory getFileCategory(String name) throws APIException;

  FileCategory getFileCategoryByUuid(String uuid) throws APIException;

  List<FileCategory> getAllFileCategories() throws APIException;

  List<FileCategory> getAllFileCategories(boolean includeRetired) throws APIException;

  long getFileCategoryCount(boolean includeRetired) throws APIException;

  @Authorized(PrivilegeConstants.MANAGE_FILE_CATEGORIES)
  FileCategory saveFileCategory(FileCategory fileCategory) throws APIException;

  @Authorized(PrivilegeConstants.MANAGE_FILE_CATEGORIES)
  FileCategory retireFileCategory(FileCategory fileCategory, String reason) throws APIException;

  @Authorized(PrivilegeConstants.MANAGE_FILE_CATEGORIES)
  FileCategory unretireFileCategory(FileCategory fileCategory) throws APIException;

  @Authorized(PrivilegeConstants.PURGE_FILE_CATEGORIES)
  void purgeFileCategory(FileCategory fileCategory) throws APIException;
}
